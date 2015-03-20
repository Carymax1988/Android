package com.aes;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	/**
	 * AES����
	 * @param seedΪ��Կ
	 * @param cleartextΪ��Ҫ���ܵ�����
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String seed,String cleartext)throws Exception{
		//����Կ���б���
		byte[] rawKey = getRawKey(seed.getBytes());
		//��������
		byte[] result = encrypt(rawKey, cleartext.getBytes());
		//��ʮ������ת��Ϊʮ��������
		return toHex(result);
	}
	
	/**
	 * AES����
	 * @param seedΪ��Կ
	 * @param encryptedΪ��Ҫ���ܵ�����
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String seed,String encrypted)throws Exception{
		//����Կ���б���
		byte[] rawKey = getRawKey(seed.getBytes());
		byte[] enc = toByte(encrypted);
		byte[] result = decrypt(rawKey, enc);
		return new String(result);
	}
	
	//����
	private static byte[] encrypt(byte[] raw,byte[] clear)throws Exception{
		//����һ����չ��Կ��������һ������֮��
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		//��ENCRYPT_MODEģʽ����skeySpec�����飬����AES���ܷ���
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		//�õ���������
		byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}
	
	//����
	private static byte[] decrypt(byte[] raw,byte[] encrypted)throws Exception{
		//����һ����չ��Կ��������һ������֮��
		SecretKeySpec skeySpec = new SecretKeySpec(raw,"AES");
		Cipher cipher = Cipher.getInstance("AES");
		//��DECRYPT_MODEģʽ����skeySpec�����飬����AES���ܷ���
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		//�õ���������
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}
	
	//����Կ���б���
	private static byte[] getRawKey(byte[] seed)throws Exception{
		//��ȡ��Կ������
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr  = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(seed);
		//����128Ϊ��AES����������
		kgen.init(128,sr);
		//������Կ
		SecretKey skey = kgen.generateKey();
		//�����ʽ
		byte[] raw = skey.getEncoded();
		return raw;
	}
	
	//��ʮ������ת��Ϊʮ������
	public static String toHex(String txt){
		return toHex(txt.getBytes());
	}
	
	//��ʮ�������ַ���ת��Ϊʮ�����ַ���
	public static String fromHex(String hex){
		return new String(toByte(hex));
	}
	
	//��ʮ�������ַ���ת��Ϊʮ�����ֽ�����
	public static byte[] toByte(String hexString){
		int len = hexString.length()/2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2*i,2*i+2),16).byteValue();
		}
		return result;
	}
	
	//��һ��ʮ�����ֽ�����ת����ʮ������
	public static String toHex(byte[] buf){
		if(buf == null)
			return "";
		StringBuffer result = new StringBuffer(2*buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}
	
	private final static String HEX = "0123456789ABCDEF";
	
	private static void appendHex(StringBuffer sb,byte b){
		sb.append(HEX.charAt((b >> 4)&0x0f)).append(HEX.charAt(b & 0x0f));
	}
}
