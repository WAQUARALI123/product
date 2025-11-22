package com.product.crud.entity;

public class ProductDto {


    private String p_name;
    private Double price;
    private String description;
    private Integer stock;
    Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


    public String getP_name() {
        return p_name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStock() {
        return stock;
    }

    public ProductDto() {
    }

    public ProductDto(String p_name, String description, Double price, Integer stock, Long id) {
        this.p_name = p_name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.id = id;

    }

}
