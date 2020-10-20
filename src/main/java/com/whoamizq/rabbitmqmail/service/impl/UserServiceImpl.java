package com.whoamizq.rabbitmqmail.service.impl;

import com.whoamizq.rabbitmqmail.common.Constant;
import com.whoamizq.rabbitmqmail.common.ResponseCode;
import com.whoamizq.rabbitmqmail.common.ServerResponse;
import com.whoamizq.rabbitmqmail.config.RabbitConfig;
import com.whoamizq.rabbitmqmail.mapper.MsgLogMapper;
import com.whoamizq.rabbitmqmail.mapper.UserMapper;
import com.whoamizq.rabbitmqmail.mq.MessageHelper;
import com.whoamizq.rabbitmqmail.pojo.LoginLog;
import com.whoamizq.rabbitmqmail.pojo.MsgLog;
import com.whoamizq.rabbitmqmail.pojo.User;
import com.whoamizq.rabbitmqmail.service.UserService;
import com.whoamizq.rabbitmqmail.service.batch.mapperProxy.MapperProxy;
import com.whoamizq.rabbitmqmail.util.JodaTimeUtil;
import com.whoamizq.rabbitmqmail.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public User getOne(Integer id) {
        return userMapper.selectOne(id);
    }

    @Override
    public ServerResponse login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ServerResponse.error(ResponseCode.USERNAME_OR_PASSWORD_EMPTY.getMsg());
        }
        User user = userMapper.selectByUsernameAndPassword(username, password);
        if (null == user){
            log.info("用户名不存在或密码错误：{}:---:{}",username,password);
            return ServerResponse.error(ResponseCode.USERNAME_OR_PASSWORD_WRONG.getMsg());
        }
        saveAndSendMsg(user);
        return ServerResponse.success();
    }

    @Override
    public void batchInsert(List<User> list) {
        new MapperProxy<User>(userMapper).batchInsert(list);
    }

    @Override
    public void batchUpdate(List<User> list) {
        new MapperProxy<User>(userMapper).batchUpdate(list);
    }

    /**
     * @author: whoamizq
     * @description: 保存并发送消息
     * @date: 11:11 2020/10/19
     * @param: [user]
     * @return: void
     **/
    private void saveAndSendMsg(User user) {
        String msgId = RandomUtil.UUID32();
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getId());
        loginLog.setType(Constant.LogType.LOGIN);
        Date date = new Date();
        loginLog.setDescription(user.getUsername() + "在：" + JodaTimeUtil.dateToStr(date) + "登录系统");
        loginLog.setCreateTime(date);
        loginLog.setUpdateTime(date);
        loginLog.setMsgId(msgId);

        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(RabbitConfig.LOGIN_LOG_EXCHANGE_NAME, RabbitConfig.LOGIN_LOG_ROUTING_KEY_NAME,
                Objects.requireNonNull(MessageHelper.objToMsg(loginLog)),correlationData);
        // 消息日志
        MsgLog msgLog = new MsgLog(msgId, loginLog, RabbitConfig.LOGIN_LOG_EXCHANGE_NAME, RabbitConfig.LOGIN_LOG_ROUTING_KEY_NAME);
        msgLogMapper.insert(msgLog);
        log.info("---完成登录操作---");
    }


}
