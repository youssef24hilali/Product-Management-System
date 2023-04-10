package com.product.productmanagement.logic.impl;

import com.product.productmanagement.dataaccess.dao.CategoryDao;
import com.product.productmanagement.dataaccess.dao.ProductDao;
import com.product.productmanagement.dataaccess.entities.Category;
import com.product.productmanagement.dataaccess.entities.Product;
import com.product.productmanagement.exceptions.DeletingException;
import com.product.productmanagement.exceptions.ResourceNotFoundException;
import com.product.productmanagement.logic.api.CategoryService;
import com.product.productmanagement.to.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public void addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        categoryDto.setSupprimer(false);
        category.setNom(categoryDto.getNom());
        category.setDescription(categoryDto.getDescription());
        category.setSlug(categoryDto.getNom().replace(" ", "-"));
        category.setSupprimer(categoryDto.getSupprimer());
        category.setDateModification(new Date());
        category.setDateCreation(new Date());
        this.categoryDao.save(category);
    }

    @Override
    public CategoryDto getCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setNom(category.getNom());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setDateCreation(category.getDateCreation());
        categoryDto.setSlug(category.getSlug());
        categoryDto.setSupprimer(category.getSupprimer());
        categoryDto.setDateModification(category.getDateModification());
        return categoryDto;
    }

    @Override
    public CategoryDto getCategory(Long id) {
        CategoryDto categoryDto = new CategoryDto();
        Category category = this.categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category " + id + " not found"));
        categoryDto.setNom(category.getNom());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setDateCreation(category.getDateCreation());
        categoryDto.setSlug(category.getSlug());
        categoryDto.setDateModification(category.getDateModification());
        return categoryDto;
    }

    @Override
    public List<CategoryDto> allCategories() {
        List<Category> categories = this.categoryDao.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category: categories) {
            categoryDtos.add(this.getCategoryDto(category));
        }
        return categoryDtos;
    }

    @Override
    public List<CategoryDto> categories() {
        List<Category> categories = this.categoryDao.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category: categories) {
            if (!category.getSupprimer())
                categoryDtos.add(this.getCategoryDto(category));
        }
        return categoryDtos;
    }

    @Override
    public void updateCategory(long id, CategoryDto updatedCategory){
        Category category = this.categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category " + id + " not found"));
        String slug;
        if (updatedCategory.getNom() != null) {
            category.setNom(updatedCategory.getNom());
            slug = updatedCategory.getNom().replace(" ", "-");
        } else {
            category.setNom(category.getNom());
            slug = category.getNom().replace(" ", "-");
        }
        if (updatedCategory.getDescription() != null) {
            category.setDescription(updatedCategory.getDescription());
        } else {
            category.setDescription(category.getDescription());
        }
        category.setId(category.getId());
        category.setSlug(slug);
        category.setDateModification(new Date());
        category.setDateCreation(category.getDateCreation());
        this.categoryDao.save(category);
    }

    @Override
    public void deleteCategory(long id) {
        this.categoryDao.deleteById(id);
    }

    @Override
    public void updateDelete(long id) {
        List<Product> products = this.productDao.findByCategory_Id(id);
        if (!products.isEmpty()) {
            throw new DeletingException("Cannot delete category " + id + " because it is referenced in the product table");
        } else {
            Category category = this.categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category " + id + " not found"));
            category.setSupprimer(true);
            category.setDateModification(new Date());
            this.categoryDao.save(category);
        }

    }

    @Override
    public int counting() {
        List<CategoryDto> categoryDtos = this.categories();
        return categoryDtos.size();
    }
}
