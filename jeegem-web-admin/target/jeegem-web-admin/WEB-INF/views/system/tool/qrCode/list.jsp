<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../common/includeBaseSet.jsp"%>
<%@include file="../../common/includeSystemSet.jsp"%>
<!-- 二维码引入js -->
<script type="text/javascript" src="${jeegempath}/static/js/jquery/jquery.qrcode.min.js"></script>
</head>
<body>
	<div class="page-content">
		<div class="row-flud">
			<div class="col-xs-12">
				<div class="widget-box">
					<div class="widget-header widget-header-flat">
						<h4>二维码</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main">
							<div class="row">
								<div class="col-xs-12">
									<div class="col-sm-6">
										<div class="widget-box">
											<div class="widget-header">
												<h5>生成二维码</h5>
												<span class="widget-toolbar"> 											
												<a title="生成二维码" onclick="createTwoD();return false;"href="#"><i class="icon-print"></i></a>
												<a data-action="collapse" href="#"><i class="icon-chevron-up"></i></a>
												</span>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<textarea rows="2" id='encoderContent' cols="10" maxlength="2048" name="description" multiline="true" class="form-control">https://www.baidu.com</textarea>
													<div class="space-6"></div>
													<div id="code">
														<img id="encoderImgId" cache="false" src="${jeegempath}/static/images/system/2qrcodeDefault.png" width="235px" height="235px" />
													</div>
												</div>
											</div>
										</div>
									</div> 
									<div class="col-sm-6">	
										<div class="widget-box">
											<div class="widget-header">
												<h5>解析二维码</h5>
												<span class="widget-toolbar"> 
												<a title="解析二维码" onclick="analyze2code();return false;"href="#"><i class="icon-search"></i></a>
												<a data-action="collapse" href="#"><i class="icon-chevron-up"></i></a>
												</span>
											</div>
											<div class="widget-body">
												<div class="widget-main">
														<form id="uploadForm" method="post" enctype="multipart/form-data" onsubmit="return false;">
															<input type="file" id="upload2code" name="codeFile"  onchange="jeegem.File.fileType(this,'gif|png|jpg|jpeg')" />
														</form>
														<textarea rows="2" id='analyzeContent' cols="10" maxlength="2048" multiline="true" class="form-control"></textarea>											
												</div>
											</div>
										</div>									
									</div> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="../../common/dialog.jsp" %>
	</div>
	<script src="${jeegempath}/static/js/system/tool/qrcode.js"></script>
</body>
</html>