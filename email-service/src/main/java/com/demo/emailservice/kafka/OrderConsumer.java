package com.demo.emailservice.kafka;

import com.demo.emailservice.email.SendMailService;
import com.tqm.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @Autowired
    private SendMailService mailService;

    private static Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(OrderEvent event) {
        mailService.sendMail("quangminh8699@gmail.com", "Test", event.toString());
        LOGGER.info("Successfully");
    }
    
}
