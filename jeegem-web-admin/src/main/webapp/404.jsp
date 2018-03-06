<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>
<title>GemSystem</title>
<link rel="stylesheet" href="<c:url value='/static/css/system/bootstrap/bootstrap.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/css/system/ace/font-awesome.min.css'/>" />
<!--[if IE 7]>
		  <link rel="stylesheet" href="<c:url value='/static/css/system/ace/font-awesome-ie7.min.css' />" />
<![endif]-->
<link rel="stylesheet" href="<c:url value='/static/css/system/ace/ace.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/css/system/ace/ace-rtl.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/css/system/ace/ace-skins.min.css'/>" />
</head>
<body style="background-color:#FFFFFF;">
	<div class="error-container">
		<div class="well">
			<h1 class="grey lighter smaller">
				<span class="bigger-125"> <i class="icon-warning-sign icon-animated-wrench" style="color:#fee188"></i>
					404
				</span>Error
			</h1>
			<hr><!--  -->
			<h3 class="lighter smaller"></h3>
			<div>
				<div class="space"></div>
				<h3 class="smaller">我们到处找，但找不到！最可能的原因是:</h3>
				<ul class="list-unstyled spaced inline bigger-110 margin-15" style="font-family:'Open Sans';">
					<li><i class="icon-hand-right blue icon-animated-wrench"></i> 
					可能我们的页面和系统正在升级或者维护</li>
					<li><i class="icon-hand-right blue icon-animated-wrench"></i> 
					可能该网页已经被删除</li>
					<li><i class="icon-hand-right blue icon-animated-wrench"></i> 
					可能地址是错误的</li>
					
				</ul>
			</div>
			<hr>
			<div class="space"></div>
			<div class="center">
				<a class="btn btn-grey" href="javascript:location.reload();"> <i class="icon-refresh"></i>
					刷新页面
				</a>
			</div>
		</div>
	</div>		
</body>
</html>