package com.xml;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class PULLXmlParser {
	private void parserXML(){
		XmlPullParserFactory factory;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			xpp.setInput(new StringReader("<poem lang=\"chinese\">"
					+ "<title>静夜思</title>"
					+ "<author>李白</author>"
					+ "<content>床前明月光，疑是地上霜。举头望明月，低头思故乡。</content>"
					+ "</poem>"));
			int eventType = xpp.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT){
				if(eventType == XmlPullParser.START_DOCUMENT){
					Log.d("TAG", "Start document");
				}else if(eventType == XmlPullParser.START_TAG){
					Log.d("TAG", "Start"+xpp.getName());
				}else if(eventType == XmlPullParser.END_TAG){
					Log.d("TAG", "Endtag"+xpp.getName());
				}else if(eventType == XmlPullParser.TEXT){
					Log.d("TAG", "Text"+xpp.getText());
				}
				eventType = xpp.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.d("TAG", "End document");
	}
}
