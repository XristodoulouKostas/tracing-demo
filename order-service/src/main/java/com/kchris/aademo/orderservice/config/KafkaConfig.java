package com.kchris.aademo.orderservice.config;

import io.micrometer.common.KeyValues;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.micrometer.KafkaRecordSenderContext;
import org.springframework.kafka.support.micrometer.KafkaTemplateObservationConvention;

@Configuration
public class KafkaConfig {

  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> pf) {
    var kafkaTemplate = new KafkaTemplate<>(pf);
    kafkaTemplate.setObservationEnabled(true);
    // TODO Check what this does.
    kafkaTemplate.setObservationConvention(new KafkaTemplateObservationConvention() {
      @Override
      public KeyValues getLowCardinalityKeyValues(KafkaRecordSenderContext context) {
        return KeyValues.of("topic", context.getDestination(),
            "id", String.valueOf(context.getRecord().key()));
      }
    });
    return kafkaTemplate;
  }

}
