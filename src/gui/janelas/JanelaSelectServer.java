package gui.janelas;

import gui.buttonlisteners.JButtonSelectServerOKListener;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class JanelaSelectServer extends JFrame {

	JTextField field_ip 	= null;
	JTextField field_port 	= null;
	JTextField field_name 	= null;
	private JanelaMain jam;

	public String getIpText() {
		return this.field_ip.getText();
	}

	public int getPortNumber() {
		return Integer.valueOf(this.field_port.getText());
	}
	public String getNameFieldText() {
		return this.field_name.getText();
	}

	public JanelaSelectServer(String title) {

		super(title);

		JPanel jpnl 		= new JPanel();
		JLabel ip_lbl 		= new JLabel("IP Address");
		this.field_ip 		= new JTextField(12);
		JLabel port_lbl 	= new JLabel("Port");
		this.field_port 	= new JTextField(5);
		JLabel lbl_name		= new JLabel("Your name");
		this.field_name 	= new JTextField(12);
		JButton btn_OK		= new JButton("OK");

		this.add(jpnl);
		jpnl.add(ip_lbl);
		jpnl.add(this.field_ip);
		jpnl.add(port_lbl);
		jpnl.add(field_port );
		jpnl.add(lbl_name);
		jpnl.add(field_name);
		jpnl.add(btn_OK);

		//Propiedades do botao OK
		btn_OK.addActionListener(new JButtonSelectServerOKListener(jam, this));
		btn_OK.setFocusable(true);
		btn_OK.setMnemonic('o');

		//Propiedades default dos campos:
//		this.field_ip.setText("127.0.0.1");
//		this.field_name.setText("Teste");
		this.field_port.setText("2000");

		//Atribui a keystroke ENTER para o envio da mensagem quando o campo de texto do chat esta em foco.
		this.field_name.setAutoscrolls(true); 							//Propiedade do botao Send
		KeyStroke keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
		this.field_name.registerKeyboardAction(new JButtonSelectServerOKListener(jam, this), keystroke,JComponent.WHEN_FOCUSED);

		this.setSize(350, 150);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocation(0, 310);
		this.setAlwaysOnTop(true);

		setResizable(false);

	}

	public JTextField getField_name() {
		return field_name;
	}

	public void setField_name(JTextField field_name) {
		this.field_name = field_name;
	}

	public void lockFields() {
		this.field_ip.setEditable(false);
		this.field_port.setEditable(false);
		this.field_name.setEditable(false);
		this.field_ip.setBackground(Color.LIGHT_GRAY);
		this.field_port.setBackground(Color.LIGHT_GRAY);
		this.field_name.setBackground(Color.LIGHT_GRAY);
	}

	public void unlockFields() {
		this.field_ip.setEditable(true);
		this.field_port.setEditable(true);
		this.field_name.setEditable(true);
		this.field_ip.setBackground(Color.white);
		this.field_port.setBackground(Color.white);
		this.field_name.setBackground(Color.white);
	}

	
//	//SINGLETON PATTERN BLOCK
//	private JanelaSelectServer() {}
//	private static JanelaSelectServer jsv;
//	public static JanelaSelectServer getInstance() {
//		if (jsv == null) {
//			jsv = new JanelaSelectServer();
//		} 
//		return jsv;
//	}
}