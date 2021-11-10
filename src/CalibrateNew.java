//package application;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.Reader;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//import java.util.Collections;

//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
//import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
//import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class CalibrateNew extends VBox
{
	//private ObservableList<ItemModel> oList = FXCollections.<ItemModel>observableArrayList();
	
	private String csvFinAddress = "C:\\OneFunInvManSys\\finishedGood.csv";
	private String csvFileRaw = "C:\\OneFunInvManSys\\rawMaterial.csv";
	//private String csvFileAssoc = "C:\\OneFunInvManSys\\associatedTable.csv";
	//private String csvFileAssocNum = "C:\\OneFunInvManSys\\associatedNumbers.csv";
	private String csvFileBox = "C:\\OneFunInvManSys\\box.csv";
	private String csvFileSKU = "C:\\OneFunInvManSys\\skuData.csv";
	
	private ArrayList<RawMaterial> rawMaterialList = new ArrayList<RawMaterial>();
	private ArrayList<FinishedGood> finishedGoodList = new ArrayList<FinishedGood>();
	private ArrayList<String> skuList = new ArrayList<String>();

	private ArrayList<Box> boxList = new ArrayList<Box>();
	
	private ItemModel newItem = new ItemModel();

	private String line = "";
	
	private int startFrom = 0;
	//private int startFromRaw = 0;
	//private String skuName = "";
	//private int skuCount = 0;
	//private String rName = "";
	//private int rCount = 0;
	//private String barColor = "";
	private int finIndex = -1;
	private int rawIndex = -1;
	private int finIndex2 = -1;
	
	//private Scanner x;
	
	
	// constructor
	public CalibrateNew()
	{
		ComboBox<String> comBox = new ComboBox<String>();
		ComboBox<String> comBoxRaw = new ComboBox<String>();
		ComboBox<String> comBoxFin = new ComboBox<String>();
		
		//Updates ArrayLists defined in arguments
		new UpdateInfo(rawMaterialList,finishedGoodList, boxList);
		
		//Updates skuList
		BufferedReader br = new BufferedReader(new FileReader(csvFileSKU));
		while ((line = br.readLine()) != null) {
			String[] cols = line.split(",");
			skuList.add(cols[0]);
		}
		br.close();

		for(int i = 0; i < finishedGoodList.size(); i++)
		{
			comBox.getItems().add(finishedGoodList.get(i).getName());
			comBoxFin.getItems().add(finishedGoodList.get(i).getName());
			startFrom = i;
		}
			
		
		
		for(int i = 0; i < rawMaterialList.size(); i++)
		{
			comBoxRaw.getItems().add(rawMaterialList.get(i).getName());
			//startFromRaw = i;
		}
		
		//startFromRaw = rawName.size();
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		
		//Defining the finished goods made today
		final TextField name = new TextField();
		name.setPromptText("Name of new Material");
		name.setPrefColumnCount(10);
		name.getText();
		GridPane.setConstraints(name, 0, 0);
		GridPane.setConstraints(comBox, 0, 0);
		grid.getChildren().add(comBox);
		//Defining the Last Name text field
		final TextField amount = new TextField();
		amount.setPromptText("# (Goods Made)");
		GridPane.setConstraints(amount, 0, 1);
		grid.getChildren().add(amount);
		//Defining the Submit button
		Button submit = new Button("Submit");
		GridPane.setConstraints(submit, 1, 0);
		grid.getChildren().add(submit);
		//Defining the Clear button
		Button clear = new Button("Clear");
		GridPane.setConstraints(clear, 1, 1);
		grid.getChildren().add(clear);
		//Drafting update button
		/*Button update = new Button("Update");
		GridPane.setConstraints(update, 4, 4);
		grid.getChildren().add(update);
		*/
		
		//Adding a error Label
		final Label label = new Label();
		GridPane.setConstraints(label, 0, 3);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);
		
		
		
		//Defining the raw goods received
		final TextField nameRaw = new TextField();
		nameRaw.setPromptText("Name of new Material");
		nameRaw.setPrefColumnCount(10);
		nameRaw.getText();
		//GridPane.setConstraints(nameRaw, 5, 0);
		GridPane.setConstraints(comBoxRaw, 5, 0);
		grid.getChildren().add(comBoxRaw);
		//Defining the Last Name text field
		final TextField amountRaw = new TextField();
		amountRaw.setPromptText("# (Raw Material Delivery)");
		GridPane.setConstraints(amountRaw, 5, 1);
		grid.getChildren().add(amountRaw);
		//Defining the Submit button
		Button submitRaw = new Button("Submit");
		GridPane.setConstraints(submitRaw, 6, 0);
		grid.getChildren().add(submitRaw);
		//Defining the Clear button
		Button clearRaw = new Button("Clear");
		GridPane.setConstraints(clearRaw, 6, 1);
		grid.getChildren().add(clearRaw);
		//Drafting update button
		//Button updateRaw = new Button("Update");
		//GridPane.setConstraints(updateRaw, 9, 4);
		//grid.getChildren().add(updateRaw);
		
		
		
		//Defining the finished goods being sent out
		final TextField nameFin = new TextField();
		nameFin.setPromptText("Name of new Material");
		nameFin.setPrefColumnCount(10);
		nameFin.getText();
		//GridPane.setConstraints(nameFin, 0, 0);
		GridPane.setConstraints(comBoxFin, 7, 0);
		grid.getChildren().add(comBoxFin);
		//Defining the Last Name text field
		final TextField amountFin = new TextField();
		amountFin.setPromptText("# (Finished Goods Sent)");
		GridPane.setConstraints(amountFin, 7, 1);
		grid.getChildren().add(amountFin);
		//Defining the Submit button
		Button submitFin = new Button("Submit");
		GridPane.setConstraints(submitFin, 8, 0);
		grid.getChildren().add(submitFin);
		//Defining the Clear button
		Button clearFin = new Button("Clear");
		GridPane.setConstraints(clearFin, 8, 1);
		grid.getChildren().add(clearFin);
		//Drafting update button
		//Button updateFin = new Button("Update");
		//GridPane.setConstraints(updateFin, 9, 4);
		//grid.getChildren().add(updateFin);
		
		
		//Add browse button for materials sent that day
		Button browse = new Button("Browse");
		GridPane.setConstraints(browse,3,1);
		grid.getChildren().add(browse);
		Label browseString = new Label();
		browseString.setText("Chosen: Nothing");
		GridPane.setColumnSpan(browseString, 4);
		GridPane.setConstraints(browseString,3,2);
		grid.getChildren().add(browseString);
		
		
		
		//Finished goods chart
		CategoryAxis xaxis = new CategoryAxis();
		Axis<Number> yaxis = new NumberAxis(0,500,50);
		xaxis.setLabel("Product");
		yaxis.setLabel("Number of Units");
		
		BarChart<String,Number> bar = new BarChart<String,Number>(xaxis,yaxis);
		bar.setTitle("Finished goods Inventory");
		
		//Comparing finished goods to each other and creating groups for them based off there first raw material
		ArrayList<ArrayList<RawMaterial>> rawMaterialsXAxis = new ArrayList<ArrayList<RawMaterial>>();
		int m = 0;

		//create an instance of rawMaterialXAxis
		rawMaterialsXAxis.add(new ArrayList<RawMaterial>());
		//Add all raw materials into the instance
		for(int k = 0; k < finishedGoodList.get(0).getMaterialList().size(); k++) {
			rawMaterialsXAxis.get(m).add(finishedGoodList.get(0).getMaterialList().get(k));
		}
		
		for(int i = 0; i < finishedGoodList.size() - 1; i++) {
			//while the first for each materials is the same
			if(finishedGoodList.get(i).getMaterialList().get(0).equals(finishedGoodList.get(i+1).getMaterialList().get(0))) {
				//add all the materials into the instance that are not already in there
				for(int j = 0; j < finishedGoodList.get(i+1).getMaterialList().size(); j++) {
					if(rawMaterialsXAxis.get(m).contains(finishedGoodList.get(i+1).getMaterialList().get(j))) {}
					else {
						rawMaterialsXAxis.get(m).add(finishedGoodList.get(i+1).getMaterialList().get(j));
					}
				}
			}
			else {
				//create an instance of rawMaterialXAxis
				rawMaterialsXAxis.add(new ArrayList<RawMaterial>());
				m++;
				//Add all raw materials into the instance
				for(int k = 0; k < finishedGoodList.get(i+1).getMaterialList().size(); k++) {
					rawMaterialsXAxis.get(m).add(finishedGoodList.get(i+1).getMaterialList().get(k));
				}
			}	
		}
		
		XYChart.Series<String,Number> series = buildFinishedChart(finishedGoodList);
		
		/*
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		
		for(int i = 0; i < finishedGoodList.size(); i++) {
			series.getData().add(new XYChart.Data<String, Number>(finishedGoodList.get(i).getName(),finishedGoodList.get(i).getStock()));
		}
		*/
		
	    //Populate the bar chart
		bar.setLegendVisible(false);
		bar.getData().add(series);
		
		
		for (XYChart.Series<String, Number> series1 : bar.getData()) {
		    for (XYChart.Data<String, Number> data : series1.getData()) {
		    	data.getNode().setOnMousePressed((MouseEvent event) -> {
		            System.out.println("you clicked " + data.toString() + series1.toString());
		        });
		        Tooltip.install(data.getNode(), new Tooltip(data.getXValue() + ":\n" + data.getYValue()));
		    }
		}
		
		
		

		
		
		//Node n = bar.lookup(".data0.chart-bar");
	    //n.setStyle("-fx-bar-fill: blue");
	    //n = bar.lookup(".data1.chart-bar");
	    //n.setStyle("-fx-bar-fill: blue");
		
		
		GridPane.setConstraints(bar, 0, 5);
		GridPane.setColumnSpan(bar, 2);
		//GridPane.setRowSpan(bar, 4);
		grid.getChildren().add(bar);
		
		
		//Raw materials chart
		CategoryAxis xRawAxis = new CategoryAxis();
		NumberAxis yRawAxis = new NumberAxis(0,1000,100);
		xRawAxis.setLabel("Raw Materials");
		yRawAxis.setLabel("Number of Units");
		
		xRawAxis.setAnimated(false);
		
		BarChart<String,Number> bar2 = new BarChart<String,Number>(xRawAxis,yRawAxis);
		bar2.setTitle("Raw Materials Inventory");
		bar2.setMinWidth(700);
		
		XYChart.Series<String,Number> series2 = buildRawChart(rawMaterialList);

		
		//Populate the bar chart
		bar2.setLegendVisible(false);
		bar2.getData().add(series2);
		
		for (XYChart.Series<String, Number> serie : bar2.getData()) {
		    for (XYChart.Data<String, Number> item : serie.getData()) {
		        //item.getNode().setOnMousePressed((MouseEvent event) -> {
		            //System.out.println("you clicked " + item.toString() + serie.toString());
		        //});
		        Tooltip.install(item.getNode(), new Tooltip(item.getXValue() + ":\n" + item.getYValue()));
		    }
		}
			
		
		ArrayList<Button> rawChartButtons = new ArrayList<Button>();
		for(int i = 0; i < rawMaterialsXAxis.size();i++) {
			final int num = i;
			Button tempSubmit = new Button(rawMaterialsXAxis.get(i).get(0).getName());
			GridPane.setConstraints(tempSubmit, 0, 20+i);
			grid.getChildren().add(tempSubmit);
			rawChartButtons.add(tempSubmit);
			
			rawChartButtons.get(i).setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
				    public void handle(ActionEvent e5) {
				    bar2.getData().clear();
				    bar2.getData().add(buildRawChart(rawMaterialsXAxis.get(num)));
				    
		        	//toolTip bar2
		    		for (XYChart.Series<String, Number> serie : bar2.getData()) {
		    		    for (XYChart.Data<String, Number> item : serie.getData()) {
		    		        //item.getNode().setOnMousePressed((MouseEvent event) -> {
		    		            //System.out.println("you clicked " + item.toString() + serie.toString());
		    		        //});
		    		        Tooltip.install(item.getNode(), new Tooltip(item.getXValue() + ":\n" + item.getYValue()));
		    		    }
		    		}
				}
			});
		}
			
		
		
		
		//Node n2 = bar.lookup(".data0.chart-bar");
	    //n2.setStyle("-fx-bar-fill: green");
	    
	    
	    //grid.setHgap(50);
	    
		
		GridPane.setConstraints(bar2, 2, 5);
		GridPane.setColumnSpan(bar2, 6);
		GridPane.setRowSpan(bar2, 12);
		grid.getChildren().add(bar2);
		
		//setting action for browse button
		browse.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				FileChooser chooser = new FileChooser();
	            File file = chooser.showOpenDialog(new Stage());
	            if (file != null) {
	                String fileAsString = file.toString();

	                browseString.setText("Chosen: " + fileAsString);
	                
	                int countBox = 0;
					int countSKU = 0;
	                boolean found = false;
	                
	                try {
	                	BufferedReader br = new BufferedReader(new FileReader(fileAsString));
	                	line = br.readLine();
	                	while ((line = br.readLine()) != null) {
	                		countBox = 0;
	                		found = false;
	                		String[] cols = line.split(",");
	                		
	                		while(countBox < boxList.size() && found == false) {
	                			if(boxList.get(countBox).getName().compareTo(cols[0]) == 0) {
	                				boxList.get(countBox).removeOneFromInventory();
	                				found = true;
	                			}
	                			countBox++;
	                		}
	                	}
	                	br.close();
	                	
	                	editBox(csvFileBox,boxList);
	                }
	                catch(IOException e3){
	                	e3.printStackTrace();
	                }

					try{
						BufferedReader br = new BufferedReader(new FileReader(fileAsString));
	                	line = br.readLine();
						while ((line = br.readLine()) != null) {
							countSKU = 0;
							found = false;

							String[] cols = line.split(",");

							while(line != null && found == false){
								if(skuList.get(countSKU).compareTo(cols[0]) == 0){
									//TODO: add changes to inventory
								}
							}

						}


						br.close();
					}
					catch(Exception e3){
	                	e3.printStackTrace();
	                }
	                
	            } else {
	            	browseString.setText(null);
	            }
	            
	            
			}
		});
		
		
		//Setting an action for the Submit button
		submit.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

		@Override
		    public void handle(ActionEvent e) {
		        if ((!amount.getText().isEmpty() && comBox.getValue() != null)) {
		            amount.getText();
		            
		            try {
		            	int count = 0;
		            	while (count  < finishedGoodList.size()) {
		            		if(finishedGoodList.get(count).getName().equals(comBox.getValue())) {
		            			finIndex = count;
		            			break;
		            		}
		            		count++;
		            	}
		            	String upFinName = finishedGoodList.get(finIndex).getName();
		            	int oldCount = finishedGoodList.get(finIndex).getStock();
		            	int changeInCount = Integer.parseInt(amount.getText());
		            	int newFinCount = oldCount + changeInCount;
		            	String newFinCountStr = String.valueOf(newFinCount);
		            	
		            	editInvintory(csvFinAddress, newFinCountStr, finIndex, upFinName);
		            	
		            	
		            	ArrayList<String> rawTotal = new ArrayList<String>();
		            	
		            	ArrayList<Integer> rawCountUsed = new ArrayList<Integer>();

		            	
		            	for(int i = 0; i < finishedGoodList.get(finIndex).getMaterialList().size(); i++)
		            	{
		            		for(int j = 0; j < rawMaterialList.size(); j++)
		            		{
		            			if((finishedGoodList.get(finIndex).getMaterialList().get(i).getName()).equals(rawMaterialList.get(j).getName()))
		            			{
		            				rawCountUsed.add(j);
		            				break;
		            			}
		            		}
		            	}
		            	
		            	double newRawTotal;
		            	String newRawTotalStr;
		            	
		            	for (int i = 0; i < rawCountUsed.size(); i++)
		            	{
		            		//calculate the raw material amount
		            		//TODO: fix calculation
		            		newRawTotal = (rawMaterialList.get(rawCountUsed.get(i)).getStock() - (changeInCount * finishedGoodList.get(finIndex).getMaterialsUsedNumber().get(i)));
		            		newRawTotalStr = String.valueOf(newRawTotal);
		            		rawTotal.add(newRawTotalStr);
		            	}
		            	
		            	//Collections.sort(rawCountUsed);
		            	
		            	editRaw(csvFileRaw, rawTotal, rawCountUsed, finishedGoodList.get(finIndex).getMaterialList());
		            	
		            	name.setText("");
		            	amount.setText("");
		            	label.setText("");
		            }
		            catch(Exception e1) {
		            	label.setText("Must enter an integer in second text box");
		            }
		        } else {
		            label.setText("One or more fields are empty");
		        }
		        
		        //Update
		        new UpdateInfo(rawMaterialList, finishedGoodList);
		        
		        
		        
		        	for(int i = startFrom + 1; i < finishedGoodList.size(); i++)
					{
						comBox.getItems().add(finishedGoodList.get(i).getName());
						startFrom = i;
					}

		        	//Update Charts
		        	bar.getData().clear();
		        	bar.getData().add(buildFinishedChart(finishedGoodList));
						
		        	
		        	bar2.getData().clear();
		        	bar2.getData().add(buildRawChart(rawMaterialList));
		        	
		        	//toolTip bar
		    		for (XYChart.Series<String, Number> serie : bar.getData()) {
		    		    for (XYChart.Data<String, Number> item : serie.getData()) {
		    		        //item.getNode().setOnMousePressed((MouseEvent event) -> {
		    		            //System.out.println("you clicked " + item.toString() + serie.toString());
		    		        //});
		    		        Tooltip.install(item.getNode(), new Tooltip(item.getXValue() + ":\n" + item.getYValue()));
		    		    }
		    		}
		        	
		        	//toolTip bar2
		    		for (XYChart.Series<String, Number> serie : bar2.getData()) {
		    		    for (XYChart.Data<String, Number> item : serie.getData()) {
		    		        //item.getNode().setOnMousePressed((MouseEvent event) -> {
		    		            //System.out.println("you clicked " + item.toString() + serie.toString());
		    		        //});
		    		        Tooltip.install(item.getNode(), new Tooltip(item.getXValue() + ":\n" + item.getYValue()));
		    		    }
		    		}
					
					//End of update in submit
						
		     }
		 });
		
		
		
		
		
		
		//Adding to raw Materials Inventory from a delivery
		submitRaw.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

		@Override
		    public void handle(ActionEvent e) {
		        if ((!amountRaw.getText().isEmpty() && comBoxRaw.getValue() != null)) {
		            amountRaw.getText();
		            
		            try {
		            	int count = 0;
		            	while (count  < rawMaterialList.size()) {
		            		if(rawMaterialList.get(count).getName().equals(comBoxRaw.getValue())) {
		            			rawIndex = count;
		            			break;
		            		}
		            		count++;
		            	}
		            	String upRawName = rawMaterialList.get(rawIndex).getName();
		            	double oldCount = rawMaterialList.get(rawIndex).getStock();
		            	double changeInCount = Integer.parseInt(amountRaw.getText());
		            	int newRawCount = (int)(oldCount + changeInCount);
		            	String newRawCountStr = String.valueOf(newRawCount);
		            	
		            	editInvintory(csvFileRaw, newRawCountStr, rawIndex, upRawName);
		            	
		            	nameRaw.setText("");
		            	amountRaw.setText("");
		            	label.setText("");
		            }
		            catch(Exception e1) {
		            	label.setText("Must enter an integer in second text box");
		            }
		        } else {
		            label.setText("One or more fields are empty");
		        }
		        
		        //Update
		        new UpdateInfo(rawMaterialList, finishedGoodList);
		        

					for(int i = startFrom + 1; i < finishedGoodList.size(); i++)
					{
						comBox.getItems().add(finishedGoodList.get(i).getName());
						startFrom = i;
					}
					
					//Update Charts
		        	bar.getData().clear();
		        	bar.getData().add(buildFinishedChart(finishedGoodList));
		        	
		        	bar2.getData().clear();
		        	bar2.getData().add(buildRawChart(rawMaterialList));
		        	
		        	//toolTip bar
		    		for (XYChart.Series<String, Number> serie : bar.getData()) {
		    		    for (XYChart.Data<String, Number> item : serie.getData()) {
		    		        //item.getNode().setOnMousePressed((MouseEvent event) -> {
		    		            //System.out.println("you clicked " + item.toString() + serie.toString());
		    		        //});
		    		        Tooltip.install(item.getNode(), new Tooltip(item.getXValue() + ":\n" + item.getYValue()));
		    		    }
		    		}
		        	
		        	//toolTip bar2
		    		for (XYChart.Series<String, Number> serie : bar2.getData()) {
		    		    for (XYChart.Data<String, Number> item : serie.getData()) {
		    		        //item.getNode().setOnMousePressed((MouseEvent event) -> {
		    		            //System.out.println("you clicked " + item.toString() + serie.toString());
		    		        //});
		    		        Tooltip.install(item.getNode(), new Tooltip(item.getXValue() + ":\n" + item.getYValue()));
		    		    }
		    		}
				
					//End of Update in submitRaw  
		        
		     }
		 });
		//end of raw material inventory submit
		
		
		
		//Removing from Finished goods Inventory from selling
		submitFin.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

		@Override
		    public void handle(ActionEvent e) {
		        if ((!amountFin.getText().isEmpty() && comBoxFin.getValue() != null)) {
		            amountFin.getText();
		            
		            try {
		            	int count = 0;
		            	while (count  < finishedGoodList.size()) {
		            		if(finishedGoodList.get(count).getName().equals(comBoxFin.getValue())) {
		            			finIndex2 = count;
		            			break;
		            		}
		            		count++;
		            	}
		            	
		            	String upFinName = finishedGoodList.get(finIndex2).getName();
		            	int oldCount = finishedGoodList.get(finIndex2).getStock();
		            	int changeInCount = Integer.parseInt(amountFin.getText());
		            	int newFinCount = oldCount - changeInCount;
		            	String newFinCountStr = String.valueOf(newFinCount);
		            	
		            	editInvintory(csvFinAddress, newFinCountStr, finIndex2, upFinName);
		            	
		            	nameFin.setText("");
		            	amountFin.setText("");
		            	label.setText("");
		            }
		            catch(Exception e1) {
		            	label.setText("Must enter an integer in second text box");
		            }
		        } else {
		            label.setText("One or more fields are empty");
		        }
		        
		        
		        //update
		        new UpdateInfo(rawMaterialList, finishedGoodList);
		        
		        
					
					for(int i = startFrom + 1; i < finishedGoodList.size(); i++)
					{
						comBox.getItems().add(finishedGoodList.get(i).getName());
						startFrom = i;
					}

					
					//Update Charts
		        	bar.getData().clear();
		        	bar.getData().add(buildFinishedChart(finishedGoodList));
		        	
		        	bar2.getData().clear();
		        	bar2.getData().add(buildRawChart(rawMaterialList));
		        	
		        	//toolTip bar
		    		for (XYChart.Series<String, Number> serie : bar.getData()) {
		    		    for (XYChart.Data<String, Number> item : serie.getData()) {
		    		        //item.getNode().setOnMousePressed((MouseEvent event) -> {
		    		            //System.out.println("you clicked " + item.toString() + serie.toString());
		    		        //});
		    		        Tooltip.install(item.getNode(), new Tooltip(item.getXValue() + ":\n" + item.getYValue()));
		    		    }
		    		}
		        	
		        	//toolTip bar2
		    		for (XYChart.Series<String, Number> serie : bar2.getData()) {
		    		    for (XYChart.Data<String, Number> item : serie.getData()) {
		    		        //item.getNode().setOnMousePressed((MouseEvent event) -> {
		    		            //System.out.println("you clicked " + item.toString() + serie.toString());
		    		        //});
		    		        Tooltip.install(item.getNode(), new Tooltip(item.getXValue() + ":\n" + item.getYValue()));
		    		    }
		    		}
		        	
					//End of update in submitFin
		        
		        
		     }
		 });
		//end of raw material inventory submit
		
		 
		//Setting an action for the Clear button
		clear.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		    public void handle(ActionEvent e) {
		        name.clear();
		        amount.clear();
		        label.setText(null);
		    }
		});
		
		
		// set up the layout
		mLabel = new Label(); 
		mLabel.setText("Enter Inventory Changes");

		setPadding(new Insets(10, 10, 10, 10));
		setSpacing(10); // Horizontal gap in pixels
		
		this.getChildren().addAll(mLabel,grid,label);

	} // end of constructor
	
	
	public ItemModel getItem()
	{
		return newItem;
	}
	
	public void editInvintory(String filepath, String newNum, int index, String name)
	{
		ArrayList<String> rest = new ArrayList<String>();
		
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

			String line = br.readLine();
			while(line != null)
			{
				if(index == count) {
					//pw.println(name + "," + newNum);
					fw.append(name + "," + newNum + rest.get(index) + "\n");
				}
				else {
					//pw.println(line);
					fw.append(line + "\n");
				}
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
	
	
	public void editRaw(String filepath, ArrayList<String> newNum, ArrayList<Integer> index, ArrayList<RawMaterial> name)
	{	
		ArrayList<String> rest = new ArrayList<String>();
		
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
		
		String tempFile = "temp1.csv";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		int count = 0;
		int count2 = 0;
		
		try
		{
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			PrintWriter pw = new PrintWriter(bw);

			String line;
			
			while((line = br.readLine()) != null)
			{
					if(count2 < name.size() && index.get(count2) == count) {
						//pw.println(name + "," + newNum);
						fw.append(name.get(count2).getName() + "," + newNum.get(count2) + rest.get(count) + "\n");
						count2++;
						//System.out.print(name.get(count2) + "," + newNum.get(count2) + "\n");
					}	
					else {
						//pw.println(line);
						fw.append(line + "\n");
						//System.out.print(line + "\n");
					}
				//line = br.readLine();
				count++;
			}
			//System.out.print(newNum.size());
			//System.out.print(index.size());
			//System.out.print(name.size());
			
			br.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
			
			//System.out.println("Working");
	
		}
		catch(Exception e3)
		{
			System.out.print("Error: Edit Raw Materials Meathod. Check to make sure raw mat. csv is formatted correctly.");
		}
		
		
	}
	
	
	
	
	
	
	public void editBox(String filepath, ArrayList<Box> boxList)
	{	
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

			String line = br.readLine();
			while(line != null)
			{
				
				fw.append(boxList.get(count).getName() + "," + boxList.get(count).getPercentUsed() + "," + boxList.get(count).getVolume() + "," + boxList.get(count).getTapeUsed() + "," + boxList.get(count).getStock() + "\n");
				
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
	
	
	
	
	//builds a chart to show finished good inventory
	public static XYChart.Series<String,Number> buildFinishedChart(ArrayList<FinishedGood> finishedGoodList)
	{
		
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		
		for(int i = 0; i < finishedGoodList.size(); i++)
		{
			//Tooltip tooltip = new Tooltip();
			XYChart.Data<String, Number> data = new XYChart.Data<>();
			
			data.setXValue(finishedGoodList.get(i).getName());
			data.setYValue(finishedGoodList.get(i).getStock());
			
			//tooltip.setText(data.getXValue().toString()+ ": " + data.getYValue().toString());
			//Tooltip.install(data.getNode(), tooltip);

			series.getData().add(data);
		}
		
		return series;
		
	}
	
	//builds a chart to show raw material inventory
	public static XYChart.Series<String,Number> buildRawChart(ArrayList<RawMaterial> rawMaterialList)
	{
		
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		
		for(int i = 0; i < rawMaterialList.size(); i++)
		{
			
			XYChart.Data<String, Number> data = new XYChart.Data<>();
			
			data.setXValue(rawMaterialList.get(i).getName());
			data.setYValue(rawMaterialList.get(i).getSingleUse());
			

			series.getData().add(data);
		}
		
		return series;
		
	}
	
	
	

	// --- Data Members ---
	private Label 				mLabel;

}
