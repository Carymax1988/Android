package com.rss;

public class RSS {
	//����
	private String title;
	//����
	private String link;
	//����
	private String description;
	//��������
	private String pubDate;
	//����Ϊ�յĹ��캯��
	public RSS(){}
	//���в����Ĺ��캯��
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
