package com.base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.util.Base64;

public class Base64Class {
	//将文件转成Base64字符串
	//path字符串参数为文件路径
	public static String encodeBase64File(String path)throws Exception{
		//用path参数创建新的文件
		File file = new File(path);
		//将文件读入数据流中
		FileInputStream inputFile = new FileInputStream(file);
		//新建一个和文件长度一样的buffer
		byte[] buffer = new byte[(int)file.length()];
		//从buffer中读取信息
		inputFile.read(buffer);
		//关闭数据流
		inputFile.close();
		//调用Android的转换函数
		return android.util.Base64.encodeToString(buffer,Base64.DEFAULT);
	}
	
	//将Base64字符解码保存文件
	public static void decoderBase64File(String base64Code,String targetPath)throws Exception{
		//调用Android的转换函数，将结果保存到baseByte中
		byte[] baseByte = android.util.Base64.decode(base64Code, Base64.DEFAULT);
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(baseByte);
		out.close();
	}
	
	//将Base64字符保存到文本文件
	public static void toFile(String base64Code,String targetPath)throws Exception{
		byte[] buffer = base64Code.getBytes();
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();
	}
}
