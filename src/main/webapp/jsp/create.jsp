<!DOCTYPE html>
<html>
    <head>
        <%@ page contentType="text/html;charset=utf-8" %>
        <title>Create Question</title>
        <style><%@include file="/style/style.css"%></style>
		<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,700" rel="stylesheet">
</head>
<body>
    <h1>Add Question</h1>
    <form id= "createQuestionForm" action="" method="post">
		<p>
			<label>Input text question:</label><br>
			<textarea rows="5" cols = "100" name="text_question" id="text_question"></textarea>
		</p>
		<p>
			<label>Image: </label>
			<div class="fileform">
				<div class="selectbutton">Обзор</div>
				<input id="upload" type="file" name="upload" />
			</div>
		</p>				
		<table id="new_fields" style="display:none">
			<tr>
				<th>Text</th>
				<th>isRight</th>
			</tr>
		</table>   
		<input type="button" id="addfields" value="Add answer" /><br>
		<input type="submit" value="Create">   
    </form>
</body>
<script><%@include file="/js/addField.js"%></script>
</html>

