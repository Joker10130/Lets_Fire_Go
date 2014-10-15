package spreading_of_fire;

import javax.swing.JPanel;

/**
 * The controller class of project from MVC pattern
 * 
 * @author Lets_Fire_Go
 * @version 2014.10.14
 */
public class Controller extends JPanel implements Controller_Interface{
    private Model myModel;
    private View myView;

    public Controller(Model myModel, View myView) {
        this.myModel = myModel;
        this.myView = myView;
    }
    
}
