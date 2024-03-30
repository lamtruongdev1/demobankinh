package com.glassyzone.service;

import java.util.List;

import com.glassyzone.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import com.glassyzone.entity.Product;

@Controller
public class ProductService {
    @Autowired
    ProductDAO productDAO;

    public Page<Product> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return productDAO.findAll(pageable);
    }
	
}
