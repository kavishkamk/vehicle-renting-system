package person.core;

import java.math.BigDecimal;

public class Drivers extends Persion {
	
	private String liceId;     //license number
	private String insurId;    //Insurance number
	private BigDecimal dPay;   // daily payment of driver
	
	//this is a sub class of Persion 
	public Drivers(String drId, String useName, String pwd, String fName, String lName, String nic, String lic, String insu, String mail, String addres, BigDecimal pay, boolean stat) {
		
		super(drId, useName, pwd, fName, lName, mail, nic, addres, stat );
		this.liceId  = lic;
		this.insurId = insu;
		this.dPay    = pay;
	}
	
	public String getLicId() {
		return liceId;
	}
	
	public void setLicId(String lic) {
		this.liceId = lic;
	}
	
	public String getInsId() {
		return insurId;
	}
	
	public void setInsId(String insu) {
		this.insurId = insu;
	}
	
	public BigDecimal getDpay() {
		return dPay;
	}
	
	public void setDpay(BigDecimal pay) {
		this.dPay = pay;
	}
	
	@Override
	public String toString() {
		return String
				.format("Driver [regNo=%s, userName=%s, password=%s, fName=%s, lName=%s, nic=%s, licId=%s, insurId=%s,  email=%s, address=%s,dPay=%s, statu=%s]",
						getRegNo(), getUseName(), getPwd(),getfName(), getlName(), getNic(), liceId, insurId,getMail(), getAddess(),dPay, getStatu());
	} 
	
}
