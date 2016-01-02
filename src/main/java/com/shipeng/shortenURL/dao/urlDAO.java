package com.shipeng.shortenURL.dao;

import java.util.List;

import com.shipeng.shortenURL.model.*;

public interface urlDAO {

	public long saveURL(final URL url);
	public long updateURL(URL url);
	public void delete(int urlId);
	public URL get(long urlId);
	public List<URL> list();
	
}
