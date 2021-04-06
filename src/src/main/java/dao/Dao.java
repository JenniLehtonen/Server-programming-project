package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import data.Candidates;

import java.sql.Connection;

public class Dao {
	
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	private Statement stmt = null;
	
	public Dao(String url, String user, String pass) {
		this.url=url;
		this.user=user;
		this.pass=pass;
	}
	
	/**
	 * Connecting to database, returns true if the connection is done successfully
	 * @return
	 */
	public boolean getConnection() {
		try {
	        if (conn == null || conn.isClosed()) {
	            try {
	                Class.forName("com.mysql.jdbc.Driver").newInstance();
	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	                throw new SQLException(e);
	            }
	            conn = DriverManager.getConnection(url, user, pass);
	        }
	        return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Yhdist�minen ei onnistu");
			return false;
		}
	}
	
	/**
	 * Read all candidates and add the to the list
	 * @return
	 */
	public ArrayList<Candidates> readAllCandidates() {
		ArrayList<Candidates> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from ehdokkaat");
			while (RS.next()){
				Candidates candidate = new Candidates();
				candidate.setEhdokas_id(RS.getInt("ehdokas_id"));
				candidate.setSukunimi(RS.getString("sukunimi"));
				candidate.setEtunimi(RS.getString("etunimi"));
				candidate.setPuolue(RS.getString("puolue"));
				candidate.setKotipaikkakunta(RS.getString("kotipaikkakunta"));
				candidate.setIka(RS.getInt("ika"));
				candidate.setMiksi_eduskuntaan(RS.getString("miksi_eduskuntaan"));
				candidate.setMita_asioita_haluat_edistaa(RS.getString("mita_asioita_haluat_edistaa"));
				candidate.setAmmatti(RS.getString("ammatti")); 
				list.add(candidate);
			}
			return list;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Update a name of one candidate. 
	 * @param c
	 * @return list of candidates
	 * @throws SQLException 
	 */
	public ArrayList<Candidates> updateCandidate(Candidates candidate) throws SQLException {
			
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
			
	}
	
	/**
	 * Delete candidate based on id
	 * @param id
	 * @return
	 */
	public ArrayList<Candidates> deleteCandidate(String id) {
		try {
			String sql="delete from ehdokkaat where ehdokas_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return readAllCandidates();
		}
		catch(SQLException e) {
			return null;
		}
	}

	/**
	 * Read one candidate based on ehdokas_id
	 * @param id
	 * @return
	 */
	public Candidates readCandidate(String id) {
		System.out.println(id);
		Candidates candidate = null;
		try {
			String sql="select * from ehdokkaat where ehdokas_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS=pstmt.executeQuery();
				while (RS.next()){
					candidate = new Candidates();
	
					candidate.setEhdokas_id(RS.getInt("ehdokas_id"));
					candidate.setSukunimi(RS.getString("sukunimi"));
					candidate.setEtunimi(RS.getString("etunimi"));
					candidate.setPuolue(RS.getString("puolue"));
					candidate.setKotipaikkakunta(RS.getString("kotipaikkakunta"));
					candidate.setIka(RS.getInt("ika"));
					candidate.setMiksi_eduskuntaan(RS.getString("miksi_eduskuntaan"));
					candidate.setMita_asioita_haluat_edistaa(RS.getString("mita_asioita_haluat_edistaa"));
					candidate.setAmmatti(RS.getString("ammatti"));
					
					}
				System.out.println(candidate.getEtunimi());
			return candidate;
		}
			catch(SQLException e) {
				System.out.println("Read candidate fail");
				return null;
		}
		
		
	}
	
	
	/**
	 * Read candidates based on party
	 * @param party
	 * @return
	 */
	
	public ArrayList<Candidates> readByParty(String party) {
		ArrayList<Candidates> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from ehdokkaat where puolue= "+ party;
			//PreparedStatement pstmt=conn.prepareStatement(sql);
			//pstmt.setString(1, party);
			ResultSet RS=stmt.executeQuery(sql);
			
			while (RS.next()){
				Candidates candidate = new Candidates();
				candidate.setEhdokas_id(RS.getInt("ehdokas_id"));
				candidate.setSukunimi(RS.getString("sukunimi"));
				candidate.setEtunimi(RS.getString("etunimi"));
				candidate.setPuolue(RS.getString("puolue"));
				candidate.setKotipaikkakunta(RS.getString("kotipaikkakunta"));
				candidate.setIka(RS.getInt("ika"));
				candidate.setMiksi_eduskuntaan(RS.getString("miksi_eduskuntaan"));
				candidate.setMita_asioita_haluat_edistaa(RS.getString("mita_asioita_haluat_edistaa"));
				candidate.setAmmatti(RS.getString("ammatti")); 
				list.add(candidate);
			}
			return list;
		}
		catch(SQLException e) {
			return null;
		}
	}
	/**
	 * Ei toimi n�in
	 * @return
	 */
	
	public HashMap<Integer, Integer> candidatesAnswers()
	{
		HashMap<Integer, Integer> answers1 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> answers2 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> answers3 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> answers4 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> answers5 = new HashMap<Integer, Integer>();
		//ArrayList<Integer> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			String sql="select ehdokas_id, kysymys_id, vastaus from vastaukset";
			//PreparedStatement pstmt=conn.prepareStatement(sql);
			//pstmt.setInt(1, ehdokas_id);
			ResultSet RS=stmt.executeQuery(sql);
			
				while (RS.next()){
					
					int ehdokas_id = RS.getInt("ehdokas_id");
					int answer = RS.getInt("vastaus");
					int kysymys_id = RS.getInt("kysymys_id");
					
					
					switch(ehdokas_id)
					{
					case 1:
						answers1.put(kysymys_id, answer);
					case 2:
						answers2.put(kysymys_id, answer);
					case 3:
						answers3.put(kysymys_id, answer);
					case 4:
						answers4.put(kysymys_id, answer);
					case 5:
						answers5.put(kysymys_id, answer);
					}
		
				}
				System.out.println("Answers collected");
				System.out.println("Ehdokkaan 1 vastaukset: " + answers1 + ". Ehdokkaan 2 vastaukset: " + answers2 +". Ehdokkaan 3 vastaukset: " + answers3 +". Ehdokkaan 4 vastaukset: " + answers4 +". Ehdokkaan 5 vastaukset: " + answers5 + "." );
			return answers1;
		}
		catch(SQLException e)
		{
			System.out.println("Can't get the answers");
			return null;
		}

	}
	
}
