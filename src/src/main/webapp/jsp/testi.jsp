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


<script src="myscriptfile.js"></script>

//Update candidate

try {
			String sql="update ehdokkaat set sukunimi=?, etunimi=?, puolue=?, kotipaikkakunta=?, ika=?, miksi_eduskuntaan=?, mita_asioita_haluat_edistaa=?, ammatti=? where ehdokas_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, candidate.getSukunimi());
			pstmt.setString(2, candidate.getEtunimi());
			pstmt.setString(3, candidate.getPuolue());
			pstmt.setString(4, candidate.getKotipaikkakunta());
			pstmt.setInt(5, candidate.getIka());
			pstmt.setString(6, candidate.getMiksi_eduskuntaan());
			pstmt.setString(7, candidate.getMita_asioita_haluat_edistaa());
			pstmt.setString(8, candidate.getAmmatti());
			pstmt.setInt(9, candidate.getEhdokas_id());
			pstmt.executeUpdate();
			System.out.println("Updated");
			return readAllCandidates();
		}
		catch(SQLException e) {
			System.out.println("Updating fails");
			return null;
		}