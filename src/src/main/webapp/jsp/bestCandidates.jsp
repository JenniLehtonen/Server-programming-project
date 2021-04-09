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

<c:forEach var="pointsAndCandidates"
	items="${requestScope.pointsAndCandidates}">

<h3>${pointsAndCandidates.candidate_id}  ${pointsAndCandidates.candidateFirstname} ${pointsAndCandidates.candidateSurname} </h3> <a href="/readformore?ehdokas_id=${pointsAndCandidates.candidate_id}">Lisätietoja</a> <br>
	<br>

</c:forEach>



<%@ include file="../footer.html"%>