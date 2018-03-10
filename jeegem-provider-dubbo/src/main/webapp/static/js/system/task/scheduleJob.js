$(function () {
              JEEGEM.Dict.setSelect("selectStatus","isTask",2,'全部');
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
	              JEEGEM.Model.edit("auDiv","新增任务",function(){
			 if(JEEGEM.Validate.form("auForm")){
				 var that =$(this);
				 JEEGEM.Ajax.doRequest("auForm",jeegempath +'/backstage/task/scheduleJob/add',null,function(data){
		        	that.dialog("close");      
		                      JEEGEM.Model.info(data.resMsg,function(){search();});
				 });
			 }	
		});
	});
	//排序显示
	$("#cronTh").tooltip({hide:{effect:"slideDown",delay:250}});
});
function search(){
	$("#searchBtn").trigger("click");
}

function getbaseList(init){
	if(init==1) $("#baseForm .pageNum").val(1);	
              JEEGEM.Model.loading();
              JEEGEM.Ajax.doRequest("baseForm",jeegempath +'/backstage/task/scheduleJob/findByPage',null,function(data){
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
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.scheduleJobId+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center hidden-480'>"+(i+leng+1)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.jobName)+"</td>";
            		 html+="<td class='center'>"+JEEGEM.Object.notEmpty(l.aliasName)+"</td>";
            		 html+="<td class='center hidden-480' >"+JEEGEM.Object.notEmpty(l.jobGroup)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.jobClass)+"</td>";
            		 if(l.status==1) html+="<td class='center'><span class='label label-sm label-success'>启用</span></td>";
            		 else             html+="<td class='center'><span class='label label-sm arrowed-in'>停用</span></td>"; 	
            		 html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.cronExpression)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.description)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Date.Default(l.createTime)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Date.Default(l.updateTime)+"</td>";
            		 html+=JEEGEM.Tags.setFunction(l.scheduleJobId,permitBtn);
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
function check(scheduleJobId){
	cleanForm();
              JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/task/scheduleJob/find',{scheduleJobId:scheduleJobId},function(data){
    		setForm(data);
    	              JEEGEM.Model.check("auDiv","查看任务");     	
	});
}
function del(scheduleJobId){
              JEEGEM.Model.confirm("确认删除吗？",function(){	
	              JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/task/scheduleJob/del',{scheduleJobId:scheduleJobId},function(data){
	      	 JEEGEM.Model.info(data.resMsg,function(){search();});
		});
	});	
}
function edit(scheduleJobId){
	cleanForm();
              JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/task/scheduleJob/find',{scheduleJobId:scheduleJobId},function(data){
    		setForm(data);   
    	              JEEGEM.Model.edit("auDiv","修改任务",function(){
    			 if(JEEGEM.Validate.form("auForm")){
					 var that =$(this);
					 JEEGEM.Ajax.doRequest("auForm",jeegempath +'/backstage/task/scheduleJob/update',null,function(data){
			        		 that.dialog("close");
			        		 JEEGEM.Model.info(data.resMsg,function(){search();});	
					 });
				 }
    		});    
	});
}
function runOnce(scheduleJobId){
              JEEGEM.Model.confirm("确认运行一次吗？",function(){	
	              JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/task/scheduleJob/runOnce',{scheduleJobId:scheduleJobId},function(data){
	      	 JEEGEM.Model.info(data.resMsg,function(){search();});
		});
	});
}
function resumeJob(scheduleJobId){
              JEEGEM.Model.confirm("确认启动吗？",function(){	
	              JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/task/scheduleJob/resumeJob',{scheduleJobId:scheduleJobId},function(data){
	                    JEEGEM.Model.info(data.resMsg,function(){search();});
		});
	});
}
function pauseJob(scheduleJobId){
              JEEGEM.Model.confirm("确认暂停吗？",function(){	
	              JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/task/scheduleJob/pauseJob',{scheduleJobId:scheduleJobId},function(data){
	      	 JEEGEM.Model.info(data.resMsg,function(){search();});
		});
	});
}

function cleanForm(){
	$("#auForm input[name$='status'][value='0']").parent("label").trigger("click");
              JEEGEM.Tags.cleanForm("auForm");
}
function setForm(data){
	var l=data.obj;
	$("#auForm input[name$='scheduleJobId']").val(JEEGEM.Object.notEmpty(l.scheduleJobId));
	$("#auForm input[name$='jobName']").val(JEEGEM.Object.notEmpty(l.jobName));
	$("#auForm input[name$='jobGroup']").val(JEEGEM.Object.notEmpty(l.jobGroup));
	$("#auForm input[name$='jobClass']").val(JEEGEM.Object.notEmpty(l.jobClass));
	$("#auForm input[name$='aliasName']").val(JEEGEM.Object.notEmpty(l.aliasName));
	$("#auForm input[name$='cronExpression']").val(JEEGEM.Object.notEmpty(l.cronExpression));
	$("#auForm input[name$='status'][value='"+(JEEGEM.Object.notNull(l.status)?l.status:"0")+"']").parent("label").trigger("click");
	$("#auForm textarea[name$='description']").val(l.description);
}
