package com.rss;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class src {
	
	private List<RSS> rssList;
	private RSSHandler rssHandler;
	
	public void ParserXML(String result){
		rssList = new ArrayList<RSS>(100);
		rssHandler = new RSSHandler(rssList);
		if(result!=null){
			try {
				//����SAXParserFactoryʵ��
				SAXParserFactory factory = SAXParserFactory.newInstance();
				//�½�SAXParser������
				SAXParser parser = factory.newSAXParser();
				//����XMLReader��ȡ��
				XMLReader reader = parser.getXMLReader();
				//Ϊreader���ô�����
				reader.setContentHandler(rssHandler);
				//��ȡ��Ҫ���������
				reader.parse(new InputSource(new StringReader(result)));
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//rssList�д�ŵľ��ǽ����������
			
		}
	}
}
