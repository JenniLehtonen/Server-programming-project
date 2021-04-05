package data;

public class Question {
	private int id;
	private String whatquestion;
	public Question(String id, String whatquestion) {
		// TODO Auto-generated constructor stub
		setId(id);
		this.whatquestion=whatquestion;
	}
	public Question() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value of id won't be changed
		}
	}
	public String getWhatquestion() {
		return whatquestion;
	}
	public void setWhatquestion(String whatquestion) {
		this.whatquestion = whatquestion;
	}
}
