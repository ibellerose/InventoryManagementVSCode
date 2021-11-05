//package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.control.Label;

public class UpdateInfo {
	
	private String csvFinAddress = "C:\\OneFunInvManSys\\finishedGood.csv";
	private String csvFileRaw = "C:\\OneFunInvManSys\\rawMaterial.csv";
	private String csvFileAssoc = "C:\\OneFunInvManSys\\associatedTable.csv";
	private String csvFileAssocNum = "C:\\OneFunInvManSys\\associatedNumbers.csv";
	private String csvFileBox = "C:\\OneFunInvManSys\\box.csv";
	private String csvForecast = "C:\\OneFunInvManSys\\forecast.csv";
	
	//private ArrayList<RawMaterial> rawMaterialList = new ArrayList<RawMaterial>();
	//private ArrayList<FinishedGood> finishedGoodList = new ArrayList<FinishedGood>();
	
	private ArrayList<String> finName = new ArrayList<String>();
	private ArrayList<String> rawName = new ArrayList<String>();
	//private ArrayList<Double> rawNum = new ArrayList<Double>();
	
	private ArrayList<RawMaterial> assocRawName = new ArrayList<RawMaterial>();
	private ArrayList<Double> assocRawNum = new ArrayList<Double>();
	
	private ArrayList<Label> rawMaterialNumberLabel = new ArrayList<Label>();
	
	private ArrayList<Box> boxList = new ArrayList<Box>();
	
	private String line = "";
	
	UpdateInfo(ArrayList<ForecastData> forecastList){
		//populate finished good raw materials used
		//TODO:CHANGE NAME
		int count5 = 0;
			try {
				BufferedReader br = new BufferedReader(new FileReader(csvForecast)); 
					while ((line = br.readLine()) != null) {
						//assocRawName.clear();
						// use comma as separator 
						String[] forecastSingle = line.split(","); 
						forecastList.add(new ForecastData(forecastSingle[0]));
						
						for(int i = 1; i < forecastSingle.length; i++) {
							forecastList.get(count5).setGood(Integer.parseInt(forecastSingle[i]));
						}
						count5++;
					}
					br.close();
				} catch (IOException e3) {
					// 
					e3.printStackTrace();
				}
		
	}
	
	UpdateInfo(ArrayList<RawMaterial> rawMaterialList,ArrayList<FinishedGood> finishedGoodList){
		rawMaterialList.clear();
		finishedGoodList.clear();
		//populate boxList
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileBox)); 
				while ((line = br.readLine()) != null) { 
					// use comma as separator 
					String[] cols = line.split(","); 
					//System.out.println("Coulmn 0= " + cols[0]); 
					if(cols[0].compareTo("") != 0) {
						boxList.add(new Box(cols[0]));
					}
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}

	//populate the percent the box is used
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileBox)); 
				int boxCount = 0;
				while ((line = br.readLine()) != null) {
					// use comma as separator 
					String[] cols = line.split(","); 
					//System.out.println("Coulmn 0= " + cols[0]); 
					//if(cols[1].compareTo("") != 0) {
					boxList.get(boxCount).setPercentUsed(Double.parseDouble(cols[1]));
					//}
					boxCount++;
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
		
	//populate the volume of the box in sq ft
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileBox));
				int boxCount = 0;
				while ((line = br.readLine()) != null) {
					// use comma as separator 
					String[] cols = line.split(","); 
					//System.out.println("Coulmn 0= " + cols[0]); 
					//if(cols[0].compareTo("") != 0) {
					boxList.get(boxCount).setVolume(Double.parseDouble(cols[2]));
					//}
					boxCount++;
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
	
	//populate the tape used for each box
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileBox));
			int tapeCount = 0;
				while ((line = br.readLine()) != null) {
					// use comma as separator 
					String[] cols = line.split(","); 
					//System.out.println("Coulmn 0= " + cols[0]); 
					//if(cols[0].compareTo("") != 0) {
					boxList.get(tapeCount).setTapeUsed(Double.parseDouble(cols[3]));
					//}
					tapeCount++;
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
		
		
	//populate the rawMaterial
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFileRaw)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]); 
				//if(cols[0].compareTo("") != 0) {
				rawName.add(cols[0]);
				//}
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}

	//Populate finishedGoodList
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFinAddress)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]);
				//if(cols[0].compareTo("") != 0) {
				finName.add(cols[0]);
				//}
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}
	//populate the Labels for the raw materials
	for(int i = 0; i < rawName.size(); i++) {
		rawMaterialNumberLabel.add(new Label());
	}
	
	//populate the rawMaterial
	for(int i = 0; i < rawName.size(); i++) {
		rawMaterialList.add(new RawMaterial(rawName.get(i)));
	}
	
	//Populating finishedGoodList Name
	for(int i = 0; i < finName.size(); i++) {
		finishedGoodList.add(new FinishedGood(finName.get(i)));
	}
	
	//adding rawMaterial to finishedGoodList
	//for(int i = 0; i < finName.size(); i++) {
		//assocRawName.clear();
	
		
		//finishedGoodList.get(i).setMaterials(assocRawName);
	//}
	
	//adding rawMaterialUsedNumbers to finishedGoodList
	//for(int i = 0; i < finName.size(); i++) {
		//rawNum.clear();
		
		//populate number of raw material used per finished good
		//TODO change name
		int count10 = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileAssocNum)); 
				while ((line = br.readLine()) != null) {
					assocRawNum.clear();
					// use comma as separator 
					String[] assocRawNumStr = line.split(","); 
					Double[] assocRawNumArray = Arrays.stream(assocRawNumStr).map(Double::valueOf).toArray(Double[]::new);
					for(int i = 0; i < assocRawNumArray.length; i++) {
						assocRawNum.add(assocRawNumArray[i]);
					}
					
					//rawNum.add(1);
					//if(cols[i].compareTo("") != 0) {
						finishedGoodList.get(count10).setMaterialsUsedNumber(assocRawNum);
					//}
					count10++;
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
		//finishedGoodList.get(i).setMaterialsUsedNumber(rawNum);
	//}
	
	//Finished good pricing
	int priceCount = 0;
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFinAddress)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]); 
				//if(cols[0].compareTo("") != 0) {
				finishedGoodList.get(priceCount).setPrice(Double.parseDouble(cols[2]));
				//}
				priceCount++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}
	
	//set Lead Time of raw materials
	int leadCount = 0;
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFileRaw)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]); 
				//if(cols[0].compareTo("") != 0) {
				rawMaterialList.get(leadCount).setLeadTime(Integer.parseInt(cols[2]));
				//}
				leadCount++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}
	
	
	//set Lead Time of raw materials
	int amount8ozCount = 0;
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFileRaw)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]); 
				//if(cols[0].compareTo("") != 0) {
				rawMaterialList.get(amount8ozCount).setAmount8oz(Double.parseDouble(cols[4]));
				//}
				amount8ozCount++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}
	
	
	
	//setting lead time to finished good
	leadCount = 0;
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFinAddress)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]); 
				//if(cols[0].compareTo("") != 0) {
				finishedGoodList.get(leadCount).setLeadTime(Integer.parseInt(cols[3]));
				//}
				leadCount++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}
	
	//TODO: change name and initiation location
	int quantCount = 0;
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFileRaw)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]); 
				//if(cols[0].compareTo("") != 0) {
				rawMaterialList.get(quantCount).setOrderQuantity(Double.parseDouble(cols[3]));
				//}
				quantCount++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}
		
	//get stock of finished goods
	try {
		int count = 0;
		BufferedReader br = new BufferedReader(new FileReader(csvFinAddress)); 
			while ((line = br.readLine()) != null) { 
			    // use comma as separator 
			    String[] cols = line.split(","); 
			    //System.out.println("Coulmn 0= " + cols[0]); 
			    finishedGoodList.get(count).setStock(Integer.parseInt(cols[1]));
			    count++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		} 
	
	//get stock of raw Materials
	try {
		int count = 0;
		BufferedReader br = new BufferedReader(new FileReader(csvFileRaw)); 
			while ((line = br.readLine()) != null) { 
			    // use comma as separator 
			    String[] cols = line.split(","); 
			    //System.out.println("Coulmn 0= " + cols[0]); 
			    rawMaterialList.get(count).setStock(Double.parseDouble(cols[1]));
			    count++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		} 
	//set Usage for raw materials
	try {
		int count = 0;
		BufferedReader br = new BufferedReader(new FileReader(csvFileRaw)); 
			while ((line = br.readLine()) != null) { 
			    // use comma as separator 
			    String[] cols = line.split(","); 
			    //System.out.println("Coulmn 0= " + cols[0]); 
			    rawMaterialList.get(count).setSingleUse(Double.parseDouble(cols[4]));
			    count++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		} 
	
	//populate finished good raw materials used
	//TODO:CHANGE NAME
	int count5 = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileAssoc)); 
				while ((line = br.readLine()) != null) {
					assocRawName.clear();
					// use comma as separator 
					String[] assocRawNameArray = line.split(","); 
					
					for(int i = 0; i < assocRawNameArray.length; i++) {
						for(int j = 0; j < rawMaterialList.size(); j++) {
							if(assocRawNameArray[i].equals(rawMaterialList.get(j).getName())) {
								assocRawName.add(rawMaterialList.get(j));
							}
						}
					}
					//System.out.println("Coulmn 0= " + cols[0]); 
					//if(cols[i] != null) {
						//assocRawName.add(cols[i]);
					//}
					finishedGoodList.get(count5).setMaterials(assocRawName);
					
					count5++;
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
		
	}

	UpdateInfo(ArrayList<RawMaterial> rawMaterialList,ArrayList<FinishedGood> finishedGoodList, ArrayList<Box> boxList){
		rawMaterialList.clear();
		finishedGoodList.clear();
		boxList.clear();
		//populate boxList
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileBox)); 
				while ((line = br.readLine()) != null) { 
					// use comma as separator 
					String[] cols = line.split(","); 
					//System.out.println("Coulmn 0= " + cols[0]); 
					if(cols[0].compareTo("") != 0) {
						boxList.add(new Box(cols[0]));
					}
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}

	//populate the percent the box is used
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileBox)); 
				int boxCount = 0;
				while ((line = br.readLine()) != null) {
					// use comma as separator 
					String[] cols = line.split(","); 
					//System.out.println("Coulmn 0= " + cols[0]); 
					//if(cols[1].compareTo("") != 0) {
					boxList.get(boxCount).setPercentUsed(Double.parseDouble(cols[1]));
					//}
					boxCount++;
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
		
	//populate the volume of the box in sq ft
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileBox));
				int boxCount = 0;
				while ((line = br.readLine()) != null) {
					// use comma as separator 
					String[] cols = line.split(","); 
					//System.out.println("Coulmn 0= " + cols[0]); 
					//if(cols[0].compareTo("") != 0) {
					boxList.get(boxCount).setVolume(Double.parseDouble(cols[2]));
					//}
					boxCount++;
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
	
	//populate the tape used for each box
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileBox));
			int tapeCount = 0;
				while ((line = br.readLine()) != null) {
					// use comma as separator 
					String[] cols = line.split(","); 
					//System.out.println("Coulmn 0= " + cols[0]); 
					//if(cols[0].compareTo("") != 0) {
					boxList.get(tapeCount).setTapeUsed(Double.parseDouble(cols[3]));
					//}
					tapeCount++;
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
		
	//populate the box stock
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileBox));
			int stockCount = 0;
				while ((line = br.readLine()) != null) {
					// use comma as separator 
					String[] cols = line.split(","); 
					//System.out.println("Coulmn 0= " + cols[0]); 
					//if(cols[0].compareTo("") != 0) {
					boxList.get(stockCount).setStock(Integer.parseInt(cols[4]));
					//}
					stockCount++;
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
		
	//populate the rawMaterial
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFileRaw)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]); 
				//if(cols[0].compareTo("") != 0) {
				rawName.add(cols[0]);
				//}
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}

	//Populate finishedGoodList
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFinAddress)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]);
				//if(cols[0].compareTo("") != 0) {
				finName.add(cols[0]);
				//}
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}
	//populate the Labels for the raw materials
	for(int i = 0; i < rawName.size(); i++) {
		rawMaterialNumberLabel.add(new Label());
	}
	
	//populate the rawMaterial
	for(int i = 0; i < rawName.size(); i++) {
		rawMaterialList.add(new RawMaterial(rawName.get(i)));
	}
	
	//Populating finishedGoodList Name
	for(int i = 0; i < finName.size(); i++) {
		finishedGoodList.add(new FinishedGood(finName.get(i)));
	}
	
	//adding rawMaterial to finishedGoodList
	//for(int i = 0; i < finName.size(); i++) {
		//assocRawName.clear();
		
		//finishedGoodList.get(i).setMaterials(assocRawName);
	//}
	
	//adding rawMaterialUsedNumbers to finishedGoodList
	//for(int i = 0; i < finName.size(); i++) {
		//rawNum.clear();
		
		//populate number of raw material used per finished good
		//TODO change name
		int count10 = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileAssocNum)); 
				while ((line = br.readLine()) != null) {
					assocRawNum.clear();
					// use comma as separator 
					String[] assocRawNumStr = line.split(","); 
					Double[] assocRawNumArray = Arrays.stream(assocRawNumStr).map(Double::valueOf).toArray(Double[]::new);
					for(int i = 0; i < assocRawNumArray.length; i++) {
						assocRawNum.add(assocRawNumArray[i]);
					}
					
					//rawNum.add(1);
					//if(cols[i].compareTo("") != 0) {
						finishedGoodList.get(count10).setMaterialsUsedNumber(assocRawNum);
					//}
					count10++;
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
		//finishedGoodList.get(i).setMaterialsUsedNumber(rawNum);
	//}
	
	//Finished good pricing
	int priceCount = 0;
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFinAddress)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]); 
				//if(cols[0].compareTo("") != 0) {
				finishedGoodList.get(priceCount).setPrice(Integer.parseInt(cols[2]));
				//}
				priceCount++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}
	
	//set Lead Time of raw materials
	int leadCount = 0;
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFileRaw)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]); 
				//if(cols[0].compareTo("") != 0) {
				rawMaterialList.get(leadCount).setLeadTime(Integer.parseInt(cols[2]));
				//}
				leadCount++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}
	
	//setting lead time to finished good
	leadCount = 0;
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFinAddress)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]); 
				//if(cols[0].compareTo("") != 0) {
				finishedGoodList.get(leadCount).setLeadTime(Integer.parseInt(cols[3]));
				//}
				leadCount++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}
	
	//TODO: change name and initiation location
	int quantCount = 0;
	try {
		BufferedReader br = new BufferedReader(new FileReader(csvFileRaw)); 
			while ((line = br.readLine()) != null) {
				// use comma as separator 
				String[] cols = line.split(","); 
				//System.out.println("Coulmn 0= " + cols[0]); 
				//if(cols[0].compareTo("") != 0) {
				rawMaterialList.get(quantCount).setOrderQuantity(Integer.parseInt(cols[3]));
				//}
				quantCount++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		}
		
	//get stock of finished goods
	try {
		int count = 0;
		BufferedReader br = new BufferedReader(new FileReader(csvFinAddress)); 
			while ((line = br.readLine()) != null) { 
			    // use comma as separator 
			    String[] cols = line.split(","); 
			    //System.out.println("Coulmn 0= " + cols[0]); 
			    finishedGoodList.get(count).setStock(Integer.parseInt(cols[1]));
			    count++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		} 
	
	//get stock of raw Materials
	try {
		int count = 0;
		BufferedReader br = new BufferedReader(new FileReader(csvFileRaw)); 
			while ((line = br.readLine()) != null) { 
			    // use comma as separator 
			    String[] cols = line.split(","); 
			    //System.out.println("Coulmn 0= " + cols[0]); 
			    rawMaterialList.get(count).setStock(Double.parseDouble(cols[1]));
			    count++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		} 
	//set Usage for raw materials
	try {
		int count = 0;
		BufferedReader br = new BufferedReader(new FileReader(csvFileRaw)); 
			while ((line = br.readLine()) != null) { 
			    // use comma as separator 
			    String[] cols = line.split(","); 
			    //System.out.println("Coulmn 0= " + cols[0]); 
			    rawMaterialList.get(count).setSingleUse(Double.parseDouble(cols[4]));
			    count++;
			}
			br.close();
		} catch (IOException e3) {
			// 
			e3.printStackTrace();
		} 
	
	//populate finished good raw materials used
	//TODO:CHANGE NAME
	int count5 = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileAssoc)); 
				while ((line = br.readLine()) != null) {
					assocRawName.clear();
					// use comma as separator 
					String[] assocRawNameArray = line.split(","); 
					
					for(int i = 0; i < assocRawNameArray.length; i++) {
						for(int j = 0; j < rawMaterialList.size(); j++) {
							if(assocRawNameArray[i].equals(rawMaterialList.get(j).getName())) {
								assocRawName.add(rawMaterialList.get(j));
							}
						}
					}
					//System.out.println("Coulmn 0= " + cols[0]); 
					//if(cols[i] != null) {
						//assocRawName.add(cols[i]);
					//}
					finishedGoodList.get(count5).setMaterials(assocRawName);
					
					count5++;
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
		
	}

	
	
	
}
