//othelloFrame class - this class is the frame 
//and container class that contains the menu bars,
//othelloGameBoardPanel, and rightPanel.

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;


public class othelloFrame extends JFrame implements ComponentListener,ActionListener
{
	public int fWidth=620;
    public int fHeight=490;
	private JMenuBar menuBar=new JMenuBar();
	static othelloFrame f;
	othelloGameBoardPanel othelloGameBoardPane=new othelloGameBoardPanel();
	JMenu game;
	JMenuItem newGame;
	JMenu player;
	JMenuItem onePVTwoPH;
	JMenuItem onePVTwoPGreedy;
	JMenuItem onePVTwoPMin;
	JMenuItem onePVTwoPMinB;
	JMenuItem onePVTwoPMinW;
	JMenuItem onePVTwoVThreeP;
	JMenu rules;
	JMenuItem normalRules;
	JMenuItem timerRulesRandom;
	JMenuItem timerRulesRandomUndo;
	JMenuItem timer1;
	JMenuItem timer2;
	JMenuItem timer3;
	JMenuItem timer4;
	JMenuItem timer5;
	JMenuItem timer6;
	JMenu help;
	JMenuItem about;
	ImageIcon iconicon;
	public othelloFrame()
	{
		
		setJMenuBar(menuBar);
		game=new JMenu("Game");
			newGame=new JMenuItem("new game");
		player=new JMenu("Player");
			onePVTwoPH=new JMenuItem("player 1 vs player 2(human)");
			onePVTwoPGreedy=new JMenuItem("player 1 vs player 2(cpu greedy)");
			onePVTwoPMin=new JMenu("player 1 vs player 2(cpu minmax)");
			onePVTwoPMinB=new JMenuItem("black");
			onePVTwoPMinW=new JMenuItem("white");
			onePVTwoVThreeP=new JMenuItem("player 1 vs player 2 vs player 3");
		rules=new JMenu("Rules");
			normalRules=new JMenuItem("normal rules");
			timerRulesRandom=new JMenu("timer rules (random move)");
				timer1=new JMenuItem("10 seconds");
				timer2=new JMenuItem("30 seconds");
				timer3=new JMenuItem("180 seconds");
			timerRulesRandomUndo=new JMenu("timer rules (random move and undo move)");
				timer4=new JMenuItem("10 seconds");
				timer5=new JMenuItem("30 seconds");
				timer6=new JMenuItem("180 seconds");
		help=new JMenu("Help");
			about=new JMenuItem("About");
		
		//newGame.setEnabled(false);
		//newGame.setVerticalTextPosition(AbstractButton.CENTER);
        //newGame.setHorizontalTextPosition(AbstractButton.LEADING);
		iconicon=createImageIcon("tick.gif");
		//newGame.setIcon(i);
		onePVTwoPH.setIcon(iconicon);
		game.add(newGame);
		player.add(onePVTwoPH);
		player.add(onePVTwoPGreedy);
		player.add(onePVTwoPMin);
		onePVTwoPMin.add(onePVTwoPMinB);
		onePVTwoPMin.add(onePVTwoPMinW);
		//player.add(onePVTwoPGenetic);
		player.add(onePVTwoVThreeP);
		rules.add(normalRules);
		rules.add(timerRulesRandom);
		timerRulesRandom.add(timer1);
		timerRulesRandom.add(timer2);
		timerRulesRandom.add(timer3);
		rules.add(timerRulesRandomUndo);
		timerRulesRandomUndo.add(timer4);
		timerRulesRandomUndo.add(timer5);
		timerRulesRandomUndo.add(timer6);
		help.add(about);
		menuBar.add(game);
		menuBar.add(player);
		menuBar.add(rules);
		menuBar.add(help);
		
		newGame.addActionListener(this);
		onePVTwoPH.addActionListener(this);;
		onePVTwoPGreedy.addActionListener(this);;
		onePVTwoPMin.addActionListener(this);;
		onePVTwoPMinB.addActionListener(this);
		onePVTwoPMinW.addActionListener(this);
		onePVTwoVThreeP.addActionListener(this);;
		normalRules.addActionListener(this);;
		timerRulesRandom.addActionListener(this);;
		timerRulesRandomUndo.addActionListener(this);;
		timer1.addActionListener(this);
		timer2.addActionListener(this);
		timer3.addActionListener(this);
		timer4.addActionListener(this);
		timer5.addActionListener(this);
		timer6.addActionListener(this);
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
	public void init()
	{
		
		f.addComponentListener(this);
		setSize(fWidth,fHeight);
		
		
		othelloGameBoardPane.setFrameWidth(f.getWidth());
		othelloGameBoardPane.init();
		getContentPane().add(othelloGameBoardPane,BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		othelloGameBoardPane.rightPanel.turnTimer.setGameBoard(othelloGameBoardPane);
		othelloGameBoardPane.onePlayerVsTwoPlayer.setGameBoard(othelloGameBoardPane);
		
		
	}
	
	public static void main(String args [])
	{	
		f=new othelloFrame();
		f.init();
		
		
			
	}

	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	boolean delay=true;//to stop the first auto resize;
	public void componentResized(ComponentEvent e) //set the top coords to stretch
	{
		if(e.getComponent()==f)
			{
				othelloGameBoardPane.setFrameWidth(f.getWidth());

				int leftOffSet=getWidth();
				leftOffSet=leftOffSet/16;
				while(!delay) {othelloGameBoardPane.setTopCoordLeftOffSet(leftOffSet);break;};
				delay=false;
			
			}
	}

	public void componentShown(ComponentEvent e) {
	}
	@Override
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==newGame)
		{
			othelloGameBoardPane.newGame();	
		}
		if(e.getSource()==onePVTwoPH)
		{
			othelloGameBoardPane.setGameType("1PV2P");
			othelloGameBoardPane.newGame();
			onePVTwoPH.setIcon(iconicon);
			onePVTwoPGreedy.setIcon(null);
			onePVTwoPMin.setIcon(null);
			onePVTwoVThreeP.setIcon(null);
			onePVTwoPMinB.setIcon(null);
			onePVTwoPMinW.setIcon(null);
			rules.setEnabled(true);
			othelloGameBoardPane.rightPanel.setPassOnly();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="normal";
			normalRules.setIcon(iconicon);
			timerRulesRandom.setIcon(null);
			timerRulesRandomUndo.setIcon(null);
			timer1.setIcon(null);
			timer2.setIcon(null);
			timer3.setIcon(null);
			timer4.setIcon(null);
			timer5.setIcon(null);
			timer6.setIcon(null);
			repaint();
		}
		if(e.getSource()==onePVTwoPGreedy)
		{
			othelloGameBoardPane.setGameType("greedy");
			othelloGameBoardPane.newGame();
			onePVTwoPH.setIcon(null);
			onePVTwoPGreedy.setIcon(iconicon);
			onePVTwoPMin.setIcon(null);
			onePVTwoVThreeP.setIcon(null);
			onePVTwoPMinB.setIcon(null);
			onePVTwoPMinW.setIcon(null);
			rules.setEnabled(false);
			othelloGameBoardPane.rightPanel.setPassOnly();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="normal";
			//othelloGameBoardPane.rightPanel.removeMiniMaxPanel();
			repaint();
			
		}
		if(e.getSource()==onePVTwoPMinB)
		{	
			othelloGameBoardPane.setGameType("miniMaxB");
			othelloGameBoardPane.newGame();
			onePVTwoPH.setIcon(null);
			onePVTwoPGreedy.setIcon(null);
			onePVTwoPMin.setIcon(iconicon);
			onePVTwoPMinB.setIcon(iconicon);
			onePVTwoPMinW.setIcon(null);
			onePVTwoVThreeP.setIcon(null);
			rules.setEnabled(false);
			othelloGameBoardPane.rightPanel.setPassOnly();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="normal";
			repaint();
			
		}
		if(e.getSource()==onePVTwoPMinW)
		{
			othelloGameBoardPane.setGameType("miniMaxW");
			othelloGameBoardPane.newGame();
			onePVTwoPH.setIcon(null);
			onePVTwoPGreedy.setIcon(null);
			onePVTwoPMin.setIcon(iconicon);
			onePVTwoPMinW.setIcon(iconicon);
			onePVTwoVThreeP.setIcon(null);
			onePVTwoPMinB.setIcon(null);
			rules.setEnabled(false);
			othelloGameBoardPane.rightPanel.setPassOnly();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="normal";
			//othelloGameBoardPane.rightPanel.addMiniMaxPanel();
			repaint();
			
		}
		if(e.getSource()==onePVTwoVThreeP)
		{
			othelloGameBoardPane.setGameType("1PV2PV3P");
			othelloGameBoardPane.newGame();
			onePVTwoPH.setIcon(null);
			onePVTwoPGreedy.setIcon(null);
			onePVTwoPMin.setIcon(null);
			onePVTwoVThreeP.setIcon(iconicon);
			onePVTwoPMinB.setIcon(null);
			onePVTwoPMinW.setIcon(null);
			rules.setEnabled(false);
			othelloGameBoardPane.rightPanel.setPassOnly();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="normal";
			repaint();
			
		}
		if(e.getSource()==normalRules)
		{
			normalRules.setIcon(iconicon);
			othelloGameBoardPane.newGame();
			timerRulesRandom.setIcon(null);
			timerRulesRandomUndo.setIcon(null);
			timer1.setIcon(null);
			timer2.setIcon(null);
			timer3.setIcon(null);
			timer4.setIcon(null);
			timer5.setIcon(null);
			timer6.setIcon(null);
			othelloGameBoardPane.rightPanel.setPassOnly();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="normal";
			
		}
		if(e.getSource()==timerRulesRandom)
		{
			normalRules.setIcon(null);
			timerRulesRandom.setIcon(iconicon);
			timerRulesRandomUndo.setIcon(null);
			
			
		}
		if(e.getSource()==timerRulesRandomUndo)
		{
			normalRules.setIcon(null);
			timerRulesRandom.setIcon(null);
			timerRulesRandomUndo.setIcon(iconicon);
			
		}
		if(e.getSource()==timer1)
		{
			normalRules.setIcon(null);
			othelloGameBoardPane.newGame();
			timerRulesRandom.setIcon(iconicon);
			timerRulesRandomUndo.setIcon(null);
			timer1.setIcon(null);
			timer2.setIcon(null);
			timer3.setIcon(null);
			timer4.setIcon(null);
			timer5.setIcon(null);
			timer6.setIcon(null);
			timer1.setIcon(iconicon);
			othelloGameBoardPane.rightPanel.setPassOnly();
			othelloGameBoardPane.rightPanel.turnTimer.setTime(10);
			othelloGameBoardPane.rightPanel.turnTimer.resetTime();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="random";
			
			
		}
		if(e.getSource()==timer2)
		{
			normalRules.setIcon(null);
			othelloGameBoardPane.newGame();
			timerRulesRandom.setIcon(iconicon);
			timerRulesRandomUndo.setIcon(null);
			timer1.setIcon(null);
			timer2.setIcon(null);
			timer3.setIcon(null);
			timer4.setIcon(null);
			timer5.setIcon(null);
			timer6.setIcon(null);
			timer2.setIcon(iconicon);
			othelloGameBoardPane.rightPanel.setPassOnly();
			othelloGameBoardPane.rightPanel.turnTimer.setTime(30);
			othelloGameBoardPane.rightPanel.turnTimer.resetTime();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="random";
		}
		if(e.getSource()==timer3)
		{
			normalRules.setIcon(null);
			othelloGameBoardPane.newGame();
			timerRulesRandom.setIcon(iconicon);
			timerRulesRandomUndo.setIcon(null);
			timer1.setIcon(null);
			timer2.setIcon(null);
			timer3.setIcon(null);
			timer4.setIcon(null);
			timer5.setIcon(null);
			timer6.setIcon(null);
			timer3.setIcon(iconicon);
			othelloGameBoardPane.rightPanel.setPassOnly();
			othelloGameBoardPane.rightPanel.turnTimer.setTime(180);
			othelloGameBoardPane.rightPanel.turnTimer.resetTime();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="random";
		}
		if(e.getSource()==timer4)
		{
			normalRules.setIcon(null);
			othelloGameBoardPane.newGame();
			timerRulesRandom.setIcon(null);
			timerRulesRandomUndo.setIcon(iconicon);
			timer1.setIcon(null);
			timer2.setIcon(null);
			timer3.setIcon(null);
			timer4.setIcon(null);
			timer5.setIcon(null);
			timer6.setIcon(null);
			timer4.setIcon(iconicon);
			othelloGameBoardPane.rightPanel.setPassUndoRandom();
			othelloGameBoardPane.rightPanel.turnTimer.setTime(10);
			othelloGameBoardPane.rightPanel.turnTimer.resetTime();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="randomUndo";
		}
		if(e.getSource()==timer5)
		{
			normalRules.setIcon(null);
			othelloGameBoardPane.newGame();
			timerRulesRandom.setIcon(null);
			timerRulesRandomUndo.setIcon(iconicon);
			timer1.setIcon(null);
			timer2.setIcon(null);
			timer3.setIcon(null);
			timer4.setIcon(null);
			timer5.setIcon(null);
			timer6.setIcon(null);
			timer5.setIcon(iconicon);
			othelloGameBoardPane.rightPanel.setPassUndoRandom();
			othelloGameBoardPane.rightPanel.turnTimer.setTime(30);
			othelloGameBoardPane.rightPanel.turnTimer.resetTime();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="randomUndo";

		}
		if(e.getSource()==timer6)
		{
			normalRules.setIcon(null);
			othelloGameBoardPane.newGame();
			timerRulesRandom.setIcon(null);
			timerRulesRandomUndo.setIcon(iconicon);
			timer1.setIcon(null);
			timer2.setIcon(null);
			timer3.setIcon(null);
			timer4.setIcon(null);
			timer5.setIcon(null);
			timer6.setIcon(null);
			timer6.setIcon(iconicon);
			othelloGameBoardPane.rightPanel.setPassUndoRandom();
			othelloGameBoardPane.rightPanel.turnTimer.setTime(180);
			othelloGameBoardPane.rightPanel.turnTimer.resetTime();
			othelloGameBoardPane.rightPanel.turnTimer.ruleType="randomUndo";

		}
		if(othelloGameBoardPane.gameType.equalsIgnoreCase("1PV2P"))
			rightPanel.setGameTypeText(" 1 player vs 2 player");
		else if (othelloGameBoardPane.gameType.equalsIgnoreCase("Greedy"))
			rightPanel.setGameTypeText(" 1 player vs greedy AI");
		else if(othelloGameBoardPane.gameType.equalsIgnoreCase("miniMaxW"))
			rightPanel.setGameTypeText(" 1 player vs minimax AI");
		else if(othelloGameBoardPane.gameType.equalsIgnoreCase("miniMaxB"))
			rightPanel.setGameTypeText(" 1 player vs minimax AI");
		else if(othelloGameBoardPane.gameType.equalsIgnoreCase("1PV2PV3P"))
			rightPanel.setGameTypeText(" 1 player vs 2 player vs 3 player");
	}
}
