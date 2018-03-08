<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="scheduleJobId" ></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>任务分组：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jeegemValidate="required,ennum" role="textbox" maxlength="32" name="jobGroup" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>任务名：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jeegemValidate="required,ennum" role="textbox" maxlength="32" name="jobName" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>任务别名：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jeegemValidate="required" role="textbox" maxlength="32" name="aliasName" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>任务类Class：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jeegemValidate="required" role="textbox" maxlength="64" name="jobClass" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>执行方式 ：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jeegemValidate="required" role="textbox" maxlength="32" name="cronExpression" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr rowpos="4" class="FormData" >
						<td class="CaptionTD">状态：</td>
						<td class="DataTD">&nbsp; 
							<label> <input class="ace" type="radio" name="status" value="0"> <span class="lbl">停用</span></label> 
							<label> <input class="ace" type="radio" name="status" value="1"> <span class="lbl">启用</span></label> 
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">描述：</td>
						<td class="DataTD">&nbsp;
						<textarea  rows="2" cols="10" maxlength="128" name="description" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>