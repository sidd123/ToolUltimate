package com.tcs.toolultimate.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.toolultimate.config.Constants;
import com.tcs.toolultimate.dao.common.BaseDAO;
import com.tcs.toolultimate.vo.Account;

@Service("AccountService")
public class AccountService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	BaseDAO baseDAO;

	/**
	 * This method is used to save account
	 * 
	 * @param account
	 */
	public void saveOrUpdateAccount(Account account) {
		mongoTemplate.save(account);
	}

	/**
	 * This method is used to fetch all accounts if passed field has that value
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return List of accounts
	 */
	public List<Account> fetchAcountsByField(String fieldName, String fieldValue) {
		Query query = new Query();
		query.addCriteria(Criteria.where(fieldName).is(fieldValue));
		List<Account> accounts = mongoTemplate.find(query, Account.class);
		return accounts;
	}

	/**
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return List of accounts
	 */
	public List<Account> fetchMatchedAcounts(String fieldName, String fieldValue) {
		Query query = new Query();
		query.addCriteria(Criteria.where(fieldName).regex(fieldValue));
		List<Account> accounts = mongoTemplate.find(query, Account.class);
		return accounts;
	}

	/**
	 * 
	 * @return List of accounts
	 */
	public List<Account> fetchAllAcounts() {
		Class<Account> objectType = Account.class;
		List<Account> accounts = baseDAO.fetchDistinctRecords("ACCOUNT",objectType);
		
		return accounts;
	}
	

	
	/**
	 * 
	 * @param sortingOrder
	 * @param sortingField
	 * @return List of accounts
	 */
	public List<Account> acountsSorting(String sortingOrder, String sortingField) {
		Query query = new Query();
		Order order = new Order(sortingOrder == "ASC" ? Direction.ASC
				: Direction.DESC, sortingField);
		Sort sort = new Sort(order.ignoreCase());
		query.with(sort);
		List<Account> accounts = mongoTemplate.findAll(Account.class);
		return accounts;
	}

	/**
	 * 
	 * @param account
	 * @return nothing
	 */
	public void deleteAcount(Account account) {
		Query query = new Query();
		query.addCriteria(Criteria
				.where(Constants.COLUMN_NAME_ACCOUNT_NAME)
				.is(account.getAccountName())
				.andOperator(
						Criteria.where(Constants.COLUMN_NAME_ACCOUNT_ID).is(
								account.getAccountId())));
		mongoTemplate.remove(query);
	}

}
