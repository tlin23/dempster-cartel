package querySet;

public class SupplierData {
	public int SID;
	public String name;
	
	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder("querySet.Airplane{");
		sb.append("SID= ").append(SID).append('\'');
		sb.append(", Name=").append(name).append('\'');
		return sb.toString();
	}
}
