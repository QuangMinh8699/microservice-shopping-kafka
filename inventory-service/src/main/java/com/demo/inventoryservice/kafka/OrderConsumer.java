package com.demo.inventoryservice.kafka;

import com.demo.inventoryservice.model.Inventory;
import com.demo.inventoryservice.repository.InventoryRepository;
import com.tqm.basedomains.dto.Order;
import com.tqm.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    @Autowired
    InventoryRepository inventoryRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
    @KafkaListener(topics = "${spring.kafka.topic.name}",
    groupId = "${spring.kafka.consumer.group-id}")
    public boolean isInStock(OrderEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s",event));
        //
        Order order = event.getOrder();
        String skuCode = order.getSkuCode();
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode);
        Integer oldQuantity = inventory.getQuantity();
        Integer newQuantity = oldQuantity-order.getQty();
        if(newQuantity >= 0){
            inventory.setQuantity(newQuantity);
            inventoryRepository.save(inventory);
            return true;
        }else{
            inventory.setQuantity(0);
            inventoryRepository.save(inventory);
            return false;
        }


    }
}
