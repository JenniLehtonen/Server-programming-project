<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:import url="../header.html" charEncoding="UTF-8"/>
<link rel="stylesheet" href="center.css">


				<h2>Kirjaudu sisään</h2>
				<form action="/login" method="GET">
					<label>Käyttäjätunnus : </label><br>
		            <input type="text" placeholder="Syötä käyttäjätunnus" name="username"><br> 
		            <label>Salasana : </label><br>
		            <input type="password" placeholder="Syötä salasana" name="password"><br>  
		            <button type="submit">Kirjaudu sisään</button>   
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
					<div id="loginFailed" hidden>Username or password incorrect!</div> <!-- Hide this if MD5Password or password is null -->
				<%
					} else if (data.LoginData.CheckPasswords(MD5Password, password)==false || userProvidedUsername!=username)
					{
				%>
						<div id="loginFailed">Käyttäjätunnus tai salasana väärin!</div> <!-- if the password is incorrect, show this -->
				<%
					}
				%>
				
<%@ include file="../footer.html" %>
