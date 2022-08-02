package com.makeawish.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.makeawish.model.Product;
import com.makeawish.repository.ProductRepository;

@Controller
public class SearchController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/search")
	public String search(@RequestParam("search") String searchString, Model model) {
		System.out.println("Inside search controller");
		System.out.println(LocalDateTime.now() + " Searched for: " + searchString);
		List<Product> searchedProducts = productRepository.searchByName(searchString);
		System.out.println("Found "+ searchedProducts.size() + " products :" + searchedProducts );
		model.addAttribute("products", searchedProducts);
		return "search";
	}
}
