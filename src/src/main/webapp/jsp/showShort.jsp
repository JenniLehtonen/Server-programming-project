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

<link rel="stylesheet" type="text/css" href="main.css">
<script src="myscriptfile.js"></script>

</head>
<body>
<%@ include file="../candidatesList.html" %>

<br><br>

<c:forEach var="candidate" items="${requestScope.candidateslist}" >
<h2><b>${candidate.ehdokas_id}:</b> ${candidate.etunimi} ${candidate.sukunimi} </h2> <a href="#">Edit</a> <a href="#">Delete</a>
<br> <br>

</c:forEach>


</body>
</html>