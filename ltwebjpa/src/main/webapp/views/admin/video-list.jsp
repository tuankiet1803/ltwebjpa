<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<a href="/ltwebjpa/admin/video/add">Add Video</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Title</th>
		<th>Description</th>
		<th>Poster</th>
		<th>Category</th>
		<th>Active</th>
		<th>View</th>
	</tr>
	<c:forEach items="${listvideo}" var="video" varStatus="STT">
		<tr class="odd gradeX">
			<td>${STT.index+1 }</td>
			<td>${video.title}</td>
			<td>${video.description}</td>
			<td>
			<c:if test="${video.poster.substring(0,5) != 'https'}">
				<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${video.poster.substring(0,5) == 'https'}">
				<c:url value="${video.poster}" var="imgUrl"></c:url>
			</c:if>
			<img height="150" width="200" src="${imgUrl}" /></td>
			<td>${video.category.categoryname}</td>
			<td>
			<c:if test="${video.active == 1 }">
			<span>Hoat dong</span>
			</c:if>
			<c:if test="${video.active != 1 }">
			<span>Khong Hoat dong</span>
			</c:if>
			</td>
			<td>${video.views }</td>
			<td><a
				href="<c:url value='/admin/category/edit?id=${cate.categoryId }'/>">Sửa</a> | <a
				href="<c:url value='/admin/category/delete?id=${cate.categoryId }'/>">Xóa</a></td>
		</tr>
	</c:forEach>
</table>