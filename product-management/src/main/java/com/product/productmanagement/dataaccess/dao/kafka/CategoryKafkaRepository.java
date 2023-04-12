package com.product.productmanagement.dataaccess.dao.kafka;

import com.product.productmanagement.dataaccess.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryKafkaRepository extends JpaRepository<Category, Long> {
}
