package com.koushik.reservation.configuration.kafka;

import com.koushik.reservation.model.ReservationPaymentDetails;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfiguration {
    private Source source;

    public KafkaConfiguration(Source source) {
        this.source = source;
    }

    public void sendMessage(ReservationPaymentDetails payload){
        source.output().send(MessageBuilder.withPayload(payload).build());
    }
}
