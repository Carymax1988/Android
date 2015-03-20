package com.rss;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler extends DefaultHandler {
	//������������ַ���
	private String XMLParseName;
	//����RSS�Ķ����б�
	private List<RSS>RSSList;
	//����RSS����
	private RSS rss;
	//���幹�캯��
	public RSSHandler(List<RSS> NewsList){
			this.RSSList = NewsList;
	}
	
	//����Ԫ�ص��ַ����ݣ��Ӳ����п��Ի������
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		String value = new String(ch,start,length);
		if(XMLParseName.equals("title")){
			rss.setTitle(value);
		}else if(XMLParseName.equals("link")){
			rss.setLink(value);
		}else if(XMLParseName.equals("description")){
			rss.setDescription(value);
		}else if(XMLParseName.equals("pubDate")){
			rss.setPubDate(value);
		}
	}
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(localName.equals("title")){
			//��⵽��ʼ�����½�RSS
			rss = new RSS();
		}
		XMLParseName = localName;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if(localName.equals("item")){
			RSSList.add(rss);
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
}
