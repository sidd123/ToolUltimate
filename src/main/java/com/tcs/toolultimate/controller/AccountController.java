package com.tcs.toolultimate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcs.toolultimate.config.Constants;
import com.tcs.toolultimate.service.AccountService;
import com.tcs.toolultimate.vo.Account;
import com.tcs.toolultimate.vo.Employee;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;
	
	
	@RequestMapping(value="/viewAccount")
	public @ResponseBody Map<String, Object> viewAccounts(){
		List<Account> fetchAllAcounts = accountService.fetchAllAcounts();
		Map<String, Object> accountDetails = new HashMap<String, Object>();
		accountDetails.put(Constants.STATUS, fetchAllAcounts.size() > 0 ? Constants.SUCCESS : Constants.FAIL);
		accountDetails.put(Constants.DETAILS, fetchAllAcounts);
		accountDetails.put(Constants.TOTAL_RECORDS, fetchAllAcounts.size());
		return accountDetails;
	}
	
	@RequestMapping(value="/saveAccount")
	public @ResponseBody Map<String, Object> saveAccount(@RequestBody Account account){
		accountService.saveOrUpdateAccount(account);
		List<Account> fetchAllAcounts = accountService.fetchAllAcounts();
		Map<String, Object> accountDetails = new HashMap<String, Object>();
		accountDetails.put(Constants.STATUS, fetchAllAcounts.size() > 0 ? Constants.SUCCESS : Constants.FAIL);
		accountDetails.put(Constants.DETAILS, fetchAllAcounts);
		accountDetails.put(Constants.TOTAL_RECORDS, fetchAllAcounts.size());
		return accountDetails;
	}
	
	@RequestMapping(value="/editAccount")
	public @ResponseBody Map<String, Object> editAccount(Account account){
		List<Account> fetchAllAcounts = accountService.fetchAcountsByField(Constants.COLUMN_NAME_ACCOUNT_ID, account.getAccountId());
		Map<String, Object> accountDetails = new HashMap<String, Object>();
		accountDetails.put(Constants.STATUS, fetchAllAcounts.size() > 0 ? Constants.SUCCESS : Constants.FAIL);
		accountDetails.put(Constants.DETAILS, fetchAllAcounts.get(0));
		accountDetails.put(Constants.TOTAL_RECORDS, fetchAllAcounts.size());
		return accountDetails;
	}
}
