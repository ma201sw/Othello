//handleMove class– when player classes like 
//onePlayerVsTwoPlayer or miniMax classes handle 
//mouse events, this class is used and it determines 
//if the move attempted is legal, and if it is legal, 
//this class will perform the move onto the virtual 
//game board, which in turn, the corresponding player 
//class and othelloGameBoardPanel will update the display 
//accordingly from the virtual game board.

import javax.swing.JPanel;

public class handleMove 
{
	public handleMove(){}
	
	public boolean validMove(char virtualGameBoard[][],int cellNo,char playerTurn)
	{
		if(validUp(virtualGameBoard,cellNo,playerTurn))return true;
		if(validDown(virtualGameBoard,cellNo,playerTurn))return true;
		if(validLeft(virtualGameBoard,cellNo,playerTurn))return true;
		if(validRight(virtualGameBoard,cellNo,playerTurn))return true;
		if(validUpRight(virtualGameBoard,cellNo,playerTurn))return true;
		if(validDownRight(virtualGameBoard,cellNo,playerTurn))return true;
		if(validDownLeft(virtualGameBoard,cellNo,playerTurn))return true;
		if(validUpLeft(virtualGameBoard,cellNo,playerTurn))return true;
		
		return false;
	}
	public void performMove(char virtualGameBoard[][],int cellNo,char playerTurn)
	{
		boolean temp=false;
		if(validUp(virtualGameBoard,cellNo,playerTurn))
		{
			for(int i=1;virtualGameBoard[(cellNo/8)-i][(cellNo%8)]!=playerTurn
			&& virtualGameBoard[(cellNo/8)-i][(cellNo%8)]!='o';i++)
			{
				temp=true;
				if(playerTurn=='b')virtualGameBoard[(cellNo/8)-i][(cellNo%8)]='b';
				else if(playerTurn=='w') virtualGameBoard[(cellNo/8)-i][(cellNo%8)]='w';
				else  virtualGameBoard[(cellNo/8)-i][(cellNo%8)]='O';
			}
		}
		if(validDown(virtualGameBoard,cellNo,playerTurn))
		{
			for(int i=1;virtualGameBoard[(cellNo/8)+i][(cellNo%8)]!=playerTurn
			&& virtualGameBoard[(cellNo/8)+i][(cellNo%8)]!='o';i++)
			{
				temp=true;
				if(playerTurn=='b')virtualGameBoard[(cellNo/8)+i][(cellNo%8)]='b';
				else if(playerTurn=='w') virtualGameBoard[(cellNo/8)+i][(cellNo%8)]='w';
				else virtualGameBoard[(cellNo/8)+i][(cellNo%8)]='O';
			}
		}
		if(validLeft(virtualGameBoard,cellNo,playerTurn))
		{
			for(int i=1;virtualGameBoard[(cellNo/8)][(cellNo%8)-i]!=playerTurn
			&& virtualGameBoard[(cellNo/8)][(cellNo%8)-i]!='o';i++)
			{
				temp=true;
				if(playerTurn=='b')virtualGameBoard[(cellNo/8)][(cellNo%8)-i]='b';
				else if(playerTurn=='w')virtualGameBoard[(cellNo/8)][(cellNo%8)-i]='w';
				else virtualGameBoard[(cellNo/8)][(cellNo%8)-i]='O';
			}
		}
		if(validRight(virtualGameBoard,cellNo,playerTurn))
		{
			for(int i=1;virtualGameBoard[(cellNo/8)][(cellNo%8)+i]!=playerTurn
			&& virtualGameBoard[(cellNo/8)][(cellNo%8)+i]!='o';i++)
			{
				temp=true;
				if(playerTurn=='b')virtualGameBoard[(cellNo/8)][(cellNo%8)+i]='b';
				else if(playerTurn=='w')virtualGameBoard[(cellNo/8)][(cellNo%8)+i]='w';
				else virtualGameBoard[(cellNo/8)][(cellNo%8)+i]='O';
			}
		}
		if(validUpRight(virtualGameBoard,cellNo,playerTurn))
		{
			for(int i=1;virtualGameBoard[(cellNo/8)-i][(cellNo%8)+i]!=playerTurn
			&& virtualGameBoard[(cellNo/8)-i][(cellNo%8)+i]!='o';i++)
			{
				temp=true;
				if(playerTurn=='b')virtualGameBoard[(cellNo/8)-i][(cellNo%8)+i]='b';
				else if(playerTurn=='w')virtualGameBoard[(cellNo/8)-i][(cellNo%8)+i]='w';
				else virtualGameBoard[(cellNo/8)-i][(cellNo%8)+i]='O';
			}
		}
		if(validDownRight(virtualGameBoard,cellNo,playerTurn))
		{
			for(int i=1;virtualGameBoard[(cellNo/8)+i][(cellNo%8)+i]!=playerTurn
			&& virtualGameBoard[(cellNo/8)+i][(cellNo%8)+i]!='o';i++)
			{
				temp=true;
				if(playerTurn=='b')virtualGameBoard[(cellNo/8)+i][(cellNo%8)+i]='b';
				else if(playerTurn=='w')virtualGameBoard[(cellNo/8)+i][(cellNo%8)+i]='w';
				else virtualGameBoard[(cellNo/8)+i][(cellNo%8)+i]='O';
			}
		}
		if(validDownLeft(virtualGameBoard,cellNo,playerTurn))
		{
			for(int i=1;virtualGameBoard[(cellNo/8)+i][(cellNo%8)-i]!=playerTurn
			&& virtualGameBoard[(cellNo/8)+i][(cellNo%8)-i]!='o';i++)
			{
				temp=true;
				if(playerTurn=='b')virtualGameBoard[(cellNo/8)+i][(cellNo%8)-i]='b';
				else if(playerTurn=='w')virtualGameBoard[(cellNo/8)+i][(cellNo%8)-i]='w';
				else virtualGameBoard[(cellNo/8)+i][(cellNo%8)-i]='O';
			}
		}
		if(validUpLeft(virtualGameBoard,cellNo,playerTurn))
		{
			for(int i=1;virtualGameBoard[(cellNo/8)-i][(cellNo%8)-i]!=playerTurn
			&& virtualGameBoard[(cellNo/8)-i][(cellNo%8)-i]!='o';i++)
			{
				temp=true;
				if(playerTurn=='b')virtualGameBoard[(cellNo/8)-i][(cellNo%8)-i]='b';
				else if(playerTurn=='w')virtualGameBoard[(cellNo/8)-i][(cellNo%8)-i]='w';
				else virtualGameBoard[(cellNo/8)-i][(cellNo%8)-i]='O';
			}
		}
		if(temp)virtualGameBoard[cellNo/8][cellNo%8]=playerTurn;
		
	}
	
	public void setCellsToVirtualGameBoard(JPanel jp,char virtualGameBoard[][],cell[] cell)
	{
		for (int i = 0; i < 64; i++) 
		{
			if(virtualGameBoard[i/8][i%8]=='o')cell[i].setEmptyCell();
			else if(virtualGameBoard[i/8][i%8]=='w')cell[i].setWhiteCell();
			else if(virtualGameBoard[i/8][i%8]=='O')cell[i].setOrangeCell();
			else cell[i].setBlackCell();
			jp.repaint();
		}
	}
	public boolean validUp(char virtualGameBoard[][],int cellNo,char playerTurn)
	{
		if((cellNo/8)<2)return false;
		if(virtualGameBoard[cellNo/8][cellNo%8]!='o')return false;
		if(virtualGameBoard[(cellNo/8)-1][cellNo%8]!=playerTurn && virtualGameBoard[(cellNo/8)-1][cellNo%8]!='o')
			for(int i=2;((cellNo/8)-i)>-1;i++)
			{
				if(virtualGameBoard[(cellNo/8)-i][cellNo%8]=='o')break;
				if(virtualGameBoard[(cellNo/8)-i][cellNo%8]==playerTurn)
					return true;
			}
		return false;
	}
	public boolean validDown(char virtualGameBoard[][],int cellNo,char playerTurn)
	{
		if((cellNo/8)>6)return false;
		if(virtualGameBoard[cellNo/8][cellNo%8]!='o')return false;
		if(virtualGameBoard[(cellNo/8)+1][cellNo%8]!=playerTurn && virtualGameBoard[(cellNo/8)+1][cellNo%8]!='o')
			for(int i=2;((cellNo/8)+i)<8;i++)
			{
				if(virtualGameBoard[(cellNo/8)+i][cellNo%8]=='o')break;
				if(virtualGameBoard[(cellNo/8)+i][cellNo%8]==playerTurn)
					return true;
			}
		return false;
	}
	public boolean validLeft(char virtualGameBoard[][],int cellNo,char playerTurn)
	{
		if((cellNo%8)<2)return false;
		if(virtualGameBoard[cellNo/8][cellNo%8]!='o')return false;
		if(virtualGameBoard[(cellNo/8)][(cellNo%8)-1]!=playerTurn && virtualGameBoard[(cellNo/8)][(cellNo%8)-1]!='o')
			for(int i=2;((cellNo%8)-i)>-1;i++)
			{
				if(virtualGameBoard[cellNo/8][(cellNo%8)-i]=='o')break;
				if(virtualGameBoard[cellNo/8][(cellNo%8)-i]==playerTurn)
					return true;
			}
		return false;
	}
	public boolean validRight(char virtualGameBoard[][],int cellNo,char playerTurn)
	{
		if((cellNo%8)>6)return false;
		if(virtualGameBoard[cellNo/8][cellNo%8]!='o')return false;
		if(virtualGameBoard[(cellNo/8)][(cellNo%8)+1]!=playerTurn && virtualGameBoard[(cellNo/8)][(cellNo%8)+1]!='o')
			for(int i=2;((cellNo%8)+i)<8;i++)
			{
				if(virtualGameBoard[cellNo/8][(cellNo%8)+i]=='o')break;
				if(virtualGameBoard[cellNo/8][(cellNo%8)+i]==playerTurn)
					return true;
			}
		return false;
	}
	public boolean validUpRight(char virtualGameBoard[][],int cellNo,char playerTurn)
	{
		if((cellNo/8)<2 || (cellNo%8)>6)return false;
		if(virtualGameBoard[cellNo/8][cellNo%8]!='o')return false;
		if(virtualGameBoard[(cellNo/8)-1][(cellNo%8)+1]!=playerTurn && virtualGameBoard[(cellNo/8)-1][(cellNo%8)+1]!='o')
			for(int i=2;((cellNo/8)-i)>-1 && ((cellNo%8)+i)<8;i++)
			{
				if(virtualGameBoard[(cellNo/8)-i][(cellNo%8)+i]=='o')break;
				if(virtualGameBoard[(cellNo/8)-i][(cellNo%8)+i]==playerTurn)
					return true;
			}
		return false;
	}
	public boolean validDownRight(char virtualGameBoard[][],int cellNo,char playerTurn)
	{
		if((cellNo/8)>6 || (cellNo%8)>6)return false;
		if(virtualGameBoard[cellNo/8][cellNo%8]!='o')return false;
		if(virtualGameBoard[(cellNo/8)+1][(cellNo%8)+1]!=playerTurn && virtualGameBoard[(cellNo/8)+1][(cellNo%8)+1]!='o')
			for(int i=2;((cellNo/8)+i)<8 && ((cellNo%8)+i)<8;i++)
			{
				if(virtualGameBoard[(cellNo/8)+i][(cellNo%8)+i]=='o')break;
				if(virtualGameBoard[(cellNo/8)+i][(cellNo%8)+i]==playerTurn)
					return true;
			}
		return false;
	}
	public boolean validDownLeft(char virtualGameBoard[][],int cellNo,char playerTurn)
	{
		if((cellNo/8)>6 || (cellNo%8)<2)return false;
		if(virtualGameBoard[cellNo/8][cellNo%8]!='o')return false;
		if(virtualGameBoard[(cellNo/8)+1][(cellNo%8)-1]!=playerTurn && virtualGameBoard[(cellNo/8)+1][(cellNo%8)-1]!='o')
			for(int i=2;((cellNo/8)+i)<8 && ((cellNo%8)-i)>-1;i++)
			{
				if(virtualGameBoard[(cellNo/8)+i][(cellNo%8)-i]=='o')break;
				if(virtualGameBoard[(cellNo/8)+i][(cellNo%8)-i]==playerTurn)
					return true;
			}
		return false;
	}
	public boolean validUpLeft(char virtualGameBoard[][],int cellNo,char playerTurn)
	{
		if((cellNo/8)<2 || (cellNo%8)<2)return false;
		if(virtualGameBoard[cellNo/8][cellNo%8]!='o')return false;
		if(virtualGameBoard[(cellNo/8)-1][(cellNo%8)-1]!=playerTurn && virtualGameBoard[(cellNo/8)-1][(cellNo%8)-1]!='o')
			for(int i=2;((cellNo/8)-i)>-1 && ((cellNo%8)-i)>-1;i++)
			{
				if(virtualGameBoard[(cellNo/8)-i][(cellNo%8)-i]=='o')break;
				if(virtualGameBoard[(cellNo/8)-i][(cellNo%8)-i]==playerTurn)
					return true;
			}
		return false;
	}
}
