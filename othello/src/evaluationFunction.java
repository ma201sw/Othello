//evaluationFunction class – this class is used to 
//return the goodness score of a game board. It uses 
//different strategies to return the goodness score – 
//weighted matrix, weighted matrix adaptable weights, 
//greedy, mobility, frontier, weight matrix with greedy, 
//weight matrix with mobility, weight matrix with frontier, 
//and customisable which all these strategies incorporated with 
//customisable weights to be set for the strategies use.
import java.util.LinkedList;

public class evaluationFunction 
{
	int staticEvaluationMatrix[][];
	handleMove hd=new handleMove();
	static char humanPlayer;
	miniMax miniMax;
	int greedyWeight=1;
	int mobilityWeight=34;
	int frontierWeight=34;
	int evaluationMatrixWeight=1;
	public evaluationFunction(int staticEvaluationMatrix[][])
	{
		this.staticEvaluationMatrix=staticEvaluationMatrix;
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
	
	public void adaptCornerWeights(char VGB[][])
	{
		setDefaultEvaluationMatrix();
		if(VGB[0][0]=='b' || VGB[0][0]=='w')
		{
			staticEvaluationMatrix[0][1]=0;
			staticEvaluationMatrix[1][0]=0;
		}
		if(VGB[0][7]=='b' || VGB[0][7]=='w')
		{
			staticEvaluationMatrix[0][6]=0;
			staticEvaluationMatrix[1][7]=0;
		}
		if(VGB[7][0]=='b' || VGB[7][0]=='w')
		{
			staticEvaluationMatrix[6][0]=0;
			staticEvaluationMatrix[7][1]=0;
		}
		if(VGB[7][7]=='b' || VGB[7][7]=='w')
		{
			staticEvaluationMatrix[6][7]=0;
			staticEvaluationMatrix[7][6]=0;
		}
	}
	public int evaluationWeightedMatrix(char VGB[][])
	{
		setDefaultEvaluationMatrix();
		if(VGB==null)return -1;
		char[][] copy=VGB.clone();
		for(int j=0;j<copy.length;j++)copy[j]=copy[j].clone();
		int score=0;
		
		
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
			{
				if(copy[i][j]=='b')
					if(humanPlayer=='b')
					score=score-(staticEvaluationMatrix[i][j]);
					else score=score+staticEvaluationMatrix[i][j];
		
				if(copy[i][j]=='w')
					if(humanPlayer=='b')
					score=score+staticEvaluationMatrix[i][j];
					else score=score-staticEvaluationMatrix[i][j];	
			}
			
		return score;
	}

	public int customisable(char VGB[][])
	{
		adaptCornerWeights(VGB);
		if(VGB==null)return -1;
		char[][] copy=VGB.clone();
		for(int j=0;j<copy.length;j++)copy[j]=copy[j].clone();
		int score=0;
				
		for (int i=0;i<8;i++)    //weighted matrix
			for (int j=0;j<8;j++)
			{
				if(copy[i][j]=='b')
					if(humanPlayer=='b')
					score=score-staticEvaluationMatrix[i][j]*evaluationMatrixWeight;
					else score=score+staticEvaluationMatrix[i][j]*evaluationMatrixWeight;
		
				if(copy[i][j]=='w')
					if(humanPlayer=='b')
					score=score+staticEvaluationMatrix[i][j]*evaluationMatrixWeight;
					else score=score-staticEvaluationMatrix[i][j]*evaluationMatrixWeight;	
			}
		
		for (int i=0;i<8;i++)   //greedy
			for (int j=0;j<8;j++)
			{
				if(copy[i][j]=='b')
					if(humanPlayer=='b')
					score=score-1*greedyWeight;
					else score=score+1*greedyWeight;
				
				 if(copy[i][j]=='w')
					 if(humanPlayer=='b')
					score=score+1*greedyWeight;
					 else score=score-1*greedyWeight;
			}
		
		for (int i=0;i<64;i++)  //mobility
		{
			if(hd.validMove(VGB, i, 'b'))
				if(humanPlayer=='b')
					score=score-1*mobilityWeight;
				else score=score+1*mobilityWeight;
			
				if(hd.validMove(VGB, i, 'w'))
					if(humanPlayer=='b')
						score=score+1*mobilityWeight;
					else score=score-1*mobilityWeight;
		}
		
		for (int i=0;i<8;i++)   //frontier
			for (int j=0;j<8;j++)
			{
				if(copy[i][j]=='o')
				{
					
						if(i!=0 && copy[i-1][j]=='w')
							if(humanPlayer=='b')
								score=score-1*frontierWeight;
							else score=score+1*frontierWeight;
					
						if(i!=7 && copy[i+1][j]=='w')
							if(humanPlayer=='b')
								score=score-1*frontierWeight;
							else score=score+1*frontierWeight;
					
						if(j!=0 && copy[i][j-1]=='w')
							if(humanPlayer=='b')
								score=score-1*frontierWeight;
							else score=score+1*frontierWeight;
					
						if(j!=7 && copy[i][j+1]=='w')
							if(humanPlayer=='b')
								score=score-1*frontierWeight;
							else score=score+1*frontierWeight;
						
						if(i!=0 && j!=0 && copy[i-1][j-1]=='w')
							if(humanPlayer=='b')
								score=score-1*frontierWeight;
							else score=score+1*frontierWeight;
						
						if(i!=0 && j!=7 && copy[i-1][j+1]=='w')
							if(humanPlayer=='b')
								score=score-1*frontierWeight;
							else score=score+1*frontierWeight;
						
						if(i!=7 && j!=0 && copy[i+1][j-1]=='w')
							if(humanPlayer=='b')
								score=score-1*frontierWeight;
							else score=score+1*frontierWeight;
						
						if(i!=7 && j!=7 && copy[i+1][j+1]=='w')
							if(humanPlayer=='b')
								score=score-1*frontierWeight;
							else score=score+1*frontierWeight;
				}
				if(copy[i][j]=='o')
				{
					
						if(i!=0 && copy[i-1][j]=='b')
							if(humanPlayer=='b')
								score=score+1*frontierWeight;
							else score=score-1*frontierWeight;
					
						if(i!=7 && copy[i+1][j]=='b')
							if(humanPlayer=='b')
								score=score+1*frontierWeight;
							else score=score-1*frontierWeight;
					
						if(j!=0 && copy[i][j-1]=='b')
							if(humanPlayer=='b')
								score=score+1*frontierWeight;
							else score=score-1*frontierWeight;
					
						if(j!=7 && copy[i][j+1]=='b')
							if(humanPlayer=='b')
								score=score+1*frontierWeight;
							else score=score-1*frontierWeight;
						
						if(i!=0 && j!=0 && copy[i-1][j-1]=='b')
							if(humanPlayer=='b')
								score=score+1*frontierWeight;
							else score=score-1*frontierWeight;
						
						if(i!=0 && j!=7 && copy[i-1][j+1]=='b')
							if(humanPlayer=='b')
								score=score+1*frontierWeight;
							else score=score-1*frontierWeight;
						
						if(i!=7 && j!=0 && copy[i+1][j-1]=='b')
							if(humanPlayer=='b')
								score=score+1*frontierWeight;
							else score=score-1*frontierWeight;
						
						if(i!=7 && j!=7 && copy[i+1][j+1]=='b')
							if(humanPlayer=='b')
								score=score+1*frontierWeight;
							else score=score-1*frontierWeight;
						
						
							
				}
				}

		
	
		return score;
	}
	public int evaluationAdapt(char VGB[][])
	{
		adaptCornerWeights(VGB);
		if(VGB==null)return -1;
		char[][] copy=VGB.clone();
		for(int j=0;j<copy.length;j++)copy[j]=copy[j].clone();
		int score=0;
		
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
			{
				if(copy[i][j]=='b')
					if(humanPlayer=='b')
					score=score-staticEvaluationMatrix[i][j];
					else score=score+staticEvaluationMatrix[i][j];
		
				if(copy[i][j]=='w')
					if(humanPlayer=='b')
					score=score+staticEvaluationMatrix[i][j];
					else score=score-staticEvaluationMatrix[i][j];	
			}
			
		return score;
	}
	
	public int evaluationGreedy(char VGB[][],boolean useMatrix)
	{
		adaptCornerWeights(VGB);
		if(VGB==null)return -1;
		char[][] copy=VGB.clone();
		for(int j=0;j<copy.length;j++)copy[j]=copy[j].clone();
		int score=0;

		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
			{
				if(copy[i][j]=='b')
					if(humanPlayer=='b')
					score=score-1;
					else score=score+1;
				
				 if(copy[i][j]=='w')
					 if(humanPlayer=='b')
					score=score+1;
					 else score=score-1;
			}

		if(useMatrix)
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
				{
					if(copy[i][j]=='b')
						if(humanPlayer=='b')
						score=score-staticEvaluationMatrix[i][j];
						else score=score+staticEvaluationMatrix[i][j];
			
					if(copy[i][j]=='w')
						if(humanPlayer=='b')
						score=score+staticEvaluationMatrix[i][j];
						else score=score-staticEvaluationMatrix[i][j];	
				}
		
		return score;
	}
	
	public int evaluationMobility(char VGB[][],boolean useMatrix)
	{
		adaptCornerWeights(VGB);
		if(VGB==null)return -1;
		char[][] copy=VGB.clone();
		for(int j=0;j<copy.length;j++)copy[j]=copy[j].clone();
		int score=0;

		for (int i=0;i<64;i++)
		{
			if(hd.validMove(VGB, i, 'b'))
				if(humanPlayer=='b')
					score=score-1;
				else score=score+1;
			
				if(hd.validMove(VGB, i, 'w'))
					if(humanPlayer=='b')
						score=score+1;
					else score=score-1;
		}
		
		if(useMatrix)
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
				{
					if(copy[i][j]=='b')
						if(humanPlayer=='b')
						score=score-staticEvaluationMatrix[i][j];
						else score=score+staticEvaluationMatrix[i][j];
			
					if(copy[i][j]=='w')
						if(humanPlayer=='b')
						score=score+staticEvaluationMatrix[i][j];
						else score=score-staticEvaluationMatrix[i][j];	
				}
		
		return score;
	}
	
	public int evaluationFrontier(char VGB[][],boolean useMatrix)
	{
		adaptCornerWeights(VGB);
		if(VGB==null)return -1;
		char[][] copy=VGB.clone();
		for(int j=0;j<copy.length;j++)copy[j]=copy[j].clone();
		int score=0;
		
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
			{
				if(copy[i][j]=='o')
				{
					
						if(i!=0 && copy[i-1][j]=='w')
							if(humanPlayer=='b')
								score=score-1;
							else score=score+1;
					
						if(i!=7 && copy[i+1][j]=='w')
							if(humanPlayer=='b')
								score=score-1;
							else score=score+1;
					
						if(j!=0 && copy[i][j-1]=='w')
							if(humanPlayer=='b')
								score=score-1;
							else score=score+1;
					
						if(j!=7 && copy[i][j+1]=='w')
							if(humanPlayer=='b')
								score=score-1;
							else score=score+1;
						
						if(i!=0 && j!=0 && copy[i-1][j-1]=='w')
							if(humanPlayer=='b')
								score=score-1;
							else score=score+1;
						
						if(i!=0 && j!=7 && copy[i-1][j+1]=='w')
							if(humanPlayer=='b')
								score=score-1;
							else score=score+1;
						
						if(i!=7 && j!=0 && copy[i+1][j-1]=='w')
							if(humanPlayer=='b')
								score=score-1;
							else score=score+1;
						
						if(i!=7 && j!=7 && copy[i+1][j+1]=='w')
							if(humanPlayer=='b')
								score=score-1;
							else score=score+1;
				}
				if(copy[i][j]=='o')
				{
					
						if(i!=0 && copy[i-1][j]=='b')
							if(humanPlayer=='b')
								score=score+1;
							else score=score-1;
					
						if(i!=7 && copy[i+1][j]=='b')
							if(humanPlayer=='b')
								score=score+1;
							else score=score-1;
					
						if(j!=0 && copy[i][j-1]=='b')
							if(humanPlayer=='b')
								score=score+1;
							else score=score-1;
					
						if(j!=7 && copy[i][j+1]=='b')
							if(humanPlayer=='b')
								score=score+1;
							else score=score-1;
						
						if(i!=0 && j!=0 && copy[i-1][j-1]=='b')
							if(humanPlayer=='b')
								score=score+1;
							else score=score-1;
						
						if(i!=0 && j!=7 && copy[i-1][j+1]=='b')
							if(humanPlayer=='b')
								score=score+1;
							else score=score-1;
						
						if(i!=7 && j!=0 && copy[i+1][j-1]=='b')
							if(humanPlayer=='b')
								score=score+1;
							else score=score-1;
						
						if(i!=7 && j!=7 && copy[i+1][j+1]=='b')
							if(humanPlayer=='b')
								score=score+1;
							else score=score-1;
						
						
							
				}
				}
		
		if(useMatrix)
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
				{
					if(copy[i][j]=='b')
						if(humanPlayer=='b')
						score=score-staticEvaluationMatrix[i][j];
						else score=score+staticEvaluationMatrix[i][j];
			
					if(copy[i][j]=='w')
						if(humanPlayer=='b') 
						score=score+staticEvaluationMatrix[i][j];
						else score=score-staticEvaluationMatrix[i][j];	
				}
					
		return score;
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
}
