package querySet;

public class DealerData {
	public int DID;
	public int cash;
	public int cocaine;
	public String name;
	public int DLID;
	public int rating;
	public String dUserName;
	
	@Override 
	public String toString(){
		final StringBuilder sb = new StringBuilder("querySet.DealerData{");
		sb.append("DID= ").append(DID).append('\'');
		sb.append(", Cash(in holding)= ").append(cash).append('\'');
		sb.append(", Cocaine (kg)= ").append(cocaine).append('\'');
		sb.append(", Name = ").append(name).append('\'');
		sb.append(", DLID= ").append(DLID).append('\'');
		sb.append(", Rating= ").append(rating).append('\'');
		sb.append(", dUsername = ").append(dUserName).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
