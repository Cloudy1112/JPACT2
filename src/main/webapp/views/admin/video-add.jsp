<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp" %>
<body>
	<form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">
		<div class="form-group">
            <label for="title">Video Title:</label>
            <input type="text" id="title" name="title" required>
        </div>

        <div class="form-group">
            <label for="poster">Poster (Image URL):</label>
            <input type="text" id="poster" name="poster">
        </div>
        

        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4"></textarea>
        </div>

        <div class="form-group">
            <label for="categoryId">Category:</label>
            <select id="categoryId" name="categoryId">
                <c:forEach items="${listcate}" var="category">
                    <option value="${category.categoryid}">${category.categoryname}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="views">Views:</label>
            <input type="number" id="views" name="views" min="0">
        </div>

        <div class="form-group">
            <label for="images1">Upload Poster File:</label>
            <input type="file" id="images1" name="images1">
        </div>

        <div class="form-group">
            <label>Status:</label>
            <input type="radio" id="ston" name="active" value=1 checked>
            <label for="ston">Active</label>
            <input type="radio" id="stoff" name="active" value=0>
            <label for="stoff">Inactive</label>
        </div>

        <input type="submit" value="Insert" class="btn btn-insert">
	</form>
</body>