package com.base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.util.Base64;

public class Base64Class {
	//���ļ�ת��Base64�ַ���
	//path�ַ�������Ϊ�ļ�·��
	public static String encodeBase64File(String path)throws Exception{
		//��path���������µ��ļ�
		File file = new File(path);
		//���ļ�������������
		FileInputStream inputFile = new FileInputStream(file);
		//�½�һ�����ļ�����һ����buffer
		byte[] buffer = new byte[(int)file.length()];
		//��buffer�ж�ȡ��Ϣ
		inputFile.read(buffer);
		//�ر�������
		inputFile.close();
		//����Android��ת������
		return android.util.Base64.encodeToString(buffer,Base64.DEFAULT);
	}
	
	//��Base64�ַ����뱣���ļ�
	public static void decoderBase64File(String base64Code,String targetPath)throws Exception{
		//����Android��ת����������������浽baseByte��
		byte[] baseByte = android.util.Base64.decode(base64Code, Base64.DEFAULT);
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(baseByte);
		out.close();
	}
	
	//��Base64�ַ����浽�ı��ļ�
	public static void toFile(String base64Code,String targetPath)throws Exception{
		byte[] buffer = base64Code.getBytes();
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();
	}
}
