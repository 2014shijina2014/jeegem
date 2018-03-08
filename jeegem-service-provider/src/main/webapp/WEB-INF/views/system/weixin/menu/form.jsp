<%@ page contentType="text/html;charset=UTF-8" %>
<div id="itemDiv" class="hide">
	<table cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>标题：</td>
				<td class="DataTD">&nbsp;
				<input type="text" maxlength="32" jeegemValidate="required" name="title" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>顺序：</td>
				<td class="DataTD">&nbsp;
				<input type="number" value="1" jeegemValidate="required,numrangeth" name="sort" min='1' max='99999' jeegemValidate="required,numrangeth" onkeyup='this.value=this.value.replace(/\D/g,&apos;&apos;)'  class="FormElement ui-widget-content ui-corner-all isSelect75"></td>
			</tr>	
			<tr class="FormData">
				<td class="CaptionTD">链接：</td>
				<td class="DataTD">&nbsp;
				<input type="text" maxlength="512"  name="url" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">图片链接：</td>
				<td class="DataTD">&nbsp;
				<input type="text" maxlength="512"  name="picUrl" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">描述：</td>
				<td class="DataTD">&nbsp;
				<input type="text" maxlength="64"  name="content" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>	
		</tbody>
	</table>
</div>
	
	