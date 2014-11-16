package spreading_of_fire;

import java.util.ArrayList;

/**
 * The view class of project from MVC pattern
 *
 * @author IQ
 * @version 2.0
 */
public final class Model {

    private int width, height, delay;
    private double probCatch, probTree, probBurn, probLightning;
    private int step;
    private Cell cell[][];
    private View myView;
    private boolean stepLightning;
    private int firstNumTree, numTree;
    private int firstFireCellX, firstFireCellY;
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
     * @param probTree
     * @param probBurn
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
        this.firstFireCellX = width / 2;
        this.firstFireCellY = height / 2;
        this.windDirection = "N";
        this.windLevel = 0;
        myView = null;
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
     * Set the lightning chance
     *
     * @param probLightning
     */
    public void setProbLightning(double probLightning) {
        this.probLightning = probLightning;
    }

    /**
     * Get the stepLightning Status
     *
     * @return stepLightning
     */
    public boolean getStepLightning() {
        return stepLightning;
    }

    /**
     * Set the stepLightning Status
     *
     * @param stepLightning
     */
    public void setStepLightning(boolean stepLightning) {
        this.stepLightning = stepLightning;
        //Update the stepLightning Status to the View
        update();
    }

    /**
     * Set the wind Direction
     *
     * @param windDirection
     */
    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * Set the wind level
     *
     * @param windLevel
     */
    public void setWindLevel(int windLevel) {
        this.windLevel = windLevel;
    }

    /**
     * Get the wind Direction
     *
     * @return windDirection
     */
    public String getWindDirection() {
        return windDirection;
    }

    /**
     * Get the wind Level
     *
     * @return windLevel
     */
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
        this.firstFireCellX = width / 2;
        this.firstFireCellY = height / 2;
        //Reset the field after set
        fieldReset();
    }

    /**
     * Get the X-coordinate of the initial fire cell
     *
     * @return firstFireCellX
     */
    public int getFirstFireCellX() {
        return firstFireCellX;
    }

    /**
     * Get the Y-coordinate of the initial fire cell
     *
     * @return firstFireCellY
     */
    public int getFirstFireCellY() {
        return firstFireCellY;
    }

    /**
     * Set the coordinate of the initial fire cell
     *
     * @param firstFireCellX
     * @param firstFireCellY
     */
    public void setFirstFireCell(int firstFireCellX, int firstFireCellY) {
        this.firstFireCellX = Math.min(width, Math.max(0, firstFireCellX));
        this.firstFireCellY = Math.min(width, Math.max(0, firstFireCellY));
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
                cell[i][j] = new Cell(i, j);
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

        //Place the burning cell
        cell[firstFireCellY][firstFireCellX].set(Cell.FIRE);

        //Initial the number of trees
        numTree = firstNumTree = countTree();

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

    /**
     * Count the number of trees
     *
     * @return countTree
     */
    private int countTree() {
        int countTree = 0;
        //Go through all cells
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                //If the cell is not empty, count it
                if (cell[i][j].get() != Cell.EMPTY) {
                    countTree++;
                }
            }
        }
        //Return the value
        return countTree;
    }

    /**
     * Get the Percent Remaining Tree
     *
     * @return percentRemainingTree
     */
    public double getPercentRemainingTree() {
        return ((double) numTree / (double) firstNumTree) * 100;
    }

    /**
     * Add the cell that affected by wind from cell[i][j] to the cellList
     *
     * @param cellList
     * @param i
     * @param j
     */
    private void windEffect(ArrayList<Cell> cellList, int i, int j) {
        if ("S".equals(windDirection)) {
            if (get(i + 1, j) == Cell.TREE) {
                cellList.add(cell[i + 1][j]);
            }
        }
        if ("W".equals(windDirection)) {
            if (get(i, j - 1) == Cell.TREE) {
                cellList.add(cell[i][j - 1]);
            }
        }
        if ("N".equals(windDirection)) {
            if (get(i - 1, j) == Cell.TREE) {
                cellList.add(cell[i - 1][j]);
            }
        }
        if ("E".equals(windDirection)) {
            if (get(i, j + 1) == Cell.TREE) {
                cellList.add(cell[i][j + 1]);
            }
        }
    }

    /**
     * Spread fire cells
     *
     * @return true if there is no burning cell
     */
    public boolean move() {

        boolean burnCellLeft = false, wouldLightningLeft = false;
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
                            wouldBurnCell.add(cell[i][j + 1]);
                        }
                        if (get(i, j - 1) == Cell.TREE) {
                            wouldBurnCell.add(cell[i][j - 1]);
                        }
                    }

                    //If the cell is not burned from lighting or stepLightning is off or turn = 0, cell will become empty
                    if (!cell[i][j].isLightning() || cell[i][j].getTurn() == 0 || !stepLightning) {
                        //Tree is burned
                        cell[i][j].set(Cell.EMPTY);
                    } else {
                        //If not, decrease the turn of cell instead
                        cell[i][j].setTurn(cell[i][j].getTurn() - 1);
                    }

                    //There are still burn cell left
                    burnCellLeft = true;
                }

                //Check if the tree cell get lightning or not
                if (cell[i][j].get() == Cell.TREE && Math.random() < probLightning) {
                    //Add it to lightning cell
                    wouldLightningCell.add(cell[i][j]);
                    //There are still lightning cell left
                    wouldLightningLeft = true;
                }
            }
        }

        //If there is no Fire or Lightning Cell, stop
        if (!burnCellLeft && !wouldLightningLeft) {
            return true;
        }

        //Check all wouldLightningCell
        while (!wouldLightningCell.isEmpty()) {

            //Get the tree cell from the wouldLightningCell
            Cell lightningCell = wouldLightningCell.get(0);
            wouldLightningCell.remove(0);

            //Check if the tree is catched by fire from lightning
            if (Math.random() < probCatch) {
                lightningCell.set(Cell.LIGHTNING);
            }
        }

        //Check all wouldBurnCell
        while (!wouldBurnCell.isEmpty()) {

            //Get the tree cell from the wouldBurnCell
            Cell fireCell = wouldBurnCell.get(0);
            wouldBurnCell.remove(0);

            if (fireCell.get() != Cell.TREE) {
                continue;
            }

            //Check if the tree is catched by fire
            if (Math.random() < probCatch) {
                fireCell.set(Cell.FIRE);
                //Spead to the neighbor by the wind effect
                windEffect(wouldSpreadByWindLevel1, fireCell.getI(), fireCell.getJ());
            }
        }

        //If wind level is more than 1, burn those cells
        if (windLevel >= 1) {
            while (!wouldSpreadByWindLevel1.isEmpty()) {

                //Get the tree cell from the wouldSpreadByWindLevel1
                Cell fireCell = wouldSpreadByWindLevel1.get(0);
                wouldSpreadByWindLevel1.remove(0);

                if (fireCell.get() != Cell.TREE) {
                    continue;
                }

                //Check if the tree is catched by fire
                if (Math.random() < probCatch) {
                    fireCell.set(Cell.FIRE);
                    //Spead to the neighbor by the wind effect again
                    windEffect(wouldSpreadByWindLevel2, fireCell.getI(), fireCell.getJ());
                }
            }
        }

        //If wind level is 2, burn those cells
        if (windLevel == 2) {
            while (!wouldSpreadByWindLevel2.isEmpty()) {

                //Get the tree cell from the wouldSpreadByWindLevel2
                Cell fireCell = wouldSpreadByWindLevel2.get(0);
                wouldSpreadByWindLevel2.remove(0);

                if (fireCell.get() != Cell.TREE) {
                    continue;
                }

                //Check if the tree is catched by fire
                if (Math.random() < probCatch) {
                    fireCell.set(Cell.FIRE);
                }
            }
        }

        //Increase the step count
        step++;

        //Update the number of tree
        numTree = countTree();

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
     * Add the View to the model
     *
     * @param view
     */
    public void observe(View view) {
        myView = view;
        update();
    }

    /**
     * Update this field
     */
    public void update() {
        if (myView != null) {
            //Send cell, step, percent remaining tree, stepLightning status to the View
            myView.update(cell, step, this.getPercentRemainingTree(), stepLightning);
        }
    }

}
