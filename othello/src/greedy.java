//greedy class – this class handles the 
//human man move and greedy AI move. Mouse 
//events, and action events are passed to this 
//class from the othelloGameBoardPanel class which
//communicates with right panel, for this class to
//handle and process it will use handleMove to perform 
//the move if the move is valid from the player side,
//and it will generate the greedy AI move in response
//and perform that move using handleMove also. 

import java.awt.event.MouseEvent;


public class greedy 
{
	cell cell[];
	char virtualGameBoard[][];
	char playerTurn='b';
	handleMove hd=new handleMove();
	public greedy(cell cell[],char virtualGameBoard[][])
	{
		this.cell=cell;
		this.virtualGameBoard=virtualGameBoard;
	}
	
	public void clickRunIt(MouseEvent e) 
	{
		hd=new handleMove();
		for (int i = 0; i < 64; i++) 
		if (e.getSource() == cell[i])
		{	
			if(hd.validMove(virtualGameBoard,i,playerTurn))
			{	
				if(playerTurn=='b')
				{
					hd.performMove(virtualGameBoard,i,playerTurn);
					setCellsToVirtualGameBoard();
					playerTurn='w';
					
				}
				if(playerTurn=='w')
				{
					System.out.println("gree"+ greedyAICellMove());
					hd.performMove(virtualGameBoard, greedyAICellMove(), playerTurn);
					setCellsToVirtualGameBoard();
					playerTurn='b';
					
				}
			}
		}
	}
	public void runAITurn()
	{
		if(playerTurn=='w')
		{
			hd.performMove(virtualGameBoard, greedyAICellMove(), playerTurn);
			setCellsToVirtualGameBoard();
			playerTurn='b';
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
	public int greedyAICellMove()
	{
		int moveWithHighestTakings=0;
		int numberOfWhites=0;
		char temp[][]=virtualGameBoard.clone();
		for(int i=0;i<temp.length;i++)temp[i]=temp[i].clone();
		handleMove hd=new handleMove();
		for (int i = 0; i < 64; i++) 
		{
			if(hd.validMove(temp,i,'w'))
			{
				hd.performMove(temp,i,'w');
				
				if(countW(temp)>numberOfWhites)
					{
						numberOfWhites=countW(temp);
						moveWithHighestTakings=i;
					}
				temp=virtualGameBoard.clone();
				for(int u=0;u<temp.length;u++)temp[u]=temp[u].clone();
				
				
			}
		}
		highlightLastMove(moveWithHighestTakings);
		return moveWithHighestTakings;
	}
	
	public int countW(char vgb[][])
	{
		int temp=0;
		for (int i = 0; i < 64; i++) 
		{
			if(vgb[i/8][i%8]=='w')temp++;
			
		}
		return temp;
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
