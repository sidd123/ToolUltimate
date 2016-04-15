package com.tcs.toolultimate.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcs.toolultimate.config.Constants;
import com.tcs.toolultimate.config.SearchHelper;
import com.tcs.toolultimate.config.Utility;
import com.tcs.toolultimate.service.AccountService;
import com.tcs.toolultimate.vo.Account;
import com.tcs.toolultimate.vo.Employee;
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
	public @ResponseBody Map<String, Object> viewAccounts(HttpSession session, @RequestBody Search searchAttribute){
		Map<String, Object> finalResults = null;
		Employee loggedInUser = (Employee)session.getAttribute("loggedInUser");
		List<Account> fetchCreatedAcounts = accountService.fetchDocumentsByField(Constants.COLUMN_NAME_ACCOUNT_CREATED_BY, loggedInUser.getEmployeeId(), Account.class);
		List<Account> fetchAssociatedAcounts = accountService.fetchDocumentsByField(Constants.COLUMN_NAME_ACCOUNT_ID, loggedInUser.getEmployeeId(), Account.class);
		fetchCreatedAcounts.addAll(fetchAssociatedAcounts);
		try {
			helper.setStartIndex(searchAttribute);
			helper.setLastIndex(searchAttribute);
			finalResults = helper.getFinalResults(fetchCreatedAcounts, searchAttribute);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error("Error occured in method viewAccounts: " + e);
		}
		return finalResults;
	}
	
	@RequestMapping(value="/saveAccount")
	public @ResponseBody Map<String, Object> saveAccount(HttpSession session, @RequestBody Account account){
		Map<String, Object> finalResults = null;
		String currentDate = "";
		Employee loggedInUser = (Employee)session.getAttribute("loggedInUser");
		try {
			currentDate = Utility.formatDate(new Date(), Constants.DATEFORMAT_WITH_DATE_AND_TIME);
		} catch (ParseException e) {
			logger.error("Parse Error Occured in method saveAccount: " + e);
		}
		account.setAccountCreatedOn(currentDate);
		account.setAccountCreatedBy(loggedInUser.getEmployeeId());
		try {
			Account saveOrUpdateDocument = (Account) accountService.saveOrUpdateDocument(Constants.COLUMN_NAME_ACCOUNT_ID, account);
			logger.info("Saved or updated records: " + saveOrUpdateDocument);
			List<Account> fetchCreatedAcounts = accountService.fetchDocumentsByField(Constants.COLUMN_NAME_ACCOUNT_CREATED_BY, loggedInUser.getEmployeeId(), Account.class);
			finalResults = helper.getFinalResults(fetchCreatedAcounts, new Search());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error("Error occured in method viewAccounts: " + e);
		}
		return finalResults;
	}
	
	@RequestMapping(value="/editAccount")
	public @ResponseBody Map<String, Object> editAccount(Account account){
		List<Account> fetchAllAcounts = accountService.fetchDocumentsByField(Constants.COLUMN_NAME_ACCOUNT_ID, account.getAccountId(), Account.class);
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
	
	@RequestMapping(value="/viewProject")
	public @ResponseBody Map<String, Object> viewProjects(HttpSession session, @RequestBody Search searchAttribute){
		Map<String, Object> finalResults = null;
		Employee loggedInUser = (Employee)session.getAttribute("loggedInUser");
		List<Account> fetchCreatedAcounts = accountService.fetchDocumentsByField(Constants.COLUMN_NAME_ACCOUNT_CREATED_BY, loggedInUser.getEmployeeId(), Account.class);
		List<Account> fetchAssociatedAcounts = accountService.fetchDocumentsByField(Constants.COLUMN_NAME_ACCOUNT_ID, loggedInUser.getEmployeeId(), Account.class);
		fetchCreatedAcounts.addAll(fetchAssociatedAcounts);
		try {
			helper.setStartIndex(searchAttribute);
			helper.setLastIndex(searchAttribute);
			finalResults = helper.getFinalResults(fetchCreatedAcounts, searchAttribute);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error("Error occured in method viewAccounts: " + e);
		}
		return finalResults;
	}
	
	@RequestMapping(value="/viewUmbrellaProject")
	public @ResponseBody Map<String, Object> viewUmbrellaProjects(HttpSession session, @RequestBody Search searchAttribute){
		Map<String, Object> finalResults = null;
		Employee loggedInUser = (Employee)session.getAttribute("loggedInUser");
		List<Account> fetchCreatedAcounts = accountService.fetchDocumentsByField(Constants.COLUMN_NAME_ACCOUNT_CREATED_BY, loggedInUser.getEmployeeId(), Account.class);
		List<Account> fetchAssociatedAcounts = accountService.fetchDocumentsByField(Constants.COLUMN_NAME_ACCOUNT_ID, loggedInUser.getEmployeeId(), Account.class);
		fetchCreatedAcounts.addAll(fetchAssociatedAcounts);
		try {
			helper.setStartIndex(searchAttribute);
			helper.setLastIndex(searchAttribute);
			finalResults = helper.getFinalResults(fetchCreatedAcounts, searchAttribute);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error("Error occured in method viewAccounts: " + e);
		}
		return finalResults;
	}
	
}
