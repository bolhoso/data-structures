package bolha.data_structures;
public class Data {
	private String val;
	
	public Data (String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	
	public String toString () {
		return this.val;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Data && ((Data)obj).getVal().equals(this.getVal());
	}
}
