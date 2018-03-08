<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../common/includeBaseSet.jsp"%>
<%@include file="../../common/includeSystemSet.jsp"%>
</head>
<body>
	<div class="page-content">
		<div class="row-flud">
			<div class="col-xs-12">
				<div class="widget-box">
					<div class="widget-header widget-header-flat">
						<h4>服务器接口测试</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main">
							<div class="row">
								<div class="col-xs-12">
									<form id="interfaceForm" class="form-horizontal" method="POST" onsubmit="return false;">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right">接口类型：</label>
											<div class="col-sm-9">
												<input class="ace" type="radio" name="reqMethod" value="POST" checked="checked" ><span class="lbl"> POST</span>
												<input class="ace" type="radio" name="reqMethod" value="GET" ><span class="lbl"> GET</span>
												&nbsp;&nbsp;
												<button class="btn btn-success btn-sm" onclick="sendSever();">请求</button>
												<button class="btn btn-info btn-sm" onclick="reSetForm();">重置</button>							
											</div>					
										</div>
										<hr>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right">请求url：</label>
											<div class="col-sm-9">
												<span class="input-icon" style="width:100%">
												<input jeegemValidate="required" name="serverUrl" type="text" style="width:100%">
												<i class="icon-globe"></i>
												</span>
												<span><font color="gray">格式: http(s)://ip:端口/xxx</font></span>
											</div>		
										</div>
										<hr>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right">参数：</label>
											<div class="col-sm-9">
												<span class="input-icon" style="width:100%">
												<input name="params" type="text" style="width:100%">
												<i class="icon-edit"></i>
												</span>
												<span><font color="gray">格式: name1=value1&name2=value2, 如果是GET请求此处没用</font></span>
											</div>		
										</div>
										<hr>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right">返回结果：</label>
											<div class="col-sm-9">
												<textarea rows="2" id='resContent' cols="10" maxlength="2048" multiline="true" class="form-control"></textarea>									
											</div>		
										</div>
										<hr>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right">服务器响应：<font id="serverTime" color="red">-</font>毫秒 </label>
											<label class="col-sm-3 control-label no-padding-right">客户端请求：<font id="clientTime" color="red">-</font>毫秒 </label>
										</div>
									</form>										
								</div>				
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="../../common/dialog.jsp" %>
	</div>
	<script src="${jeegempath}/static/js/system/tool/interfaceTest.js"></script>
</body>
</html>