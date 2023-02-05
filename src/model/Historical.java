package model;

import java.util.ArrayList;

public abstract class Historical {
	protected String name;
	protected ArrayList<String> otherNames = new ArrayList<>();

	public ArrayList<String> getOtherNames() {
		return otherNames;
	}

	public Historical(String name) {
		this.name = name;
	}

	public boolean addOtherName(String otherName) {
		if (otherNames.contains(otherName)) {
			return false;
		} else {
			otherNames.add(otherName);
			return true;
		}
	}

	public String getName() {
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		Historical historical = (Historical) obj;
		if (historical.name.equals(this.name))
			return true;
		return false;

	}

	public boolean checking(Object obj) {

		if (obj instanceof String) {
			String a = (String) obj;
			if (otherNames == null)
				return name.contains(a);
			if (otherNames != null && !otherNames.isEmpty()) {
				for (int i = 0; i < otherNames.size(); i++) {
					if (otherNames.get(i).contains(a))
						return true;
				}
			}
			if (name != null && !name.isEmpty())
				return name.contains(a);
		}
		return false;
	}
}
