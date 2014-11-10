package com.liujiaqi.robot;

public class MessageData {
	public static final int ROBOTMESSAGE = 0;
	public static final int USERMESSAGE = 1;
	private String message;
	private int flag;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
