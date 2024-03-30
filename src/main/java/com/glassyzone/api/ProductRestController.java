package com.glassyzone.api;

import java.util.List;

import com.glassyzone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.glassyzone.entity.Product;
import com.glassyzone.repository.ProductDAO;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductRestController {

	@Autowired
	ProductDAO productDAO;

	@Autowired
	ProductService productService;

	@GetMapping("/sortAsc")
	public ResponseEntity<List<Product>> sortAsc() {
		return ResponseEntity.ok(productDAO.findAllByOrderByPriceAsc());
	}

	@GetMapping("/sortDesc")
	public ResponseEntity<List<Product>> sortDesc() {
		return ResponseEntity.ok(productDAO.findAllByOrderByPriceDesc());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
		Product product = productDAO.findById(id).orElse(null);
		return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProductByName(@RequestParam String name) {
		List<Product> matchingProducts = productDAO.findByNameContainingIgnoreCase(name);
		if (!matchingProducts.isEmpty()) {
			return ResponseEntity.ok(matchingProducts);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		return ResponseEntity.ok(productDAO.save(product));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		return ResponseEntity.ok(productDAO.save(product));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
		if (productDAO.existsById(id)) {
			productDAO.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/getTop3")
	public ResponseEntity<List<Product>> findByTop3 (){
		return ResponseEntity.ok(productDAO.findByTop3());
	}

	@GetMapping
	public ResponseEntity<Page<Product>> getProducts(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
		Page<Product> productPage = productService.findPaginated(page, size);
		return ResponseEntity.ok(productPage);
	}

}