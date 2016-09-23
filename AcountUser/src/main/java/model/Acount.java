package model;

public class Acount {

	String acountId;
	String acountName;
	
	
	
	
	public Acount() {
		super();
	}
	public Acount(String acountId, String acountName) {
		super();
		this.acountId = acountId;
		this.acountName = acountName;
	}
	public String getAcountId() {
		return acountId;
	}
	public void setAcountId(String acountId) {
		this.acountId = acountId;
	}
	public String getAcountName() {
		return acountName;
	}
	public void setAcountName(String acountName) {
		this.acountName = acountName;
	}
	
	
}
