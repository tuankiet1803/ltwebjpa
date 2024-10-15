<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<a href="/ltwebjpa/admin/category/add">Add Category</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>CagetoryID</th>
		<th>CategoryName</th>
		<th>Status</th>
	</tr>
	<c:forEach items="${listcate}" var="cate" varStatus="STT">
		<tr class="odd gradeX">
			<td>${STT.index+1 }</td>
			<td>
			<c:if test="${cate.images.substring(0,5) != 'https'}">
				<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${cate.images.substring(0,5) == 'https'}">
				<c:url value="${cate.images}" var="imgUrl"></c:url>
			</c:if>
			<img height="150" width="200" src="${imgUrl}" /></td>
			<td>${cate.categoryname}</td>
			<td>
			<c:if test="${cate.status == 1 }">
			<span>Hoat dong</span>
			</c:if>
			<c:if test="${cate.status != 1 }">
			<span>Khong Hoat dong</span>
			</c:if>
			</td>
			<td><a
				href="<c:url value='/admin/category/edit?id=${cate.categoryId }'/>">Sửa</a> | <a
				href="<c:url value='/admin/category/delete?id=${cate.categoryId }'/>">Xóa</a></td>
		</tr>
	</c:forEach>
</table>
