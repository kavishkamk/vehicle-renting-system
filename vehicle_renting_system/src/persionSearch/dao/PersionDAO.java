package persionSearch.dao;

import java.util.*;

import java.math.BigDecimal;
import java.sql.*;

import vehicle_renting_system.Database;
import person.core.*;

public class PersionDAO {
	
	private Connection conn = null;
	
	// get connection to the database
	public PersionDAO () throws Exception{
		
		try {
		//get DB connection
			conn =  Database.getDBConnection();
		}
		catch (SQLException exc) {
			System.out.println(exc.getMessage());
		}
	}
	
	// this use to permanently delete customer(typ = 1), vehicle owners(typ = 2), staff(typ = 3), drivers(typ = 4)
	public void permantlyDelUser(String regno, int typ) throws Exception{
		
		PreparedStatement stat1 = null;
		PreparedStatement stat2 = null;
		PreparedStatement stat3 = null;
		PreparedStatement stat4 = null;
		PreparedStatement stat5 = null;
		
		// delete statement direct connect table with customer table with foreign key
		String Cdeltel = "DELETE FROM customertel WHERE cus_id = ?;";
		String Cdelf   = "DELETE FROM custfinansmap WHERE cst_id = ?;";
		String Cdel    = "DELETE FROM customer WHERE cust_id = ?;";
		
		//delete statement direct connect table with staff table with foreign key
		String Sdeltel = "DELETE FROM stafftel WHERE stf_id = ?;";
		String Sdel    = "DELETE FROM staff WHERE stf_id = ?;";
		String Sdelpay = "DELETE FROM staff_payments WHERE staff_id = ?;";
		String SdelrD  = "DELETE FROM returndetails WHERE staff_id = ?;";
		String Sdelf   = "DELETE FROM staffinansmap WHERE stfId = ?;";
		
		//delete statement direct connect table with owner table with foreign key
		String Odeltel = "DELETE FROM ownertel WHERE owner_id = ?;";
		String Odel    = "DELETE FROM owners WHERE owner_id = ?;";
		String Odelpay = "DELETE FROM ownerpayment WHERE owner_id = ?;";
		String Odelveh = "DELETE FROM ownervehmap WHERE ownId = ?;";
		
		//delete statement direct connect table with driver table with foreign key
		String Ddeltel = "DELETE FROM drivertel WHERE dri_id = ?;";
		String Ddel    = "DELETE FROM driver WHERE driver_ID = ?;";
		String Ddelf   = "DELETE FROM fdriver WHERE driver_id = ?;";
		String Dpay    = "DELETE FROM driver_payments WHERE driver_id = ?;";
		
		//prepare statement = customer(typ = 1), vehicle owners(typ = 2), staff(typ = 3), drivers(typ = 4)
		try {
			if(typ == 1) { 
				stat1 = conn.prepareStatement(Cdeltel);
				stat2 = conn.prepareStatement(Cdelf);
				stat3 = conn.prepareStatement(Cdel);
			}
			else if(typ == 2) {
				stat1 = conn.prepareStatement(Odeltel);
				stat2 = conn.prepareStatement(Odelpay);
				stat3 = conn.prepareStatement(Odelveh);
				stat4 = conn.prepareStatement(Odel);
			}
			else if(typ == 3) {
				stat1 = conn.prepareStatement(Sdeltel);
				stat2 = conn.prepareStatement(Sdelpay);
				stat3 = conn.prepareStatement(SdelrD);
				stat4 = conn.prepareStatement(Sdelf);
				stat5 = conn.prepareStatement(Sdel);
			}
			else if(typ == 4) {
				stat1 = conn.prepareStatement(Ddeltel);
				stat2 = conn.prepareStatement(Ddelf);
				stat3 = conn.prepareStatement(Dpay);
				stat4 = conn.prepareStatement(Ddel);
			}
			
			// value assign to statements
			stat1.setString(1, regno);
			stat2.setString(1, regno);
			stat3.setString(1, regno);
			
			if(typ != 1) {
				stat4.setString(1, regno);
			}
			if(typ == 3) {
				stat5.setString(1, regno);
			}
			
			//execute statement
			stat1.executeUpdate();
			stat2.executeUpdate();
			stat3.executeUpdate();
			
			if(typ != 1) {
				stat4.executeUpdate();
			}
			if(typ == 3) {
				stat5.executeUpdate();
			}
		}
		// close  statements
		finally {
			close(stat1);
			close(stat2);
			close(stat3);
			close(stat4);
			close(stat5);
		}
	}
	
	//this used to update customer , owner tables = customer(typ = 1), vehicle owners(typ = 2)
	public void updatePerson(Persion thePersion, int Typ) throws SQLException{
		
		PreparedStatement stat = null;
		
		//update Statement
		String cSql = "UPDATE customer "
                		+ "SET username = ?, pwd = ?, firstName = ?, lastName = ?, email = ?, nicNo = ?, Address = ?, statu = ? "
				        + "WHERE cust_id = ?;";
		
		String OSql = "UPDATE owners "
        				+ "SET username = ?, pwd = ?, firstName = ?, lastName = ?, email = ?, nicNo = ?, address = ?, statu = ? "
        				+ "WHERE owner_id = ?;";
		
		// prepare update Statement
		try {
			if(Typ == 1) {
				stat = conn.prepareStatement(cSql);
			}
			else if(Typ == 2) {
				stat = conn.prepareStatement(OSql);
			}
			
			// assign values to statement
			stat.setString(1,thePersion.getUseName());
			stat.setString(2,thePersion.getPwd());
			stat.setString(3,thePersion.getfName());
			stat.setString(4,thePersion.getlName());
			stat.setString(5,thePersion.getMail());
			stat.setString(6,thePersion.getNic());
			stat.setString(7,thePersion.getAddess());
			stat.setBoolean(8,thePersion.getStatu());
			stat.setString(9, thePersion.getRegNo());
			
			//execute query
			stat.executeUpdate();
		}
		finally {
			close(stat); // close connection
		}
	}
	
	// update driver details
	public void updateDriver(Drivers theDriver) throws SQLException{
		
		PreparedStatement stat = null;
		
		//driver table update Statement
		String DSql = "UPDATE driver "
				+ "SET username = ?, pwd = ?, firstName = ?, lastName = ?, nicNo = ?, licenseID = ?, driver_InsuranceID = ?, "
				+ "email = ?, address = ?, DpayPerDay = ?, statu = ?"
				+ " WHERE driver_ID = ?";
				
		try {
			//prepare statement
			stat = conn.prepareStatement(DSql);
			
			//assign values to statement
			stat.setString(1, theDriver.getUseName());
			stat.setString(2, theDriver.getPwd());
			stat.setString(3, theDriver.getfName());
			stat.setString(4, theDriver.getlName());
			stat.setString(5, theDriver.getNic());
			stat.setString(6, theDriver.getLicId());
			stat.setString(7, theDriver.getInsId());
			stat.setString(8, theDriver.getMail());
			stat.setString(9, theDriver.getAddess());
			stat.setBigDecimal(10, theDriver.getDpay());
			stat.setBoolean(11, theDriver.getStatu());
			stat.setString(12, theDriver.getRegNo());
			
			//execute query
			stat.executeUpdate();
		}
		finally{
			close(stat); // close connection
		}
	}
	
	//update staff table details
	public void updateStaff(Staff theStaff) throws Exception{
		
		PreparedStatement stat = null;
		
		// staff table update query
		String sUpdate = "UPDATE staff SET "
					+ "titleID = ?, username = ?, pwd = ?, firstName = ?, lastName = ?, email = ?, nicNo = ?, Address = ?, statu = ? "
					+ "WHERE stf_id = ?;";
		try{
			//create statement
			stat = conn.prepareStatement(sUpdate);
			
			// value assign to statement
			stat.setInt(1,theStaff.getTitleID());
			stat.setString(2, theStaff.getUseName());
			stat.setString(3, theStaff.getPwd());
			stat.setString(4, theStaff.getfName());
			stat.setString(5, theStaff.getlName());
			stat.setString(6, theStaff.getMail());
			stat.setString(7, theStaff.getNic());
			stat.setString(8, theStaff.getAddess());
			stat.setBoolean(9,  theStaff.getStatu());
			stat.setString(10, theStaff.getRegNo());
			
			//execute Query
			stat.executeUpdate();
		
		}
		finally {
			close(stat);
		}
	}
	
	// update contact numbers of customer(typ = 1), vehicle owners(typ = 2), staff(typ = 3), drivers(typ = 4) 
	public void updateContact(ContactNo theTel,ContactNo newTel, int typ) throws Exception{
		
		PreparedStatement statT = null;
		
		// contact number table update query
		String updateCusTel = "UPDATE customertel SET tel = ? WHERE cus_id = ? AND tel = ?";
		String updateOwnTel = "UPDATE ownertel SET tel = ? WHERE owner_id = ? AND tel = ?";
		String updateStfTel = "UPDATE stafftel SET tel = ? WHERE stf_id = ? AND tel = ?";
		String updateDriTel = "UPDATE drivertel SET tel = ? WHERE dri_id = ? AND tel = ?";
		
		// create statements
		try {
			if(typ == 1) {
				statT = conn.prepareStatement(updateCusTel);
			}
			else if(typ == 2) {
				statT = conn.prepareStatement(updateOwnTel);
			}
			else if(typ == 3) {
				statT = conn.prepareStatement(updateStfTel);
			}
			else if(typ == 4){
				statT = conn.prepareStatement(updateDriTel);
			}
			
			//assign values to statements
			statT.setString(1,newTel.getTel());
			statT.setString(2,newTel.getReg());
			statT.setString(3, theTel.getTel());
			
			// execute query
			statT.executeUpdate();
		}
		finally {
			close(statT);
		}
	}
	
	// add  customers and owner tables (typ = 1), vehicle owners(typ = 2)
	public void addPersion(Persion thePersion, int typ) throws Exception{
		
		PreparedStatement statI = null;
		
		// insert query
		String addCus = "INSERT INTO customer(cust_id, username, pwd, firstName, lastName, email, nicNo, Address, statu) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		String addOwn = "INSERT INTO owners(owner_id, username, pwd, firstName, lastName, email, nicNo, address, statu )"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		//prepare statements
		try {
			if(typ == 1) {
				statI = conn.prepareStatement(addCus);
			}
			else if(typ == 2) {
				statI = conn.prepareStatement(addOwn);
			}
			
			// assign values to statements 
			statI.setString(1,thePersion.getRegNo());
			statI.setString(2, thePersion.getUseName());
			statI.setString(3, thePersion.getPwd());
			statI.setString(4, thePersion.getfName());
			statI.setString(5, thePersion.getlName());
			statI.setString(6, thePersion.getMail());
			statI.setString(7, thePersion.getNic());
			statI.setString(8, thePersion.getAddess());
			statI.setBoolean(9, thePersion.getStatu());
			
			//execute query
			statI.executeUpdate();
			
		}
		finally {
			close(statI); // close statement
		}
	}
	
	// insert data to driver table
	public void insertDriver(Drivers theDriver) throws Exception {
		
		PreparedStatement statI = null;
		
		String addStf = "INSERT INTO driver(driver_ID, username, pwd, firstName, lastName, nicNo, licenseID, driver_InsuranceID, email, address, DpayPerDay, statu) " 
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			
			statI = conn.prepareStatement(addStf);
			
			statI.setString(1,theDriver.getRegNo());
			statI.setString(2,theDriver.getUseName());
			statI.setString(3,theDriver.getPwd());
			statI.setString(4,theDriver.getfName());
			statI.setString(5,theDriver.getlName());
			statI.setString(6,theDriver.getNic());
			statI.setString(7,theDriver.getLicId());
			statI.setString(8,theDriver.getInsId());
			statI.setString(9,theDriver.getMail());
			statI.setString(10,theDriver.getAddess());
			statI.setBigDecimal(11,theDriver.getDpay());
			statI.setBoolean(12,theDriver.getStatu());
			
			statI.executeUpdate();
			
		}
		finally {
			close(statI);
		}
	}
	
	//insert data to staff table customer(typ = 1), vehicle owners(typ = 2), staff(typ = 3), drivers(typ = 4) 
	public void addStaff(Staff theStaff) throws Exception {
		
		PreparedStatement sAdd = null;
		
		//Insert query
		String addStf = "INSERT INTO staff (stf_id, titleID, username, pwd, firstName, lastName, email, nicNo, Address, statu) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try{
			//create statement
			sAdd = conn.prepareStatement(addStf);
			
			//assign values
			sAdd.setString(1, theStaff.getRegNo());
			sAdd.setInt(2, theStaff.getTitleID());
			sAdd.setString(3, theStaff.getUseName());
			sAdd.setString(4, theStaff.getPwd());
			sAdd.setString(5, theStaff.getfName());
			sAdd.setString(6, theStaff.getlName());
			sAdd.setString(7, theStaff.getMail());
			sAdd.setString(8, theStaff.getNic());
			sAdd.setString(9, theStaff.getAddess());
			sAdd.setBoolean(10,theStaff.getStatu());
			
		//	// execute Query
			sAdd.executeUpdate();
			
		}
		finally {
			close(sAdd);  // close connection
		}
	}
	
	// insert contact numbers customer(typ = 1), vehicle owners(typ = 2), staff(typ = 3), drivers(typ = 4) 
	public void insertTel(ContactNo theTel, int typ) throws Exception{
		
		PreparedStatement statT = null;
		
		String addCusTel = "INSERT INTO customertel(cus_id, tel) VALUES (?, ?);";
		String addownTel = "INSERT INTO ownertel(owner_id, tel) VALUES (?, ?);";
		String addStfTel = "INSERT INTO stafftel(stf_id, tel) VALUES (?, ?);";
		String addDriTel = "INSERT INTO drivertel(dri_id, tel) VALUES (?, ?);";
		
		try {
			if(typ == 1) {
				statT = conn.prepareStatement(addCusTel);
			}
			else if(typ == 2) {
				statT = conn.prepareStatement(addownTel);
			}
			else if(typ == 3) {
				statT = conn.prepareStatement(addStfTel);
			}
			else if(typ == 4) {
				statT = conn.prepareStatement(addDriTel);
			}
			else {
				System.out.println("persionSearch.dao.PersionDAO.java : Use defined values");
			}
			
			// assign values
			statT.setString(1,theTel.getReg());
			statT.setString(2,theTel.getTel());
			
			statT.executeUpdate();
		}
		finally {
			close(statT);
		}
	}
	
	////This method use to get list of persion with statu = customer (typ = 1), vehicle owners(typ = 2)
	// active = 1  // diactive = 0
	public List<Persion> getListOfPersion(int xx, int active) throws Exception {
		
		List<Persion> perList = new ArrayList<>();
		
		PreparedStatement st1 = null;
		ResultSet ReSlt = null;
		
		String cusSelect = "SELECT * FROM customer WHERE statu = ?;";
		String ownSelect = "SELECT * FROM owners WHERE statu = ?;";
		
		//check table
		try {
			
			if     (xx == 1) {
				st1 = conn.prepareStatement(cusSelect);
			}
			else if(xx == 2) {
				st1 = conn.prepareStatement(ownSelect);
			}
			else {
				System.out.println("Requst type Wrong");
			}
			
			// check active or not
			//deactivate = statu = 0 // //active  = statu = 1
			if(active == 1) {
				st1.setBoolean(1,true);
			}
			else if (active == 0) {
				st1.setBoolean(1,false);
			}
			else {
				System.out.println("Requst type, should be boolean");
			}
			
			//execute query
			ReSlt = st1.executeQuery();
			
			while (ReSlt.next()) {
				Persion tempPersion = convertRowToPersion(ReSlt, xx);
				perList.add(tempPersion);
			}
			
			return perList;
		}
		finally {
			
			close(st1,ReSlt);
		}
	}
	
	// get all staff details   active(1), deacive(0)
	public List<Staff> getListOfStaff(int active) throws Exception{
		
		List<Staff> stfList = new ArrayList<>();
		
		PreparedStatement st1 = null;
		ResultSet ReSlt = null;
		
		String stfSelect = "SELECT S.*, J.jTitle, J.dpt_id, D.dpt, J.payPerH FROM staff S "
								+ "LEFT JOIN jobtitle J ON S.titleID = J.titleID LEFT JOIN department D "
								+ "ON J.dpt_id = D.dpt_id WHERE S.statu = ?;";
		
		try {
			st1 = conn.prepareStatement(stfSelect);
			
			if(active == 1) {
				st1.setBoolean(1,true);
			}
			else if (active == 0) {
				st1.setBoolean(1,false);
			}
			else {
				System.out.println("Requst type, should be boolean");
			}
			
			//execute query
			ReSlt = st1.executeQuery();
			
			while(ReSlt.next()) {
				Staff tempStaff = convertRowToStaff(ReSlt);
				stfList.add(tempStaff);
			}
	
			return stfList;
		}
		
		finally {
			close(st1,ReSlt);
		}
	}
	
	//get all driver details  active(1), deacive(0)
	public List<Drivers> getListOfDriver(int active) throws Exception{
		
		List<Drivers> dirList = new ArrayList<>();
		
		PreparedStatement st1 = null;
		ResultSet ReSlt = null;
		
		String driSelect = "SELECT * FROM driver WHERE statu = ?;";
		
		try {
			st1 = conn.prepareStatement(driSelect);
			
			if(active == 1) {
				st1.setBoolean(1,true);
			}
			//active
			else if (active == 0) {
				st1.setBoolean(1,false);
			}
			else {
				System.out.println("Requst type, should be boolean");
			}

			//execute query
			ReSlt = st1.executeQuery();

			while (ReSlt.next()) {
				Drivers tempDriver = convertRowToDriver(ReSlt);
				dirList.add(tempDriver);
			}
			
			return dirList;
		}
		finally {
			close(st1,ReSlt);
		}
	}
	
	//this method search with given name customer(typ = 1), vehicle owners(typ = 2)
	//(split it words and search each word with first name and last name column)
	//active(1), deacive(0)
	public List<Persion> searchPWithName(String name, int typ, int active) throws Exception{
		
		List<Persion> perList = new ArrayList<>();
		
		String[] splitName = name.split("\\s+");
		
		int numEleNam = splitName.length;
		
		PreparedStatement st1 = null;
		PreparedStatement st2 = null;
		ResultSet ReSlt1 = null;
		ResultSet ReSlt2 = null;
			
		String cusFSql = "SELECT * FROM customer WHERE (firstName LIKE ? AND statu = ?);";
		String cusLSql = "SELECT * FROM customer WHERE (lastName LIKE ? AND statu = ?);";
		String ownFSql = "SELECT * FROM owners WHERE (firstName LIKE ? AND statu = ?);";
		String ownLSql = "SELECT * FROM owners WHERE (lastName LIKE ? AND statu = ?);";
			
		try {
			
			// get name and split words and set as array
			for(int i = 0; i < numEleNam; i++) {
				
				String pName = splitName[i];
				
				pName += '%';
				
				if(typ == 1){
					st1 = conn.prepareStatement(cusFSql);
					st2 = conn.prepareStatement(cusLSql);
				}
				else if(typ == 2) {
					st1 = conn.prepareStatement(ownFSql);
					st2 = conn.prepareStatement(ownLSql);
				}
				
				if(active == 1) {
					st1.setString(1, pName);
					st1.setBoolean(2, true);
					st2.setString(1, pName);
					st2.setBoolean(2, true);
				}
				else if(active == 0) {
					st1.setString(1, pName);
					st1.setBoolean(2, false);
					st2.setString(1, pName);
					st2.setBoolean(2, false);
				}
				else {
					System.out.println("Requst type, should be boolean");
				}
				
				ReSlt1 = st1.executeQuery();
				ReSlt2 = st2.executeQuery();
				
				while(ReSlt1.next()) {
					Persion tempPersion =  convertRowToPersion(ReSlt1, typ);
					perList.add(tempPersion);
				}
				
				while(ReSlt2.next()) {
					Persion tempPersion =  convertRowToPersion(ReSlt2, typ);
					perList.add(tempPersion);
				}
			}
				
			return perList;
		}
		finally {
			close(st1, ReSlt1);
			close(st2, ReSlt2);
		}
	}
	
	//  search with registration number customer(typ = 1), vehicle owners(typ = 2)
	//active(1), deacive(0)
	public List<Persion> searchPWithID(String ID, int typ, int active) throws Exception{
		
		List<Persion> perList = new ArrayList<>();
		
		PreparedStatement st1 = null;
		ResultSet ReSlt1 = null;
			
		String cusregSql = "SELECT * FROM customer WHERE (cust_id = ? AND statu = ?);";
		String owregSql = "SELECT * FROM owners WHERE (owner_id = ? AND statu = ?);";
			
		try {
				
			if(typ == 1){
				st1 = conn.prepareStatement(cusregSql);
			}
			else if(typ == 2) {
				st1 = conn.prepareStatement(owregSql);
			}
				
			if(active == 1) {
					st1.setString(1, ID);
					st1.setBoolean(2, true);
				}
			else if(active == 0) {
				st1.setString(1, ID);
				st1.setBoolean(2, false);
			}
			else {
				System.out.println("Requst type, should be boolean");
			}
				
			ReSlt1 = st1.executeQuery();
				
			while(ReSlt1.next()) {
				Persion tempPersion =  convertRowToPersion(ReSlt1, typ);
				perList.add(tempPersion);
			}
				
			return perList;
		}
		finally {
			close(st1, ReSlt1);
		}
	}
	
	//this method search staff table with given name(split it words and search each word with first name and last name column)
	//active(1), deacive(0)
	public List<Staff> searchStfWithName(String name,int active) throws Exception{
		
		List<Staff> stfList = new ArrayList<>();
		
		String[] splitName = name.split("\\s+");
		
		int numEleNam = splitName.length;
		
		PreparedStatement st1 = null;
		ResultSet ReSlt1 = null;
		
		// this  query get staff with relational data of department and jobtitle tables
		String stfFSql = "SELECT S.*, J.jTitle, J.dpt_id, D.dpt, J.payPerH FROM staff S "
				+ "LEFT JOIN jobtitle J ON S.titleID = J.titleID "
				+ "LEFT JOIN  department D ON J.dpt_id = D.dpt_id "
				+ "WHERE ((S.firstName LIKE ? AND S.statu = ?) OR (S.lastName LIKE ? AND S.statu = ?));";
		
		try {
			// get string name and split to word and store in array
			for(int i = 0; i < numEleNam; i++) {
				
				String sName = splitName[i];
				
				sName += '%';
			
				st1 = conn.prepareStatement(stfFSql);
				
				if(active == 1) {
					st1.setString(1, sName);
					st1.setBoolean(2, true);
					st1.setString(3, sName);
					st1.setBoolean(4, true);
				}
				else if(active == 0) {
					st1.setString(1, sName);
					st1.setBoolean(2, false);
					st1.setString(3, sName);
					st1.setBoolean(4, false);
				}
			
				ReSlt1 = st1.executeQuery();
			
				while(ReSlt1.next()) {
					Staff tempStaff =  convertRowToStaff(ReSlt1);
					stfList.add(tempStaff);
				}
			}
			
			return stfList;
		}
		finally {
			close(st1, ReSlt1);
		}
	}
	
	//  search staff table with registration number 
	//active(1), deacive(0)
	public List<Staff> searchStfWithID(String ID,int active) throws Exception{
		
		List<Staff> stfList = new ArrayList<>();
		
		PreparedStatement st1 = null;
		ResultSet ReSlt1 = null;
		
		String stfIDSql = "SELECT S.*, J.jTitle, J.dpt_id, D.dpt, J.payPerH FROM staff S "
				+ "LEFT JOIN jobtitle J ON S.titleID = J.titleID "
				+ "LEFT JOIN  department D ON J.dpt_id = D.dpt_id WHERE S.stf_id = ? AND S.statu = ?";
		
		try {
			
			st1 = conn.prepareStatement(stfIDSql);
				
			if(active == 1) {
				st1.setString(1, ID);
				st1.setBoolean(2, true);
				}
			else if(active == 0) {
				st1.setString(1, ID);
				st1.setBoolean(2, false);
			}
			
			ReSlt1 = st1.executeQuery();
			
			while(ReSlt1.next()) {
				Staff tempStaff =  convertRowToStaff(ReSlt1);
				stfList.add(tempStaff);
			}
			
			return stfList;
		}
		finally {
			close(st1, ReSlt1);
		}
	}
	
	//this method search with given name (split it words and search each word with first name and last name column)
	//active(1), deacive(0)
	public List<Drivers> searchDriWithName(String name,int active) throws Exception{
		
		List<Drivers> dirList = new ArrayList<>();
		
		String[] splitName = name.split("\\s+");
		
		int numEleNam = splitName.length;
		
		PreparedStatement st1 = null;
		PreparedStatement st2 = null;
		ResultSet ReSlt1 = null;
		ResultSet ReSlt2 = null;
		
		String driFSql = "SELECT * FROM driver WHERE (firstName LIKE ? AND statu = ?);";
		String driLSql = "SELECT * FROM driver WHERE (lastName LIKE ? AND statu = ?);";
		
		try {
			
			for(int i = 0; i < numEleNam; i++) {
				
				String sName = splitName[i];
				
				sName += '%';
			
				st1 = conn.prepareStatement(driFSql);
				st2 = conn.prepareStatement(driLSql);
				
				if(active == 1) {
					st1.setString(1, sName);
					st1.setBoolean(2, true);
					st2.setString(1, sName);
					st2.setBoolean(2, true);
				}
				else if(active == 0) {
					st1.setString(1, sName);
					st1.setBoolean(2, false);
					st2.setString(1, sName);
					st2.setBoolean(2, false);
				}
			
				ReSlt1 = st1.executeQuery();
				ReSlt2 = st2.executeQuery();
			
				while(ReSlt1.next()) {
					Drivers tempDriver =  convertRowToDriver(ReSlt1);
					dirList.add(tempDriver);
				}
			
				while(ReSlt2.next()) {
					Drivers tempDriver =  convertRowToDriver(ReSlt2);
					dirList.add(tempDriver);
				}
			}
			
			return dirList;
		}
		finally {
			close(st1, ReSlt1);
			close(st2, ReSlt2);
		}
	}
	
	// search drivers with registration number 
	//active(1), deacive(0)
	public List<Drivers> searchDriWithID(String ID,int active) throws Exception{
		
		List<Drivers> dirList = new ArrayList<>();
		
		PreparedStatement st1 = null;
		ResultSet ReSlt1 = null;
		
		String driIDSql = "SELECT * FROM driver WHERE (driver_ID = ? AND statu = ?)";
		
		try {
			
			st1 = conn.prepareStatement(driIDSql);
				
			if(active == 1) {
				st1.setString(1, ID);
				st1.setBoolean(2, true);
			}
			else if(active == 0) {
				st1.setString(1, ID);
				st1.setBoolean(2, false);
			}
			
			
			ReSlt1 = st1.executeQuery();
			
			while(ReSlt1.next()) {
				Drivers tempDriver =  convertRowToDriver(ReSlt1);
				dirList.add(tempDriver);
			}
			
			return dirList;
		}
		finally {
			close(st1, ReSlt1);
		}
	}

	// this use to get contactNo list with given registration number and type
	//customer(typ = 1), vehicle owners(typ = 2), staff(typ = 3), drivers(typ = 4)
	public List<ContactNo> getTelNumbers(String regNo, int typ) throws Exception{
		
		List<ContactNo> contactNo = new ArrayList<>();
		
		PreparedStatement statsql = null;
		ResultSet resSet = null;
		
		String cusConNo = "SELECT * FROM customertel WHERE cus_id = ?;";
		String ownConNo = "SELECT * FROM ownertel WHERE owner_id = ?;";
		String stfConNo = "SELECT * FROM stafftel WHERE stf_id = ?;";
		String dirConNo = "SELECT * FROM drivertel WHERE dri_id = ?;";
		
		try {
			if(typ == 1) {
				statsql = conn.prepareStatement(cusConNo);
			}
			else if(typ == 2) {
				statsql = conn.prepareStatement(ownConNo);
			}
			else if(typ == 3) {
				statsql = conn.prepareStatement(stfConNo);
			}
			else if(typ == 4) {
				statsql = conn.prepareStatement(dirConNo);
			}
			
			statsql.setString(1,regNo);
			
			resSet = statsql.executeQuery();
			
			while(resSet.next()) {
				ContactNo temTel =  convertRowTotel(resSet, typ);
				contactNo.add(temTel);
			}
		}
		finally {
			close(statsql, resSet);
		}
		
		return contactNo;	
	}
	
	// this use to get list of positions using jobtitle and department tables
	public List<StaffPosetion> listofposetion() throws Exception{
		
		List<StaffPosetion> posetion = new ArrayList<>();
		
		PreparedStatement stat= null;
		ResultSet res = null;
		
		// get data in jobtitle and department tables using left join
		String sqlQ = "SELECT J.titleID, J.jTitle, J.dpt_id, J.payPerH, D.dpt "
				+ "FROM jobtitle J LEFT JOIN department D ON J.dpt_id = D.dpt_id;";
		
		try {
			
			stat = conn.prepareStatement(sqlQ);
			
			res = stat.executeQuery();
			
			while(res.next()) {
				StaffPosetion tempP = convertPosetonToString(res);
				posetion.add(tempP);
			}
		}
		finally {
			
			close(stat, res);
		}
		
		return posetion;	
	}
	
	// ContactNo ResultSet translate to String
	//customer(typ = 1), vehicle owners(typ = 2), staff(typ = 3), drivers(typ = 4)
	public ContactNo convertRowTotel(ResultSet res, int typ) throws SQLException {
		
		String reg = null;
		
		if(typ == 1) {
			reg = res.getString("cus_id");
		}
		else if(typ == 2){
			reg = res.getString("owner_id");
		}
		else if(typ == 3){
			reg = res.getString("stf_id");
		}
		else if(typ == 4){
			reg = res.getString("dri_id");
		}
		
		String tel = res.getString("tel");
		
		ContactNo newCon = new ContactNo(reg, tel);
		
		return newCon;
	
	}
	
	// Persion ResultSet translate to String  //customer(typ = 1), vehicle owners(typ = 2)
	public Persion convertRowToPersion(ResultSet res, int typ) throws SQLException {
		
		String reg = null;
		String addres = null;
		// 1=customer    2= owner
		if(typ == 1) {
			reg    = res.getString("cust_id");
			addres = res.getString("Address");
		}
		else if(typ == 2) {
			reg = res.getString("owner_id");
			addres = res.getString("address");
		}
		
		String useName = res.getString("userName");
		String pwd     = res.getString("pwd");
		String fName   = res.getString("firstName");
		String lName   = res.getString("lastName");
		String mail    = res.getString("email");
		String nic     = res.getString("nicNo");
		boolean statu  = res.getBoolean("statu");
		
		Persion tempPersion = new Persion(reg, useName, pwd, fName, lName, mail, nic, addres, statu);
		
		return tempPersion;
		
	}
	
	// Drivers ResultSet translate to String
	public Drivers convertRowToDriver(ResultSet res) throws SQLException{
		
		String reg     = res.getString("driver_ID");
		String useName = res.getString("username");
		String pwd     = res.getString("pwd");
		String fName   = res.getString("firstName");
		String lName   = res.getString("lastName");
		String nic     = res.getString("nicNo");
		String licId   = res.getString("licenseID");
		String insuId  = res.getString("driver_InsuranceID");
		String mail    = res.getString("email");
		String addres  = res.getString("address");
		BigDecimal dPay= res.getBigDecimal("DpayPerDay");
		boolean statu  = res.getBoolean("statu");
		
		Drivers tempDriver = new Drivers(reg, useName, pwd, fName, lName, nic, licId, insuId, mail, addres ,dPay, statu);
		return tempDriver;
		
	}
	
	// Staff ResultSet translate to String
	public Staff convertRowToStaff(ResultSet res) throws SQLException {
		
		String reg     = res.getString("stf_id");
		int titleID    = res.getInt("titleID");
		String useName = res.getString("username");
		String pwd     = res.getString("pwd");
		String fName   = res.getString("firstName");
		String lName   = res.getString("lastName");
		String mail    = res.getString("email");
		String nic     = res.getString("nicNo");
		String addres  = res.getString("Address");
		boolean statu  = res.getBoolean("statu");
		String jTitle  = res.getString("Jtitle");
		int dptId      = res.getInt("dpt_id");
		String dpt     = res.getString("dpt");
		double pay     = res.getDouble("payPerH");
		
		Staff tempStaff = new Staff(reg,titleID, useName, pwd, fName, lName, mail, nic, addres, statu, jTitle, dptId, dpt, pay);
		
		return tempStaff;
		
	}
	
	// position ResultSet translate to String
	public StaffPosetion convertPosetonToString(ResultSet res) throws SQLException{
		
		int titId  = res.getInt("J.titleID");
		String tit = res.getString("J.jTitle");
		int dptId  = res.getInt("J.dpt_id");
		String dpt = res.getString("D.dpt");
		double pay = res.getDouble("J.payPerH");
		
		StaffPosetion tempPosetion = new StaffPosetion(titId, tit, dptId, pay, dpt);
		
		return tempPosetion;
	}
	
	// close connection, statement and ResultSet
	private static void close(Connection conn, Statement stat, ResultSet res) throws SQLException {
		
		if (res != null) {
			res.close();
		}
		
		if (stat != null) {
			stat.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
	
	// close() method assess with statement and ResultSet
	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}
	
	// close() method assess with statement
	private void close(Statement mystat) throws SQLException{
		close(null, mystat, null);
	}

}
