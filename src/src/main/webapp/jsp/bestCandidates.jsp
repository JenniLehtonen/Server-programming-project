<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="data.*"%>
<%@ page import="dao.Dao"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="../header.html"%>




<!--<c:forEach var="candidatePoints" items="${requestScope.pointsHash}">



${candidatePoints.key} | ${candidatePoints.value} <br><br>

</c:forEach>-->





<c:forEach var="pointsAndCandidates"
	items="${requestScope.pointsAndCandidates}">

${pointsAndCandidates.candidate_id} | ${pointsAndCandidates.candidateFirstname} ${pointsAndCandidates.candidateSurname} | ${pointsAndCandidates.pointAmount} | <a href="/readformore?ehdokas_id=${pointsAndCandidates.candidate_id}">Lisätietoja</a> <br>
	<br>

</c:forEach>



<%@ include file="../footer.html"%>