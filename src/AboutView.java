//package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class AboutView extends VBox 
{
	// constructor
	public AboutView(AboutModel inAbout) 
	{
		// initialize instance variables 

		ObservableList<String> aboutList = FXCollections.<String>observableArrayList(inAbout.getGroup());
		listView = new ListView<>(aboutList);

		// set up the layout
		// ----
		mLabel = new Label(); 
		mLabel.setText("Inventory management");

		setPadding(new Insets(10, 10, 10, 10));
		setSpacing(10); // Horizontal gap in pixels
		
		// ReviewPane is a VBox - add the components here
		// ----
		this.getChildren().addAll(mLabel, listView);

	} // end of constructor

	// --- Data Members ---
	private ListView<String> 	listView;
	// Descriptive GUI text
	private Label 				mLabel;
} // End of ReviewPane class