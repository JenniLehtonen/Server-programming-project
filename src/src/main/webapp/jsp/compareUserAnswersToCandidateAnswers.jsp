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
	  <h1>Vertaa vastauksiasi kandidaattien vastauksiin </h1>
		<c:forEach var="answer" items="${requestScope.answerslist}" >
			<b>Ehdokasid: </b><br>
			
			<!--<c:forEach var="candidate" items="${requestScope.candidateslist}">
			<b>Ehdokasnimi:</b> 
			${candidate.etunimi}
			</c:forEach>-->
			
			${answer.ehdokas_id} <br>
			<b>Kysymysid</b>
			${answer.kysymys_id}<br>
			<b>Vastaus</b>
			<b>${answer.vastaus}</b>
			<br> <br>
		</c:forEach>
		<%@ include file="../footer.html" %>