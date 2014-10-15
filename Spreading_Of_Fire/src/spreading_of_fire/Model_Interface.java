package spreading_of_fire;

/**
 * The model interface of project from MVC pattern
 * 
 * @author Lets_Fire_Go
 * @version 2014.10.14
 */
public interface Model_Interface {
    /**
     * Set the fire catch rate
     * @param probCatch
     */
    public void setProbCatch(double probCatch);

    /**
     * Get the fire catch rate
     * @return probCatch
     */
    public double getProbCatch();

    /**
     * Set the tree survival rate
     * @param probTree
     */
    public void setProbTree(double probTree);

    /**
     * Set the burning rate
     * @param probBurn
     */
    public void setProbBurn(double probBurn);

    /**
     * Get the tree survival rate
     * @return probTree
     */
    public double getProbTree();

    /**
     * Get the burning rate
     * @return probBurn
     */
    public double getProbBurn();

    /**
     * Get the width
     * @return width
     */
    public int getWidth();

    /**
     * Get the height
     * @return height
     */
    public int getHeight();

    /**
     * Get the delay
     * @return delay
     */
    public int getDelay();

    /**
     * Set the delay
     * @param delay
     */
    public void setDelay(int delay);
    
    /**
     * Set the size
     * @param width
     * @param height
     */
    public void setSize(int width,int height);
    
    /**
     * Reset the field
     */
    public void fieldReset();
    
    /**
     * Start moving
     */
    public void start();
    
    /**
     * Spread fire cells
     * @return true if there is no burning cell
     */
    public boolean move();

    /**
     * Add the observer for this model
     * @param view
     */
    public void addObserver(View view);

    /**
     * Update this field
     */
    public void update();
    
}
