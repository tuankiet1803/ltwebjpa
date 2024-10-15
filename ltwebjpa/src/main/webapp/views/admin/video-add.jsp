<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<form action="/ltwebjpa/admin/video/insert" method="post" enctype="multipart/form-data">
  <label for="catename">Title:</label><br>
  <input type="text" id="title" name="title"><br>
  <label for="catename">Description:</label><br>
  <input type="text" id="title" name="description"><br>
  <label for="image">Poster:</label><br>
  <img height="150" width="200" id="imagess" src="" />
  <input type="file" onchange="chooseFile(this)" id="images" name="poster">
  <label for="status">Active:</label><br>
  <input type="radio" id="staton" name="status" value = "1" checked>
  <label for="status">Dang hoat dong</label><br>
  <input type="radio" id="statoff" name="status" value = "0" >
  <label for="status">Khong hoat dong</label><br>
  <label for="category">The loai</label><br>
  <select name="category" id="cars">
     <c:forEach items="${listcate}" var="cate">
     <option value="${cate.categoryId }">${cate.categoryname}</option>
     </c:forEach>
  </select>
  <button type="submit">Them</button>
</form>