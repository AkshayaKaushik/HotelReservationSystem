package com.koushik.reservation.configuration.kafka;

import com.koushik.reservation.model.ReservationPaymentDetails;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfiguration {
    private Source source;

    public KafkaConfiguration(Source source) {
        this.source = source;
    }

    public void sendMessage(ReservationPaymentDetails paylload){

    }
}
