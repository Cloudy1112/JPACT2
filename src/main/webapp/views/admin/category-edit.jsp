<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp" %>
<body>
	<form action="${pageContext.request.contextPath}/admin/category/update" method="post" enctype="multipart/form-data">
		
		<input type="text" id="categoryid" name="categoryid" value="${cate.categoryid}" hidden> 
		<label for="categoryname">Category name:</label><br> 
		<input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br> 
			
		<label for="images">Image:</label><br> 
		<input type="text" id="images" name="images""><br> 
		<c:if test="${cate.images.substring(0,5) != 'https'}">
				<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
			</c:if>

			<c:if test="${cate.images.substring(0,5) == 'https'}">
				<c:url value="${cate.images}" var="imgUrl"></c:url>
			</c:if>
		<img id="imagess" height="150" width="200" src="${imgUrl}" />
		<input type="file" onchange="chooseFile(this)" id="images_up" name="images_up" value ="${cate.images}"> <br>
  			
			
			
		<label for="status">Status:</label><br> 
		<label for="hoatdong">Hoạt động:</label>
		<input type="radio" id="ston" name="status" value = 1 ${cate.status==1?'checked':'' }><br> 
		
		<label for="khoa">Khoá:</label> 
		<input type="radio" id="stoff" name="status" value = 0 ${cate.status==0?'checked':'' }> <br>
		
		<input type= "submit" value= "Update">
	</form>
</body>