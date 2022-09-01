package person.core;

public class StaffPosetion {
	
	private int titID;    // Job title ID
	private String Jtit;  // Job Title
	private int dptID;    //Department ID
	private String DPT;   //Department
	private double PayH;  //Staff Payment Per Hour
	
	//combined of jobtitle table and department
	public StaffPosetion(int titId, String jTitle, int dptid, double payH, String dpt) {
		
		this.titID = titId;
		this.Jtit  = jTitle;
		this.dptID = dptid;
		this.DPT   = dpt;
		this.PayH  = payH;
	}
	
	public int getSPTitId() {
		return titID;
	}
	
	public void setSPTitId(int TitId) {
		this.titID = TitId;
	}
	
	public String getSPTitle() {
		return Jtit;
	}
	
	public void setTitle(String Title) {
		this.Jtit = Title;
	}
	
	public int getSPdptID(){
		return dptID;
	}
	
	public void setSPdptID(int dId) {
		this.dptID = dId;
	}
	
	public double getSPpayH(){
		return PayH;
	}
	
	public void setSPpayH(double payh){
		this.PayH = payh;
	}
	
	public String getDPT() {
		return DPT;
	}
	
	public void setDPT(String dptN) {
		this.DPT = dptN;
	}
	
	@Override
	public String toString() {
		return String
				.format("StaffPosetion [titID=%s, Jtit=%s, dptID=%s, PayH=%s, DPT]",
						titID, Jtit, dptID, PayH ,DPT );
	}

}
