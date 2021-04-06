<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ include file="html/somehtml.html" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Election machine</title>
</head>
<body>
<h2>Edit question</h2>
<form action='update' method='post'>
Question id: <input type='text' name='id' value='${requestScope.question.id}' readonly><br> 
Question: <input type='text' name='breed' value='${requestScope.question.whatquestion}'><br>
<input type='submit' name='ok' value='Send'> 
</form>
</body>
</html>