package com.kchris.aademo.notificationservice.listeners;

import com.kchris.aademo.notificationservice.domain.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderCreatedListener {

  @KafkaListener(topics = "order-created", groupId = "notification-service-group", containerFactory = "kafkaListenerContainerFactory")
  public void handleOrderCreated(OrderCreatedEvent message) {
    log.info("Notification sent for order: " + message);
  }
}
