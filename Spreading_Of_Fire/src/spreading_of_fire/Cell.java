/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreading_of_fire;

import java.awt.Color;

/**
 *
 * @author IQ
 */
public class Cell {
    public static final int FIRE=2,TREE=1,EMPTY=0;
    private int status;

    /**
     * Constructor - create the empty cell
     */
    public Cell(){
        this.status=Cell.EMPTY;
    }

    /**
     * Constructor - create the cell
     * @param status
     */
    public Cell(int status){
        this.status=status;
    }

    /**
     * Get the status of cell
     * @return status
     */
    public int get(){
        return status;
    }

    /**
     * Set the color of cell
     * @param status
     */
    public void set(int status){
        this.status=status;
    }

    /**
     * Check if the cell is empty or not
     * @return true if cell empty
     */
    public boolean isEmpty(){
        return status==EMPTY;
    }

    /**
     * Get the RGB Color of cell
     * @return RGB Color
     */
    public Color getColor(){
        if(status==FIRE)return Color.RED;
        if(status==TREE)return new Color(0,180,0);
        return Color.YELLOW;
    }
}

