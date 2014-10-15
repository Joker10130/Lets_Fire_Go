package spreading_of_fire;

/**
 * The view interface of project from MVC pattern
 * 
 * @author Lets_Fire_Go
 * @version 2014.10.14
 */
public interface View_Interface {
    
    /**
     * Paint the cell
     * @param g
     */
    public void paintComponent(java.awt.Graphics g);
    
    /**
     * Toggle the showValue
     */
    public void toggleValue();
    
    /**
     * Set the block size
     * @param blockWidth
     * @param blockHeight
     */
    public void setBlockSize(int blockWidth,int blockHeight);
    
    /**
     * Set the step
     * @param step
     */
    public void setStep(int step);
    
    /**
     * Update the cell
     * @param cell
     */
    public void update(Cell cell[][]);
}
