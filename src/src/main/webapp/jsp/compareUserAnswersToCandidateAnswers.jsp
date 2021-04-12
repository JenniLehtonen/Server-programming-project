<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="data.*"%>
<%@ page import="dao.Dao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../header.html" charEncoding="UTF-8" />
<link rel="stylesheet" href="koodit.css">
<link rel="stylesheet" href="center.css">

<h2>Vertaa vastauksiasi kandidaattien vastauksiin</h2>
<br>

<div style="display:flex;">
<div style="flex:50%;">
		<c:forEach var="answer"
			items="${requestScope.candidatesAndAnswersList}">

			<c:choose>
				<%-- If the same candidate's name has been shown already, the program won't show it another time --%>
				<c:when test="${answer.ehdokasEtunimi eq firstname}">
					<b> </b>
				</c:when>
				<c:when test="${answer.ehdokasEtunimi ne firstname}">
					<b>Ehdokas: </b>
					<b>${answer.ehdokasSukunimi}</b>
					<b>${answer.ehdokasEtunimi}</b>
					<br>
					<br>
				</c:when>
			</c:choose>

Kysymys: <span>${answer.kysymys_id}</span>,
Vastaus: <span><b>${answer.vastaus}</b></span>

			<br>
			<br>
			<c:set var="firstname" value="${answer.ehdokasEtunimi}" />
			<c:set var="lastname" value="${answer.ehdokasSukunimi}" />
		</c:forEach>

	</div>

	<% int i = 1; %>


<div style="flex:50%;">
			
			<% for (int j=0; j<20;j++) { %>
			<% i=1; %>

	<b>Sinä:</b><br><br>
		<c:forEach var="useranswer" items="${requestScope.useranswers}">
		
		<c:set var="realAnswer" value="${useranswer-48}"/>
	
Kysymys: <span>	<%= i %></span>,
Vastaus: <span><b>${realAnswer}</b></span><br><br>


<% i++; %>

		</c:forEach>
		
		<% } %>
		

	</div>
	</div>
	
<%@ include file="../footer.html"%>