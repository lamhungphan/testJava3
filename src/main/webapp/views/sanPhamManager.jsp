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
<c:url var="path" value="/product" />
<!-- FORM -->
<form method="post" >
    <label>Id:</label><br>
    <input name="id" value="${item.id}">
    <br>
    <label>Product Name</label><br>
    <input name="name" value="${item.name}">
    <br>
    <label>Price:</label><br>
    <input name="price" value="${item.price}">
    <br>
    <label>Type:</label><br>
    <input name="type" value="${item.type}">
    <br>
    <button formaction="${path}/create">Create</button>
    <button formaction="${path}/update">Update</button>
    <button formaction="${path}/delete">Delete</button>
    <button formaction="${path}/reset">Reset</button>
    <label>Filter by type:</label>
    <select name="loai">
        <option value="Tatca">Tất cả</option>
        <option value="Bánh kẹo">Bánh kẹo</option>
        <option value="Rượu bia">Rượu bia</option>
        <option value="Hải sản">Hải sản</option>
    </select>

    <button formaction="${path}/filter">Search</button>
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
        <th>Type</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="d" items="${list}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${d.id}</td>
            <td>${d.name}</td>
            <td>${d.price}</td>
            <td>${d.type}</td>
            <td><a href="${path}/edit/${d.id}">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>