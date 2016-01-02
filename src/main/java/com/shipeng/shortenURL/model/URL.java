package com.shipeng.shortenURL.model;

public class URL {

	private long id;
	private String longURL;
	private String shortURL;

	public URL() {
	}

	public URL(long id, String longURL) {
		this.id       = id;
		this.longURL  = longURL;
	}

	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getShortURL() {
		return this.shortURL;
	}
	
	public void setShortURL(String value) {
		this.shortURL = value;
	}

	public String getLongURL() {
		return this.longURL;
	}

	public void setLongURL(String value) {
		this.longURL = value;
	}

}
