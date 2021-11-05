//package application;

import java.beans.*;

public class ItemModel
{
	// Data Members
	private int count = 0;
	private String itemName = "";
	
	//make nested so i don't have to put it in own .java file
	static class Support // crazy- Support must be static?
	{
		private int mValue;
		private PropertyChangeSupport mSupport;

		public Support() 
		{
			mValue = 0;
			
			// Yucky. I had to make this nested class because I couldn't figure out how
			// to use a raw, PropertyChangeSupport object because the ctor expects
			// a "this" argument.
			// Stole it from here: https://www.baeldung.com/java-observer-pattern
			mSupport = new PropertyChangeSupport(this);
		}

		public void addPropertyChangeListener(PropertyChangeListener inListener) 
		{
			mSupport.addPropertyChangeListener(inListener);
		}

		public void removePropertyChangeListener(PropertyChangeListener inListener) 
		{
			mSupport.removePropertyChangeListener(inListener);
		}

		public void setChanged(String InOrigin)
		{
			// Yuck. Value must always be different, otherwise I'll never get a change notification.
			mSupport.firePropertyChange(InOrigin, mValue, mValue+1);
			mValue = mValue+1;
		}
	}

	// Static notifier 
	// I wanted to notify Main when Main.mPatientList has changed, I couldn't 
	// figure out how to do it. So instead, I used this static PropertyChangeSupport() 
	// as a notifier to say something, somewhere in the app has changed. 
	// Yucky, but I ran out of time.
	private static Support sSupport = new Support();

	public ItemModel(int inCount, String inItem) 
	{
		count = inCount;
		itemName = inItem;
	}
	

	public ItemModel() 
	{
		// This space intentionally empty

		count = 0;
		itemName = "item";
	}
	
	// Getters and Setters
	public int getCount()
	{
		return count;
	}

	public void setCount(int inCount)
	{
		count = inCount;
	}

	public String getItemName()
	{
		return itemName;
	}
	
	public void setItemName(String inItem)
	{
		itemName = inItem;
	}

	// getSupport() notifies for changes to any/all patientModel.
	public static Support getSupport()
	{
		return sSupport;
	}
	
	public static void somethingChanged(String inOrigin)
	{
		getSupport().setChanged(inOrigin);
	}
}