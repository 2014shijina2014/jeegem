$(function () {
	GEM.Dict.setSelect("selectisValid","isValid",2,'全部');
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
		GEM.Model.edit("auDiv","新增",function(){
			 if(GEM.Validate.form("auForm")){
				 var that =$(this);
				 GEM.Ajax.doRequest("auForm",gempath +'/backstage/sysDict/add',null,function(data){
		        	that.dialog("close");      
		        	GEM.Model.info(data.resMsg,function(){search();});  	
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
			 GEM.Model.info("您没有选择任何内容!"); 
		}else{
			GEM.Model.confirm("确认要删除选中的数据吗?",function(){	
				GEM.Ajax.doRequest(null,gempath +'/backstage/sysDict/delBatch',{chks:chks.toString()},function(data){
					GEM.Model.info(data.resMsg,function(){search();});
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
	GEM.Model.loading();
	GEM.Ajax.doRequest("baseForm",gempath +'/backstage/sysDict/findByPage',null,function(data){
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
            		 html+="<td class='center'>"+GEM.Object.notEmpty(l.paramName)+"</td>";
            		 html+="<td class='center hidden-480' >"+GEM.Object.notEmpty(l.paramKey)+"</td>";
            		 html+="<td class='center'>"+GEM.Object.notEmpty(l.paramValue)+"</td>";
            		 if(l.isValid==1) html+="<td class='center hidden-480'><span class='label label-sm label-success'>有效</span></td>";
            		 else             html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>无效</span></td>";
            		 html+="<td class='center hidden-480'>"+GEM.Date.Default(l.createTime)+"</td>";
            		 html+="<td class='center hidden-480'>"+GEM.Date.Default(l.updateTime)+"</td>";
            		 html+=GEM.Tags.setFunction(l.id,permitBtn);
            		 html+="</tr>";		 
            	 } 
        		 $("#baseTable tbody").append(html);
        		 GEM.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='9' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }	
        	 GEM.Model.loadingClose();
	});
}
function check(id){
	cleanForm();
	GEM.Ajax.doRequest(null,gempath +'/backstage/sysDict/find',{id:id},function(data){
    	setForm(data);
    	GEM.Model.check("auDiv");     	
	});
}
function del(id){
	GEM.Model.confirm("确认删除吗？",function(){	
		GEM.Ajax.doRequest(null,gempath +'/backstage/sysDict/del',{id:id},function(data){
	      	GEM.Model.info(data.resMsg,function(){search();});
		});
	});	
}
function edit(id){
	cleanForm();
	GEM.Ajax.doRequest(null,gempath +'/backstage/sysDict/find',{id:id},function(data){
    		setForm(data);   
    		GEM.Model.edit("auDiv","修改",function(){
    			 if(GEM.Validate.form("auForm")){
					 var that =$(this);
					 GEM.Ajax.doRequest("auForm",gempath +'/backstage/sysDict/update',null,function(data){
						 if(data.res==1){
			        		 that.dialog("close");
			        		 GEM.Model.info(data.resMsg,function(){search();});
			        	 }else{
			        		 GEM.Model.error(data.resMsg);
			        	 } 		
					 });
				 }
    		});   
	});
}
function cleanForm(){
	GEM.Tags.isValid("auForm","1");
	GEM.Tags.cleanForm("auForm");
}
function setForm(data){
	var l=data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='paramName']").val(GEM.Object.notEmpty(l.paramName));
	$("#auForm input[name$='paramKey']").val(GEM.Object.notEmpty(l.paramKey));
	$("#auForm input[name$='paramValue']").val(GEM.Object.notEmpty(l.paramValue));
	GEM.Tags.isValid("auForm",(GEM.Object.notNull(l.isValid)?l.isValid:"0"));
	$("#auForm textarea[name$='description']").val(GEM.Object.notEmpty(l.description));
}
