package spreading_of_fire;

import javax.swing.*;

/**
 * The view class of project from MVC pattern
 * 
 * @author mewmeww
 * @version 2014.04.19
 */

public class View  {
    private Cell cell[][];
    private int blockWidth,blockHeight,step;
    private boolean showValue;
    
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

        
        
    /**
     * Toggle the showValue
     */
    public void toggleValue(){
        if(showValue)showValue=false;
        else showValue=true;
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
     * Set the step
     * @param step
     */
    public void setStep(int step) {
        this.step=step;
    }
    
    /**
     * Update the cell
     * @param cell
     */
    public void update(Cell cell[][]){
        this.cell=cell;
    }
}

