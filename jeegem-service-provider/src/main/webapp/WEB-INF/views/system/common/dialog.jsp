<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- #dialog-confirm -->
<div id="jeegemConfirm" class="hide">
	<div class="center">
		<h4><i class="icon-hand-right blue bigger-120"></i> <span id="jeegemConfirmStr"></span></h4>
	</div>
</div>
<div id="jeegemInfo" class="hide">
	<div class="center">
		<h4><i class="icon-info-sign blue bigger-120"></i> <span id="jeegemInfoStr"></span></h4>
	</div>
</div>
<div id="jeegemError" class="hide">
	<div class="center">
		<h4><i class="icon-warning-sign red bigger-120"></i> <span id="jeegemErrorStr"></span></h4>
	</div>
</div>
<div id="jeegemLoading" class="hide">
	<div class="center">
		<h4><img alt="loading" src="<c:url value="/static/images/system/loading.gif"/>"><span id="jeegemLoadingStr"></span></h4>
	</div>
</div>