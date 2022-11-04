package com.demo.inventoryservice.controller;

import com.demo.inventoryservice.kafka.OrderConsumer;
import com.demo.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    OrderConsumer orderConsumer;

    
}
