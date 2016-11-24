<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
  <title>Users Page</title>

  <style type="text/css">
    .tg {
      border-collapse: collapse;
      border-spacing: 0;
      border-color: #ccc;
    }

    .tg td {
      font-family: Arial, sans-serif;
      font-size: 14px;
      padding: 10px 5px;
      border-style: solid;
      border-width: 1px;
      overflow: hidden;
      word-break: normal;
      border-color: #ccc;
      color: #333;
      background-color: #fff;
    }

    .tg th {
      font-family: Arial, sans-serif;
      font-size: 14px;
      font-weight: normal;
      padding: 10px 5px;
      border-style: solid;
      border-width: 1px;
      overflow: hidden;
      word-break: normal;
      border-color: #ccc;
      color: #333;
      background-color: #f0f0f0;
    }

    .tg .tg-4eph {
      background-color: #f9f9f9
    }
  </style>
</head>
<body>
<!-- <a href="index.jsp">Back to main menu</a> -->

<h1>User Manager</h1>

<c:if test="${!empty listUsers}">
  <table class="tg">
    <tr>
      <th width="40">ID</th>
      <th width="120">Name</th>
      <th width="40">Age</th>
      <th width="60">isAdmin</th>
      <th width="60">createdDate</th>
      <th width="60">Edit</th>
      <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listUsers}" var="user">
      <tr>
        <td>${user.id}</td>
        <td><a href="/userdata/${user.id}" target="_blank">${user.name}</a></td>
        <td>${user.age}</td>
        <td>${user.isAdmin}</td>
        <td>${user.createdDate}</td>
        <td><a href="<c:url value='/edit/${user.id}'/>">Edit</a></td>
        <td><a href="<c:url value='/delete/${user.id}'/>">Delete</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<br/>
<div style="padding:5px;">
  <a style="padding:10px;" href="new">Add new student</a>
  <a style="padding:10px;" href="searchUser">Search student</a>
</div>
<br/>
<div align="left" class="panel-footer" style="" id="pagination">
  <c:url value="/users" var="prev">
    <c:param name="page" value="${page-1}"/>
  </c:url>
  <c:if test="${page > 1}">
    <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
  </c:if>

  <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
    <c:choose>
      <c:when test="${page == i.index}">
        <span>${i.index}</span>
      </c:when>
      <c:otherwise>
        <c:url value="/users" var="url">
          <c:param name="page" value="${i.index}"/>
        </c:url>
        <a href='<c:out value="${url}" />'>${i.index}</a>
      </c:otherwise>
    </c:choose>
  </c:forEach>
  <c:url value="/users" var="next">
    <c:param name="page" value="${page + 1}"/>
  </c:url>
  <c:if test="${page + 1 <= maxPages}">
    <a href='<c:out value="${next}" />' class="pn next">Next</a>
  </c:if>
</div>

</body>
</html>
