import java.awt.BorderLayout;
import javax.swing.*;
public class bottomPanel extends JPanel
{
	JTextArea textBox=new JTextArea(5,5);
	JScrollPane sp=new JScrollPane(textBox,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JLabel sayLabel=new JLabel("Say:");
	JTextField sayField=new JTextField(20);
	JButton sendButton=new JButton("Send");
	public bottomPanel()
	{
	}
	
	public void init()
	{
		textBox.setLineWrap(true);
		setLayout(new BorderLayout());
		JPanel southTB=new JPanel(new BorderLayout());
		southTB.add(sp,BorderLayout.CENTER);
		
		JPanel southSend=new JPanel(new BorderLayout());
		southSend.add(sayLabel,BorderLayout.WEST);
		southSend.add(sayField,BorderLayout.CENTER);
		southSend.add(sendButton,BorderLayout.EAST);
		
		add(southTB,BorderLayout.CENTER);
		add(southSend,BorderLayout.SOUTH);
	}
}
