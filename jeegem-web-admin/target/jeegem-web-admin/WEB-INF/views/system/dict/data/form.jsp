<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
					</tr>
					<tr class="FormData" >
						<td class="CaptionTD">状态：</td>
						<td class="DataTD">&nbsp;
							<label class="inline isValidCheckbox">
								<input type="checkbox" checked="checked" sh-isValid="" role="checkbox" class="FormElement ace ace-switch ace-switch-5" />	
								<span  class="lbl"></span>
								<!--cb-isValid和Yes和No选择框配套使用 -->	
								<input type="hidden" hi-isValid=""  name="isValid" value="1" />
							</label>
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>关键字：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="16" jeegemValidate="required,ennum" name="dataKey" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">中文说明：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="32" name="name" class="FormElement ui-widget-content ui-corner-all"></td>
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
		<a id="itemformAdd" class="lrspace3 aBtnNoTD" title="增加" href="#">
			<i class="icon-plus-sign color-green bigger-120"></i>
		</a>
		<div class="hr hr-dotted"></div>		
		<table id="itemsTable" cellspacing="0" cellpadding="0" border="0" class="table table-striped table-bordered table-hover">
			<thead>
			<tr>
				<th style="width:35%"  class="center">名字</th>
				<th style="width:35%"  class="center">编码</th>	
				<th id="itemformSort" style="width:5%"  class="center"><i title="排序" class="icon-sort-by-order bigger-110 hidden-480"></i></th>
				<th style="width:20%"  class="center">操作</th>
			</tr>
			</thead>
		</table>
		<br/><br/>
</div>
<div id="itemDiv" class="hide">
	<table cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>编码：</td>
				<td class="DataTD">&nbsp;
				<input type="text" maxlength="16" jeegemValidate="required,ennum" name="value" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>中文说明：</td>
				<td class="DataTD">&nbsp;
				<input type="text" maxlength="32" jeegemValidate="required" name="name" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>顺序：</td>
				<td class="DataTD">&nbsp;
				<input type="number" value="1" jeegemValidate="required,numrangeth" name="sort" min='1' max='99999' jeegemValidate="required,numrangeth" onkeyup='this.value=this.value.replace(/\D/g,&apos;&apos;)'  class="FormElement ui-widget-content ui-corner-all isSelect75"></td>
			</tr>		
		</tbody>
	</table>
</div>
	
	