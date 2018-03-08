<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../../common/includeBaseSet.jsp" %>
<%@include file="../../common/includeSystemSet.jsp" %>
<link rel="stylesheet" href="${jeegempath}/static/plugins/webuploader/css/webuploader.css" />
<script src="${jeegempath}/static/plugins/webuploader/js/webuploader.nolog.min.js"></script>
</head>
<body>
<%@include file="../../common/dialog.jsp" %>
	<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div id="thelist" class="uploader-list"></div>
    <div class="btns">
        <div id="picker">选择文件</div>
    </div>
    <!-- style只为显示效果，真正用请去掉 -->
			<div>存放位置:<input style="width:100%" id="savePath" readonly="readonly" type="text" name="savePath" ></div>
</div>
</body>
<script type="text/javascript">
// 图片上传demo
jQuery(function() {
    var $ = jQuery,
        // Web Uploader实例
        uploader;
    // 初始化Web Uploader
    uploader = WebUploader.create({
        // 自动上传。
        auto: true,
        // swf文件路径
        swf: jeegempath + '/static/plugins/webuploader/js/Uploader.swf',
        // 文件接收服务端。
        server: jeegempath +'/backstage/tool/webuploader/uploadFile',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        //不压缩文件
   		compress: null
    });
 // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress .progress-bar');
        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
              '</div>' +
            '</div>').appendTo( $li ).find('.progress-bar');
        }
        $li.find('p.state').text('上传中');
        $percent.css( 'width', percentage * 100 + '%' );
    });
    uploader.on( 'uploadSuccess', function(file,json) {
        $( '#'+file.id ).find('p.state').text('已上传');
        if("1"==json.res){
        	$( '#'+file.id ).addClass('upload-state-done');
        	var savePath=$("#savePath").val();
        	if(JEEGEM.Object.notNull(savePath)){
        		savePath+=","+json.saveUrl;
        	}else{
        		savePath=json.saveUrl;
        		JEEGEM.Model.info(json.resMsg);
        	}
        	$("#savePath").val(savePath);
        	
        }else{
        	JEEGEM.Model.error(json.resMsg);
        	 var $li = $( '#'+file.id ),$error = $li.find('div.error');
             // 避免重复创建
             if ( !$error.length )$error = $('<div class="error"></div>').appendTo( $li );
             $error.text('上传失败');
        }      
    });
    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
    });
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });
})
</script>
</html>