package spreading_of_fire;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



/**
 * The controller class of project from MVC pattern
 * 
 * @author aimmx
 * @version 2014.10.17
 */
public class Controller extends JPanel{
    private Model myModel;
    private View myView;
    private Thread startThread;
   
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
        
             
        //Create and Add the Slider from 0-100
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL,0, 100, 50);
        mySlider.addChangeListener(new ChangeListener() {
             public void stateChanged(ChangeEvent e) {
                 //Set the Fire Catch Rate
                int newProb=((JSlider)(e.getSource())).getValue();
                myModel.setProbCatch((double)newProb/100);
                //Change the label
                myLabel.setText(""+(int)(myModel.getProbCatch()*100)+"%");
            }
        });
          //Add the slider to the panel
        mySliderPanel.add(mySlider);
   }
   private void addProbTreeSlider(){
              
        //Create the panel with 2 column and add it to the main panel
        JPanel myPanel=new JPanel();
        myPanel.setLayout(new GridLayout(1,2));
        add(myPanel);
        
        //Create and add the label panel
        JPanel myLabelPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);
            
        //Add the name label
        myLabelPanel.add(new JLabel("Tree Survival Rate : "));

        //Add the value label
        JLabel myLabel=new JLabel(""+(int)(myModel.getProbTree()*100)+"%");
        myLabelPanel.add(myLabel);
            

        //Create and add the controller panel
        JPanel mySliderPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);
            
        //Create and Add the Slider from 0-100
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL,0, 100, 0);
        mySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Set the Tree Survival Rate
                int newProbTree=((JSlider)(e.getSource())).getValue();
                myModel.setProbTree((double)newProbTree/100.0);
                //Change the label
                myLabel.setText(""+(int)(myModel.getProbTree()*100)+"%");
            }
        });
        
        //Add the slider to the panel
        mySliderPanel.add(mySlider);
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
        
        //Create and Add the Slider from 0-100
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL,0, 100, 100);
        mySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Set the Burning Rate
                int newProbBurn=((JSlider)(e.getSource())).getValue();
                myModel.setProbBurn((double)newProbBurn/100.0);
                //Change the label
                myLabel.setText(""+(int)(myModel.getProbBurn()*100)+"%");
            }
        });
        
        //Add the slider to the panel
        mySliderPanel.add(mySlider);
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
        //Create and Add the Slider from 5-49
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL,5, 49, 15);
        mySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               //Set the Size in myModel
                int newSize=((JSlider)(e.getSource())).getValue();
                myModel.setSize(newSize*2+1,newSize*2+1);
                //Set the Size in myView
                int boxSize=(int)((500)/(newSize*2+1));
                myView.setBlockSize(boxSize, boxSize);
                //Change the label
                myLabel.setText(""+myModel.getWidth()+"x"+myModel.getHeight());
                //Stop the Thread if alive
               if(startThread!=null&&startThread.isAlive()){
                    startThread.stop();
                }
                
            }
        });
        
        //Add the slider to the panel
        mySliderPanel.add(mySlider);
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
        //Create and Add the Slider from 0-100
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL,1, 1000, 100);
        mySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               //Set the delay
                int newDelay=((JSlider)(e.getSource())).getValue();
                myModel.setDelay(newDelay);
                //Change the label
                myLabel.setText(""+myModel.getDelay()+"ms");
            }
        });
        
        //Add the slider to the panel
        mySliderPanel.add(mySlider);
   }
}
