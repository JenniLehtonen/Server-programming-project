package data;

public class Points {
	private int candidate_id;
	private int pointAmount;
	private String candidateFirstname;
	private String candidateSurname;
	
	public String getCandidateFirstname() {
		return candidateFirstname;
	}

	public void setCandidateFirstname(String candidateFirstname) {
		this.candidateFirstname = candidateFirstname;
	}

	public String getCandidateSurname() {
		return candidateSurname;
	}

	public void setCandidateSurname(String candidateSurname) {
		this.candidateSurname = candidateSurname;
	}

	public Points() {
		
	}
	
	public Points(int candidate_id, int pointAmount) {
		this.candidate_id = candidate_id;
		this.pointAmount = pointAmount;
	}

	public int getCandidate_id() {
		return candidate_id;
	}

	public void setCandidate_id(int candidate_id) {
		this.candidate_id = candidate_id;
	}

	public int getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(int pointAmount) {
		this.pointAmount = pointAmount;
	}
}
