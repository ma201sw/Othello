//onePTwoPThreeP class – this class is nearly the 
//same as onePlayerVsTwoPlayer class except instead 
//of two human players there are 3 human players.

import java.awt.event.MouseEvent;

public class onePTwoPThreeP 
{
	
	MouseEvent mEvent;
	cell cell[];
	char virtualGameBoard[][];
	char playerTurn='b';
	rightPanel rightPanel;
	handleMove hd=new handleMove();
	
	public onePTwoPThreeP(cell cell[],char virtualGameBoard[][],rightPanel rightPanel)
	{
		this.cell=cell;
		this.virtualGameBoard=virtualGameBoard;
		this.rightPanel=rightPanel;
	}
	
	public void setDefaultBoard()
	{
		virtualGameBoard=new char [][]{
				{'o','o','o','o','o','o','o','o'},
				{'o','o','o','o','o','o','o','o'},
				{'o','o','o','o','o','o','o','o'},
				{'o','o','o','b','w','O','o','o'},
				{'o','o','o','w','O','b','o','o'},
				{'o','o','o','O','b','w','o','o'},
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
			else if(virtualGameBoard[i/8][i%8]=='O')cell[i].setOrangeCell();
			else cell[i].setBlackCell();
		}
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
				hd.performMove(virtualGameBoard,i,playerTurn);
				setCellsToVirtualGameBoard();
				highlightLastMove(i);
				if(playerTurn=='b')playerTurn='w';
				else if(playerTurn=='w')playerTurn='O';
				else playerTurn='b';

			}
		}
	}
}
