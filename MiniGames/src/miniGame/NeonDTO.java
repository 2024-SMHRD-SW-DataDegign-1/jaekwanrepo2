package miniGame;

public class NeonDTO {

	private String q;
	private String a;
	private String hint1;
	private String hint2;
	public NeonDTO(String q, String a, String hint1, String hint2) {
	
		this.q = q;
		this.a = a;
		this.hint1 = hint1;
		this.hint2 = hint2;
	
	}
	public String getQ() {
		return q;
	}
	public String getA() {
		return a;
	}
	public String getHint1() {
		return hint1;
	}
	public String getHint2() {
		return hint2;
	}


}	
