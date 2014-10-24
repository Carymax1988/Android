package com.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;

public class DomXmlParser {
	
	private static Context context;
	
	private static final String CITY_NAME = "name";
	private static final String CITY_ID = "id";
	private static final String CITY_CODE = "code";
	
	public DomXmlParser(Context context) {
		DomXmlParser.context = context;
	}

	public static List<City> parseXml(){
		List<City> Citys = new ArrayList<City>();
		DocumentBuilder builder;
		DocumentBuilderFactory factory = null;
		Document document = null;
		InputStream inputStream = null;
		
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
			inputStream = context.getResources().getAssets().open("city.xml");
			document = builder.parse(inputStream);
			Element root = document.getDocumentElement();
			NodeList nodes = root.getElementsByTagName("city");
			City city = null;
			for(int i=0,len=nodes.getLength();i<len;i++){
				city = new City();
				
				Element cityElement = (Element) nodes.item(i);
				city.setId(cityElement.getAttribute(CITY_ID));
				
				Element cityNameElement = (Element) cityElement.getElementsByTagName(CITY_NAME).item(0);
				city.setName(cityNameElement.getFirstChild().getNodeValue());
				
				Element cityCodeElement = (Element) cityElement.getElementsByTagName(CITY_CODE).item(0);
				city.setCode(cityCodeElement.getFirstChild().getNodeValue());
				
				Citys.add(city);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Citys;
	}

}
