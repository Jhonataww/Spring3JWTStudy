package com.example.springboot.services;

import com.example.springboot.dtos.ProductForm;
import com.example.springboot.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public interface ProductService {

    public ArrayList<ProductModel> productFilter (String name, BigDecimal value);

    public ArrayList<ProductModel> findAllFilterGeneric(ProductForm filter);

    public Page<ProductModel> productFilterPage(String name, BigDecimal value, Pageable pageable);

    public ArrayList<ProductModel> findAll();

    public Page<ProductModel> findAll(Pageable pageable);

    public ProductModel save(ProductModel productModel);

    public void delete(ProductModel productModel);

    public ProductModel findById(UUID id);
}
