package com.ly.leadershipmailbox.dao.impl;

import java.util.List;
import java.util.Map;

import com.ly.leadershipmailbox.dao.LeadershipMailboxDao;
import com.ly.leadershipmailbox.util.LeadershipMailboxConstants;
import com.ly.leadershipmailbox.util.LowerCaseColumnMapRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository("LeadershipMailboxDao")
public class LeadershipMailboxDaoImpl implements LeadershipMailboxDao {
	private static final Logger LOG = LoggerFactory.getLogger(LeadershipMailboxDaoImpl.class);
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int getItemListCount(String querySql, SqlParameterSource params) {
		LOG.debug("querySql : {}", querySql);
		LOG.debug("params : {}", params);

		String sql = LeadershipMailboxConstants.SQL_COUNT_START + querySql + LeadershipMailboxConstants.SQL_COUNT_END;
		LOG.debug("sql : {}", sql);
		
		return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
	}

	@Override
	public List<Map<String, Object>> getItemList(String querySql, int start, int end, SqlParameterSource params) {
		LOG.debug("querySql : {}", querySql);

		String sql = querySql;
		if (start != -1 && end != -1) {
			sql = LeadershipMailboxConstants.SQL_PAGING_START + querySql + LeadershipMailboxConstants.SQL_PAGING_END;
		}
		LOG.debug("sql : {}", sql);

		return namedParameterJdbcTemplate.query(sql, params, new LowerCaseColumnMapRowMapper());
	}

	@Override
	public Map<String, Object> getSingleItem(String querySql, SqlParameterSource params) {
		LOG.debug("insertSql : {}", querySql);
		LOG.debug("params : {}", params);
		try {
			return namedParameterJdbcTemplate.queryForObject(querySql, params, new LowerCaseColumnMapRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public <T> T getSingleItem(String querySql, SqlParameterSource params, Class<T> requiredType) {
		LOG.debug("insertSql : {}", querySql);
		LOG.debug("params : {}", params);
		LOG.debug("requiredType : {}", requiredType);
		try {
			return namedParameterJdbcTemplate.queryForObject(querySql, params, requiredType);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int addSingleItem(String insertSql, SqlParameterSource params) {
		LOG.debug("insertSql : {}", insertSql);
		LOG.debug("params : {}", params);
		return namedParameterJdbcTemplate.update(insertSql, params);
	}

	@Override
	public int updateSingleItem(String updateSql, SqlParameterSource params) {
		LOG.debug("updateSql : {}", updateSql);
		LOG.debug("params : {}", params);

		return namedParameterJdbcTemplate.update(updateSql, params);
	}

	@Override
	public int delSingleItem(String delSql, SqlParameterSource params) {
		LOG.debug("delSql : {}", delSql);
		LOG.debug("params : {}", params);

		return namedParameterJdbcTemplate.update(delSql, params);
	}
}
