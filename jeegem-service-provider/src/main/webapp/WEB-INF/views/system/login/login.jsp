<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>
<%@include file="../common/includeBaseSet.jsp" %>
<link rel="stylesheet" href="${jeegempath}/static/css/system/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="${jeegempath}/static/css/system/ace/font-awesome.min.css" />
<link rel="stylesheet" href="${jeegempath}/static/css/system/jquery/jquery-ui-1.10.3.full.min.css" />
<link rel="stylesheet" href="${jeegempath}/static/css/system/ace/ace.min.css" /> 
<link rel="stylesheet" href="${jeegempath}/static/css/system/ace/camera.css" />
<link rel="stylesheet" href="${jeegempath}/static/css/system/system/login.css" />
<script src="${jeegempath}/static/js/jquery/jquery.md5.js"></script>
<script src="${jeegempath}/static/js/jquery/jquery-ui-1.10.3.full.min.js"></script>
<script src="${jeegempath}/static/js/system/login/login.js"></script>
</head>
<body class='login-layout login-bg' >
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1><i class="icon-apple green"></i> <span class="red">JEEGEM</span>&nbsp;<span class="white">System</span></h1>
							<!-- <h4 class="blue">&copy; base</h4> -->
						</div>
						<div class="space-6"></div>
						<div class="position-relative">
							<div id="login-box" class="login-box visible widget-box no-border">
								<div  class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-github-sign green"></i> 请输入您的信息
										</h4>
										<div class="space-6"></div>
										<form id="loginForm" method="post">
											<fieldset>
												<label class="block clearfix">
													<span class="block input-icon input-icon-right"> 
														<input type="text" id="accountNameId" name="accountName" required="required" maxlength="16" class="form-control" placeholder="请输入用户名" /><i class="icon-user"></i>
													</span>
												</label> 
												<label class="block clearfix"> 
													<span class="block input-icon input-icon-right"> 
														<input type="password" id="passwordId" name="password"  required="required" maxlength="16" class="form-control" placeholder="请输入密码" /><i class="icon-lock"></i>
													</span>
												</label>
												<label class="block clearfix"> 
													<span > 
														<input class="col-xs-5" id="verifyCodeId" name="verifyCode" required="required" maxlength="4" type="tel" class="form-control" placeholder="请输入验证码" onkeyup="this.value=this.value.replace(/\D/g,'')" />
													</span>
													<span class="col-sm-5"> 
														<img  id="vimg" style="cursor:pointer;width:80px;height:30px;" title="验证码" width="60" height="37" />
													</span>
												</label>
												<div class="space"></div>
												<div class="clearfix">
													<!-- <label class="inline"> <input type="checkbox" class="ace" /> <span class="lbl"> Remember Me</span></label> -->
													<button id="loginBtn" type="button" class="width-35 pull-right btn btn-sm btn-primary">
														<i class="icon-key"></i> 登录
													</button>
												</div>
												<div class="space-4"></div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->
									<div class="toolbar clearfix">
										<div>
											<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
												<i class="icon-arrow-left"></i>忘记密码
											</a>
										</div>
										<div>
											<a href="#" onclick="show_box('register-box'); return false;" class="user-signup-link">
												注册<i class="icon-arrow-right"></i>
											</a>
										</div>
									</div>
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->
							<%@include file="forgot.jsp" %>
							<!-- /forgot-box -->
							<%@include file="register.jsp" %>
							<!-- /signup-box -->
							<%@include file="../common/dialog.jsp" %>
						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
		</div>
	</div>
</body>
</html>