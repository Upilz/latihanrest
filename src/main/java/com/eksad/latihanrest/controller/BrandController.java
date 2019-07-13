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
import com.eksad.latihanrest.model.Brand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/brand") //sebelumswagger "brand1"
@Api(tags="Brand")
public class BrandController {

	@Autowired
	 BrandDao brandDao;
	
	//GET ALL
	@ApiOperation(
			value = "API to Retrieve All Supermarket Brand's Data",
			notes = "Return Supermarket Brand's Data with JSON Format"
			)
	@GetMapping("getAll")
		public List<Brand> getAll(){
		List<Brand> result = new ArrayList<>();
		brandDao.findAll().forEach(result::add);
		return result;
	}
	
	//GET ONE BY ID
	//req untuk ngambil dari parameter
	@ApiOperation(
			value = "API to Retrieve Supermarket Brand's Data Based On Selected ID",
			notes = "Return Supermarket Brand's Selected Data with JSON Format"
			)
	@GetMapping("getOne/{id}")
	public Brand getOne (@PathVariable Long id) {
	return brandDao.findById(id).orElse(null);
	
	//cara manggil sesuai id
	//http://localhost:8080/brand1/getOne/1
}

	//SAVE /insert 
	@ApiOperation(
			value = "API to Add / Insert New Supermarket Brand's Data",
			notes = "Add / Insert New Supermarket Brand's Data with JSON Format",
			tags = "Supermarket's Data Manipulation API"
			)
	@PostMapping(value= "Save")
	public Brand save(@RequestBody Brand brand) { //reqbody untuk negbacadata body yang di save di post man 
	try {
		return brandDao.save(brand);
		} 
	catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}

	//UPDATE
	@ApiOperation(
			value = "API to Update Supermarket Brand's Data Based On ID",
			notes = "Return Update Supermarket Brand's Data with JSON Format",
			tags = "Supermarket's Data Manipulation API"
			)
	@PutMapping(value="update/{id}")
	public Brand update (@RequestBody Brand brand, @PathVariable Long id)
	{
	Brand brandSelected = brandDao.findById(id).orElse(null);
	if (brandSelected != null)
	{
		brandSelected.setName(brand.getName());
		brandSelected.setProductType(brand.getProductType());
		
		return brandDao.save(brandSelected);
		
	}
	else { return null;}
	}

	//DELETE-hashmap
	@ApiOperation(
			value = "API to Delete Supermarket Brand's Data",
			notes = "Delete Supermarket Brand's Data with JSON Format",
			tags = "Supermarket's Data Manipulation API"
			)
	@DeleteMapping(value="delete/{id}")
	public HashMap<String, Object> delete(@PathVariable Long id)
	{
	HashMap<String, Object> result = new HashMap<String, Object>();
	brandDao.deleteById(id);
	result.put("message", "berhasil dihapus");
	return result;
	}
}
