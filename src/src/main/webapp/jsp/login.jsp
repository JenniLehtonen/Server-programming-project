<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:import url="../header.html" charEncoding="UTF-8"/>
<link rel="stylesheet" href="center.css">


				<h2>Kirjaudu sisÃ¤Ã¤n</h2>
				<form action="/login" method="GET">
					<label>KÃ¤yttÃ¤jÃ¤tunnus : </label><br>
		            <input type="text" placeholder="SyÃ¶tÃ¤ kÃ¤yttÃ¤jÃ¤tunnus" name="username"><br> 
		            <label>Salasana : </label><br>
		            <input type="password" placeholder="SyÃ¶tÃ¤ salasana" name="password"><br>  
		            <button type="submit">Kirjaudu sisÃ¤Ã¤n</button>   
				</form>
				<%
					String userProvidedUsername = (String)request.getAttribute("userProvidedUsername");
					String username = (String)request.getAttribute("username");
					String password = (String)request.getAttribute("password");
					String MD5Password = (String)request.getAttribute("MD5Password");
				%>
				<%
					if(data.LoginData.CheckPasswords(MD5Password, password)==true && userProvidedUsername.equals(username)){
						response.sendRedirect("/jsp/adminPage.jsp");
					}
					else if(MD5Password==null || password==null || userProvidedUsername==null)
					{
				%>
					<div id="loginFailed" hidden>K�ytt�j�tunnus tai salasana v��rin!</div> <!-- Hide this if MD5Password or password is null -->
				<%
					} else if (data.LoginData.CheckPasswords(MD5Password, password)==false || userProvidedUsername!=username)
					{
				%>
						<div id="loginFailed">Käyttäjätunnus tai salasana väärin!</div> <!-- if the password is incorrect, show this -->
				<%
					}
				%>
				
<%@ include file="../footer.html" %>
