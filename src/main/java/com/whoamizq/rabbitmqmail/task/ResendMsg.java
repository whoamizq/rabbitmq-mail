package com.whoamizq.rabbitmqmail.task;

import com.whoamizq.rabbitmqmail.common.Constant;
import com.whoamizq.rabbitmqmail.mq.MessageHelper;
import com.whoamizq.rabbitmqmail.pojo.MsgLog;
import com.whoamizq.rabbitmqmail.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
public class ResendMsg {
    @Autowired
    private MsgLogService msgLogService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 最大投递次数
    private static final int MAX_TRY_COUNT = 3;

    /**
     * 每30s拉取投递失败的消息，重新投递
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void resend(){
        log.info("开始执行定时任务(重新投递消息)");

        List<MsgLog> msgLogs = msgLogService.selectTimeoutMsg();
        msgLogs.forEach(msgLog -> {
            String msgId = msgLog.getMsgId();
            if (msgLog.getTryCount() >= MAX_TRY_COUNT){
                msgLogService.updateStatus(msgId, Constant.MsgLogStatus.DELIVER_FAIL);
                log.info("超过最大重试次数，消息投递失败，msgId：{}", msgId);
            }else {
                msgLogService.updateTryCount(msgId, msgLog.getNextTryTime());
                CorrelationData correlationData = new CorrelationData(msgId);
                rabbitTemplate.convertAndSend(msgLog.getExchange(), msgLog.getRoutingKey(), MessageHelper.objToMsg(msgLog.getMsg()),correlationData);
                log.info("第 -{}- 次重新投递消息",(msgLog.getTryCount()+1));
            }
        });
        log.info("定时任务执行结束(重新投递消息)");
    }
}
