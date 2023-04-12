package com.product.productmanagement.consumers;

import com.product.productmanagement.dataaccess.dao.kafka.ProductKafkaRepository;
import com.product.productmanagement.dataaccess.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    @Autowired
    private ProductKafkaRepository productKafkaRepository;

    @KafkaListener(topics = "product-topic", groupId = "product-consumer", containerFactory = "productKafkaListenerContainerFactory")
    public void receive(Product product) {
        productKafkaRepository.save(product);
    }

}
