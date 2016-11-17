package querySet;

import java.sql.Date;

public class SupplyTransData {
	public int STID;
	public int cash;
	public int cocaine;
	public Date transDate;
	public int SID;
	public int DLID;
	
	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder("querySet.SupplyTransData{");
		sb.append("STID= ").append(STID).append('\'');
		sb.append(", Cash= ").append(cash).append('\'');
		sb.append(", Cocaine(kg)= ").append(cocaine).append('\'');
		sb.append(", Transaction Date= ").append(transDate).append('\'');
		sb.append(", SID=").append(SID).append('\'');
		sb.append(", DLID=").append(DLID).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
