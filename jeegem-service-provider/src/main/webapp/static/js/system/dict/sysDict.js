$(function () {
	JEEGEM.Dict.setSelect("selectisValid","isValid",2,'全部');
	getbaseList();
	//增加回车事件
	$("#baseForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if(keycode==13){
			 search();
		 }
	});
	//新加
	$('#addBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		cleanForm();
		JEEGEM.Model.edit("auDiv","新增",function(){
			 if(JEEGEM.Validate.form("auForm")){
				 var that =$(this);
				 JEEGEM.Ajax.doRequest("auForm",jeegempath +'/backstage/sysDict/add',null,function(data){
		        	that.dialog("close");      
		        	JEEGEM.Model.info(data.resMsg,function(){search();});  	
				 });
			 }	
		});
	});
	//批量删除
	$('#delBatchBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		var chks =[];    
		$('#baseTable input[name="ids"]:checked').each(function(){chks.push($(this).val());});    
		if(chks.length==0) {
			 JEEGEM.Model.info("您没有选择任何内容!"); 
		}else{
			JEEGEM.Model.confirm("确认要删除选中的数据吗?",function(){	
				JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/sysDict/delBatch',{chks:chks.toString()},function(data){
					JEEGEM.Model.info(data.resMsg,function(){search();});
				});
			});
		}	
	});
});
function search(){
	$("#searchBtn").trigger("click");
}

function getbaseList(init){
	if(init==1) $("#baseForm .pageNum").val(1);	
	JEEGEM.Model.loading();
	JEEGEM.Ajax.doRequest("baseForm",jeegempath +'/backstage/sysDict/findByPage',null,function(data){
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
            		 html+="<td class='center'>"+JEEGEM.Object.notEmpty(l.paramName)+"</td>";
            		 html+="<td class='center hidden-480' >"+JEEGEM.Object.notEmpty(l.paramKey)+"</td>";
            		 html+="<td class='center'>"+JEEGEM.Object.notEmpty(l.paramValue)+"</td>";
            		 if(l.isValid==1) html+="<td class='center hidden-480'><span class='label label-sm label-success'>有效</span></td>";
            		 else             html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>无效</span></td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Date.Default(l.createTime)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Date.Default(l.updateTime)+"</td>";
            		 html+=JEEGEM.Tags.setFunction(l.id,permitBtn);
            		 html+="</tr>";		 
            	 } 
        		 $("#baseTable tbody").append(html);
        		 JEEGEM.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='9' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }	
        	 JEEGEM.Model.loadingClose();
	});
}
function check(id){
	cleanForm();
	JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/sysDict/find',{id:id},function(data){
    	setForm(data);
    	JEEGEM.Model.check("auDiv");     	
	});
}
function del(id){
	JEEGEM.Model.confirm("确认删除吗？",function(){	
		JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/sysDict/del',{id:id},function(data){
	      	JEEGEM.Model.info(data.resMsg,function(){search();});
		});
	});	
}
function edit(id){
	cleanForm();
	JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/sysDict/find',{id:id},function(data){
    		setForm(data);   
    		JEEGEM.Model.edit("auDiv","修改",function(){
    			 if(JEEGEM.Validate.form("auForm")){
					 var that =$(this);
					 JEEGEM.Ajax.doRequest("auForm",jeegempath +'/backstage/sysDict/update',null,function(data){
						 if(data.res==1){
			        		 that.dialog("close");
			        		 JEEGEM.Model.info(data.resMsg,function(){search();});
			        	 }else{
			        		 JEEGEM.Model.error(data.resMsg);
			        	 } 		
					 });
				 }
    		});   
	});
}
function cleanForm(){
	JEEGEM.Tags.isValid("auForm","1");
	JEEGEM.Tags.cleanForm("auForm");
}
function setForm(data){
	var l=data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='paramName']").val(JEEGEM.Object.notEmpty(l.paramName));
	$("#auForm input[name$='paramKey']").val(JEEGEM.Object.notEmpty(l.paramKey));
	$("#auForm input[name$='paramValue']").val(JEEGEM.Object.notEmpty(l.paramValue));
	JEEGEM.Tags.isValid("auForm",(JEEGEM.Object.notNull(l.isValid)?l.isValid:"0"));
	$("#auForm textarea[name$='description']").val(JEEGEM.Object.notEmpty(l.description));
}
