<%@ include file="../header.html" %>
<head>
<link rel="stylesheet" href="/jsp/login.css">
</head>
				<h1>Login</h1>
				<form action="/login" method="GET">
					<label>Username : </label><br>
		            <input type="text" placeholder="Enter Username" name="username"><br> 
		            <label>Password : </label><br>
		            <input type="password" placeholder="Enter Password" name="password"><br>  
		            <button type="submit">Login</button>   
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
						<div id="loginFailed">Username or password incorrect!</div> <!-- if the password is incorrect, show this -->
				<%
					}
				%>
				
<%@ include file="../footer.html" %>
