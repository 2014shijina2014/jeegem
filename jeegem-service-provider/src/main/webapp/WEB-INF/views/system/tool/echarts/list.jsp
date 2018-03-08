<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../common/includeBaseSet.jsp"%>
<%@include file="../../common/includeSystemSet.jsp"%>
<!-- echarts图表引入js -->
<script type="text/javascript" src="${jeegempath}/static/plugins/echarts/2.2.7/echarts.js"></script>
</head>
<body>
	<div class="page-content">
		<div class="row" >
			<div class="col-xs-12 col-sm-10" >
				<button class="btn btn-primary" onclick="changeEchart('折线图','line');" >折线图</button>
				<button class="btn btn-primary" onclick="changeEchart('柱形图','bar');" >柱形图</button>
				<button class="btn btn-primary" onclick="changeEchart('K线图','k');" >K线图</button>
				<button class="btn btn-primary" onclick="changeEchart('饼图','pie');" >饼图</button>
				<button class="btn btn-primary" onclick="changeEchart('雷达图','radar');" >雷达图 </button>
				<button class="btn btn-primary" onclick="changeEchart('地图','map');" >地图</button>
				<!-- <button class="btn btn-primary" onclick="changeEchart('和弦图','chord');" >和弦图</button> -->
				<!-- <button class="btn btn-primary" onclick="changeEchart('力导向图','force');" >力导向图</button> -->
				<!-- <button class="btn btn-primary" onclick="changeEchart('热力图','heatmap');" >热力图</button> -->
				<button class="btn btn-primary" onclick="changeEchart('事件河流图','eventRiver');" >事件河流图</button>
				<button class="btn btn-primary" onclick="changeEchart('散点图','scatter');" >散点图</button>
				<button class="btn btn-primary" onclick="changeEchart('韦恩图','venn');" >韦恩图</button>
				<button class="btn btn-primary" onclick="changeEchart('仪表图','gauge');" >仪表图</button>
				<button class="btn btn-primary" onclick="changeEchart('漏斗图','funnel');" >漏斗图</button>
		   </div>	
			  <div class="col-xs-12 col-sm-2" >
			  <label>主题：</label>
				 <select  name="theme-select">
	                    <option selected="true" name='infographic'>infographic</option>
	                    <option name='macarons'>macarons</option>
	                    <option name='shine'>shine</option>
	                    <option name='dark'>dark</option>
	                    <option name='blue'>blue</option>
	                    <option name='green'>green</option>
	                    <option name='red'>red</option>
	                    <option name='gray'>gray</option>
	                    <option name="helianthus">helianthus</option>
	                    <option name="roma">roma</option>
	                    <option name="mint">mint</option>
	                    <option name="macarons2">macarons2</option>
	                    <option name="sakura">sakura</option>
	                    <option name='default'>default</option>
	                </select>
	        </div>
		</div>
		<div class="row" >
			<div class="col-xs-12" >
				<h2 id='titleDiv' class="header smaller lighter green" >折线图</h2>
			</div>
		</div>
		<div class="row" >
			<div class="col-xs-12">
			 <div id="main" style="height:350px"></div>
			</div>
		</div>
		<%@include file="../../common/dialog.jsp" %>
	</div>
	<script src="${jeegempath}/static/js/system/tool/echarts.js"></script>

</body>
</html>