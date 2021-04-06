<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.*" %> 
 <%@ page import="dao.Dao" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    

<%@ include file="../header.html" %>

<br><br>

<c:forEach var="candidate" items="${requestScope.candidateslist}" >
<b>${candidate.ehdokas_id}:</b> ${candidate.etunimi} ${candidate.sukunimi}  <a href="/updatecandidate">Edit</a> <a href="/deleteCandidate?id=${candidate.ehdokas_id}">Delete</a>

<br> <br>

</c:forEach>


<%@ include file="../footer.html" %>