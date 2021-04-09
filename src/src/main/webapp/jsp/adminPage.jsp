<%@ page import="data.LoginData" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:import url="../adminHeader.html" charEncoding="UTF-8"/>
<link rel="stylesheet" href="../center.css">
	
<a class="adminButtons" href="showshort">Muokkaa ehdokkaita</a> 
<a class="adminButtons" href="addCandidate">Lisää uusi ehdokas</a>
<a class="adminButtons" href="editQuestions">Edit questions</a> <br>
<a class="adminButtons" href="/addNewQuestions">Lisää kysymys</a>
<a class="adminButtons" href='/removeQuestions'>Poista kysymyksiä</a> 
<a class="adminButtons" href="showquestiontoedit">Edit question</a>
		
<%@ include file="../footer.html" %>