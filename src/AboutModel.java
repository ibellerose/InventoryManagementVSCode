//package application;
import java.util.ArrayList;

public class AboutModel{
	// List of Info
	private ArrayList<String> description = new ArrayList<String>();

	// Constructor to add all info
	public AboutModel() 
	{
		description.add("One Fun Inventory Managment System");
		description.add("Creator: Ian Bellerose");
	}

	// toString() method returns a string
	public String getGroup() 
	{
		String result = "";
		for (String i : description)
		{
			result += i;
			result += "\n";
		}
		return result;
	}
}
