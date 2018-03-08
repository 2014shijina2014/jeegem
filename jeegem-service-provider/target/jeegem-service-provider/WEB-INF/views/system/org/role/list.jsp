<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../common/includeBaseSet.jsp" %>
<%@include file="../../common/includeSystemSet.jsp" %>
<link rel="stylesheet" href="${jeegempath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script type="text/javascript" src="${jeegempath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${jeegempath}/static/plugins/zTree/3.5/jquery.ztree.excheck-3.5.js"></script>
</head>
<body>
	<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
				<div class="row">
					<div class="col-sm-3">
						<ul id="orgTree" class="ztree orgTree"></ul>
						<div class="dataTables_info customBtn">
							<a class="lrspace3" id="authOrgBtn" title="授权组织权限" href="#"><i class="icon-key color-dark-green bigger-240"></i></a>
							<a class="lrspace3" id="addOrgBtn" title="增加组织" href="#"><i class="icon-home color-green bigger-240"></i></a>
							<a class="lrspace3" id="editOrgBtn" title="修改组织" href="#"><i class="icon-edit color-blue bigger-240"></i></a>
							<a class="lrspace3" id="delOrgBtn" title="删除组织" href="#"><i class="icon-remove-sign color-red bigger-240"></i></a>
						</div>	
						
					</div>	
					<div class="col-sm-9">	
						<div class="row page-header">
							<h1>&nbsp;<span id='orgName1'></span>&nbsp;<small> <i class="icon-double-angle-right"></i> <span id='orgName2'></span> </small></h1>
						</div>
						<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
							<div class="widget-main customBtn">	
								<input  type="text"  name="keyWord" placeholder="这里输入关键词" class="input-large">
								&nbsp;&nbsp;<span id="selectisValid"><label></label>：<select  data-placeholder="状态" name="isValid" class="chosen-select isSelect75"></select></span>														
								&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>		
							</div>			
						<input type='hidden' class='pageNum' name='pageNum' value='1'/>
						<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
						<input type='hidden' name='orgId' />
						</form>
						<table id="baseTable" class="table table-striped table-bordered table-hover" >
							<thead>
								<tr>
									<th style="width:3%" class="center">
										<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
									</th>
									<th style="width:7%"  class="center hidden-480">序号</th>
									<th style="width:30%" class="center">角色名字</th>
									<th style="width:10%"  class="center hidden-480">状态</th>
									<th style="width:33%" class="center hidden-480">角色描述</th>
									<th style="width:20%" class="center">操作</th>
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
					</div>	
				</div>	
			<!-- #addorUpdateFrom -->
			<%@include file="form.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../../common/dialog.jsp" %>
			</div>
		</div>
	</div>
<script src="${jeegempath}/static/js/system/org/role.js"></script>
</body>
</html>