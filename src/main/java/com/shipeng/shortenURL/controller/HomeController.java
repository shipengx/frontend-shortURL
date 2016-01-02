package com.shipeng.shortenURL.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shipeng.shortenURL.dao.urlDAO;
import com.shipeng.shortenURL.model.URL;
import com.shipeng.shortenURL.utils.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class HomeController {
	
	@Autowired
	private urlDAO urlDAO;
	
	@Autowired
	private Helper helper;
	
	
	@RequestMapping(value="/")
	public ModelAndView listContact(ModelAndView model) throws IOException{
		List<URL> listURL = urlDAO.list();
		for (URL url : listURL){
			url.setShortURL("localhost:8080/shortenURL/redirect/" + helper.idToShortURL(url.getId()));
		}
		model.addObject("listURL", listURL);
		model.setViewName("home");  
		return model;
	}
	
	@RequestMapping(value="/redirect/{shortURL}", method = RequestMethod.GET)
	public RedirectView redirectToLongURL(@PathVariable String shortURL) {
		System.out.println("shortURL: " + shortURL);
		long id = helper.shortURLToId(shortURL);
		URL url = urlDAO.get(id);
		
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl("http://" + url.getLongURL());
	    return redirectView;
	}
	
	
	@RequestMapping(value = "/newURL", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		URL newURL = new URL();
		model.addObject("url", newURL);
		model.setViewName("urlForm");
		return model; 
	}
	
	
	@RequestMapping(value = "/saveURL", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute URL url) {
		long inserted_id = urlDAO.saveURL(url);
		url.setShortURL(helper.idToShortURL(inserted_id));
		return new ModelAndView("redirect:/");
	}
	
	
	@RequestMapping(value = "/deleteURL", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		urlDAO.delete(contactId);
		return new ModelAndView("redirect:/");
	}
	
	
	@RequestMapping(value = "/editURL", method = RequestMethod.GET)
	public ModelAndView editURL(HttpServletRequest request) {
		int urlId = Integer.parseInt(request.getParameter("id"));
		URL url   = urlDAO.get(urlId);
		ModelAndView model = new ModelAndView("urlForm");
		model.addObject("url", url);
		
		return model;
	}
	
}
