package com.glassyzone.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.glassyzone.entity.Product;
import com.glassyzone.repository.ProductDAO;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	ProductDAO productDAO;
	@Autowired
	HttpSession session;

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping
	public String index(Model model) {
		ResponseEntity<List<Product>> response = restTemplate.exchange("http://localhost:8080/product/getTop3",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
				});
		List<Product> products = response.getBody();
		model.addAttribute("products", products);
		return "index";
	}
}
