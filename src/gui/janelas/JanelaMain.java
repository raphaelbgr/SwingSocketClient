package gui.janelas;

import gui.buttonlisteners.JButtonConnectListener;
import gui.buttonlisteners.JButtonDisconnectListener;
import gui.buttonlisteners.JButtonExitListener;
import gui.buttonlisteners.JButtonSelectServerListener;
import gui.buttonlisteners.JButtonSendServerListener;
import gui.jmenuListeners.JMenuExitListener;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class JanelaMain extends JFrame {

	FlowLayout layout 						= new FlowLayout();
	JTextField jtxt_send 					= null;
	
	@SuppressWarnings("unused")
	private JanelaSelectServer jsv			= null;
	private static JTextField jtxt_cnlog 	= null;
	public 	static JTextArea msg_list1		= null;
	public 	static JList<String> msg_list	= null;

	public JTextField getTextField() {
		return this.jtxt_send;
	}

	public JanelaMain(JanelaSelectServer jsv) {
		super("Fasolti Intranet Chat Client");
		
		this.jsv = jsv;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(490, 240);
		this.setLayout(layout);

		//Bloco de criação do Menu e seus botões
		JMenuBar jmb			= new JMenuBar();					//
		JMenu jmi				= new JMenu("File");				//
		JMenuItem jmexit 		= new JMenuItem("Exit");			//
		jmexit.addActionListener (new JMenuExitListener());			//
		this.setJMenuBar(jmb);										//
		jmb.add(jmi);												//
		jmi.add(jmexit);											//	
		msg_list				= new JList<String>();						//
		JLabel jlbl_msg 		= new JLabel("Message");			//
		JLabel jlbl_list 		= new JLabel("Chat log");			//
		JLabel jlbl_cnlog		= new JLabel("Connection log");		//
		jtxt_send				= new JTextField(30);				//TextField do campo Mensagem no escopo da classe. 
		setJtxt_cnlog			 (new JTextField(30));				//TextField Estático para log de conexão.
		JButton jbt_send		= new JButton("Send");				//
		JButton jbt_connect		= new JButton("Connect");			//
		JButton jbt_selserv 	= new JButton("Select Server");		//
		JButton jbt_disconn		= new JButton("Disconnect");		//
		JButton jbt_exit 		= new JButton("Exit");				//

		//Propiedades botão Send
		jbt_send.setFocusable(true);								//Atrubui Seleção com Tab
		jbt_send.setMnemonic('s'); 									//Atribui atalho Alt+S
		
		//Propiedades do botão do Menu Exit
		jmexit.setFocusable(true);
		jmexit.setMnemonic('e');
		
		//Propiedades do botão Exit
		jbt_exit.setFocusable(true);
		jbt_exit.setMnemonic('e');
		
		//Propiedades do botão Connect
		jbt_connect.setFocusable(true);
		jbt_connect.setMnemonic('c');
		
		//Propiedades do botão Disconnect
		jbt_disconn.setFocusable(true);
		jbt_disconn.setMnemonic('d');
		
		//Propiedades do botão Select Server
		jbt_selserv.setFocusable(true);
		jbt_selserv.setMnemonic('v');
		
		//Atribui a keystroke ENTER para o envio da mensagem quando o campo de texto do chat está em foco.
		jtxt_send.setAutoscrolls(true); //Propiedade do botão Send
		KeyStroke keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
		jtxt_send.registerKeyboardAction(new JButtonSendServerListener(this, jsv), keystroke,JComponent.WHEN_FOCUSED);

		jbt_send.addActionListener(		new JButtonSendServerListener(this, jsv));		//Comportamento do botão "Send"
		jbt_selserv.addActionListener(	new JButtonSelectServerListener(this,jsv));		//Comportamento do botão "Select Server"
		jbt_exit.addActionListener(		new JButtonExitListener(this,jsv));				//Comportamento do botão "Exit"
		jbt_connect.addActionListener(	new JButtonConnectListener(this,jsv));			//Comportamento para o botão "Connect"
		jbt_disconn.addActionListener(	new JButtonDisconnectListener(this,jsv));		//Comportamento para o botão "Disconnect"

		getJtxt_cnlog().setEditable(false);											//Desabilita o campo para edição
		getJtxt_cnlog().setBackground(Color.LIGHT_GRAY);								//Muda a cor do campo "Connection log" para cinza

		//Cria Borda para a JTextArea da Lista de mensagens
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		msg_list.setBorder(BorderFactory.createCompoundBorder(border, 
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		jtxt_send.setBorder(BorderFactory.createCompoundBorder(border, 
				BorderFactory.createEmptyBorder(1, 1, 1, 1)));

		msg_list.setBorder(border);		//
//		msg_list.setEditable(false);	//Desabilita edição para a lista de mensagens

		//NEEDS HELP CREATING THIS JLIST TO STORE THE BROADCASTED MESSAGES FROM SERVER!!!!
/*		msg_list = new JList(new Message [30] ); //data has type Object[]
		msg_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		msg_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		msg_list.setSize(40, 180);
		msg_list.setSize(30, getDefaultCloseOperation());
		msg_list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setPreferredSize(new Dimension(250, 80));*/
		
		//Montagem do componente JFrame, em ordem
		this.add(jlbl_list);			//
		this.add(msg_list);				//
		this.add(jlbl_msg);				//JLabel da Mensagem
		this.add(jtxt_send);			//TextField da Mensagem à enviar
		this.add(jbt_send);				//
		this.add(jlbl_cnlog);			//
		this.add(getJtxt_cnlog());			//
		this.add(jbt_connect);			//
		this.add(jbt_selserv);			//
		this.add(jbt_disconn);			//
		this.add(jbt_exit);				//

		this.setResizable(false);		//Desabilita redimensionamento desta janela
		this.setVisible(true);			//Torna essa janela visível
		this.setAlwaysOnTop(true);		//Janela se sobrepoe

	}

	public JanelaMain getInstance() {
		return this;
	}

	/**
	 * @return the jtxt_cnlog
	 */
	public JTextField getJtxt_cnlog() {
		return jtxt_cnlog;
	}

	/**
	 * @param jtxt_cnlog the jtxt_cnlog to set
	 */
	public static void setJtxt_cnlog(JTextField jtxt_cnlog) {
		JanelaMain.jtxt_cnlog = jtxt_cnlog;
	}
	
}
