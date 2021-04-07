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
	  <h1>Vertaa vastauksiasi kandidaattien vastauksiin</h1>
		<c:forEach var="answer" items="${requestScope.candidatesAndAnswersList}">
			<b>Ehdokas: </b>
			${answer.ehdokas_id}<br>
			<b>${answer.ehdokasSukunimi}</b>
			<b>${answer.ehdokasEtunimi}</b>
			<b>Kysymysid</b>
			${answer.kysymys_id}<br>
			<b>Vastaus</b>
			<b>${answer.vastaus}</b>
			
			<br> <br>
		</c:forEach>
		<!--<c:forEach var="answer" items="${requestScope.candidateslist}">
			<b>Ehdokkaan nimi: </b>
			${answer.etunimi}<br>
		</c:forEach>-->
<%@ include file="../footer.html" %>