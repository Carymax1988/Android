package com.des;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.kobjects.base64.Base64;

public class DES {

	private static byte[] iv = {1,2,3,4,5,6,7,8};
	
	/**
	 * DES����
	 * @param encryptStringΪԭ��
	 * @param encryptKeyΪ��Կ
	 * @return ���ؼ��ܺ������
	 * @throws Exception
	 */
	public static String encryptDES(String encryptString,String encryptKey)throws Exception{
		//ʵ����lvParameterSpec����ʹ��ָ���ĳ�ʼ������
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		//ʵ����SecretKeySpec�࣬�����ֽ�����������SecretKey
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		//����������
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		//����Կ��ʼ��Cipher����
		cipher.init(Cipher.ENCRYPT_MODE, key,zeroIv);
		//ִ�м��ܲ���
		byte[]encryptedData = cipher.doFinal(encryptString.getBytes());
		//���ؼ��ܺ������
		return Base64.encode(encryptedData);
	}
	
	/**
	 * DES����
	 * @param decryptStringΪ����
	 * @param decryptKeyΪ��Կ
	 * @return ���ؽ��ܺ��ԭ��
	 * @throws Exception
	 */
	public static String decryptDES(String decryptString,String decryptKey)throws Exception{
		//��ʹ��Base64����
		byte[] byteMi = new Base64().decode(decryptString);
		//ʵ����lvParameterSpec����ʹ��ָ���ĳ�ʼ������
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		//ʵ����SecretKeySpec�࣬�����ֽ�����������SecretKey
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		//����������
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		//����Կ��ʼ��Cipher����
		cipher.init(Cipher.DECRYPT_MODE, key,zeroIv);
		//��ȡ���ܵ�����
		byte decryptedData[] = cipher.doFinal(byteMi);
		//��������ת��Ϊ�ַ������
		return new String(decryptedData);
	}
}