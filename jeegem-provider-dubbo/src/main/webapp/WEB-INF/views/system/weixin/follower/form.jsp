<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input  type="hidden" name="openid" ></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">昵称：</td>
						<td class="DataTD">&nbsp;
						<input type="text" readonly  role="textbox" maxlength="64" name="nickname" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">国家：</td>
						<td class="DataTD">&nbsp;
						<input type="text" readonly  role="textbox" maxlength="64" name="country" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">省份：</td>
						<td class="DataTD">&nbsp;
						<input type="text" readonly role="textbox" maxlength="64" name="province" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">城市：</td>
						<td class="DataTD">&nbsp;
						<input type="text" readonly role="textbox" maxlength="64" name="city" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">备注：</td>
						<td class="DataTD">&nbsp;
						<textarea  rows="2" cols="10" readonly maxlength="100" name="remark" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>