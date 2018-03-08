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
							<a class="lrspace3" id="addPosBtn" title="新增职务" href="#"><i class="icon-user-md color-green bigger-240"></i></a>
						</div>						
					</div>	
					<div class="col-sm-9">	
						<div id="rightOrgDiv" class="hide">
							<div class="row page-header">
								<h1>&nbsp;<span id='title1'></span><small>  <span id='title2'></span> </small></h1>
							</div>
							<form id="baseOrgForm" method="POST" onsubmit="return false;">
								<div class="widget-main customBtn">	
									<input  type="text"  name="keyWord" placeholder="这里输入关键词" class="input-large">																										
									&nbsp;&nbsp;<button class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getOrgList(1)"><i class="icon-search bigger-110 icon-only"></i></button>		
								</div>			
							<input type='hidden' class='pageNum' name='pageNum' value='1'/>
							<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
							<input type='hidden' name='orgId' />
							</form>
							<table id="baseOrgTable" class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
										<th style="width:3%" class="center">
											<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
										</th>
										<th style="width:7%"  class="center hidden-480">序号</th>
										<th style="width:20%" class="center">职务名字</th>
										<th style="width:20%" class="center">职务类别</th>
										<th style="width:33%" class="center hidden-480">职务描述</th>
										<th style="width:20%" class="center">操作</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
							<div class="row">
								<div class="col-sm-4"></div>
								<div class="col-sm-8">
									<!--设置分页位置-->
									<div id="orgpageing" class="dataTables_paginate paging_bootstrap">
										<ul class="pagination"></ul>
									</div>
								</div>
							</div>
						</div>
						<div id="rightPosDiv" class="hide">
						<div class="row page-header">
							<h1>&nbsp;<span id='title3'></span><small>  <span id='title4'></span> </small></h1>
						</div>
						<form id="basePosForm" class="form-inline" method="POST" onsubmit="return false;">
							<div class="widget-main customBtn">	
								<input  type="text"  name="keyWord" placeholder="这里输入关键词" class="input-large">												
								&nbsp;&nbsp;<button class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getPosList(1)"><i class="icon-search bigger-110 icon-only"></i></button>		
							</div>			
						<input type='hidden' class='pageNum' name='pageNum' value='1'/>
						<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
						<input type='hidden' name='posId' />
						</form>
						<table id="basePosTable" class="table table-striped table-bordered table-hover" >
							<thead>
								<tr>
									<th style="width:3%" class="center">
										<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
									</th>
									<th style="width:7%"  class="center hidden-480">序号</th>
									<th style="width:25%" class="center hidden-480">人员登录名</th>
									<th style="width:25%" class="center ">人员名字</th>
									<th style="width:25%" class="center ">人员角色</th>
									<th style="width:10%" class="center">操作</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
						<div class="row">
							<div class="col-sm-4">
								<div class="dataTables_info customBtn">					
									<a class="lrspace3" id="arrangeAccBtn" title="安排人员" href="#"><i class="icon-check color-pale-orange bigger-220"></i></a>					
								</div>
							</div>
							<div class="col-sm-8">
								<!--设置分页位置-->
								<div id="pospageing" class="dataTables_paginate paging_bootstrap">
									<ul class="pagination"></ul>
								</div>
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
<script src="${jeegempath}/static/js/system/org/position.js"></script>
</body>
</html>