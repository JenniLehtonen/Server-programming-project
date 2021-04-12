<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.*" %> 
 <%@ page import="dao.Dao" %> 
 <%@ page import="app.*" %>  
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<c:import url="../header.html" charEncoding="UTF-8"/>

	  <h2>Vertaa vastauksiasi kandidaattien vastauksiin</h2>
		<c:forEach var="answer" items="${requestScope.candidatesAndAnswersList}">
		
		<c:choose> <%-- If the same candidate's name has been shown already, the program won't show it another time --%>
			<c:when test="${answer.ehdokasEtunimi eq firstname}">
			<b> </b>
			</c:when>
			<c:when test="${answer.ehdokasEtunimi ne firstname}">
				<b>Ehdokas: </b>
				<b>${answer.ehdokasSukunimi}</b>
				<b>${answer.ehdokasEtunimi}</b>
				<br>
			</c:when>
		</c:choose>
			
			<p>Kysymys: <span>${answer.kysymys_id}</span></p>
			<p>Vastaus: <span>${answer.vastaus}</span></p>
			
			<br> <br>
			<c:set var="firstname" value = "${answer.ehdokasEtunimi}" />
			<c:set var="lastname" value = "${answer.ehdokasSukunimi}" />
		</c:forEach>
		
		
		
		
<%@ include file="../footer.html" %>