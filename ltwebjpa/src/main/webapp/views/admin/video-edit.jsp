<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<form action="/ltwebjpa/admin/video/update" method="post" enctype="multipart/form-data">
  <input type="text" id="catename" name="videoid" value="${video.videoId}" hidden="hidden"><br>
  <label for="catename">Title:</label><br>
  <input type="text" id="catename" name="title" value="${video.title }"><br>
  <label for="catename">Description:</label><br>
  <input type="text" id="title" name="description" value="${video.description }"><br>
  <label for="image">Poster:</label><br>
  <c:if test="${video.poster.substring(0,5) != 'https'}">
	<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
  </c:if>
  <c:if test="${video.poster.substring(0,5) == 'https'}">
	<c:url value="${video.poster}" var="imgUrl"></c:url>
  </c:if>
  <img height="150" width="200" src="${imgUrl}" id="imagess"/>
  <input type="file" id="images" onchange="chooseFile(this)" name="poster"><br>
    <c:if test="${video.videoname.substring(0,5) != 'https'}">
	<c:url value="/video?fname=${video.videoname}" var="videoUrl"></c:url>
  </c:if>
  <c:if test="${video.videoname.substring(0,5) == 'https'}">
	<c:url value="${videoname.videoname}" var="videoUrl"></c:url>
  </c:if>
  <video src="${videoUrl}"></video>
  <input type="file" id="images" onchange="chooseFile(this)" name="videoname">
  <label for="status">Active:</label><br>
  <input type="radio" id="staton" name="active" value = "1" ${video.active == 1?'checked':''}>
  <label for="status">Dang hoat dong</label><br>
  <input type="radio" id="statoff" name="active" value = "0" ${video.active == 0?'checked':''}>
  <label for="status">Khong hoat dong</label><br>
    <select name="category" id="cars">
     <c:forEach items="${listcate}" var="cate">
     <option value="${cate.categoryId }">${cate.categoryname}</option>
     </c:forEach>
  </select>
  <button type="submit">Update</button>
</form>