<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.*" %> 
<%@ page import="dao.Dao" %>   
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ include file="../header.html" %>
<link rel="stylesheet" href="/jsp/login.css">

<form method="get" action="addNewQuestions">
<label for="kysymys_id">ID</label> <br>
<input type="text" id="kysymys_id" name="kysymys_id"> <br><br>
<label for="kysymys">Kysymys</label> <br>
<input type="text" id="kysymys" name="kysymys"> <br><br>
<button type="submit" value="Tallenna">Tallenna</button>
</form>

<%@ include file="../footer.html" %>
