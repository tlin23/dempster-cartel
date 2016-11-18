package querySet;

import java.sql.*;
import java.util.*;
import java.util.Date;
// SQLQueries added again

public class DataQueries {

	public static Connection con;
	
	public static List<DrugLordData> getDruglords() throws SQLException{
		String getDruglordStmnt = "select * from druglord";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDruglords(rs);
		}
	}
	// Helper for getDruglords
	private static List<DrugLordData> getDruglords(ResultSet results) throws SQLException{
		List<DrugLordData> l = new ArrayList<>();
		while(results.next()){
			DrugLordData dl = new DrugLordData();
			dl.DLID = results.getInt("DLID");
			dl.name = results.getString("name");
			dl.cash = results.getInt("cash");
			dl.cocaine = results.getInt("cocaine");
			l.add(dl);
		}
		return l;
	}
	
	public static List<DealerData> getDealers() throws SQLException{
		String getDruglordStmnt = "select * from dealer";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDealers(rs);
		}
	}
	// Helper for getDealer
	
	private static List<DealerData> getDealers(ResultSet results) throws SQLException{
		List<DealerData> l = new ArrayList<>();
		while(results.next()){
			DealerData d = new DealerData();
			d.DID = results.getInt("DID");
			d.name = results.getString("name");
			d.cash = results.getInt("cash");
			d.cocaine = results.getInt("cocaine");
			d.DLID = results.getInt("DLID");
			d.rating = results.getInt("rating");			
			l.add(d);
		}
		return l;
	}
	
	public static List<SupplierData> getSuppliers() throws SQLException{
		String getDruglordStmnt = "select * from supplier";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getSuppliers(rs);
		}
	}

	private static List<SupplierData> getSuppliers(ResultSet results) throws SQLException {
		List<SupplierData> l = new ArrayList<>();
		while(results.next()){
			SupplierData s = new SupplierData();
			s.SID = results.getInt("SID");
			s.name = results.getString("name");
			l.add(s);
		}
		return l;
	}
	
	public static List<AddictData> getAddicts() throws SQLException{
		String getAddictsStmnt = "select * from addict";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getAddictsStmnt);
			return getAddicts(rs);
		}
	}
	//Helper Methods 
	private static List<AddictData> getAddicts(ResultSet results) throws SQLException{
		List<AddictData> l = new ArrayList<>();
		while(results.next()){
			AddictData d = new AddictData();
			d.AID = results.getInt("AID");
			d.name = results.getString("name");
			d.cash = results.getInt("cash");
			l.add(d);
		}
		return l;
	}
	
	private static java.sql.Date convertToSQLDate(Date date){
		return new java.sql.Date(date.getTime());
	}
	
	private static Timestamp convertToSQLTimeSt(Date date){
		return new Timestamp(date.getTime());
	}
	
	private static String dateToStr(java.sql.Date date){
		return "to_date('" + date.getDate() + "-" + (date.getMonth() + 1) + "-" + (date.getYear() - 100) + "',' DD-MM-YY')";
	}
	
}
