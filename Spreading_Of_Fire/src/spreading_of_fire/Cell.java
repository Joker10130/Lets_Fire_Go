package spreading_of_fire;

import java.awt.Color;

/**
 * The cell class that contain the properties of cell
 *
 * @author Lets_Fire_Go
 * @version 2014.10.14
 */
public class Cell {

    public static final int FIRE = 2, TREE = 1, EMPTY = 0, LIGHTNING = 3;
    private int status;
    private boolean lightning;
    private int turn;

    /**
     * Constructor - create the empty cell
     */
    public Cell() {
        this(Cell.EMPTY);
    }

    /**
     * Constructor - create the cell
     *
     * @param status
     */
    public Cell(int status) {
        this.status = status;
        lightning = false;
        turn = 0;
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

    public boolean isLightning() {
        return lightning;
    }

    public int getTurn() {
        return turn;
    }

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
        if(lightning)return Color.BLUE;
        if (status == FIRE) {
            return Color.RED;
        }
        if (status == TREE) {
            return new Color(0, 180, 0);
        }
        return Color.YELLOW;
    }
}
