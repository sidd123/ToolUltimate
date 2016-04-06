package com.tcs.toolultimate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcs.toolultimate.config.Constants;
import com.tcs.toolultimate.service.AccountService;
import com.tcs.toolultimate.vo.Account;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@RequestMapping(value="/viewAccount")
	public @ResponseBody Map<String, Object> viewAccounts(){
		List<Account> fetchAllAcounts = accountService.fetchAllAcounts();
		Map<String, Object> accountDetails = new HashMap<String, Object>();
		accountDetails.put(Constants.STATUS, accountDetails.size() > 0 ? Constants.SUCCESS : Constants.FAIL);
		accountDetails.put(Constants.DETAILS, fetchAllAcounts);
		return accountDetails;
	}
}
