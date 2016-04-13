package com.tcs.toolultimate.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcs.toolultimate.config.Constants;
import com.tcs.toolultimate.config.SearchHelper;
import com.tcs.toolultimate.service.AccountService;
import com.tcs.toolultimate.vo.Account;
import com.tcs.toolultimate.vo.Search;
import com.tcs.toolultimate.vo.SubProjects;

@Controller
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	SearchHelper helper;
	
	@RequestMapping(value="/viewAccount")
	public @ResponseBody Map<String, Object> viewAccounts(@RequestBody Search searchAttribute){
		Map<String, Object> finalResults = null;
		List<Account> fetchAllAcounts = accountService.fetchAllAcounts();
		try {
			finalResults = helper.getFinalResults(fetchAllAcounts, searchAttribute);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error("Error occured in method viewAccounts: " + e);
		}
		return finalResults;
	}
	
	@RequestMapping(value="/saveAccount")
	public @ResponseBody Map<String, Object> saveAccount(@RequestBody Account account){
		Map<String, Object> finalResults = null;
		accountService.saveOrUpdateAccount(account);
		List<Account> fetchAllAcounts = accountService.fetchAllAcounts();
		try {
			finalResults = helper.getFinalResults(fetchAllAcounts, new Search());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error("Error occured in method viewAccounts: " + e);
		}
		return finalResults;
	}
	
	@RequestMapping(value="/editAccount")
	public @ResponseBody Map<String, Object> editAccount(Account account){
		List<Account> fetchAllAcounts = accountService.fetchAcountsByField(Constants.COLUMN_NAME_ACCOUNT_ID, account.getAccountId());
		Map<String, Object> accountDetails = new HashMap<String, Object>();
		accountDetails.put(Constants.STATUS, fetchAllAcounts.size() > 0 ? Constants.SUCCESS : Constants.FAIL);
		accountDetails.put(Constants.DETAILS, fetchAllAcounts.isEmpty() ? "" : fetchAllAcounts.get(0));
		accountDetails.put(Constants.TOTAL_RECORDS, fetchAllAcounts.size());
		return accountDetails;
	}
	
	@RequestMapping(value="/viewSubProject")
	public @ResponseBody Map<String, Object> viewSubProject(){
		List<SubProjects> fetchAllAcounts = accountService.fetchAllSubProjects();
		Map<String, Object> accountDetails = new HashMap<String, Object>();
		accountDetails.put(Constants.STATUS, fetchAllAcounts.size() > 0 ? Constants.SUCCESS : Constants.FAIL);
		accountDetails.put(Constants.DETAILS, fetchAllAcounts);
		accountDetails.put(Constants.TOTAL_RECORDS, fetchAllAcounts.size());
		return accountDetails;
	}
	
}
