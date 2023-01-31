package model;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Festival extends Historical {
	protected String location;
	protected String doiTuongSuyTon;
	protected String desc;
	protected String holdTime;

	public Festival(String name, String holdTime, String location, String doiTuongSuyTon, String desc) {
		super(name);
		this.holdTime = holdTime;
		this.location = location;
		this.desc = desc;
		this.doiTuongSuyTon = doiTuongSuyTon;
	}

	@Override
	public String toString()
	{
		return this.name + " " + this.location + " " + this.doiTuongSuyTon + " " + this.desc + " " + this.holdTime;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Gson gson = new Gson();
		FileReader reader = new FileReader("/home/tienviper/Desktop/oop/history_vietnam/src/data/festival.json");
		Festival Festivals[] = null;
		Festivals = gson.fromJson(reader, Festival[].class);
		System.out.println(Festivals);
		for (Festival p : Festivals){
			System.out.println(p);
		}
	}
}
