<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../common/includeBaseSet.jsp" %>
<%@include file="../common/includeSystemSet.jsp" %>
<link rel="stylesheet" href="${jeegempath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${jeegempath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
	<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
				<div class="widget-header widget-header-large">				
					<div class="widget-main customBtn">	
						<button title="选择显示层级" value='1' class="btn btn-xs btn-success"><i class="icon-desktop"></i></button>					
						<button title="选择显示层级" value='2' class="btn btn-xs"><i class="icon-th"></i></button>		
						<button title="选择显示层级" value='3' class="btn btn-xs"><i class="icon-user"></i></button>		
						<button title="选择显示层级" value='4' class="btn btn-xs"><i class="icon-bitbucket"></i></button>
						<input type="hidden" name="layer" value="1" >
					</div>
					<div class="widget-toolbar customBtn">	
						<c:forEach var="pbtn" items="${permitBtn}">
							<a href="#" title="${pbtn.name}" id="${pbtn.btnId}" class="lrspace3" ><i class='${pbtn.icon} bigger-180'></i></a>
						</c:forEach>
						<a href="#" title="刷新" id="searchBtn" onclick="getbaseList()" class="lrspace3" ><i class='icon-refresh bigger-180 orange'></i></a>		
					</div>		
				</div>
				</form>
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th style="width:3%" class="center">
								<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
							</th>
							<th style="width:5%" class="center hidden-480">序号</th>
							<th style="width:35%" class="center">资源名称</th>
							<th style="width:25%" class='center hidden-480'>资源路径</th>		
							<th style="width:4%"  class='center hidden-480'>状态</th>
							<th style="width:18%" class='center hidden-480'>资源描述</th>
							<th style="width:13%" class="center">操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="row">
					<div class="col-sm-4">
						<div class="dataTables_info customBtn"></div>
					</div>
					<div class="col-sm-8"></div>
				</div>
			<!-- #addorUpdateFrom -->
			<%@include file="form.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../common/dialog.jsp" %>
			</div>
		</div>
	</div>
<script src="${jeegempath}/static/js/system/resources/resources.js"></script>
</body>
</html>