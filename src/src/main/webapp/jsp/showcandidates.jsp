<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Candidates" %>   
    
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

<div class= "dropdown">
<select>
<option value="all"> All </option>
<option value="vasemmisto"> Vasemmistoliitto</option>
<option value="demarit"> Suomen Sosialidemokraattinen Puolue</option>
<option value="vihrea"> Vihre├ñ liitto</option>
<option value="kommarit"> Suomen Kommunistinen Puolue</option>
<option value="keskusta"> Suomen Keskusta</option>
<option value="ruotsi"> Suomen ruotsalainen kansanpuolue</option>
<option value="kristityt"> Suomen Kristillisdemokraatit (KD)</option>
<option value="kokoomus"> Kansallinen Kokoomus</option>
<option value="itsenaisyys"> Itsen├ñisyyspuolue</option>
</select>
</div>


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
<%
ArrayList<Candidates> candidateList=(ArrayList<Candidates>)request.getAttribute("candidateslist");
/*
for (int i=0; candidateList != null && i < candidateList.size(); i++){
	Candidates candidate = candidateList.get(i);
	out.println(candidate.getEhdokas_id()+": "+candidate.getEtunimi());
}*/
%>

</body>
</html>