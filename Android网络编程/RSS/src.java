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
				//创建SAXParserFactory实例
				SAXParserFactory factory = SAXParserFactory.newInstance();
				//新建SAXParser解析类
				SAXParser parser = factory.newSAXParser();
				//创建XMLReader读取类
				XMLReader reader = parser.getXMLReader();
				//为reader设置处理器
				reader.setContentHandler(rssHandler);
				//读取需要处理的数据
				reader.parse(new InputSource(new StringReader(result)));
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//rssList中存放的就是解析后的数组
			
		}
	}
}
