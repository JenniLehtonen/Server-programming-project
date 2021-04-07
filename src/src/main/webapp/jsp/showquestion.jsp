<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Question" %>  
 <%@ page import="dao.Dao" %> 
 <%@ page import="app.ShowQuestion" %>
 <%@ include file="/header.html" %>
    
   
    



<h2>Answer the questions</h2><br><br>
<ol>
<c:forEach var="question" items="${requestScope.questionlist}" >
<b>${question.id}: </b>${question.whatquestion}<a>
<br><br><input type="radio" id="1" name="Q1" value="1"><label for="1">1  Totally disagree</label><br> </a><a><input type="radio" id="2" name="Q1" value="2"><label for="2">2</label> </a><br><a><input type="radio" id="3" name="Q3" value="3"><label for="3">3</label><br> </a>
<a><input type="radio" id="4" name="Q1" value="4"><label for="4">4</label> </a><br><a><input type="radio" id="Q1" name="5" value="5"><label for="5">5  Totally agree</label> </a>

<br> <br>
</c:forEach>

 <%
            @SuppressWarnings("unchecked") 
            ArrayList<Question> questionList = (ArrayList<Question>)request.getAttribute("questionlist");
            for (int i=0;questionList!=null && i<questionList.size();i++) {
            	Question question=questionList.get(i);
            	%>
            <div>
                <%= question.getId() %> <br>
                <%= question.getWhatquestion() %>
                 </div>
                <form><b>
                     <input type = "radio" name = "Q1" value="1"> 1 Totally agreed<br>
    				 <input type = "radio" name = "Q1" value="2"> 2<br>
    				 <input type = "radio" name = "Q1" value="3"> 3<br>
    				 <input type = "radio" name = "Q1" value="4"> 4<br>
    				 <input type = "radio" name = "Q1" value="5"> 5 Totally disagreed<br>
                </b></form>
                    
                <%
            } 
        %>



 <%/***
 @SuppressWarnings("unchecked")
 ArrayList<Question> questionList=(ArrayList<Question>)request.getAttribute("questionlist");
 Question f2=questionList.get(0);
 Question f3=questionList.get(1);
 Question f4=questionList.get(2);
 Question f5=questionList.get(3);
 Question f6=questionList.get(4);
 Question f7=questionList.get(5);
 Question f8=questionList.get(6);
 Question f9=questionList.get(7);
 Question f10=questionList.get(8);
 Question f11=questionList.get(9);
 Question f12=questionList.get(10);
 Question f13=questionList.get(11);
 Question f14=questionList.get(12);
 Question f15=questionList.get(13);
 Question f16=questionList.get(14);
/** Question f17=questionList.get(15);
 Question f18=questionList.get(16);
 Question f19=questionList.get(17);
 Question f20=questionList.get(18);
 Question f21=questionList.get(19);***/
 /***out.println("<form action=\"Game\" method=\"POST\">" +
			
		 
 
    "<b>"+f2.getId()+": "+f2.getWhatquestion()+" </b><br> <br>" +//tee looppi radiobutton id = kysymys id
    

    "<input type = \"radio\" name = \"Q1\" value=\"1\"> 1 Totally agreed<br>" +
    "<input type = \"radio\" name = \"Q1\" value=\"2\"> 2<br>" +
    "<input type = \"radio\" name = \"Q1\" value=\"3\"> 3<br>" +
    "<input type = \"radio\" name = \"Q1\" value=\"4\"> 4<br>" +
    "<input type = \"radio\" name = \"Q1\" value=\"5\"> 5 Totally disagreed<br>" +
    
    "<br><br><b>"+f3.getId()+": "+f3.getWhatquestion()+"</b><br> <br>"+

"<input type = \"radio\" name = \"Q2\" value=\"1\"> 1 Totally agreed<br>" +
"<input type = \"radio\" name = \"Q2\" value=\"2\"> 2<br>" +
"<input type = \"radio\" name = \"Q2\" value=\"3\"> 3<br>" +
"<input type = \"radio\" name = \"Q2\" value=\"4\"> 4<br>" +
"<input type = \"radio\" name = \"Q2\" value=\"5\"> 5 Totally disagreed<br>" +

"<br><br><b>"+f4.getId()+": "+f4.getWhatquestion()+"</b><br> <br>"+
"<input type = \"radio\" name = \"Q3\" value=\"1\"> 1 Totally agreed <br>" +
"<input type = \"radio\" name = \"Q3\" value=\"2\"> 2<br>" +
"<input type = \"radio\" name = \"Q3\" value=\"3\"> 3<br>" +
"<input type = \"radio\" name = \"Q3\" value=\"4\"> 4<br>" +
"<input type = \"radio\" name = \"Q3\" value=\"5\"> 5 Totally disagreed<br>" +

"<br><br><b>"+f5.getId()+": "+f5.getWhatquestion()+"</b><br> <br>" +

"<input type = \"radio\" name = \"Q4\" value=\"1\"> 1 Totally agreed <br>" +
"<input type = \"radio\" name = \"Q4\" value=\"2\"> 2<br>" +
"<input type = \"radio\" name = \"Q4\" value=\"3\"> 3<br>" +
"<input type = \"radio\" name = \"Q4\" value=\"4\"> 4<br>" +
"<input type = \"radio\" name = \"Q4\" value=\"5\"> 5 Totally disagreed<br>" +
    "<Center><a href=>/SaveAnswers</a><INPUT  TYPE=\"SUBMIT\"></Center>" +

    "</form>"
    );***/%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%></ol>


<%@ include file="../footer.html" %>
