package com.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;

public class SAXXmlParser {
	Context context;
	public SAXXmlParser(Context context) {
		this.context = context;
	}
	
	private void parseXml(){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		SAXParserHelper helperHandler = null;
		try {
			parser = factory.newSAXParser();
			XMLReader xmlReader = parser.getXMLReader();
			helperHandler = new SAXParserHelper();
			xmlReader.setContentHandler(helperHandler);
			InputStream stream = context.getResources().getAssets().open("link.xml");
			InputSource is = new InputSource(stream);
			xmlReader.parse(is);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		helperHandler.getLinks();
	}
}
