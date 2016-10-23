<%@page import="com.iam_vip.sync.rs.u.PathUtil"%>
<%@page import="com.iam_vip.sync.rs.FileComparator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.io.File"%>
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

<title>File Looker</title>

<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/sync.global.css" />
<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/sync.frame.css" />
<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/look-server.css" />
<style type="text/css">
.link-p a {
	display: block;
	width: 100%;
}
</style>
</head>
<body class="home-body">


	<div class="header">
		<h1 class="logo-text">
			<a href="${URL }/">File Looker</a>
		</h1>
	</div>


	<%
		String path = request.getParameter("path");
		if (path == null || "".equals(path)) {
			path = "";
		} else {
			path = path.endsWith(":") ? path + File.separator : path;
		}
	%>


	<div class="wrap-box" style="margin: 54px 0 40px">

		<div class="full-w-dom">
			<div class="container">

				<p>&nbsp;</p>

				<form action="look-server.jsp" style="margin: 0; padding: 0">
					<p style="margin: 0; padding: 0; height: 40px; line-height: 40px;">
						<input class="zz-input" placeholder="服务器磁盘路径" name="path" value="<%=path%>" type="text" style="width: 75%;" />
						<input class="link-btn" style="width: 20%;" type="submit" value="Go to view" />
					</p>
					<span style="clear: both"></span>
				</form>

				<p>&nbsp;</p>

			</div>
		</div>

		<%
			if (!"".equals(path)) { // <path-not-null>

				File f = new File(path);
				File[] fs = null;

				if (f.isDirectory()) {

					File[] tmp = f.listFiles();
					Arrays.sort(tmp, new FileComparator());

					if (!f.getAbsolutePath().endsWith(":\\")) {

						fs = new File[tmp.length + 1];
						fs[0] = f.getParentFile();

						for (int i = 0, l = tmp.length; i < l; ++i) {
							fs[i + 1] = tmp[i];
						}
					} else {
						fs = tmp;
					}
				}
		%>
		<div class="full-w-dom">
			<div class="container">
				<%
					for (File file : fs) {
				%>
				<p class="link-p" style="margin: 10px 0">
					<%
						if (file.isDirectory()) {
					%>

					<a href="${URL }/look-server.jsp?path=<%=URLEncoder.encode( file.getAbsolutePath(  ), "UTF-8" ) %>" title="<%=file.getAbsolutePath()%>"><%=file.getName()%></a>
					<%
						} else {
					%>
					<a href="<%=PathUtil.getPath(request, file.getAbsolutePath())%>" title="<%=file.getAbsolutePath()%>"><%=file.getName()%></a>
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

	<p id="emp-p" style="height: 0px"></p>

	<div class="footer">
		<p class="declare">Client &amp; Server File Transfer 2016</p>
	</div>

</body>
<script type="text/javascript" src="${URL }/static_r/component/JQuery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
	function download() {
		window.alert($(this).html())
		//window.alert(path)
	}
</script>
<script type="text/javascript">
	window
			.setTimeout(
					function() {
						//判断访问终端
						var browser = {
							versions : function() {
								var u = navigator.userAgent, app = navigator.appVersion;
								return {
									trident : u.indexOf('Trident') > -1, //IE内核
									presto : u.indexOf('Presto') > -1, //opera内核
									webKit : u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
									gecko : u.indexOf('Gecko') > -1
											&& u.indexOf('KHTML') == -1,//火狐内核
									mobile : !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
									ios : !!u
											.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
									android : u.indexOf('Android') > -1
											|| u.indexOf('Adr') > -1, //android终端
									iPhone : u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
									iPad : u.indexOf('iPad') > -1, //是否iPad
									webApp : u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
									weixin : u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
									qq : u.match(/\sQQ/i) == " qq" //是否QQ
								};
							}(),
							language : (navigator.browserLanguage || navigator.language)
									.toLowerCase()
						};

						//判断是否移动端
						if (browser.versions.mobile || browser.versions.android
								|| browser.versions.ios) {
							var h = $(".footer").height();
							$("#emp-p").height(h / 2);

							$(".full-w-dom").find("p").find("a").css(
									"font-size", "22px");
						}
					}, 10);
</script>
</html>
