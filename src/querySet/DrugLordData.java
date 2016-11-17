package querySet;

public class DrugLordData {
	public int DLID;
	public int cash;
	public int cocaine;
	public String name;
	public int warWith;
	
	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder("querySet.DrugLordData{");
		sb.append("DLID= ").append(DLID).append('\'');
		sb.append(", Cash(in holding)= ").append(cash).append('\'');
		sb.append(", Cocaine(kg)= ").append(cocaine).append('\'');
		sb.append(", Name= ").append(name).append('\'');
		sb.append(", Currently in war with= ").append(warWith).append('\'');
		sb.append('}');
		return sb.toString();
	}

}
