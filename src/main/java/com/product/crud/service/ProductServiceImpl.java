package com.product.crud.service;

import com.product.crud.entity.Product;
import com.product.crud.entity.ProductDto;
import com.product.crud.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
   private ProductRepository productRepository;

  private static final Logger  loger = LoggerFactory.getLogger(ProductServiceImpl.class);



    @Override
    public List<ProductDto> getAllProduct() {

      loger.info("Feching All Product from database");
        return productRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }


    private ProductDto convertToDto(Product product){

        loger.debug("Converting Product entity to DTO: {}", product.getId());

        ProductDto dto = new ProductDto();

     dto.setP_name(product.getP_name());
     dto.setPrice(product.getPrice());
     dto.setDescription(product.getDescription());
     dto.setStock(product.getStock());
     dto.setId(product.getId());

     return dto;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {

       Product product = new Product();

       product.setP_name(productDto.getP_name());
       product.setPrice(productDto.getPrice());
       product.setDescription(productDto.getDescription());
       product.setStock(productDto.getStock());

       loger.info("saving product into database ;{}");

       Product savedProduct = productRepository.save(product);

       loger.info("product saved successfully with id : {}", savedProduct.getId());

       ProductDto dto = new ProductDto();

       dto.setId(savedProduct.getId());
       dto.setP_name(savedProduct.getP_name());
       dto.setPrice(savedProduct.getPrice());
       dto.setDescription(savedProduct.getDescription());
       dto.setStock(savedProduct.getStock());

       return dto;
    }


    @Override
     public ProductDto updateProduct(ProductDto productDto, Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id :"+id));

        product.setP_name(productDto.getP_name());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setStock(productDto.getStock());

        Product savedProduct = productRepository.save(product);

        ProductDto dto = new ProductDto();

        dto.setId(savedProduct.getId());
        dto.setP_name(savedProduct.getP_name());
        dto.setDescription(savedProduct.getDescription());
        dto.setStock(savedProduct.getStock());
        dto.setPrice(savedProduct.getPrice());
        return dto;
    }



    @Override
    public Page<ProductDto> getAllProducts(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);  // pagination object

        Page<Product> productPage = productRepository.findAll(pageable);

        // Page<Product> ko Page<ProductDto> me convert
        return productPage.map(this::convertToDtos);
    }

    private ProductDto convertToDtos(Product product) {
        ProductDto dto = new ProductDto();

        dto.setId(product.getId());
        dto.setP_name(product.getP_name());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setStock(product.getStock());

        return dto;
    }

    @Override
    @Transactional
    public List<ProductDto> upsertAll(List<ProductDto> productDtos) {

        List<ProductDto> responseList = new ArrayList<>();

        for (ProductDto dto : productDtos) {

            Product product;

            if (dto.getId() == null) {
                product = new Product();
            }
            else {
                product = productRepository.findById(dto.getId())
                        .orElse(new Product());
            }

            product.setP_name(dto.getP_name());
            product.setPrice(dto.getPrice());
            product.setDescription(dto.getDescription());
            product.setStock(dto.getStock());

            Product savedProduct = productRepository.save(product);

            responseList.add(mapToDto(savedProduct));
        }

        return responseList;
    }

    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setP_name(product.getP_name());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setStock(product.getStock());
        return dto;
    }

}
