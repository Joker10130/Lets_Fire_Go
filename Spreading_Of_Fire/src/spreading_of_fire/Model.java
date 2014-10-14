/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreading_of_fire;

import java.util.ArrayList;

/**
 *
 * @author IQ
 */
public final class Model {
    private int width,height,delay;
    private double probCatch;
    private Cell cell[][];

    /**
     * Constructor, create the field
     */
    public Model() {
        this(31,31,0.5);
    }

    /**
     * Constructor, create the field
     * @param width
     * @param height
     */
    public Model(int width,int height){
        this(width,height,0.5);
    }

    /**
     * Constructor, create the field
     * @param width
     * @param height
     * @param probCatch
     */
    public Model(int width, int height, double probCatch) {
        //Set the properties of field
        this.width = width;
        this.height = height;
        this.probCatch = probCatch;
        this.delay=100;
        //Reset the field
        fieldReset();
    }

    /**
     * Set the fire catch rate
     * @param probCatch
     */
    public void setProbCatch(double probCatch) {
        this.probCatch = probCatch;
    }

    /**
     * Get the fire catch rate
     * @return probCatch
     */
    public double getProbCatch() {
        return probCatch;
    }
    
    /**
     * Set the size
     * @param width
     * @param height
     */
    public void setSize(int width,int height){
        this.width=width;
        this.height=height;
        //Reset the field after set
        fieldReset();
    }
    
    /**
     * Reset the field
     */
    public void fieldReset(){
        //Create new field with current size
        cell=new Cell[height][width];
        
        //Place the tree cell
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                cell[i][j]=new Cell(Cell.TREE);
            }
        }
        
        //Place the burning cell at the center
        cell[height/2][width/2]=new Cell(Cell.FIRE);
        
    }
    
    /**
     * Start moving
     */
    public void start(){
        //Move until it can't move
        while(!move());
    }
    
    /**
     * Get the state of cell at x,y
     * @param x
     * @param y
     * @return Cell state
     */
    private int get(int x,int y){
        try{
            return cell[x][y].get();
        }
        catch(Exception e){
            //If x,y out of bound, treat as empty
            return Cell.EMPTY;
        }
    }
    
    /**
     * Spread fire cells
     * @return true if there is no burning cell
     */
    public boolean move(){
        
        boolean burnCellLeft=false;
        ArrayList<Cell> wouldBurnCell=new ArrayList();
        
        //Go through the field
        for(int i=0;i<cell.length;i++){
            for(int j=0;j<cell[0].length;j++){
                
                //Check if it is burning cell
                if(cell[i][j].get()==Cell.FIRE){
                    
                    //Spread fire to the neighbors
                    if(get(i+1,j)==Cell.TREE)wouldBurnCell.add(cell[i+1][j]);
                    if(get(i-1,j)==Cell.TREE)wouldBurnCell.add(cell[i-1][j]);
                    if(get(i,j+1)==Cell.TREE)wouldBurnCell.add(cell[i][j+1]);
                    if(get(i,j-1)==Cell.TREE)wouldBurnCell.add(cell[i][j-1]);
                    
                    //Tree is burned
                    cell[i][j].set(Cell.EMPTY);
                    
                    //There are still burn cell left
                    burnCellLeft=true;
                }
            }
        }
        
        //If there is no Fire Cell, stop
        if(!burnCellLeft)return true;
        
        //Check all wouldBurnCell
        while(!wouldBurnCell.isEmpty()){
            
            //Get the tree cell from the wouldBurnCell
            Cell fireCell=wouldBurnCell.get(0);
            wouldBurnCell.remove(0);
            
            //Check if the tree is catched by fire
            if(Math.random() < probCatch){
                fireCell.set(Cell.FIRE);
            }
        }
        
        //Print the field
        print();
        
        //Delay the move
        try {
            Thread.sleep(delay);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        //Still continue spreading fire
        return false;
    }
    
    private void print(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                System.out.print(cell[i][j].get()+" ");
            }
            System.out.println("");
        }
    }
    
}
