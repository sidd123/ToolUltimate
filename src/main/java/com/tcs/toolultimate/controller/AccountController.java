package com.tcs.toolultimate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcs.toolultimate.service.AccountService;
import com.tcs.toolultimate.vo.Account;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@RequestMapping(value="/viewAccount")
	public @ResponseBody List<Account> viewAccounts(){
		List<Account> fetchAllAcounts = accountService.fetchAllAcounts();
		return fetchAllAcounts;
	}
}
