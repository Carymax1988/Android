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
	
	//MD5加密
	private static String toMd5(byte[] bytes){
		try {
			//实例化一个指定摘要算法为MD5的MessageDigest对象
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			//重置摘要以供再次使用
			algorithm.reset();
			//使用bytes更新摘要
			algorithm.update(bytes);
			//使用指定的byte数组对摘要进行最后更新，然后完成摘要计算
			return toHexString(algorithm.digest(), "");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//将字符串中的每个字符转换为十六进制
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
