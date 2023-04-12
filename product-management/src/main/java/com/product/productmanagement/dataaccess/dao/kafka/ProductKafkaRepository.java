package com.product.productmanagement.dataaccess.dao.kafka;

import com.product.productmanagement.dataaccess.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductKafkaRepository extends JpaRepository<Product, Long> {
}
