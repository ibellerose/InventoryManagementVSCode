//package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
//import java.util.Arrays;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Forecast extends HBox
{
	//private ObservableList<ItemModel> oList = FXCollections.<ItemModel>observableArrayList();
	
	private String csvForecast = "C:\\OneFunInvManSys\\forecast.csv";
	
	private ArrayList<Label> finNameLabel = new ArrayList<Label>();
	private ArrayList<TextField> final_forcast_number = new ArrayList<TextField>();
	private ArrayList<Integer> final_forcast_count = new ArrayList<Integer>();
	
	private ArrayList<RawMaterial> rawMaterialList = new ArrayList<RawMaterial>();
	private ArrayList<FinishedGood> finishedGoodList = new ArrayList<FinishedGood>();
	
	private ArrayList<ForecastData> forecastList = new ArrayList<ForecastData>();
	
	private ArrayList<Box> boxList = new ArrayList<Box>();
	
	//private ArrayList<Double> peanut_coeff = new ArrayList<Double>();
	
	//private String line = "";
	private int material_usage_index;
	private double rawMaterialUsed = 0.0;
	//private double finalPrice = 0;
	private int dropDownWeek = 0;
	//private double peanut_inst = 0;
	private int sumOfOrder = 0;
	//private int peanutSpot = 0;
	//private int boxSpot = 0;
	private int gridSpot = 0;
	private int leftGridSpot = 0;
	//private int boxCount = 0;
	private int tape_total = 0;
	
	private double total_peanuts = 0;
	
	private String newMonthName = "";
	
	//find a different way to do this
	//private final int NUMBER_OF_BOXES = 5;
	
	//private int totalPeanutsNeeded = 0;
	
	private int count;
	// constructor
	public Forecast()
	{		
		Font headerFont = Font.font("Verdana", FontWeight.BOLD, 16);
		
		new UpdateInfo(forecastList);
		new UpdateInfo(rawMaterialList,finishedGoodList);
		
		//text field name of forecast data
		TextField forecastDateTf = new TextField();
		
		//Label for date
		Label forecastDateLabel = new Label();
		
		//Topic label and error label
		Label topic = new Label();
		topic.setText("Choose a forecasting option:");
		Label error = new Label();
		error.setText("Make sure all feilds are filled in");
		error.setTextFill(Color.color(1,0,0));
		
		//Left grid
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setStyle("-fx-background-color: #4287f5");
		
		
		//Right grid
		GridPane gridRight = new GridPane();
		gridRight.setPadding(new Insets(10, 10, 10, 10));
		gridRight.setVgap(5);
		gridRight.setHgap(5);
		//gridRight.setGridLinesVisible(true); //need to fix the constraints if want grid lines
		//set for a colored background(too dark?)
		//gridRight.setStyle("-fx-background-color: #bbbbbb");
	
		//grid (left)
		GridPane.setConstraints(topic, 0, leftGridSpot++);
		GridPane.setColumnSpan(topic, 2);
		grid.getChildren().add(topic);
		
		//Add in a place to get the monthly forecast
		ComboBox<String> forecastCombo = new ComboBox<String>();
		forecastCombo.getItems().addAll("Create new monthly forecast","Upload existing forecast","Input Data without saving");

		GridPane.setConstraints(forecastCombo, 0, leftGridSpot++);
		GridPane.setColumnSpan(forecastCombo, 2);
		grid.getChildren().add(forecastCombo);
/*		
		for(int i = 0; i < finishedGoodList.size(); i++) {
			Label tempLabel = new Label();
			tempLabel.setText(finishedGoodList.get(i).getName() + ": ");
			finNameLabel.add(tempLabel);
			
			TextField tempTf = new TextField();
			tempTf.setPromptText("Enter Value");
			tempTf.setPrefColumnCount(10);
			tempTf.getText();
			final_forcast_number.add(tempTf);
			
			GridPane.setConstraints(tempLabel, 0, leftGridSpot);
			GridPane.setColumnSpan(tempLabel, 1);
			grid.getChildren().add(tempLabel);
			
			GridPane.setConstraints(tempTf, 1, leftGridSpot++);
			GridPane.setColumnSpan(tempTf, 1);
			grid.getChildren().add(tempTf);
			count = i;
		}
*/		
		Button submit = new Button("Submit");
		//GridPane.setConstraints(submit, 0, leftGridSpot++);
		//grid.getChildren().add(submit);
		
		//error position
		GridPane.setConstraints(error, 0, leftGridSpot);
		GridPane.setColumnSpan(error, 2);
		
				
		//end grid (left)
		
		//start gridRight	
		
		//end gridRight
		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setContent(gridRight);
		
		
		this.getChildren().addAll(grid, scroll);
		//this.getChildren().addAll(grid, gridRight);
		
		forecastCombo.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e1) {
				
				//Allow for multiple attempts
				grid.getChildren().clear();
				grid.getChildren().addAll(topic,forecastCombo);
				finNameLabel.clear();
				final_forcast_number.clear();
				leftGridSpot = 2;
				
				if(forecastCombo.getSelectionModel().getSelectedIndex() == 0 || forecastCombo.getSelectionModel().getSelectedIndex() == 2) {
					if(forecastCombo.getSelectionModel().getSelectedIndex() == 0) {
						forecastDateLabel.setText("Enter month and year\n(ex. Aug 2021): ");
						finNameLabel.add(forecastDateLabel);
						
						forecastDateTf.setPromptText("Month Year");
						forecastDateTf.setPrefColumnCount(10);
						//tempTf.setText(String.valueOf(forecastList.get(forecastDateCombo.getSelectionModel().getSelectedIndex()).getSingleGood(i)));
						
						GridPane.setConstraints(forecastDateLabel, 0, leftGridSpot);
						GridPane.setColumnSpan(forecastDateLabel, 1);
						grid.getChildren().add(forecastDateLabel);
						
						GridPane.setConstraints(forecastDateTf, 1, leftGridSpot++);
						GridPane.setColumnSpan(forecastDateTf, 1);
						grid.getChildren().add(forecastDateTf);
					}
					
					for(int i = 0; i < finishedGoodList.size(); i++) {
						Label tempLabel = new Label();
						tempLabel.setText(finishedGoodList.get(i).getName() + ": ");
						finNameLabel.add(tempLabel);
						
						TextField tempTf = new TextField();
						tempTf.setPromptText("Enter Value");
						tempTf.setPrefColumnCount(10);
						//tempTf.setText(String.valueOf(forecastList.get(forecastDateCombo.getSelectionModel().getSelectedIndex()).getSingleGood(i)));
						tempTf.getText();
						final_forcast_number.add(tempTf);
						
						GridPane.setConstraints(tempLabel, 0, leftGridSpot);
						GridPane.setColumnSpan(tempLabel, 1);
						grid.getChildren().add(tempLabel);
						
						GridPane.setConstraints(tempTf, 1, leftGridSpot++);
						GridPane.setColumnSpan(tempTf, 1);
						grid.getChildren().add(tempTf);
						count = i;
					}
					
					//Move button to bottom
					GridPane.setConstraints(submit, 0, leftGridSpot++);
					grid.getChildren().add(submit);
					
				}
				
				
				if(forecastCombo.getSelectionModel().getSelectedIndex() == 1) {
					ComboBox<String> forecastDateCombo = new ComboBox<String>();
					
					for(int i = 0; i < forecastList.size();i++) {
					forecastDateCombo.getItems().add(forecastList.get(i).getDate());
					}
					
					GridPane.setConstraints(forecastDateCombo, 0, leftGridSpot++);
					grid.getChildren().add(forecastDateCombo);
					
					
					forecastDateCombo.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e1) {
							
							for(int i = 0; i < finNameLabel.size(); i++) {
								grid.getChildren().removeAll(finNameLabel.get(i),final_forcast_number.get(i));
							}
							grid.getChildren().remove(submit);
							finNameLabel.clear();
							final_forcast_number.clear();
							leftGridSpot = 3;
							
							for(int i = 0; i < finishedGoodList.size(); i++) {
								Label tempLabel = new Label();
								tempLabel.setText(finishedGoodList.get(i).getName() + ": ");
								finNameLabel.add(tempLabel);
								
								TextField tempTf = new TextField();
								tempTf.setPromptText("Enter Value");
								tempTf.setPrefColumnCount(10);
								tempTf.setText(String.valueOf(forecastList.get(forecastDateCombo.getSelectionModel().getSelectedIndex()).getSingleGood(i)));
								tempTf.getText();
								final_forcast_number.add(tempTf);
								
								GridPane.setConstraints(tempLabel, 0, leftGridSpot);
								GridPane.setColumnSpan(tempLabel, 1);
								grid.getChildren().add(tempLabel);
								
								GridPane.setConstraints(tempTf, 1, leftGridSpot++);
								GridPane.setColumnSpan(tempTf, 1);
								grid.getChildren().add(tempTf);
								count = i;
							}
							
							//Move button to bottom
							GridPane.setConstraints(submit, 0, leftGridSpot++);
							grid.getChildren().add(submit);
							
						}
					});	
				}
				
				
				
				
			}
		});
		
		
		submit.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				gridRight.getChildren().clear();
				boolean check_empty = false;
				newMonthName = forecastDateTf.getText();
				gridSpot = 0;
				
				//clearing
				boxList.clear();
				new UpdateInfo(rawMaterialList,finishedGoodList,boxList);
				final_forcast_count.clear();
				
				for(int i = 0; i < finishedGoodList.size(); i++) {
					final_forcast_number.get(i).getText();
				}
				
				for(int i = 0; i < finishedGoodList.size(); i++) {
					if(final_forcast_number.get(i).getText().isEmpty()) {
						check_empty = true;
					}
				}
				
				if(check_empty == false) {
					
					grid.getChildren().remove(error);
					gridRight.getChildren().clear();
					
					for(int i = 0; i < finishedGoodList.size(); i++) {
						final_forcast_count.add(Integer.parseInt(final_forcast_number.get(i).getText()));
					}
					for(int i = 0; i < finishedGoodList.size(); i++) {
						for(int j = 0; j < rawMaterialList.size(); j++) {
							//check if the material is used for the good
							if(finishedGoodList.get(i).getMaterialList().contains(rawMaterialList.get(j))) {
								material_usage_index = finishedGoodList.get(i).getMaterialList().indexOf(rawMaterialList.get(j));
								//calculate the amount of raw material needed for each finished good
								 rawMaterialUsed = (final_forcast_count.get(i) * finishedGoodList.get(i).getMaterialsUsedNumber().get(material_usage_index));
								 
								 //calculate price total
								 
								//Used to set the labels
								rawMaterialList.get(j).addToTotal(rawMaterialUsed);
								
								//calculate total amount ordered

								//System.out.println(rawMaterialList.get(0).getNeeded());
								//calculate trigger values
								//trigger value is (lead time * 3) * burn rate
								//burn rate is total / 30
								//trigger value = ((leadtime*3) * total needed)/30
								
								//debugging
								//System.out.println(i + ", " + j + ": " + rawMaterialList.get(0).getNeeded());

								
							}
						}
						//amount of each finished good needed
						finishedGoodList.get(i).addToNeeded(final_forcast_count.get(i));
						
						//total amount of expected finished goods
						sumOfOrder += final_forcast_count.get(i);
						
						//calculate total price
						//finalPrice += final_forcast_count.get(i) * finishedGoodList.get(i).getPrice();
					}
					
					//calculate box
					for(int i = 0; i < boxList.size(); i++) {
						boxList.get(i).addToNeeded(sumOfOrder);
						
						total_peanuts += boxList.get(i).getVolume() * boxList.get(i).getNeeded();
					}
					
					//for(int i = 0; i < rawMaterialList.size(); i ++) {
						//rawMaterialNumberLabel.get(i).setText(String.valueOf(rawMaterialList.get(i).getNeeded()));
					//}
					
					//Save forecast if asked
					if(forecastCombo.getSelectionModel().getSelectedIndex() == 0) {
						saveForecast(csvForecast,newMonthName,final_forcast_count);
						new UpdateInfo(forecastList);
					}
					
					//raw material header
					Label rawMaterialHeader = new Label();
					rawMaterialHeader.setFont(headerFont);
					rawMaterialHeader.setUnderline(true);
					rawMaterialHeader.setText("Raw Materials");
					
					GridPane.setConstraints(rawMaterialHeader, 0, ++gridSpot);
					GridPane.setColumnSpan(rawMaterialHeader, 1);
					gridRight.getChildren().add(rawMaterialHeader);
					
					//set raw Material info
					for(int i = 0; i < rawMaterialList.size(); i++) {
						Label tempLabelName = new Label();
						tempLabelName.setText(rawMaterialList.get(i).getName() + " Total:");
						if(rawMaterialList.get(i).daysLeft() < (rawMaterialList.get(i).getLeadTime()*3)) {tempLabelName.setTextFill(Color.RED);}
						
						GridPane.setConstraints(tempLabelName, 0, ++gridSpot);
						GridPane.setColumnSpan(tempLabelName, 1);
						gridRight.getChildren().add(tempLabelName);
						
						
						Label tempLabelNum = new Label();
						tempLabelNum.setText(String.valueOf(rawMaterialList.get(i).getNeeded()));
						if(rawMaterialList.get(i).daysLeft() < (rawMaterialList.get(i).getLeadTime()*3)) {tempLabelNum.setTextFill(Color.RED);}
						
						GridPane.setConstraints(tempLabelNum, 1, gridSpot);
						GridPane.setColumnSpan(tempLabelNum, 1);
						gridRight.getChildren().add(tempLabelNum);
					
						//Trigger Label
						Label tempTriggerLabel = new Label();
						tempTriggerLabel.setText("Trigger: " + String.valueOf(rawMaterialList.get(i).getTrigger()));
						if(rawMaterialList.get(i).daysLeft() < (rawMaterialList.get(i).getLeadTime()*3)) {tempTriggerLabel.setTextFill(Color.RED);}
						
						GridPane.setConstraints(tempTriggerLabel, 2, gridSpot);
						GridPane.setColumnSpan(tempTriggerLabel, 1);
						gridRight.getChildren().add(tempTriggerLabel);
						
						//OrderQuantity
						Label tempOrder = new Label();
						tempOrder.setText("Order: " + String.valueOf(rawMaterialList.get(i).orderAmount()) + "\t(quantity: " + String.valueOf(rawMaterialList.get(i).getOrderQuantity()) + ")");
						if(rawMaterialList.get(i).daysLeft() < (rawMaterialList.get(i).getLeadTime()*3)) {tempOrder.setTextFill(Color.RED);}
						
						GridPane.setConstraints(tempOrder, 3, gridSpot);
						GridPane.setColumnSpan(tempOrder, 1);
						gridRight.getChildren().add(tempOrder);
						
						//Timeleft with materials
						Label timeLeft = new Label();
						timeLeft.setText("Days left: " + String.valueOf(rawMaterialList.get(i).daysLeft()) + "\t\t");
						if(rawMaterialList.get(i).daysLeft() < (rawMaterialList.get(i).getLeadTime()*3)) {timeLeft.setTextFill(Color.RED);}
						
						GridPane.setConstraints(timeLeft, 4, gridSpot);
						GridPane.setColumnSpan(timeLeft, 1);
						gridRight.getChildren().add(timeLeft);
						
						//reOrder rate
						/*No longer using reOrder rate
						Label tempReOrder = new Label();
						tempReOrder.setText("ReOrder: " + String.valueOf(rawMaterialList.get(i).getAndSetReOrderSize(dropDownWeek)));
						
						GridPane.setConstraints(tempReOrder, 3, gridSpot);
						GridPane.setColumnSpan(tempReOrder, 1);
						gridRight.getChildren().add(tempReOrder);
						*/
					}
					
					//finished goods
					Label finishedGoodHeader = new Label();
					finishedGoodHeader.setFont(headerFont);
					finishedGoodHeader.setUnderline(true);
					finishedGoodHeader.setText("Finished Goods");
					
					GridPane.setConstraints(finishedGoodHeader, 0, ++gridSpot);
					GridPane.setColumnSpan(finishedGoodHeader, 1);
					gridRight.getChildren().add(finishedGoodHeader);
					
					//set finished good info
					for(int i = 0; i < finishedGoodList.size(); i++) {
						Label tempLabelName = new Label();
						tempLabelName.setText(finishedGoodList.get(i).getName() + " Total:");
						
						GridPane.setConstraints(tempLabelName, 0, ++gridSpot);
						GridPane.setColumnSpan(tempLabelName, 1);
						gridRight.getChildren().add(tempLabelName);
						
						Label tempFinishedNumber = new Label();
						tempFinishedNumber.setText(String.valueOf(finishedGoodList.get(i).getNeeded()));
						
						GridPane.setConstraints(tempFinishedNumber, 1, gridSpot);
						GridPane.setColumnSpan(tempFinishedNumber, 1);
						gridRight.getChildren().add(tempFinishedNumber);
					
						//Trigger Label
						Label tempTriggerLabel = new Label();
						tempTriggerLabel.setText("Trigger: " + String.valueOf(finishedGoodList.get(i).getTrigger()));
						
						GridPane.setConstraints(tempTriggerLabel, 2, gridSpot);
						GridPane.setColumnSpan(tempTriggerLabel, 1);
						gridRight.getChildren().add(tempTriggerLabel);
						
						//reOrder rate
						/*
						Label tempReOrder = new Label();
						tempReOrder.setText("ReOrder: " + String.valueOf(rawMaterialList.get(i).getAndSetReOrderSize(dropDownWeek)));
						
						GridPane.setConstraints(tempReOrder, 3, gridSpot);
						GridPane.setColumnSpan(tempReOrder, 1);
						gridRight.getChildren().add(tempReOrder);
						*/
					}
					
					
					//fulfillment header
					Label boxHeader = new Label();
					boxHeader.setFont(headerFont);
					boxHeader.setUnderline(true);
					boxHeader.setText("Fulfillment");
					
					GridPane.setConstraints(boxHeader, 0, ++gridSpot);
					GridPane.setColumnSpan(boxHeader, 1);
					gridRight.getChildren().add(boxHeader);
					
					//print box info
					for(int i = 0; i < boxList.size(); i++) {
						Label tempLabelName = new Label();
						tempLabelName.setText(boxList.get(i).getName() + " Total:");
						if(boxList.get(i).daysLeft() < (boxList.get(i).getLeadTime()*3)) {tempLabelName.setTextFill(Color.RED);}
						
						GridPane.setConstraints(tempLabelName, 0, ++gridSpot);
						GridPane.setColumnSpan(tempLabelName, 1);
						gridRight.getChildren().add(tempLabelName);
						
						Label tempLabelNumber = new Label();
						tempLabelNumber.setText(String.valueOf(boxList.get(i).getNeeded()));
						if(boxList.get(i).daysLeft() < (boxList.get(i).getLeadTime()*3)) {tempLabelNumber.setTextFill(Color.RED);}
						
						GridPane.setConstraints(tempLabelNumber, 1, gridSpot);
						GridPane.setColumnSpan(tempLabelNumber, 1);
						gridRight.getChildren().add(tempLabelNumber);
					
						//Trigger Label
						Label tempTriggerLabel = new Label();
						tempTriggerLabel.setText("Trigger: " + String.valueOf(boxList.get(i).getTrigger()));
						if(boxList.get(i).daysLeft() < (boxList.get(i).getLeadTime()*3)) {tempTriggerLabel.setTextFill(Color.RED);}
						
						GridPane.setConstraints(tempTriggerLabel, 2, gridSpot);
						GridPane.setColumnSpan(tempTriggerLabel, 1);
						gridRight.getChildren().add(tempTriggerLabel);
						
						Label tempOrder = new Label();
						tempOrder.setText("Order: " + String.valueOf(boxList.get(i).orderAmount()) + "\t(quantity: 25)");
						if(boxList.get(i).daysLeft() < (boxList.get(i).getLeadTime()*3)) {tempOrder.setTextFill(Color.RED);}
						
						GridPane.setConstraints(tempOrder, 3, gridSpot);
						GridPane.setColumnSpan(tempOrder, 1);
						gridRight.getChildren().add(tempOrder);
						
						//Timeleft with materials
						Label timeLeft = new Label();
						timeLeft.setText("Days left: " + String.valueOf(boxList.get(i).daysLeft()) + "\t\t");
						if(boxList.get(i).daysLeft() < (boxList.get(i).getLeadTime()*3)) {timeLeft.setTextFill(Color.RED);}
						
						GridPane.setConstraints(timeLeft, 4, gridSpot);
						GridPane.setColumnSpan(timeLeft, 1);
						gridRight.getChildren().add(timeLeft);

						//reOrder rate
						/*
						Label tempReOrder = new Label();
						tempReOrder.setText("ReOrder: " + String.valueOf(rawMaterialList.get(i).getAndSetReOrderSize(dropDownWeek)));
						
						GridPane.setConstraints(tempReOrder, 3, i + peanutSpot);
						GridPane.setColumnSpan(tempReOrder, 1);
						gridRight.getChildren().add(tempReOrder);
						*/
						tape_total += boxList.get(i).getTapeUsed();
					}
					
					
					//main peanut label
					
					Label peanutLabel = new Label();
					peanutLabel.setText("Peanuts by Bag(14 sq ft): ");
					GridPane.setConstraints(peanutLabel, 0, ++gridSpot);
					GridPane.setColumnSpan(peanutLabel, 1);
					gridRight.getChildren().add(peanutLabel);
					
					Label peanutNumberLabel = new Label();
					peanutNumberLabel.setText(String.valueOf(total_peanuts/14));
					GridPane.setConstraints(peanutNumberLabel, 1, gridSpot);
					GridPane.setColumnSpan(peanutNumberLabel, 1);
					gridRight.getChildren().add(peanutNumberLabel);
					
					//trigger peanut label
					Label peanutTriggerLabel = new Label();
					peanutTriggerLabel.setText("Trigger: " + String.valueOf(((total_peanuts/14)*3*dropDownWeek)/30));
					GridPane.setConstraints(peanutTriggerLabel, 2, gridSpot);
					GridPane.setColumnSpan(peanutTriggerLabel, 1);
					gridRight.getChildren().add(peanutTriggerLabel);
					
					//Tape label
					Label tapeLabel = new Label();
					tapeLabel.setText("Tape (by role): ");
					GridPane.setConstraints(tapeLabel, 0, ++gridSpot);
					GridPane.setColumnSpan(tapeLabel, 1);
					gridRight.getChildren().add(tapeLabel);
					
					Label tapeNumberLabel = new Label();
					tapeNumberLabel.setText(String.valueOf(tape_total/3960));
					GridPane.setConstraints(tapeNumberLabel, 1, gridSpot);
					GridPane.setColumnSpan(tapeNumberLabel, 1);
					gridRight.getChildren().add(tapeNumberLabel);
				
					//NEEDS TO BE UPDATED TO NOT BE 40
					//price
					/*
					Label price = new Label();
					price.setText(String.valueOf(finalPrice));
					GridPane.setConstraints(price, 0, 40);
					GridPane.setColumnSpan(price, 1);
					gridRight.getChildren().add(price);
					*/
				}
				else {
					grid.getChildren().add(error);
				}
			}
		});
		
	
	} // end of constructor

	public void saveForecast(String filepath, String date, ArrayList<Integer> forecastValues)
	{	
		
		//String tempFile = "tempCast1.csv";
		File oldFile = new File(filepath);
		//File newFile = new File(tempFile);
		
		try
		{
			FileWriter fw = new FileWriter(oldFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			//BufferedReader br = new BufferedReader(new FileReader(filepath));
			PrintWriter pw = new PrintWriter(bw);
			
			fw.append("\n" + date);
			for(int i = 0;i < forecastValues.size();i++) {
				fw.append("," + forecastValues.get(i));
			}
			
			pw.flush();
			pw.close();
			
			//System.out.println("Working");
	
		}
		catch(Exception e3)
		{
			System.out.print("Error");
		}
		
		
	}
	

}