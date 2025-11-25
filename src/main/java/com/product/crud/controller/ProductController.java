package com.product.crud.controller;

import com.product.crud.entity.ProductDto;
import com.product.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;


import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

  @Autowired
  private ProductService productService;

private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

   @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        logger.info("Getting ALl Product , calling getAllProduct API ");
        return ResponseEntity.ok(productService.getAllProduct());
    }




    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5")int size){

        //logger.info("Fetching product - page : {}, size: {}", page, size);

        Page<ProductDto> products = productService.getAllProducts(page,size);
       return ResponseEntity.ok(products) ;
    }


    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){

     logger.info("Creating new Product with product name: {} "+ productDto.getP_name());
     logger.info("Product creation request send to service layer");
     return ResponseEntity.ok(productService.createProduct(productDto));
     }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateproduct(@RequestBody ProductDto productDto, @PathVariable Long id){

        logger.info("updating product : {}", productDto.getP_name());
       return ResponseEntity.ok(productService.updateProduct(productDto, id));
    }


  @PostMapping("/upsert-all")
  public ResponseEntity<List<ProductDto>> upsertAll(@RequestBody List<ProductDto> productDtos) {
    List<ProductDto> result = productService.upsertAll(productDtos);
    return ResponseEntity.ok(result);
}

@PostMapping("/update-stocks")
    public ResponseEntity<String> updateStocks(@RequestBody List<ProductDto> productDto){

       productService.updateStocks(productDto);
       return ResponseEntity.ok("Update user successfully :");
}


}