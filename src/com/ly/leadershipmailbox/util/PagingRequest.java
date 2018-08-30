package com.ly.leadershipmailbox.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PagingRequest<T> implements Serializable {
	private static final Logger LOG = LoggerFactory.getLogger(PagingRequest.class);

	private int currentPageNum = 1;
	private int totalPageNum = 1;
	private int pageSize = 10;
	private int totalRecordNum = 1;
	private int queryRecordStart = 0;
	private int queryRecordEnd = 1;
	private List<T> data = Collections.emptyList();

	private PagingRequest() {
	}

	public PagingRequest(int currentPageNum, int totalRecordNum) {
		this.totalRecordNum = totalRecordNum;
		if (currentPageNum < 1) {
			this.currentPageNum = 1;
		} else {
			this.currentPageNum = currentPageNum;
		}
		if (this.pageSize < 1) {
			this.pageSize = 1;
		}
		this.totalPageNum = (totalRecordNum + this.pageSize - 1) / this.pageSize;
		if (this.totalPageNum == 0) {
			this.totalPageNum = 1;
		}
		if (this.currentPageNum > this.totalPageNum) {
			this.currentPageNum = this.totalPageNum;
		}
		this.queryRecordStart = this.pageSize * (this.currentPageNum - 1);
		this.queryRecordEnd = this.pageSize * this.currentPageNum;
	}

	public PagingRequest(int currentPageNum, int pageSize, int totalRecordNum) {
		if (pageSize < 1) {
			pageSize = 1;
		}
		this.pageSize = pageSize;
		this.totalRecordNum = totalRecordNum;
		if (currentPageNum < 1) {
			this.currentPageNum = 1;
		} else {
			this.currentPageNum = currentPageNum;
		}
		this.totalPageNum = (totalRecordNum + pageSize - 1) / pageSize;
		if (this.totalPageNum == 0) {
			this.totalPageNum = 1;
		}
		if (this.currentPageNum > this.totalPageNum) {
			this.currentPageNum = this.totalPageNum;
		}
		this.queryRecordStart = this.pageSize * (this.currentPageNum - 1);
		this.queryRecordEnd = this.pageSize * this.currentPageNum;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalRecordNum() {
		return totalRecordNum;
	}

	public int getQueryRecordStart() {
		return queryRecordStart;
	}

	public int getQueryRecordEnd() {
		return queryRecordEnd;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Map<String, Object> toMap() {
		return toMap(false);
	}

	public Map<String, Object> toMap(boolean onlyData) {
		Map<String, Object> json = new HashMap<>();
		if (!onlyData) {
			json.put("currentPageNum", currentPageNum);
			json.put("totalPageNum", totalPageNum);
			json.put("pageSize", pageSize);
			json.put("totalRecordNum", totalRecordNum);
		}
		json.put("data", data);

		return json;
	}

	public Map<String, Object> toLayuiTableMap() {
		Map<String, Object> json = new HashMap<>();
		json.put("count", totalRecordNum);
		json.put("code", 0);
		json.put("msg", "");
		json.put("data", data);
		return json;
	}

	@Override
	public String toString() {
		return String.format("PagingRequest [currentPageNum=%s, totalPageNum=%s, pageSize=%s, totalRecordNum=%s, queryRecordStart=%s, queryRecordEnd=%s, data=%s]", currentPageNum, totalPageNum, pageSize, totalRecordNum, queryRecordStart, queryRecordEnd, data);
	}
}
