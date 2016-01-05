/**
 * 
 */
package com.iam_vip.sync.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Colin
 */
@Controller
public class ActionRedirect extends ActionBase {
	
	private static final String REDIRECT_FACADE = "/u/home/index", REDIRECT_BACKEND = "/m/index";
	
	
	/**
	 * 
	 */
	public ActionRedirect() {}
	
	@RequestMapping( method = RequestMethod.GET, value = { "facade" } )
	public ModelAndView facade( HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws IOException {
		
		return new ModelAndView( "redirect:" + REDIRECT_FACADE, model );
	}
	
	@RequestMapping( method = RequestMethod.GET, value = { "backend", "back", "manage", "guanli" } )
	public ModelAndView backend( HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws IOException {
		
		return new ModelAndView( "redirect:" + REDIRECT_BACKEND, model );
	}
	
}
