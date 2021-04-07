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
	  <h1>Poista vaalikoneen kysymyksiä</h1>
		<c:forEach var="question" items="${requestScope.questionlist}">

			<b>Kysymysid</b>
			${question.id}<a href="removeQuestion?id=${question.id}">Poista kysymys</a><br>
			<b>Kysymys</b><br>
			<b>${question.whatquestion}</b><br>	
		</c:forEach>
		
<%@ include file="../footer.html" %>