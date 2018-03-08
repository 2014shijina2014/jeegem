<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../common/includeBaseSet.jsp"%>
<%@include file="../../common/includeSystemSet.jsp"%>
<script type="text/javascript" charset="utf-8" src="${jeegempath}/static/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${jeegempath}/static/plugins/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${jeegempath}/static/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<div class="tabbable">
					<ul  class="inbox-tabs nav nav-tabs padding-16 tab-size-bigger tab-space-1">	
						<li class="pull-right">
							<a data-toggle="tab" href="#writeDiv"  class="btn-new-mail"> 
								<span class="btn bt1n-small btn-purple no-border"> 
									<i class="icon-pencil bigger-130"></i> <span class="bigger-110">写信</span>
								</span>
							</a>
						</li>	
						<li class="">
							<a data-toggle="tab" href="#inbox" > 
								<i class="green icon-inbox bigger-130"></i>
								<span class="bigger-110">收件箱</span>
							</a>
						</li>
						<li  class="active">
							<a id="sentA" data-toggle="tab" href="#sentDiv">
								<i class="orange icon-location-arrow bigger-130 "></i> 
								<span class="bigger-110">已发送</span>
							</a>
						</li>
						<li class="">
							<a href="#setConfig" data-toggle="tab" onclick="getConfig();return false;">
								<i class="color-blue icon-cog bigger-130 "></i> 
								<span class="bigger-110">设置</span>
							</a>
						</li>
					</ul>
					<div class="tab-content no-border no-padding">
						<div id="sentDiv" class="tab-pane active">			
							<div class="message-container">
								<div class="message-navbar align-center clearfix" >
									<div class="message-bar"></div>
									<div>
										<div class="messagebar-item-left">
										</div>
										<div class="nav-search minimized">
											<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">	
													<input  type="text"  name="keyWord" placeholder="这里输入关键词" class="input-large">												
													&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
												<input type='hidden' class='pageNum' name='pageNum' value='1'/>
												<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
											</form>
										</div>
									</div>
								</div>
								<div class="message-list-container">
									<div class="message-list">
										<div class="message-item message-unread selected">
											<label class="inline"><input id='id-toggle-all' type="checkbox" class="ace"> <span class="lbl"></span></label> 
											<i class="message-star icon-user"></i> 
											<span class="sender">收件人</span> 
												<span class="time"><i class="icon-time bigger-110 hidden-480"></i>时间↓</span> <span class="summary"> 
												<span class="text">主题</span>
											</span>
										</div>
										<span id="sentTable"></span>
									</div>
								</div>
								<div style="background-color: #f1f1f1;padding: 12px 16px;">
									<div id="pageing" class="dataTables_paginate paging_bootstrap">
										<ul class="pagination"></ul>
									</div>
								</div>								
							</div>
						</div>
						<div id="setConfig" class="tab-pane">			
							<div class="message-container">
								<div class="message-navbar align-center clearfix" >
									<div class="message-bar">
										<!-- <div class="message-toolbar">
											<a class="btn btn-xs btn-message" href="#" onclick="saveConfig();return false;" >
												<i class="icon-save bigger-125"></i>
												<span class="bigger-110">保存配置</span>
											</a>
										</div> -->
									</div>
									<div>
										<div class="messagebar-item-left"><h4>邮箱配置</h4></div>
										<div class="nav-search minimized"></div>
									</div>
								</div>
								<div class="message-list-container">
									<div class="message-list">
										<form id="configForm" class="form-horizontal message-form col-xs-12" method="POST" onsubmit="return false;">
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right">Smtp：</label>
												<div class="col-sm-6">
													<input type="text" readonly maxlength="300" class="col-xs-12 " name="smtp">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right">端口：</label>
												<div class="col-sm-6">
													<input type="text" readonly maxlength="15" class="col-xs-12" name="port">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right">邮箱地址：</label>
												<div class="col-sm-6">
													<input type="text" readonly maxlength="300" class="col-xs-12" name="email">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right">邮箱名：</label>
												<div class="col-sm-6">
													<input type="text" readonly maxlength="50" class="col-xs-12" name="emailName">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right">用户名：</label>
												<div class="col-sm-6">
													<input type="text" readonly maxlength="100" class="col-xs-12" name="userName">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right">密码：</label>
												<div class="col-sm-6">
													<input type="password" readonly maxlength="100" class="col-xs-12" name="password"> 
													<label class="inline"> 
													<input class="ace" type="checkbox" onclick="showPW(this)" > 
													<span class="lbl">&nbsp;&nbsp;明文 </span>
													</label>
												</div>
											</div>
										</form>
									</div>
								</div>								
							</div>
						</div>
						<div id="writeDiv" class="tab-pane">
							<div class="message-container">
								<div class="message-navbar align-center clearfix">
									<div class="message-bar">
										<!-- <div class="message-toolbar">
											<a class="btn btn-xs btn-message" href="#">
												<i class="icon-save bigger-125"></i>
												<span class="bigger-110">保存草稿</span>
											</a>
										</div> -->
									</div>
									<div class="message-item-bar">
										<div class="messagebar-item-left">
											<a onclick="writeClose();return false;" class="btn-back-message-list no-hover-underline" href="#">
												<i class="icon-arrow-left blue bigger-110 middle"></i> 
												<b class="middle bigger-110">关闭</b>
											</a>
										</div>
	
										<div class="messagebar-item-right">
											<span class="inline btn-send-message">
												<button onclick="sendMail();return false;" class="btn btn-sm btn-primary no-border" type="button">
													<span class="bigger-110">发送</span> <i
														class="icon-arrow-right icon-on-right"></i>
												</button>
											</span>
										</div>
									</div>
								</div>
								<div class="message-list-container">
								<form id="mailform" class="form-horizontal message-form col-xs-12" method="POST" onsubmit="return false;">
									<div class="form-group">
										<label class="col-sm-1 control-label no-padding-right">收件人：</label>
										<div class="col-sm-11 col-xs-12">
											<span class="input-icon block col-xs-12 no-padding">
												<input type="email" placeholder="请输入邮箱，多个请用(;)分号隔开" maxlength="5000" class="col-xs-12" name="toList">
												<i class="icon-user"></i>
											</span>
										</div>
									</div>
									<div class="hr hr-18 dotted"></div>
									<div class="form-group">
										<label class="col-sm-1 control-label no-padding-right">主题：</label>
										<div class="col-sm-11 col-xs-12">
											<div class="input-icon block col-xs-12 no-padding">
												<input type="text" placeholder=""  class="col-xs-12" maxlength="100" name="subject">
												<i class="icon-comment-alt"></i>
											</div>
										</div>
									</div>
									<div class="hr hr-18 dotted"></div>
									<div class="form-group">
										<label class="col-sm-1 control-label no-padding-right"><span class="inline space-24 hidden-480"></span>正文：</label>
									 	<div class="col-sm-11 col-xs-12">
									 	 <script id="editor" type="text/plain" style="width:100%;height:300px;"></script>
									 	</div>
									</div>
								</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="../../common/dialog.jsp" %>
	</div>
	<script src="${jeegempath}/static/js/system/tool/email.js"></script>
</body>
</html>