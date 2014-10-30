package com.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	private String md5 = "";
	
	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public MD5(byte[] bytes) {
		md5 = toMd5(bytes);
	}
	
	//MD5����
	private static String toMd5(byte[] bytes){
		try {
			//ʵ����һ��ָ��ժҪ�㷨ΪMD5��MessageDigest����
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			//����ժҪ�Թ��ٴ�ʹ��
			algorithm.reset();
			//ʹ��bytes����ժҪ
			algorithm.update(bytes);
			//ʹ��ָ����byte�����ժҪ���������£�Ȼ�����ժҪ����
			return toHexString(algorithm.digest(), "");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//���ַ����е�ÿ���ַ�ת��Ϊʮ������
	private static String toHexString(byte[] bytes,String separator){
		StringBuffer hexString = new StringBuffer();
		for(byte b:bytes){
			String hex = Integer.toHexString(0xFF&b);
			if(hex.length()==1){
				hexString.append('0');
			}
			hexString.append(hex).append(separator);
		}
		return hexString.toString();
	}
}
