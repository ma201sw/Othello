import javax.swing.JTextArea;


class turnTimer extends java.util.TimerTask
{
	long targ;
	long countDownTime;
	JTextArea textArea= new JTextArea();
	onePlayerVsTwoPlayer onePlayerVsTwoPlayer;
	othelloGameBoardPanel othelloGameBoardPanel;
	String ruleType="";
	private int n=Integer.MAX_VALUE;
	public turnTimer(long seconds, JTextArea TA,othelloGameBoardPanel othelloGameBoardPanel)
	{
		 this.textArea=TA;
		 this.targ=System.currentTimeMillis()+seconds*1000;
		 countDownTime=seconds;
		 
		 this.othelloGameBoardPanel=othelloGameBoardPanel;
    }
	
	
	public void set1P2P(onePlayerVsTwoPlayer onePlayerVsTwoPlayer)
	{
		this.onePlayerVsTwoPlayer=onePlayerVsTwoPlayer;
	}
	public void setGameBoard(othelloGameBoardPanel othelloGameBoardPanel)
	{
		this.othelloGameBoardPanel=othelloGameBoardPanel;
	}
	public void resetTime()
	{
		this.targ=System.currentTimeMillis()+countDownTime*1000;
		
	}
	public void setTime(long seconds)
	{
		this.targ=System.currentTimeMillis()+seconds*1000;
		 countDownTime=seconds;
	}
 

public void run()
{    
	 n = (int)((this.targ - System.currentTimeMillis())/(1000L));
     textArea.setText("");
     
     if(n<=0 && ruleType.equalsIgnoreCase("randomUndo"))
    	 textArea.setText(" out of time,undo/random opponents move"); 
     else if(ruleType.equalsIgnoreCase("randomUndo")) textArea.setText(" Remaining turn time(seconds) : "+String.valueOf(n));
     
     if(n>=0 && ruleType.equalsIgnoreCase("random"))
    	 textArea.setText(" Remaining turn time(seconds) : "+String.valueOf(n));
     if(n==-1)
     {
    	 try
    	 {
    		 rightPanel.undo.setEnabled(true);
    		 rightPanel.random.setEnabled(true);
    		 
    		 if(ruleType.equalsIgnoreCase("random"))
    			 {
    			 	onePlayerVsTwoPlayer.randomMove();//oneplayervstwoplayer not instantiated?
    			 	resetTime();
    			 	if(onePlayerVsTwoPlayer.playerTurn=='b')onePlayerVsTwoPlayer.rightPanel.setTurnText(" Blacks turn");
    			 	else onePlayerVsTwoPlayer.rightPanel.setTurnText(" Whites turn");
    			 }
    		 if(ruleType.equalsIgnoreCase("randomUndo"))
    		 {
    			 if(onePlayerVsTwoPlayer.playerTurn=='b')onePlayerVsTwoPlayer.rightPanel.setTurnText(" Whites turn");
    			 else onePlayerVsTwoPlayer.rightPanel.setTurnText(" Blacks turn");

    		 }

    	 }
     catch(java.lang.RuntimeException ex){throw ex;}   
     }
}
} 
