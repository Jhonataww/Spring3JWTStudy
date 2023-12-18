package com.example.springboot.services;

import com.example.springboot.dtos.ProductForm;
import com.example.springboot.models.ProductModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public interface ProductService {

    public ArrayList<ProductModel> productFilter (String name, BigDecimal value);

    public ArrayList<ProductModel> findAllFilterGeneric(ProductForm filter);
}
