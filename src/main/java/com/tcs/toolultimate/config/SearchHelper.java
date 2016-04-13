package com.tcs.toolultimate.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.tcs.toolultimate.vo.Search;

public class SearchHelper {
	
	private int startIndex = 0;	
	private int lastIndex = 0;
	
	@Autowired
	SearchComparator comparator;

	public SearchHelper(Search searchAttribute) {
		setStartIndex(searchAttribute);
		setLastIndex(searchAttribute);
	}

	public Map<String, Object> getFinalResults(List<?> searchResults, Search searchAttribute) throws IllegalArgumentException, IllegalAccessException{
		if(Utility.isEmptyOrNull(searchResults)){			
			return emptyResult(searchAttribute);
		}
		
		if(!Utility.isEmptyOrNull(searchAttribute.getQuickSearchText())){
			searchResults = matchedResults(searchResults, searchAttribute);
			
		}
		
		if(Utility.isEmptyOrNull(searchResults)){			
			return emptyResult(searchAttribute);
		}		
		
		Collections.sort(searchResults, comparator);
		
		searchResults.subList(startIndex, lastIndex > searchResults.size() ? searchResults.size() : lastIndex);
		
		final Map<String, Object> searchDetails = new HashMap<String, Object>();
		searchDetails.put(Constants.STATUS, Constants.SUCCESS);
		searchDetails.put(Constants.DETAILS, searchResults);
		searchDetails.put(Constants.TOTAL_RECORDS, searchResults.size());
		searchDetails.put(Constants.MESSAGE, Constants.DATA_FOUND);
		searchDetails.put(Constants.SEARCH_ATTRIBUTE, searchAttribute);
		return searchDetails;		
	}
	
	private List<Object> matchedResults(List<?> searchResults, Search searchAttribute) throws IllegalArgumentException, IllegalAccessException {
		final List<Object> matchedRows = new ArrayList<Object>();
		for (Object object : searchResults) {
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {					
				String fieldValue = String.valueOf(field.get(object));
				if(fieldValue.contains(searchAttribute.getQuickSearchText())){
					matchedRows.add(object);
					break;
				}
				
			}
		}		
		return matchedRows;
	}

	private Map<String, Object> emptyResult(Search searchAttribute) {
		final Map<String, Object> searchDetails = new HashMap<String, Object>();
		searchDetails.put(Constants.STATUS, Constants.FAIL);
		searchDetails.put(Constants.DETAILS, "");
		searchDetails.put(Constants.TOTAL_RECORDS, 0);
		searchDetails.put(Constants.MESSAGE, Constants.NO_DATA_FOUND);
		searchDetails.put(Constants.SEARCH_ATTRIBUTE, searchAttribute);
		return searchDetails;
	}

	/**
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(Search searchAttribute) {
		this.startIndex = searchAttribute.getPageIndex() -1 * searchAttribute.getItemPerPage();
	}

	/**
	 * @return the lastIndex
	 */
	public int getLastIndex() {
		return lastIndex;
	}

	/**
	 * @param lastIndex the lastIndex to set
	 */
	public void setLastIndex(Search searchAttribute) {
		this.lastIndex = searchAttribute.getPageIndex() * searchAttribute.getItemPerPage();
	}
	
	@Bean
	SearchComparator getComparator(String sortField, String sortingOrder){
		return new SearchComparator(sortField, sortingOrder);
	}
	

}