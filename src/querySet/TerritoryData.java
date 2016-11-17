package querySet;

public class TerritoryData {
	public int TID;
	public String name;
	
	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder("querySet.TerritoryData{");
		sb.append("TID = ").append(TID).append('\'');
		sb.append(", Name= ").append('\'');
		sb.append('}');
		return sb.toString();
	}
}
