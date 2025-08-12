package org.example.service;

import org.example.dto.DeliveryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeliveryProducer {
    private final KafkaTemplate<String, DeliveryRequest> kafkaTemplate;

    @Autowired
    public DeliveryProducer(KafkaTemplate<String, DeliveryRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(DeliveryRequest deliveryRequest) {
        kafkaTemplate.send("order-events", deliveryRequest);
    }
}
