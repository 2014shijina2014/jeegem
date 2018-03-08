<%@ page contentType="text/html;charset=UTF-8" %>
<!-- 岗位-->
<div id="auPosDiv" class="hide">
		<form id="auPosForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>职务名称：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jeegemValidate="required"  maxlength="16" name="name" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData form-inline">
						<td class="CaptionTD"><font color="red">*</font>职务类别：</td>
						<td class="DataTD ">&nbsp;
							<span id="selectPosType"><select name="type" class="form-control isSelect105"></select></span>	
						</td>
					</tr>	
					<tr id="preOrgTR" class="FormData">
						<td  class="CaptionTD"><font color="red">*</font>上级组织：</td>
						<td class="DataTD">&nbsp;
							<input id="preOrg" jeegemValidate="required" type="text" readonly value="" class="FormElement ui-widget-content ui-corner-all" onclick="showPreOrg(); return false;"/>
							<input type="hidden" name="orgId" value="0" >
							<a href="#" title="清空" onclick="emptyPreOrg(); return false;" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
							<div id='preOrgContent' class="menuContent ztreeMC" style="display: none; position: absolute;">
								<ul id="preOrgTree" class="ztree preOrgTree"></ul>
							</div>
						</td>
					</tr>					
					<tr class="FormData">
						<td class="CaptionTD">职务描述：</td>
						<td class="DataTD">&nbsp;
						<textarea rows="2" cols="10" maxlength="100" name="description" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<br/><br/><br/><br/>
</div>
<div id="arrangeAccDiv" class="hide">
	<form id="arrangeAccForm" class="form-inline" method="POST" onsubmit="return false;">
		<div class="widget-main customBtn">	
			<input  type="text"  name="keyWord" placeholder="这里输入关键词" class="input-medium">												
			&nbsp;&nbsp;<button class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="loadArrangeAcc(1)"><i class="icon-search bigger-110 icon-only"></i></button>		
		</div>			
		<input type='hidden' class='pageNum' name='pageNum' value='1'/>
		<input type='hidden' class='pageSize'  name='pageSize' value='3'/>
	</form>
	<table id="arrangeAccTable" cellspacing="0" cellpadding="0" border="0" class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th class="center" style="width:10%">
					<label><input type="checkbox" class="ace"><span class="lbl"></span></label>
				</th>
				<th style="width:20%" class="center">序号</th>
				<th style="width:35%" class="center">登录名</th>
				<th style="width:35%" class="center">用户名</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<div class="row">
		<div id="arrangeAccpageing" class="dataTables_paginate paging_bootstrap">
			<ul class="pagination"></ul>
		</div>
	</div>
</div>

