<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../../common/includeBaseSet.jsp" %>
<%@include file="../../../common/includeSystemSet.jsp" %>
</head>
<body>
	<div class="page-content">
	<%@include file="../../../common/dialog.jsp" %>
		<div class="page-header">
			<h1>
				请假申请表 <small> <i class="icon-double-angle-right"></i>如果是调休,一天按照8小时计算</small>
			</h1>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<form id='leaveFrom' class="form-horizontal" method="POST" onsubmit="return false;">
					<div class="form-group">
						<label class="col-sm-2 control-label no-padding-right"><font color="red">*</font>部门</label>
						<div class="col-sm-2">
							<input type="text" name="org" jeegemValidate="required"  maxlength="32" class="col-xs-12 col-sm-12" >
						</div>	
						<label class="col-sm-1 control-label no-padding-right"><font color="red">*</font>申请人</label>
						<div class="col-sm-2">
							<input type="text" name="name" jeegemValidate="required" maxlength="16" class="col-xs-12 col-sm-12" >
						</div>	
						<label class="col-sm-1 control-label no-padding-right"><font color="red">*</font>审批人</label>
						<div class="col-sm-2">
							<input type="text" name="approver" jeegemValidate="required"  maxlength="16" class="col-xs-12 col-sm-12"  >
						</div>	
					</div>
					<div class="space-4"></div>
					<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"><font color="red">*</font>请假类型</label>
						<div class="col-sm-1">
						<span id="selectLeaveType"><select name="type" class="chosen-select isSelect85"></select></span>		
						</div>	
						<label class="col-sm-2 control-label no-padding-right"><font color="red">*</font>开始时间</label>
						<div class="col-sm-1">
								<input name="beginTime" value="" jeegemValidate="required,datetime" class="date-picker" type="text" placeholder="请假开始时间" >					
						</div>
						<label class="col-sm-2 control-label no-padding-right"><font color="red">*</font>结束时间</label>
						<div class="col-sm-1">
							<input name="endTime" value="" jeegemValidate="required,datetime" class="date-picker" type="text" placeholder="请假结束时间" >	
						</div>	
					</div>				
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label no-padding-right">请假事由</label>
						<div class="col-sm-5">
							<textarea  rows="2" cols="10" maxlength="128" style="width: 100%" name="description" multiline="true" class="FormElement ui-widget-content ui-corner-all "></textarea>
						</div>	
					</div>
					<div class="space-4"></div>
					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button type="button" class="btn btn-info" onclick="submitApply()">
								<i class="icon-ok bigger-110"></i> 提交
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="${jeegempath}/static/js/system/workflow/online/apply.js"></script>		
</body>
</html>