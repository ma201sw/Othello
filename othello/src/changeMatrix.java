//changeMatrix class – this class is used by the miniMax AI if it 
//uses the customisable evaluation function. This class displays the 
//current weighted matrix and allows modifications to the weighted matrix.

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class changeMatrix extends JFrame implements ActionListener
{
	JPanel pane= new JPanel(new GridLayout(8,8));
	JPanel bottom=new JPanel(new GridLayout(0,2));
	JTextField theCells[][]=new JTextField[8][8];
	JButton save=new JButton("Save");
	JButton cancel=new JButton("Cancel");
	int [][]staticEvaluationMatrix;
	public changeMatrix(int [][]staticEvaluationMatrix)
	{
		this.staticEvaluationMatrix=staticEvaluationMatrix;
		save.addActionListener(this);
		cancel.addActionListener(this);
			for(int i=0;i<8;i++)
				for(int j=0;j<8;j++)
			{
				theCells[i][j]=new JTextField("");
				theCells[i][j].setHorizontalAlignment(JTextField.CENTER);
				pane.add(theCells[i][j]);
				
			}
	}
	public void init()
	{
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			theCells[i][j].setText(Integer.toString(staticEvaluationMatrix[i][j]));
			
		setSize(345,350);
		getContentPane().add(pane,BorderLayout.CENTER);
		bottom.add(save);
		bottom.add(cancel);
		getContentPane().add(bottom,BorderLayout.SOUTH);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==save)
		{
			setVisible(false);
			for(int i=0;i<8;i++)
				for(int j=0;j<8;j++)
				staticEvaluationMatrix[i][j]=Integer.parseInt(theCells[i][j].getText());
		}
		if(e.getSource()==cancel)setVisible(false);
		
	}
	

}
