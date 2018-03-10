$(function () {
	getbaseList();
	//增加回车事件
	$("#baseForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if (keycode==13) {
			 search();
		 } 
	});
});
function search(){
	$("#searchBtn").trigger("click");
}
function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);	
              JEEGEM.Model.loading();
              JEEGEM.Ajax.doRequest("baseForm",jeegempath +'/backstage/workflow/online/myTask/findSignByPage',null,function(data){
		 $("#baseTable tbody").empty();
	 	 var obj=data.obj;
    	 var list=obj.list;
    	 var results=list.results;
    	 //var permitBtn=obj.permitBtn;
     	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
    	 var html="";
    		 if(results!=null&&results.length>0){
        		 var leng=(pageNum-1)*pageSize;//计算序号
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.id+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center hidden-480'>"+(i+leng+1)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.id)+"</td>";
            		 html+="<td class='center'>"+JEEGEM.Object.notEmpty(l.taskDefinitionKey)+"</td>";
            		 html+="<td class='center'>"+JEEGEM.Object.notEmpty(l.name)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.processDefinitionId)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.processInstanceId)+"</td>";
            		 /*html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.priority)+"</td>";*/
            		 html+="<td class='center hidden-480'>"+JEEGEM.Date.Default(l.createTime)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Date.Default(l.dueDate)+"</td>";
            		 html+="<td class='left hidden-480'>"+JEEGEM.Object.notEmpty(l.description)+"</td>";
            		 html+="<td class='left hidden-480'>"+JEEGEM.Object.notEmpty(l.owner)+"</td>";     		 
            		 html+="<td class='left'><button class='btn btn-xs btn-info'  onclick='claimTask(&apos;"+l.id+"&apos;)' ><i class='icon-pencil align-top bigger-125'></i>签收</button></td>";
            		 html+="</tr>";		 
            	 } 
        		 $("#baseTable tbody").append(html);
        		 JEEGEM.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='12' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }	 
    	 JEEGEM.Model.loadingClose();
	 });
}
function claimTask(taskId){
              JEEGEM.Model.confirm("确认签收吗？",function(){	
	              JEEGEM.Ajax.doRequest("",jeegempath +'/backstage/workflow/online/myTask/claim/'+taskId,null,function(data){
		              JEEGEM.Model.info(data.resMsg,function(){search();});  		
		});
	});
}