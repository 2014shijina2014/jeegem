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
              JEEGEM.Ajax.doRequest("baseForm",jeegempath +'/backstage/workflow/online/myTask/findTodoByPage',null,function(data){
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
            		 html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.id)+"</td>";
            		 var taskDKey=l.taskDefinitionKey;
            		 html+="<td class='center'>"+JEEGEM.Object.notEmpty(taskDKey)+"</td>";
            		 html+="<td class='center'>"+JEEGEM.Object.notEmpty(l.name)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.processDefinitionId)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.processInstanceId)+"</td>";
            		 /*html+="<td class='center hidden-480'>"+JEEGEM.Object.notEmpty(l.priority)+"</td>";*/
            		 html+="<td class='center hidden-480'>"+JEEGEM.Date.Default(l.createTime)+"</td>";
            		 html+="<td class='center hidden-480'>"+JEEGEM.Date.Default(l.dueDate)+"</td>";
            		 html+="<td class='left hidden-480'>"+JEEGEM.Object.notEmpty(l.description)+"</td>";
            		 html+="<td class='left hidden-480'>"+JEEGEM.Object.notEmpty(l.owner)+"</td>";      
            		 if('deptAudit'==taskDKey||'hrAudit'==taskDKey){
            			 html+="<td class='left'><button class='btn btn-xs btn-success' onclick='todoTask(&apos;"+l.id+"&apos;,&apos;"+l.processInstanceId+"&apos;)' ><i class='icon-pencil align-top bigger-125'></i>办理</button></td>";
            		 }else{
            			 html+="<td class='left'><button class='btn btn-xs btn-grey' onclick='adjustTask(&apos;"+l.id+"&apos;,&apos;"+l.processInstanceId+"&apos;)' ><i class='icon-pencil align-top bigger-125'></i>调整申请</button></td>";
            		 }	
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
function adjustTask(id,pId){
	cleanAdjustForm();
              JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/workflow/online/myTask/findTask',{pId:pId},function(data){
		var obj=data.obj;
		setAdjustForm(obj);
	              JEEGEM.Model.adjust("adjustDiv","调整申请"
			,function(){	
			//重新提交申请
			var that=$(this);
			var vars =[{key:'reApply',value:true,type:'B'}];
			var description=$("#adjustForm textarea[name$='description']").val();
			if(JEEGEM.Object.notNull(description)){
				if(JEEGEM.Validate.form("adjustForm")){
				              JEEGEM.Model.confirm("确认提交申请吗?",function(){	
						var ovar=JEEGEM.Object.comVar(vars);
						ovar.description=description;
						ovar.pId=pId;
					              JEEGEM.Ajax.doRequest("",jeegempath +'/backstage/workflow/online/myTask/adjust/'+id,ovar,function(data){
						              JEEGEM.Model.info(data.resMsg,function(){search();that.dialog("close");});
						});
					});
				} 
			}else{
				$("#adjustForm textarea[name$='description']").tips({side:1,msg:"请填写事由！",bg:'#FF2D2D',time:1});
			}
		},function(){
			//放弃申请
			var that=$(this);
			var vars =[{key:'reApply',value:false,type:'B'}];
		              JEEGEM.Model.confirm("确认放弃吗？",function(){	
			              JEEGEM.Ajax.doRequest("",jeegempath +'/backstage/workflow/online/myTask/complete/'+id,JEEGEM.Object.comVar(vars),function(data){
				              JEEGEM.Model.info(data.resMsg,function(){search();that.dialog("close");});
				});
			});
		});
	});
}
function todoTask(id,pId){
	cleanTodoForm();
              JEEGEM.Ajax.doRequest(null,jeegempath +'/backstage/workflow/online/myTask/findTask',{pId:pId},function(data){
		var obj=data.obj;
		setTodoForm(obj);
	              JEEGEM.Model.audit("auDiv","审核"
		 ,function(){	
			//同意申请
			var that=$(this);
		              JEEGEM.Model.confirm("确认同意吗？",function(){	
				var vars =[{key:'auditPass',value:true,type:'B'}];
			              JEEGEM.Ajax.doRequest("",jeegempath +'/backstage/workflow/online/myTask/complete/'+id,JEEGEM.Object.comVar(vars),function(data){
				              JEEGEM.Model.info(data.resMsg,function(){search();that.dialog("close");});
				});
			});
		},function(){
			//驳回申请
			$(this).dialog("close");
		              JEEGEM.Tags.cleanForm("rejectDiv");
		              JEEGEM.Model.edit("rejectDiv","驳回",function(){	
				 var rejectReason=$("#rejectForm textarea[name$='rejectReason']").val();
				 if(JEEGEM.Object.notNull(rejectReason)){
					 var that =$(this);
					 JEEGEM.Model.confirm("确认驳回吗？",function(){			 
						 var vars =[{key:'auditPass',value:false,type:'B'}];
					     var ovar=JEEGEM.Object.comVar(vars);
						 ovar.rejectReason=rejectReason;
						 ovar.pId=pId;
						 JEEGEM.Ajax.doRequest("",jeegempath +'/backstage/workflow/online/myTask/reject/'+id,ovar,function(data){
							 JEEGEM.Model.info(data.resMsg,function(){search();that.dialog("close");});
						 });	
						});     
				 }else{
			    	$("#rejectForm textarea[name$='rejectReason']").tips({side:1,msg:"请填写理由！",bg:'#FF2D2D',time:1});
			     }
			});
		}
		);
		
	});
}
function setTodoForm(l){
	if(JEEGEM.Object.notNull(l)){
		$("#auForm input[name$='id']").val(l.id);
		$("#auForm input[name$='org']").val(JEEGEM.Object.notEmpty(l.org));
		$("#auForm input[name$='name']").val(JEEGEM.Object.notEmpty(l.name));
		$("#auForm input[name$='approver']").val(JEEGEM.Object.notEmpty(l.approver));
		$("#auForm input[name$='typeName']").val(JEEGEM.Object.notEmpty(l.typeName));
		$("#auForm input[name$='beginTime']").val(JEEGEM.Date.Default(l.beginTime));
		$("#auForm input[name$='endTime']").val(JEEGEM.Date.Default(l.endTime));
		$("#auForm textarea[name$='description']").val(JEEGEM.Object.notEmpty(l.description));
	}
}
function setAdjustForm(l){
	if(JEEGEM.Object.notNull(l)){
		$("#adjustForm input[name$='id']").val(l.id);
		$("#adjustForm input[name$='org']").val(JEEGEM.Object.notEmpty(l.org));
		$("#adjustForm input[name$='name']").val(JEEGEM.Object.notEmpty(l.name));
		$("#adjustForm input[name$='approver']").val(JEEGEM.Object.notEmpty(l.approver));
		$("#adjustForm input[name$='typeName']").val(JEEGEM.Object.notEmpty(l.typeName));
		$("#adjustForm input[name$='type']").val(JEEGEM.Object.notEmpty(l.type));
		$("#adjustForm input[name$='beginTime']").val(JEEGEM.Date.Default(l.beginTime));
		$("#adjustForm input[name$='endTime']").val(JEEGEM.Date.Default(l.endTime));
		$("#adjustForm textarea[name$='description']").val(JEEGEM.Object.notEmpty(l.description));
		$("#adjustForm textarea[name$='rejectReason']").val(JEEGEM.Object.notEmpty(l.rejectReason));
	}
}

function cleanTodoForm(){
              JEEGEM.Tags.cleanForm("auForm");
}
function cleanAdjustForm(){
              JEEGEM.Tags.cleanForm("adjustForm");
}
