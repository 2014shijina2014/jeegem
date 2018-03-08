<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../common/includeBaseSet.jsp" %>
<%@include file="../../common/includeSystemSet.jsp" %>
<link rel="stylesheet" href="${jeegempath}/static/css/system/system/weixin.css" />
</head>
<body>
<%@include file="../../common/dialog.jsp"%>
<%@include file="form.jsp"%>
<div class="col-xs-12">
	<div class="col-sm-4">
		<div class="menu_preview_area">
			<div class="mobile_menu_preview">
				<div class="mobile_hd tc">GemSystem微信平台</div>
				<div class="mobile_bd">
					<ul id="menuList" class="pre_menu_list grid_line ui-sortable ui-sortable-disabled"></ul>
				</div>
				
			</div>
		</div>
	</div>
	<div class="col-sm-8">
			<div class="widget-box">
			<div class="widget-header">
				<h4 id="menuTile">选择菜单位置</h4>
				<div class="widget-toolbar ">
					<button id="delMenuBtn" class="btn btn-minier btn-danger hide" onclick="delMenu();" >删除菜单</button>
				</div>
			</div>
			<div class="widget-body">
				<div class="widget-main">
					<div>
						<form id="menuForm" class="form-horizontal" >
							<input type="hidden" name="pId" value="0" >
							<input type="hidden" name="id" value="" >
							<input type="hidden" name="type" value="click" >
							<input type="hidden" name="keyId" value="" >
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" ><font color="red">*</font>菜单名称：</label>
								<div class="col-sm-9">
									<input type="text" jeegemValidate="required" name="name" maxlength="20" class="col-xs-10 col-sm-5"   >
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" ><font color="red">*</font>排序：</label>
								<div class="col-sm-9">
										<input type="number" value="1" jeegemValidate="required,numrangeth" name="sort" min='1' max='99999' jeegemValidate="required,numrangeth" onkeyup='this.value=this.value.replace(/\D/g,&apos;&apos;)'  class="FormElement ui-widget-content ui-corner-all isSelect75"></td>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >菜单内容：</label>
								<div class="col-sm-9">
									<label> <input class="ace" type="radio" name="selectType" value="0" checked="checked"> <span class="lbl">发送消息</span></label> 
									<label> <input class="ace" type="radio" name="selectType" value="1"> <span class="lbl">跳转网页</span></label> 
								</div>
							</div>
							<div id="selectMessage" class=''>
								<div class="tabbable">
											<ul id="myTab" class="nav nav-tabs">
												<li id="tabText" class="active">
													<a href="#text" data-toggle="tab"><i class="icon-pencil bigger-110"></i>文字</a>
												</li>
												<li id="tabimageText" >
													<a href="#imageText" data-toggle="tab"><i class="icon-list-alt  bigger-110"></i>图文信息</a>
												</li>
											
												<li id="tabimage">
													<a href="#image" data-toggle="tab"><i class="icon-picture bigger-110"></i>图片</a>
												</li>
											</ul>
											<div class="tab-content">
												<div class="tab-pane in active" id="text">
													<textarea  rows="2" cols="10" style="width:100%;height:100px;" maxlength="100" name="text" multiline="true" class="FormElement ui-widget-content ui-corner-all ">
													</textarea>
												</div>
												<div class="tab-pane " id="imageText">
													<a id="itemformAdd" class="lrspace3 aBtnNoTD" title="增加图文" href="#"  >
														<i class="icon-plus-sign color-green bigger-160"></i>
													</a>
													<table id="itemsTable" cellspacing="0" cellpadding="0" border="0" class="table table-striped table-bordered table-hover">
														<thead>
														<tr>
															<th style="width:20%"  class="center">标题</th>
															<th style="width:10%"  class="center">描述</th>
															<th style="width:40%"  class="center">链接</th>	
															<th style="width:10%"  class="center">图片</th>
															<th style="width:10%"  class="center"><i title="排序" class="icon-sort-by-order bigger-110 hidden-480"></i>排序</th>
															<th style="width:10%"  class="center">操作</th>
														</tr>
														</thead>
													</table>
												</div>
												<div class="tab-pane" id="image">
													图片链接：<input type="text" maxlength="512"  name="picUrl" class="FormElement ui-widget-content ui-corner-all"></td>
												</div>
											</div>
										</div>						
							</div>
							<div id="selectUrl" class='hide'>																					
								<div class="form-group">										
									<label class="col-sm-3 control-label no-padding-right" >页面地址：</label>
									<div class="col-sm-9">
									<input name="url" class="col-xs-10 col-sm-5" type="text"  >
									</div>
								</div>							
							</div>
							<div class="center">
								<button type="button" class="btn btn-info" onclick="saveMenu()">
									<i class="icon-save bigger-110"></i>保存
								</button>
								
							</div>
						</form>					
					</div>
				</div>
			</div>
		</div>
		<button type="button" class="btn btn-purple" onclick="syncMenu()">
			<i class="icon-cloud-upload  bigger-110"></i>发布菜单
		</button>
	</div>
</div>
<script src="${jeegempath}/static/js/system/weixin/menu/menu.js"></script>
</body>
</html>