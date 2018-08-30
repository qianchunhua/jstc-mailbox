package com.ly.leadershipmailbox.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public interface LeadershipMailboxDao {
	int getItemListCount(String querySql, SqlParameterSource params);

	List<Map<String, Object>> getItemList(String querySql, int start, int end, SqlParameterSource params);

	Map<String, Object> getSingleItem(String querySql, SqlParameterSource params);

	<T> T getSingleItem(String querySql, SqlParameterSource params, Class<T> requiredType);

	int addSingleItem(String insertSql, SqlParameterSource params);

	int updateSingleItem(String updateSql, SqlParameterSource params);

	int delSingleItem(String delSql, SqlParameterSource params);

}
