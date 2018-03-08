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
		<%@include file="../../common/dialog.jsp"%>
		<div class="widget-box">
			<div class="widget-header">
				<h4>接口配置信息</h4>
				<span class="widget-toolbar"> 
					<a data-action="collapse" href="#"> <i class="icon-chevron-up"></i></a>
				</span>
			</div>
			<div class="widget-body">
				<div class="widget-main">
					<div>
						<form id="configForm" class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >appID：</label>
								<div class="col-sm-9">
									<input name="appId" class="col-xs-10 col-sm-5" type="password" readonly >
									<label class="inline"> 
										<input class="ace" type="checkbox" onclick="showPW(this,'appId')" > 
										<span class="lbl">&nbsp;&nbsp;明文 </span>
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >密钥(appsecret)：</label>
								<div class="col-sm-9">
									<input name="appSecret" class="col-xs-10 col-sm-5" type="password" readonly >
									<label class="inline"> 
										<input class="ace" type="checkbox" onclick="showPW(this,'appSecret')" > 
										<span class="lbl">&nbsp;&nbsp;明文 </span>
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >令牌(Token)：</label>
								<div class="col-sm-9">
									<input name="token" class="col-xs-10 col-sm-5" type="password" readonly >
									<label class="inline"> 
										<input class="ace" type="checkbox" onclick="showPW(this,'token')" > 
										<span class="lbl">&nbsp;&nbsp;明文 </span>
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >AES安全加密密钥(EncodingAESKey)：</label>
								<div class="col-sm-9">
									<input name="aseKey" class="col-xs-10 col-sm-5" type="password" readonly >
									<label class="inline"> 
										<input class="ace" type="checkbox" onclick="showPW(this,'aseKey')" > 
										<span class="lbl">&nbsp;&nbsp;明文 </span>
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >开启机器人应答：</label>
								<div class="col-sm-9">
									<label> 
										<input type="checkbox" class="ace ace-switch ace-switch-7"  name="turing" >
										<span class="lbl"></span>
									</label>
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${jeegempath}/static/js/system/weixin/config/config.js"></script>
</body>
</html>