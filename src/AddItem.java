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


public class AddItem extends VBox
{
	//private ObservableList<ItemModel> oList = FXCollections.<ItemModel>observableArrayList();
	
	private ArrayList<FinishedGood> finishedGoodList = new ArrayList<FinishedGood>();
	private ArrayList<RawMaterial> rawMaterialList = new ArrayList<RawMaterial>();
	
	private ArrayList<TextField> rawMaterialTF = new ArrayList<TextField>();
	
	private ArrayList<String> rawMaterialName = new ArrayList<String>();
	
	private ItemModel newItem = new ItemModel();
	private String fileAddress = "C:\\OneFunInvManSys\\rawMaterial.csv";
	private String csvFileRaw = "C:\\OneFunInvManSys\\rawMaterial.csv";
	//private String fileAddress = "C:\\Users\\Ian\\eclipse-workspace\\InventoryTracking2\\rawMaterial.csv";
	
	private int gridSpot = 0;
	
	// constructor
	public AddItem()
	{
		Font headerFont = Font.font("Verdana", FontWeight.BOLD, 16);
		
		new UpdateInfo(rawMaterialList,finishedGoodList);

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		
		Button createRawMaterial = new Button("Create new Raw Material");
		GridPane.setConstraints(createRawMaterial,0,gridSpot++);
		grid.getChildren().add(createRawMaterial);
		
		
		
		/*
		//Defining the Name text field
		final TextField name = new TextField();
		name.setPromptText("Name of new Material");
		name.setPrefColumnCount(10);
		name.getText();
		GridPane.setConstraints(name, 0, 0);
		grid.getChildren().add(name);
		//Defining the Last Name text field
		final TextField stock = new TextField();
		stock.setPromptText("Enter stock");
		GridPane.setConstraints(stock, 0, 1);
		grid.getChildren().add(stock);
		//Defining the Submit button
		Button submit = new Button("Submit");
		GridPane.setConstraints(submit, 1, 0);
		grid.getChildren().add(submit);
		//Defining the Clear button
		Button clear = new Button("Clear");
		GridPane.setConstraints(clear, 1, 1);
		grid.getChildren().add(clear);
		*/
		
		TextField name = new TextField();
		TextField stock = new TextField();
		TextField leadTime = new TextField();
		TextField quantity = new TextField();
		TextField amount8oz = new TextField();
		
		Button submit = new Button("Submit");
		Button clear = new Button("Cancel");
		
		//Adding a Label
		final Label label = new Label();
		GridPane.setConstraints(label, 0, 3);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);
		
		//Add label for reviewing finished good
		Label title1 = new Label();
		title1.setText("Calibrate Raw Materials:");
		title1.setFont(headerFont);
		GridPane.setConstraints(title1, 0, gridSpot++);
		GridPane.setColumnSpan(title1, 2);
		grid.getChildren().add(title1);

		for(int i = 0; i < rawMaterialList.size(); i++) {
		Label tempLabel = new Label();
		tempLabel.setText(rawMaterialList.get(i).getName() + ": ");
		//finNameLabel.add(tempLabel);
		
		TextField tempTf = new TextField();
		tempTf.setPromptText("Enter Value");
		tempTf.setPrefColumnCount(10);
		tempTf.setText(String.valueOf(rawMaterialList.get(i).getStock()));
		//tempTf.setText(String.valueOf(forecastList.get(forecastDateCombo.getSelectionModel().getSelectedIndex()).getSingleGood(i)));
		tempTf.getText();
		rawMaterialTF.add(tempTf);
		
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
		
		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setContent(grid);
		
		Label successLabel = new Label();
		successLabel.setText("Calibration Succeeded!");
		GridPane.setConstraints(successLabel, 1, gridSpot);
		
		createRawMaterial.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				grid.getChildren().clear();
				
				//Defining the Name text field
				name.setPromptText("Name of new Material");
				GridPane.setConstraints(name, 0, 0);
				grid.getChildren().add(name);
				
				gridSpot = 1;
				//Defining the price text field
				stock.setPromptText("Enter Stock");
				GridPane.setConstraints(stock, 0, gridSpot++);
				grid.getChildren().add(stock);
				//Defining the leadTime text field
				leadTime.setPromptText("Enter Lead Time");
				GridPane.setConstraints(leadTime, 0, gridSpot++);
				grid.getChildren().add(leadTime);
				//Defining the amount text field
				quantity.setPromptText("Enter Order Quantity");
				GridPane.setConstraints(quantity, 0, gridSpot++);
				grid.getChildren().add(quantity);
				//Defining the price text field
				amount8oz.setPromptText("Amount used in an 8oz");
				GridPane.setConstraints(amount8oz, 0, gridSpot++);
				grid.getChildren().add(amount8oz);
				
				
				
				
				//Defining the Submit button
				GridPane.setConstraints(submit, 1, 0);
				grid.getChildren().add(submit);
				//Defining the Clear button
				GridPane.setConstraints(clear, 2, 0);
				GridPane.setColumnSpan(clear, 2);
				grid.getChildren().add(clear);
		
			}
		});
		

		//Setting action for the calibrate button
		calibrate.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				editInventory(fileAddress,rawMaterialTF);
				grid.getChildren().add(successLabel);
			}
		});
		
		//Setting an action for the Submit button
		submit.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

		@Override
		    public void handle(ActionEvent e) {
		        if ((!name.getText().isEmpty() && !stock.getText().isEmpty() && !leadTime.getText().isEmpty() && !quantity.getText().isEmpty() && !amount8oz.getText().isEmpty())) {
		            label.setText(name.getText() + ": " + stock.getText());
		        
		            RawMaterial newMaterial = new RawMaterial();
		            newMaterial.setName(name.getText());
		            newMaterial.setStock(Integer.parseInt(stock.getText()));
		            newMaterial.setLeadTime(Integer.parseInt(leadTime.getText()));
		            newMaterial.setOrderQuantity(Integer.parseInt(quantity.getText()));
		            newMaterial.setAmount8oz(Double.parseDouble(amount8oz.getText()));
		            
		            //for(int i = 0; i < rawMaterialList.size(); i++) {
		            //	rawMaterialName.add(rawMaterialList.get(i).getName());
		            //}
		            
		            //rawMaterialName.add(newMaterial.getName());
		            
		            rawMaterialList.add(newMaterial);
		            
		            
		            addRawMaterial(csvFileRaw,rawMaterialList);
		            
		            new UpdateInfo(rawMaterialList,finishedGoodList);
		            
		            
					grid.getChildren().clear();
					
					grid.getChildren().add(createRawMaterial);
		            
		    		//Add label for reviewing finished good
		    		Label title1 = new Label();
		    		title1.setText("Calibrate Raw Materials:");
		    		title1.setFont(headerFont);
		    		GridPane.setConstraints(title1, 0, gridSpot++);
		    		GridPane.setColumnSpan(title1, 2);
		    		grid.getChildren().add(title1);

		    		for(int i = 0; i < rawMaterialList.size(); i++) {
		    		Label tempLabel = new Label();
		    		tempLabel.setText(rawMaterialList.get(i).getName() + ": ");
		    		//finNameLabel.add(tempLabel);
		    		
		    		TextField tempTf = new TextField();
		    		tempTf.setPromptText("Enter Value");
		    		tempTf.setPrefColumnCount(10);
		    		tempTf.setText(String.valueOf(rawMaterialList.get(i).getStock()));
		    		//tempTf.setText(String.valueOf(forecastList.get(forecastDateCombo.getSelectionModel().getSelectedIndex()).getSingleGood(i)));
		    		tempTf.getText();
		    		rawMaterialTF.add(tempTf);
		    		
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
		            
		            
		            
		        }
		        else {
		        	label.setText("One or more fields are empty");
		        }
			}
		 });
		 
		//Setting an action for the Clear button
		clear.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		    public void handle(ActionEvent e) {

			grid.getChildren().clear();
			
			grid.getChildren().add(createRawMaterial);
			
			
			gridSpot = 2;
			
			//Add label for reviewing finished good
			grid.getChildren().add(title1);

			for(int i = 0; i < rawMaterialList.size(); i++) {
			Label tempLabel = new Label();
			tempLabel.setText(rawMaterialList.get(i).getName() + ": ");
			//finNameLabel.add(tempLabel);
			
			TextField tempTf = new TextField();
			tempTf.setPromptText("Enter Value");
			tempTf.setPrefColumnCount(10);
			tempTf.setText(String.valueOf(rawMaterialList.get(i).getStock()));
			//tempTf.setText(String.valueOf(forecastList.get(forecastDateCombo.getSelectionModel().getSelectedIndex()).getSingleGood(i)));
			tempTf.getText();
			rawMaterialTF.add(tempTf);
			
			GridPane.setConstraints(tempLabel, 0, gridSpot);
			GridPane.setColumnSpan(tempLabel, 1);
			grid.getChildren().add(tempLabel);
			
			GridPane.setConstraints(tempTf, 1, gridSpot++);
			GridPane.setColumnSpan(tempTf, 1);
			grid.getChildren().add(tempTf);
			//count = i;
		}
			
			grid.getChildren().add(calibrate);
			
			label.setText("");
			
			
			
		    }
		});
		
		
/*
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				newItem.setCount(Integer.parseInt(stock.getText()));
				newItem.setItemName(name.getText());

				ItemModel.somethingChanged("AddData");

				stock.setText("");
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
	
	
	public void addRawMaterial(String filepath, ArrayList<RawMaterial> rawMaterials) {
		
		ArrayList<String> rawMaterialOrderedName = new ArrayList<String>();
		
		
		for(int i = 0; i < rawMaterials.size(); i++) {
			rawMaterialOrderedName.add(rawMaterials.get(i).getName());
		}
		
		Collections.sort(rawMaterialOrderedName);
		
		String tempFile = "temp.csv";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		
		try
		{
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			PrintWriter pw = new PrintWriter(bw);
			
			for(int i = 0; i < rawMaterialOrderedName.size(); i++) {
				for(int j = 0; j < rawMaterials.size(); j++)
				{
					if(rawMaterialOrderedName.get(i).equals(rawMaterials.get(j).getName())) {
						fw.append(rawMaterials.get(j).getName() + "," + rawMaterials.get(j).getStock() + "," + rawMaterials.get(j).getLeadTime() + "," + rawMaterials.get(j).getOrderQuantity() + "," + rawMaterials.get(j).getAmount8oz() + "\n");
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