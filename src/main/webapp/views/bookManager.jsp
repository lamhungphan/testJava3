<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Book Management</title>
</head>
<body>
<c:url var="path" value="/book" />
<!-- FORM -->
<form method="post" >
    <label>Id:</label><br>
    <input name="masach" value="${item.masach}">
    <br>
    <label>Name:</label><br>
    <input name="tensach" value="${item.tensach}">
    <br>
    <label>Price:</label><br>
    <input name="gia" value="${item.gia}">
    <br>
    <label>Publish:</label><br>
    <input name="namxb" value="${item.namxb}">
    <br>
    <label>Type:</label><br>
    <input name="loai" value="${item.loai}">
    <br>
    <button formaction="${path}/create">Create</button>
    <button formaction="${path}/update">Update</button>
    <button formaction="${path}/delete">Delete</button>
    <button formaction="${path}/reset">Reset</button>
    <input name="timKiem" placeholder="Enter book info to search">
    <button formaction="${path}/search">Search</button>
</form>
<hr>
<!-- TABLE -->
<table border="1" style="width: 100%">
    <thead>
    <tr>
        <th>No.</th>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Publish Year</th>
        <th>Type</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="d" items="${list}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${d.masach}</td>
            <td>${d.tensach}</td>
            <td>${d.gia}</td>
            <td>${d.namxb}</td>
            <td>${d.loai}</td>
            <td><a href="${path}/edit/${d.masach}">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>