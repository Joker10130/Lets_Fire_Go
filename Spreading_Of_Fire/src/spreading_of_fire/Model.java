package spreading_of_fire;

import java.util.ArrayList;

/**
 * The view class of project from MVC pattern
 *
 * @author Lets_Fire_Go
 * @version 2014.10.18
 */
public final class Model {

    private int width, height, delay;
    private double probCatch, probTree, probBurn, probLightning;
    private int step;
    private Cell cell[][];
    private View observer;
    private boolean stepLightning;
    private int firstNumTree,numTree;
    private int firstFireCellX,firstFireCellY;
    private String windDirection;
    private int windLevel;

    /**
     * Constructor, create the field
     */
    public Model() {
        this(31, 31, 0.5, 1.0, 0);
    }

    /**
     * Constructor, create the field
     *
     * @param width
     * @param height
     */
    public Model(int width, int height) {
        this(width, height, 0.5, 1.0, 0);
    }

    /**
     * Constructor, create the field
     *
     * @param width
     * @param height
     * @param probCatch
     */
    public Model(int width, int height, double probCatch, double probTree, double probBurn) {
        //Set the properties of field
        this.width = width;
        this.height = height;
        this.probCatch = probCatch;
        this.probTree = probTree;
        this.probBurn = probBurn;
        this.probLightning = 0.00;
        this.stepLightning = false;
        this.delay = 100;
        this.firstFireCellX=width/2;
        this.firstFireCellY=height/2;
        this.windDirection="N";
        this.windLevel=0;
        observer = null;
        //Reset the field
        fieldReset();
    }

    /**
     * Set the fire catch rate
     *
     * @param probCatch
     */
    public void setProbCatch(double probCatch) {
        this.probCatch = probCatch;
    }

    /**
     * Get the fire catch rate
     *
     * @return probCatch
     */
    public double getProbCatch() {
        return probCatch;
    }

    /**
     * Set the tree survival rate
     *
     * @param probTree
     */
    public void setProbTree(double probTree) {
        this.probTree = probTree;
    }

    /**
     * Set the burning rate
     *
     * @param probBurn
     */
    public void setProbBurn(double probBurn) {
        this.probBurn = probBurn;
    }

    /**
     * Get the tree survival rate
     *
     * @return probTree
     */
    public double getProbTree() {
        return probTree;
    }

    /**
     * Get the burning rate
     *
     * @return probBurn
     */
    public double getProbBurn() {
        return probBurn;
    }

    /**
     * Get the lightning rate
     *
     * @return probLightning
     */
    public double getProbLightning() {
        return probLightning;
    }

    /**
     * Set the lightning rate
     *
     * @param probLightning
     */
    public void setProbLightning(double probLightning) {
        this.probLightning = probLightning;
    }

    public boolean getStepLightning() {
        return stepLightning;
    }

    public void setStepLightning(boolean stepLightning) {
        this.stepLightning = stepLightning;
        observer.setStepLightning(this.stepLightning);
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public void setWindLevel(int windLevel) {
        this.windLevel = windLevel;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public int getWindLevel() {
        return windLevel;
    }
    
    /**
     * Get the width
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the delay
     *
     * @return delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Set the delay
     *
     * @param delay
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * Set the size
     *
     * @param width
     * @param height
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.firstFireCellX=width/2;
        this.firstFireCellY=height/2;
        //Reset the field after set
        fieldReset();
    }

    public int getFirstFireCellX() {
        return firstFireCellX;
    }

    public int getFirstFireCellY() {
        return firstFireCellY;
    }

    public void setFirstFireCell(int firstFireCellX,int firstFireCellY) {
        this.firstFireCellX = Math.min(width,Math.max(0, firstFireCellX));
        this.firstFireCellY = Math.min(width,Math.max(0, firstFireCellY));
    }
    
    /**
     * Reset the field
     */
    public void fieldReset() {
        //Create new field with current size
        cell = new Cell[height][width];

        //Place the tree cell
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cell[i][j] = new Cell(i,j);
                //Tree density
                if (Math.random() < probTree) {
                    //Tree is burn or not
                    if (Math.random() < probBurn) //Tree is burning
                    {
                        cell[i][j].set(Cell.FIRE);
                    } else //Tree is not burning
                    {
                        cell[i][j].set(Cell.TREE);
                    }
                } else {
                    //Cell is empty
                    cell[i][j].set(Cell.EMPTY);
                }
            }
        }

        //Place the burning cell at the center if probBurn = 0
        if (probBurn == 0) {
            cell[firstFireCellY][firstFireCellX].set(Cell.FIRE);
        }

        firstNumTree=countTree();
        
        //Reset the step count
        step = 0;

        //Update the field
        update();
    }

    /**
     * Start moving
     */
    public void start() {
        //Move until it can't move
        while (!move());
    }

    /**
     * Get the state of cell at x,y
     *
     * @param x
     * @param y
     * @return Cell state
     */
    private int get(int x, int y) {
        try {
            return cell[x][y].get();
        } catch (Exception e) {
            //If x,y out of bound, treat as empty
            return Cell.EMPTY;
        }
    }

    private int countTree(){
        int countTree=0;
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if(cell[i][j].get()==Cell.TREE)countTree++;
            }
        }
        return countTree;
    }
    
    public double getPercentRemainingTree(){
        return ((double)numTree/(double)firstNumTree)*100;
    }
    
    private void windEffect(ArrayList<Cell> cellList, int i, int j){
        if("S".equals(windDirection)){
            if(get(i+1,j)==Cell.TREE)cellList.add(cell[i+1][j]);
        }
        if("W".equals(windDirection)){
            if(get(i,j-1)==Cell.TREE)cellList.add(cell[i][j-1]);
        }
        if("N".equals(windDirection)){
            if(get(i-1,j)==Cell.TREE)cellList.add(cell[i-1][j]);
        }
        if("E".equals(windDirection)){
            if(get(i,j+1)==Cell.TREE)cellList.add(cell[i][j+1]);
        }
    }
    
    /**
     * Spread fire cells
     *
     * @return true if there is no burning cell
     */
    public boolean move() {

        boolean burnCellLeft = false,wouldLightningLeft=false;
        ArrayList<Cell> wouldBurnCell = new ArrayList();
        ArrayList<Cell> wouldLightningCell = new ArrayList();
        ArrayList<Cell> wouldSpreadByWindLevel1 = new ArrayList();
        ArrayList<Cell> wouldSpreadByWindLevel2 = new ArrayList();

        //Go through the field
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {

                //Check if it is burning cell
                if (cell[i][j].get() == Cell.FIRE) {

                    if (!cell[i][j].isLightning() || (cell[i][j].getTurn() > 1 && cell[i][j].getTurn() < 5) || !stepLightning) {
                        //Spread fire to the neighbors
                        if (get(i + 1, j) == Cell.TREE) {
                            wouldBurnCell.add(cell[i + 1][j]);
                        }
                        if (get(i - 1, j) == Cell.TREE) {
                            wouldBurnCell.add(cell[i - 1][j]);
                        }
                        if (get(i, j + 1) == Cell.TREE) {
                            wouldBurnCell.add(cell[i][j+1]);
                        }
                        if (get(i, j - 1) == Cell.TREE) {
                            wouldBurnCell.add(cell[i ][j-1]);
                        }
                    }

                    if (!cell[i][j].isLightning() || cell[i][j].getTurn() == 0 || !stepLightning) {
                        //Tree is burned
                        cell[i][j].set(Cell.EMPTY);
                    }
                    else{
                        cell[i][j].setTurn(cell[i][j].getTurn() - 1);
                    }

                    //There are still burn cell left
                    burnCellLeft = true;
                }

                //Check if the tree cell get lightning or not
                if (cell[i][j].get() == Cell.TREE && Math.random() < probLightning) {
                    wouldLightningCell.add(cell[i][j]);
                    wouldLightningLeft=true;
                }
            }
        }

        //If there is no Fire Cell, stop
        if (!burnCellLeft && !wouldLightningLeft) {
            return true;
        }
        
        //Check all wouldBurnCell
        while (!wouldLightningCell.isEmpty()) {

            //Get the tree cell from the wouldLightningCell
            Cell lightningCell = wouldLightningCell.get(0);
            wouldLightningCell.remove(0);

            //Check if the tree is catched by fire
            if (Math.random() < probCatch) {
                lightningCell.set(Cell.LIGHTNING);
            }
        }
        
        //Check all wouldBurnCell
        while (!wouldBurnCell.isEmpty()) {

            //Get the tree cell from the wouldBurnCell
            Cell fireCell = wouldBurnCell.get(0);
            wouldBurnCell.remove(0);
            
            if(fireCell.get()!=Cell.TREE)continue;
            
            //Check if the tree is catched by fire
            if (Math.random() < probCatch) {
                fireCell.set(Cell.FIRE);
                windEffect(wouldSpreadByWindLevel1, fireCell.getI(), fireCell.getJ());
            }
        }
        
        if(windLevel>=1){
            while (!wouldSpreadByWindLevel1.isEmpty()) {

                //Get the tree cell from the wouldBurnCell
                Cell fireCell = wouldSpreadByWindLevel1.get(0);
                wouldSpreadByWindLevel1.remove(0);

                if(fireCell.get()!=Cell.TREE)continue;

                //Check if the tree is catched by fire
                if (Math.random() < probCatch) {
                    fireCell.set(Cell.FIRE);
                    windEffect(wouldSpreadByWindLevel2, fireCell.getI(), fireCell.getJ());
                }
            }
        }
        
        if(windLevel==2){
            while (!wouldSpreadByWindLevel2.isEmpty()) {

                //Get the tree cell from the wouldBurnCell
                Cell fireCell = wouldSpreadByWindLevel2.get(0);
                wouldSpreadByWindLevel2.remove(0);

                if(fireCell.get()!=Cell.TREE)continue;

                //Check if the tree is catched by fire
                if (Math.random() < probCatch) {
                    fireCell.set(Cell.FIRE);
                }
            }
        }

        //Increase the step count
        step++;

        numTree=countTree();
        //System.out.println(numTree+"/"+firstNumTree+" : "+getPercentRemainingTree());
        //Update the field
        update();

        //Delay the move
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //Still continue spreading fire
        return false;
    }

    /**
     * Add the observer for this model
     *
     * @param view
     */
    public void addObserver(View view) {
        observer = view;
        update();
    }

    /**
     * Update this field
     */
    public void update() {
        if (observer != null) {;
            observer.setPercent(this.getPercentRemainingTree());
            observer.setStep(step);
            observer.update(cell);
        }
    }

}
