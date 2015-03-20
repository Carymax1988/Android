package com.aes;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	/**
	 * AES加密
	 * @param seed为密钥
	 * @param cleartext为需要加密的内容
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String seed,String cleartext)throws Exception{
		//对密钥进行编码
		byte[] rawKey = getRawKey(seed.getBytes());
		//加密数据
		byte[] result = encrypt(rawKey, cleartext.getBytes());
		//将十进制数转换为十六进制数
		return toHex(result);
	}
	
	/**
	 * AES解密
	 * @param seed为密钥
	 * @param encrypted为需要解密的内容
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String seed,String encrypted)throws Exception{
		//对密钥进行编码
		byte[] rawKey = getRawKey(seed.getBytes());
		byte[] enc = toByte(encrypted);
		byte[] result = decrypt(rawKey, enc);
		return new String(result);
	}
	
	//加密
	private static byte[] encrypt(byte[] raw,byte[] clear)throws Exception{
		//生成一组扩展密钥，并放入一个数组之中
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		//用ENCRYPT_MODE模式，用skeySpec密码组，生成AES加密方法
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		//得到加密数据
		byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}
	
	//解密
	private static byte[] decrypt(byte[] raw,byte[] encrypted)throws Exception{
		//生成一组扩展密钥，并放入一个数组之中
		SecretKeySpec skeySpec = new SecretKeySpec(raw,"AES");
		Cipher cipher = Cipher.getInstance("AES");
		//用DECRYPT_MODE模式，用skeySpec密码组，生成AES解密方法
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		//得到解密数据
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}
	
	//对密钥进行编码
	private static byte[] getRawKey(byte[] seed)throws Exception{
		//获取密钥生成器
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr  = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(seed);
		//生成128为的AES密码生成器
		kgen.init(128,sr);
		//生成密钥
		SecretKey skey = kgen.generateKey();
		//编码格式
		byte[] raw = skey.getEncoded();
		return raw;
	}
	
	//将十进制数转换为十六进制
	public static String toHex(String txt){
		return toHex(txt.getBytes());
	}
	
	//将十六进制字符串转换为十进制字符串
	public static String fromHex(String hex){
		return new String(toByte(hex));
	}
	
	//将十六进制字符串转换为十进制字节数组
	public static byte[] toByte(String hexString){
		int len = hexString.length()/2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2*i,2*i+2),16).byteValue();
		}
		return result;
	}
	
	//把一个十进制字节数组转换成十六进制
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
