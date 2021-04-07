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

</head>
<body>
<%@ include file="../candidatesList.html" %>


<br><br>

<c:forEach var="candidate" items="${requestScope.bestCandidates}" >
<h2><b>${candidate.ehdokas_id}:</b> ${candidate.etunimi} ${candidate.sukunimi} </h2>
<b>Puolue: </b><br>
${candidate.puolue}<br>
<b>Kotipaikkakunta:</b><br>
${candidate.kotipaikkakunta}<br>
<b>Ikä: </b><br>
${candidate.ika}<br>
<b>Ammatti:</b><br>
${candidate.ammatti}<br>
<b>Miksi haluat eduskuntaan?</b><br>
${candidate.miksi_eduskuntaan}<br>
<b>Mitä asioita haluat edistää?</b><br>
${candidate.mita_asioita_haluat_edistaa}<br>
<br> <br>

</c:forEach>
<%@ include file="../footer.html" %>

</body>
</html>