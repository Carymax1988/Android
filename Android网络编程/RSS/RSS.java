package com.rss;

public class RSS {
	//标题
	private String title;
	//链接
	private String link;
	//描述
	private String description;
	//发布日期
	private String pubDate;
	//参数为空的构造函数
	public RSS(){}
	//含有参数的构造函数
	public RSS(String title,String link,String description,String pubDate){
		super();
		this.title = title;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
}
