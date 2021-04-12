<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="data.*"%>
<%@ page import="dao.Dao"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="../header.html" charEncoding="UTF-8"/>




<!--<c:forEach var="candidatePoints" items="${requestScope.pointsHash}">



${candidatePoints.key} | ${candidatePoints.value} <br><br>

</c:forEach>-->



<h2> Sinulle sopivimmat ehdokkaat:</h2>
<br>
<br>

<% int i = 1; %>

<c:forEach var="pointsAndCandidates"
	items="${requestScope.pointsAndCandidates}">

<h3>${pointsAndCandidates.candidate_id}  ${pointsAndCandidates.candidateFirstname} ${pointsAndCandidates.candidateSurname} </h3> <a href="/readformore?ehdokas_id=${pointsAndCandidates.candidate_id}">Lisätietoja</a> <br>
	<br>

</c:forEach>

<c:forEach var="useranswers" items="${requestScope.useranswers}">
	<c:set var="useranswers_string" value="${useranswers_string}${useranswers}" />

	<% i++; %>

</c:forEach>

<a href="/compareUserAnswersToCandidateAnswers?answers=${useranswers_string}" class="btn">Vertaa vastauksiasi</a> <br>


<%@ include file="../footer.html"%>
