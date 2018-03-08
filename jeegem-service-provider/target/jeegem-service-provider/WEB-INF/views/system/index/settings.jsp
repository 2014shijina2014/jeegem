<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--系统设置-->
<div id="perSetting" class="hide">
<form id="perSettingFrom" method="POST" onsubmit="return false;">
	<div class="tabbable">
	<input id='userSkin' type="hidden" value="${currentAccount.skin}" >
    <ul class="nav nav-tabs">
    	<li class="active"><a data-toggle="tab" href="#skinSetting"><i id='skinSettingIcon1' class="icon-github-alt bigger-110"></i> 皮肤</a></li>
        <li><a data-toggle="tab" href="#proSetting"><i id='skinSettingIcon2' class="icon-magnet bigger-110"></i> 个性化</a></li>
    </ul>
    <div class="tab-content noline tabCenter">
		<div id="skinSetting" class="tab-pane in active">
			<table  class="customTable">
				<tr class="FormData">
					<td>
						<span class="radSkin skinBlue"></span>
						<label><input type="radio" class="ace" name="skin" value="skin-0" <c:if test="${currentAccount.skin =='skin-0' }">checked="checked"</c:if> ><span class="lbl"> 经典蓝</span></label>
					</td>
					<td>
						<span class="radSkin skinBlack"></span>
						<label><input type="radio" class="ace" name="skin" value="skin-1" <c:if test="${currentAccount.skin =='skin-1' }">checked="checked"</c:if>><span class="lbl"> 机械黑</span></label>
					</td>
					<td>
						<span class="radSkin skinPurple"></span>
						<label><input type="radio" class="ace" name="skin" value="skin-2" <c:if test="${currentAccount.skin =='skin-2' }">checked="checked"</c:if>><span class="lbl"> 炫丽紫</span></label>
					</td>
					<td>
						<span class="radSkin skinGray" ></span>
						<label><input type="radio" class="ace" name="skin" value="skin-3" <c:if test="${currentAccount.skin =='skin-3' }">checked="checked"</c:if>><span class="lbl"> 清新灰</span></label>
					</td>
				</tr>
			</table>
		</div>
		<div id="proSetting" class="tab-pane">
			  <table  class="customTable">
			  	<tr>
			  		<td class="red">(个性化设置保存在cookie)</td>
			  	</tr>	  
			  	<!-- <tr class="FormData">
					<td>
					<input id="jeegem-settings-navbar" name="fixNavbar" class="ace ace-checkbox-2" type="checkbox"> 
					<label class="lbl" for="jeegem-settings-navbar"> 固定导航条 <b></b></label>
					</td>
			  	</tr> -->
			  	<!-- <tr class="FormData">
					<td>
					<input id="jeegem-settings-fixMenu" name="fixMenu" class="ace ace-checkbox-2" type="checkbox"> 
					<label class="lbl" for="jeegem-settings-fixMenu"> 固定菜单栏 <b></b></label>
					</td>
			  	</tr> -->
			  	<tr class="FormData">
					<td>
					<input id="jeegem-settings-add-container" name="narWinMenu" class="ace ace-checkbox-2" type="checkbox"> 
					<label class="lbl" for="jeegem-settings-add-container"> 切换窄屏幕 <b></b></label>
					</td>
			  	</tr>
			  	<tr class="FormData">
					<td>
					<input id="jeegem-settings-sfMenu" name="sfMenu" class="ace ace-checkbox-2" type="checkbox"> 
					<label class="lbl" for="jeegem-settings-sfMenu"> 缩放菜单栏 <b></b></label>
					</td>
			  	</tr>			  	
			  	<tr class="FormData">
					<td>
				  		<input id="jeegem-settings-rtl" name="posMenu" class="ace ace-checkbox-2" type="checkbox">
						<label class="lbl" for="ace-settings-rtl"> 切换右菜单</label>
			  		</td>
			  	</tr>
			  </table>
			</div>
         </div> 
	</div>
</form>
</div>
<div id="perData" class="hide">
	<div class="tabbable">
		<ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
			<li class="active"><a href="#avatarSetting" data-toggle="tab">头像设置</a></li>
			<li class=""><a href="#perfileSetting" data-toggle="tab">个人资料</a></li>
			<li class=""><a href="#pwSetting" data-toggle="tab">密码管理</a>
			</li>
		</ul>
		<div class="tab-content noline">
			<div class="tab-pane active" id="avatarSetting">
				<!-- 我是头像 -->				
				<div class="row-fluid center">	
					<h4>头像预览</h4>
					<p><img id='headpicPreviewImg' class="headpicPreview" src="/static/images/system/user/hpic1.jpg"></p>
					<p>头像100*100</p>
					<ul id="headpicRecommend" class="ace-thumbnails headpic" data-hotpor="wildkid-1">
					<li>
						<img src="<c:url value="/static/images/system/user/hpic1.jpg" />">
						<span class="recommendSpan"></span>
					</li>
					<li >
						<img src="<c:url value="/static/images/system/user/hpic2.jpg" />">
						<span class="recommendSpan"></span>
					</li>
					<li>
						<img src="<c:url value="/static/images/system/user/hpic3.jpg" />">
						<span class="recommendSpan"></span>
					</li>
					<li>
						<img src="<c:url value="/static/images/system/user/hpic4.jpg" />">
						<span class="recommendSpan"></span>
					</li>
					</ul>
				</div>
			</div>
			<div class="tab-pane" id="perfileSetting">
			<form id="perfileSettingFrom" method="POST" onsubmit="return false;">
				<div class="profile-user-info profile-user-info-striped">
					<div class="profile-info-row">
						<div class="profile-info-name tab-pane-left">登录名</div>
						<div class="profile-info-value tab-pane-right">
							<span class="editable" id='perDataloginName'> </span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name tab-pane-left">用户名</div>
						<div class="profile-info-value tab-pane-right">
							<div class="clearfix">
								<input type="text" id='perDataName' maxlength="32" name='name' class="col-xs-12">
							</div>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name tab-pane-left">电子邮箱</div>
						<div class="profile-info-value tab-pane-right">
							<div class="clearfix">
								<input type="text" jeegemValidate="email" id='perDataEmail' maxlength="120" name='email' class="col-xs-12">
							</div>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name tab-pane-left">用户角色</div>
						<div class="profile-info-value tab-pane-right">
							<span class="editable" id='perDataRole' > </span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name tab-pane-left">登录IP</div>
						<div class="profile-info-value tab-pane-right">
							<span class="editable" id='perDataloginIP'> </span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name tab-pane-left">登录时间</div>
						<div class="profile-info-value tab-pane-right">
							<span class="editable" id='perDataloginTime'> </span>
						</div>
					</div>
				</div>
			</form>
			</div>
			<div class="tab-pane" id="pwSetting">
			<form id="pwSettingFrom" method="POST" onsubmit="return false;">
				<table class="customTable">
					<tr class="FormData">
						<td class="CaptionTD">
							<label>旧密码&nbsp;</label>
						</td>
						<td class="DataTD">
							<span class="input-icon"> <input maxlength="12" name="opwd" type="password" > <i class="icon-lock blue"></i></span>
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">
							<label>新密码&nbsp;</label>
						</td>
						<td class="DataTD">
							<span class="input-icon"> <input maxlength="12" name="npwd" type="password" placeholder="" value=""> <i class="icon-lock green"></i></span>
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">
							<label>确认新密码&nbsp;</label>
						</td>
						<td class="DataTD">
							<span class="input-icon"> <input maxlength="12" name="qpwd" type="password" placeholder="" value=""> <i class="icon-lock green"></i></span>
						</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
	</div>

</div>