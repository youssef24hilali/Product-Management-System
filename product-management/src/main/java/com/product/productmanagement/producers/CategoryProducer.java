package com.product.productmanagement.producers;

import com.product.productmanagement.dataaccess.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CategoryProducer {

    private static final String TOPIC_NAME = "category-topic";

    @Autowired
    private KafkaTemplate<String, Category> kafkaTemplate;

    public void send(Category category) {
        kafkaTemplate.send(TOPIC_NAME, category);
    }

}
