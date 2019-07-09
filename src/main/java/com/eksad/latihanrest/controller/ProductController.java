package com.eksad.latihanrest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.dao.BrandDao;
import com.eksad.latihanrest.dao.ProductDao;
import com.eksad.latihanrest.model.Brand;
import com.eksad.latihanrest.model.Product;

@RestController
@RequestMapping("product1")
public class ProductController {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	BrandDao brandDao;
	
	@RequestMapping("getAll")
	public List<Product> getAll(){
		List<Product> result = new ArrayList<>();
		productDao.findAll().forEach(result::add);
		return result;
	}
	
	//req untuk ngambil dari parameter
	@RequestMapping("getByBrandId/{brandId}")
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
	//save
	@RequestMapping(value= "save", method = RequestMethod.POST)
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
	@RequestMapping (value = "update/{id}", method = RequestMethod.PUT)
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
	@RequestMapping(value="delete/{id}", method =  RequestMethod.DELETE)
	public HashMap<String, Object> delete(@PathVariable Long id)
	{
		HashMap<String, Object> result = new HashMap<String, Object>();
		productDao.deleteById(id);
		result.put("message", "berhasil dihapus");
		return result;
	}
	
	//SEARCH BY NAME
	@RequestMapping("getBySearch/{search}")
	public List<Product> getBySearch(@PathVariable String search) {
		List<Product> result = new ArrayList<Product>();
		productDao.findBySearch(search).forEach(result::add);
		return  result;
	}
	
	
}
