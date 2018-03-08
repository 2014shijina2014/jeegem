<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>
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
			<br/><br/><br/><br/><br/><br/>
			<div class="center" >
				<img src="<c:url value='/static/images/system/noAuthLockIcon.png'/>" />
				<hr/>
				<div class="space"></div>
				<h4 class="smaller">
				<span class="bigger-125"> <i class="icon-warning-sign" style="color:#fee188"></i>
					抱歉...当前页面您没有访问权限，请联系管理员
				</span>
				</h4>
			</div>
			<br/><br/><br/>
		</div>
	</div>
	<!-- PAGE CONTENT ENDS -->				
</body>
</html>