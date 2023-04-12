package com.product.productmanagement.consumers;

import com.product.productmanagement.dataaccess.dao.kafka.CategoryKafkaRepository;
import com.product.productmanagement.dataaccess.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CategoryConsumer {

    @Autowired
    private CategoryKafkaRepository categoryKafkaRepository;

    @KafkaListener(topics = "category-topic", groupId = "category-consumer", containerFactory = "categoryKafkaListenerContainerFactory")
    public void receive(Category category) {
        categoryKafkaRepository.save(category);
    }

}
