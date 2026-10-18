package io.github.jotabrc.densha_eda_express.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopic {

    public static final String TOPIC_INVENTORY_FAILED = "inventory.failed.0";
    public static final String TOPIC_INVENTORY_FAILED_RETRY = "inventory.failed.0-retry";
    public static final String TOPIC_INVENTORY_RESERVED = "inventory.reserved.0";
    public static final String TOPIC_INVENTORY_RESERVED_RETRY = "inventory.reserved.0-retry";
    public static final String TOPIC_INVENTORY_DEDUCTED = "inventory.deducted.0";
    public static final String TOPIC_INVENTORY_DEDUCTED_RETRY = "inventory.deducted.0-retry";
    public static final String TOPIC_ORDER_CREATED = "order.created.0";
    public static final String TOPIC_ORDER_CREATED_RETRY = "order.created.0-retry";
    public static final String TOPIC_PAYMENT_FAILED = "payment.failed.0";
    public static final String TOPIC_PAYMENT_FAILED_RETRY = "payment.failed.0-retry";
    public static final String TOPIC_PAYMENT_FULFILLED = "payment.fulfilled.0";
    public static final String TOPIC_PAYMENT_FULFILLED_RETRY = "payment.fulfilled.0-retry";
    public static final String TOPIC_PAYMENT_REFUNDED = "payment.refunded.0";
    public static final String TOPIC_PAYMENT_REFUNDED_RETRY = "payment.refunded.0-retry";
    public static final String TOPIC_SHIPPING_SENT = "shipping.sent.0";
    public static final String TOPIC_SHIPPING_SENT_RETRY = "shipping.sent.0-retry";
    public static final String TOPIC_NOTIFICATION_NOTIFIED = "notification.notified.0";
    public static final String TOPIC_NOTIFICATION_NOTIFIED_RETRY = "notification.notified.0-retry";

    @Bean
    NewTopic inventoryFailed() {
        return new NewTopic(TOPIC_INVENTORY_FAILED, 3, (short) 2);
    }

    @Bean
    NewTopic inventoryReserved() {
        return new NewTopic(TOPIC_INVENTORY_RESERVED, 3, (short) 2);
    }

    @Bean
    NewTopic inventoryDeducted() {
        return new NewTopic(TOPIC_INVENTORY_DEDUCTED, 3, (short) 2);
    }

    @Bean
    NewTopic inventoryFailedRetry() {
        return new NewTopic(TOPIC_INVENTORY_FAILED_RETRY, 3, (short) 2);
    }

    @Bean
    NewTopic inventoryReservedRetry() {
        return new NewTopic(TOPIC_INVENTORY_RESERVED_RETRY, 3, (short) 2);
    }

    @Bean
    NewTopic inventoryDeductedRetry() {
        return new NewTopic(TOPIC_INVENTORY_DEDUCTED_RETRY, 3, (short) 2);
    }

    @Bean
    NewTopic orderCreated() {
        return new NewTopic(TOPIC_ORDER_CREATED, 3, (short) 2);
    }

    @Bean
    NewTopic orderCreatedRetry() {
        return new NewTopic(TOPIC_ORDER_CREATED_RETRY, 3, (short) 2);
    }

    @Bean
    NewTopic paymentFailed() {
        return new NewTopic(TOPIC_PAYMENT_FAILED, 3, (short) 2);
    }

    @Bean
    NewTopic paymentFulfilled() {
        return new NewTopic(TOPIC_PAYMENT_FULFILLED, 3, (short) 2);
    }

    @Bean
    NewTopic paymentFailedRetry() {
        return new NewTopic(TOPIC_PAYMENT_FAILED_RETRY, 3, (short) 2);
    }

    @Bean
    NewTopic paymentFulfilledRetry() {
        return new NewTopic(TOPIC_PAYMENT_FULFILLED_RETRY, 3, (short) 2);
    }

    @Bean
    NewTopic paymentRefunded() {
        return new NewTopic(TOPIC_PAYMENT_REFUNDED, 3, (short) 2);
    }

    @Bean
    NewTopic paymentRefundedRetry() {
        return new NewTopic(TOPIC_PAYMENT_REFUNDED_RETRY, 3, (short) 2);
    }

    @Bean
    NewTopic shippingSent() {
        return new NewTopic(TOPIC_SHIPPING_SENT, 3, (short) 2);
    }

    @Bean
    NewTopic shippingSentRetry() {
        return new NewTopic(TOPIC_SHIPPING_SENT_RETRY, 3, (short) 2);
    }

    @Bean
    NewTopic notificationNotified() {
        return new NewTopic(TOPIC_NOTIFICATION_NOTIFIED, 3, (short) 2);
    }

    @Bean
    NewTopic notificationNotifiedRetry() {
        return new NewTopic(TOPIC_NOTIFICATION_NOTIFIED_RETRY, 3, (short) 2);
    }
}
