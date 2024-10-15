<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<form action="/ltwebjpa/admin/category/update" method="post" enctype="multipart/form-data">
  <input type="text" id="catename" name="cateid" value="${cate.categoryId}" hidden="hidden"><br>
  <label for="catename">Category Name:</label><br>
  <input type="text" id="catename" name="catename" value="${cate.categoryname }"><br>
  <label for="image">Image:</label><br>
  <c:if test="${cate.images.substring(0,5) != 'https'}">
				<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${cate.images.substring(0,5) == 'https'}">
				<c:url value="${cate.images}" var="imgUrl"></c:url>
			</c:if>
			<img height="150" width="200" src="${imgUrl}" id="imagess"/>
  <input type="file" id="images" onchange="chooseFile(this)" name="image">
  <label for="status">Status:</label><br>
  <input type="radio" id="staton" name="status" value = "1" ${cate.status == 1?'checked':''}>
  <label for="status">Dang hoat dong</label><br>
  <input type="radio" id="statoff" name="status" value = "0" ${cate.status == 0?'checked':''}>
  <label for="status">Khong hoat dong</label><br>
  <button type="submit">Update</button>
</form>