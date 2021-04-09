<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ include file="../header.html" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<c:import url="../header.html" charEncoding="UTF-8"/>

<h2>Muokkaa kysymyksiä</h2>
<form action='update' method='post'>
Question id: <input type='text' name='id' value='${requestScope.question.id}' readonly><br> 
   Question: <input type='text' name='whatquestion' value='${requestScope.question.whatquestion}'><br>
<input type='submit' name='ok' value='Send'> 
</form>
</body>
</html>

<c:import url="../footer.html" charEncoding="UTF-8"/>
