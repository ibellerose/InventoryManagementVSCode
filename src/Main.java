//package application;

//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;

import javafx.application.Application;
//import javafx.geometry.Side;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;



public class Main extends Application {
	//private ArrayList<ItemModel> rawMaterialList = new ArrayList<ItemModel>();
	//private ObservableList<ItemModel> oList = FXCollections.<ItemModel>observableArrayList();
	//private ItemModel temp;

	private AboutModel aboutMod;
	
	private AboutView aboutView;
	private TabPane mTabPane;
	private CalibrateNew cal;
	private AddItem addTab = new AddItem();
	private AddFinishedGood finGood = new AddFinishedGood();
	
	private Forecast forecast = new Forecast();
	//private ItemListView saveView;
	//private ItemListView loadView;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//rawMaterialList = new ArrayList<ItemModel>();
			
			aboutMod = new AboutModel();
			
			StackPane root = new StackPane();
			
			cal = new CalibrateNew();
			//addTab = new AddItem();
			
			//saveView = new ItemListView(primaryStage, rawMaterialList, ItemListView.Style.SAVE);
			//loadView = new ItemListView(primaryStage, rawMaterialList, ItemListView.Style.LOAD);
			
			aboutView = new AboutView(aboutMod);
			
			mTabPane = new TabPane();
			//mTabPane.setSide(Side.LEFT);

			
			Tab tab1 = new Tab();
			tab1.setText("About");
			tab1.setContent(aboutView);
			
			Tab tab2 = new Tab();
			tab2.setText("Inventory");
			tab2.setContent(cal);
			
			Tab tab3 = new Tab();
			tab3.setText("Add Raw Material");
			tab3.setContent(addTab);
			
			//Tab tab4 = new Tab();
			//tab4.setText("Load Data");
			//tab4.setContent(loadView);
			
			//Tab tab5 = new Tab();
			//tab5.setText("Save Data");
			//tab5.setContent(saveView);
			
			Tab tab6 = new Tab();
			tab6.setText("Add Finished Good");
			tab6.setContent(finGood);
			
			Tab tab7 = new Tab();
			tab7.setText("Forecast");
			tab7.setContent(forecast);

			mTabPane.getSelectionModel().select(0);
			mTabPane.getTabs().addAll(tab2,tab7,tab1,tab3,tab6);
			//mTabPane.getTabs().addAll(tab1,tab2,tab3,tab4,tab5);

			mTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			// Remove closing option

			root.getChildren().add(mTabPane);
			
			// Notifier = PatientModel.getSupport()
			// Notifier.addPropertyChangeListener(new Listener)
/*			
			ItemModel.getSupport().addPropertyChangeListener(new PropertyChangeListener()
			{
				// super googled hackery to allow the controller (Main) to 
				// recieve notification that the model (mPatientList) has changed
				// Stole it from here: https://www.baeldung.com/java-observer-pattern
				public void propertyChange(PropertyChangeEvent inEvent)
				{
					
					if (inEvent.getPropertyName() == "PatientListView")
					{
						rawMaterialList = loadView.getPatientList();
					}
					
					else if (inEvent.getPropertyName() == "AddData")
					{
						rawMaterialList.add(addTab.getItem());
						temp = addTab.getItem();
						try(FileWriter sb = new FileWriter("C:\\Users\\ianbe\\eclipse-workspace\\InventoryTracking\\rawMaterial.csv",true)){
							//StringBuilder sb = new StringBuilder();
							sb.append(temp.getItemName());
							sb.append(",");
							sb.append(String.valueOf(temp.getCount()));
							sb.append("\n");
							
							sb.flush();
							sb.close();
						

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					//else

					{
						//throw new Exception("Unknown Origin for mPatientList (Model) change.");
					}

					cal.update(rawMaterialList);
					//loadView.update(rawMaterialList);
					//saveView.update(rawMaterialList);
				}
			});
			
			*/
			Scene scene = new Scene(root,1200,650);
			
			primaryStage.setTitle("Inventory Management");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
