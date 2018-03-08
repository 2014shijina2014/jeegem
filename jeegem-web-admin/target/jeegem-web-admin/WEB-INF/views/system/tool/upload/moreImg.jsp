<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../common/includeBaseSet.jsp" %>
<%@include file="../../common/includeSystemSet.jsp" %>
<link rel="stylesheet" type="text/css" href="${jeegempath}/static/plugins/webuploader/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${jeegempath}/static/plugins/webuploader/image-upload/style.css" />

<script src="${jeegempath}/static/plugins/webuploader/js/webuploader.nolog.min.js"></script>
</head>
<body>
<%@include file="../../common/dialog.jsp" %>
	<div id="wrapper">
        <div id="container">
            <!--头部，相册选择和格式选择-->

            <div id="uploader">
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker"></div>
                        <p>或将照片拖到这里，单次最多可选300张</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;">
                    <div class="progress">
                        <span class="text">0%</span>
                        <span class="percentage"></span>
                    </div><div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
   <script type="text/javascript" src="${jeegempath}/static/plugins/webuploader/js/upload.js"></script>

</html>