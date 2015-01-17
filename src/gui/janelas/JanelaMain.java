package gui.janelas;

import gui.buttonlisteners.JButtonConnectListener;
import gui.buttonlisteners.JButtonDisconnectListener;
import gui.buttonlisteners.JButtonExitListener;
import gui.buttonlisteners.JButtonSelectServerListener;
import gui.buttonlisteners.JButtonSendServerListener;
import gui.jmenuListeners.JMenuExitListener;
import gui.updatelogs.ConnectionLog;
import gui.updatelogs.TextLog;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import sendable.Message;

@SuppressWarnings("serial")
public class JanelaMain extends JFrame {

	private FlowLayout layout 							= new FlowLayout();
	private JTextField jtxt_send 						= null;
	private JanelaSelectServer jsv 						= null;

	//CUSTOM SWING COMPONENTS
	private ConnectionLog cn_log 				= new ConnectionLog(this, 30);
	private TextLog msg_list					= TextLog.getInstance();

	public JTextField getTextField() {
		return this.jtxt_send;
	}

	private JanelaMain(JanelaSelectServer jsv) {
		super("Open Source Chat Client");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(490, 290);
		this.setLayout(layout);

		//Bloco de cria��o do Menu e seus bot�es
		JMenuBar jmb			= new JMenuBar();					//
		JMenu jmi				= new JMenu("File");				//
		JMenuItem jmexit 		= new JMenuItem("Exit");			//
		jmexit.addActionListener (new JMenuExitListener());			//
		this.setJMenuBar(jmb);										//
		jmb.add(jmi);												//
		jmi.add(jmexit);											//
		JLabel jlbl_msg 		= new JLabel("Message");			//
		JLabel jlbl_list 		= new JLabel("Chat log");			//
		JLabel jlbl_cnlog		= new JLabel("Connection log");		//
		jtxt_send				= new JTextField(30);				//TextField do campo Mensagem no escopo da classe. 
		JButton jbt_send		= new JButton("Send");				//
		JButton jbt_connect		= new JButton("Connect");			//
		JButton jbt_selserv 	= new JButton("Select Server");		//
		JButton jbt_disconn		= new JButton("Disconnect");		//
		JButton jbt_exit 		= new JButton("Exit");				//

		//Propiedades bot�o Send
		jbt_send.setFocusable(true);								//Atrubui Sele��o com Tab
		jbt_send.setMnemonic('s'); 									//Atribui atalho Alt+S

		//Propiedades do bot�o do Menu Exit
		jmexit.setFocusable(true);
		jmexit.setMnemonic('e');

		//Propiedades do bot�o Exit
		jbt_exit.setFocusable(true);
		jbt_exit.setMnemonic('e');

		//Propiedades do bot�o Connect
		jbt_connect.setFocusable(true);
		jbt_connect.setMnemonic('c');

		//Propiedades do bot�o Disconnect
		jbt_disconn.setFocusable(true);
		jbt_disconn.setMnemonic('d');

		//Propiedades do bot�o Select Server
		jbt_selserv.setFocusable(true);
		jbt_selserv.setMnemonic('v');

		//Atribui a keystroke ENTER para o envio da mensagem quando o campo de texto do chat est� em foco.
		jtxt_send.setAutoscrolls(true); //Propiedade do bot�o Send
		KeyStroke keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
		jtxt_send.registerKeyboardAction(new JButtonSendServerListener(this, jsv), keystroke,JComponent.WHEN_FOCUSED);

		jbt_send.addActionListener(		new JButtonSendServerListener(this, jsv));		//Comportamento do bot�o "Send"
		jbt_selserv.addActionListener(	new JButtonSelectServerListener(this,jsv));		//Comportamento do bot�o "Select Server"
		jbt_exit.addActionListener(		new JButtonExitListener(this,jsv));				//Comportamento do bot�o "Exit"
		jbt_connect.addActionListener(	new JButtonConnectListener(this,jsv));			//Comportamento para o bot�o "Connect"
		jbt_disconn.addActionListener(	new JButtonDisconnectListener(this,jsv));		//Comportamento para o bot�o "Disconnect"

		getCn_log().setEditable(false);												//Desabilita o campo para edi��o
		getCn_log().setBackground(Color.LIGHT_GRAY);								//Muda a cor do campo "Connection log" para cinza

		//Cria Borda para a JTextArea da Lista de mensagens
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		msg_list.setBorder(BorderFactory.createCompoundBorder(border, 
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		jtxt_send.setBorder(BorderFactory.createCompoundBorder(border, 
				BorderFactory.createEmptyBorder(1, 1, 1, 1)));

		msg_list.setBorder(border);


		//Montagem do componente JFrame, em ordem		
		this.add(jlbl_list);						//
		this.add(this.msg_list.getScrollPane());	//Adds the ScrolledPane JTextArea
		this.add(jlbl_msg);							//JLabel da Mensagem
		this.add(jtxt_send);						//TextField da Mensagem � enviar
		this.add(jbt_send);							//
		this.add(jlbl_cnlog);						//
		this.add(getCn_log());						//
		this.add(jbt_connect);						//
		this.add(jbt_selserv);						//
		this.add(jbt_disconn);						//
		this.add(jbt_exit);							//

		this.setResizable(false);					//Desabilita redimensionamento desta janela
		this.setVisible(true);						//Torna essa janela vis�vel
		this.setAlwaysOnTop(true);					//Janela se sobrepoe
		
		this.jsv = jsv;

	}

	public void addMessageToChatLog(Message m) {
	}


	//SINGLETON PATTERN BLOCK
	private static JanelaMain jam;
	public static JanelaMain getInstance() {
		if (JanelaMain.jam == null) {
			jam = new JanelaMain(new JanelaSelectServer("Address Input"));
		}
		return jam;
	}

	public ConnectionLog getCn_log() {
		return cn_log;
	}

	public void setCn_log(ConnectionLog cn_log) {
		this.cn_log = cn_log;
	}

	public TextLog getMsg_list() {
		return msg_list;
	}

	public void setMsg_list(TextLog msg_list) {
		this.msg_list = msg_list;
	}

	public JanelaSelectServer getJsv() {
		return jsv;
	}

}
