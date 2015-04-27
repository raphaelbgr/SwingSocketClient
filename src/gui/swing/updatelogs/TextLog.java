package gui.swing.updatelogs;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextLog extends JTextArea {

	private static final long serialVersionUID = 1L;
	JScrollPane sp = null;

	public TextLog() {
		JScrollPane scrollPane = new JScrollPane(this);
		scrollPane.setPreferredSize(new Dimension(380, 100));
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.setEditable(false);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.sp = scrollPane;
	}

	public JScrollPane getScrollPane() {
		return sp;
	}
	
	public void addMessage(String s) {
		this.append(s + "\n");
		this.setCaretPosition(this.getDocument().getLength());
	}

//	//SINGLETON PATTERN BLOCK
//	private static TextLog tl;
//	public static TextLog getInstance() {
//		if (tl == null){
//			return new TextLog();
//		} else {
//			return tl;
//		}
//	}

}
