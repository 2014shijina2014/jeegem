$(function () {
	GEM.Dict.setSelect("selectLeaveType","leaveType","1");
	$("input[name='beginTime']").datetimepicker({
		format:'yyyy-mm-dd hh:ii:00',language:'zh-CN',weekStart:1,todayBtn:1,autoclose: 1,todayHighlight: 1,startView: 2,minView:0,
      }).on('changeDate', function(ev){
    	  	var beginTime=$("input[name='beginTime']").val();
    	  	$("input[name='endTime']").datetimepicker('setStartDate',beginTime);
    	 });
	$("input[name='endTime']").datetimepicker({
		format: 'yyyy-mm-dd hh:ii:00',language:'zh-CN',weekStart: 1,todayBtn:1,autoclose:1,todayHighlight:1,startView:2,minView:0,
	}).on('changeDate', function(ev){
	  	var endTime=$("input[name='endTime']").val();
	  	$("input[name='beginTime']").datetimepicker('setEndDate',endTime);
	 });	
});
function submitApply(){
	if(GEM.Validate.form("leaveFrom")){
		var type=$("#leaveFrom select[name='type']").val();
		if(GEM.Object.notNull(type)){
			GEM.Model.confirm("确认提交申请吗?",function(){	
				GEM.Ajax.doRequest("leaveFrom",gempath +'/backstage/workflow/online/apply/start',"",function(data){
					 GEM.Model.info(data.resMsg);
				});
			});
		}else{
			$("#leaveFrom select[name='type']").tips({side:1,msg:"请选择类型！",bg:'#FF2D2D',time:1});
		}
	}
}