<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Question" %>   
    <%@ page import="data.Question"%>
<%@ page import="dao.Dao"%>
<%@ page import="app.Update"%>
<%@ page import="app.ReadQuestionToUpdate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<c:import url="../header.html" charEncoding="UTF-8"/>

<h2>Muokkaa kysymyksiä</h2>




<ol>
<c:forEach var="question" items="${requestScope.questionlist}" >
<li>${question.id}: ${question.whatquestion} <a href='/readquestiontoupdate?id=${question.id}'>update</a>
<%System.out.println("looppi toimii");%>
</c:forEach>
</ol>


<c:import url="../footer.html" charEncoding="UTF-8"/>






</body>
</html>