<%@ page contentType="text/html;charset=UTF-8"%>
<div id="forgot-box" class="forgot-box widget-box no-border">
	<div class="widget-body">
		<div class="widget-main">
			<h4 class="header red lighter bigger">
				<i class="icon-key"></i>找回密码
			</h4>
			<div class="space-6"></div>
			<p>请输入您的电子邮件接受邮件</p>
			<form id="forgotFrom">
				<fieldset>
					<label class="block clearfix"> <span class="block input-icon input-icon-right"> 
						<input id="forgotEmail" type="email" class="form-control" placeholder="电子邮箱" />
						<i class="icon-envelope"></i>
					</span>
					</label>
					<div class="clearfix">
						<button id="forgotBtn" type="button" class="width-35 pull-right btn btn-sm btn-danger">
							<i class="icon-lightbulb"></i> 发送
						</button>
					</div>
				</fieldset>
			</form>
		</div>
		<!-- /widget-main -->
		<div class="toolbar center">
			<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link"> 返回登录 
			<i class="icon-arrow-right"></i>
			</a>
		</div>
	</div>
	<!-- /widget-body -->
</div>