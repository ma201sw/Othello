//othelloGameBoardPanel class – this class is
//the game board, the functionality of the game
//is shown in this class. It displays the board
//and the results of AI/Human moves, on the corresponding cells.
//It communicates with rightPanel to update its information.
//It communicates with different player classes to update the
//board with the move.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;

public class othelloGameBoardPanel extends JPanel implements MouseListener
{
	JPanel gamePanel = new JPanel();//game board
	JPanel boardPanel = new JPanel();//contains gameboard and borders
	cell cell[] = new cell[64];
	int frameWidth;
	JPanel topCo;
	JLabel coordL[]=new JLabel[8];
	char virtualGameBoard[][]=new char [][]{
			{'o','o','o','o','o','o','o','o'},
			{'o','o','o','o','o','o','o','o'},
			{'o','o','o','o','o','o','o','o'},
			{'o','o','o','w','b','o','o','o'},
			{'o','o','o','b','w','o','o','o'},
			{'o','o','o','o','o','o','o','o'},
			{'o','o','o','o','o','o','o','o'},
			{'o','o','o','o','o','o','o','o'}
	};
	//char playerTurn='b';//b blacks turn w whites turn

	greedy greedy=new greedy(cell,virtualGameBoard);
	rightPanel rightPanel=new rightPanel(this);
	onePlayerVsTwoPlayer onePlayerVsTwoPlayer=new onePlayerVsTwoPlayer(cell,virtualGameBoard,rightPanel);
	onePTwoPThreeP onePTwoPThreeP=new onePTwoPThreeP(cell,virtualGameBoard,rightPanel);
	miniMax miniMax=new miniMax(cell,virtualGameBoard);
	String gameType="1PV2P";
	public othelloGameBoardPanel()
	{
		rightPanel.init();
		rightPanel.setGameType(onePlayerVsTwoPlayer);

	}
	public void setGameType(String s)
	{
		gameType=s;
	}

	public void setFrameWidth(int fw)
	{
		frameWidth = fw;


	}
	public void removeAction()
	{
		cell[20].removeMouseListener(this);
	}
	public void removeHighlighted()
	{
		for (int i=0;i<64;i++)
		{
			cell[i].lastMove=false;
			cell[i].repaint();
		}
	}
	public void newGame()
	{

		if(gameType.equalsIgnoreCase("1PV2P"))
		{
			onePlayerVsTwoPlayer.setDefaultBoard();
			onePlayerVsTwoPlayer.playerTurn='b';
			onePlayerVsTwoPlayer.setCellsToVirtualGameBoard();
			rightPanel.setTurnText(" Blacks turn");
			rightPanel.setGameType(onePlayerVsTwoPlayer);
			onePlayerVsTwoPlayer.previousBoardsB=new LinkedList();
			onePlayerVsTwoPlayer.previousBoardsW=new LinkedList();
			onePlayerVsTwoPlayer.lastTurnB=new LinkedList();
			onePlayerVsTwoPlayer.lastTurnW=new LinkedList();
			removeHighlighted();
			rightPanel.removeMiniMaxPanel();
			rightPanel.removeMiniMaxCust();

		}
		if(gameType.equalsIgnoreCase("greedy"))
		{
			greedy.setDefaultBoard();
			greedy.playerTurn='b';
			greedy.setCellsToVirtualGameBoard();
			rightPanel.setTurnText(" Blacks turn");
			removeHighlighted();
			rightPanel.removeMiniMaxPanel();
			rightPanel.removeMiniMaxCust();
		}

		if(gameType.equalsIgnoreCase("miniMaxB"))
		{
			miniMax.setDefaultBoard();
			miniMax.playerTurn='b';
			miniMax.setCellsToVirtualGameBoard();
			rightPanel.setTurnText(" Blacks turn");
			miniMax.humanPlayer='b';
			removeHighlighted();
			rightPanel.addMiniMaxPanel();

		}
		if(gameType.equalsIgnoreCase("miniMaxW"))
		{
			miniMax.setDefaultBoard();
			rightPanel.setTurnText(" whitesTurn");
			miniMax.playerTurn='b';
			miniMax.playerTurn='w';
			miniMax.humanPlayer='w';
			removeHighlighted();
			miniMax.setDefaultEvaluationMatrix();
			miniMax.doAIMove('b');
			rightPanel.addMiniMaxPanel();


		}
		if(gameType.equalsIgnoreCase("1PV2PV3P"))
		{
			onePTwoPThreeP.setDefaultBoard();
			onePTwoPThreeP.playerTurn='b';
			onePTwoPThreeP.setCellsToVirtualGameBoard();
			rightPanel.setTurnText(" Blacks turn");
			rightPanel.orangeScoreText.setText(" Oranges score : "+numberOfOranges(onePTwoPThreeP));
			removeHighlighted();
		}
	}

	public int getFrameWidth()
	{
		return frameWidth;
	}

	public void init()
	{
		setLayout(new BorderLayout());
		boardPanel.setLayout(new BorderLayout());
		boardPanel.setBackground(new Color(100,0,0));

		addRightAndBottomBorder();

		addGameBoard();
		add(boardPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
		addLeftCoord();
		addTopCoord();
	}


	public void addRightAndBottomBorder()
	{
		JLabel bottomBorder=new JLabel("A");
		bottomBorder.setForeground(new Color(100,0,0));

		JLabel rightBorder=new JLabel("1");
		rightBorder.setForeground(new Color(100,0,0));

		JPanel rb=new JPanel(new GridLayout(8,0));
		rb.setBorder(BorderFactory.createEmptyBorder(
                0, //top
                5, //left
                0, //bottom
                0) //right
        );
		rb.add(rightBorder);
		rb.setBackground(new Color(100,0,0));
		rightPanel.add(rb,BorderLayout.WEST);

		JPanel bp=new JPanel(new GridLayout());
		bp.setBorder(BorderFactory.createEmptyBorder(
                0, //top
                0, //left
                0, //bottom
                0) //right
        );
		bp.setBackground(new Color(100,0,0));
		bp.add(bottomBorder);
		boardPanel.add(bp,BorderLayout.SOUTH);
	}

	public void addGameBoard()
	{
		gamePanel.setLayout(new GridLayout(8, 8));
		gamePanel.setBackground(new Color(100,0,0));

		for (int i = 0; i < 64; i++)
		{
			cell[i] = new cell();
			cell[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
			gamePanel.add(cell[i]);
			cell[i].addMouseListener(this);
			ImageIcon emptyIcon=createImageIcon("empty cell.jpg");
			cell[i].setIcon(emptyIcon);
		}
		setCellsToVirtualGameBoard();
		boardPanel.add(gamePanel, BorderLayout.CENTER);
	}

	public void addLeftCoord()
	{
		JPanel leftCo=new JPanel(new GridLayout(8,0));
		leftCo.setBackground(new Color(100,0,0));
		JLabel coordN[]=new JLabel[8];
		for(int i=0;i<8;i++)
		{
		coordN[i]=new JLabel();
		coordN[i].setForeground(new Color(170,170,0));
		coordN[i].setText(Integer.toString(i+1));
		coordN[i].setBorder(BorderFactory.createEmptyBorder(
                0, //top
                5, //left
                0, //bottom
                5) //right
        );
		leftCo.add(coordN[i]);
		}
		boardPanel.add(leftCo,BorderLayout.WEST);

	}

	public void addTopCoord()
	{
//		top coordinates letters
		topCo = new JPanel(new GridLayout());
		topCo.setBackground(new Color(100,0,0));
		for (int i = 0; i < 8; i++)
		{
			coordL[i] = new JLabel();
			coordL[i].setForeground(new Color(170,170,0));


			topCo.add(coordL[i]);
		}
		int offset=frameWidth/16;
		coordL[0].setText("A");
		coordL[1].setText("B");
		coordL[2].setText("C");
		coordL[3].setText("D");
		coordL[4].setText("E");
		coordL[5].setText("F");
		coordL[6].setText("G");
		coordL[7].setText("H");

	    setTopCoordLeftOffSet(offset);
		boardPanel.add(topCo, BorderLayout.NORTH);
	}

	public void setTopCoordLeftOffSet(int offSet)
	{
		for(int i=0;i<8;i++)
		{
		coordL[i].setBorder(BorderFactory.createEmptyBorder(
                0, //top
                offSet, //left
                0, //bottom
                0) //right
        );
		offSet=offSet-3;
		}
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = othelloFrame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public void paintComponent(Graphics g) {

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
	public void actionFromRightPanel(ActionEvent e)
	{
		if(e.getSource()==rightPanel.comboEvaluation)
		{
			JComboBox cb = (JComboBox)e.getSource();
			miniMax.selectedEvaluationMethod=(String)cb.getSelectedItem();
			miniMax.setDefaultEvaluationMatrix();
			if(miniMax.selectedEvaluationMethod.equalsIgnoreCase("Customisable"))rightPanel.addMiniMaxCust();
			else rightPanel.removeMiniMaxCust();
		}

		if(e.getSource()==rightPanel.changeMatrixWeights)
		{
			changeMatrix changeMatrix=new changeMatrix(miniMax.staticEvaluationMatrix);
			System.out.println("change weights");
			changeMatrix.init();
		}

		if(e.getSource()==rightPanel.pass)
		{
			if(gameType.equalsIgnoreCase("1PV2P"))
			{
				if(onePlayerVsTwoPlayer.playerTurn=='b')onePlayerVsTwoPlayer.playerTurn='w';
				else onePlayerVsTwoPlayer.playerTurn='b';
				if(onePlayerVsTwoPlayer.playerTurn=='b')rightPanel.setTurnText(" Blacks turn");
				else rightPanel.setTurnText(" Whites turn");
				rightPanel.turnTimer.resetTime();
				rightPanel.blackScoreText.setText(" Blacks score : "+numberOfBlacks(onePlayerVsTwoPlayer));
				rightPanel.whiteScoreText.setText(" Whites score : "+numberOfWhites(onePlayerVsTwoPlayer));
			}
			if(gameType.equalsIgnoreCase("greedy"))
			{
				if(greedy.playerTurn=='b')greedy.playerTurn='w';
				else greedy.playerTurn='b';
				if(greedy.playerTurn=='b')rightPanel.setTurnText(" Blacks turn");
				else rightPanel.setTurnText(" Whites turn");
				greedy.runAITurn();
				rightPanel.blackScoreText.setText(" Blacks score : "+numberOfBlacks(greedy));
				rightPanel.whiteScoreText.setText(" Whites score : "+numberOfWhites(greedy));

			}
			if(gameType.equalsIgnoreCase("miniMaxB"))
			{
				miniMax.playerTurn='w';
				miniMax.doAIMove('w');
				miniMax.playerTurn='b';
				rightPanel.setTurnText(" Blacks turn");
				rightPanel.blackScoreText.setText(" Blacks score : "+numberOfBlacks(miniMax));
				rightPanel.whiteScoreText.setText(" Whites score : "+numberOfWhites(miniMax));
			}
			if(gameType.equalsIgnoreCase("miniMaxW"))
			{
				miniMax.playerTurn='b';
				miniMax.doAIMove('b');
				miniMax.playerTurn='w';
				rightPanel.setTurnText(" Whites turn");
				rightPanel.blackScoreText.setText(" Blacks score : "+numberOfBlacks(miniMax));
				rightPanel.whiteScoreText.setText(" Whites score : "+numberOfWhites(miniMax));

			}
			if(gameType.equalsIgnoreCase("1PV2PV3P"))
			{
				if(onePTwoPThreeP.playerTurn=='b')onePTwoPThreeP.playerTurn='w';
				else if (onePTwoPThreeP.playerTurn=='w')onePTwoPThreeP.playerTurn='O';
				else onePTwoPThreeP.playerTurn='b';

				if(onePTwoPThreeP.playerTurn=='b')rightPanel.setTurnText(" Blacks turn");
				else if(onePTwoPThreeP.playerTurn=='w')rightPanel.setTurnText(" Whites turn");
				else rightPanel.setTurnText(" Oranges turn");
				rightPanel.blackScoreText.setText(" Blacks score : "+numberOfBlacks(onePTwoPThreeP));
				rightPanel.whiteScoreText.setText(" Whites score : "+numberOfWhites(onePTwoPThreeP));
			}
		}
		if(e.getSource()==rightPanel.undo)
		{
			onePlayerVsTwoPlayer.actionRunUndo(e,rightPanel.turnTimer);
		}
		if(e.getSource()==rightPanel.random)
		{
			onePlayerVsTwoPlayer.actionRunRandom(e,rightPanel.turnTimer);
		}
	}

	public void documentFromRightPanel(DocumentEvent e)
	{
		if(e.getDocument()==rightPanel.readAheadText.getDocument())
		{
			miniMax.readAhead=Integer.parseInt(rightPanel.readAheadText.getText());
			miniMax.depthCopy=Integer.parseInt(rightPanel.readAheadText.getText());
			System.out.println(miniMax.readAhead);
		}
		if(e.getDocument()==rightPanel.matrixT.getDocument())
		{
			miniMax.evaluationFuntion.evaluationMatrixWeight=Integer.parseInt(rightPanel.matrixT.getText());
			System.out.println("works");
		}
		if(e.getDocument()==rightPanel.greedyT.getDocument())
		{
			miniMax.evaluationFuntion.greedyWeight=Integer.parseInt(rightPanel.greedyT.getText());
			System.out.println("works");
		}
		if(e.getDocument()==rightPanel.mobilityT.getDocument())
		{
			miniMax.evaluationFuntion.mobilityWeight=Integer.parseInt(rightPanel.mobilityT.getText());
			System.out.println("works");
		}
		if(e.getDocument()==rightPanel.frontierT.getDocument())
		{
			miniMax.evaluationFuntion.frontierWeight=Integer.parseInt(rightPanel.frontierT.getText());
			System.out.println("works");
		}
	}

	public void mouseClicked(MouseEvent e)
	{
		if(gameType.equalsIgnoreCase("1PV2P"))
		{
			onePlayerVsTwoPlayer.clickRunIt(e);
			rightPanel.blackScoreText.setText(" Blacks score : "+numberOfBlacks(onePlayerVsTwoPlayer));
			rightPanel.whiteScoreText.setText(" Whites score : "+numberOfWhites(onePlayerVsTwoPlayer));
			if(onePlayerVsTwoPlayer.getPlayerTurn()=='b')rightPanel.setTurnText(" Blacks turn");else rightPanel.setTurnText(" Whites turn");
		}
		if(gameType.equalsIgnoreCase("Greedy"))
		{
			greedy.clickRunIt(e);
			rightPanel.blackScoreText.setText(" Blacks score : "+numberOfBlacks(greedy));
			rightPanel.whiteScoreText.setText(" Whites score : "+numberOfWhites(greedy));
			if(greedy.playerTurn=='b')rightPanel.setTurnText(" Blacks turn");else rightPanel.setTurnText(" Whites turn");
		}
		if(gameType.equalsIgnoreCase("miniMaxB"))
		{
			miniMax.clickRunIt(e);
			rightPanel.blackScoreText.setText(" Blacks score : "+numberOfBlacks(miniMax));
			rightPanel.whiteScoreText.setText(" Whites score : "+numberOfWhites(miniMax));
			if(miniMax.playerTurn=='b')rightPanel.setTurnText(" Blacks turn");else rightPanel.setTurnText(" Whites turn");
		}
		if(gameType.equalsIgnoreCase("miniMaxW"))
		{
			miniMax.clickRunIt(e);
			rightPanel.blackScoreText.setText(" Blacks score : "+numberOfBlacks(miniMax));
			rightPanel.whiteScoreText.setText(" Whites score : "+numberOfWhites(miniMax));
			if(miniMax.playerTurn=='b')rightPanel.setTurnText(" Blacks turn");else rightPanel.setTurnText(" Whites turn");
		}
		if(gameType.equalsIgnoreCase("1PV2PV3P"))
		{
			onePTwoPThreeP.clickRunIt(e);
			rightPanel.blackScoreText.setText(" Blacks score : "+numberOfBlacks(onePTwoPThreeP));
			rightPanel.whiteScoreText.setText(" Whites score : "+numberOfWhites(onePTwoPThreeP));
			rightPanel.orangeScoreText.setText(" Oranges score : "+numberOfOranges(onePTwoPThreeP));
			if(onePTwoPThreeP.playerTurn=='b')rightPanel.setTurnText(" Blacks turn");
			else if(onePTwoPThreeP.playerTurn=='w') rightPanel.setTurnText(" Whites turn");
			else rightPanel.setTurnText(" Oranges turn");
		}
	}

	public void setCellsToVirtualGameBoard()
	{
		for (int i = 0; i < 64; i++)
		{
			if(virtualGameBoard[i/8][i%8]=='o')cell[i].setEmptyCell();
			else if(virtualGameBoard[i/8][i%8]=='w')cell[i].setWhiteCell();
			else cell[i].setBlackCell();
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}