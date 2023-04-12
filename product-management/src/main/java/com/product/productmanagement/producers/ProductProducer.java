package com.product.productmanagement.producers;

import com.product.productmanagement.dataaccess.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductProducer {

    private static final String TOPIC_NAME = "product-topic";

    @Autowired
    private KafkaTemplate<String, Product> kafkaTemplate;

    public void send(Product product) {
        kafkaTemplate.send(TOPIC_NAME, product);
    }

}
