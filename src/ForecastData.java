//package application;

import java.util.ArrayList;

public class ForecastData {
	private String date;
	private ArrayList<Integer> estimatedGoods;
	
	ForecastData(String date){
		this.date = date;
		estimatedGoods = new ArrayList<Integer>();
	}
	
	ForecastData(){
		date = "";
		estimatedGoods = new ArrayList<Integer>();
	}
	
	//getters
	String getDate() {
		return date;
	}
	
	ArrayList<Integer> getAllEstimatedGoods(){
		return estimatedGoods;
	}
	
	int getSingleGood(int index) {
		if(index >= 0 && index < estimatedGoods.size()) {
			return estimatedGoods.get(index);
		}
		else
			return -1;
	}
	
	//setters
	void setDate(String newDate) {
		date = newDate;
	}
	
	void setGood(int newNum) {
		estimatedGoods.add(newNum);
	}

}
