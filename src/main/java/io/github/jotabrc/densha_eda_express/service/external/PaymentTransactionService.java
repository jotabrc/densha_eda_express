package io.github.jotabrc.densha_eda_express.service.external;

import io.github.jotabrc.densha_eda_express.domain.Payment;
import io.github.jotabrc.densha_eda_express.domain.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentTransactionService {

    public PaymentStatus fulfill(Payment payment) {
        return PaymentStatus.PAID;
    }
}
