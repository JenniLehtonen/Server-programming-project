package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Candidates;

import java.sql.Connection;

public class Dao {
	
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	
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
			System.out.println("Yhdistäminen ei onnistu");
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
	 * Update a name of one candidate. Don't need this yet. Needs all parameters
	 * @param c
	 * @return
	 */
	public ArrayList<Candidates> updateCandidate(Candidates c) {
		try {
			String sql="update ehdokkaat set etunimi=? where ehdokas_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, c.getEtunimi());
			pstmt.setInt(2, c.getEhdokas_id());
			pstmt.executeUpdate();
			return readAllCandidates();
		}
		catch(SQLException e) {
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
		Candidates candidate = null;
		try {
			String sql="select * from ehdokkaat where id=?";
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
			return candidate;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	
	/**
	 * Read candidates based on party
	 * @param party
	 * @return
	 */
	
	public Candidates readByParty(String party) {
		Candidates candidate = null;
		try {
			String sql="select * from ehdokkaat where id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, party);
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
			return candidate;
		}
		catch(SQLException e) {
			return null;
		}
	}

	
}
