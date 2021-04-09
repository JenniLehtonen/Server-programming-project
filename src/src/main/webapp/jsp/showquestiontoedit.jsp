<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ include file="../header.html" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muokkaa kysymyksiä</title>
</head>
<body>
<h2>Muokkaa kysymyksiä</h2>
<form action='/saveupdatedquestion' method='post'>
Question id: <input type='text' name='id' value='${requestScope.question.id}' readonly><br> 
   Question: <input type='text' name='whatquestion' value='${requestScope.question.whatquestion}'><br>
<input type='submit' name='ok' value='Send'> 
</form>
</body>
</html>

<%@ include file="../footer.html" %>