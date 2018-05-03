<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="../include/tag.jsp"%>
<title>${title }</title>
</head>
<body>
	<div style="width:100%;height:600px;">
		<input id="fileId" name="fileId" type="file" accept="*" multiple>
	</div>
	<script>
		var ctx = $("#ctx").val().trim();
		$("#fileId").fileinput({
			uploadUrl : ctx+"/admin/upload",
			autoReplace : true,
			maxFileCount : 5,
			allowedFileExtensions : [ "jpg", "png", "gif","pdf","mp3" ]
		});
		
		$(document).on('fileuploaded', function(event, data, previewId, index) {    
           
    });    
	</script>
</html>
