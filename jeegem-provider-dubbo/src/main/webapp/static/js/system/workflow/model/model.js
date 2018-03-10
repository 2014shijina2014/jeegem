$(function () {
	getbaseList();
	//增加回车事件
	$("#baseForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if (keycode==13) {
			 search();
		 } 
	});
	//新加
	$('#addBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault(); 
		cleanForm();	
	              JEEGEM.Model.edit("auDiv","创建新模型",function(){
			 if(JEEGEM.Validate.form("auForm")){
				 var that =$(this);
				 that.dialog("close"); 
				 $("#auForm").submit();
				 setTimeout('search()',2000); 
			 }	
		});
	});
});
function search(){
	$("#searchBtn").trigger("click");
}
function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);	
              JEEGEM.Model.loading();
              JEEGEM.Ajax.doRequest("baseForm",jeegempath +'/backstage/workflow/model/findByPage',null,function(data){
		 $("#baseTable tbody").empty();
    		 var obj=data.obj;
        	 var list=obj.list;
        	 var results=list.results;
        	 var permitBtn=obj.permitBtn;
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
            		 html+="<td class='center'>"+JEEGEM.Object.notEmpty(l.key)+"</td>";
            		 html+="<td class='center'>"+JEEGEM.Object.notEmpty(l.name)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.version)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Date.Default(l.createTime)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Date.Default(l.lastUpdateTime)+"</td>";
            		 html+="<td class='left hidden-480'>"+JEEGEM.Object.notEmpty(l.metaInfo)+"</td>";
            		 html+=JEEGEM.Tags.setFunction(l.id,permitBtn);
            		 html+="</tr>";		 
            	 } 
        		 $("#baseTable tbody").append(html);
        		 JEEGEM.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }		 
    	 JEEGEM.Model.loadingClose();
	 });
}
function cleanForm(){
              JEEGEM.Tags.cleanForm("auForm");
}
function edit(modelId){
              JEEGEM.Model.confirm("确认要编辑模型吗?",function(){	
		window.open(gempath+"/backstage/workflow/model/edit?modelId="+modelId); 
	});
}
function del(modelId){
              JEEGEM.Model.confirm("确认要删除模型吗？",function(){	
	              JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/workflow/model/del',{modelId:modelId},function(data){
			 JEEGEM.Model.info(data.resMsg,function(){search();});
		});
	});
}
function exportForm(modelId){
              JEEGEM.Model.check("exportDiv","导出模型文件");
	$("#exportDiv input[name$='modelId']").val(modelId);
}
function exportFile(type){
	var modelId=$("#exportDiv input[name$='modelId']").val();
	window.open(gempath+"/backstage/workflow/model/export/"+modelId+"/"+type);
}

function deployModel(modelId){
              JEEGEM.Model.confirm("确认要模型部署流程吗？",function(){	
	              JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/workflow/model/deploy',{modelId:modelId},function(data){
		              JEEGEM.Model.info(data.resMsg,function(){search();});
		});
	});
}
