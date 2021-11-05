//package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class AddFinishedGood extends VBox
{
	//private ObservableList<ItemModel> oList = FXCollections.<ItemModel>observableArrayList();
	
	private ArrayList<FinishedGood> finishedGoodList = new ArrayList<FinishedGood>();
	private ArrayList<RawMaterial> rawMaterialList = new ArrayList<RawMaterial>();
	
	private ArrayList<TextField> finishedGoodTF = new ArrayList<TextField>();
	private ArrayList<Label> finishedGoodLabel = new ArrayList<Label>();
	private ArrayList<CheckBox> rawMaterialCB = new ArrayList<CheckBox>();
	private ArrayList<TextField> rawMaterialUsageTF = new ArrayList<TextField>();
	
	private ArrayList<Label> rawMaterialUsageLabel = new ArrayList<Label>();
	
	private ItemModel newItem = new ItemModel();
	private String fileAddress = "C:\\OneFunInvManSys\\finishedGood.csv";
	private String csvFileAssoc = "C:\\OneFunInvManSys\\associatedTable.csv";
	private String csvFileAssocNum = "C:\\OneFunInvManSys\\associatedNumbers.csv";
	
	private int gridSpot = 0;
	
	//private String fileAddress = "C:\\Users\\Ian\\eclipse-workspace\\InventoryTracking2\\finishedGood.csv";
	
	// constructor
	public AddFinishedGood()
	{
		Font headerFont = Font.font("Verdana", FontWeight.BOLD, 16);
	
		new UpdateInfo(rawMaterialList,finishedGoodList);

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		
		//Button to create new finished good
		Button addGood = new Button();
		addGood.setText("Add new Finished Good");
		GridPane.setConstraints(addGood, 0, gridSpot++);
		grid.getChildren().add(addGood);

		TextField name = new TextField();
		TextField price = new TextField();
		TextField leadTime = new TextField();
		TextField amount = new TextField();
		
		Button clear = new Button("Cancel");
		Button submit = new Button("Submit");
		
		//Adding a Label
		final Label label = new Label();
		GridPane.setConstraints(label, 0, 3);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);
		
		//Add label for reviewing finished good
		Label title1 = new Label();
		title1.setFont(headerFont);
		title1.setText("Calibrate Finished Goods:");
		GridPane.setConstraints(title1, 0, gridSpot++);
		GridPane.setColumnSpan(title1, 2);
		grid.getChildren().add(title1);
		
		
		//create labels and textfields for finished goods
		for(int i = 0; i < finishedGoodList.size(); i++) {
			Label tempLabel = new Label();
			tempLabel.setText(finishedGoodList.get(i).getName() + ": ");
			finishedGoodLabel.add(tempLabel);
			//finNameLabel.add(tempLabel);
			
			TextField tempTf = new TextField();
			tempTf.setPromptText("Enter Value");
			tempTf.setPrefColumnCount(10);
			tempTf.setText(String.valueOf(finishedGoodList.get(i).getStock()));
			//tempTf.setText(String.valueOf(forecastList.get(forecastDateCombo.getSelectionModel().getSelectedIndex()).getSingleGood(i)));
			tempTf.getText();
			finishedGoodTF.add(tempTf);
		
			GridPane.setConstraints(tempLabel, 0, gridSpot);
			GridPane.setColumnSpan(tempLabel, 1);
			grid.getChildren().add(tempLabel);
		
			GridPane.setConstraints(tempTf, 1, gridSpot++);
			GridPane.setColumnSpan(tempTf, 1);
			grid.getChildren().add(tempTf);
			//count = i;
		}
		
		//populating rawMaterialUsageTF
		for(int i = 0; i < rawMaterialList.size(); i++) {
			TextField usageTF = new TextField();
			usageTF.setPromptText(rawMaterialList.get(i).getName() + " used");
			rawMaterialUsageTF.add(usageTF);
		}
			
		//populating rawMaterialUsageLabel
		for(int i = 0; i < rawMaterialList.size(); i++) {
			Label rawMat = new Label();
			rawMat.setText(rawMaterialList.get(i).getName() + ":");
			rawMaterialUsageLabel.add(rawMat);
		}
			
		
		Button calibrate = new Button();
		calibrate.setText("Calibrate");
		GridPane.setConstraints(calibrate, 0, gridSpot);
		grid.getChildren().add(calibrate);
		
		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setContent(grid);
		
		Label successLabel = new Label();
		successLabel.setText("Calibration Succeeded!");
		GridPane.setConstraints(successLabel, 1, gridSpot);
		
		//this.getChildren().addAll(scroll);
		
		
		//Setting action for the calibrate button
		addGood.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				new UpdateInfo(rawMaterialList,finishedGoodList);
				
				grid.getChildren().clear();
				
				//Defining the Name text field
				name.setPromptText("Name of new Good");
				GridPane.setConstraints(name, 0, 0);
				grid.getChildren().add(name);
				
				//Add label for rawMaterial checkBox
				Label rawMaterialTitle = new Label();
				//rawMaterialTitle.setFont(headerFont);
				rawMaterialTitle.setText("Raw Material Additions:");
				GridPane.setConstraints(rawMaterialTitle, 0, 1);
				GridPane.setColumnSpan(rawMaterialTitle, 2);
				grid.getChildren().add(rawMaterialTitle);
				
				gridSpot = gridSpot + 1;
				//set up raw materials check box
				for(int i = 0; i < rawMaterialList.size(); i++) {
					CheckBox rawMaterialBox = new CheckBox();
					rawMaterialBox.setText(rawMaterialList.get(i).getName());
					GridPane.setConstraints(rawMaterialBox,i%3,(int)((gridSpot++/3) + 1));
					grid.getChildren().add(rawMaterialBox);
					rawMaterialCB.add(rawMaterialBox);
				}
				
				
				gridSpot = (gridSpot/3) + 1;
				//Defining the price text field
				price.setPromptText("Enter Price");
				GridPane.setConstraints(price, 0, gridSpot++);
				grid.getChildren().add(price);
				//Defining the leadTime text field
				leadTime.setPromptText("Enter Lead Time");
				GridPane.setConstraints(leadTime, 0, gridSpot++);
				grid.getChildren().add(leadTime);
				//Defining the amount text field
				amount.setPromptText("Enter Stock Amount");
				GridPane.setConstraints(amount, 0, gridSpot++);
				grid.getChildren().add(amount);
				
				
				
				
				//Defining the Submit button
				GridPane.setConstraints(submit, 1, 0);
				grid.getChildren().add(submit);
				//Defining the Clear button
				GridPane.setConstraints(clear, 2, 0);
				GridPane.setColumnSpan(clear, 2);
				grid.getChildren().add(clear);
				
				Label rawMaterialUsageTitle = new Label();
				rawMaterialUsageTitle.setFont(headerFont);
				rawMaterialUsageTitle.setText("Raw Material Usage:");
				GridPane.setConstraints(rawMaterialUsageTitle, 0, gridSpot++);
				grid.getChildren().add(rawMaterialUsageTitle);
				
				
				//Adding textfield next to checkBox if clicked
				for(int i = 0; i < rawMaterialCB.size(); i++) {
					final int index = i;
					
					rawMaterialCB.get(i).setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

						public void handle(ActionEvent e) {
							if(rawMaterialCB.get(index).isSelected()) {
								GridPane.setConstraints(rawMaterialUsageLabel.get(index), 0, gridSpot);
								grid.getChildren().add(rawMaterialUsageLabel.get(index));
								GridPane.setConstraints(rawMaterialUsageTF.get(index), 1, gridSpot++);
								grid.getChildren().add(rawMaterialUsageTF.get(index));
							}
							
							if(!rawMaterialCB.get(index).isSelected()) {
								if(grid.getChildren().contains(rawMaterialUsageTF.get(index))) {
									grid.getChildren().removeAll(rawMaterialUsageLabel.get(index),rawMaterialUsageTF.get(index));
									
									gridSpot--;
								}
							}
						}
							
							
						
					});
				}
				
				
				/*
				//Add label for reviewing finished good
				Label title1 = new Label();
				title1.setFont(headerFont);
				title1.setText("Finished Goods:");
				GridPane.setConstraints(title1, 0, gridSpot++);
				GridPane.setColumnSpan(title1, 2);
				grid.getChildren().add(title1);
				
				//create labels and textfields for finished goods
				for(int i = 0; i < finishedGoodList.size(); i++) {
					Label tempLabel = new Label();
					tempLabel.setText(finishedGoodList.get(i).getName() + ": ");
					finishedGoodLabel.add(tempLabel);
					//finNameLabel.add(tempLabel);
					
					TextField tempTf = new TextField();
					tempTf.setPromptText("Enter Value");
					tempTf.setPrefColumnCount(10);
					tempTf.setText(String.valueOf(finishedGoodList.get(i).getStock()));
					//tempTf.setText(String.valueOf(forecastList.get(forecastDateCombo.getSelectionModel().getSelectedIndex()).getSingleGood(i)));
					tempTf.getText();
					finishedGoodTF.add(tempTf);
				
					GridPane.setConstraints(tempLabel, 0, gridSpot);
					GridPane.setColumnSpan(tempLabel, 1);
					grid.getChildren().add(tempLabel);
				
					GridPane.setConstraints(tempTf, 1, gridSpot++);
					GridPane.setColumnSpan(tempTf, 1);
					grid.getChildren().add(tempTf);
					//count = i;
				}
				
				Button calibrate = new Button();
				calibrate.setText("Calibrate");
				GridPane.setConstraints(calibrate, 0, gridSpot);
				grid.getChildren().add(calibrate);
			
			*/
			}
		});
		
		
		
		//Setting action for the calibrate button
		calibrate.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				editInventory(fileAddress,finishedGoodTF);
				grid.getChildren().add(successLabel);
			}
		});
		
		//Setting an action for the Submit button
		submit.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

		@Override
		    public void handle(ActionEvent e) {
			
			
				boolean notSelected = true;
				for(int i = 0; i < rawMaterialCB.size(); i++) {
					if(rawMaterialCB.get(i).isSelected()) {
						notSelected = false;
					}
				}
			
			
		        if ((!name.getText().isEmpty() && !notSelected && !price.getText().isEmpty() && !amount.getText().isEmpty() && !leadTime.getText().isEmpty())) {
		        	//finishedGoodList.add(new FinishedGood(name.getText(), amount.getText(), price.getText(), leadTime.getText()));
		        	
		            finishedGoodList.add(new FinishedGood(name.getText()));
		            
					for(int i = 0; i < rawMaterialCB.size(); i++) {
						if(rawMaterialCB.get(i).isSelected()) {
							finishedGoodList.get(finishedGoodList.size()-1).addMaterial(rawMaterialList.get(i));
						}
					}
					
					for(int i = 0; i < rawMaterialUsageTF.size(); i++) {
						if(!rawMaterialUsageTF.get(i).getText().isEmpty()) {
							finishedGoodList.get(finishedGoodList.size()-1).addMaterialUsage(Double.parseDouble(rawMaterialUsageTF.get(i).getText()));
						}
					}
					
					
		            try {

			            finishedGoodList.get(finishedGoodList.size()-1).setPrice(Integer.parseInt(price.getText()));
			            finishedGoodList.get(finishedGoodList.size()-1).setStock(Integer.parseInt(amount.getText()));
			            finishedGoodList.get(finishedGoodList.size()-1).setLeadTime(Integer.parseInt(leadTime.getText()));
		            	
			            
			            addFinishedGood(fileAddress, finishedGoodList);
			            
			            addFinishedGoodMaterials(csvFileAssoc, finishedGoodList);
			            addFinishedGoodMaterialUsages(csvFileAssocNum, finishedGoodList);
		            	
			            new UpdateInfo(rawMaterialList,finishedGoodList);
			            
			            /*
						try(FileWriter sb = new FileWriter(fileAddress,true)){
							//StringBuilder sb = new StringBuilder();
							sb.append(finishedGoodList.get(finishedGoodList.size()-1).getName());
							sb.append(",");
							sb.append(String.valueOf(finishedGoodList.get(finishedGoodList.size()-1).getStock()));
							sb.append(",");
							sb.append(String.valueOf(finishedGoodList.get(finishedGoodList.size()-1).getPrice()));
							sb.append(",");
							sb.append(String.valueOf(finishedGoodList.get(finishedGoodList.size()-1).getLeadTime()));
							sb.append("\n");
							
							sb.flush();
							sb.close();
						

						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						*/
			            //Same as clear/cancel
						grid.getChildren().clear();
						rawMaterialCB.clear();
						
						grid.getChildren().add(addGood);
						
						gridSpot = 2;
						
						for(int i = 0; i < finishedGoodList.size(); i++) {
							Label tempLabel = new Label();
							tempLabel.setText(finishedGoodList.get(i).getName() + ": ");
							finishedGoodLabel.add(tempLabel);
							//finNameLabel.add(tempLabel);
							
							TextField tempTf = new TextField();
							tempTf.setPromptText("Enter Value");
							tempTf.setPrefColumnCount(10);
							tempTf.setText(String.valueOf(finishedGoodList.get(i).getStock()));
							//tempTf.setText(String.valueOf(forecastList.get(forecastDateCombo.getSelectionModel().getSelectedIndex()).getSingleGood(i)));
							tempTf.getText();
							finishedGoodTF.add(tempTf);
						
							GridPane.setConstraints(tempLabel, 0, gridSpot);
							GridPane.setColumnSpan(tempLabel, 1);
							grid.getChildren().add(tempLabel);
						
							GridPane.setConstraints(tempTf, 1, gridSpot++);
							GridPane.setColumnSpan(tempTf, 1);
							grid.getChildren().add(tempTf);
							//count = i;
						}
						
						GridPane.setConstraints(calibrate, 0, gridSpot);
						grid.getChildren().add(calibrate);
						
						grid.getChildren().add(calibrate);
						
		            	
		            }
		            catch(Exception e1) {
		            	label.setText("Must enter an integer in second text box");
		            }
		        } else {
		            label.setText("One or more fields are empty");
		        }
		     }
		 });
		 
		//Setting an action for the Clear button
		clear.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		    public void handle(ActionEvent e) {
			
				grid.getChildren().clear();
				rawMaterialCB.clear();
				
				grid.getChildren().add(addGood);
				
				gridSpot = 2;
				
				for(int i = 0; i < finishedGoodList.size(); i++) {
					Label tempLabel = new Label();
					tempLabel.setText(finishedGoodList.get(i).getName() + ": ");
					finishedGoodLabel.add(tempLabel);
					//finNameLabel.add(tempLabel);
					
					TextField tempTf = new TextField();
					tempTf.setPromptText("Enter Value");
					tempTf.setPrefColumnCount(10);
					tempTf.setText(String.valueOf(finishedGoodList.get(i).getStock()));
					//tempTf.setText(String.valueOf(forecastList.get(forecastDateCombo.getSelectionModel().getSelectedIndex()).getSingleGood(i)));
					tempTf.getText();
					finishedGoodTF.add(tempTf);
				
					GridPane.setConstraints(tempLabel, 0, gridSpot);
					GridPane.setColumnSpan(tempLabel, 1);
					grid.getChildren().add(tempLabel);
				
					GridPane.setConstraints(tempTf, 1, gridSpot++);
					GridPane.setColumnSpan(tempTf, 1);
					grid.getChildren().add(tempTf);
					//count = i;
				}
				
				grid.getChildren().add(calibrate);
				
		    }
		});
		
		
/*
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				newItem.setCount(Integer.parseInt(amount.getText()));
				newItem.setItemName(name.getText());

				ItemModel.somethingChanged("AddData");

				amount.setText("");
				name.setText("");
			}
		};

		submit.setOnAction(event);
	*/	
		// set up the layout
		mLabel = new Label(); 
		mLabel.setText("Add an Item");

		setPadding(new Insets(10, 10, 10, 10));
		setSpacing(10); // Horizontal gap in pixels
		
		this.getChildren().addAll(mLabel,scroll,label);

	} // end of constructor
	
	
	public ItemModel getItem()
	{
		return newItem;
	}
	

	// --- Data Members ---
	private Label 				mLabel;
	
	
	public void addFinishedGood(String filepath, ArrayList<FinishedGood> finishedGoods) {
		
		
		ArrayList<String> finishedGoodOrderedName = new ArrayList<String>();
		
		
		for(int i = 0; i < finishedGoods.size(); i++) {
			finishedGoodOrderedName.add(finishedGoods.get(i).getName());
		}
		
		Collections.sort(finishedGoodOrderedName);
		
		
		String tempFile = "temp.csv";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		
		try
		{
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			PrintWriter pw = new PrintWriter(bw);

			for(int i = 0; i < finishedGoodOrderedName.size(); i++)
			{
				for(int j = 0; j < finishedGoods.size(); j++) {
					if(finishedGoodOrderedName.get(i).equals(finishedGoods.get(j).getName())) {
						fw.append(finishedGoods.get(j).getName() + "," + finishedGoods.get(j).getStock() + "," + finishedGoods.get(j).getPrice() + "," + finishedGoods.get(j).getLeadTime() + "\n");
					}
				}
			}

			br.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
	
		}
		catch(Exception e3)
		{
			
			
		}
		
	}
	
	public void addFinishedGoodMaterials(String filepath, ArrayList<FinishedGood> finishedGoods) {
		
		ArrayList<String> finishedGoodOrderedName = new ArrayList<String>();
		
		
		for(int i = 0; i < finishedGoods.size(); i++) {
			finishedGoodOrderedName.add(finishedGoods.get(i).getName());
		}
		
		Collections.sort(finishedGoodOrderedName);
		
		String tempFile = "temp.csv";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		
		try
		{
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			PrintWriter pw = new PrintWriter(bw);

			for(int i = 0; i < finishedGoodOrderedName.size(); i++)
			{
				for(int j = 0; j < finishedGoods.size(); j++) {
					if(finishedGoodOrderedName.get(i).equals(finishedGoods.get(j).getName())) {
						fw.append(finishedGoods.get(j).toStringMaterials());
					}
				}
			}

			br.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
	
		}
		catch(Exception e3)
		{
			
			
		}
		
	}
	
	
	
	public void addFinishedGoodMaterialUsages(String filepath, ArrayList<FinishedGood> finishedGoods) {
		
		
		ArrayList<String> finishedGoodOrderedName = new ArrayList<String>();
		
		
		for(int i = 0; i < finishedGoods.size(); i++) {
			finishedGoodOrderedName.add(finishedGoods.get(i).getName());
		}
		
		Collections.sort(finishedGoodOrderedName);
		
		
		String tempFile = "temp.csv";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		
		try
		{
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			PrintWriter pw = new PrintWriter(bw);

			for(int i = 0; i < finishedGoodOrderedName.size(); i++)
			{
				for(int j = 0; j < finishedGoods.size(); j++) {
					if(finishedGoodOrderedName.get(i).equals(finishedGoods.get(j).getName())) {
						fw.append(finishedGoods.get(j).toStringMaterialUsages());
					}
				}
			}

			br.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
	
		}
		catch(Exception e3)
		{
			
			
		}
		
	}
	
	
	
	
	
	public void editInventory(String filepath, ArrayList<TextField> finishedGoodNumbers)
	{
		ArrayList<String> rest = new ArrayList<String>();
		ArrayList<String> name = new ArrayList<String>();
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath)); 
				while ((line = br.readLine()) != null) {
					// use comma as separator 
					String[] cols = line.split(","); 
					//System.out.println("Coulmn 0= " + cols[0]);
					//if(cols[0].compareTo("") != 0) {
					name.add(cols[0]);
					//}
				}
				br.close();
			} catch (IOException e3) {
				// 
				e3.printStackTrace();
			}
		
		
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath)); 
				while ((line = br.readLine()) != null) { 
					//delete first two words and commas
					StringBuilder sb = new StringBuilder(line);
					sb.deleteCharAt(sb.indexOf(","));
					line = line.substring(sb.indexOf(",")+1);
				    rest.add(line);
				}
				br.close();
			} catch (IOException e3) {

				e3.printStackTrace();
			} 
		
		String tempFile = "temp.csv";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		int count = 0;
		
		try
		{
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			PrintWriter pw = new PrintWriter(bw);

			line = br.readLine();
			while(line != null)
			{
				fw.append(name.get(count) + "," + finishedGoodNumbers.get(count).getText() + rest.get(count) + "\n");

				line = br.readLine();
				count++;
			}

			br.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
	
		}
		catch(Exception e3)
		{
			
			
		}
		
		
	}
	
	
	
	

}