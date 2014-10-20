package spreading_of_fire;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;



/**
 * The controller class of project from MVC pattern
 * 
 * @author aimmx
 * @version 2014.10.17
 */
public class Controller extends JPanel{
    private Model myModel;
    private View myView;

   public Controller(Model myModel,View myView){
        this.myModel=myModel;
        this.myView=myView;
        
        //Set layout to gridLayout with 1 column and 6 rows
        setLayout(new GridLayout(6,1));
        
        //Add main controller
        addMainController();
        
        //Add fire catch rate slider
        addProbCatchSlider();
        
        //Add tree survival rate slider
        addProbTreeSlider();
        
        //Add burning rate slider
        addProbFireSlider();
        
        //Add size slider
        addSizeSlider();
        
        //Add delay slider
        addDelaySlider();
    }
   private void addMainController(){
    //Create the panel for main controller
        JPanel controller1=new JPanel();
        
        //Add to the main panel
        add(controller1);
        
        //Add the move button
        addMoveButton(controller1);
        
        //Add the start button
        addStartButton(controller1);
        
        //Add the stop button
        addStopButton(controller1);
        
        //Add the reset button
        addResetButton(controller1);
        
        //Add the toggle button
        addToggleButton(controller1);
    }
   private void addMoveButton(JPanel mypanel){
    JButton moveButton=new JButton("Spread");
    
   
   }
   private void addStartButton(JPanel mypanel){
    JButton startButton=new JButton("Auto-Spread");
    
   }
   private void addStopButton(JPanel mypanel){
   JButton stopButton=new JButton("Stop");
   }
   private void addResetButton(JPanel mypanel){
   JButton resetButton=new JButton("Reset");
   }
   private void addToggleButton(JPanel mypanel){
   JButton toggleButton=new JButton("Toggle Value");
   
       
   }
   
   
   
   
   private void addProbCatchSlider(){
    JPanel myPanel=new JPanel();
        myPanel.setLayout(new GridLayout(1,2));
        add(myPanel);
        //Create and add the label panel
        JPanel myLabelPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);
         myLabelPanel.add(new JLabel("Fire Catch Rate : "));
        
         //Add the value label
         JLabel myLabel=new JLabel(""+(int)(myModel.getProbCatch()*100)+"%");
        myLabelPanel.add(myLabel);
         
        JPanel mySliderPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);
   
   }
   private void addProbTreeSlider(){
   JPanel myPanel=new JPanel();
        myPanel.setLayout(new GridLayout(1,2));
        add(myPanel);
        
        JPanel myLabelPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);     
         //Add the name label
        myLabelPanel.add(new JLabel("Tree Survival Rate : "));
      
           
 
   }
   private void addProbFireSlider(){
   //Create the panel with 2 column and add it to the main panel
        JPanel myPanel=new JPanel();
        myPanel.setLayout(new GridLayout(1,2));
        add(myPanel);
        
        //Create and add the label panel
        JPanel myLabelPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);
         //Add the name label
        myLabelPanel.add(new JLabel("Burning Rate : "));
        
         //Add the value label
        JLabel myLabel=new JLabel(""+(int)(myModel.getProbBurn()*100)+"%");
        myLabelPanel.add(myLabel);
            

        //Create and add the controller panel
        JPanel mySliderPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);
   }
   private void addSizeSlider(){
     JPanel myPanel=new JPanel();
        myPanel.setLayout(new GridLayout(1,2));
        add(myPanel);
        
        //Create and add the label panel
        JPanel myLabelPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);
            
        //Add the name label
        myLabelPanel.add(new JLabel("Size : "));
        
        //Add the value label
        JLabel myLabel=new JLabel(""+myModel.getWidth()+"x"+myModel.getHeight());
        myLabelPanel.add(myLabel);
            

        //Create and add the controller panel
        JPanel mySliderPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);
    }
   private void addDelaySlider(){
   JPanel myPanel=new JPanel();
        myPanel.setLayout(new GridLayout(1,2));
        add(myPanel);
        
        //Create and add the label panel
        JPanel myLabelPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);
            
        //Add the name label
        myLabelPanel.add(new JLabel("Delay : "));
        
         //Add the value label
        JLabel myLabel=new JLabel(""+myModel.getDelay()+"ms");
        myLabelPanel.add(myLabel);
            

        //Create and add the controller panel
        JPanel mySliderPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);
   }
}
