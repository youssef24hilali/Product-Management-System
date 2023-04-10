package com.product.productmanagement.logic.api;

import com.product.productmanagement.dataaccess.entities.Product;
import com.product.productmanagement.to.ProductDto;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    void addProduct(ProductDto productDto) throws IOException;
    ProductDto getProductDto(Product product);
    List<ProductDto> getProducts();
    void updateProduct(long id, ProductDto productDto) throws Exception;
    void deleteProduct(long id);
    int counting();
    List<ProductDto> products();
    ProductDto getProduct(Long id);
    void deleteUpdateProduct(long id) throws Exception;
    Product newUpdateProduct(Long id, ProductDto productDto) throws IOException;
}
