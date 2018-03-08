$(function () {
	left();
	$("#tabul >li").each(function () {
		$(this).bind("click",function(){
			$("#tabul >li").removeClass("active_tab"); 
			$(this).toggleClass("active_tab"); 
		});
	});
});
function left(){
	var html="";
	html=leftli(html,"jq_index i_32_tables","首页","","index.html");
	html=leftli(html,"jq_dialog i_32_dashboard ","Dialog","对话框控件","dialog.html");
	html=leftli(html,"jq_datepicker i_32_dashboard ","Datepicker","日期控件","datepicker.html");
	$("#tabul").append(html);
	var smallhtml="";
	smallhtml=leftsmallli(smallhtml,"jq_index i_22_tables","首页","","index.html");
	smallhtml=leftsmallli(smallhtml,"jq_dialog i_22_dashboard ","Dialog","对话框控件","dialog.html");
	smallhtml=leftsmallli(smallhtml,"jq_datepicker i_22_dashboard ","Datepicker","日期控件","datepicker.html");
	$("#smalltabul").append(smallhtml);
}
//licss图片在Icons下32直接填名字
function leftli(h,licss,label,info,ahref){
	h+="<li class='"+licss+"'>";
	h+="<a href='"+ahref+"' title='"+info+"'>";
	h+="<span class='tab_label'>"+label+"</span>";
	h+="<span class='tab_info'>"+info+"</span>";
	h+="</a>";
	h+="</li>";
	return h;
}

function leftsmallli(h,licss,label,info,ahref){
	h+="<li><a title='"+label+"' class='"+licss+"' href='"+ahref+"'></a></li>";
	return h;
}