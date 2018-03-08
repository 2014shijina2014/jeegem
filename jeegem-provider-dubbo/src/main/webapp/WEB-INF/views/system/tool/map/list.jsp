<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html lang="en">
<head>

<%@include file="../../common/includeBaseSet.jsp"%>
<%@include file="../../common/includeSystemSet.jsp"%>
<style type="text/css">
#l-map{height:420px;width:100%;}
#r-result{width:100%;}
</style>
<!-- 百度js引入 -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=15&ak=40GWXiduhOft266lK4N1dopL"></script>
</head>
<body>
<div class="page-content">
	<div class="row">
		<div class="widget-main">
			<label>
				<span>当前位置：</span><span id="cityName" style="color: #2679b5;" >北京(默认)</span>
			</label>
			<select id="selectFun" onchange="selectFun(this)" >
				<option value="1" selected="selected">本地搜索</option>
				<option value="2">公交检索</option>
				<option value="3">驾车线路</option>
				<option value="4">步行规划</option>
				<option value="5">显示Gem</option>
			</select>			
			&nbsp;&nbsp;
			<span id="keyWordSpan"><input type="text"  id="keyWord" placeholder="这里输入关键词" class="input-large"></span>
			<span id="roadSpan" class="hide">
				<input type="text"  id="beginWord" placeholder="这里输入起点" class="input-large">
				<input type="text"  id="endWord" placeholder="这里输入终点" class="input-large">
			</span>	
			&nbsp;&nbsp;<button class="btn btn-warning  btn-xs" title="搜索" type="button" onclick="searchMap()"><i class="icon-search bigger-110 icon-only"></i></button>
		</div>
	</div>
		<div class="col-sm-3">
			<div id="r-result"></div>	
		</div>
		<div class="col-sm-9">
			<div id="l-map"></div>
		</div>
	<%@include file="../../common/dialog.jsp" %>
</div>
<script src="${jeegempath}/static/js/system/tool/map.js"></script>
</body>
</html>
