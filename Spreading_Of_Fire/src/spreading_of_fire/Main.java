package spreading_of_fire;

import java.awt.*;
import javax.swing.*;

/**
 * The Main class of project that contains Model, View, and Controller
 *
 * @author Lets_Fire_Go
 * @version 2.0
 */
public class Main extends JFrame {

    /**
     * Create the GUI of project
     */
    public Main() {

        //Create the main frame with 2 column
        super("Spreading Of Fire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 600);
        setResizable(false);
        setLayout(new GridLayout(1, 2));

        //Create the model, the main process of project
        Model myModel = new Model();

        //Create the view, the output of main process of project
        int boxWidth = (int) ((500) / myModel.getWidth());
        int boxHeight = (int) (500 / myModel.getHeight());
        View myView = new View(boxWidth, boxHeight);

        //Add the myView panel to the left
        add(myView);

        //Let myView be the observer of myModel
        myModel.registerObserver(myView);

        //Create the controller panel and it must know myModel and myView
        JPanel controller = new Controller(myModel, myView);

        //Add the controller panel to the right
        add(controller);

        //Set the frame Visible
        setVisible(true);
    }

}
