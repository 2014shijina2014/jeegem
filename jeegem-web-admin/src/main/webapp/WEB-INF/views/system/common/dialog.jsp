<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- #dialog-confirm -->
<div id="gemConfirm" class="hide">
	<div class="center">
		<h4><i class="icon-hand-right blue bigger-120"></i> <span id="gemConfirmStr"></span></h4>
	</div>
</div>
<div id="gemInfo" class="hide">
	<div class="center">
		<h4><i class="icon-info-sign blue bigger-120"></i> <span id="gemInfoStr"></span></h4>
	</div>
</div>
<div id="gemError" class="hide">
	<div class="center">
		<h4><i class="icon-warning-sign red bigger-120"></i> <span id="gemErrorStr"></span></h4>
	</div>
</div>
<div id="gemLoading" class="hide">
	<div class="center">
		<h4><img alt="loading" src="<c:url value="/static/images/system/loading.gif"/>"><span id="gemLoadingStr"></span></h4>
	</div>
</div>