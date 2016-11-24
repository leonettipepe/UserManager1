<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<h1>Registration form</h1>

<c:url var="addAction" value="/users/add"/>

<form:form action="${addAction}" commandName="user">
  <form:hidden path="createdDate"/>
  <table>
    <c:if test="${!empty user.id}">
      <tr>
        <td>
          <form:label path="id">
            <spring:message text="ID"/>
          </form:label>
        </td>
        <td>
          <form:input path="id" readonly="true" size="8" disabled="true"/>
          <form:hidden path="id"/>
        </td>
      </tr>
    </c:if>
    <tr>
      <td>
        <form:label path="name">
          <spring:message text="Name"/>
        </form:label>
      </td>
      <td>
        <form:input path="name"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="age">
          <spring:message text="Age"/>
        </form:label>
      </td>
      <td>
        <form:input path="age"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="isAdmin">
          <spring:message text="Is Admin"/>
        </form:label>
      </td>
      <td>
        <form:input path="isAdmin"/>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <c:if test="${!empty user.id}">
          <input type="submit"
                 value="<spring:message text="Update"/>"/>
        </c:if>
        <c:if test="${empty user.id}">
          <input type="submit"
                 value="<spring:message text="Register"/>"/>
        </c:if>
      </td>
    </tr>
  </table>
</form:form>

<br/>
<br/>
Go back to <a style="padding:10px;" href="../users">List of All Students</a>