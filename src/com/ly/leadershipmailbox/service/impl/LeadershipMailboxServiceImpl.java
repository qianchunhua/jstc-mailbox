package com.ly.leadershipmailbox.service.impl;

import java.util.List;
import java.util.Map;

import com.ly.leadershipmailbox.bean.Message;
import com.ly.leadershipmailbox.bean.MessageForward;
import com.ly.leadershipmailbox.dao.LeadershipMailboxDao;
import com.ly.leadershipmailbox.service.LeadershipMailboxService;
import com.ly.leadershipmailbox.util.LeadershipMailboxConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.transaction.annotation.Transactional;


public class LeadershipMailboxServiceImpl implements LeadershipMailboxService {
	private static final Logger LOG = LoggerFactory.getLogger(LeadershipMailboxServiceImpl.class);
	private LeadershipMailboxDao leadershipMailboxDao;

	private String querySql;
	private String querySingleSql;
	private String insertSql;
	private String updateSql;
	private String delSql;
	private String loginSql;
	private String allUserSql;
	private String userForwardSql;
	private String userForwardSaveSql;
	private String userForwardNewestSql;
	private String userDisplayNameSql;
	private String queryTeacherSql;

	public void setLeadershipMailboxDao(LeadershipMailboxDao leadershipMailboxDao) {
		this.leadershipMailboxDao = leadershipMailboxDao;
	}

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	public void setQuerySingleSql(String querySingleSql) {
		this.querySingleSql = querySingleSql;
	}

	public void setInsertSql(String insertSql) {
		this.insertSql = insertSql;
	}

	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}

	public void setDelSql(String delSql) {
		this.delSql = delSql;
	}

	public void setLoginSql(String loginSql) {
		this.loginSql = loginSql;
	}

	public void setAllUserSql(String allUserSql) {
		this.allUserSql = allUserSql;
	}

	public void setUserForwardSql(String userForwardSql) {
		this.userForwardSql = userForwardSql;
	}

	public void setUserForwardSaveSql(String userForwardSaveSql) {
		this.userForwardSaveSql = userForwardSaveSql;
	}

	public void setUserForwardNewestSql(String userForwardNewestSql) {
		this.userForwardNewestSql = userForwardNewestSql;
	}

	public void setUserDisplayNameSql(String userDisplayNameSql) {
		this.userDisplayNameSql = userDisplayNameSql;
	}

	public void setQueryTeacherSql(String queryTeacherSql) {
		this.queryTeacherSql = queryTeacherSql;
	}

	@Transactional(readOnly = true)
	@Override
	public int getItemListCount(Message message) {
		return leadershipMailboxDao.getItemListCount(querySql, message == null ? new EmptySqlParameterSource() : new BeanPropertySqlParameterSource(message));
	}

	@Transactional(readOnly = true)
	@Override
	public List<Map<String, Object>> getItemList(Message message, int start, int end) {
		return leadershipMailboxDao.getItemList(querySql, start, end, message == null ? new EmptySqlParameterSource() : new BeanPropertySqlParameterSource(message));
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, Object> getSingleItem(Message message) {
		return leadershipMailboxDao.getSingleItem(querySingleSql, message == null ? new EmptySqlParameterSource() : new BeanPropertySqlParameterSource(message));
	}

	@Transactional()
	@Override
	public int addSingleItem(Message message) {
		return leadershipMailboxDao.addSingleItem(insertSql, message == null ? new EmptySqlParameterSource() : new BeanPropertySqlParameterSource(message));
	}

	@Transactional()
	@Override
	public int updateSingleItem(Message message) {
		return leadershipMailboxDao.updateSingleItem(updateSql, message == null ? new EmptySqlParameterSource() : new BeanPropertySqlParameterSource(message));
	}

	@Transactional()
	@Override
	public int delSingleItem(Message message) {
		return leadershipMailboxDao.delSingleItem(delSql, message == null ? new EmptySqlParameterSource() : new BeanPropertySqlParameterSource(message));
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, Object> login(String username, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("password", password);
		return leadershipMailboxDao.getSingleItem(loginSql, parameters);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Map<String, Object>> getAllUser(String username, int start, int end) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(LeadershipMailboxConstants.SQL_RECORD_START, start);
		parameters.addValue(LeadershipMailboxConstants.SQL_RECORD_END, end);
		parameters.addValue("username", username);
		return leadershipMailboxDao.getItemList(allUserSql, start, end, parameters);
	}

	@Transactional(readOnly = true)
	public int getAllUserCount(String username) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		return leadershipMailboxDao.getItemListCount(allUserSql, parameters);
	}

	@Transactional(readOnly = true)
	@Override
	public int getUserForwardCount(MessageForward messageForward) {
		return leadershipMailboxDao.getItemListCount(userForwardSql, new BeanPropertySqlParameterSource(messageForward));
	}

	@Transactional()
	@Override
	public int addUserForward(MessageForward messageForward) {
		return leadershipMailboxDao.addSingleItem(userForwardSaveSql, new BeanPropertySqlParameterSource(messageForward));
	}

	@Override
	public Map<String, Object> getNewestUserForward(String email, String querykey) {
		MessageForward messageForward = new MessageForward();
		messageForward.setEmail(email);
		messageForward.setQuerykey(querykey);
		int forwardCount = getUserForwardCount(messageForward);

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(LeadershipMailboxConstants.EMAIL, email);
		parameters.addValue(LeadershipMailboxConstants.QUERY_KEY, querykey);
		parameters.addValue(LeadershipMailboxConstants.FORWARDORDER, forwardCount);
		return leadershipMailboxDao.getSingleItem(userForwardNewestSql, parameters);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Map<String, Object>> joinUserForward(List<Map<String, Object>> messageList) {
		if (CollectionUtils.isNotEmpty(messageList)) {
			for (Map<String, Object> message : messageList) {
				String email = MapUtils.getString(message, LeadershipMailboxConstants.EMAIL, "");
				String queryKey = MapUtils.getString(message, LeadershipMailboxConstants.QUERY_KEY, "");
				Map<String, Object> newestUserForward = getNewestUserForward(email, queryKey);
				if (newestUserForward != null) {
					String fromusername = MapUtils.getString(newestUserForward, LeadershipMailboxConstants.FROMUSERNAME, "");
					message.put(LeadershipMailboxConstants.FROMDISPLAYNAME, getUserDisplayName(fromusername));
					String tousername = MapUtils.getString(newestUserForward, LeadershipMailboxConstants.TOUSERNAME, "");
					message.put(LeadershipMailboxConstants.TODISPLAYNAME, getUserDisplayName(tousername));
					message.putAll(newestUserForward);
				}
			}
		}
		return messageList;
	}

	@Override
	public String getUserDisplayName(String userName) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(LeadershipMailboxConstants.USERNAME, userName);
		Map<String, Object> singleItem = leadershipMailboxDao.getSingleItem(userDisplayNameSql, parameters);
		if (MapUtils.isNotEmpty(singleItem)) {
			return MapUtils.getString(singleItem, LeadershipMailboxConstants.DISPLAYNAME, "");
		} else {
			return "";
		}
	}

	@Override
	public int getTeacherItemListCount(String username) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		
		return leadershipMailboxDao.getItemListCount(queryTeacherSql, parameters);
	}

	@Override
	public List<Map<String, Object>> getTeacherItemList(String username, int start, int end) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(LeadershipMailboxConstants.SQL_RECORD_START, start);
		parameters.addValue(LeadershipMailboxConstants.SQL_RECORD_END, end);
		parameters.addValue("username", username);
		return leadershipMailboxDao.getItemList(queryTeacherSql, start, end, parameters);
	}
		
	
}
