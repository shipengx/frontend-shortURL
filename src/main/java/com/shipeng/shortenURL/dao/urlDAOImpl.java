package com.shipeng.shortenURL.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.shipeng.shortenURL.model.URL;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class urlDAOImpl implements urlDAO{
	private JdbcTemplate jdbcTemplate;
	 
    public urlDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
    
    public long saveURL(final URL url) {
    	if (url.getId() > 0) {
    		//update url
    		return updateURL(url);
    	}else {
    		//insert 
    		final String sql = "INSERT INTO URL (longURL) " + " VALUES (?) ";
    		
    		KeyHolder keyHolder = new GeneratedKeyHolder();
        	jdbcTemplate.update(
        	    new PreparedStatementCreator() {
        	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        	            PreparedStatement pst =
        	                con.prepareStatement(sql, new String[] {"id"});
        	            pst.setString(1, url.getLongURL().replace("http://","").replace("https://",""));
        	            return pst;
        	        }
        	    },
        	    keyHolder);
        	return (Long)keyHolder.getKey();
    	}
    }
    
    public long updateURL(URL url) {
    	//update
    	String sql = "UPDATE URL SET longURL=? WHERE id=?";
		jdbcTemplate.update(sql, url.getLongURL(), url.getId());
		return url.getId();
    }
 
    public void delete(int urlId) {
        // implementation details goes here...
    	String sql = "DELETE FROM URL WHERE id=?";
        jdbcTemplate.update(sql, urlId);
    }
 
    public URL get(long urlId) {
        // implementation details goes here...
    	String sql = "SELECT * FROM URL WHERE id=" + urlId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<URL>() {
     
            public URL extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    URL URL = new URL();
                    URL.setId(rs.getInt("id"));
                    URL.setLongURL(rs.getString("longURL"));
                    return URL;
                }
     
                return null;
            }
     
        });
    }
    
    public List<URL> list() {
        // implementation details goes here...
    	String sql = "SELECT * FROM URL";
        List<URL> listURL = jdbcTemplate.query(sql, new RowMapper<URL>() {
     
            public URL mapRow(ResultSet rs, int rowNum) throws SQLException {
                URL aURL = new URL();
     
                aURL.setId(rs.getInt("id"));
                aURL.setLongURL(rs.getString("longURL"));
     
                return aURL;
            }
     
        });
     
        return listURL;
    }
    
}
