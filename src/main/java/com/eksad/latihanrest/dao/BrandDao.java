package com.eksad.latihanrest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.eksad.latihanrest.model.Brand;

public interface BrandDao extends CrudRepository<Brand, Long>
{
	//pake findBy
	
	//search by one by name
	public Brand findOneByName (String name);
	
	//serachby nama produk
	public List<Brand> findByName (String name);
	//serachby product type 
	public List<Brand> findByProductType (String type);

	//selain findBy Bisa pake findSearch tambahin anotation query
	
	@Query ("select b from Brand b where name = :search")
	public List<Brand> findBySearch(@Param("search") String search);
}
