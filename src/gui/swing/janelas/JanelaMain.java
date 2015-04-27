package gui.swing.janelas;

import gui.swing.WindowDataFacade;
import gui.swing.buttonlisteners.JButtonConnectListener;
import gui.swing.buttonlisteners.JButtonDisconnectListener;
import gui.swing.buttonlisteners.JButtonExitListener;
import gui.swing.buttonlisteners.JButtonSelectServerListener;
import gui.swing.buttonlisteners.JButtonSendServerListener;
import gui.swing.jmenuListeners.JMenuExitListener;
import gui.swing.onlinelist.OnlineUserList;
import gui.swing.updatelogs.LocalLogUpdater;
import gui.swing.updatelogs.ServerLogUpdater;
import gui.swing.updatelogs.TextLog;

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

	private FlowLayout layout 				= new FlowLayout();
	private JTextField jtxt_send 			= null;
	private JanelaSelectServer jsv 			= null;

	//CUSTOM SWING COMPONENTS
	private LocalLogUpdater cnlog 			= new LocalLogUpdater(this, 30);
	private ServerLogUpdater cnlog2 		= new ServerLogUpdater(this, 30);
	private TextLog msg_list				= new TextLog();
	private JButton jbt_connect;
	private JButton jbt_selserv;
	private JButton jbt_disconn;
	private JButton jbt_send;
	private	OnlineUserList le 				= new OnlineUserList();

	public JTextField getTextField() {
		return this.jtxt_send;
	}

	public JanelaMain(JanelaSelectServer jsv) {
		super("Open Source Chat Client");

		this.jsv = jsv;
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setSize(650, 290);
		this.setLayout(layout);

		//Bloco de criacao do Menu e seus botoes
		JMenuBar jmb			= new JMenuBar();					//
		JMenu jmi				= new JMenu("File");				//
		JMenuItem jmexit 		= new JMenuItem("Exit");			//
		jmexit.addActionListener (new JMenuExitListener());			//
		this.setJMenuBar(jmb);										//
		jmb.add(jmi);												//
		jmi.add(jmexit);											//
		JLabel jlbl_msg 		= new JLabel("Message");			//
		JLabel jlbl_list 		= new JLabel("Chat log");			//
		JLabel jlbl_cnlog		= new JLabel("                                           Local log");//
		JLabel jlbl_cnlog2		= new JLabel("                                    Server log");//
		jtxt_send				= new JTextField(30);				//TextField do campo Mensagem no escopo da classe. 
		this.jbt_send			= new JButton("Send");				//
		this.jbt_connect		= new JButton("Connect");			//
		this.jbt_selserv 		= new JButton("Select Server");		//
		this.jbt_disconn		= new JButton("Disconnect");		//
		JButton jbt_exit 		= new JButton("Exit");				//

		//Propiedades botao Send
		jbt_send.setFocusable(true);								//Atrubui Selecao com Tab
		jbt_send.setMnemonic('s'); 									//Atribui atalho Alt+S

		//Propiedades do botao do Menu Exit
		jmexit.setFocusable(true);
		jmexit.setMnemonic('e');

		//Propiedades do botao Exit
		jbt_exit.setFocusable(true);
		jbt_exit.setMnemonic('e');

		//Propiedades do botao Connect
		jbt_connect.setFocusable(true);
		jbt_connect.setMnemonic('c');

		//Propiedades do botao Disconnect
		jbt_disconn.setFocusable(true);
		jbt_disconn.setMnemonic('d');

		//Propiedades do botao Select Server
		jbt_selserv.setFocusable(true);
		jbt_selserv.setMnemonic('v');

		//Atribui a keystroke ENTER para o envio da mensagem quando o campo de texto do chat estiver em foco.
		jtxt_send.setAutoscrolls(true); 											//Propiedade do botao Send
		KeyStroke keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
		jtxt_send.registerKeyboardAction(new JButtonSendServerListener(this), keystroke,JComponent.WHEN_FOCUSED);

		jbt_send.addActionListener(		new JButtonSendServerListener(this));		//Comportamento do botao "Send"
		jbt_selserv.addActionListener(	new JButtonSelectServerListener(this));		//Comportamento do botao "Select Server"
		jbt_exit.addActionListener(		new JButtonExitListener(this));				//Comportamento do botao "Exit"
		jbt_connect.addActionListener(	new JButtonConnectListener(this,cnlog));	//Comportamento para o botao "Connect"
		jbt_disconn.addActionListener(	new JButtonDisconnectListener(this));		//Comportamento para o botao "Disconnect"

		getLocalConnectionLog().setEditable(false);									//Desabilita o campo para edi��o
		getLocalConnectionLog().setBackground(Color.LIGHT_GRAY);					//Muda a cor do campo "Connection log" para cinza
		getServerConnectionLog().setEditable(false);								//Desabilita o campo para edi��o
		getServerConnectionLog().setBackground(Color.LIGHT_GRAY);	
		
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
		this.add(le.getListExample());				//LISTA ONLINE USERS
		this.add(jlbl_msg);							//JLabel da Mensagem
		this.add(jtxt_send);						//TextField da Mensagem � enviar
		this.add(jbt_send);							//
		this.add(jlbl_cnlog);						//
		this.add(getLocalConnectionLog());			//
		this.add(jlbl_cnlog2);						//
		this.add(getServerConnectionLog());			//
		this.add(jbt_connect);						//
		this.add(jbt_selserv);						//
		this.add(jbt_disconn);						//
		this.add(jbt_exit);							//

		this.setResizable(false);					//Desabilita redimensionamento desta janela
		this.setVisible(true);						//Torna essa janela visivel
		this.setAlwaysOnTop(true);					//Janela se sobrepoe
		
		WindowDataFacade wdf = WindowDataFacade.getInstance();	//Guarda as instancias numa classe estatica
		wdf.loadInstance(this, this.getJsv());					//Guarda as instancias numa classe estatica
		jbt_disconn.setEnabled(false);
		jbt_send.setEnabled(false);
	}

	public void addMessageToChatLog(Message m) {
		
	}

	public LocalLogUpdater getLocalConnectionLog() {
		return cnlog;
	}

	public ServerLogUpdater getServerConnectionLog() {
		return cnlog2;
	}

	public void setCn_log(LocalLogUpdater cn_log) {
		this.cnlog = cn_log;
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
	
	public JButton getJbt_Connect() {
		return this.jbt_connect;
	}
	
	public JButton getJbt_SelServ() {
		return this.jbt_selserv;
	}
	
	public JButton getJbt_Disconn() {
		return this.jbt_disconn;
	}

	public JButton getJbt_send() {
		return jbt_send;
	}

	public OnlineUserList getLe() {
		return le;
	}
	
}
