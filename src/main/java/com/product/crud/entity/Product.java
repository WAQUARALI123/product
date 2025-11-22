package com.product.crud.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        private String p_name;
        private Double price;
        private String description;
        private Integer stock;


        public void setId(Long id) {
            this.id = id;
        }

        public void setP_name(String p_name) {
            this.p_name = p_name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }


        public Long getId() {
            return id;
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


    public Product() {
    }


    public Product(Long id, String p_name, Double price, String description, Integer stock) {
        this.id = id;
        this.p_name = p_name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

}

