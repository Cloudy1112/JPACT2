<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>


<a href="<c:url value="/admin/video/add"/>">Add Video</a>
<br>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Video ID</th>
		<th>Images</th>
		<th>Video Title</th>
		<th>Description</th>
		<th>Views</th>
		<th>Category</th>
		<th>Status</th>
		<th>Action</th>
	</tr>

	<c:forEach items="${listvideo}" var="vid" varStatus="STT">
		<tr>
			<td>${STT.index+1}</td>
			<td>${vid.videoId}</td>

			<c:if test="${vid.poster.substring(0,5) != 'https'}">
				<c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>
			</c:if>

			<c:if test="${vid.poster.substring(0,5) == 'https'}">
				<c:url value="${vid.poster}" var="imgUrl"></c:url>
			</c:if>

			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${vid.title}</td>
			<td>${vid.description}</td>
			<td>${vid.views}</td>
			<td>${vid.category.categoryname}</td>
			<td>${vid.active == 1 ? 'Active' : 'Inactive'}</td>

			<td>
			<a href="<c:url value='/admin/video/edit?id=${vid.videoId}'/>">Sửa</a>
			| <a href="<c:url value='/admin/video/delete?id=${vid.videoId}'/>">Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table>