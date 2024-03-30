package com.glassyzone.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.glassyzone.entity.Product;

@Repository
@EnableJpaRepositories
public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query(nativeQuery = true, value = "SELECT * FROM product WHERE name = ?1")
	Product findByProductName(String productName);

	@Query(nativeQuery = true, value = "SELECT * FROM product ORDER BY price DESC LIMIT 3")
	List<Product> findByTop3();

	@Query(nativeQuery = true, value = "SELECT * FROM product")
	List<Product> findAll();

	List<Product> findByNameContainingIgnoreCase(String name);
	
	@Query(nativeQuery = true, value = "SELECT * FROM product ORDER BY price ASC")
	List<Product> findAllByOrderByPriceAsc();
	
	@Query(nativeQuery = true, value = "SELECT * FROM product ORDER BY price DESC")
	List<Product> findAllByOrderByPriceDesc();

	Page<Product> findAll(Pageable pageable);
}
