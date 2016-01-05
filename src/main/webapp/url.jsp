<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%!
	String URL = "";
	String base_url() {
		return this.base_url( "" );
	}
	
	String base_url( String action ) {
		return this.base_url( action, "" );
	}
	
	String base_url( String action, String method ) {
		return URL + ( URL.endsWith( "/" ) ? "" : "/" ) + action + ( method == null || method.equals( "" ) ? "" : "/" + method );
	}
%>
<%
	if ( "".equals( URL ) ) {
		int port = request.getServerPort();
		URL = request.getScheme() + "://" + request.getServerName() + ( port == 80 ? "" : ":" + port );
		String path = request.getContextPath();
		URL = URL + ( URL.endsWith( "/" ) ? "" : "/" ) + ( path.startsWith( "/" ) ? path.substring( 1 ) : path );
	}
	request.setAttribute( "URL", URL );
%>