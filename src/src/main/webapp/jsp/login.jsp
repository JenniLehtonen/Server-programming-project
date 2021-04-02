<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
 <%@ page import="data.LoginData" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="jsp/login.css">
<title>Login</title>
</head>
<body>
	<div class="all">
		<div class="header">
			<h1>Vaalikone</h1>
		</div>
		<div class="navi">
			<a href='index.html'>Home page</a> | <a href="#">Candidates</a> | <a
				href="#">Link</a> | <a href="/login">Admin</a>
		</div>
		<div class="body">
			<div class="body2">
				<h1>Login</h1>
				<form action="/login" method="GET">
					<label>Username : </label><br>
		            <input type="text" placeholder="Enter Username" name="username"><br> 
		            <label>Password : </label><br>
		            <input type="password" placeholder="Enter Password" name="password"><br>  
		            <button type="submit">Login</button>   
				</form>
				
				
				<%
String name = request.getParameter("username");
String password = request.getParameter("password");
%>
			<%
if(!(name == null || (name.equals(""))))
if(name.equals("testi") && password.equals("testi"))
{
%>
<p>Welcome, <%=name %></p>
<%
}
%>
			</div>
		</div>
		<div class="footer">
			<p>&copy; Jenni Lehtonen, Liisa Vuorenmaa, Sanna Nieminen-Vuorio,
				Riikka Siukola</p>
		</div>
	</div>
</body>
</html>