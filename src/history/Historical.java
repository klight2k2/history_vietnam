package history;

import java.util.ArrayList;

public abstract class Historical {
	protected String name;
	protected ArrayList<String> otherNames=new ArrayList<>();
	
	public Historical(String name) {
		this.name=name;
	}
	
	public boolean addOtherName(String otherName) {
		if(otherNames.contains(otherName)) {
			return false;
		}else {
			otherNames.add(otherName);
			return true;
		}
	}
}
