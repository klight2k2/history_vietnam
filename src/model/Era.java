package model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Era extends Historical {
	protected String fromYear;
	protected String toYear;
	protected Era isPreceededBy;
	protected Era isSuccessedBy;
	public Era(String name,String fromYear,String toYear,Era isPrecededBy,Era isSuccessedBy) {
		super(name);
		this.fromYear=fromYear;
		this.toYear=toYear;
		this.isPreceededBy=isPrecededBy;
		this.isSuccessedBy=isSuccessedBy;
	}
	public Era(String name,String fromYear,String toYear) {
		super(name);
		this.fromYear=fromYear;
		this.toYear=toYear;
	}

	@Override
	public String toString()
	{
		return this.name + " " + this.fromYear + " " + this.toYear;
	}

	public static void main(String[] args) throws FileNotFoundException{
		Gson gson = new Gson();
		FileReader reader = new FileReader("/home/tienviper/Desktop/oop/history_vietnam/src/data/era.json");
		Era erass[] = null;
		erass = gson.fromJson(reader, Era[].class);
		System.out.println(erass);
		for (Era p : erass){
			System.out.println(p);
		}
		// System.out.println(erass[0]);
	}
}
