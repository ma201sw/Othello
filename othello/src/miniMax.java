//miniMax class – this class handles the human
//man move and miniMax AI move. Mouse events and 
//action events are passed to this class from the
//othelloGameBoardPanel class which communicates with 
//right panel class, for this class to handle and process 
//it will use handleMove to perform the move if the move 
//is valid from the player side, and it will generate the 
//miniMax AI move in response and perform that move using
//handleMove also. It uses evaluationFunction class in generating 
//its AI miniMax move.

import java.awt.event.MouseEvent;
import java.util.*;

public class miniMax 
{
	char humanPlayer;
	handleMove hd;
	cell cell[];
	char virtualGameBoard[][];
	char playerTurn='b';
	int staticEvaluationMatrix[][]=new int [][]{
			{ 90000,-4000,0,0,0,0,-4000, 90000},
			{-4000,-8000,0,0,0,0,-8000,-4000},
			{ 0,  0, 0,0,0,0, 0,  0},
			{ 0,  0, 0,0,0,0, 0,  0},
			{ 0,  0, 0,0,0,0, 0,  0},
			{ 0,  0, 0,0,0,0, 0,  0},
			{-4000,-8000,0,0,0,0,-8000,-4000},
			{ 90000,-4000,0,0,0,0,-4000, 90000}
	};
	int chosenScore;
	int bestScore;
	int depthLimit=3;
	char[][] tempBoard;
    int chosenMove;
    int readAhead=1;
    int depthCopy=readAhead;
    char[][] bestBoardMax;
    char[][] bestBoardMin;
    String selectedEvaluationMethod="Weighted Matrix";
    evaluationFunction evaluationFuntion=new evaluationFunction(staticEvaluationMatrix);
	public miniMax(cell cell[],char virtualGameBoard[][])
	{
		this.cell=cell;
		this.virtualGameBoard=virtualGameBoard;
		hd=new handleMove();
		
	}
	public void setDefaultEvaluationMatrix()
	{
		staticEvaluationMatrix=new int [][]{
				{ 90000,-4000,0,0,0,0,-4000, 90000},
				{-4000,-8000,0,0,0,0,-8000,-4000},
				{ 0,  0, 0,0,0,0, 0,  0},
				{ 0,  0, 0,0,0,0, 0,  0},
				{ 0,  0, 0,0,0,0, 0,  0},
				{ 0,  0, 0,0,0,0, 0,  0},
				{-4000,-8000,0,0,0,0,-8000,-4000},
				{ 90000,-4000,0,0,0,0,-4000, 90000}
		};
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
	
	public int evaluation(char VGB[][])
	{
		if(selectedEvaluationMethod.equalsIgnoreCase("Weighted Matrix"))
			return evaluationFuntion.evaluationWeightedMatrix(VGB);

		
		else if(selectedEvaluationMethod.equalsIgnoreCase("WM+Adaptable Weights"))
	    return evaluationFuntion.evaluationAdapt(VGB);
		
		else if(selectedEvaluationMethod.equalsIgnoreCase("Greedy"))
		    return evaluationFuntion.evaluationGreedy(VGB,false);
		
		else if(selectedEvaluationMethod.equalsIgnoreCase("WMA+Greedy"))
		    return evaluationFuntion.evaluationGreedy(VGB,true);
		
		else if(selectedEvaluationMethod.equalsIgnoreCase("Mobility"))
		    return evaluationFuntion.evaluationMobility(VGB,false);
		
		else if(selectedEvaluationMethod.equalsIgnoreCase("WMA+Mobility"))
		    return evaluationFuntion.evaluationMobility(VGB,true);
		
		else if(selectedEvaluationMethod.equalsIgnoreCase("Frontier"))
		    return evaluationFuntion.evaluationFrontier(VGB,false);
		
		else if(selectedEvaluationMethod.equalsIgnoreCase("WMA+Frontier"))
		    return evaluationFuntion.evaluationFrontier(VGB,true);
		
		else if(selectedEvaluationMethod.equalsIgnoreCase("Customisable"))
		return evaluationFuntion.customisable(VGB);
		
		System.out.println("something wrong");
		return 0;
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
	
	public void doAIMove(char c)
	{
		int move=minMax(virtualGameBoard);
		hd.performMove(virtualGameBoard,move, c);

		setCellsToVirtualGameBoard();
		System.out.println("AI");
	}
	
	public int []maxMove(char VGB[][],int depth)
	{
		char playerTurnTemp='w';
		if(humanPlayer=='b')playerTurnTemp='w';else playerTurnTemp='b';
		handleMove hd=new handleMove();
		int bestScore=-999999;
		int score=0;
		int []result=new int[2];
		char[][] tempBoard=VGB.clone();
		for(int j=0;j<tempBoard.length;j++)tempBoard[j]=tempBoard[j].clone();
		LinkedList <Integer>moves= listOfMoves(tempBoard,playerTurnTemp);
		
		
		if(depth>0)
		{
			depth=depth-1;
			for(int i=0;i<moves.size();i++)
			{
				hd.performMove(tempBoard, moves.get(i).intValue(), playerTurnTemp);
				score=minMove(tempBoard,depth)[1];
				
				if(score>=bestScore)
				{
					bestScore=score;
					result[0]=moves.get(i).intValue();	
				}
				tempBoard=VGB.clone();
				for(int j=0;j<tempBoard.length;j++)tempBoard[j]=tempBoard[j].clone();
			}
			
		}
		if(moves==null || depth==0)bestScore=evaluation(tempBoard);
		result[1]=bestScore;
		return result;
	}
	
	public int []minMove(char VGB[][],int depth)
	{
		char playerTurnTemp='b';
		if(humanPlayer=='b')playerTurnTemp='b';else playerTurnTemp='w';
		handleMove hd=new handleMove();
		int bestScore=999999;
		int score=0;
		int result[]=new int[2];
		char[][] tempBoard=VGB.clone();
		for(int j=0;j<tempBoard.length;j++)tempBoard[j]=tempBoard[j].clone();	
		LinkedList <Integer>moves= listOfMoves(tempBoard,playerTurnTemp);
		
		
		if(depth>0)
		{
			depth=depth-1;
			for(int i=0;i<moves.size();i++)
			{
				hd.performMove(tempBoard, moves.get(i).intValue(), playerTurnTemp);
				score=maxMove(tempBoard,depth)[1];
				
				if(score<=bestScore)
				{
					bestScore=score;
					result[0]=moves.get(i).intValue();
				}
				tempBoard=VGB.clone();
				for(int j=0;j<tempBoard.length;j++)tempBoard[j]=tempBoard[j].clone();
			}
		}
		if(moves==null || depth==0)bestScore=evaluation(tempBoard);
		result[1]=bestScore;
		return result;
	}
	public int minMax(char[][]VGB)
	{
		int temp=readAhead;
		int emptyCells=0;
		for(int i=0;i<64;i++)if(VGB[i/8][i%8]=='o')emptyCells++;
		if(readAhead>=emptyCells)readAhead=emptyCells-1;
		if(readAhead<=0)readAhead=1;
		System.out.print(readAhead);
		int move=maxMove(VGB,readAhead)[0];
		highlightLastMove(move);
		readAhead=temp;
		return move;
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
	public void	clickRunIt(MouseEvent e)
	{
		for (int i = 0; i < 64; i++) 
		if (e.getSource() == cell[i])
		{	
			if(hd.validMove(virtualGameBoard,i,playerTurn) && humanPlayer=='b')
			{	
				evaluationFunction.humanPlayer='b';
				if(playerTurn=='b')
				{
					hd.performMove(virtualGameBoard,i,playerTurn);
					setCellsToVirtualGameBoard();
					playerTurn='w';
					
				}
				if(playerTurn=='w')
				{
					
					
					hd.performMove(virtualGameBoard,minMax(virtualGameBoard), playerTurn);									
					setCellsToVirtualGameBoard();
					playerTurn='b';
	
				}
				
				
			}
			else if(hd.validMove(virtualGameBoard,i,playerTurn) && humanPlayer=='w')
			{
				evaluationFunction.humanPlayer='w';
				if(playerTurn=='w')
				{
					hd.performMove(virtualGameBoard,i,playerTurn);
					setCellsToVirtualGameBoard();
					playerTurn='b';
					
				}
				if(playerTurn=='b')
				{
					
					hd.performMove(virtualGameBoard,minMax(virtualGameBoard), playerTurn);
					setCellsToVirtualGameBoard();
					playerTurn='w';
				
				}
			}
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
}
