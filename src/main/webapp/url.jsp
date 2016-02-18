<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%!String URL = null;
	
	
	String base_url() {
		
		return this.base_url( "" );
	}
	
	String base_url( String action ) {
		
		return this.base_url( action, "" );
	}
	
	String base_url( String action, String method ) {
		
		return URL + ( URL.endsWith( "/" ) ? "" : "/" ) + action + ( method == null || method.equals( "" ) ? "" : "/" + method );
	}%>
<%
	URL = request.getAttribute( "URL" ) == null ? null : request.getAttribute( "URL" ) + "";
	if ( URL == null ) {
		
		// 		int port = request.getServerPort();
		// 		URL = request.getScheme() + "://" + request.getServerName() + ( port == 80 ? "" : ":" + port );
		// 		URL = URL.endsWith( "/" ) ? URL.substring( 0, URL.length() - 1 ) : URL;
		String path = request.getContextPath();
		URL = /* URL + */ path;
		
		request.setAttribute( "URL", URL );
	}
%>