package com.kchris.aademo.notificationservice.listeners;

import com.kchris.aademo.notificationservice.domain.events.OrderCreatedEvent;
import commonutilities.ArtificialError;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderCreatedListener {

  private ArtificialError artificialError = new ArtificialError();

  @KafkaListener(topics = "order-created", groupId = "notification-service-group", containerFactory = "kafkaListenerContainerFactory")
  public void handleOrderCreated(OrderCreatedEvent message) {
    artificialError.randomlyFailWith(0.1, new RuntimeException("Could not send the notification"));
    artificialError.randomlyAddDelay(0.3, 10, ChronoUnit.SECONDS);
    log.info("Notification sent for order: " + message);
  }
}
