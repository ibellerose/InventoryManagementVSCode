//package application;

public class RawMaterial {
	private String name;
	private double needed;
	private int leadTime;
	private double orderQuantity;
	private double stock;
	private double singleUsage;
	private double amount8oz;
	
	RawMaterial(){
		name = "";
		needed = 0;
		leadTime = 0;
		orderQuantity = 0;
		stock = 0;
		singleUsage = 0;
		amount8oz = 0;
	}
	
	RawMaterial(String name){
		this.name = name;
	}
	
	String getName() {
		return name;
	}
	
	double getNeeded() {
		return needed;
	}
	
	int getLeadTime() {
		return leadTime;
	}
	
	double getOrderQuantity() {
		return orderQuantity;
	}
	
	double getStock() {
		return stock;
	}
	
	int getSingleUse() {
		return (int)(stock / singleUsage);
	}
	
	double getAmount8oz() {
		return amount8oz;
	}
	
	void setName(String newName) {
		name = newName;
	}
	
	void setNeeded(int newNeeded) {
		needed = newNeeded;
	}
	
	void setLeadTime(int newLeadTime) {
		leadTime = newLeadTime;
	}
	
	void setOrderQuantity(double newOrderQuantity) {
		orderQuantity = newOrderQuantity;
	}
	
	void setStock(double newStock) {
		stock = newStock;
	}
	
	void setSingleUse(double newUsage) {
		singleUsage = newUsage;
	}
	
	void setAmount8oz(double newAmount8oz) {
		amount8oz = newAmount8oz;
	}
	
	//No longer letting a reOrder size
	/*
	double getAndSetReOrderSize(int week) {
		double order = ((needed/30) * 7.5) * week;
		
		reOrderSize = order;
		return reOrderSize;
	}
	*/
	
	void addToTotal(double add) {
		needed += add;
	}
	
	int getTrigger() {
		double trigger = (((leadTime * 3) * needed) / 30);
		return (int)trigger;
	}
	
	int orderAmount() {
		int num = (int)(needed/orderQuantity) + 1;
		
		if(num < 1)
			return 1;
		else
			return num;
	}
	
	int daysLeft() {
		return (int)(stock/(needed/20));
	}
	

}
