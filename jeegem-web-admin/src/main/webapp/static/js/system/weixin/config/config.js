$(function () {
	getConfig();
});
function getConfig(){
	GEM.Tags.cleanForm("configForm");
	GEM.Model.loading();
	GEM.Ajax.doRequest(null,gempath +'/backstage/weixin/config/getConfig',null,function(data){
			var mc=data.obj;
			$("#configForm input[name$='appId']").val(mc.appId);
			$("#configForm input[name$='appSecret']").val(mc.appSecret);
			$("#configForm input[name$='token']").val(mc.token);
			$("#configForm input[name$='aseKey']").val(mc.aeskey);
			$("#configForm input[name$='turing']").prop("checked",mc.turing);
		GEM.Model.loadingClose();
	});
}

function showPW(o,id){
	var checked=o.checked;
	if(checked){
		$("#configForm input[name$='"+id+"']").attr("type","text");
	}else{
		$("#configForm input[name$='"+id+"']").attr("type","password");
	}
}