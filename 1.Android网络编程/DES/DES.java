package com.des;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.kobjects.base64.Base64;

public class DES {

	private static byte[] iv = {1,2,3,4,5,6,7,8};
	
	/**
	 * DES加密
	 * @param encryptString为原文
	 * @param encryptKey为密钥
	 * @return 返回加密后的密文
	 * @throws Exception
	 */
	public static String encryptDES(String encryptString,String encryptKey)throws Exception{
		//实例化lvParameterSpec对象，使用指定的初始化向量
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		//实例化SecretKeySpec类，根据字节数组来构造SecretKey
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		//创建密码器
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		//用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, key,zeroIv);
		//执行加密操作
		byte[]encryptedData = cipher.doFinal(encryptString.getBytes());
		//返回加密后的数据
		return Base64.encode(encryptedData);
	}
	
	/**
	 * DES解密
	 * @param decryptString为密文
	 * @param decryptKey为密钥
	 * @return 返回解密后的原文
	 * @throws Exception
	 */
	public static String decryptDES(String decryptString,String decryptKey)throws Exception{
		//先使用Base64解密
		byte[] byteMi = new Base64().decode(decryptString);
		//实例化lvParameterSpec对象，使用指定的初始化向量
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		//实例化SecretKeySpec类，根据字节数组来构造SecretKey
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		//创建密码器
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		//用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, key,zeroIv);
		//获取解密的数据
		byte decryptedData[] = cipher.doFinal(byteMi);
		//解密数据转换为字符串输出
		return new String(decryptedData);
	}
}