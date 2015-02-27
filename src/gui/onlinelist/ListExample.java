package gui.onlinelist;

import javax.swing.JFrame;
import javax.swing.JList;

public class ListExample extends JFrame
{
	// Instance attributes used in this example
	private	JList		listbox;

	// Constructor of main frame
	public ListExample()
	{

		// Create some items to add to the list
		String	listData[] =
			{
				"Item 1",
				"Item 2",
				"Item 3",
				"Item 4"
			};

		// Create a new listbox control
		listbox = new JList( listData );
	}

}
