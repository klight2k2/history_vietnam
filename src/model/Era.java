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

	public void fillEra(int isPrecededBy, int isSuccessedBy) {
		this.isPrecededBy = isPrecededBy;
		this.isSuccessedBy = isSuccessedBy;
	}
}
