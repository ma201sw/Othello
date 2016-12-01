// cell class -  these are the cells of the game board,
//they are JLabels and are updated with the corresponding .jpg 
//accordingly, i.e. update cell to black chip or to empty cell.
import java.awt.*;
import javax.swing.*;

public class cell extends JLabel
{
	ImageIcon emptyCellIcon = new ImageIcon("empty cell.jpg");
	ImageIcon whiteCellIcon = new ImageIcon("white chip.jpg");
	ImageIcon blackCellIcon = new ImageIcon("black chip.jpg");
	ImageIcon orangeCellIcon = new ImageIcon("orange chip.jpg");
	ImageIcon redEmptyCellIcon = new ImageIcon("red cell.jpg");
	char iconType='e';
	
	ImageIcon emptyIcon=createImageIcon("empty cell.jpg");
	ImageIcon whiteIcon=createImageIcon("white chip.jpg");
	ImageIcon blackIcon=createImageIcon("black chip.jpg");
	ImageIcon orangeIcon=createImageIcon("orange chip.jpg");
	ImageIcon redIcon=createImageIcon("red cell.jpg");
	boolean lastMove=false;
	public cell()
	{
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent (g);
	    if(iconType=='e')g.drawImage (emptyCellIcon.getImage(), 0, 0, getWidth (), getHeight (), null);
	    if(iconType=='w')g.drawImage (whiteCellIcon.getImage(), 0, 0, getWidth (), getHeight (), null);
	    if(iconType=='b')g.drawImage (blackCellIcon.getImage(), 0, 0, getWidth (), getHeight (), null);
	    if(iconType=='O')g.drawImage (orangeCellIcon.getImage(), 0, 0, getWidth (), getHeight (), null);
	    if(iconType=='r')g.drawImage (redEmptyCellIcon.getImage(), 0, 0, getWidth (), getHeight (), null);
	    
	    if(lastMove)
	    {
	    g.setColor(Color.red);
	    g.drawOval(6, 6, getWidth()-14, getHeight()-14);
	    }
	    
	}
	public void setEmptyCell()
	{
		setIcon(emptyIcon);
		iconType='e';
	}
	public void setWhiteCell()
	{
		setIcon(whiteIcon);
		iconType='w';
	}
	public void setBlackCell()
	{
		setIcon(blackIcon);
		iconType='b';
	}
	public void setRedCell()
	{
		setIcon(redIcon);
		iconType='r';
	}
	
	public void setOrangeCell()
	{
		setIcon(orangeIcon);
		iconType='O';
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

}
