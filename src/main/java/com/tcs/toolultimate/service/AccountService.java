package com.tcs.toolultimate.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.tcs.toolultimate.config.Constants;
import com.tcs.toolultimate.dao.common.BaseDAO;
import com.tcs.toolultimate.vo.Account;
import com.tcs.toolultimate.vo.SubProjects;
import com.tcs.toolultimate.vo.UmbrellaProject;

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
	public void saveAccount(Account account) {
		mongoTemplate.save(account);
	}
	
	/**
	 * This method is used to save or update account
	 * 
	 * @param account
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public Object saveOrUpdateDocument(String primaryColumn, Object entityObject) throws IllegalArgumentException, IllegalAccessException {
		Query query = new Query();
		Update update = new Update();
		Class<?> entityClass = entityObject.getClass();
		for (Field field : entityClass.getDeclaredFields()) {
			if(!field.isAccessible()){
				field.setAccessible(Boolean.TRUE);
			}			
			update.set(field.getName(), field.get(entityObject));
		}
		mongoTemplate.upsert(query, update, entityClass);
		Object saveOrUpdatedData = mongoTemplate.findOne(query, entityClass);
		return saveOrUpdatedData;
	}

	/**
	 * This method is used to fetch all accounts if passed field has that value
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return List of accounts
	 */
	public <T>List<T> fetchDocumentsByField(String fieldName, String fieldValue, Class<T> entityClass) {
		Query query = new Query();
		query.addCriteria(Criteria.where(fieldName).is(fieldValue));
		List<T> accounts = mongoTemplate.find(query, entityClass);
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
		List<Account> accounts = baseDAO.fetchDistinctRecords(Constants.ACCOUNT_STORE_NAME,null,null,objectType);
		
		return accounts;
	}
	
	/**
	 * 
	 * @return List of accounts
	 */
	public List<SubProjects> fetchAllSubProjects() {
		Class<SubProjects> objectType = SubProjects.class;
		List<SubProjects> subProjects = baseDAO.fetchDistinctRecords("ACCOUNT",null,Constants.COLUMN_NAME_SUB_PROJ_ID,objectType);
		
		return subProjects;
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
	
	public List<UmbrellaProject> getAllUmbrellaProjectsForAccnt(String accountId){
		
		Class<UmbrellaProject> objectType = UmbrellaProject.class;
		Map<String,String> matchCriteria = new HashMap<String,String>();
		matchCriteria.put(Constants.COLUMN_NAME_ACCOUNT_ID, accountId);
		
		List<UmbrellaProject> projects = baseDAO.fetchDistinctRecords(Constants.ACCOUNT_STORE_NAME, matchCriteria,Constants.COLUMN_NAME_UMBRELLA_PROJ_ID, objectType);
		
		return projects;
	}

}
