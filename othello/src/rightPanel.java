import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sun.awt.RepaintArea;
public class rightPanel extends JPanel implements ActionListener, DocumentListener
{
	othelloGameBoardPanel othelloGameBoardPanel;
	JPanel jp=new JPanel(new BorderLayout());
	static JButton pass;
	static JButton undo;
	static JButton random;
	JPanel containText;
	String[] evaluationMethods = { "Weighted Matrix", "WM+Adaptable Weights", "Greedy", "Mobility", "Frontier","WMA+Greedy", "WMA+Mobility", "WMA+Frontier", "Customisable" };
	JComboBox comboEvaluation = new JComboBox(evaluationMethods);
	

	
	static JTextField gameTypeText=new JTextField(" 1 player vs 2 player");
	JTextArea turnText=new JTextArea("");
	JTextArea blackScoreText=new JTextArea(" Blacks score : 2");
	JTextArea whiteScoreText=new JTextArea(" Whites score : 2");
	JTextArea orangeScoreText=new JTextArea(" Oranges score : 0");
	JTextArea tempText1=new JTextArea("");
	JTextArea tempText2=new JTextArea("");
	JTextArea tempText3=new JTextArea("");
	JTextArea tempText4=new JTextArea("");
	JTextArea tempText5=new JTextArea("");
	JTextArea tempText6=new JTextArea("");
	JTextArea tempText7=new JTextArea("");
	JTextArea tempText8=new JTextArea("");
	JPanel readAheadPanel=new JPanel(new GridLayout(0,2));
	JLabel readAheadLabel=new JLabel(" Read ahead");
	

	int readAhead=1;
	JTextField readAheadText=new JTextField(Integer.toString(readAhead));
	JTextArea countDownText=new JTextArea("");
	onePlayerVsTwoPlayer onePlayerVsTwoPlayer;
	
	String gameType="1PV2P";
	GridLayout gl = new GridLayout(1,3);
	JPanel actionButtons= new JPanel(gl);
	
	JLabel weights=new JLabel("         Adjustable weights:");
	JPanel matrixP=new JPanel(new GridLayout(0,2));
	JPanel greedyP=new JPanel(new GridLayout(0,2));
	JPanel mobilityP=new JPanel(new GridLayout(0,2));
	JPanel frontierP=new JPanel(new GridLayout(0,2));
	JTextField matrixT=new JTextField("1");
	JTextField greedyT=new JTextField("1");
	JTextField mobilityT=new JTextField("34");
	JTextField frontierT=new JTextField("34");
	JButton changeMatrixWeights=new JButton("Change matrix weights");
	
	cell cell[];
	char[][] virtualGameBoard;
	java.util.Timer timer = new java.util.Timer();
	turnTimer turnTimer=new turnTimer(5,countDownText,othelloGameBoardPanel);
	
	public rightPanel(othelloGameBoardPanel othelloGameBoardPanel)
	{
		timer.schedule(turnTimer,0L, 1000L);
		this.othelloGameBoardPanel=othelloGameBoardPanel;
		virtualGameBoard=othelloGameBoardPanel.virtualGameBoard;
		//turnTimer=new turnTimer(5,countDownText,onePlayerVsTwoPlayer,othelloGameBoardPanel);
	}
	public void setGameType(onePlayerVsTwoPlayer oneTwo)
	{
		onePlayerVsTwoPlayer=oneTwo;
		turnTimer.set1P2P(oneTwo);
		gameType="1PV2P";
	}

	public static void setGameTypeText(String s)
	{
		gameTypeText.setText(s);
	}
	public void init()
	{
		readAheadText.getDocument().addDocumentListener(this);
		matrixT.getDocument().addDocumentListener(this);
		greedyT.getDocument().addDocumentListener(this);
		mobilityT.getDocument().addDocumentListener(this);
		frontierT.getDocument().addDocumentListener(this);
		changeMatrixWeights.addActionListener(this);
		
		setLayout(new BorderLayout());
		turnText.setText(" Blacks turn");
		
		comboEvaluation.setMaximumRowCount(10);
		comboEvaluation.setSelectedIndex(0);
		comboEvaluation.addActionListener(this);
		
		pass= new JButton("pass");
		undo=new JButton("undo");
		random=new JButton("random");
		undo.setEnabled(false);
		random.setEnabled(false);
		//l.setEnabled(false);
		JPanel s=new JPanel(new BorderLayout());
		s.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
		JLabel actions=new JLabel(" Actions:                                           ");
		actions.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.BLACK));
		add(jp);
		
		
		containText=new JPanel(new GridLayout(15,0));
		gameTypeText.setForeground(Color.BLUE);
		containText.add(gameTypeText);
		containText.add(turnText);
		containText.add(blackScoreText);
		containText.add(whiteScoreText);
		containText.add(orangeScoreText);
		containText.add(tempText1);
		containText.add(tempText2);
		containText.add(tempText3);
		containText.add(tempText4);
		containText.add(tempText5);
		containText.add(tempText6);
		containText.add(tempText7);
		containText.add(tempText8);
		containText.add(new JTextArea());
		
		
		
		countDownText.setForeground(Color.RED);
		Font font=new Font("ARIAL",Font.BOLD,12);
		countDownText.setFont(font);
		containText.add(countDownText);
		jp.add(containText);

		JPanel temp=new JPanel(new BorderLayout());
		temp.add(actions,BorderLayout.NORTH);
		temp.add(actionButtons);
		actionButtons.add(pass);
		s.add(temp,BorderLayout.SOUTH);
		jp.add(s,BorderLayout.SOUTH);
		
		pass.addActionListener(this);
		undo.addActionListener(this);
		random.addActionListener(this);
		
		matrixP.add(new JLabel(" Matrix"));
		matrixP.add(matrixT);
		greedyP.add(new JLabel(" Greedy"));
		greedyP.add(greedyT);
		mobilityP.add(new JLabel(" Mobility"));
		mobilityP.add(mobilityT);
		frontierP.add(new JLabel(" Frontier"));
		frontierP.add(frontierT);
	}
	public int numberOfBlacks(greedy n)
	{
		int no=0;
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				if(n.virtualGameBoard[i][j]=='b')
					no=no+1;
		return no;

	}
	
	public int numberOfWhites(greedy n)
	{
		int no=0;
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				if(n.virtualGameBoard[i][j]=='w')
					no=no+1;
		return no;
	}
	public int numberOfBlacks(miniMax n)
	{
		int no=0;
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				if(n.virtualGameBoard[i][j]=='b')
					no=no+1;
		return no;
	}
	
	public int numberOfBlacks(onePTwoPThreeP n)
	{
		int no=0;
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				if(n.virtualGameBoard[i][j]=='b')
					no=no+1;
		return no;
	}
	public int numberOfWhites(onePTwoPThreeP n)
	{
		int no=0;
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				if(n.virtualGameBoard[i][j]=='w')
					no=no+1;
		return no;
	}
	
	public int numberOfOranges(onePTwoPThreeP n)
	{
		int no=0;
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				if(n.virtualGameBoard[i][j]=='O')
					no=no+1;
		return no;
	}
	
	
	public int numberOfWhites(miniMax n)
	{
		int no=0;
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				if(n.virtualGameBoard[i][j]=='w')
					no=no+1;
		return no;
	}
	
	public int numberOfBlacks(onePlayerVsTwoPlayer n)
	{
		int no=0;
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				if(n.virtualGameBoard[i][j]=='b')
					no=no+1;
		return no;

	}
	
	public int numberOfWhites(onePlayerVsTwoPlayer n)
	{
		int no=0;
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				if(n.virtualGameBoard[i][j]=='w')
					no=no+1;
		return no;

	}
	public void addMiniMaxPanel()
	{
		containText.remove(tempText1);
		containText.remove(tempText2);
		readAheadLabel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
		readAheadPanel.add(readAheadLabel);
		readAheadPanel.add(readAheadText);
		containText.add(readAheadPanel, 5);
	
		containText.add(comboEvaluation, 6);
	}
	public void addMiniMaxCust()
	{
		
		containText.remove(tempText3);
		containText.remove(tempText4);
		containText.remove(tempText5);
		containText.remove(tempText6);
		containText.remove(tempText7);
		containText.remove(tempText8);
		
		containText.add(weights, 7);
		containText.add(matrixP, 8);
		containText.add(greedyP, 9);
		containText.add(mobilityP, 10);
		containText.add(frontierP, 11);
		containText.add(changeMatrixWeights, 12);
		if(othelloGameBoardPanel.miniMax.playerTurn=='b')othelloGameBoardPanel.rightPanel.setTurnText(" Blacks turn");
		else othelloGameBoardPanel.rightPanel.setTurnText(" Whites turn");
	}

	public void removeMiniMaxPanel()
	{
		containText.remove(readAheadPanel);
		containText.remove(comboEvaluation);
		
		containText.add(tempText1,5);
		containText.add(tempText2,6);
		
	}
	public void removeMiniMaxCust()
	{
		containText.remove(weights);
		containText.remove(matrixP);
		containText.remove(greedyP);
		containText.remove(mobilityP);
		containText.remove(frontierP);
		containText.remove(changeMatrixWeights);
		
		containText.add(tempText3,7);
		containText.add(tempText4,8);
		containText.add(tempText5,9);
		containText.add(tempText6,10);
		containText.add(tempText7,11);
		containText.add(tempText8,12);
		
	}
	
	public void setPassOnly()
	{
		actionButtons.removeAll();
		actionButtons.add(pass);
	}
	public void setPassUndoRandom()
	{
		actionButtons.removeAll();
		actionButtons.add(pass);
		actionButtons.add(undo);
		undo.setEnabled(false);
		random.setEnabled(false);
		actionButtons.add(random);
	}
	public void setTurnText(String s)
	{
		turnText.setText(s);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		othelloGameBoardPanel.actionFromRightPanel(e);
		
	}
	@Override
	public void changedUpdate(DocumentEvent e) 
	{
		
	}
	@Override
	public void insertUpdate(DocumentEvent e) 
	{
		othelloGameBoardPanel.documentFromRightPanel(e);
	}
	@Override
	public void removeUpdate(DocumentEvent e) 
	{

	}
}
  