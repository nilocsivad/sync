<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.io.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Download File</title>
</head>
<body>
	<%
		String path = request.getParameter( "path" );
		File file = new File( path );
		
		response.reset();
		
		response.setContentType( "application/x-download" );
		response.addHeader( "Content-Disposition", "attachment;filename=" + URLEncoder.encode( file.getName(), "UTF-8" ) );
		response.addHeader( "Content-Length", file.length() + "" );
		
		BufferedInputStream input = new BufferedInputStream( new FileInputStream( file ) );
		BufferedOutputStream output = new BufferedOutputStream( response.getOutputStream() );
		
		byte[] buf = new byte[ 1024 * 1024 * 36 ];
		int len = 0;
		while ( ( len = input.read( buf ) ) > 0 ) {
			output.write( buf, 0, len );
		}
		
		input.close();
		output.flush();
		output.close();
	%>
</body>
</html>