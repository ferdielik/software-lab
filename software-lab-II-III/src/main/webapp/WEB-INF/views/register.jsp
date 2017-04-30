<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>

<body>
<h2>Register</h2>
<form:form method = "POST" modelAttribute="user" action = "/register">
<table>
<tr>
    <label>Name</label>
    <td><form:input path ="username" /></td>
    <label>-Password</label>
    <td><form:input path ="password" /></td>

</tr>
<tr>
    <td colspan="2">
        <input type="button" value="Register"/>
    </td>

</tr>

</table>
</form:form>
</body>
</html>