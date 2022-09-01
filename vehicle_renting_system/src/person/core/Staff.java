package person.core;

public class Staff extends Persion{
	
	private int titID;    //Job title ID
	private String Jtit;  //Job title
	private int dptID;    // Department ID
	private String DPT;   //Department
	private double PayH;  //Staff payment per Hour
	
	//sub class of Persion class
	public Staff(String pregNo, int titId, String puseName, String pwd, String pfName, String plName, String pemail,
					String pnic, String add, boolean statu, String jTitle, int dptid, String dpt, double payH ) {
		
		super(pregNo, puseName, pwd, pfName, plName, pemail, pnic, add, statu);
		this.titID = titId;
		this.Jtit  = jTitle;
		this.dptID = dptid;
		this.DPT   = dpt;
		this.PayH  = payH;
	}
	
	public int getTitleID() {
		return titID;
	}
	
	public void setTitleId(int titId) {
		this.titID = titId;
	}
	
	public String getTitle() {
		return Jtit;
	}
	
	public void SetTitle(String titl) {
		this.Jtit = titl;
	}
	
	public int getDptID() {
		return dptID;
	}
	
	public void setDptID(int DptId) {
		this.dptID = DptId;
	}
	
	public String getDPT() {
		return DPT;
	}
	
	public void setDPT(String dptn) {
		this.DPT = dptn;
	}
	
	public double getPayPH() {
		return PayH;
	}
	
	public void setPayPH(double payh) {
		this.PayH = payh;
	}

	@Override
	public String toString() {
		return String
				.format("Staff [regNo=%s, titID=%s, userName=%s, password=%s, fName=%s, lName=%s, email=%s, nic=%s, address=%s, statu=%s, Jtit=%s, dptID=%s, DPT=%s, PayH=%s]",
						getRegNo(), titID, getUseName(), getPwd(),getfName(), getlName(), getNic(), getMail(), getNic(), getAddess(), getStatu(), Jtit, dptID, DPT, PayH);
	}

}
