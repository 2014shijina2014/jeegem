<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="accountId" ></td>
					</tr>
					<tr class="FormData" >
						<td class="CaptionTD">状态：</td>
						<td class="DataTD">&nbsp;
							<label class="inline isValidCheckbox">
								<input type="checkbox" checked="checked" sh-isValid="" role="checkbox" class="FormElement ace ace-switch ace-switch-5" />	
								<span  class="lbl"></span>
								<!-- cb-isValid和Yes和No选择框配套使用-->
								<input type="hidden" hi-isValid=""  name="isValid" value="1" />
							</label>
						</td>
					</tr>	
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
					</tr>		
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>登录名：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jeegemValidate="required"  maxlength="16" name="loginName" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>用户角色：</td>
						<td class="DataTD">&nbsp;
							<input id="roleName" jeegemValidate="required" type="text" readonly value="" class="FormElement ui-widget-content ui-corner-all" onclick="showRole(); return false;"/>
							<input type="hidden" name="roleId" value="0" >
							<a href="#" title="清空" onclick="emptyRole(); return false;" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
							<div id='roleContent' class="menuContent ztreeMC" style="display: none; position: absolute;">
								<ul id="roleTree" class="ztree accountRoleTree"></ul>
							</div>
					</tr>	
					<tr class="FormData">
						<td class="CaptionTD">用户名：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="name" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>	
					<tr class="FormData">
						<td class="CaptionTD">电子邮箱：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jeegemValidate="email"  maxlength="32" name="email" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>	
					<tr class="FormData">
						<td class="CaptionTD">描述：</td>
						<td class="DataTD">&nbsp;
						<textarea rows="2" cols="10" maxlength="100" name="description" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
						</td>
					</tr> 
				</tbody>
			</table>
		</form>
</div>
<div id="resetPwdDiv" class="hide">
	<form id="resetPwdFrom" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr style="display:none">
					<td colspan="2" class="ui-state-error"><input type="hidden" name="accountId" ></td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD">重置密码：</td>
					<td class="DataTD">&nbsp;
						<input type="password" jeegemValidate="required"  maxlength="16" name="pwd" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr> 
			</tbody>
		</table>
	</form>		
</div>