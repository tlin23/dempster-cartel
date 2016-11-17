package querySet;

import java.sql.*;
import java.util.*;
import java.util.Date;
// SQLQueries added again

public class DataQueries {

	public static Connection con;
	//pass commands 
	
	static String getAddictsStmnt = "select * from addict ORDER BY AID";
	public static List<AddictData> getAddicts() throws SQLException{;
		try(PreparedStatement st = con.prepareStatement(getAddictsStmnt)){
			ResultSet results = st.executeQuery();
			return resultsOFAddict(results);
		}
	}
	
	
//Helper Methods 
	private static List<AddictData> resultsOFAddict(ResultSet results) throws SQLException{
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
