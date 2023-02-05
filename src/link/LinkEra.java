package link;

import java.util.ArrayList;

import model.Era;

public class LinkEra implements Runnable {
	private ArrayList<Era> listEraDataRaw;
	private ArrayList<Era> listEraData;
	private int numsOfLinks = 0;

	public LinkEra(ArrayList<Era> listEraDataRaw, ArrayList<Era> listEraData) {
		this.listEraDataRaw = listEraDataRaw;
		this.listEraData = listEraData;
	}

	public int findExistedPreceded(int curId) {
		for (Era era : listEraData) {
			if (era.getSuccessed() == curId) {
				return era.getId();
			}
		}
		return 0;
	}

	public int findExistedSuccessed(int curId) {
		for (Era era : listEraData) {
			if (era.getPreceded() == curId) {
				return era.getId();
			}
		}
		return 0;
	}

	public int YearStringToInt(String year) {
		if (year.isEmpty() || year.contains("đến nay")) {
			return 999999;
		}
		int trCN = 1;
		if (year.contains("trCN")) {
			trCN = -1;
			year = year.replace("trCN", "");
		}
		year = year.replaceAll("\\s+", "");
		int intYear = Integer.parseInt(year) * trCN;
		return intYear;
	}

	public void findPrecededAndSuccessed(Era curEra) {
		if (curEra.getFromYear().isEmpty() && curEra.getToYear().isEmpty()) {
			curEra.fillEra(1, 2);
			this.numsOfLinks += 2;
			listEraData.add(curEra);
		} else {
			int nearestPreviousEraGap = 99999;
			int nearestNextEraGap = 99999;

			int nearestPreviousEraId = findExistedPreceded(curEra.getId());
			int nearestNextEraId = findExistedSuccessed(curEra.getId());

			// Previous Era
			if (nearestPreviousEraId == 0)
				for (Era era : listEraDataRaw) {
					int loopEraYear = YearStringToInt(era.getToYear());
					int curEraYear = YearStringToInt(curEra.getFromYear());
					if (YearStringToInt(curEra.getToYear()) < 0) {
						curEraYear = curEraYear * (-1);
					}
					if (Math.abs(curEraYear - loopEraYear) < nearestPreviousEraGap) {
						nearestPreviousEraGap = Math.abs(curEraYear - loopEraYear);
						nearestPreviousEraId = era.getId();
					}
				}

			// Next Era
			if (curEra.getToYear().contains("đến nay")) {
				nearestNextEraId = curEra.getId();
			}
			if (nearestNextEraId == 0)
				for (Era era : listEraDataRaw) {
					int loopEraYear = YearStringToInt(era.getFromYear());
					if (YearStringToInt(era.getToYear()) < 0) {
						loopEraYear = loopEraYear * (-1);
					}
					int curEraYear = YearStringToInt(curEra.getToYear());
					if (Math.abs(curEraYear - loopEraYear) < nearestNextEraGap) {
						nearestNextEraGap = Math.abs(curEraYear - loopEraYear);
						nearestNextEraId = era.getId();
					}
				}

			this.numsOfLinks += 2;
			curEra.fillEra(nearestPreviousEraId, nearestNextEraId);
			listEraData.add(curEra);
		}
	}

	public void run() {
		System.out.println("Link Era");
		for (Era era : listEraDataRaw) {
			findPrecededAndSuccessed(era);
		}

//		String filePath = "src\\data\\";
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		try {
//			FileWriter writer = new FileWriter(new File(filePath + "numsOfLinkEra.json"));
//			gson.toJson(numsOfLinks, writer);
//			writer.flush();
//			writer.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
