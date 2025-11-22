package com.product.crud.service;


import com.product.crud.entity.ProductDto;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {

 public List<ProductDto> getAllProduct();

 public ProductDto createProduct(ProductDto productDto);

 public ProductDto updateProduct(ProductDto productDto, Long id);

  Page<ProductDto> getAllProducts(int page, int size);

 List<ProductDto> upsertAll(List<ProductDto> productDtos);



}
