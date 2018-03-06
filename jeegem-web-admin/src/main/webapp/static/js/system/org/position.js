$(function () {
	GEM.Dict.setSelect("selectPosType","positionType",'1');
	loadOrgTree();
	loadPreOrgTree();
	//新增职务
	$('#addPosBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		cleanPosForm();
		GEM.Model.edit("auPosDiv","新增职务",function(){
			 var selectType=$("#auPosForm select[name$='type']").val();
			 if(!GEM.Object.notNull(selectType)){
				$("#auPosForm select[name$='type']").tips({side:1,msg : "请选择！",bg:'#FF2D2D',time:1});
				return;
			 }
			 if(GEM.Validate.form("auPosForm")){
				 var that =$(this);
				 GEM.Ajax.doRequest("auPosForm",gempath +'/backstage/org/position/addPos',null,function(data){	
					that.dialog("close"); 
		        	GEM.Model.info(data.resMsg,function(){refreshOrgTree();getOrgList(1);});    
				 }); 
			 }	
		});
	});
	//安排人员
	$('#arrangeAccBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		loadArrangeAcc();
		cleanArrangeAccForm();
		var posId=$("#basePosForm input[name$='posId']").val();
		GEM.Model.edit("arrangeAccDiv","安排人员",function(){
			var chks =[];    
			$('#arrangeAccTable input[name="ids"]:checked').each(function(){    
				chks.push($(this).val());    
			});
			if(chks.length==0) {
				 GEM.Model.info("您没有选择任何人员!"); 
			}else{
				var that =$(this);
				GEM.Model.confirm("确认选中的人员吗?",function(){	
					GEM.Ajax.doRequest("",gempath +'/backstage/org/position/arrangeAcc',{posId:posId,chks:chks.toString()},function(data){
						that.dialog("close"); 
						GEM.Model.info(data.resMsg,function(){getPosList(1);});
					});
				});		
			}		
		});
	});
});

function refreshOrgTree(){
	 GEM.Model.loading();
	 loadPreOrgTree();
	 GEM.Ajax.doRequest(null,gempath +'/backstage/org/position/getOrgAndPositionTree',null,function(data){
			//设置
			$.fn.zTree.init($("#orgTree"),{view:{selectedMulti:false,fontCss:{color:"#393939"}},data:{simpleData: {enable: true}},callback:{onClick:clickOrg}},data.obj);
			GEM.Model.loadingClose();	 
	 });	 
}
function loadOrgTree(){
	GEM.Ajax.doRequest(null,gempath +'/backstage/org/position/getOrgAndPositionTree',null,function(data){
		//设置
		$.fn.zTree.init($("#orgTree"),{view:{selectedMulti:false,fontCss:{color:"#393939"}},data:{simpleData: {enable: true}},callback:{onClick:clickOrg}},data.obj);
		var treeObj = $.fn.zTree.getZTreeObj("orgTree");
		var nodes = treeObj.getNodes();
		if(nodes.length>0){
			//默认选中第一个
			treeObj.selectNode(nodes[0]);
			clickOrg(null,null,nodes[0]);
		}
	});
}
function clickOrg(event,treeId,treeNode) {
	cleanRightDiv();
		var zTree = $.fn.zTree.getZTreeObj("orgTree"),
		nodes = zTree.getSelectedNodes(),v ="",n ="",o="",p="";	
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";//获取name值
			n += nodes[i].id + ",";//获取id值
			o += nodes[i].other + ",";//获取自定义值
			var pathNodes=nodes[i].getPath();
			for(var y=0;y<pathNodes.length;y++){
				var name=pathNodes[y].name.replace("组织:","").replace("职务:","");
				p+=name+",";//获取path/name值
			}
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);	
		if (n.length > 0 ) n = n.substring(0, n.length-1);
		if (o.length > 0 ) o = o.substring(0, o.length-1);
		if (p.length > 0 ) p = p.substring(0, p.length-1);
		var ps=p.split(","),psl=ps.length,title1="",title2="";	
		if(o=='o'){	//判断是否组织 		
			for(var i=0;i<psl;i++){
				title1+=ps[i]+"&nbsp;<i class='icon-double-angle-right'></i>&nbsp;";
			}	
			title2="职务";
			$("#rightOrgDiv").removeClass("hide");
			$("#title1").html(title1);
			$("#title2").html(title2);	
			$("#baseOrgForm input[name$='orgId']").prop("value",n);
			$("#addPosBtn").removeClass("hide");
			getOrgList(1);
		}else if(o=='p'){//判断是否岗位	
			for(var i=0;i<psl;i++){
				if(i!=(psl-1)){
					title1+=ps[i]+"&nbsp;<i class='icon-double-angle-right'></i>&nbsp;";
				}else{
					title2+=ps[i];
				}
			}			
			n=n.replace("pos","");
			$("#basePosForm input[name$='posId']").prop("value",n);
			$("#rightPosDiv").removeClass("hide");
			$("#addPosBtn").addClass("hide");
			$("#title3").html(title1);
			$("#title4").html(title2);
			getPosList(1);
		}
}
function cleanRightDiv(){
	$("#title1").html("");
	$("#title2").html("");
	$("#title3").html("");
	$("#title4").html("");
	$("#basePosForm input[name$='posId']").prop("value","");
	$("#baseOrgForm input[name$='orgId']").prop("value","");
	$("#rightPosDiv").addClass("hide");
	$("#rightOrgDiv").addClass("hide");
	$("#baseOrgTable tbody").empty();
	$("#basePosTable tbody").empty();
	$("#orgpageing ul").empty();//清空org分页
	$("#pospageing ul").empty();//清空pos分页
}
function loadPreOrgTree(){
	GEM.Ajax.doRequest(null,gempath +'/backstage/org/position/getPreOrgTree',null,function(data){
		//设置
		$.fn.zTree.init($("#preOrgTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickPreOrg}},data.obj);
	});
}
function clickPreOrg(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("preOrgTree"),
	nodes = zTree.getSelectedNodes(),v ="",n ="",p="";	
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";//获取name值
		n += nodes[i].id + ",";	//获取id值
		var pathNodes=nodes[i].getPath();
		for(var y=0;y<pathNodes.length;y++){
			p+=pathNodes[y].name+"/";//获取path/name值
		}
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (n.length > 0 ) n = n.substring(0, n.length-1);
	if (p.length > 0 ) p = p.substring(0, p.length-1);
	$("#preOrg").prop("value",p);
	$("#auPosForm input[name$='orgId']").prop("value",n);
	//因为单选选择后直接关闭，如果多选请另外写关闭方法
	hidePreOrg();
}
function emptyPreOrg(){
	$("#preOrg").prop("value","");
	$("#auPosForm input[name$='orgId']").prop("value","0");
}
var preisShow=false;//窗口是否显示
function showPreOrg() {
	if(preisShow){
		hidePreOrg();
	}else{
		var obj = $("#preOrg");
		var offpos = $("#preOrg").position();
		$("#preOrgContent").css({left:offpos.left+"px",top:offpos.top+obj.heith+"px"}).slideDown("fast");	
		preisShow=true;
	}
}
function hidePreOrg(){
	$("#preOrgContent").fadeOut("fast");
	preisShow=false;
}
function getOrgList(init){
	if(init==1)$("#baseOrgForm .pageNum").val(1);	
	GEM.Model.loading();
	GEM.Ajax.doRequest("baseOrgForm",gempath +'/backstage/org/position/findOrgByPage',null,function(data){
		 $("#baseOrgTable tbody").empty();
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
            		 html+="<td class='center'>"+GEM.Object.notEmpty(l.name)+"</td>";
            		 html+="<td class='center'>"+GEM.Object.notEmpty(l.type)+"</td>";
            		 html+="<td class='center hidden-480'>"+GEM.Object.notEmpty(l.description)+"</td>";
            		 var permitBtn=[{name:"查看职务",btnFun:"checkPos",icon:"icon-zoom-in color-purple"}
            		 				,{name:"修改职务",btnFun:"editPos",icon:"icon-edit color-blue"}
            		 				,{name:"删除职务",btnFun:"delPos",icon:"icon-remove-sign color-red"}];
            		 html+=GEM.Tags.setFunction(l.id,permitBtn);
            		 html+="</tr>";		 
            	 } 
        		 $("#baseOrgTable tbody").append(html);
        		 GEM.Page.setPage("baseOrgForm","orgpageing",pageSize,pageNum,totalRecord,"getOrgList");
        	 }else{
        		html+="<tr><td colspan='6' class='center'>没有相关数据</td></tr>";
        		$("#baseOrgTable tbody").append(html);
        		$("#orgpageing ul").empty();//清空分页
        	 }	 	        	 
    	 GEM.Model.loadingClose();	
	});
}
function cleanPosForm(){
	GEM.Tags.cleanForm("auPosForm");
	$("#auPosForm input[name$='orgId']").val('0');//上级资源
	$("#auPosForm select[name$='type']").prop("value","");//类别
	hidePreOrg();
}
function setPosForm(data){
	var l=data.obj;
	$("#auPosForm input[name$='id']").val(GEM.Object.notEmpty(l.id));
	$("#auPosForm input[name$='name']").val(GEM.Object.notEmpty(l.name));
	$("#auPosForm select[name$='type']").prop("value",GEM.Object.notEmpty(l.type));
	$("#auPosForm textarea[name$='description']").val(GEM.Object.notEmpty(l.description));//描述
	var treeObj = $.fn.zTree.getZTreeObj("preOrgTree");
	var nodes =treeObj.getNodesByParam("id",l.orgId);
	if(GEM.Object.notNull(nodes)&&nodes.length>0){
		treeObj.selectNode(nodes[0]);
		clickPreOrg(null,null,nodes[0]);
	}
}
function checkPos(id){
	cleanPosForm();
	GEM.Ajax.doRequest(null,gempath+'/backstage/org/position/findPos',{id:id},function(data){
    	setPosForm(data);    
    	GEM.Model.check("auPosDiv","查看职务");   
	});
}
function editPos(id){
	//清空表单
	cleanPosForm();	
	GEM.Ajax.doRequest(null,gempath+'/backstage/org/position/findPos',{id:id},function(data){
			setPosForm(data);    
    		GEM.Model.edit("auPosDiv","修改职务",function(){
    			 var selectType=$("#auPosForm select[name$='type']").val();
    			 if(!GEM.Object.notNull(selectType)){
    				$("#auPosForm select[name$='type']").tips({side:1,msg : "请选择！",bg:'#FF2D2D',time:1});
    				return;
    			 }
    			 if(GEM.Validate.form("auForm")){
					 var that =$(this);
					 GEM.Ajax.doRequest("auPosForm",gempath+'/backstage/org/position/updatePos',null,function(data){
			        	that.dialog("close");
			        	GEM.Model.info(data.resMsg,function(){refreshOrgTree();getOrgList();});      				        		 
					 });
				}
    		});  
	});
}
function delPos(id){
	GEM.Model.confirm("确认删除职务吗？",function(){	
		GEM.Ajax.doRequest(null,gempath+'/backstage/org/position/delPos',{id:id},function(data){
			GEM.Model.info(data.resMsg,function(){refreshOrgTree();getOrgList();});
		});
	});	
}
function cleanArrangeAccForm(){
	GEM.Tags.cleanForm("arrangeAccForm");
}
function loadArrangeAcc(init){
	if(init==1)$("#arrangeAccForm .pageNum").val(1);	
	GEM.Model.loading();
	var posId=$("#basePosForm input[name$='posId']").val();
	GEM.Ajax.doRequest("arrangeAccForm",gempath +'/backstage/org/position/findArrangeAccByPage',{id:posId},function(data){
		 $("#arrangeAccTable tbody").empty();
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
	           		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.accountId+"' class='ace' /> <span class='lbl'></span></label></td>";
	           		 html+="<td class='center hidden-480'>"+(i+leng+1)+"</td>";
	           		 html+="<td class='center'>"+GEM.Object.notEmpty(l.loginName)+"</td>";
	           		 html+="<td class='center'>"+GEM.Object.notEmpty(l.name)+"</td>";
	           		 html+="</tr>";		 
	           	 } 
	       		 $("#arrangeAccTable tbody").append(html);
	       		 GEM.Page.setSimPage("arrangeAccForm","arrangeAccpageing",pageSize,pageNum,totalRecord,"loadArrangeAcc");
	       	 }else{
	       		html+="<tr><td colspan='4' class='center'>没有相关数据</td></tr>";
	       		$("#arrangeAccTable tbody").append(html);
	       		$("#arrangeAccpageing ul").empty();//清空分页
	       	 }	        	 
	   	 GEM.Model.loadingClose();	
	});
}
function getPosList(init){
	if(init==1)$("#basePosForm .pageNum").val(1);	
	GEM.Model.loading();
	GEM.Ajax.doRequest("basePosForm",gempath +'/backstage/org/position/findPosByPage',null,function(data){
		 $("#basePosTable tbody").empty();
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
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.accountId+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center hidden-480'>"+(i+leng+1)+"</td>";
            		 html+="<td class='center hidden-480'>"+GEM.Object.notEmpty(l.loginName)+"</td>";
            		 html+="<td class='center'>"+GEM.Object.notEmpty(l.name)+"</td>";
            		 html+="<td class='center'>"+GEM.Object.notEmpty(l.roleName)+"</td>";
            		 var permitBtn=[{name:"移除人员职务",btnFun:"delAccPos",icon:"icon-remove-sign color-red"}];
            		 html+=GEM.Tags.setFunction(l.accountId,permitBtn);
            		 html+="</tr>";		 
            	 } 
        		 $("#basePosTable tbody").append(html);
        		 GEM.Page.setPage("basePosForm","pospageing",pageSize,pageNum,totalRecord,"getPosList");
        	 }else{
        		html+="<tr><td colspan='6' class='center'>没有相关数据</td></tr>";
        		$("#basePosTable tbody").append(html);
        		$("#pospageing ul").empty();//清空分页
        	 }	 	        	 
    	 GEM.Model.loadingClose();	
	});
}
function delAccPos(id){
	GEM.Model.confirm("确认移除人员职务吗？",function(){	
		GEM.Ajax.doRequest(null,gempath+'/backstage/org/position/delAccPos',{accId:id},function(data){
			GEM.Model.info(data.resMsg,function(){getPosList(1);});
		});
	});	
}