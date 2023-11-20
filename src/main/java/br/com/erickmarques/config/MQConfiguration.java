package br.com.erickmarques.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfiguration {

    @Value("${mq.queues.sender-email}")
    private String senderEmaiQueue;

    @Bean
    public Queue queueSenderEmail() {
        return new Queue(senderEmaiQueue, true);
    }
}
