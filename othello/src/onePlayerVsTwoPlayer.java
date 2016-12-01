//onePlayerVsTwoPlayer class – this class handles 
//1 player vs 2 player (human players), mouse events 
//and action events are passed to this class from the 
//othelloGameBoardPanel which communicates with right panel 
//class,for this class to process and handle, it will use 
//handleMove to perform the move if the move is valid. It 
//also uses turnTimer class as a timer when using the new additional rules.

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.*;

public class onePlayerVsTwoPlayer
{
	MouseEvent mEvent;
	cell cell[];
	
	char virtualGameBoard[][];
	char playerTurn='b';
	othelloGameBoardPanel othelloGameBoardPanel;
	LinkedList <char[][]>previousBoardsW =new LinkedList();
	LinkedList <char[][]>previousBoardsB =new LinkedList();
	LinkedList <Integer>lastTurnB=new LinkedList();
	LinkedList <Integer>lastTurnW=new LinkedList();

	rightPanel rightPanel;
	handleMove hd=new handleMove();
	
	public onePlayerVsTwoPlayer(cell cell[],char virtualGameBoard[][],rightPanel rightPanel)
	{
		this.cell=cell;
		this.virtualGameBoard=virtualGameBoard;
		this.rightPanel=rightPanel;
	}
	public char getPlayerTurn()
	{
		return playerTurn;
	}
	
	public void setGameBoard(othelloGameBoardPanel othelloGameBoardPanel)
	{
		this.othelloGameBoardPanel = othelloGameBoardPanel;
	}
	
	public void highlightLastMove(int lastMove)
	{
		for (int i=0;i<64;i++)
		{
			cell[i].lastMove=false;
			cell[i].repaint();
		}
		cell[lastMove].lastMove=true;
	}
	
	public void clickRunIt(MouseEvent e) 
	{
		handleMove hd=new handleMove();
		for (int i = 0; i < 64; i++) 
		if (e.getSource() == cell[i])
		{		
			if(hd.validMove(virtualGameBoard,i,playerTurn))
			{   
				rightPanel.turnTimer.resetTime();
				if(playerTurn=='b')
				{
					char temp[][]=virtualGameBoard.clone();
					for(int j=0;j<temp.length;j++)temp[j]=temp[j].clone();
					previousBoardsB.add(temp);	
					lastTurnB.add(new Integer(i));
					
				}
				else if(playerTurn=='w')
				{
					char temp[][]=virtualGameBoard.clone();
					for(int j=0;j<temp.length;j++)temp[j]=temp[j].clone();
					previousBoardsW.add(temp);
					lastTurnW.add(new Integer(i));
				}
				
				hd.performMove(virtualGameBoard,i,playerTurn);
				setCellsToVirtualGameBoard();
				
				highlightLastMove(i);
				
				if(playerTurn=='b')playerTurn='w';
				else playerTurn='b';

			}
		}
	}
	public void actionRunUndo(ActionEvent e,turnTimer turnTimer)
	{
		

			if(playerTurn=='b' && previousBoardsB.size()!=0/*playerTurnTrack.removeLast().charValue()=='b'*/)
			{	
				virtualGameBoard=previousBoardsB.removeLast();
				previousBoardsW.removeLast();
				setCellsToVirtualGameBoard();
				turnTimer.resetTime();
				rightPanel.undo.setEnabled(false);
				rightPanel.random.setEnabled(false);
				rightPanel.setTurnText(" Blacks turn");
			
				if(lastTurnB.size()>0)
				{
					cell[lastTurnB.removeLast().intValue()].setRedCell();
					lastTurnW.removeLast();
					highlightLastMove(lastTurnW.getLast().intValue());
				}
			}	
		
		else if(previousBoardsW.size()!=0 && playerTurn=='w') 
		{
			System.out.println("enter w");
			virtualGameBoard=previousBoardsW.removeLast();
			previousBoardsB.removeLast();
			setCellsToVirtualGameBoard();
			turnTimer.resetTime();
			rightPanel.undo.setEnabled(false);
			rightPanel.random.setEnabled(false);
			rightPanel.setTurnText(" Whites turn");
			
			if(lastTurnW.size()>0)
			{
				cell[lastTurnW.removeLast().intValue()].setRedCell();
				lastTurnB.removeLast();
				highlightLastMove(lastTurnB.getLast().intValue());
			}
		}
			
	}
	
	public LinkedList<Integer> listOfMoves(char VGB[][],char pT)
	{
		LinkedList <Integer>moves=new LinkedList<Integer>();
		for(int i=0;i<64;i++)
		{
			if(hd.validMove(VGB, i, pT))
				moves.add(new Integer(i));
		}
		return moves;
	}
	public void randomMove()
	{
		LinkedList <Integer>moves=listOfMoves(virtualGameBoard,playerTurn);
		Collections.shuffle(moves);
		hd.performMove(virtualGameBoard, moves.get(0).intValue(), playerTurn);
		setCellsToVirtualGameBoard();
		highlightLastMove(moves.get(0).intValue());
		if(playerTurn=='b')
		{
			playerTurn='w';
			rightPanel.setTurnText(" Whites turn");
		}
		else 
			{
				playerTurn='b';
				rightPanel.setTurnText(" Blacks turn");
			}
	}
	public void actionRunRandom(ActionEvent e,turnTimer turnTimer)
	{
		
		LinkedList <Integer>moves=listOfMoves(virtualGameBoard,playerTurn);
		Collections.shuffle(moves);
		
		
		if(playerTurn=='b')
		{
			char temp[][]=virtualGameBoard.clone();
			for(int j=0;j<temp.length;j++)temp[j]=temp[j].clone();
			previousBoardsB.add(temp);	
			lastTurnB.add(new Integer(moves.get(0).intValue()));
			rightPanel.setTurnText(" Whites turn");
		}
		else 
			{
				char temp[][]=virtualGameBoard.clone();
				for(int j=0;j<temp.length;j++)temp[j]=temp[j].clone();
				previousBoardsW.add(temp);
				lastTurnW.add(new Integer(moves.get(0).intValue()));
				rightPanel.setTurnText(" Blacks turn");
			}
		
		rightPanel.undo.setEnabled(false);
		rightPanel.random.setEnabled(false);
		turnTimer.resetTime();
		
		hd.performMove(virtualGameBoard, moves.get(0).intValue(), playerTurn);
		setCellsToVirtualGameBoard();
		highlightLastMove(moves.get(0).intValue());
		
		if(playerTurn=='b')playerTurn='w';
		else playerTurn='b';
	}
	public void setDefaultBoard()
	{
		virtualGameBoard=new char [][]{
				{'o','o','o','o','o','o','o','o'},
				{'o','o','o','o','o','o','o','o'},
				{'o','o','o','o','o','o','o','o'},
				{'o','o','o','w','b','o','o','o'},
				{'o','o','o','b','w','o','o','o'},
				{'o','o','o','o','o','o','o','o'},
				{'o','o','o','o','o','o','o','o'},
				{'o','o','o','o','o','o','o','o'}
		};
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
}
