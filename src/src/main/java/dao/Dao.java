package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import app.Answer;
import data.Candidates;
import data.CandidatesAndAnswers;
import data.Question;

import java.sql.Connection;

/***
 * 
 * @author Liisa, Riikka, Jenni, Sanna
 * 
 * This class contains methods that we use to connect to the database and that we use to edit information in the database
 *
 */
public class Dao {

	private String url;
	private String user;
	private String pass;
	private Connection conn;

	public Dao(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	/**
	 * Connecting to database, returns true if the connection is done successfully
	 *
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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Yhdistäminen ei onnistu");
			return false;
		}
	}

	/**
	 * Read all candidates and add the to the list
	 *
	 * @return
	 */
	public ArrayList<Candidates> readAllCandidates() {
		ArrayList<Candidates> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet RS = stmt.executeQuery("select * from ehdokkaat");
			while (RS.next()) {
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
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Read candidates' answers to the questions
	 *
	 * @return
	 */
	public ArrayList<CandidatesAndAnswers> readAllAnswers(){
		ArrayList<CandidatesAndAnswers> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet RS = stmt.executeQuery("select vastaukset.kysymys_id, vastaukset.vastaus, ehdokkaat.ehdokas_id, ehdokkaat.etunimi, ehdokkaat.sukunimi from vastaukset inner join ehdokkaat on vastaukset.ehdokas_id=ehdokkaat.ehdokas_id;");
			while (RS.next()) {
				CandidatesAndAnswers answer = new CandidatesAndAnswers();
				answer.setEhdokasEtunimi(RS.getString("etunimi"));
				answer.setEhdokasSukunimi(RS.getString("sukunimi"));
				answer.setEhdokas_id(RS.getInt("ehdokas_id"));
				answer.setVastaus(RS.getInt("vastaus"));
				answer.setKysymys_id(RS.getInt("kysymys_id"));
				list.add(answer);
			}
			return list;
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * Read all questions and add the to the list
	 *
	 * @return
	 */

	public ArrayList<Question> readAllQuestion() {
		ArrayList<Question> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from kysymykset");
			while (RS.next()){
				Question f=new Question();
				f.setId(RS.getInt("KYSYMYS_ID"));
				f.setWhatquestion(RS.getString("KYSYMYS"));
				list.add(f);
			}
			System.out.println("Lista haettu");
			return list;
		}
		catch(SQLException e) {
			System.out.println("Listaa ei haettu");
			return null;
			
		}
	}
	
	/**
	 * reads candidates' responses from the database by candidate id and question id
	 *@param id
	 * @return
	 */
	
	public ArrayList<CandidatesAndAnswers> readCandidatesAnswers(int id) {
		CandidatesAndAnswers a = null;
		ArrayList<CandidatesAndAnswers> list = new ArrayList<>();
		try {
			String sql="select * from vastaukset where ehdokas_id=? order by kysymys_id";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet RS=pstmt.executeQuery();
			while (RS.next()){
				a = new CandidatesAndAnswers();
				a.setEhdokas_id(RS.getInt("ehdokas_id"));
				a.setVastaus(RS.getInt("vastaus"));
				a.setKysymys_id(RS.getInt("kysymys_id"));			
				list.add(a);
			}
			return list;
		}
		catch(SQLException e) {
			return null;
		}
		}
	
	/**
	 *  Add new question to the database
	 *@param q is the new question that the user wants to add to the database
	 * @return function that executes an sql command that searches all the questions that are in the database
	 */

	public ArrayList<Question> addQuestion(Question q) {
		String sql = "INSERT INTO kysymykset (kysymys) VALUES (?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, q.getWhatquestion());
			pstmt.executeUpdate();
			return readAllQuestion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Update question by question id
	 *@param f
	 * @return
	 */
	public ArrayList<Question> updateQuestion(Question f) {
		try {
			String sql="update kysymykset set kysymys=? where KYSYMYS_ID=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, f.getWhatquestion());
			pstmt.setInt(2, f.getId());
			pstmt.executeUpdate();
			return readAllQuestion();
		}
		catch(SQLException e) {
			return null;
		}
	}
	/**
	 * Read one question by question id
	 *@param id
	 * @return
	 */
	public Question readQuestion(String id) {
		Question f=null;
		try {
			String sql="select * from kysymykset where KYSYMYS_ID=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS=pstmt.executeQuery();
			while (RS.next()){
				f=new Question();
				f.setId(RS.getInt("KYSYMYS_ID"));
				f.setWhatquestion(RS.getString("KYSYMYS"));
			}
			System.out.println("Lista 1kysymys");
			return f;
		}
		catch(SQLException e) {
			return null;
		}
	}

	/**
	 * Update  information of one candidate by id. 
	 *
	 * @param candidate
	 * @return
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
	 * Delete candidate by id
	 *
	 * @param id
	 * @return
	 */
	public ArrayList<Candidates> deleteCandidate(String id) {
		try {
			String sql = "delete from ehdokkaat where ehdokas_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return readAllCandidates();
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * Remove question from database by id
	 *@param id of the question that needs to be removed.
	 * @return function that executes an sql command that searches all the questions that are in the database
	 */
	public ArrayList<Question> removeQuestion(String id) {
		try {
			String sql = "delete from kysymykset where kysymys_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return readAllQuestion();
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 *  Add new candidate's information to the database
	 *@param c
	 * @return
	 */
	public ArrayList<Candidates> addCandidate(Candidates c) {
		String sql = "INSERT INTO ehdokkaat (ehdokas_id, sukunimi, etunimi, puolue, kotipaikkakunta, ika, miksi_eduskuntaan, mita_asioita_haluat_edistaa, ammatti) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getEhdokas_id());
			pstmt.setString(2, c.getEtunimi());
			pstmt.setString(3, c.getSukunimi());
			pstmt.setString(4, c.getPuolue());
			pstmt.setString(5, c.getKotipaikkakunta());
			pstmt.setInt(6, c.getIka());
			pstmt.setString(7, c.getMiksi_eduskuntaan());
			pstmt.setString(8, c.getMita_asioita_haluat_edistaa());
			pstmt.setString(9, c.getAmmatti());
			pstmt.executeUpdate();
			return readAllCandidates();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Read information of one candidate by id
	 *
	 * @param id
	 * @return
	 */
	public Candidates readCandidate(String id) {
		Candidates candidate = null;
		try {
			String sql = "select * from ehdokkaat where ehdokas_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS = pstmt.executeQuery();
			while (RS.next()) {
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
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Read candidates by parties
	 *
	 * @param party
	 * @return
	 */

	public ArrayList<Candidates> readByParty(String party) {
		ArrayList<Candidates> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from ehdokkaat where puolue= " + party;
			// PreparedStatement pstmt=conn.prepareStatement(sql);
			// pstmt.setString(1, party);
			ResultSet RS = stmt.executeQuery(sql);

			while (RS.next()) {
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
		} catch (SQLException e) {
			return null;
		}
	}


	
	/**
	 * Add candidate's answers to database
	 * Returns string, which tells if the adding was done successfully or not
	 * @param list
	 * @param ehdokas_id
	 * @return
	 */
				public String addCandidateAnswers(ArrayList<Integer> list, int ehdokas_id)
				{
					String sql; 
					String done = null;
	
					try {
			
						for(int i = 0; i<list.size();i++)
						{
							sql = "insert into vastaukset (ehdokas_id, kysymys_id, vastaus, kommentti) VALUES (?, ?, ?, ?)";
					        
					        PreparedStatement statement = conn.prepareStatement(sql);
					        statement.setInt(1, ehdokas_id);
					        statement.setInt(2, (i+1));
					        statement.setInt(3, list.get(i));
					        statement.setString(4, "Ehdokkaan " + ehdokas_id + " vastaus kysymykseen " + (i+1));
					         
					        int rowsInserted = statement.executeUpdate();
					        if (rowsInserted > 0) {
					            System.out.println("Tiedot sy�tetty tauluun.");
					            }
					        
					        done = "Vastausten tallennus onnistui!";
						}

						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Tallennus ei onnistunut!");
						done = "Jotain meni vikaan, eikä tietojen tallennus onnistunut. Tarkista, että ehdokasnumero on oikein.  Jos olet jo vastannut kysymyksiin, päivitä vastaukset kohdasta Muokkaa vastauksia. ";
					}
					return done;
					
				} //addCandidateAnswers-sulje
				
				/**
				 * Update candidate's answers to database
				 * Returns string, which tells if the updating was done successfully or not
				 * @param list
				 * @param ehdokas_id
				 * @return
				 */
				
				public String updateCandidateAnswer(ArrayList<Integer> list, int ehdokas_id)
				{
					String sql; 
					String done = "Jotain meni vikaan, eikä tietojen päivitys onnistunut. Tarkista, että ehdokasnumero on oikein.  Jos et ole vielä vastannut kysymyksiin, vastaa kysymyksiin kohdasta Vastaa kysymyksiin. ";
					
					try {
			
						for(int i = 0; i<list.size();i++)
						{
							sql = "update vastaukset set vastaus = ? where ehdokas_id = ? and kysymys_id = ?";
					        
					        PreparedStatement statement = conn.prepareStatement(sql);
					        statement.setInt(1, list.get(i));
					        statement.setInt(2, ehdokas_id);
					        statement.setInt(3, (i+1));

					        int rowsInserted = statement.executeUpdate();
					        if (rowsInserted > 0) {
					            System.out.println("Tiedot p�ivitetty tauluun.");
					            done = "Vastausten tallennus onnistui!";
					            }
					        
						}

						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("P�ivitys ei onnistunut!");
						done = "Jotain meni vikaan, eikä tietojen päivitys onnistunut. Tarkista, että ehdokasnumero on oikein.  Jos et ole vielä vastannut kysymyksiin, vastaa kysymyksiin kohdasta Vastaa kysymyksiin. ";
					}
					
					return done;
					
				} //updateCandidateAnswer-sulje

}
