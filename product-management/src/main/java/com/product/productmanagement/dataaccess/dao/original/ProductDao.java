package com.product.productmanagement.dataaccess.dao.original;

import com.product.productmanagement.dataaccess.entities.Category;
import com.product.productmanagement.dataaccess.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    List<Product> findByCategory_Id(Long categoryId);
}
