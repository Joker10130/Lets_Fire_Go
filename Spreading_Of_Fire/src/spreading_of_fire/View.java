package spreading_of_fire;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;

/**
 * The view class of project from MVC pattern
 *
 * @version 2014.10.19
 */
public class View extends JPanel  {
    private final Cell cell[][];
    private final int blockWidth,blockHeight,step;
    private final boolean showValue;
    
    /**
     * Paint the cell
     * @param g
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
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("Step: "+step, 10, 550);
        
        //Show the showValue toggle status
        g.drawString("Show Value: "+showValue, 200, 550);
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
    }
    
    public void toggleValue() {
        
    }

    public void setBlockSize(int blockWidth, int blockHeight) {
        
    }

    
    public void setStep(int step) {
        
    }

    public void update(Cell[][] cell) {
        
    }
    
}
