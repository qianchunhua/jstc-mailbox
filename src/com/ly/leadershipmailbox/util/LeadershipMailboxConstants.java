package com.ly.leadershipmailbox.util;


public class LeadershipMailboxConstants {
	//错误
	public static final String ERROR_MSG = "errorMsg";
	public static final String ERROR_LOGIN = "登录失败，请检查用户名和密码！";
	public static final String ERROR_CAPTCHA = "验证码错误！";

	//登录状态
	public static final String STATE__LOGIN_NAME = "login";
	public static final String STATE__LOGIN = "login";


	//留言状态
	public static final String STATE_NOT_REPLY = "未回复";
	public static final String STATE_REPLIED = "已回复";

	//手风琴显示状态
	public static final String COLLAPSE_STATE = "collapseState";
	public static final String COLLAPSE_EMAILNEW = "emailNew";
	public static final String COLLAPSE_EMAILQUERY = "emailQuery";

	//Bean名称
	//用户显示名称
	public static final String DISPLAYNAME = "displayname";
	//用户账号
	public static final String USERNAME = "username";
	//用户类型
	public static final String USERTYPE = "usertype";
	//电子邮箱
	public static final String EMAIL = "email";
	//状态
	public static final String STATE = "state";
	//回复
	public static final String REPLY = "reply";
	//未回复时的提示
	public static final String STATE_NOT_REPLY_MSG = "邮件在处理中，请稍后再试。";
	public static final String STATE_NOT_EXIST_MSG = "邮件不存在，请检查。";
	//查询回复用的密钥
	public static final String QUERY_KEY = "querykey";

	//用户转发顺序
	public static final String FORWARDORDER = "forwardorder";
	//用户转发最大次数
	public static final int USER_FORWARD_MAX_COUNT = 2;
	//从哪个用户名转发
	public static final String FROMUSERNAME = "fromusername";
	//转发给哪个用户名
	public static final String TOUSERNAME = "tousername";
	//转发给哪个显示名称
	public static final String FROMDISPLAYNAME = "fromdisplayname";
	//转发给哪个显示名称
	public static final String TODISPLAYNAME = "todisplayname";

	//DAO参数名称
	public static final String SQL_RECORD_START = "start";
	public static final String SQL_RECORD_END = "end";
	public static final String SQL_PAGING_START = "SELECT * FROM (SELECT PAGINGTEMP.*, ROWNUM RN FROM (";
	public static final String SQL_PAGING_END = ") PAGINGTEMP WHERE ROWNUM <= :end) WHERE RN > :start";
	public static final String SQL_COUNT_START = "SELECT COUNT(*) FROM (";
	public static final String SQL_COUNT_END = ")";
}
