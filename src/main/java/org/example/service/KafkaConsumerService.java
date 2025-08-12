package org.example.service;

import org.example.event.LocationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "location-update", groupId = "order-service-group")
    public void consumeLocationEvent(LocationEvent locationEvent) {
        System.out.println("Location for order " + locationEvent.getOrderId() + " received: " + locationEvent.getLatitude() + ", " + locationEvent.getLongitude());
    }
}
