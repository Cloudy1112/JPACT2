<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp" %>
<body>
	<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
		<label for="categoryname">Category name:</label><br> 
		<input type="text" id="categoryname" name="categoryname"><br> 
			
		<label for="images">Link image:</label><br> 
		<input type="text" id="images" name="images"><br> 
		
		<img id="imagess" src="" width="150px" height="150px" />
		<input type="file" onchange="chooseFile(this)" id="images_up" name="images_up"> <br>
  			
			
		<label for="status">Status:</label><br> 
		<label for="hoatdong">Hoạt động:</label>
		<input type="radio" id="ston" name="status" value = 1><br> 
		
		<label for="khoa">Khoá:</label> 
		<input type="radio" id="stoff" name="status" value = 0> <br>
		
		<input type= "submit" value= "Insert">
	</form>
</body>