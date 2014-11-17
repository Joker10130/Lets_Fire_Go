package spreading_of_fire;

import java.awt.Color;

/**
 * The cell class that contain the properties of cell
 *
 * @author IQ
 * @version 2.0
 */
public class Cell {

    public static final int FIRE = 2, TREE = 1, EMPTY = 0, LIGHTNING = 3;
    private int status;
    private boolean lightning;
    private int turn;
    private int i, j;
    private double probCatchFactor;

    /**
     * Constructor - create the empty cell
     * @param i
     * @param j
     */
    public Cell(int i, int j) {
        this(Cell.EMPTY, i, j);
    }

    /**
     * Constructor - create the cell
     *
     * @param status
     * @param i
     * @param j
     */
    public Cell(int status, int i, int j) {
        this.status = status;
        this.i = i;
        this.j = j;
        lightning = false;
        turn = 0;
        probCatchFactor=-1.0;
    }

    public double getProbCatchFactor() {
        return probCatchFactor;
    }

    public void setProbCatchFactor(double probCatchFactor) {
        this.probCatchFactor = Math.max(probCatchFactor, this.probCatchFactor);
    }
    
    /**
     * Get the status of cell
     *
     * @return status
     */
    public int get() {
        return status;
    }

    /**
     * Set the color of cell
     *
     * @param status
     */
    public void set(int status) {
        if (status == LIGHTNING) {
            this.status = FIRE;
            lightning = true;
            turn = 5;
        } else {
            this.status = status;
            lightning = false;
            turn = 0;
        }
    }

    /**
     * Get the Y-coordination of the cell
     *
     * @return I
     */
    public int getI() {
        return i;
    }

    /**
     * Get the X-coordination of the cell
     *
     * @return J
     */
    public int getJ() {
        return j;
    }

    /**
     * Get the Lightning Status
     *
     * @return lightning
     */
    public boolean isLightning() {
        return lightning;
    }

    /**
     * Get the burning turn
     *
     * @return turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Set the burning turn
     *
     * @param turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Check if the cell is empty or not
     *
     * @return true if cell empty
     */
    public boolean isEmpty() {
        return status == EMPTY;
    }

    /**
     * Get the RGB Color of cell
     *
     * @return RGB Color
     */
    public Color getColor() {
        if (lightning) {
            return Color.BLUE;
        }
        if (status == FIRE) {
            return Color.RED;
        }
        if (status == TREE) {
            return new Color(0, 180, 0);
        }
        return Color.YELLOW;
    }
}
