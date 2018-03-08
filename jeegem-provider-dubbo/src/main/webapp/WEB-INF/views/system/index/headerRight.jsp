<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
				<div class="navbar-header pull-right" >
				<!-- 	<div class="navbar-header pull-right" role="navigation"> -->
					<ul class="nav ace-nav">
						<li class="grey">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-tasks"></i><span class="badge badge-grey">0</span>
							</a>
							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header"><i class="icon-ok"></i>还有0个任务完成</li>
							</ul>
						</li>
						<li class="purple">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-bell-alt icon-animated-bell"></i>
								<span class="badge badge-important">0</span>
							</a>
							<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header"><i class="icon-warning-sign"></i>0条通知</li>
							</ul>
						</li>
						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-envelope icon-animated-vertical"></i>
								<span class="badge badge-success">0</span>
							</a>
							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header"><i class="icon-envelope-alt"></i>0条消息</li>
							</ul>
						</li>
						<li class="userperLi">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img id="mainHeadpic" class="nav-user-photo" src="<c:choose>
										<c:when test="${!empty currentAccount.picUrl}">${currentAccount.picUrl}</c:when>
										<c:otherwise><c:url value="/static/images/system/user/hpic0.jpg" /></c:otherwise>
										</c:choose>" />
								<span class="user-info">
									<small>欢迎光临,</small> 
									<span id="user-info-name">
										<c:choose>
										<c:when test="${!empty currentAccount.name}">${currentAccount.name}</c:when>
										<c:when test="${!empty currentAccount.loginName}">${currentAccount.loginName}</c:when>
										<c:otherwise>用户</c:otherwise>
										</c:choose> 
									</span>									
								</span>
								<i class="icon-caret-down"></i>
							</a>
							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li><a href="javascript:void(0);" onclick="perData();" ><i class="icon-user"></i>个人设置</a></li>
								<li><a href="javascript:void(0);" onclick="perSetting();" ><i class="icon-cog"></i>系统设置</a></li>		
								<!-- <li><a href="javascript:void(0);" onclick="开发中" ><i class="icon-comments-alt "></i>在线聊天</a></li> -->					
								<li class="divider"></li>
								<li><a href="javascript:void(0);" onclick="logout();"><i class="icon-off"></i>退出</a></li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->