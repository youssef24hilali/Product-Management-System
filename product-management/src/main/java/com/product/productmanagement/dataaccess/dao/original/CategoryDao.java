package com.product.productmanagement.dataaccess.dao.original;

import com.product.productmanagement.dataaccess.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {
}
