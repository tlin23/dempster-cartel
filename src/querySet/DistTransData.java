package querySet;
import java.sql.Date;

public class DistTransData {
	public int DTID;
	public int cash;
	public int cocaine;
	public Date transDate;
	public int DID;
	public int DLID;
	public int TID;
	public int AID;
	
	@Override 
	public String toString(){
		final StringBuilder sb = new StringBuilder("querySet.DistTransData{");
		sb.append("DTID= ").append(DTID).append('\'');
		sb.append(", Cash= ").append(cash).append('\'');
		sb.append(", Cocaine= ").append(cash).append('\'');
		sb.append(", Transaction Date= ").append(transDate).append('\'');
		sb.append(", DID= ").append(DID).append('\'');
		sb.append(", DLID= ").append(DLID).append('\'');
		sb.append(", TID= ").append(TID).append('\'');
		sb.append(", AID= ").append(AID).append('\'');
		sb.append('}');
		return sb.toString();
	}

}
