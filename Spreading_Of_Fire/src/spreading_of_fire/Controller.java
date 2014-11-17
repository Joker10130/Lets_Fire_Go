package spreading_of_fire;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The controller class of project from MVC pattern
 *
 * @author aimmx
 * @version 2.0
 */
public class Controller extends JPanel {

    private Model myModel;
    private View myView;
    private Thread startThread;

    private JSlider XSlider, YSlider;
    private JLabel XLabel, YLabel;

    public Controller(Model myModel, View myView) {
        this.myModel = myModel;
        this.myView = myView;

        //Set layout to gridLayout with 1 column and 6 rows
        setLayout(new GridLayout(10, 1));

        //Add main controller
        addMainController();

        //Add main controller
        addMainController2();

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

        //Add lightning slider
        addProbLightningSlider();

        //add Wind Controller
        addWindController();

        //add Initial Fire Setter
        addInitialFireSetter();

    }

    private void addMainController() {
        //Create the panel for main controller
        JPanel controller1 = new JPanel();

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


    }

    private void addMainController2() {
        //Create the panel for main controller
        JPanel controller2 = new JPanel();

        //Add to the main panel
        add(controller2);

        //Add the toggle button
        addToggleButton(controller2);
        
        //Add the steplightning button
        addStepButton(controller2);
        
        //Add the Help button
        addHelpButton(controller2);

    }

    private void addMoveButton(JPanel myPanel) {

        //Create the button
        JButton moveButton = new JButton("Spread");
        //Add the action listener to the button
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //If there is no Thread or Thread is dead, do move
                if (startThread == null || !startThread.isAlive()) {
                    myModel.move();
                }
            }
        });

        //Add the button to the myPanel
        myPanel.add(moveButton);
    }

    private void addStartButton(JPanel myPanel) {

        //Create the button
        JButton startButton = new JButton("Auto-Spread");
        //Add the action listener to the button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //If there is no Thread or Thread is dead, create new Thread and start
                if (startThread == null || !startThread.isAlive()) {
                    startThread = new Thread() {
                        @Override
                        public void run() {
                            myModel.start();
                        }
                    };
                    startThread.start();
                }
            }
        });

        //Add the button to the myPanel
        myPanel.add(startButton);
    }

    private void addStopButton(JPanel myPanel) {

        //Create the button
        JButton stopButton = new JButton("Stop");

        //Add the action listener to the button
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //If There is an alive Thread, stop it
                if (startThread != null && startThread.isAlive()) {
                    startThread.stop();
                }

            }
        });

        //Add the button to the myPanel
        myPanel.add(stopButton);
    }

    private void addResetButton(JPanel myPanel) {

        //Create the button
        JButton resetButton = new JButton("Reset");

        //Add the action listener to the button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //If There is an alive Thread, stop it
                if (startThread != null && startThread.isAlive()) {
                    startThread.stop();

                }
                //Reset the main process
                myModel.fieldReset();
            }
        });
        //Add the button to the myPanel
        myPanel.add(resetButton);
    }

    private void addToggleButton(JPanel myPanel) {

        //Create the button
        JButton toggleButton = new JButton("Toggle Value");

        //Add the action listener to the button
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Toggle to show value in the View
                myView.toggleValue();
            }
        });
        //Add the button to the myPanel
        myPanel.add(toggleButton);
    }

    private void addHelpButton(JPanel myPanel) {

        //Create the button
        JButton helpButton = new JButton("Help");

        //Create the help window
        Help help = new Help();

        //Add the action listener to the button
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Show the help window
                help.setVisible(true);
            }
        });
        //Add the button to the myPanel
        myPanel.add(helpButton);
    }

    private void addStepButton(JPanel myPanel) {

        //Create the button
        JButton stepButton = new JButton("Toggle Step Lightning");

        //Add the action listener to the button
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Toggle the stepLightning Status in Model
                myModel.setStepLightning(!myModel.getStepLightning());
            }
        });

        //Add the button to the myPanel
        myPanel.add(stepButton);

    }

    private void addProbCatchSlider() {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(1, 2));
        add(myPanel);
        //Create and add the label panel
        JPanel myLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);
        myLabelPanel.add(new JLabel("Fire Catch Rate : "));

        //Add the value label
        JLabel myLabel = new JLabel("" + (int) (myModel.getProbCatch() * 100) + "%");
        myLabelPanel.add(myLabel);

        JPanel mySliderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);

        //Create and Add the Slider from 0-100
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        mySlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                //Set the Fire Catch Rate
                int newProb = ((JSlider) (e.getSource())).getValue();
                myModel.setProbCatch((double) newProb / 100);
                //Change the label
                myLabel.setText("" + (int) (myModel.getProbCatch() * 100) + "%");
            }
        });
        //Add the slider to the panel
        mySliderPanel.add(mySlider);
    }

    private void addProbTreeSlider() {

        //Create the panel with 2 column and add it to the main panel
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(1, 2));
        add(myPanel);

        //Create and add the label panel
        JPanel myLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);

        //Add the name label
        myLabelPanel.add(new JLabel("Tree Density: "));

        //Add the value label
        JLabel myLabel = new JLabel("" + (int) (myModel.getProbTree() * 100) + "%");
        myLabelPanel.add(myLabel);

        //Create and add the controller panel
        JPanel mySliderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);

        //Create and Add the Slider from 0-100
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        mySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Set the Tree Survival Rate
                int newProbTree = ((JSlider) (e.getSource())).getValue();
                myModel.setProbTree((double) newProbTree / 100.0);
                //Change the label
                myLabel.setText("" + (int) (myModel.getProbTree() * 100) + "%");
                //If There is an alive Thread, stop it
                if (startThread != null && startThread.isAlive()) {
                    startThread.stop();

                }
                //Reset the main process
                myModel.fieldReset();
            }
        });

        //Add the slider to the panel
        mySliderPanel.add(mySlider);
    }

    private void addProbFireSlider() {
        //Create the panel with 2 column and add it to the main panel
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(1, 2));
        add(myPanel);

        //Create and add the label panel
        JPanel myLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);
        //Add the name label
        myLabelPanel.add(new JLabel("Initial burning : "));

        //Add the value label
        JLabel myLabel = new JLabel("" + (int) (myModel.getProbBurn() * 100) + "%");
        myLabelPanel.add(myLabel);

        //Create and add the controller panel
        JPanel mySliderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);

        //Create and Add the Slider from 0-100
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        mySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Set the Burning Rate
                int newProbBurn = ((JSlider) (e.getSource())).getValue();
                myModel.setProbBurn((double) newProbBurn / 100.0);
                //Change the label
                myLabel.setText("" + (int) (myModel.getProbBurn() * 100) + "%");
                //If There is an alive Thread, stop it
                if (startThread != null && startThread.isAlive()) {
                    startThread.stop();

                }
                //Reset the main process
                myModel.fieldReset();
            }
        });

        //Add the slider to the panel
        mySliderPanel.add(mySlider);
    }

    private void addSizeSlider() {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(1, 2));
        add(myPanel);

        //Create and add the label panel
        JPanel myLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);

        //Add the name label
        myLabelPanel.add(new JLabel("Size : "));

        //Add the value label
        JLabel myLabel = new JLabel("" + myModel.getWidth() + "x" + myModel.getHeight());
        myLabelPanel.add(myLabel);

        //Create and add the controller panel
        JPanel mySliderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);
        //Create and Add the Slider from 5-49
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL, 5, 49, 15);
        mySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Set the Size in myModel
                int newSize = ((JSlider) (e.getSource())).getValue();
                myModel.setSize(newSize * 2 + 1, newSize * 2 + 1);
                //Get the new initial fire cell coordinate
                int X = myModel.getFirstFireCellX();
                int Y = myModel.getFirstFireCellY();
                //Set the Size in myView
                int boxSize = (int) ((500) / (newSize * 2 + 1));
                myView.setBlockSize(boxSize, boxSize);
                //Change the label
                myLabel.setText("" + myModel.getWidth() + "x" + myModel.getHeight());
                //Change the initail fire cell controller
                if (XSlider != null && YSlider != null) {
                    //Change the max of slider 
                    XSlider.setMaximum(myModel.getWidth() - 1);
                    //Change the value
                    XSlider.setValue(X);
                    XLabel.setText("" + (X + 1));
                    //Change the max of slider 
                    YSlider.setMaximum(myModel.getHeight() - 1);
                    //Change the value
                    YSlider.setValue(Y);
                    YLabel.setText("" + (Y + 1));
                }
                //Stop the Thread if alive
                if (startThread != null && startThread.isAlive()) {
                    startThread.stop();
                }

            }
        });

        //Add the slider to the panel
        mySliderPanel.add(mySlider);
    }

    private void addDelaySlider() {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(1, 2));
        add(myPanel);

        //Create and add the label panel
        JPanel myLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);

        //Add the name label
        myLabelPanel.add(new JLabel("Delay : "));

        //Add the value label
        JLabel myLabel = new JLabel("" + myModel.getDelay() + "ms");
        myLabelPanel.add(myLabel);

        //Create and add the controller panel
        JPanel mySliderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);
        //Create and Add the Slider from 0-100
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 100);
        mySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Set the delay
                int newDelay = ((JSlider) (e.getSource())).getValue();
                myModel.setDelay(newDelay);
                //Change the label
                myLabel.setText("" + myModel.getDelay() + "ms");
            }
        });

        //Add the slider to the panel
        mySliderPanel.add(mySlider);
    }

    private void addProbLightningSlider() {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(1, 2));
        add(myPanel);

        //Create and add the label panel
        JPanel myLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);

        //Add the name label
        myLabelPanel.add(new JLabel("Lighting Chance : "));

        //Add the value label
        JLabel myLabel = new JLabel("" + (int) (myModel.getProbLightning() * 100) + "%");
        myLabelPanel.add(myLabel);

        //Create and add the controller panel
        JPanel mySliderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);
        //Create and Add the Slider from 0-100
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        mySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Set the Lightning Chance
                int newProb = ((JSlider) (e.getSource())).getValue();
                myModel.setProbLightning((double) newProb / 100);
                //Change the label
                myLabel.setText("" + (int) (myModel.getProbLightning() * 100) + "%");
            }
        });

        //Add the slider to the panel
        mySliderPanel.add(mySlider);
    }

    private void addWindController() {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(1, 2));
        add(myPanel);

        //Wind Direction Controller Part
        
        //Create and add the label panel
        JPanel myLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanel);

        //Create the comboBox of wind direction
        JComboBox windDirectionComboBox = new JComboBox();
        windDirectionComboBox.addItem("Northern");
        windDirectionComboBox.addItem("Western");
        windDirectionComboBox.addItem("Eastern");
        windDirectionComboBox.addItem("Southern");
        windDirectionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Get the selected direction
                String direction = (String) ((JComboBox) ae.getSource()).getSelectedItem();
                //Set the new direction
                myModel.setWindDirection(direction.substring(0, 1));
            }
        });

        //Add comboBox to the panel
        myLabelPanel.add(windDirectionComboBox);

        //Wind Level Controller Part
        
        //Add the name label
        myLabelPanel.add(new JLabel("Wind Level : "));

        //Add the value label
        JLabel myLabel = new JLabel("" + myModel.getWindLevel());
        myLabelPanel.add(myLabel);

        //Create and add the controller panel
        JPanel mySliderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanel);

        //Create and Add the Slider from 0-2
        JSlider mySlider = new JSlider(JSlider.HORIZONTAL, 0, 2, 0);
        mySlider.setSize(100, 10);
        mySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Set the Wind Level
                int newLevel = ((JSlider) (e.getSource())).getValue();
                myModel.setWindLevel(newLevel);
                //Change the label
                myLabel.setText("" + myModel.getWindLevel());
            }
        });
        
        //Add the slider to the panel
        mySliderPanel.add(mySlider);
    }

    private void addInitialFireSetter() {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(2, 2));
        add(myPanel);

        //X-Coordinate Part
        
        //Create and add the label panel
        JPanel myLabelPanelX = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanelX);

        //Add the name label
        myLabelPanelX.add(new JLabel("Initial Fire X-Coordinate : "));

        //Add the value label
        JLabel myLabelX = new JLabel("" + (myModel.getFirstFireCellX() + 1));
        myLabelPanelX.add(myLabelX);

        //Create and add the controller panel
        JPanel mySliderPanelX = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanelX);
        //Create and Add the Slider from 0-width
        JSlider mySliderX = new JSlider(JSlider.HORIZONTAL, 0, myModel.getWidth() - 1, (int) (myModel.getWidth() / 2.0));
        mySliderX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Set the initial fire cell coordinate
                int newX = ((JSlider) (e.getSource())).getValue();
                myModel.setFirstFireCell(newX, myModel.getFirstFireCellY());
                //Change the label
                myLabelX.setText("" + (myModel.getFirstFireCellX() + 1));
                //If There is an alive Thread, stop it
                if (startThread != null && startThread.isAlive()) {
                    startThread.stop();

                }
                //Reset the main process
                myModel.fieldReset();
            }
        });

        //Add the slider to the panel
        mySliderPanelX.add(mySliderX);

        //Y-Coordinate Part
        
        //Create and add the label panel
        JPanel myLabelPanelY = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(myLabelPanelY);

        //Add the name label
        myLabelPanelY.add(new JLabel("Initial Fire Y-Coordinate : "));

        //Add the value label
        JLabel myLabelY = new JLabel("" + (myModel.getFirstFireCellY() + 1));
        myLabelPanelY.add(myLabelY);

        //Create and add the controller panel
        JPanel mySliderPanelY = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(mySliderPanelY);
        //Create and Add the Slider from 0-height
        JSlider mySliderY = new JSlider(JSlider.HORIZONTAL, 0, myModel.getHeight() - 1, (int) (myModel.getHeight() / 2.0));
        mySliderY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Set the initial fire cell coordinate
                int newY = ((JSlider) (e.getSource())).getValue();
                myModel.setFirstFireCell(myModel.getFirstFireCellX(), newY);
                //Change the label
                myLabelY.setText("" + (myModel.getFirstFireCellY() + 1));
                //If There is an alive Thread, stop it
                if (startThread != null && startThread.isAlive()) {
                    startThread.stop();

                }
                //Reset the main process
                myModel.fieldReset();
            }
        });

        //Assign to Slider and label to global variable
        XSlider = mySliderX;
        YSlider = mySliderY;
        XLabel = myLabelX;
        YLabel = myLabelY;

        //Add the slider to the panel
        mySliderPanelY.add(mySliderY);
    }
}
