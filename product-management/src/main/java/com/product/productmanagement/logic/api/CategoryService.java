package com.product.productmanagement.logic.api;

import com.product.productmanagement.dataaccess.entities.Category;
import com.product.productmanagement.dataaccess.entities.Product;
import com.product.productmanagement.to.CategoryDto;
import com.product.productmanagement.to.ProductDto;

import java.util.List;

public interface CategoryService {

    void addCategory(CategoryDto categoryDto);
    List<CategoryDto> allCategories();
    List<CategoryDto> categories();
    void updateCategory(long id, CategoryDto updatedCategory);
    void deleteCategory(long id);
    void updateDelete(long id);
    int counting();
    CategoryDto getCategoryDto(Category category) ;

    CategoryDto getCategory(Long id);

}
