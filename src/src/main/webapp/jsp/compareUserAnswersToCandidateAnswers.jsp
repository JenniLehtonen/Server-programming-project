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
		
		<c:choose> <%-- If the same candidate's name has been shown already, the program won't show it another time --%>
			<c:when test="${answer.ehdokasEtunimi eq firstname}"> 
			<b> </b>
			</c:when>
			<c:when test="${answer.ehdokasEtunimi ne firstname}">
				<b>Ehdokasid: </b>
				${answer.ehdokas_id}<br>
				<b>${answer.ehdokasSukunimi}</b>
				<b>${answer.ehdokasEtunimi}</b>
				<br>
			</c:when>
		</c:choose>
			
			<b>Kysymysid</b>
			${answer.kysymys_id}<br>
			<b>Vastaus</b>
			<b>${answer.vastaus}</b>
			
			<br> <br>
			<c:set var="firstname" value = "${answer.ehdokasEtunimi}" />
			<c:set var="lastname" value = "${answer.ehdokasSukunimi}" />
		</c:forEach>
<%@ include file="../footer.html" %>