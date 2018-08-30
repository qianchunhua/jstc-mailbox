package com.ly.leadershipmailbox.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ly.leadershipmailbox.bean.Message;
import com.ly.leadershipmailbox.bean.MessageForward;
import com.ly.leadershipmailbox.service.LeadershipMailboxService;
import com.ly.leadershipmailbox.util.CaptchaSessionRender;
import com.ly.leadershipmailbox.util.LeadershipMailboxConstants;
import com.ly.leadershipmailbox.util.PagingRequest;
import com.ybg.bean.AuthResult;
import com.ybg.service.auth.PlatformAuth;
import com.ybg.util.LogUtil;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LeadershipMailboxAction {
	private static final Logger LOG = LoggerFactory.getLogger(LeadershipMailboxAction.class);
	private LeadershipMailboxService leadershipMailboxService; 

	@Autowired
	public void setLeadershipMailboxService(LeadershipMailboxService leadershipMailboxService) {
		this.leadershipMailboxService = leadershipMailboxService;
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/emailNew")
	public String emailNew(ModelMap modelMap) {
		modelMap.put(LeadershipMailboxConstants.COLLAPSE_STATE, LeadershipMailboxConstants.COLLAPSE_EMAILNEW);
		return "email";
	}
	
	@RequestMapping("/emailReply")
	public String emailReply(ModelMap modelMap) {
		modelMap.put(LeadershipMailboxConstants.COLLAPSE_STATE, LeadershipMailboxConstants.COLLAPSE_EMAILQUERY);
		return "email";
	}
	
	@RequestMapping("/emailSave")
	public String emailSave(Message message, String checkcode, ModelMap modelMap, HttpServletRequest request) {
		boolean validate = CaptchaSessionRender.validate(request, checkcode);
		if (validate) {
			message.setState(LeadershipMailboxConstants.STATE_NOT_REPLY);
			message.setSubmitdate(new Date());
			/**
			 * 随机生成6位数queryKey
			 * queryKey这里用作游客查询信箱页面登录之用途。
			 * @author spring
			 */
			Random random = new Random();
			String queryKey = "";
			for (int i = 0; i < 6; i ++) {
				queryKey += random.nextInt(10);
			}
			message.setQuerykey(queryKey);
			int result;
			try {
				result = leadershipMailboxService.addSingleItem(message);
			} catch (Exception e) {
				LOG.error("addSingleItem error :", e);
				return "fail";
			}
			if (result > 0) {
				modelMap.put(LeadershipMailboxConstants.QUERY_KEY, queryKey);
				return "success";
			} else {
				return "fail";
			}
		}
		modelMap.addAttribute(LeadershipMailboxConstants.ERROR_MSG, LeadershipMailboxConstants.ERROR_CAPTCHA);
		return "email";
	}

	@RequestMapping("/emailQuery")
	public String emailQuery(Message message, ModelMap modelMap, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute(LeadershipMailboxConstants.EMAIL, message.getEmail());
		session.setAttribute(LeadershipMailboxConstants.QUERY_KEY, message.getQuerykey());
		
		Map<String, Object> singleItem;
		try {
			singleItem = leadershipMailboxService.getSingleItem(message);
		} catch (Exception e) {
			LOG.error("emailQuery error :", e);
			return "fail";
		}
		String reply = LeadershipMailboxConstants.STATE_NOT_REPLY_MSG;
		if (singleItem == null) {
			reply = LeadershipMailboxConstants.STATE_NOT_EXIST_MSG;
		} else {
			String state = MapUtils.getString(singleItem, LeadershipMailboxConstants.STATE, "");
			if (StringUtils.equals(state, LeadershipMailboxConstants.STATE_REPLIED)) {
				reply = MapUtils.getString(singleItem, LeadershipMailboxConstants.REPLY, "");
			}			
		}
		modelMap.put(LeadershipMailboxConstants.REPLY, reply);
		modelMap.put(LeadershipMailboxConstants.COLLAPSE_STATE, LeadershipMailboxConstants.COLLAPSE_EMAILQUERY);
		return "queryReply";
	}
	
	@RequestMapping("/emailQueryShow")
	@ResponseBody
	public Map<String, Object> emailQueryShow(int page, int pageSize, HttpServletRequest request) {
		HttpSession session = request.getSession();		
		if (session == null) {
			LOG.warn("Permission denied , need login !");
			Map<String, Object> json = new HashMap<>();
			json.put("count", 0);
			json.put("code", 0);
			json.put("msg", "");
			json.put("data", Collections.emptyList());
			return json;
		} else {
			Message message = new Message();
			message.setEmail((String)session.getAttribute(LeadershipMailboxConstants.EMAIL));
			message.setQuerykey((String )session.getAttribute(LeadershipMailboxConstants.QUERY_KEY));
			Map<String, Object> singleItem;
			singleItem = leadershipMailboxService.getSingleItem(message);
			
			int emailCount = 1;
			page = Math.max(page, 0);
			pageSize = Math.max(pageSize, 10);
			PagingRequest<Map<String, Object>> pagingRequest = new PagingRequest<>(page, pageSize, emailCount);
			
			List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
			itemList.add(singleItem);
			pagingRequest.setData(itemList);
			
			return pagingRequest.toLayuiTableMap();
		}
	}
		
	/**
	 * 实现移动APP认证对接
	 * @author spring
	 * @return login
	 * @since 3.0
	 */
	@RequestMapping(value = "/leadershipLoginPageCas",method = RequestMethod.GET)
	public String auth(@RequestParam String accessToken,@RequestParam String uniqueCode,
			   		   @RequestParam String yyid,@RequestParam String type,ModelMap modelMap,
			   		   HttpServletRequest request) {
		PlatformAuth platformAuth = PlatformAuth.getInstance();
		AuthResult authResult = null;
		try {
			authResult = platformAuth.auth(accessToken, uniqueCode, yyid, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取用户id
		String userId = "";
		String userType = "";//获取类型
		String xxdm = "";//获取学校代码
		String userName = "";
		String url = "";
		if (authResult.isSuccess()) {
			//用户账号
			userId = authResult.getData().getUserId();
			//获取老师姓名
			userName = authResult.getData().getUserName();
			//获取用户类型
			userType =  authResult.getData().getUserType();
			//获取学校代码
			xxdm = authResult.getData().getXxdm();
			/**
			 * target url是用于一个系统中有多个应用要接入平台的情况,
			 * target url用于标识应用系统认证成功后,该跳转到具体哪个应用。
			 */
			url = authResult.getData().getTargetUrl();
			
		} 
		//判断获取的是否拿到用户id
		if ("".equals(userId)) {
			//如果为空,那么跳转重定向至登录页面
			return "login";
		} 
		
		//否则，将信息保存到session 
		HttpSession session = request.getSession(true);
		session.setAttribute(LeadershipMailboxConstants.STATE__LOGIN_NAME, LeadershipMailboxConstants.STATE__LOGIN);
		session.setAttribute(LeadershipMailboxConstants.USERNAME, userId);
		session.setAttribute(LeadershipMailboxConstants.DISPLAYNAME, userName);
		session.setAttribute(LeadershipMailboxConstants.USERTYPE, userType);
		modelMap.addAttribute("displayname", userName);
		return "emailMgt";
		
	}
	
	@RequestMapping("/leadershipLoginPageCas")
	public String leadershipLoginPageCas() {
		return "login";
	}
	
	@RequestMapping("/leadershipLoginPage")
	public String leadershipLoginPage() {
		return "login";
	}
	/**
	 * 添加usertype的session保存
	 * 
	 * @param username
	 * @param password
	 * @param modelMap
	 * @param request
	 * @return
	 */

	@RequestMapping("/leadershipLogin")
	public String leadershipLogin(String username, String password, ModelMap modelMap, HttpServletRequest request) {
		Map<String, Object> user;
		try {
			user = leadershipMailboxService.login(username, password);			
		} catch (Exception e) {
			LOG.error("login error :", e);
			return "fail";
		}
		if (user == null) {
			modelMap.put(LeadershipMailboxConstants.ERROR_MSG, LeadershipMailboxConstants.ERROR_LOGIN);
			return "login";
		}
		HttpSession session = request.getSession(true);
		session.setAttribute(LeadershipMailboxConstants.STATE__LOGIN_NAME, LeadershipMailboxConstants.STATE__LOGIN);
		session.setAttribute(LeadershipMailboxConstants.USERNAME, username);
		session.setAttribute(LeadershipMailboxConstants.USERTYPE, MapUtils.getString(user, LeadershipMailboxConstants.USERTYPE, ""));
		modelMap.put(LeadershipMailboxConstants.DISPLAYNAME, MapUtils.getString(user, LeadershipMailboxConstants.DISPLAYNAME, ""));
		return "emailMgt";
	}

	/**
	 * 根据用户类别处理数据
	 * 
	 * @param page
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("/emailList")
	@ResponseBody
	public Map<String, Object> emailList(int page, int pageSize, HttpServletRequest request) {
		HttpSession session = request.getSession();		
		if (session == null) {
			LOG.warn("Permission denied , need login !");
			Map<String, Object> json = new HashMap<>();
			json.put("count", 0);
			json.put("code", 0);
			json.put("msg", "");
			json.put("data", Collections.emptyList());
			return json;
		} else {
			Object login = session.getAttribute(LeadershipMailboxConstants.STATE__LOGIN_NAME);
			if (login != null) {
				String loginStr = (String) login;
				if (!StringUtils.equalsIgnoreCase(loginStr, LeadershipMailboxConstants.STATE__LOGIN)) {
					LOG.warn("Permission denied , need login !");
					Map<String, Object> json = new HashMap<>();
					json.put("count", 0);
					json.put("code", 0);
					json.put("msg", "");
					json.put("data", Collections.emptyList());
					return json;
				}
			} else {
				LOG.warn("Permission denied , need login !");
				Map<String, Object> json = new HashMap<>();
				json.put("count", 0);
				json.put("code", 0);
				json.put("msg", "");
				json.put("data", Collections.emptyList());
				return json;
			}
		}
		
		int emailCount = 0;
		String username = (String)session.getAttribute(LeadershipMailboxConstants.USERNAME);
		String usertype = (String)session.getAttribute(LeadershipMailboxConstants.USERTYPE);
		
		if(StringUtils.equalsIgnoreCase(usertype, "teacher")){
			emailCount = leadershipMailboxService.getTeacherItemListCount(username);
		} else {
			emailCount = leadershipMailboxService.getItemListCount(null);	
		}
		page = Math.max(page, 0);
		pageSize = Math.max(pageSize, 10);
		PagingRequest<Map<String, Object>> pagingRequest = new PagingRequest<>(page, pageSize, emailCount);
		Message message = new Message();
		message.setStart(pagingRequest.getQueryRecordStart());
		message.setEnd(pagingRequest.getQueryRecordEnd());
		List<Map<String, Object>> itemList;
		try {
			if(StringUtils.equalsIgnoreCase(usertype, "teacher")){
				itemList = leadershipMailboxService.getTeacherItemList(username, pagingRequest.getQueryRecordStart(), pagingRequest.getQueryRecordEnd());
			} else {
				itemList = leadershipMailboxService.getItemList(message, pagingRequest.getQueryRecordStart(), pagingRequest.getQueryRecordEnd());
			}
			itemList = leadershipMailboxService.joinUserForward(itemList);
			pagingRequest.setData(itemList);
		} catch (Exception e) {
			LOG.error("emailList error :", e);
		}
		return pagingRequest.toLayuiTableMap();
	}
	
	@RequestMapping("/replyPage")
	public String replyPage(Message message, ModelMap modelMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			LOG.warn("Permission denied , need login !");
			return "login";
		} else {
			Object login = session.getAttribute(LeadershipMailboxConstants.STATE__LOGIN_NAME);
			if (login != null) {
				String loginStr = (String) login;
				if (!StringUtils.equalsIgnoreCase(loginStr, LeadershipMailboxConstants.STATE__LOGIN)) {
					LOG.warn("Permission denied , need login !");
					return "login";
				}
			} else {
				LOG.warn("Permission denied , need login !");
				return "login";
			}
		}

		Map<String, Object> singleItem;
		try {
			singleItem = leadershipMailboxService.getSingleItem(message);
		} catch (Exception e) {
			LOG.error("replyPage error :", e);
			return "fail";
		}
		modelMap.putAll(singleItem);
		return "reply";
	}

	@RequestMapping("/replyEdit")
	public void replyEdit(Message message, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			LOG.warn("Permission denied , need login !");
			return;
		} else {
			Object login = session.getAttribute(LeadershipMailboxConstants.STATE__LOGIN_NAME);
			if (login != null) {
				String loginStr = (String) login;
				if (!StringUtils.equalsIgnoreCase(loginStr, LeadershipMailboxConstants.STATE__LOGIN)) {
					LOG.warn("Permission denied , need login !");
					return;
				}
			} else {
				LOG.warn("Permission denied , need login !");
				return;
			}
		}

		if (message != null) {
			message.setReplydate(new Date());
			message.setState(LeadershipMailboxConstants.STATE_REPLIED);
		}
		int result = leadershipMailboxService.updateSingleItem(message);
		if (result < 1) {
			LOG.error("replyEdit fail");
		}
	}
	
	
	
	@RequestMapping("/userPage")
	public String userPage(ModelMap modelMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			LOG.warn("Permission denied , need login !");
			return "login";
		} else {
			Object login = session.getAttribute(LeadershipMailboxConstants.STATE__LOGIN_NAME);
			if (login != null) {
				String loginStr = (String) login;
				if (!StringUtils.equalsIgnoreCase(loginStr, LeadershipMailboxConstants.STATE__LOGIN)) {
					LOG.warn("Permission denied , need login !");
					return "login";
				}
			} else {
				LOG.warn("Permission denied , need login !");
				return "login";
			}
		}
		return "user";
	}

	/**
	 * 将自己从转发列表中剔除，根据username
	 * 
	 * @param page
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("/userList")
	@ResponseBody
	public Map<String, Object> userList(int page, int pageSize, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			LOG.warn("Permission denied , need login !");
			Map<String, Object> json = new HashMap<>();
			json.put("count", 0);
			json.put("code", 0);
			json.put("msg", "");
			json.put("data", Collections.emptyList());
			return json;
		} else {
			Object login = session.getAttribute(LeadershipMailboxConstants.STATE__LOGIN_NAME);
			if (login != null) {
				String loginStr = (String) login;
				if (!StringUtils.equalsIgnoreCase(loginStr, LeadershipMailboxConstants.STATE__LOGIN)) {
					LOG.warn("Permission denied , need login !");
					Map<String, Object> json = new HashMap<>();
					json.put("count", 0);
					json.put("code", 0);
					json.put("msg", "");
					json.put("data", Collections.emptyList());
					return json;
				}
			} else {
				LOG.warn("Permission denied , need login !");
				Map<String, Object> json = new HashMap<>();
				json.put("count", 0);
				json.put("code", 0);
				json.put("msg", "");
				json.put("data", Collections.emptyList());
				return json;
			}
		}
		
		String username = (String)session.getAttribute(LeadershipMailboxConstants.USERNAME);
		int userCount = leadershipMailboxService.getAllUserCount(username);
		page = Math.max(page, 0);
		pageSize = Math.max(pageSize, 10);
		PagingRequest<Map<String, Object>> pagingRequest = new PagingRequest<>(page, pageSize, userCount);
		Message message = new Message();
		message.setStart(pagingRequest.getQueryRecordStart());
		message.setEnd(pagingRequest.getQueryRecordEnd());
		List<Map<String, Object>> itemList;
		try {
			itemList = leadershipMailboxService.getAllUser(username, pagingRequest.getQueryRecordStart(), pagingRequest.getQueryRecordEnd());
			pagingRequest.setData(itemList);
		} catch (Exception e) {
			LOG.error("emailList error :", e);
		}
		return pagingRequest.toLayuiTableMap();
	}

	@RequestMapping("/userForward")
	public void userForward(MessageForward messageForward, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			LOG.warn("Permission denied , need login !");
		} else {
			String currentUserName = session.getAttribute(LeadershipMailboxConstants.USERNAME) + "";
			int forwardCount = leadershipMailboxService.getUserForwardCount(messageForward);
			if (forwardCount < LeadershipMailboxConstants.USER_FORWARD_MAX_COUNT) {
				messageForward.setForwardorder(forwardCount + 1);
				messageForward.setFromusername(currentUserName);
				leadershipMailboxService.addUserForward(messageForward);
			}
		}
	}
	
	@RequestMapping("/getCaptcha")
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
		new CaptchaSessionRender().create(request, response);
	}
}
