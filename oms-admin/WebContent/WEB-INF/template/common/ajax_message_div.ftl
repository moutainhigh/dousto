<#include "/WEB-INF/template/common/jquery-ui-dialog.ftl" />
<link href="${base}/resources/admin/css/errorbox.css" rel="stylesheet" type="text/css" />
<div id="ajaxErrorBox" class="errorBox" title="信息">
	<div class="errorDetail">
		<div class="errorContent">
			<#if (errorMessages?size > 0)! || (actionMessages?size > 0)! || (fieldErrors?size > 0)!>
				<#list errorMessages as list>${list?replace('^\\[', '', 'r')?replace('\\]$', '', 'r')}<br></#list>
				<#list actionMessages as list>${list?replace('^\\[', '', 'r')?replace('\\]$', '', 'r')}<br></#list>
				<#list (fieldErrors?keys)! as key>
					${fieldErrors[key]?replace('^\\[', '', 'r')?replace('\\]$', '', 'r')}<br>
				</#list>
			</#if>
		</div>
	</div>
</div>
<script>
$(function() {
		$("#ajaxErrorBox").dialog({
			autoOpen:false,
			modal: true,
			buttons: {
				确定: function() {
					$( this ).dialog( "close" );
				}
			}
		});
	<#if (errorMessages?size > 0)! || (actionMessages?size > 0)! || (fieldErrors?size > 0)!>
	$("#ajaxErrorBox").dialog("open");
	</#if>
	});
</script>
