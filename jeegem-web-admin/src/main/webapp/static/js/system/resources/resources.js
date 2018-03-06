$(function () {
	//加载上级资源列表
	loadPreResources();
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
		hidePreResources();
		var layer=$("#auForm input[name$='layer']").val();
		GEM.Ajax.doRequest("",gempath +'/backstage/resources/listResources',{layer:layer},function(data){
			   //获取数据
			   var zNodes=data.obj;
			   //设置
			   var setting = {view:{dblClickExpand: false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable:true}},callback:{onClick:onClickTree}};	
			   $.fn.zTree.init($("#preResourcesTree"), setting, zNodes);
				GEM.Model.edit("auDiv","新增",function(){
					 cleanType();			 
					 if(GEM.Validate.form("auForm")){
						 var that =$(this);
						 GEM.Ajax.doRequest("auForm",gempath +'/backstage/resources/add',null,function(data){
				        	that.dialog("close");      
				        	GEM.Model.info(data.resMsg,function(){search();});
		
						 }); 
					 }	
				});
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
				 GEM.Ajax.doRequest(null,gempath +'/backstage/resources/delBatch',{chks:chks.toString()},function(data){
					GEM.Model.info(data.resMsg,function(){search();});
				 });
			});	
		}	
	});
	
	$("#iconDiv .comIconSelectul i").bind('click', function(){		
		var iconHidden=$(this).attr("iicon");
		var colorHidden=$(this).attr("icolor");
		$("#iconHidden").val(iconHidden);
		$("#colorHidden").val(colorHidden);
		$("#testIconId").prop("class",iconHidden+" "+colorHidden+" bigger-200 ");
		
	});
	//图标选择
	$("#iconDiv .iconSelectul i").bind('click', function(){		
		var iconHidden=$(this).prop("class");
		var colorHidden=$("#colorHidden").val();
		$("#iconHidden").val(iconHidden);
		$("#testIconId").prop("class",GEM.Object.notEmpty(iconHidden)+" "+GEM.Object.notEmpty(colorHidden)+" bigger-200 ");
	});
	//颜色选择
	$("#iconDiv .iconColorSelect td").bind('click', function(){		
		var colorHidden=$(this).prop("class");
		var iconHidden=$("#iconHidden").val();
		$("#colorHidden").val(colorHidden);
		$("#testIconId").prop("class",GEM.Object.notEmpty(iconHidden)+" "+GEM.Object.notEmpty(colorHidden)+" bigger-200 ");
	});
	//层显示选择
	$("#baseForm .widget-main button").bind('click', function(){		
		$("#baseForm .widget-main button").removeClass("btn-success");
		$(this).addClass("btn-success");
		var selectVal=$(this).val();
		$("#baseForm input[name$='layer']").val(selectVal);
		$("#auForm input[name$='layer']").val(selectVal);
		search();
		//加载上级资源列表
		loadPreResources();
	});
	//form显示选择
	$("#selectlayer button").bind('click', function(){		
		$("#selectlayer button").removeClass("btn-success");
		$(this).addClass("btn-success");
		$("#auForm input[name$='layer']").val($(this).val());
		//加载上级资源
		loadPreResources();
		$("#auForm input[name$='parentId']").val('0');//上级资源
		$("#preResources").val("");//上级资源
	});
});
function search(){
	$("#searchBtn").trigger("click");
}
var sequenceNum=0;
function getbaseList(){
	GEM.Model.loading();
	GEM.Ajax.doRequest("baseForm",gempath +'/backstage/resources/listAllParentMenu',"",function(data){
		$("#baseTable tbody").empty();
   		 var obj=data.obj;
   		 var list=obj.list;
       	 var permitBtn=obj.permitBtn;
       	 var html="";
   		 if(list!=null&&list.length>0){
       		 for(var i = 0;i<list.length;i++){
           		 var l=list[i];
           		 var level=0;	
           		 html+="<tr>";
           		 html+="<td class='left'><label><input type='checkbox' name='ids' value='"+l.id+"' class='ace'/><span class='lbl'></span></label></td>";
           		 sequenceNum++;
           		 html+="<td class='center hidden-480'>"+sequenceNum+"</td>";
           		 html+="<td class='left'>";
           		 if(l.nodes!=null&&l.nodes.length>0)
           			 html+="<i class='icon-chevron-sign-right tree-hit tree-expanded' onclick='extendMenu(this,&apos;"+l.id+"&apos;)'></i>";
           		 else
           			 html+="<span class='tree-indent'></span>";
           		 html+=GEM.Object.notNull(l.icon)?"<i class='"+l.icon+" menuicon'></i>":"";
           		 if(l.type=="1"){
           			 html+=GEM.Object.notEmpty(l.name)+"</td>";
           			 html+="<td class='left hidden-480'>"+GEM.Object.notEmpty(l.resUrl)+"</td>";
           		 }else if(l.type=="2"){
           			 html+= "<font color='blue'>功能：</font>";
           			 html+= GEM.Object.notEmpty(l.name);
           			 html+="</td>";
           			 html+="<td class='left hidden-480'>"+GEM.Object.notEmpty(l.resUrl)+"</td>";
           		 }else{
           			 html+= "<font color='orange'>按钮：</font>";
           			 html+= GEM.Object.notEmpty(l.name);
           			 html+="</td>";
           			 html+="<td class='left hidden-480'>"+GEM.Object.notEmpty(l.resUrl)+"</td>";
           		 }   
           		 if(l.isValid==1){
           			 html+="<td class='hidden-480'><span class='label label-sm label-success'>有效</span></td>";
           		 }else{
           			 html+="<td class='hidden-480'><span class='label label-sm arrowed-in'>无效</span></td>";
           		 }  
           		 html+="<td class='left hidden-480'>"+GEM.Object.notEmpty(l.description)+"</td>";      		
           		 html+=GEM.Tags.setFunction(l.id,permitBtn);
           		 html+="</tr>";		 
           		 html=setNodes(l.id,"0",l.nodes,html,level,permitBtn);
           	 } 
       		 $("#baseTable tbody").append(html);
       	 }else{
       		html+="<tr><td colspan='7' class='center'>没有相关数据</td></tr>";
       		$("#baseTable tbody").append(html);
       	 }	
   		 sequenceNum=0; 
   	 GEM.Model.loadingClose();
	});
}
/*
 * 设置子菜单(递归算法)
 */
function setNodes(id,parentid,nodes,html,level,permitBtn){
	if(nodes!=null&&nodes.length>0){
		level++;
		for(var i = 0;i<nodes.length;i++){
		     var n=nodes[i];
			 html+="<tr class='hide parent"+id+" parent"+parentid+" '>";
    		 html+="<td class='left'><label><input type='checkbox' name='ids' value='"+n.id+"' class='ace'/><span class='lbl'></span></label></td>";
    		 sequenceNum++;
    		 html+="<td class='center hidden-480'>"+sequenceNum+"</td>";
    		 html+="<td class='left'>";
    		 for(var le=0;le<level;le++){
    			 html+="<span class='tree-indent'></span>";
    		 }
    		 if(i==nodes.length-1)
    			 html += "<img src='"+gempath+"/static/css/system/system/images/joinbottom.gif' class='joinMiddle'/>";
    		 else
    			 html += "<img src='"+gempath+"/static/css/system/system/images/join.gif' class='joinMiddle'/>";
    		 if(n.nodes!=null&&n.nodes.length>0)
    			 html+="<i class='icon-chevron-sign-right tree-hit tree-expanded ' onclick='extendMenu(this,&apos;"+n.id+"&apos;)'></i>";
    		 else
    			 html+="<span class='tree-indent'></span>";
    		 html+=GEM.Object.notNull(n.icon)?"<i class='"+n.icon+" menuicon'></i>":"";	
    		 if(n.type=="1"){
    			 html+=GEM.Object.notEmpty(n.name)+"</td>";
    			 html+="<td class='left hidden-480'>"+GEM.Object.notEmpty(n.resUrl)+"</td>";
    		 }else if(n.type=="2"){
    			 html+= "<font color='blue'>功能：</font>";
    			 html+= GEM.Object.notEmpty(n.name);
    			 html+="</td>";
    			 html+="<td class='left hidden-480'>"+GEM.Object.notEmpty(n.resUrl)+"</td>";
    		 }else{
    			 html+= "<font color='orange'>按钮：</font>";
    			 html+= GEM.Object.notEmpty(n.name);			
    			 html+="</td>";
    			 html+="<td class='left hidden-480'>"+GEM.Object.notEmpty(n.resUrl)+"</td>";
    		 }
    		 if(n.isValid==1){
    			 html+="<td class='hidden-480'><span class='label label-sm label-success'>有效</span></td>";
    		 }else{
    			 html+="<td class='hidden-480'><span class='label label-sm arrowed-in'>无效</span></td>";
    		 }	 
    		 html+="<td class='left hidden-480'>"+GEM.Object.notEmpty(n.description)+"</td>";
    		 html+=GEM.Tags.setFunction(n.id,permitBtn);
    		 html+="</tr>";	
    		 //递归循环	 
    		 html=setNodes(n.id,id,n.nodes,html,level,permitBtn);
		}
	}
	return html;
}
/*
 * 打开子菜单
 */
function extendMenu(obj,id){
	if($(obj).hasClass('icon-chevron-sign-right')){
		$(obj).removeClass("icon-chevron-sign-right").addClass("icon-chevron-sign-down");
		$("#baseTable .parent"+id+" i.icon-chevron-sign-right").removeClass("icon-chevron-sign-right").addClass("icon-chevron-sign-down");
		$("#baseTable .parent"+id ).removeClass("hide");
	}else if($(obj).hasClass('icon-chevron-sign-down')){
		$(obj).removeClass("icon-chevron-sign-down").addClass("icon-chevron-sign-right");		
		$("#baseTable .parent"+id).addClass("hide");
	}
}
function onClickTree(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("preResourcesTree"),
	nodes = zTree.getSelectedNodes(),v ="",n ="",p="";
	
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";//获取name值
		n += nodes[i].id + ",";//获取id值
		var pathNodes=nodes[i].getPath();
		for(var y=0;y<pathNodes.length;y++){
			p+=pathNodes[y].name+"/";//获取path/name值
		}
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (n.length > 0 ) n = n.substring(0, n.length-1);
	if (p.length > 0 ) p = p.substring(0, p.length-1);
	$("#preResources").prop("value",p);
	$("#auForm input[name$='parentId']").prop("value",n);
	//因为单选选择后直接关闭，如果多选请另外写关闭方法
	hidePreResources();
}
var preisShow=false;//窗口是否显示
function showPreResources() {
	if(preisShow){
		hidePreResources();
	}else{
		var obj = $("#preResources");
		var offpos = $("#preResources").position();
		$("#preResourcesContent").css({left:offpos.left+"px",top:offpos.top+obj.heith+ "px"}).slideDown("fast");	
		preisShow=true;
	}
}
function emptyPre(){
	$("#preResources").prop("value","");
	$("#auForm input[name$='parentId']").prop("value","0");
}
function hidePreResources(){
	$("#preResourcesContent").fadeOut("fast");
	preisShow=false;
}
function loadPreResources(){
	hidePreResources();
	var layer=$("#auForm input[name$='layer']").val();
	GEM.Ajax.doRequest("",gempath +'/backstage/resources/listResources',{layer:layer},function(data){
		   //获取数据
		   var zNodes=data.obj;
		   //设置
		   var setting = {view:{dblClickExpand: false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable:true}},callback:{onClick:onClickTree}};	
		   $.fn.zTree.init($("#preResourcesTree"), setting, zNodes);
	});
}
function changeType(v){
	if("1"==v){
		$("#trbtnId").addClass('hide');
		$("#trbtnFun").addClass('hide');
		//$("#selectlayer").removeClass('hide');
		//$("#trresUrl").removeClass('hide');
	}else if("2"==v){
		$("#trbtnId").removeClass('hide');
		$("#trbtnFun").removeClass('hide');
		//$("#selectlayer").addClass('hide');
		//$("#trresUrl").addClass('hide');
	}else{
		$("#trbtnId").addClass('hide');
	  //$("#trresUrl").addClass('hide');
		$("#trbtnFun").removeClass('hide');	
		//$("#selectlayer").addClass('hide');
	}
}
function cleanType(){
	var v=$("#auForm select[name$='type']").val();
	if("1"==v){
		$("#auForm input[name$='btnId']").val("");//主键
		$("#auForm input[name$='btnFun']").val("");//方法
	}else if("2"==v){
		//$("#auForm input[name$='resUrl']").val("");//路径
	}else{
		$("#auForm input[name$='btnId']").val("");//主键
		//$("#auForm input[name$='resUrl']").val("");//路径
	}
}

function check(id){
	//清空表单
	cleanForm();
	GEM.Ajax.doRequest(null,gempath +'/backstage/resources/find',{id:id},function(data){
    		setForm(data);  
    		GEM.Model.check("auDiv");  	
	});
}
function edit(id){
	//清空表单
	cleanForm();
	hidePreResources();
	var layer=$("#auForm input[name$='layer']").val();
	GEM.Ajax.doRequest("",gempath +'/backstage/resources/listResources',{layer:layer},function(data){
		   //获取数据
		   var zNodes=data.obj;
		   //设置
		   var setting = {view:{dblClickExpand: false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable:true}},callback:{onClick:onClickTree}};	
		   $.fn.zTree.init($("#preResourcesTree"), setting, zNodes);
		   GEM.Ajax.doRequest(null,gempath +'/backstage/resources/find',{id:id},function(data){
		    		setForm(data);  		
		    		GEM.Model.edit("auDiv","修改",function(){
		    			 cleanType();
						 if(GEM.Validate.form("auForm")){
							 var that =$(this);
							 GEM.Ajax.doRequest("auForm",gempath +'/backstage/resources/update',null,function(data){
					        		 that.dialog("close");
					        		 GEM.Model.info(data.resMsg,function(){search();});		        		 	
							 });
						}
		    		});  
			}); 
	});
	
}
function setForm(data){
	var l=data.obj;
	$("#auForm input[name$='id']").val(GEM.Object.notEmpty(l.id));
	GEM.Tags.isValid("auForm",(GEM.Object.notNull(l.isValid)?l.isValid:"0"));
	$("#auForm input[name$='name']").val(GEM.Object.notEmpty(l.name));
	$("#preResources").val(GEM.Object.notEmpty(l.parentName));
	var parentId=l.parentId;
	$("#auForm input[name$='parentId']").val(GEM.Object.notEmpty(parentId));//上级资源
	$("#auForm input[name$='sort']").val(GEM.Object.notEmpty(l.sort));//排序
	$("#auForm input[name$='layer']").val(GEM.Object.notEmpty(l.layer));
	$("#selectlayer button").removeClass("btn-success").addClass("hide");
	$("#selectlayer button[value$='"+GEM.Object.notEmpty(l.layer)+"']").addClass("btn-success");
	if(GEM.Object.notNull(parentId) && parentId=='0'){
		$("#selectlayer button").removeClass("hide");
	}else{
		$("#selectlayer button[value$='"+GEM.Object.notEmpty(l.layer)+"']").removeClass("hide");
	}
	if(GEM.Object.notNull(l.icon)){
		$("#selecticonId").removeClass().addClass(l.icon+" bigger-120 ");
		$("#auForm input[name$='icon']").val(GEM.Object.notEmpty(l.icon));//图标
		var dis=l.icon.split(" ");
		for(var i=0;i<dis.length;i++){
			var di=dis[i];
			if(di.indexOf("icon") > -1) $("#iconHidden").val(di);
			else if(di.indexOf("color") > -1)$("#colorHidden").val(di);
		}
	}	
	$("#auForm select[name$='type']").val(GEM.Object.notEmpty(l.type));//类型
	changeType(l.type);
	$("#auForm input[name$='resUrl']").val(GEM.Object.notEmpty(l.resUrl));//路径
	$("#auForm input[name$='btnId']").val(GEM.Object.notEmpty(l.btnId));//主键
	$("#auForm input[name$='btnFun']").val(GEM.Object.notEmpty(l.btnFun));//方法
	$("#auForm textarea[name$='description']").val(GEM.Object.notEmpty(l.description));//描述
	var treeObj = $.fn.zTree.getZTreeObj("preResourcesTree");
	var nodes = treeObj.getNodesByParam("id", l.parentId);
	if (GEM.Object.notNull(nodes) && nodes.length > 0) {
		treeObj.selectNode(nodes[0]);
		onClickTree(null, null, nodes[0]);
	}

}
function cleanForm(){
	//加载上级资源列表
	loadPreResources();
	GEM.Tags.cleanForm("auForm");
	$("#auForm input[name$='parentId']").val('0');//上级资源
	hidePreResources();
	GEM.Tags.isValid("auForm","1");
	emptyIcon();
	$("#auForm input[name$='sort']").val('1');
	$("#auForm select[name$='type']").val('1');//类型
	changeType("1");
	$("#colorHidden").val("");
	$("#iconHidden").val("");
	//获取默认设置显示层
	var bflayer=$("#baseForm input[name$='layer']").val();
	$("#auForm input[name$='layer']").val(bflayer);
	$("#selectlayer button").removeClass("btn-success").removeClass("hide");
	$("#selectlayer button[value$='"+bflayer+"']").addClass("btn-success");	
}
function del(id){
	GEM.Model.confirm("确认删除吗？",function(){	
		GEM.Ajax.doRequest(null,gempath +'/backstage/resources/del',{id:id},function(data){
			GEM.Model.info(data.resMsg,function(){search();});
		});
	});	
}

function emptyIcon(){
	$("#selecticonId").removeClass();
	$("#auForm input[name$='icon']").val("");//图标
}
function selectIcon(){
	var type=$("#auForm select[name$='type']").val();
	//判断是否菜单，菜单没颜色版
	if(type==1) {
		$("#colorPlate").addClass('hide');
		$("#commonIcons").addClass('hide');
	}else{
		$("#colorPlate").removeClass('hide');
		$("#commonIcons").removeClass('hide');
	}
	var icon=$("#auForm input[name$='icon']").val();
	//判断图标是否为空，设置预览图标,没有设置默认值
	if(GEM.Object.notNull(icon)) $("#testIconId").prop("class",icon+" bigger-200");
	else                        $("#testIconId").prop("class","icon-android bigger-200");	
	$("#iconDiv").removeClass('hide').dialog({
		resizable: false,modal: true,title: "<div class='widget-header'><h4 class='smaller'>选择图标</h4></div>",
		title_html: true,
		buttons: [{html: "<i class='icon-ok bigger-110'></i>&nbsp;确定","class" : "btn btn-primary btn-xs",click: function() {		
			var iconHidden=$("#iconHidden").val();
			var colorHidden=$("#colorHidden").val();
			$("#selecticonId").prop("class",GEM.Object.notEmpty(iconHidden)+" "+GEM.Object.notEmpty(colorHidden)+" bigger-120 ");
			$("#auForm input[name$='icon']").val(GEM.Object.notEmpty(iconHidden)+" "+GEM.Object.notEmpty(colorHidden));		
			$(this).dialog("close");	
		     }},
		   {html: "<i class='icon-remove bigger-110'></i>&nbsp;取消","class" : "btn btn-xs",click: function() {$(this).dialog("close");}}]
	});
}
