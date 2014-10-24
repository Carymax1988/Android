package com.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserHelper extends DefaultHandler {

	private static final String TAG = "SAXParserHelper";
	
	List<Link>Links;
	
	Link link;
	
	int currentState = 0;
	
	public List<Link> getLinks() {
		return Links;
	}
	
	public void characters(char[] ch,int start,int length)throws SAXException{
		String theString = String.valueOf(ch,start,length);
		if(currentState!=0){
			link.setText(theString);
			currentState = 0;
		}
		return;
	}
	
	public void endDocument()throws SAXException{
		super.endDocument();
	}
	
	public void endElement(String uri,String localName,String qName)throws SAXException{
		if(localName.equals("a")){
			Links.add(link);
		}
	}
	
	@Override
	public void startDocument() throws SAXException {
		Links = new ArrayList<Link>();
	}
	
	public void startElement(String uri,String localName,String qName,Attributes attributes)throws SAXException{
		link = new Link();
		if(localName.equals("a")){
			for(int i=0,len=attributes.getLength();i<len;i++){
				if(attributes.getLocalName(i).equals("href")){
					link.setHref(attributes.getValue(i));
				}else if(attributes.getLocalName(i).equals("name")){
					link.setName(attributes.getValue(i));
				}
			}
			currentState = 1;
			return;
		}
		currentState = 0;
		return;
	}
}
