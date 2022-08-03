package com.makeawish.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

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

	/**
	 * Returns @Callable<@String> so that Asynchronous threads are used to handle
	 * the request("GET" here)
	 *
	 * @return @Callable for Async processing to work.
	 */
	@GetMapping("/search")
	public Callable<String> search(@RequestParam("search") String searchString, Model model,
			HttpServletRequest request) {
		System.out.println("Inside search controller");
		System.out.println(Thread.currentThread().getName());
		System.out.println(LocalDateTime.now() + " Searched for: " + searchString);

		return () -> {
			Thread.sleep(3000);
			System.out.println(request.isAsyncSupported());
			System.out.println("From spring mvc task executor " + Thread.currentThread().getName());
			List<Product> searchedProducts = productRepository.searchByName(searchString);
			System.out.println("Found " + searchedProducts.size() + " products :" + searchedProducts);
			model.addAttribute("products", searchedProducts);
			return "search";
		};
	}
}
