<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.*" %> 
 <%@ page import="dao.Dao" %>   
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
 <%@ include file="../header.html" %>
<head>
<link rel="stylesheet" href="koodit.css">
</head>
	  <h1 style="text-align:center;">Poista vaalikoneen kysymyksiä</h1>
		<c:forEach var="question" items="${requestScope.questionlist}">
			<b>Kysymysid</b>
			${question.id}<br>
			<b>Kysymys: </b><br>
			${question.whatquestion}<br>	
			<a id="removeButton" href="removeQuestions?id=${question.id}">Poista kysymys</a><br>
		</c:forEach>
		
<%@ include file="../footer.html" %>