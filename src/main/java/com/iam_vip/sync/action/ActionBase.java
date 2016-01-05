/**
 * 
 */
package com.iam_vip.sync.action;

import javax.servlet.http.HttpServletRequest;

import com.iam_vip.sync.rs.C;

/**
 * @author Colin
 * 		
 */
public class ActionBase implements C {
	
	protected static final String REDIRECT = "redirect:";
	
	
	/**
	 * 
	 */
	public ActionBase() {}
	
	
	private String URL;
	
	
	protected String base_url( HttpServletRequest request ) {
		
		return this.base_url( request, "" );
	}
	
	protected String base_url( HttpServletRequest request, String action ) {
		
		return this.base_url( request, action, "" );
	}
	
	protected String base_url( HttpServletRequest request, String action, String method ) {
		
		if ( URL == null ) {
			int port = request.getServerPort();
			URL = request.getScheme() + "://" + request.getServerName() + ( port == 80 ? "" : ":" + port );
			String path = request.getContextPath();
			URL = URL + ( URL.endsWith( "/" ) ? "" : "/" ) + ( path.startsWith( "/" ) ? path.substring( 1 ) : path );
		}
		return URL + ( URL.endsWith( "/" ) ? "" : "/" ) + action + ( method == null || method.equals( "" ) ? "" : "/" + method );
	}
	
}
