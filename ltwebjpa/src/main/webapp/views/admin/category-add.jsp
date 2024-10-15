<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="/ltwebjpa/admin/category/insert" method="post" enctype="multipart/form-data">
  <label for="catename">Category Name:</label><br>
  <input type="text" id="catename" name="catename"><br>
  <label for="image">Image:</label><br>
  <img height="150" width="200" id="imagess" src="" />
  <input type="file" onchange="chooseFile(this)" id="images" name="image">
  <label for="status">Status:</label><br>
  <input type="radio" id="staton" name="status" value = "1" checked>
  <label for="status">Dang hoat dong</label><br>
  <input type="radio" id="statoff" name="status" value = "0" >
  <label for="status">Khong hoat dong</label><br>
  <button type="submit">Them</button>
</form>