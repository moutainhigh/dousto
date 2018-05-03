<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理员列表 </title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="${base}/images/favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
    <link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/scripts/list.js"></script>
</head>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>后台任务监控的方法&nbsp;<span class="pageInfo"></span></h1>
		</div>
		<form id="listForm" action="user!list.action" method="post">
			
			<table class="listTable">
				<tr>
					<th>Trigger名称</th>
					<th>Trigger分组</th>
					<th>状态</th>
					<th>上次执行时间</th>
					<th>下次执行时间</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>操作</th>
				</tr>
				<#list taskList as list>
					<tr>
						<td>${list.jobName}</td> 
						<td>${list.jobGroup}</td>
						<td>${(list.status)!"&nbsp;"}</td>
						<td>
							<#if (list.lastTime)??>
								${(list.lastTime)?string("yyyy-MM-dd HH:mm:ss")!}
							<#else>
								&nbsp;
							</#if>
						</td>
						<td>${(list.nextTime)?string("yyyy-MM-dd HH:mm:ss")!}</td>
						
						<td>${(list.startTime)?string("yyyy-MM-dd HH:mm:ss")}</td>
						<td>
							<#if (list.endTime)??>
								${(list.endTime)?string("yyyy-MM-dd HH:mm:ss")!}
							<#else>
								&nbsp;
							</#if>
						</td>
						
						<td>
							<a href="task_manage!trigger.action?jobName=${list.jobName}&jobGroup=${list.jobGroup}" title="启动">[启动]</a>
							<a href="task_manage!pause.action?jobName=${list.jobName}&jobGroup=${list.jobGroup}" title="暂停">[暂停]</a>
							<a href="task_manage!resume.action?jobName=${list.jobName}&jobGroup=${list.jobGroup}" title="恢复">[恢复]</a>
						</td>
					</tr>
				</#list>
			</table>
			
		</form>
	</div>
</body>
</html>
