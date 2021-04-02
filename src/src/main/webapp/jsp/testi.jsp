/*
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
	</div> */
	
	<%
ArrayList<Candidates> candidateList=(ArrayList<Candidates>)request.getAttribute("candidateslist");

%>

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException {
		response.sendRedirect("index.html");
	}
	)