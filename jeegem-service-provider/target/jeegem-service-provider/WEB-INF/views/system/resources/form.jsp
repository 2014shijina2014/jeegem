<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
					</tr>
					<tr  class="FormData" >
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
						<td class="CaptionTD"><font color="red">*</font>资源名称：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jeegemValidate="required"  maxlength="25" name="name" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
					<td class="CaptionTD">资源图标：</td>
						<td class="DataTD">&nbsp;
						<span class="input-icon">
							<input type="text" readonly maxlength="64" name="icon"  class="FormElement ui-widget-content ui-corner-all isSelect147" >
							<i id="selecticonId" ></i>
						</span>
						<a class="lrspacew2 aBtnNoTD" onclick="selectIcon();return false;" title="浏览图标" href="#">
							<i class="icon-picture color-dark-green bigger-120 red"></i>
						</a>
						<a class="lrspace2 aBtnNoTD" onclick="emptyIcon();return false;" title="清空" href="#">
							<i class="icon-remove bigger-120 red"></i>
						</a>
					</td>					
					</tr>
					<tr id="selectlayer" class="FormData" >
						<td class="CaptionTD">显示层级：</td>
						<td class="DataTD">&nbsp;
							<button title="选择显示层级" value='1' class="btn btn-xs btn-success"><i class="icon-desktop"></i></button>					
							<button title="选择显示层级" value='2' class="btn btn-xs"><i class="icon-th"></i></button>		
							<button title="选择显示层级" value='3' class="btn btn-xs"><i class="icon-user"></i></button>	
							<button title="选择显示层级" value='4' class="btn btn-xs"><i class="icon-bitbucket"></i></button>			
							<input type="hidden" name="layer" value="1" >
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">上级资源：</td>
						<td class="DataTD">&nbsp;
							<input id="preResources" type="text" readonly value="" class="FormElement ui-widget-content ui-corner-all" onclick="showPreResources(); return false;"/>
							<input type="hidden" name="parentId" value="0" >
							<a href="#" title="清空" onclick="emptyPre(); return false;" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
							<div id="preResourcesContent" class="menuContent ztreeMC" style="display: none; position: absolute;">
								<ul id="preResourcesTree" class="ztree resourcesTree"></ul>
							</div>
						</td>
					</tr>
					<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>顺序：</td>
						<td class="DataTD">&nbsp;
						<input type="number" value="1" name="sort" min='1' max='99999' jeegemValidate="required,numrangeth" onkeyup='this.value=this.value.replace(/\D/g,&apos;&apos;)'  class="FormElement ui-widget-content ui-corner-all isSelect75"></td>
					</tr>		
					<tr class="FormData">
						<td class="CaptionTD">资源类型：</td>
						<td class="DataTD">&nbsp;
						<select class="isSelect75" onchange="changeType(this.value);" name="type" >
							<option value="1">菜单</option>
							<option value="2">功能</option>
							<option value="3">按钮</option>
						</select>
					</tr>			
					<tr class="FormData" id="trresUrl">
						<td class="CaptionTD">资源路径：</td>
						<td class="DataTD">&nbsp;
						<input type="text" maxlength="256" name="resUrl" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData hide" id="trbtnId">
						<td class="CaptionTD">标签主键：</td>
						<td class="DataTD">&nbsp;
						<input type="text"  maxlength="16" name="btnId" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
						<tr class="FormData hide" id="trbtnFun">
						<td class="CaptionTD">标签方法：</td>
						<td class="DataTD">&nbsp;
						<input type="text"  maxlength="32" name="btnFun" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">资源描述：</td>
						<td class="DataTD">&nbsp;
						<textarea rows="2" cols="10" maxlength="100" name="description" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
</div>
<div id="iconDiv" class="hide">
	<input type="hidden" id="iconHidden" >
	<input type="hidden" id="colorHidden" >
	<div class="row-fluid">	
		<div class="center">
			<font class="previewFont">预览：</font><i id="testIconId" ></i>	
		</div>
	</div>
	<div class="row-fluid" id="commonIcons">		
	<h4><i class="icon-hand-down color-green bigger-120"></i> 常用图标</h4>	
		<div class="comIconSelectul">
			<ul class="ace-thumbnails">
				<li><i title="查看" class="icon-zoom-in color-purple"  iicon="icon-zoom-in" icolor="color-purple"  ></i></li>
				<li><i title="增加" class="icon-plus-sign color-green" iicon="icon-plus-sign" icolor="color-green" ></i></li>
				<li><i title="修改" class="icon-edit color-blue"       iicon="icon-edit" icolor="color-blue" ></i></li>
				<li><i title="删除" class="icon-remove-sign color-red" iicon="icon-remove-sign" icolor="color-red" ></i></li>
				<li><i title="批量删除" class="icon-trash color-red"   iicon="icon-trash" icolor="color-red" ></i></li>
			</ul>
		</div>
	</div>
	<div class="row-fluid">		
	<h4><i class="icon-hand-down bigger-120"></i> 图标(360个)</h4>	
		<!-- 360个图标 -->		
		<%@include file="icon.jsp" %>
	</div>
	<div class="hr hr-dotted"></div>
	<div class="row-fluid" id="colorPlate" >
	<h4><i class="icon-html5 bigger-120"></i> 颜色(20种)</h4>							
		<table class="iconColorSelect">
			<tbody>
				<tr>					
					<td title="浅黄色" style="background-color:#fee188"   class="color-pale-yellow"></td>
					<td title="黄色"   style="background-color:#ffb752"   class="color-yellow"></td>
					<td title="浅橙色 " style="background-color:#ff892a" class="color-pale-orange" ></td>
					<td title="浅红色" style="background-color:#d15b47"  class="color-pale-red"  ></td>
					<td title="红色"   style="background-color:#dd5a43"    class="color-red" ></td>			
					<td title="粉红色" style="background-color:#d6487e"  class="color-pink" ></td>		
					<td title="鲜红色" style="background-color:#d12723"  class="color-bright-red" ></td>			
					<td title="浅蓝色" style="background-color:#6fb3e0"  class="color-pale-blue" ></td>
					<td title="蓝色"   style="background-color:#428bca"    class="color-blue" ></td>
					<td title="鲜蓝色 " style="background-color:#009393" class="color-bright-blue" ></td>			
				</tr>
				<tr>
					<td title="浅绿色" style="background-color:#87b87f" class="color-pale-green"></td>
					<td title="绿色" style="background-color:#69aa46"   class="color-green"></td>
					<td title="深绿色" style="background-color:#468847" class="color-dark-green"></td>
					<td title="浅灰色" style="background-color:#abbac3" class="color-pale-gray"></td>
					<td title="浅紫色"  style="background-color:#a069c3"class="color-pale-purple"></td>	
					<td title="紫色" style="background-color:#9585bf"   class="color-purple"></td>
					<td title="灰色" style="background-color:#777"      class="color-gray"></td>
					<td title="深蓝色" style="background-color:#4d6878" class="color-dark-blue"></td>		
					<td title="啡黄色" style="background-color:#963"    class="color-pale-brown"></td>								
					<td title="深灰色" style="background-color:#555"    class="color-dark-gray"></td>		
				</tr>	
			</tbody>
		</table>
	</div>
</div>

	