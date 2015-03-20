package com.liujiaqi.connectionmanager;

public class XMPPType {
	public static enum Type{
		//连接服务器
		ConnectServer,
		//创建联系人
		Register,
		//登录代码
		Login,
		//用户下线
		Logout,
		//隐身
		Disappear,
		//获取联系人列表
		ContactsList,
		//获取联系人状态
		ContactsStatues,
		//设置状态（个性签名）
		Status,
		//添加联系人
		addContacts,
		//删除联系人
		deleteContacts,
		//监听联系人添加信息
		addListener,
		//接收消息
		addMessageListener,
		//发送消息
		sendMessage,
		//获取指定联系人状态
		SomeOneStatus
	};
}
