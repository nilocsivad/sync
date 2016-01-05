<%@page import="java.util.Arrays"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/url.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<!--[if IE]>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<![endif]-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Client &amp; Server File Transfer</title>

<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/sync.global.css" />
<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/sync.frame.css" />
<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/look-server.css" />
</head>
<body class="home-body">


	<div class="header">
		<h1 class="logo-text">
			<a href="${URL }/">Client &amp; Server File Transfer</a>
		</h1>
	</div>


	<%
		String path = request.getParameter( "path" );
		if ( path == null || "".equals( path ) ) {
			path = "";
		}
		else {
			path = path.endsWith( ":" ) ? path + File.separator : path;
		}
	%>


	<div class="wrap-box" style="margin: 54px 0 40px">

		<div class="full-w-dom">
			<div class="container">

				<p>&nbsp;</p>

				<form action="look-server.jsp" style="margin: 0; padding: 0">
					<p style="margin: 0; padding: 0; height: 40px; line-height: 40px;">
						<input class="zz-input" placeholder="服务器磁盘路径" name="path" value="<%=path%>" type="text" style="width: 75%;" /> <input class="link-btn" style="width: 20%;" type="submit" value="Go to view" />
					</p>
					<span style="clear: both"></span>
				</form>

				<p>&nbsp;</p>

			</div>
		</div>

		<%
			if ( !"".equals( path ) ) { // <path-not-null>
				
				File f = new File( path );
				File[] fs = null;
				
				if ( f.isDirectory() ) {
					
					File[] tmp = f.listFiles();
					
					if ( !f.getAbsolutePath().endsWith( ":\\" ) ) {
						
						fs = new File[ tmp.length + 1 ];
						fs[ 0 ] = f.getParentFile();
						
						for ( int i = 0, l = tmp.length; i < l; ++ i ) {
							fs[ i + 1 ] = tmp[ i ];
						}
					}
					else {
						fs = tmp;
					}
				}
		%>
		<div class="full-w-dom">
			<div class="container">
				<%
					for ( File file : fs ) {
				%>
				<p style="margin: 6px 0">
					<%
						if ( file.isDirectory() ) {
					%>

					<a href="${URL }/look-server.jsp?path=<%=URLEncoder.encode( file.getAbsolutePath(  ), "UTF-8" ) %>" title="<%=file.getAbsolutePath()%>"><%=file.getAbsolutePath()%></a>
					<%
						}
								else {
					%>
					<a href="${URL }/download.jsp?path=<%=URLEncoder.encode( file.getAbsolutePath(  ), "UTF-8" ) %>" title="<%=file.getAbsolutePath()%>"><%=file.getAbsolutePath()%></a>
					<%
						}
					%>
				</p>
				<%
					} // end for
				%>
			</div>
		</div>
		<%
			} // </path-not-null>
		%>

	</div>



	<div class="footer">
		<p class="declare">Client &amp; Server File Transfer 2016</p>
	</div>

</body>
<script type="text/javascript" src="${URL }/static_r/component/JQuery/1.11.2/jquery.min.js"></script>
</html>
