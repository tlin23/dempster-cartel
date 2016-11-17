package querySet;

public class AddictData {
	public int AID;
	public int cash;
	public String name;
	
	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder("querySet. AddictData{");
		sb.append("AID= ").append(AID).append('\'');
		sb.append(", Cash(in holding)= ").append(cash).append('\'');
		sb.append(", Name= ").append(name).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
