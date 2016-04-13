package com.tcs.toolultimate.vo;

public class Search {
	private String quickSearchText; 
	private int pageIndex = 1;
	private String sortByCol;
	private String sortByDir = "asc";
	private int itemPerPage = 25;
	
	/**
	 * @return the quickSearchText
	 */
	public String getQuickSearchText() {
		return quickSearchText;
	}
	/**
	 * @param quickSearchText the quickSearchText to set
	 */
	public void setQuickSearchText(String quickSearchText) {
		this.quickSearchText = quickSearchText;
	}
	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	/**
	 * @return the sortByCol
	 */
	public String getSortByCol() {
		return sortByCol;
	}
	/**
	 * @param sortByCol the sortByCol to set
	 */
	public void setSortByCol(String sortByCol) {
		this.sortByCol = sortByCol;
	}
	/**
	 * @return the sortByDir
	 */
	public String getSortByDir() {
		return sortByDir;
	}
	/**
	 * @param sortByDir the sortByDir to set
	 */
	public void setSortByDir(String sortByDir) {
		this.sortByDir = sortByDir;
	}
	/**
	 * @return the itemPerPage
	 */
	public int getItemPerPage() {
		return itemPerPage;
	}
	/**
	 * @param itemPerPage the itemPerPage to set
	 */
	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}
	
	
}
