//package application;

public class Box {
	private String name;
	private double volume;
	private double percent_used;
	private double tape_used;
	private double needed;
	private final int ORDER_QUANTITY = 25;
	private int stock;
	private final int LEAD_TIME = 3;
	
	Box(String name){
		this.name = name;
		volume = 0;
		percent_used = 0;
		tape_used = 0;
		needed = 0;
		stock = 0;
	}
	
	Box(){
		name = "";
		volume = 0;
		percent_used = 0;
		tape_used = 0;
		needed = 0;
		stock = 0;
	}
	
	void setName(String newName) {
		name = newName;
	}
	
	void setVolume(double newVolume) {
		volume = newVolume;
	}
	
	void setPercentUsed(double new_percent_used) {
		percent_used = new_percent_used;
	}
	
	void setTapeUsed(double new_tape_used) {
		tape_used = new_tape_used;
	}
	
	void setStock(int newStock) {
		stock = newStock;
	}
	
	String getName() {
		return name;
	}
	
	double getVolume() {
		return volume;
	}
	
	double getPercentUsed() {
		return percent_used;
	}
	
	int getTapeUsed() {
		return (int)tape_used;
	}
	
	void addToNeeded(double add) {
		needed += add * percent_used;
	}
	
	double getNeeded() {
		return needed;
	}
	
	int getStock() {
		return stock;
	}
	
	double getTrigger() {
		return (needed * 9) / 30;
	}
	
	int getLeadTime() {
		return LEAD_TIME;
	}
	
	int orderAmount() {
		return (int)(needed/ORDER_QUANTITY);
	}
	
	int daysLeft() {
		return (int)(stock/(needed/20));
	}
	
	void removeOneFromInventory() {
		stock--;
	}
	

}
