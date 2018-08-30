package com.ly.leadershipmailbox.bean;

import java.io.Serializable;

public class MessageForward implements Serializable {
	//电子邮箱
	private String email;
	//查询回复的密钥
	private String querykey;
	//转发顺序
	private int forwardorder;
	//从哪个用户名
	private String fromusername;
	//转发给哪个用户名
	private String tousername;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuerykey() {
		return querykey;
	}

	public void setQuerykey(String querykey) {
		this.querykey = querykey;
	}

	public int getForwardorder() {
		return forwardorder;
	}

	public void setForwardorder(int forwardorder) {
		this.forwardorder = forwardorder;
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MessageForward{");
		sb.append("email='").append(email).append('\'');
		sb.append(", querykey='").append(querykey).append('\'');
		sb.append(", forwardorder=").append(forwardorder);
		sb.append(", fromusername='").append(fromusername).append('\'');
		sb.append(", tousername='").append(tousername).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
