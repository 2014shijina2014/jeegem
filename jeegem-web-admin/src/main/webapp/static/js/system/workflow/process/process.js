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
	$('#uploadModel').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		cleanFile();
		GEM.Model.edit("uploadDiv","上传流程模型",function(){
			var upVal=$("#uploadModelFile").val();
			if(GEM.Object.notNull(upVal)){
				var that =$(this);
				$("#uploadForm").ajaxSubmit({
                    type:'post',
                    url:gempath +'/backstage/workflow/process/uploadModel',
                    success:function(data){
                    	var o=JSON.parse(data);
                    	var ores=o.res;
                    	var oresMsg=o.resMsg;
                    	if(ores==1){
                    		GEM.Model.info(oresMsg,function(){search();});  	
        		        	that.dialog("close");  
                    	}else{
                    		GEM.Model.error(oresMsg);  	
                    	}
                    }
                });
			}else{
				GEM.Model.error("请选择文件");  	
			}			
		});
	});
	//上传
	$('#uploadModelFile').ace_file_input({
		no_file:'请选择模型 ...',
		btn_choose:'选择',
		btn_change:'更改',
		droppable:false,
		onchange:null,
		thumbnail:false, //| true | large
		whitelist:'zip|bar|bpmn|bpmn20.xml',
		blacklist:'gif|png|jpg|jpeg|xls'
		//onchange:''
		//
	});
});
function search(){
	$("#searchBtn").trigger("click");
}
function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);	
	GEM.Model.loading();
	GEM.Ajax.doRequest("baseForm",gempath +'/backstage/workflow/process/findByPage',null,function(data){
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
            		 html+="<td class='center hidden-480'>"+GEM.Object.notEmpty(l.id)+"</td>";
            		 html+="<td class='center hidden-480'>"+GEM.Object.notEmpty(l.deploymentId)+"</td>";
            		 html+="<td class='center hidden-480'>"+GEM.Object.notEmpty(l.name)+"</td>";
            		 html+="<td class='center '>"+GEM.Object.notEmpty(l.key)+"</td>";
            		 html+="<td class='center '>"+GEM.Object.notEmpty(l.version)+"</td>";
            		 html+="<td class='center hidden-480'><a href='javascritp:void(0);' onclick='currentType(&apos;xml&apos;,&apos;"+l.id+"&apos;);return false' >"+l.resourceName+"</a></td>";
            		 html+="<td class='center hidden-480'><a href='javascritp:void(0);' onclick='currentType(&apos;image&apos;,&apos;"+l.id+"&apos;);return false' >"+l.resourceName+"</a></td>";
            		 html+="<td class='center hidden-480'>"+GEM.Date.Default(l.deploymentTime)+"</td>";
            		 html+=GEM.Tags.setFunction(l.id,permitBtn);
            		 html+="</tr>";		 
            	 } 
        		 $("#baseTable tbody").append(html);
        		 GEM.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }		 
    	 GEM.Model.loadingClose();
	 });
}
function del(processDefinitionId){
	GEM.Model.confirm("确认要删除流程定义吗？",function(){	
		GEM.Ajax.doRequest(null,gempath +'/backstage/workflow/process/del',{processDefinitionId:processDefinitionId},function(data){
			GEM.Model.info(data.resMsg,function(){search();});
		});
	});
}
function convertToModel(processDefinitionId){
	GEM.Model.confirm("确认要转换成模型吗？",function(){	
		GEM.Ajax.doRequest(null,gempath +'/backstage/workflow/process/convertToModel',{processDefinitionId:processDefinitionId},function(data){
			 GEM.Model.info(data.resMsg,function(){search();});
		});
	});
}

function currentType(type,pdId){
	var width=document.documentElement.clientWidth * 0.85+"px";
	var height=document.documentElement.clientHeight * 0.85+"px";
	layer.open({
	    type: 2,
	    title: "流程【"+type+"】",
	    shadeClose: true,
	    maxmin: true,
	    area: [width, height],
	    content: gempath+"/backstage/workflow/process/resource/read?resourceType="+type+"&processDefinitionId="+pdId
	});
}
function cleanFile(){
	$(".ace-file-input .remove").trigger("click");
}