package com.product.productmanagement.logic.impl;

import com.product.productmanagement.dataaccess.dao.ProductDao;
import com.product.productmanagement.dataaccess.entities.Category;
import com.product.productmanagement.dataaccess.entities.Product;
import com.product.productmanagement.exceptions.ResourceNotFoundException;
import com.product.productmanagement.logic.api.ProductService;
import com.product.productmanagement.to.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void addProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setNom(productDto.getNom());
        product.setDescription(productDto.getDescription());
        product.setDateCreation(new Date());

        String imagePath = productDto.getImage().getOriginalFilename();
        product.setImage(imagePath);

        File imageFile = new File("C:/Users/Youssef/Desktop/Product Management System/product-management-frontend/public/assets/images/" + imagePath);
        productDto.getImage().transferTo(imageFile);

        product.setSupprimer(false);
        product.setSlug(productDto.getNom().replace(" ", "-"));
        product.setDateModification(new Date());
        product.setCategory(new Category(productDto.getCategoryId()));
        this.productDao.save(product);
    }

    @Override
    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setNom(product.getNom());
        productDto.setDescription(product.getDescription());
        productDto.setDateCreation(product.getDateCreation());
        productDto.setImagePath(product.getImage());
        productDto.setSupprimer(product.getSupprimer());
        productDto.setSlug(product.getSlug());
        productDto.setDateModification(product.getDateModification());
        if (product.getCategory() != null) {
            productDto.setCategoryId(product.getCategory().getId());
        }
        return productDto;
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = this.productDao.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product: products) {
            productDtos.add(this.getProductDto(product));
        }
        return productDtos;
    }

    @Override
    public ProductDto getProduct(Long id) {
        ProductDto productDto = new ProductDto();
        Product product = this.productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found"));
        productDto.setNom(product.getNom());
        productDto.setDescription(product.getDescription());
        productDto.setSlug(product.getSlug());
        productDto.setImagePath(product.getImage());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setDateCreation(product.getDateCreation());
        productDto.setDateModification(product.getDateModification());
        return productDto;
    }

    @Override
    public List<ProductDto> products() {
        List<Product> products = this.productDao.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product: products) {
            if (!product.getSupprimer())
                productDtos.add(this.getProductDto(product));
        }
        return productDtos;
    }

    @Override
    public void updateProduct(long id, ProductDto productDto) throws Exception {
        Product product = this.productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found"));
        String slug;
        if (productDto.getNom() != null){
            product.setNom(productDto.getNom());
            slug = productDto.getNom().replace(" ", "-");
        } else {
            product.setNom(product.getNom());
            slug = product.getNom().replace(" ", "-");
        }
        if (productDto.getDescription() != null){
            product.setDescription(productDto.getDescription());
        } else {
            product.setDescription(product.getDescription());
        }
        String imagePath = productDto.getImage().getOriginalFilename();
        product.setImage(imagePath);

        File imageFile = new File("C:/Users/Youssef/Desktop/Product Management System/product-management-frontend/public/assets/images/" + imagePath);
        productDto.getImage().transferTo(imageFile);

        product.setId(product.getId());
        product.setDateCreation(product.getDateCreation());
        product.setSupprimer(product.getSupprimer());
        product.setSlug(slug);
        product.setDateModification(new Date());
        product.setCategory(product.getCategory());
        this.productDao.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        this.productDao.deleteById(id);
    }

    @Override
    public int counting() {
        List<ProductDto> productDtos = this.products();
        return productDtos.size();
    }

    @Override
    public void deleteUpdateProduct(long id) {
        Product product = this.productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found"));
        product.setSupprimer(true);
        product.setDateModification(new Date());
        this.productDao.save(product);
    }

    public Product newUpdateProduct(Long id, ProductDto productDto) throws IOException {
        Product product = productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found"));
        String slug;
        if (productDto.getNom() != null){
            product.setNom(productDto.getNom());
            slug = productDto.getNom().replace(" ", "-");
        } else {
            product.setNom(product.getNom());
            slug = product.getNom().replace(" ", "-");
        }
        if (productDto.getDescription() != null){
            product.setDescription(productDto.getDescription());
        } else {
            product.setDescription(product.getDescription());
        }

        product.setImage(saveImage(productDto.getImage()));
        product.setId(product.getId());
        product.setDateCreation(product.getDateCreation());
        product.setSupprimer(product.getSupprimer());
        product.setSlug(slug);
        product.setDateModification(new Date());
        product.setCategory(product.getCategory());
        return productDao.save(product);
    }

    private String saveImage(MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID().toString() + "." + image.getOriginalFilename().split("\\.")[1];
        Path path = Paths.get("C:/Users/Youssef/WebstormProjects/product-management-frontend/public/assets/images/" + fileName);
        Files.write(path, image.getBytes());

        return fileName;
    }

}
