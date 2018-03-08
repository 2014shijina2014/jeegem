<%@ page contentType="text/html;charset=UTF-8" %>
<div id="uploadDiv" class="hide">
	<form id="uploadForm" method="post" enctype="multipart/form-data" onsubmit="return false;">
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr class="FormData">
					<td class="CaptionTD"><!-- <font color="red">*</font>参数名： --></td>
					<td class="DataTD">&nbsp; 
					<input type="file" id="uploadModelFile" name="modelFile"  onchange="jeegem.File.fileType(this,'zip|bar|bpmn|bpmn20.xml')" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
