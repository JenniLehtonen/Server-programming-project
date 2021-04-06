<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.*" %> 
 <%@ page import="dao.Dao" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Candidates</title>



</head>
<body>
<%@ include file="../candidatesList.html" %>

<br><br>

<c:forEach var="candidate" items="${requestScope.candidateslist}" >
<b>${candidate.ehdokas_id}:</b> ${candidate.etunimi} ${candidate.sukunimi}  <a href="/readcandidate?ehdokas_id=${candidate.ehdokas_id}">Edit</a> <a href="/findcandidates?ehdokas_id=${candidate.ehdokas_id}">Show answers</a>

<br> <br>

</c:forEach>

<%@ include file="../footer.html" %>
</body>
</html>