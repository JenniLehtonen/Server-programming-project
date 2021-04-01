<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Candidates" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fish application</title>

<link rel="stylesheet" type="text/css" href="main.css">
<script src="myscriptfile.js"></script>

</head>
<body>
<h2>Fish application</h2>
<ol>
<c:forEach var="candidate" items="${requestScope.candidateslist}" >
<li>${fish.id}: ${fish.breed}
</c:forEach>
</ol>

<%
ArrayList<Candidates> candidateList=(ArrayList<Candidates>)request.getAttribute("candidateslist");

for (int i=0; candidateList != null && i < candidateList.size(); i++){
	Candidates candidate = candidateList.get(i);
	out.println(candidate.getEhdokas_id()+": "+candidate.getEtunimi());
}
%>

<%@ include file="../src/main/webapp/index.html" %>



</body>
</html>