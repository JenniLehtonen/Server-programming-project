<%@ include file="../header.html" %>
<link rel="stylesheet" href="/jsp/login.css">

				<h1>Kirjaudu sis��n</h1>
				<form action="/login" method="GET">
					<label>K�ytt�j�tunnus : </label><br>
		            <input type="text" placeholder="Sy�t� k�ytt�j�tunnus" name="username"><br> 
		            <label>Salasana : </label><br>
		            <input type="password" placeholder="Sy�t� salasana" name="password"><br>  
		            <button type="submit">Kirjaudu sis��n</button>   
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
						<div id="loginFailed">K�ytt�j�tunnus tai salasana v��rin!</div> <!-- if the password is incorrect, show this -->
				<%
					}
				%>
				
<%@ include file="../footer.html" %>
