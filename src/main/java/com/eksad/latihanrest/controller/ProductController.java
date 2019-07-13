package com.eksad.latihanrest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.dao.BrandDao;
import com.eksad.latihanrest.dao.ProductDao;
import com.eksad.latihanrest.model.Brand;
import com.eksad.latihanrest.model.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/product")//sebelum swagger "product1"
@Api(tags="Product")
public class ProductController {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	BrandDao brandDao;
	
	//GET ALL
	@ApiOperation(
			value = "API to Retrieve All Supermarket Product's Data",
			notes = "Return All Supermarket Product's Data with JSON Format"
			)
	@GetMapping("getAll")
	public List<Product> getAll(){
		List<Product> result = new ArrayList<>();
		productDao.findAll().forEach(result::add);
		return result;
	}
	
	//GET BY BRAND ID
	//req untuk ngambil dari parameter
	@ApiOperation(
			value = "API to Retrieve Supermarket Product's Data Based On Selected Brand's ID",
			notes = "Return Selected Supermarket Product's Data Based On Brand's ID with JSON Format"
			)
	@GetMapping("getByBrandId/{brandId}")
	public List<Product> getByProductBrandId(@PathVariable Long brandId) 
	{
		List<Product> result = new ArrayList<Product>();
		productDao.findByBrandId(brandId).forEach(result::add);
		return  result;
		}
		
	//buat bisa ngapus constraint key
	public List<Product> getByBrandId(@PathVariable Long brandId)
	{
		List<Product> result = new ArrayList<Product>();
		productDao.findByBrandId(brandId).forEach(result::add);
		return result;
	
		
	}
	
	//SAVE / INSERT / ADD
	@ApiOperation(
			value = "API to Add / Insert New Supermarket Product's Data",
			notes = "Add / Insert New Supermarket Product's Data with JSON Format",
			tags = "Supermarket's Data Manipulation API"
			)
	@PostMapping(value= "save")
	public Product save (@RequestBody Product product)
	
	{
		//buat bisa edit brand id di save langsung
		Brand brand = brandDao.findById(product.getBrandId()).orElse(null);
		//tambahan
		if (brand!= null)
		{
			product.setBrand(brand);
			return productDao.save(product);
		}
		
		return null;
	}
	
	//UPDATE
	@ApiOperation(
			value = "API to Update Supermarket Product's Data",
			notes = "Update New Supermarket Product's Data with JSON Format",
			tags = "Supermarket's Data Manipulation API"
			)
	@PutMapping (value = "update/{id}")
	public Product update (@RequestBody Product product, @PathVariable Long id)
	{
		Product productSelected = productDao.findById(id).orElse(null);
		if (productSelected != null)
		{
			productSelected.setBrandId(product.getBrandId());
			productSelected.setName(product.getName());
			productSelected.setPrice(product.getPrice());
			
			return productDao.save(productSelected);
			
		}
		else { return null;}
	}
	
	//DELETE
	@ApiOperation(
			value = "Delete Supermarket Product's Data",
			notes = "Delete Supermarket Product's Data with JSON Format",
			tags = "Supermarket's Data Manipulation API"
			)
	@DeleteMapping(value="delete/{id}")
	public HashMap<String, Object> delete(@PathVariable Long id)
	{
		HashMap<String, Object> result = new HashMap<String, Object>();
		productDao.deleteById(id);
		result.put("message", "berhasil dihapus");
		return result;
	}
	
	//SEARCH BY NAME
	@ApiOperation(
			value = "API to Retrieve Supermarket Product's Data Based on Name",
			notes = "Return Supermarket Product's Data Based on Name with JSON Format"
			)
	@GetMapping("getBySearch/{search}")
	public List<Product> getBySearch(@PathVariable String search) {
		List<Product> result = new ArrayList<Product>();
		productDao.findBySearch(search).forEach(result::add);
		return  result;
	}
	
	
}
