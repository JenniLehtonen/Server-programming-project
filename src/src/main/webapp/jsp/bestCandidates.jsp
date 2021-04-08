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







<%
int i = 0;
%>

<c:forEach var="pointsAndCandidates"
	items="${requestScope.pointsAndCandidates}">

	<c:set var="currentID" value="${pointsAndCandidates.getCandidate_id()}" />
	<c:set var="candidate" value="${candidatelist.get(i)}" />


${pointsAndCandidates.getCandidate_id()} | ${candidate.getEtunimi()} ${candidate.getSukunimi()} | ${pointsAndCandidates.getPointAmount()} <br>
	<br>

	<%
	i = i++;
	%>

</c:forEach>



<%@ include file="../footer.html"%>