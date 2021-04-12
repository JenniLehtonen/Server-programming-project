<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="data.Question"%>
<%@ page import="dao.Dao"%>
<%@ page import="app.ShowQuestion"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ include file="/header.html"%>


<h2>Vastaa kysymyksiin:</h2>
<br>
<br>
<form method="post" action="/updatecandidateanswer">
	<c:forEach var="question" items="${requestScope.questionlist}">
		<b>${question.id}: </b>${question.whatquestion} <br> <br>
			<input type="radio" id="${question.id}1" name="${question.id}"
			value="1"><label for="${question.id}1">1 Totally
				disagree</label><br> <input type="radio" id="${question.id}2"
			name="${question.id}" value="2"><label for="${question.id}2">2</label>
			<br> <input type="radio" id="${question.id}3"
			name="${question.id}" value="3"> <label for="${question.id}3">3</label>
			<br> <input type="radio" id="${question.id}4"
			name="${question.id}" value="4"> <label for="${question.id}4">4</label>
			<br> <input type="radio" id="${question.id}5"
			name="${question.id}" value="5"><label for="${question.id}5">5
				Totally agree</label> <br> <br>
	</c:forEach>
	<label> Syötä tähän olemassa oleva vaalinumerosi:</label>
	<input type="text" name="ehdokas_id" value="">
	<input type="submit" value="Tallenna vastaukset" class='dropbtn'>

</form>


<%@ include file="../footer.html"%>