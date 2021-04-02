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

<div class="dropdown">
  <button class="dropbtn">Dropdown</button>
  <div class="dropdown-content">
  	<a href='/showcandidates'>All candidates</a>
    <a href="/readparty?party='Vasemmistoliitto'">Vasemmistoliitto</a>
    <a href="/readparty?party='Suomen Sosialidemokraattinen Puolue'">Suomen Sosialidemokraattinen Puolue</a>
    <a href="/readparty?party='Vihre├ñ liitto'">Vihreä liitto</a>
    <a href="/readparty?party='Suomen Kommunistinen Puolue'">Suomen Kommunistinen Puolue</a>
    <a href="/readparty?party='Suomen Keskusta'">Suomen Keskusta</a>
    <a href="/readparty?party='Suomen ruotsalainen kansanpuolue'">Suomen ruotsalainen kansanpuolue</a>
    <a href="/readparty?party='Suomen Kristillisdemokraatit (KD)'">Suomen Kristillisdemokraatit (KD)</a>
    <a href="/readparty?party='Kansallinen Kokoomus'">Kansallinen Kokoomus</a>
    <a href="/readparty?party='Itsen├ñisyyspuolue'">Itsenäisyyspuolue</a>
    
  </div>
</div>
<br><br>

<ol>
<c:forEach var="candidate" items="${requestScope.candidateslist}" >
<li id="name">${candidate.ehdokas_id}: ${candidate.etunimi} ${candidate.sukunimi}
<li id="bold">Puolue: 
<li>${candidate.puolue}
<li id="bold">Kotipaikkakunta:
<li>${candidate.kotipaikkakunta}
<li id="bold">Ikä:
<li>${candidate.ika}
<li id="bold">Ammatti:
<li>${candidate.ammatti}
<li id="bold">Miksi haluat eduskuntaan?
<li>${candidate.miksi_eduskuntaan}
<li id="bold">Mitä asioita haluat edistää?
<li>${candidate.mita_asioita_haluat_edistaa}
<br> <br>

</c:forEach>
</ol>


</body>
</html>