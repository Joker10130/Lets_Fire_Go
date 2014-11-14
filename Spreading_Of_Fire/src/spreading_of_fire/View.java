package spreading_of_fire;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;

/**
 * The view class of project from MVC pattern
 * 
 * @author mewmeww
 * @version 2014.04.19
 */

public class View extends JPanel{
    private Cell cell[][];
    private int blockWidth,blockHeight,step;
    private boolean showValue;
    private boolean showLighting;
    private double percentRemainingTree;
    
     /**
     * Paint the cell
     */
    @Override
    public void paintComponent(Graphics g){
        //Fill the back ground with Yellow
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 500, 600);
        
        //Calculate the border width
        int border=(500-(blockWidth*cell.length))/2;
        
        
        //Don't paint if there is no cell
        if(cell==null)return;
        for(int i=0;i<cell.length;i++){
            for(int j=0;j<cell[0].length;j++){
                //Paint the cell
                g.setColor(cell[i][j].getColor());
                g.fillOval(border+j*blockWidth,border+i*blockHeight,blockWidth,blockHeight);
                
                //Check the show value or not
                if(showValue){
                    //Show the value of each cell
                    g.setColor(Color.BLACK);
                    g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, blockHeight));
                    g.drawString(""+cell[i][j].get(), border+j*blockWidth+blockWidth/4,border+i*blockHeight+blockHeight*7/8);
                }
            }
        }
        
        //Paint the "Step" label
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        g.drawString("Step: "+step, 10, 520);
        
        //Show the showValue toggle status
        g.drawString("Show Value: "+showValue, 100, 520);
       
        //show step lighting status
        g.drawString("Lighting Status: "+showLighting, 255, 520);
        
        //Show remainingtree
        g.drawString("Remaining Tree: "+percentRemainingTree, 10, 550);
    }
    
    /**
     * Constructor - create the view panel
     * @param blockWidth
     * @param blockHeight
     */
    public View(int blockWidth,int blockHeight) {
        this.blockWidth=blockWidth;
        this.blockHeight=blockHeight;
        showValue=false;
        cell=null;
        showLighting=false;
        percentRemainingTree = 100.00;
    }
        
        
    /**
     * Toggle the showValue
     */
    public void toggleValue(){
        if(showValue)showValue=false;
        else showValue=true;
        repaint();
    }
    
    /**
     * Set the block size
     * @param blockWidth
     * @param blockHeight
     */
    public void setBlockSize(int blockWidth,int blockHeight) {
        this.blockWidth=blockWidth;
        this.blockHeight=blockHeight;
    }
    
    /**
     * Update the cell
     * @param cell
     */
    public void update(Cell cell[][],int step,double percentRemainingTree, boolean stepLightning){
        this.cell=cell;
        this.step=step;
        this.showLighting=stepLightning;
        this.percentRemainingTree=percentRemainingTree;
        repaint();
    }
}

