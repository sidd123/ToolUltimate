package com.tcs.toolultimate.config;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SearchComparator implements Comparator<Object> {

	private static final Logger logger = LoggerFactory
			.getLogger(SearchComparator.class);

	private String sortField;
	private String sortingOrder;

	/**
	 * @return the sortField
	 */
	public String getSortField() {
		return sortField;
	}

	/**
	 * @param sortField
	 *            the sortField to set
	 */
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	/**
	 * @return the sortingOrder
	 */
	public String getSortingOrder() {
		return sortingOrder;
	}

	/**
	 * @param sortingOrder
	 *            the sortingOrder to set
	 */
	public void setSortingOrder(String sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	@Override
	public int compare(Object o1, Object o2) {
		int compareTo = 0;
		try {
			Field declaredField1 = o1.getClass().getDeclaredField(sortField);
			if(!declaredField1.isAccessible()){
				declaredField1.setAccessible(true);
			}
			
			Field declaredField2 = o2.getClass().getDeclaredField(sortField);
			if(!declaredField2.isAccessible()){
				declaredField2.setAccessible(true);
			}
			String objectValue1 = (String) declaredField1.get(o1);
			String objectValue2 = (String) declaredField2.get(o2);
			if(!Utility.isEmptyOrNull(objectValue1) && !Utility.isEmptyOrNull(objectValue2)){
				if (sortingOrder
						.equalsIgnoreCase(Constants.SORTING_ORDER_ASCENDING)) {
					compareTo = objectValue1.compareTo(objectValue2);
				} else {
					compareTo = objectValue2.compareTo(objectValue1);
				}
			}
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			logger.error("Error occured in compare method: " + e);
		}

		return compareTo;
	}
}
