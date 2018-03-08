<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
	<form id="auForm" method="POST" onsubmit="return false;">
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr style="display: none">
					<td colspan="2" class="ui-state-error">
						<input type="hidden" name="id">
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD">部门：</td>
					<td class="DataTD">&nbsp; 
						<input type="text" disabled="disabled" readonly role="textbox" maxlength="64" name="org" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD">申请人：</td>
					<td class="DataTD">&nbsp; 
						<input type="text" disabled="disabled" readonly role="textbox" maxlength="64" name="name" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD">审批人：</td>
					<td class="DataTD">&nbsp; 
						<input type="text" disabled="disabled" readonly role="textbox" maxlength="64" name="approver" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD">请假类型：</td>
					<td class="DataTD">&nbsp; 
						<input type="text" disabled="disabled" readonly role="textbox" maxlength="64" name="typeName" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD">开始时间：</td>
					<td class="DataTD">&nbsp; 
						<input type="text" disabled="disabled" readonly role="textbox" maxlength="64" name="beginTime" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD">结束时间：</td>
					<td class="DataTD">&nbsp; 
						<input type="text" disabled="disabled" readonly role="textbox" maxlength="64" name="endTime" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				<tr class="FormData">
						<td class="CaptionTD">请假事由：</td>
						<td class="DataTD">&nbsp;
						<textarea rows="2" disabled="disabled" readonly cols="10" maxlength="100" name="description" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
						</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<div id="rejectDiv" class="hide">
<form id="rejectForm" method="POST" onsubmit="return false;">
	<table cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>驳回理由：</td>
				<td class="DataTD">&nbsp;
					<textarea rows="2"  cols="10" maxlength="100" name="rejectReason" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
				</td>
			</tr>
		</tbody>
	</table>
</form>
</div>
<div id="adjustDiv" class="hide">
<form id="adjustForm" method="POST" onsubmit="return false;">
	<table cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*驳回理由：</font></td>
				<td class="DataTD">&nbsp;
					<textarea rows="2" disabled="disabled" readonly cols="10" maxlength="100" name="rejectReason" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
				</td>
			</tr>
			<tr style="display: none">
				<td colspan="2" class="ui-state-error">
					<input type="hidden" name="id">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">部门：</td>
				<td class="DataTD">&nbsp; 
					<input type="text" role="textbox" disabled="disabled" readonly maxlength="64" name="org" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">申请人：</td>
				<td class="DataTD">&nbsp; 
				<input type="text" role="textbox" disabled="disabled" readonly maxlength="64" name="name" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">审批人：</td>
				<td class="DataTD">&nbsp; 
					<input type="text" role="textbox" disabled="disabled" readonly maxlength="64" name="approver" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">请假类型：</td>
				<td class="DataTD">&nbsp; 
					<input type="hidden" name="type">
					<input type="text" role="textbox" disabled="disabled" readonly="readonly" maxlength="64" name="typeName" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">开始时间：</td>
				<td class="DataTD">&nbsp; 
					<input type="text" role="textbox" disabled="disabled" readonly maxlength="64" name="beginTime" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">结束时间：</td>
				<td class="DataTD">&nbsp; 
					<input type="text" role="textbox" disabled="disabled" readonly maxlength="64" name="endTime" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>请假事由：</td>
				<td class="DataTD">&nbsp;
					<textarea rows="2" cols="10" maxlength="100" name="description" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
				</td>
			</tr>
		</tbody>
	</table>
</form>
</div>