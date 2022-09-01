package person.core;

public class Persion {
	
	private String regNo;     //Registration number
	private String userName;  //User Name
	private String password;  //Password
	private String fName;     //First Name
	private String lName;     //Last Name
	private String email;     //email
	private String nic;       //NIC
	private String address;   //Address
	private boolean statu;    //Active / Deactivate(1,0)
	
	public Persion(String pregNo, String puseName, String pwd, String pfName, String plName, String pemail, String pnic, String add, boolean statu) {
		
		this.regNo    = pregNo;
		this.userName = puseName;
		this.password = pwd;
		this.fName    = pfName;
		this.lName    = plName;
		this.email    = pemail;
		this.nic      = pnic;
		this.address  = add;
		this.statu    = statu;
	}
	
	public String getRegNo() {
		return regNo;
	}
	
	public void setRegNo(String sRegNo) {
		this.regNo = sRegNo;
	}
	
	public String getUseName() {
		return userName;
	}
	
	public void setUseName(String sUname) {
		this.userName = sUname;
	}
	
	public String getPwd() {
		return password;
	}
	
	public void setPwd(String spwd) {
		this.password = spwd;
	}
	
	public String getfName() {
		return fName;
	}
	
	public void setfName(String sFname) {
		this.fName = sFname;
	}
	
	public String getlName() {
		return lName;
	}
	
	public void setlName(String slname) {
		this.lName = slname;
	}
	
	public String getMail() {
		return  email;
	}
	
	public void setMail(String smail) {
		this.email = smail;
	}
	
	public String getNic() {
		return nic;
	}
	
	public void setNic(String sNic) {
		this.nic = sNic;
	}
	
	public String getAddess() {
		return address;
	}
	
	public void setAddress(String sAdd) {
		this.address = sAdd;
	}
	
	public boolean getStatu(){
		return statu;
	}
	
	public void setStatu(boolean sStatu) {
		this.statu = sStatu;
	}
	
	@Override
	public String toString() {
		return String
				.format("Persion [regNo=%s, userName=%s, password=%s, fName=%s, lName=%s, email=%s, nic=%s, address=%s, statu=%s]",
						regNo, userName, password,fName, lName, email, nic, address, statu);
	}
}
