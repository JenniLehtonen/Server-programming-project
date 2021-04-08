package data;

public class Points {
	private int candidate_id;
	private int pointAmount;
	
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
