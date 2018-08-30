package com.ly.leadershipmailbox.bean;


import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	//留言标题
	private String title;
	//留言内容
	private String content;
	//留言类型
	private String type;
	//网名/昵称
	private String nickname;
	//手机号码
	private String mobilephone;
	//电子邮箱
	private String email;
	//状态
	private String state;
	//查询回复的密钥
	private String querykey;
	//回复内容
	private String reply;
	//留言提交时间
	private Date submitdate;
	//回复时间
	private Date replydate;
	//已转发次数
	private int forwardCount;

	//分页计算好的开始序号
	private transient int start;
	//分页计算好的结束序号
	private transient int end;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getQuerykey() {
		return querykey;
	}

	public void setQuerykey(String querykey) {
		this.querykey = querykey;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Date getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(Date submitdate) {
		this.submitdate = submitdate;
	}

	public Date getReplydate() {
		return replydate;
	}

	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getForwardCount() {
		return forwardCount;
	}

	public void setForwardCount(int forwardCount) {
		this.forwardCount = forwardCount;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Message{");
		sb.append("title='").append(title).append('\'');
		sb.append(", content='").append(content).append('\'');
		sb.append(", type='").append(type).append('\'');
		sb.append(", nickname='").append(nickname).append('\'');
		sb.append(", mobilephone='").append(mobilephone).append('\'');
		sb.append(", email='").append(email).append('\'');
		sb.append(", state='").append(state).append('\'');
		sb.append(", querykey='").append(querykey).append('\'');
		sb.append(", reply='").append(reply).append('\'');
		sb.append(", submitdate=").append(submitdate);
		sb.append(", replydate=").append(replydate);
		sb.append(", forwardCount=").append(forwardCount);
		sb.append(", start=").append(start);
		sb.append(", end=").append(end);
		sb.append('}');
		return sb.toString();
	}
}
