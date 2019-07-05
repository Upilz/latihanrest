package com.eksad.latihanrest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.eksad.latihanrest.model.Transaction;

public interface TransactionDao extends CrudRepository<Transaction,Long> {
	
	//MAU MENJUMLAHKAN QUANTITY DI JAVA LIKE SQL
	@Query("select sum(quantity) from TransasctionDetail "
			+ "where transaction.id =?1")
	public Integer findTotalItemByTransaction(Long id);
	
	@Query("select td.transaction.id as transactionId, "
			+ "sum(td.quantity * pr.price) as total "
			+ "from TransasctionDetail td "
			+ "join td.product pr "
			+ "group by td.transaction.id")
	public List<Object[]>findTotalNominalPerTransaction();
}
