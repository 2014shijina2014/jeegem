$(function () {
	//下拉框
	GEM.Dict.setSelect("selectisValid","isValid",2,"全部");
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
		loadRoleTree();
		GEM.Model.edit("auDiv","新增",function(){
			 if(GEM.Validate.form("auForm")){
				 var that =$(this);
				 GEM.Ajax.doRequest("auForm",gempath +'/backstage/account/add',null,function(data){
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
		$('#baseTable input[name="ids"]:checked').each(function(){    
			chks.push($(this).val());    
		});     
		if(chks.length==0) {
			GEM.Model.info("您没有选择任何内容!"); 
		}else{
			GEM.Model.confirm("确认要删除选中的数据吗?",function(){	
				GEM.Ajax.doRequest(null,gempath +'/backstage/account/delBatch',{chks:chks.toString()},function(data){
					GEM.Model.info(data.resMsg,function(){search();});
				});
			});		
		}		
	});
});
function search(){
	$("#searchBtn").trigger("click");
}
function loadRoleTree(){
	GEM.Ajax.doRequest(null,gempath +'/backstage/account/roleTree',null,function(data){
		$.fn.zTree.init($("#roleTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
	});
}
function emptyRole(){
	$("#roleName").prop("value","");
	$("#auForm input[name$='roleId']").prop("value","0");
}
var preisShow=false;//窗口是否显示
function showRole() {
	if(preisShow){
		hideRole();
	}else{
		var obj = $("#roleName");
		var offpos = $("#roleName").position();
		$("#roleContent").css({left:offpos.left+"px",top:offpos.top+obj.heith+"px"}).slideDown("fast");	
		preisShow=true;
	}
}
function clickRole(e, treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if(check){
		var zTree = $.fn.zTree.getZTreeObj("roleTree"),
		nodes = zTree.getSelectedNodes(),v ="",n ="",o="",p="";	
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";//获取name值
			n += nodes[i].id + ",";//获取id值
			o += nodes[i].other + ",";//获取自定义值
			var pathNodes=nodes[i].getPath();
			for(var y=0;y<pathNodes.length;y++){
				p+=pathNodes[y].name+"/";//获取path/name值
			}
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);	
		if (n.length > 0 ) n = n.substring(0, n.length-1);
		if (o.length > 0 ) o = o.substring(0, o.length-1);
		if (p.length > 0 ) p = p.substring(0, p.length-1);
		if(o=='r'){//判断是否角色	
			$("#roleName").val(p);
			n=n.replace("role","");
			$("#auForm input[name$='roleId']").prop("value",n);
			//因为单选选择后直接关闭，如果多选请另外写关闭方法
			hideRole();
		}	
	}
}
function hideRole(){
	$("#roleContent").fadeOut("fast");
	preisShow=false;
}
function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);	
	GEM.Model.loading();
	GEM.Ajax.doRequest("baseForm",gempath +'/backstage/account/findByPage',null,function(data){
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
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.accountId+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center hidden-480'>"+(i+leng+1)+"</td>";
            		 html+="<td class='center'>"+GEM.Object.notEmpty(l.loginName)+"</td>";
            		 html+="<td class='center hidden-480' >"+GEM.Object.notEmpty(l.name)+"</td>";
            		 html+="<td class='center '>"+GEM.Object.notEmpty(l.roleName)+"</td>";
            		 html+="<td class='center hidden-480'>"+GEM.Object.notEmpty(l.email)+"</td>";
            		 if(l.isValid==1) html+="<td class='center hidden-480'><span class='label label-sm label-success'>有效</span></td>";
            		 else             html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>无效</span></td>";
            		 html+="<td class='center hidden-480'>"+GEM.Date.Default(l.loginLog.loginTime)+"</td>";
            		 html+="<td class='center hidden-480'>"+GEM.Object.notEmpty(l.loginLog.loginIP)+"</td>";
            		 html+=GEM.Tags.setFunction(l.accountId,permitBtn);
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
function check(accountId){
	cleanForm();
	GEM.Ajax.doRequest(null,gempath +'/backstage/account/roleTree',null,function(data){
		$.fn.zTree.init($("#roleTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
		GEM.Ajax.doRequest(null,gempath +'/backstage/account/find',{accountId:accountId},function(data){
		    setForm(data);
		    GEM.Model.check("auDiv");       
		});
	});
}
function del(accountId){
	GEM.Model.confirm("确认删除吗？",function(){	
		GEM.Ajax.doRequest(null,gempath +'/backstage/account/del',{accountId:accountId},function(data){
			GEM.Model.info(data.resMsg,function(){search();});
		});
	});
}
function edit(accountId){
	cleanForm();
	GEM.Ajax.doRequest(null,gempath +'/backstage/account/roleTree',null,function(data){
		$.fn.zTree.init($("#roleTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
		GEM.Ajax.doRequest(null,gempath +'/backstage/account/find',{accountId:accountId},function(data){
		    setForm(data);   
		    GEM.Model.edit("auDiv","修改",function(){
		    	if(GEM.Validate.form("auForm")){
					var that =$(this);
					GEM.Ajax.doRequest("auForm",gempath +'/backstage/account/update',null,function(data){
					    that.dialog("close");
					    GEM.Model.info(data.resMsg,function(){search();});	
					});
				}	
		    });
		});
	});
}
function resetPwd(accountId){
	$("#resetPwdFrom input[name$='accountId']").val(accountId);//类型
	$("#resetPwdFrom input[name$='pwd']").val('');//类型
	$("#resetPwdDiv").removeClass('hide').dialog({resizable: false,modal:true,title:"<div class='widget-header'><h4 class='smaller'>密码重置</h4></div>",title_html: true,
		buttons: [
		  {  
			 html: "<i class='icon-ok bigger-110'></i>&nbsp;保存","class" : "btn btn-primary btn-xs",
			 click: function() {
				 if(GEM.Validate.form("resetPwdFrom")){
					 var that =$(this);
					 GEM.Ajax.doRequest("resetPwdFrom",gempath +'/backstage/account/resetPwd',null,function(data){
						 that.dialog("close");
			        	GEM.Model.info(data.resMsg,function(){search();});		
					 });
				 }		
			}
		  },
		   {
			 html: "<i class='icon-remove bigger-110'></i>&nbsp;取消","class":"btn btn-xs",
			 click: function() {
				$(this).dialog("close");
			 }
		   }
		]
	});
}
function cleanForm(){
	GEM.Tags.isValid("auForm","1");
	GEM.Tags.cleanForm("auForm");
	$("#auForm input[name$='roleId']").val('0');//上级资源
	$("#auForm input[name$='loginName']").prop("disabled",false); 
	hideRole();
}
function setForm(data){
	var l=data.obj;
	$("#auForm input[name$='accountId']").val(l.accountId);
	GEM.Tags.isValid("auForm",(GEM.Object.notNull(l.isValid)?l.isValid:"0"));
	$("#auForm input[name$='loginName']").val(GEM.Object.notEmpty(l.loginName));
	$("#auForm input[name$='loginName']").prop("disabled",true); 
	$("#auForm input[name$='name']").val(GEM.Object.notEmpty(l.name));
	$("#auForm input[name$='email']").val(GEM.Object.notEmpty(l.email));
	$("#auForm textarea[name$='description']").val(GEM.Object.notEmpty(l.description));//描述
	var treeObj = $.fn.zTree.getZTreeObj("roleTree");
	var nodes =treeObj.getNodesByParam("id","role"+l.roleId);
	if(nodes.length>0){
		treeObj.selectNode(nodes[0]);
		clickRole(null,null,nodes[0]);
	}
}
