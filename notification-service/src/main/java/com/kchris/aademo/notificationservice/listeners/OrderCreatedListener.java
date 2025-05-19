package com.kchris.aademo.notificationservice.listeners;

import com.kchris.aademo.notificationservice.domain.events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCreatedListener {

  @KafkaListener(topics = "order-created", groupId = "notification-service-group", containerFactory = "kafkaListenerContainerFactory")
  public void handleOrderCreated(OrderCreatedEvent message) {
    System.out.println("Notification received for order: " + message);
    // send email/SMS/etc. in a real app
  }
}
