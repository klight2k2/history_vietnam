package model;

public class Era extends Historical {
	protected int id;
	public static int idCounter = 1;
	protected String fromYear;
	protected String toYear;
	protected int isPrecededBy;
	protected int isSuccessedBy;

	public Era(String name) {
		super(name);
	}

	public Era(String name, String fromYear, String toYear, int isPrecededBy, int isSuccessedBy) {
		super(name);
		this.id = idCounter++;
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.isPrecededBy = isPrecededBy;
		this.isSuccessedBy = isSuccessedBy;
	}

	public Era(String name, String fromYear, String toYear) {
		super(name);
		this.id = idCounter++;
		this.fromYear = fromYear;
		this.toYear = toYear;
	}

	public Era getRawEra(String name) {
		Era era = new Era(name);
		era.id = this.id;
		era.fromYear = this.fromYear;
		era.toYear = this.toYear;
		return era;
	}

	public void fillEra(Era era) {
		this.isPrecededBy = era.isPrecededBy;
		this.isSuccessedBy = era.isSuccessedBy;
	}
}
