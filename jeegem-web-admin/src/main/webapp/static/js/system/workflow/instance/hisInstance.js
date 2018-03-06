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
	GEM.Model.loading();
	GEM.Ajax.doRequest("baseForm",gempath +'/backstage/workflow/instance/his/findByPage',null,function(data){
		 $("#baseTable tbody").empty();   
    		 var obj=data.obj;
        	 var list=obj.list;
        	 var results=list.results;
         	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 var leng=(pageNum-1)*pageSize;//计算序号
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.id+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center hidden-480'>"+(i+leng+1)+"</td>";
            		 html+="<td class='center hidden-480'>"+GEM.Object.notEmpty(l.id)+"</td>";
            		 html+="<td class='center'>"+GEM.Object.notEmpty(l.processDefinitionId)+"</td>";
            		 html+="<td class='center hidden-480'>"+GEM.Date.Default(l.startTime)+"</td>";
            		 html+="<td class='center hidden-480'>"+GEM.Date.Default(l.endTime)+"</td>";
            		 html+="<td class='center'>"+GEM.Object.notEmpty(l.deleteReason)+"</td>";
            		 html+="</tr>";		 
            	 } 
        		 $("#baseTable tbody").append(html);
        		 GEM.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='7' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }		 
    	 GEM.Model.loadingClose();
	 });
}
