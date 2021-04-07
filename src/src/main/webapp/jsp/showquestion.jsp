<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Question" %>  
 <%@ page import="dao.Dao" %> 
 <%@ page import="app.ShowQuestion" %>
 
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%></ol>
 
 
 <%@ include file="/header.html" %>
    
   
    



<h2>Answer the questions</h2><br><br>
<ol>
<c:forEach var="question" items="${requestScope.questionlist}" >
<b>${question.id}: </b>${question.whatquestion}<a>
<br><br><input type="radio" id="1" name="${question.id}" value="1"><label for="1">1  Totally disagree</label><br> </a><a><input type="radio" id="2" name="${question.id}" value="2"><label for="2">2</label> </a><br><a><input type="radio" id="3" name="${question.id}" value="3"><label for="3">3</label><br> </a>
<a><input type="radio" id="4" name="${question.id}" value="4"><label for="4">4</label> </a><br><a><input type="radio" id="Q1" name="5" value="5"><label for="5">5  Totally agree</label> </a>

<br> <br>
</c:forEach>


<%@ include file="../footer.html" %>
