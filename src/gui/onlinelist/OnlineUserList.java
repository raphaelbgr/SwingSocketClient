package gui.onlinelist;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class OnlineUserList extends JPanel
{
	// Instance attributes used in this example
	private	JPanel topPanel;
	private	JList<String> listbox;
	private JScrollPane sp;

	public OnlineUserList()
	{
		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		this.add( topPanel,BorderLayout.EAST );

		// Create some items to add to the list
		String	listData[] =
			{
				"---  ONLINE USERS --- "
			};

		//SCROLL PANE
		JScrollPane scrollPane = new JScrollPane(this);
		scrollPane.setPreferredSize(new Dimension(150, 100));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.sp = scrollPane;

		// Create a new listbox control
		listbox = new JList<String>( listData );
		topPanel.add( listbox, BorderLayout.CENTER );
	}

	public JScrollPane getListExample() {
		return this.sp;
	}

	public void addUser(Vector<String> vector, String s)
	{
		// Add this item to the list and refresh
		vector.addElement("---  ONLINE USERS --- ");
		vector.addElement(s);
		listbox.setListData(vector);

		getListExample().revalidate();
		getListExample().repaint();
	}

	public void delUser(Vector<String> vector, String s) {
		// Remove this item to the list and refresh
		vector.removeElement(s);
		listbox.setListData(vector);
		getListExample().revalidate();
		getListExample().repaint();
	}
	
	public void updateOnlineList(Vector<String> vector) {
		listbox.setListData(vector);
		String s = "---  ONLINE USERS --- ";
		if (!vector.contains(s.equals("---  ONLINE USERS --- "))) {
			vector.insertElementAt("---  ONLINE USERS --- ",0);
		}
		getListExample().revalidate();
		getListExample().repaint();
	}

}
