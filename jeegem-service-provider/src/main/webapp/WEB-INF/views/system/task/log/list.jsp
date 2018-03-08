<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../common/includeBaseSet.jsp" %>
<%@include file="../../common/includeSystemSet.jsp" %>
</head>
<body>
	<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
				<div class="row">
					<div class="widget-main">	
						<input  type="text"  name="keyWord" placeholder="这里输入关键词" class="input-large">												
						<input name="beginTime" value="" class="date-picker required" type="text" placeholder="开始日期" >	
						<input name="endTime" value="" class="date-picker" type="text" placeholder="结束日期" >		
						&nbsp;&nbsp;<span id="selectlogType"><label></label>：<select  data-placeholder="日志类型" name="type" class="chosen-select isSelect75"></select></span>		
						&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
					</div>
				</div>
				<input type='hidden' class='pageNum' name='pageNum' value='1'/>
				<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
				</form>
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th style="width:3%" class="center">
								<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
							</th>						
							<th style="width:5%" class="center hidden-480">序号</th>
							<th style="width:15%" class="center">任务对象</th>
							<th style="width:18%" class="center  hidden-480">任务类</th>
							<th style="width:7%" class="center ">日志类型</th>	
							<th style="width:30%" class="center hidden-480">任务类描述</th>	
							<th style="width:15%" class="center "><i class="icon-time bigger-110 hidden-480"></i>记录时间</th>		
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="row">
					<div class="col-sm-4">
						<div class="dataTables_info customBtn" >
							<c:forEach var="pbtn" items="${permitBtn}">
								<a href="#" title="${pbtn.name}" id="${pbtn.btnId}" class="lrspace3" ><i class='${pbtn.icon} bigger-220'></i></a>
							</c:forEach>
						</div>
				</div>
					<div class="col-sm-8">
						<!--设置分页位置-->
						<div id="pageing" class="dataTables_paginate paging_bootstrap">
							<ul class="pagination"></ul>
						</div>
					</div>
				</div>
			<!-- #dialog-confirm -->
			<%@include file="../../common/dialog.jsp" %>
			</div>
		</div>
	</div>
<script src="${jeegempath}/static/js/system/task/log.js"></script>
</body>
</html>