package com.sp.mailru.entities;

public class InboxMailListItem {
	
	private String title;
	
	private String from;
	
	private String url;
	
	public InboxMailListItem(String title, String from, String url) {
		this.title = title;
		this.from = from;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

