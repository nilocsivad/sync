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

<title>File Transfer</title>

<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/sync.global.css" />
<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/sync.frame.css" />
<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/index.css" />
</head>
<body class="home-body">


	<div class="header">
		<h1 class="logo-text">
			<a href="${URL }/">File Transfer</a>
		</h1>
	</div>



	<div class="wrap-box" style="margin: 54px 0 40px">

		<div class="full-w-dom">
			<div class="container">

				<p>&nbsp;</p>

				<p>
					<a class="link-btn" href="${URL }/upload.jsp">上传</a> <a class="link-btn" href="${URL }/look-server.jsp">浏览</a>
				</p>

				<p>&nbsp;</p>

			</div>
		</div>
	</div>



	<div class="footer">
		<p class="declare">Client &amp; Server File Transfer 2016</p>
	</div>

</body>
<script type="text/javascript" src="${URL }/static_r/component/JQuery/1.11.2/jquery.min.js"></script>
</html>
