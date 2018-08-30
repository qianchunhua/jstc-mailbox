package com.ly.leadershipmailbox.service;


import java.util.List;
import java.util.Map;

import com.ly.leadershipmailbox.bean.Message;
import com.ly.leadershipmailbox.bean.MessageForward;

public interface LeadershipMailboxService {
	int getItemListCount(Message message);

	List<Map<String, Object>> getItemList(Message message, int start, int end);

	Map<String, Object> getSingleItem(Message message);

	int addSingleItem(Message message);

	int updateSingleItem(Message message);

	int delSingleItem(Message message);

	Map<String, Object> login(String username, String password);
	
	//Map<String, Object> loginCas(String username, String password); 

	List<Map<String, Object>> getAllUser(String username, int start, int end);

	int getAllUserCount(String username);

	int getUserForwardCount(MessageForward messageForward);

	int addUserForward(MessageForward messageForward);

	Map<String, Object> getNewestUserForward(String email, String querykey);

	List<Map<String, Object>> joinUserForward(List<Map<String, Object>> messageList);

	String getUserDisplayName(String userName);
	
	int getTeacherItemListCount(String username);
	
	List<Map<String, Object>> getTeacherItemList(String username, int start, int end);
}
