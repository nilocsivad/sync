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

<title>File Uploader</title>

<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/sync.global.css" />
<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/sync.frame.css" />
<link type="text/css" rel="stylesheet" href="${URL }/static_r/style/upload.css" />
</head>
<body class="home-body">


	<div class="header">
		<h1 class="logo-text">
			<a href="${URL }/">File Uploader</a>
		</h1>
	</div>



	<div class="wrap-box" style="margin: 54px 0 40px">

		<div class="full-w-dom">
			<div class="container">

				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<h2>上传行为日志文件</h2>
				<p>&nbsp;</p>

			</div>
		</div>

		<div class="full-w-dom">
			<div class="container">

				<p>&nbsp;</p>

				<form id="upload-form" action="${URL }/file-sync/upload" method="post" enctype="multipart/form-data">
					<p>
						<label>选择文件:</label>
						<input type="file" name="f" />
					</p>
					<p>&nbsp;</p>
					<p>
						<label>选择文件:</label>
						<input type="file" name="f" />
					</p>
					<p>&nbsp;</p>
					<p>
						<label>选择文件:</label>
						<input type="file" name="f" />
					</p>
					<p>&nbsp;</p>
					<p>
						<label>选择文件:</label>
						<input type="file" name="f" />
					</p>
					<p>&nbsp;</p>
					<p>
						<label>选择文件:</label>
						<input type="file" name="f" />
					</p>
					<p>&nbsp;</p>
					<p>
						<label>选择文件:</label>
						<input type="file" name="f" />
					</p>
					<p>&nbsp;</p>
					<p>
						<label>选择文件:</label>
						<input type="file" name="f" />
					</p>
					<p>&nbsp;</p>
					<p>
						<label>选择文件:</label>
						<input type="file" name="f" />
					</p>
				</form>

				<p>&nbsp;</p>

			</div>
		</div>

		<div class="full-w-dom">
			<div class="container">

				<p>&nbsp;</p>
				<p>
					<a class="link-btn" href="javascript:doUp()">上传日志文件</a>
					<span style="clear: both"></span>
				</p>
				<span style="clear: both"></span>
				<p>&nbsp;</p>

			</div>
		</div>

	</div>



	<div class="footer">
		<p class="declare">RFID Server 2015</p>
	</div>

</body>
<script type="text/javascript" src="${URL }/static_r/component/JQuery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
	function doUp() {
		document.getElementById("upload-form").submit();
	}
</script>
</html>
