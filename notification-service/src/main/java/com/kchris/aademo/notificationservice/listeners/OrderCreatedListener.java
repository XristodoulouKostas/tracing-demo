package com.kchris.aademo.notificationservice.listeners;

import com.kchris.aademo.notificationservice.domain.events.OrderCreatedEvent;
import commonutilities.ArtificialError;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderCreatedListener {

  private final ArtificialError artificialError = new ArtificialError();

  @KafkaListener(topics = "order-created", groupId = "notification-service-group", containerFactory = "kafkaListenerContainerFactory")
  public void handleOrderCreated(Message<OrderCreatedEvent> message) {
    log.info("Message headers: {}", message.getHeaders());
    artificialError.randomlyAddDelay(0.3, 10, ChronoUnit.SECONDS);
    log.info("Notification sent for order: " + message.getPayload());
  }
}
