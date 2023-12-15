package com.example.springboot.services;

import com.example.springboot.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface ProductService {

    public ArrayList<ProductModel> productFilter (String name, BigDecimal value);

    public Page<ProductModel> productFilterPage (String name, BigDecimal value, Pageable pageable);

}
