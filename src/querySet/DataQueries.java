package querySet;

import java.sql.*;
import java.util.*;
import java.util.Date;
// SQLQueries added again

import com.sun.xml.*;

public class DataQueries {

	public static Connection con;
	
	
	public static List<DealerData> getSketchyDealers() throws SQLException{ 
		String getSketchyDealersStmnt = "select distinct te.Name as TerritoryName, d.name as DealerName "
										+ "from distTrans t, dealer d, territory te "
										+ "where t.DID = d.DID and t.TID = te.TID and d.DID != ALL "
												+ "((select distinct DID from distTrans) "
												+ "minus "
												+ "(select distinct t1.DID from distTrans t1, distTrans t2 where t1.DID = t2.DID and t1.TID != t2.TID))";
		
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getSketchyDealersStmnt);
			return getSketchyDealers(rs);
		}
	}
	// Helper for getSketchyDealers
	private static List<DealerData> getSketchyDealers(ResultSet results) throws SQLException{
		List<DealerData> l = new ArrayList<>();
		while(results.next()){
			DealerData d = new DealerData();
			d.name = results.getString("TerritoryName");
			d.dUserName = results.getString("DealerName");
			l.add(d);
		}
		return l;
	}
	
	
	public static List<DrugLordData> getDruglords() throws SQLException{
		String getDruglordStmnt = "select * from druglord";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDruglords(rs);
		}
	}
	
	public static List<DrugLordData> findDruglordsByName(String name) throws SQLException {
		String searchName = name.equals("") ? "'" + name.toUpperCase() + "'" : "'%" + name.toUpperCase() + "%'";
		String getDruglordStmnt = "SELECT * FROM Druglord WHERE UPPER(name) Like " + searchName;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDruglords(rs);
		}
	}
	
	public static List<DrugLordData> findDruglordsByExactUsername(String name) throws SQLException {
		String searchName = "'" + name.toUpperCase() + "'";
		String getDruglordStmnt = "SELECT * FROM Druglord WHERE UPPER(dlusername) Like " + searchName;
		System.out.println(getDruglordStmnt);
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDruglords(rs);
		}
	}
	
	public static List<DrugLordData> findDruglordsByCashGreater(String cashGreater) throws SQLException {
		if (cashGreater.equals("") || !cashGreater.matches("^-?\\d+$")) {
			return new ArrayList<>();
		}
		String getDruglordStmnt = "SELECT * FROM Druglord WHERE Cash > " + cashGreater;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDruglords(rs);
		}
	}
	
	public static List<DrugLordData> findDruglordsByCashLesser(String cashLesser) throws SQLException {
		if (cashLesser.equals("") || !cashLesser.matches("^-?\\d+$")) {
			return new ArrayList<>();
		}
		String getDruglordStmnt = "SELECT * FROM Druglord WHERE Cash < " + cashLesser;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDruglords(rs);
		}
	}
	
	public static List<DrugLordData> findDruglordsByCocaineGreater(String cocaineGreater) throws SQLException {
		if (cocaineGreater.equals("") || !cocaineGreater.matches("^-?\\d+$")) {
			return new ArrayList<>();
		}
		String getDruglordStmnt = "SELECT * FROM Druglord WHERE Cocaine > " + cocaineGreater;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDruglords(rs);
		}
	}
	
	public static List<DrugLordData> findDruglordsByCocaineLesser(String cocaineLesser) throws SQLException {
		if (cocaineLesser.equals("") || !cocaineLesser.matches("^-?\\d+$")) {
			return new ArrayList<>();
		}
		String getDruglordStmnt = "SELECT * FROM Druglord WHERE Cocaine < " + cocaineLesser;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDruglords(rs);
		}
	}
	
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
	
	public static List<DealerData> findDealersByName(String name) throws SQLException {
		String searchName = name.equals("") ? "'" + name.toUpperCase() + "'" : "'%" + name.toUpperCase() + "%'";
		String getDruglordStmnt = "SELECT * FROM Dealer WHERE UPPER(name) Like " + searchName;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDealers(rs);
		}
	}
	
	public static List<DealerData> findDealersByCashGreater(String cashGreater) throws SQLException {
		if (cashGreater.equals("") || !cashGreater.matches("^-?\\d+$")) {
			return new ArrayList<>();
		}
		String getDruglordStmnt = "SELECT * FROM Dealer WHERE Cash > " + cashGreater;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDealers(rs);
		}
	}

	public static List<DealerData> findDealersByCashLesser(String cashLesser) throws SQLException {
		if (cashLesser.equals("") || !cashLesser.matches("^-?\\d+$")) {
			return new ArrayList<>();
		}
		String getDruglordStmnt = "SELECT * FROM Dealer WHERE Cash < " + cashLesser;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDealers(rs);
		}
	}
	
	public static List<DealerData> findDealersByCocaineGreater(String cocaineGreater) throws SQLException {
		if (cocaineGreater.equals("") || !cocaineGreater.matches("^-?\\d+$")) {
			return new ArrayList<>();
		}
		String getDruglordStmnt = "SELECT * FROM Dealer WHERE Cocaine > " + cocaineGreater;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDealers(rs);
		}
	}

	public static List<DealerData> findDealersByCocaineLesser(String cocaineLesser) throws SQLException {
		if (cocaineLesser.equals("") || !cocaineLesser.matches("^-?\\d+$")) {
			return new ArrayList<>();
		}
		String getDruglordStmnt = "SELECT * FROM Dealer WHERE Cocaine < " + cocaineLesser;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getDealers(rs);
		}
	}
	
	public static List<SupplierData> getSuppliers() throws SQLException{
		String getDruglordStmnt = "select * from supplier";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getSuppliers(rs);
		}
	}
	
	public static List<SupplierData> findSuppliersByName(String name) throws SQLException {
		String searchName = name.equals("") ? "'" + name.toUpperCase() + "'" : "'%" + name.toUpperCase() + "%'";
		String getDruglordStmnt = "SELECT * FROM Supplier WHERE UPPER(name) Like " + searchName;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getSuppliers(rs);
		}
	}
	
	public static List<SupplierData> findSuppliersByExactName(String name) throws SQLException {
		String searchName = "'" + name.toUpperCase() + "'";
		String getDruglordStmnt = "SELECT * FROM Supplier WHERE UPPER(name) Like " + searchName;
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
	
	public static List<AddictData> findAddictsByName(String name) throws SQLException {
		String searchName = name.equals("") ? "'" + name.toUpperCase() + "'" : "'%" + name.toUpperCase() + "%'";
		String getDruglordStmnt = "SELECT * FROM Addict WHERE UPPER(name) Like " + searchName;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getAddicts(rs);
		}
	}
	
	public static List<AddictData> findAddictsByCashGreater(String cashGreater) throws SQLException {
		if (cashGreater.equals("") || !cashGreater.matches("^-?\\d+$")) {
			return new ArrayList<>();
		}
		String getDruglordStmnt = "SELECT * FROM Addict WHERE Cash > " + cashGreater;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getAddicts(rs);
		}
	}
	
	public static List<AddictData> findAddictsByCashLesser(String cashLesser) throws SQLException {
		if (cashLesser.equals("") || !cashLesser.matches("^-?\\d+$")) {
			return new ArrayList<>();
		}
		String getDruglordStmnt = "SELECT * FROM Addict WHERE Cash < " + cashLesser;
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDruglordStmnt);
			return getAddicts(rs);
		}
	}

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
	
	public static List<TerritoryData> getTerritories() throws SQLException {
		String getAddictsStmnt = "select * from territory";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getAddictsStmnt);
			return getTerritories(rs);
		}
	}
	
	private static List<TerritoryData> getTerritories(ResultSet results) throws SQLException {
		List<TerritoryData> l = new ArrayList<>();
		while(results.next()){
			TerritoryData d = new TerritoryData();
			d.TID = results.getInt("TID");
			d.name = results.getString("name");
			l.add(d);
		}
		return l;
	}
	
	public static List<DistTransData> getDistTrans() throws SQLException {
		String getAddictsStmnt = "select * from disttrans";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getAddictsStmnt);
			return getDistTrans(rs);
		}
	}
	
	private static List<DistTransData> getDistTrans(ResultSet results) throws SQLException {
		List<DistTransData> l = new ArrayList<>();
		while(results.next()){
			DistTransData d = new DistTransData();
			d.DTID = results.getInt("DTID");
			d.cash = results.getInt("cash");
			d.cocaine = results.getInt("cocaine");
			d.transDate = results.getDate("transDate");
			d.DID = results.getInt("DID");
			d.DLID = results.getInt("DLID");
			d.TID = results.getInt("TID");
			d.AID = results.getInt("AID");
			l.add(d);
		}
		return l;
	}
	
	public static List<SupplyTransData> getSupplyTrans() throws SQLException {
		String getAddictsStmnt = "select * from supplytrans";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getAddictsStmnt);
			return supplyTrans(rs);
		}
	}
	
	private static List<SupplyTransData> supplyTrans(ResultSet results) throws SQLException {
		List<SupplyTransData> l = new ArrayList<>();
		while(results.next()){
			SupplyTransData d = new SupplyTransData();
			d.STID = results.getInt("STID");
			d.cash = results.getInt("cash");
			d.cocaine = results.getInt("cocaine");
			d.transDate = results.getDate("transDate");
			d.SID = results.getInt("SID");
			d.DLID = results.getInt("DLID");
			l.add(d);
		}
		return l;
	}
	

	public static List<DealerData> getTotalCashDealers(String name) throws SQLException{
		String getTotalCashDealersStmnt = "Select d.NAME, (SUM(a.Cash) + d.Cash) as TotalCash "
										  + "From DistTrans t, Addict a, Dealer d "
										  + "Where t.AID = a.AID and t.DID = d.DID and t.DID IN "
										  + "(SELECT d.DID FROM Dealer d, DrugLord l "
										  + "WHERE d.DLID = l.DLID AND UPPER(l.Name) LIKE '%"
										  + name.toUpperCase() + "%') "
										  + "GROUP BY d.NAME, d.Cash "
										  + "Order by d.Cash DESC";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getTotalCashDealersStmnt);
			return getTotalCashDealers(rs);
		}
	}
	// Helper for getTotalCashDealers
	private static List<DealerData> getTotalCashDealers(ResultSet results) throws SQLException{
		List<DealerData> l = new ArrayList<>();
		while(results.next()){
			DealerData d = new DealerData();
			d.name = results.getString("name");
			d.cash = results.getInt("TotalCash");
			l.add(d);
		}
		return l;
	}
	
	
	
	public static List<DealerData> getDealerMostCash() throws SQLException{
		String getDealerMostCashStmnt = "SELECT Name, Cash, Cocaine "
										+ "FROM Dealer "
										+ "Where Cash = (SELECT max(Cash) From Dealer)";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getDealerMostCashStmnt);
			return getDealerMostCash(rs);
		}
	}
	// Helper for getDealerMostCash
	private static List<DealerData> getDealerMostCash(ResultSet results) throws SQLException{
		List<DealerData> l = new ArrayList<>();
		while(results.next()){
			DealerData d = new DealerData();
			d.name = results.getString("name");
			d.cash = results.getInt("cash");
			d.cocaine = results.getInt("cocaine");
			l.add(d);
		}
		return l;
	}

	public static List<DealerData> getSummaryDealers() throws SQLException{
		String getSummaryDealersStmnt = "Select d.Name, SUM(t.Cash) as TotalSales, SUM(t.Cocaine) as TotalCocaine "
										 + "FROM Dealer d, DistTrans t "
										 + "WHERE d.DID = t.DID "
										 + "Group By d.Name "
										 + "Order BY TotalSales Desc, TotalCocaine Desc";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getSummaryDealersStmnt);
			return getSummaryDealers(rs);
		}
	}
	// Helper for getSummaryDealers
	private static List<DealerData> getSummaryDealers(ResultSet results) throws SQLException{
		List<DealerData> l = new ArrayList<>();
		while(results.next()){
			DealerData d = new DealerData();
			d.name = results.getString("name");
			d.cash = results.getInt("TotalSales");
			d.cocaine = results.getInt("TotalCocaine");
			l.add(d);
		}
		return l;
	}
	
	
	
	public static List<AddictData> getAddictDebt() throws SQLException{
		String getAddictDebtStmnt = "Select SUM(Cash) as TotalCashOwed "
									+ "FROM Addict";
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery(getAddictDebtStmnt);
			return getAddictDebt(rs);
		}
	}
	// Helper for getAddictDebt
	private static List<AddictData> getAddictDebt(ResultSet results) throws SQLException{
		List<AddictData> l = new ArrayList<>();
		while(results.next()){
			AddictData a = new AddictData();
			a.cash = results.getInt("TotalCashOwed");			
			l.add(a);
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

	public static boolean makeSupplyTrans(String sid, String dlid,String cashAmount, String cocaineAmount) throws SQLException {
		String makeSupplyTransStmnt = "INSERT INTO SupplyTrans(STID, Cash, Cocaine, TransDate, SID, DLID) VALUES(0," + cashAmount + "," + cocaineAmount + ", CURRENT_TIMESTAMP," + sid + "," + dlid +")";
		try(Statement st = con.createStatement()){
			st.executeQuery(makeSupplyTransStmnt);
		}
		
		String druglordGiveCocaineStmnt = "UPDATE DrugLord SET Cocaine = Cocaine + " + cocaineAmount  + " WHERE DLID = " + dlid;
		try(Statement st = con.createStatement()){
			st.executeQuery(druglordGiveCocaineStmnt);
		}
		
		String druglordTakeMoneyStmnt = "UPDATE DrugLord SET Cash = Cash - " + cashAmount  + " WHERE DLID = " + dlid;
		try(Statement st = con.createStatement()){
			st.executeQuery(druglordTakeMoneyStmnt);
		}
		
		return true;
	}

	

	

	

	
	
	
	
	
	
	
	

}
