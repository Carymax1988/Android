package com.liujiaqi.connectionmanager;

public class XMPPType {
	public static enum Type{
		//���ӷ�����
		ConnectServer,
		//������ϵ��
		Register,
		//��¼����
		Login,
		//�û�����
		Logout,
		//����
		Disappear,
		//��ȡ��ϵ���б�
		ContactsList,
		//��ȡ��ϵ��״̬
		ContactsStatues,
		//����״̬������ǩ����
		Status,
		//�����ϵ��
		addContacts,
		//ɾ����ϵ��
		deleteContacts,
		//������ϵ�������Ϣ
		addListener,
		//������Ϣ
		addMessageListener,
		//������Ϣ
		sendMessage,
		//��ȡָ����ϵ��״̬
		SomeOneStatus
	};
}
