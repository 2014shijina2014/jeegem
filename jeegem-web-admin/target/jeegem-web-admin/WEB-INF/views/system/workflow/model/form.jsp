<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" target="_blank" method="POST" action="${jeegempath}/backstage/workflow/model/create" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>名称：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jeegemValidate="required" role="textbox" maxlength="64" name="name" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>KEY：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jeegemValidate="required,ennum" role="textbox" maxlength="32" name="key" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">描述：</td>
						<td class="DataTD">&nbsp;
						<textarea  rows="2" cols="10" maxlength="100" name="description" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
</div>
<div id="exportDiv" class="hide">
	<div class="row">
	<input type="hidden" name="modelId" >
		<div class="center"> 
			<span class="btn btn-sm btn-info"    onclick="exportFile('bpmn')" >BPMN</span>
			<span class="btn btn-sm btn-danger"  onclick="exportFile('json')" >JSON</span>
		</div>	
	</div>
</div>