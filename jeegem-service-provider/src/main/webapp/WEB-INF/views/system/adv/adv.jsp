<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../common/includeBaseSet.jsp" %>
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
	<div class="center" style="margin:35px;" >
		<img id="advImg" src="" />
	<%-- 	<img src="<c:url value='/static/images/system/indexadv.png'/>" /> --%>
	</div>	
	<script type="text/javascript">
	if("ontouchend" in document) {
		//手机适应;
		document.write("<script src='"+jeegempath+"/static/js/jquery/jquery.mobile-1.4.5.min.js'><"+"/script>");
		$("#advImg").attr('src',gempath+'/static/images/system/indexadv_mob.png');
	}else{
		$("#advImg").attr('src',gempath+'/static/images/system/indexadv.png');
	}
	</script>
</body>
</html>