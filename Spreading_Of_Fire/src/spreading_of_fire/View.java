package spreading_of_fire;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The view class of project from MVC pattern
 * 
 * @author Mewmeww
 * @version 2014.10.19
 */
public class View extends JPanel {

    public void paintComponent(Graphics g){
        private Cell cell[][];
    private int blockWidth,blockHeight,step;
    private boolean showValue;
    
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
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("Step: "+step, 10, 550);
        
        //Show the showValue toggle status
        g.drawString("Show Value: "+showValue, 200, 550);
    }
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
