package com.xml;

public class Link {
	private String href;
	private String text;
	private String name;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Link[href="+href+",text="+text+",name="+name+"]";
	}
}
