package model;

import java.util.ArrayList;

public abstract class Historical {
	protected String name;
	protected ArrayList<String> otherNames = new ArrayList<>();

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
}
