<%@ page contentType="text/html;charset=UTF-8"%>
<div id="register-box" class="signup-box widget-box no-border">
	<div class="widget-body">
		<div class="widget-main">
			<h4 class="header green lighter bigger">
				<i class="icon-group blue"></i> 新用户注册
			</h4>
			<div class="space-6"></div>
			<p>请输入信息：</p>
			<form id="registerForm" method="post" >
				<fieldset>
					<label class="block clearfix"> 
						<span class="block input-icon input-icon-right"> 
							<input id="registerEmail" type="email" class="form-control" name="email" placeholder="电子邮箱" /><i class="icon-envelope"></i>
						</span>
					</label> 
					<label class="block clearfix"> 
						<span class="block input-icon input-icon-right"> 
							<input type="text" class="form-control" name="userName" placeholder="输入登录名" /><i class="icon-user"></i>
						</span>
					</label> 
					<label class="block clearfix"> 
						<span class="block input-icon input-icon-right"> 
							<input type="password" class="form-control" name="password" placeholder="输入密码"  /><i class="icon-lock"></i>
						</span>
					</label> 
					<label class="block clearfix">
						<span class="block input-icon input-icon-right">
							<input type="password" class="form-control" name="rtpassword" placeholder="重复密码" /><i class="icon-retweet"></i>
						</span>
					</label> 
					<label class="block">
						<input type="checkbox" class="ace" />
						<span class="lbl"> 我接受 <a href="#">用户协议</a>
					</span>
					</label>
					<div class="space-24"></div>
					<div class="clearfix">
						<button type="reset" class="width-30 pull-left btn btn-sm">
							<i class="icon-refresh"></i>重置
						</button>
						<button type="button" id="registerBtn" class="width-65 pull-right btn btn-sm btn-success">注册<i class="icon-arrow-right icon-on-right"></i>
						</button>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="toolbar center">
			<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
				<i class="icon-arrow-left"></i> 返回登录
			</a>
		</div>
	</div>
	<!-- /widget-body -->
</div>