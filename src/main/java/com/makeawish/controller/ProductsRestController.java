package com.makeawish.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makeawish.model.Product;
import com.makeawish.repository.ProductRepository;

@Controller
public class ProductsRestController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/makeawish/rest/products")
	@ResponseBody // Instead of @Controller in class and @ResponseBody here we can use
					// @RestController which is combination of both and then by default all method
					// will return a JSON repsonse.
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
}
