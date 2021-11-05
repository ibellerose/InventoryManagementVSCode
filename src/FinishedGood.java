//package application;

import java.util.ArrayList;

public class FinishedGood {
	private String name;
	private ArrayList<RawMaterial> rawMaterials;
	private double price;
	private ArrayList<Double> materialsUsedNumber = new ArrayList<Double>();
	private double needed;
	private int leadTime;
	private int stock;
	private final int COLORS = 4;
	
	FinishedGood(String name){
		this.name = name;
		price = 0;
		this.rawMaterials = new ArrayList<RawMaterial>();
		needed = 0;
		leadTime = 0;
		stock = 0;
	}
	
	FinishedGood(String name, int price, ArrayList<Double> materialsUsedNumber){
		this.name = name;
		this.rawMaterials = new ArrayList<RawMaterial>();
		this.price = price;
		this.materialsUsedNumber = materialsUsedNumber;
		needed = 0;
		leadTime = 0;
		stock = 0;
	}
	void setName(String name) {
		this.name = name;
		needed = 0;
		leadTime = 0;
	}
	
	void setMaterials(ArrayList<RawMaterial> materials) {
		rawMaterials.clear();
		for(int i = 0; i < materials.size(); i++)
		{
			rawMaterials.add(materials.get(i));
		}
	}
	
	void setPrice(double newPrice) {
		price = newPrice;
	}
	
	void setMaterialsUsedNumber(ArrayList<Double> newMaterialsUsed) {
		materialsUsedNumber.clear();
		for(int i = 0; i < newMaterialsUsed.size(); i++)
		{
			materialsUsedNumber.add(newMaterialsUsed.get(i));
		}
	}
	
	void setLeadTime(int newLeadTime) {
		leadTime = newLeadTime;
	}
	
	void setStock(int newStock) {
		stock = newStock;
	}
	
	void addToNeeded(double add) {
		needed += add;
	}
	
	String getName() {
		return name;
	}
	
	ArrayList<RawMaterial> getMaterialList(){
		return rawMaterials;
	}
	
	double getPrice() {
		return price;
	}

	ArrayList<Double> getMaterialsUsedNumber(){
		return materialsUsedNumber;
	}
	
	int getLeadTime() {
		return leadTime;
	}
	
	double getNeeded() {
		return needed;
	}
	
	double getTrigger() {
		return ((needed / 30) * leadTime * COLORS) * 1.25;
	}
	
	int getStock() {
		return stock;
	}
	
	void addMaterial(RawMaterial material) {
			rawMaterials.add(material);
	}

	void addMaterialUsage(Double materialUsage) {
		materialsUsedNumber.add(materialUsage);
}
	
	String toStringMaterials() {
		String allMat = "";
		
		for(int i = 0; i < rawMaterials.size(); i++) {
				allMat += rawMaterials.get(i).getName();
				
				if(i < rawMaterials.size() - 1) {
					allMat += ",";
				}
				else {
					allMat += "\n";
				}
		}
		
		return allMat;
	}
	
	String toStringMaterialUsages() {
		String allMat = "";
		
		for(int i = 0; i < materialsUsedNumber.size(); i++) {
				allMat += materialsUsedNumber.get(i);
				
				if(i < materialsUsedNumber.size() - 1) {
					allMat += ",";
				}
				else {
					allMat += "\n";
				}
				
		}
		
		return allMat;
	}
	
	
	
}
