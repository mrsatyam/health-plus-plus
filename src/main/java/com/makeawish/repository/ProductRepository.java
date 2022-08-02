package com.makeawish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.makeawish.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	@Query("select product from Product product where product.name like %:name%")
	public List<Product> searchByName(@Param("name") String name);
}
