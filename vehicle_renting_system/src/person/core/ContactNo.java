package person.core;

public class ContactNo {
	
	private String regNo; //Registration number
	private String tel;   // telephone number
	
	// this constructor use to get contact number object
	public ContactNo(String pReg, String pTel) {
		
		this.regNo = pReg;
		this.tel = pTel;
	}
	
	public String getReg() {
		return regNo;
	}
	
	public void setReg(String Reg) {
		this.regNo = Reg;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String Tel) {
		this.tel = Tel;
	}
	
	@Override
	public String toString() {
		return String
				.format("ContactNo [regNo=%s, tel=%s]",
						regNo, tel);
	}
	
}
