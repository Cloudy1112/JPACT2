<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp" %>
<body>
	<form action="${pageContext.request.contextPath}/admin/video/update" method="post" enctype="multipart/form-data">
		<input type="text" id="videoId" name="videoId" value="${video.videoId}" hidden> 
		
		<div class="form-group">
            <label for="title">Video Title:</label>
            <input type="text" id="title" name="title" value="${video.title}">
        </div>

        <div class="form-group">
            <label for="poster">Poster (Image URL):</label>
            <input type="text" id="poster" name="poster">
        </div>
        

        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4">"${video.description}"</textarea>
        </div>

        <div class="form-group">
            <label for="categoryId">Category:</label>
            <select id="categoryId" name="categoryId">
                <c:forEach items="${listcate}" var="category">
                   <option value="${category.categoryid}" ${vid.category.categoryid == category.categoryid ? 'selected' : ''}>
                  		 ${category.categoryname}
                   </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="views">Views:</label>
            <input type="number" id="views" name="views" min="0" value="${video.views}">
        </div>

        <div class="form-group">
            <label for="images1">Upload Poster File:</label>
            <input type="file" id="images1" name="images1">
        </div>

        <div class="form-group">
            <label>Status:</label>
             <input type="radio" id="active" name="active" value=1 ${vid.active == 1 ? 'checked' : ''}>
            <label for="active">Active</label>
            <input type="radio" id="inactive" name="active" value=0 ${vid.active == 0 ? 'checked' : ''}>
            <label for="inactive">Inactive</label>
        </div>

        <input type="submit" value="Edit" class="btn btn-editt">
	</form>
</body>