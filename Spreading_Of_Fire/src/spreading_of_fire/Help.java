/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spreading_of_fire;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * The Help class that show the help frame
 * 
 * @author IQ
 * @version 2.0
 */
public class Help extends JFrame {

    public Help() {
        //Run constructor from the super class
        super("Help");
        //Cannot be resized
        setResizable(false);
        
        //Try if it can load the help or not
        try {
            //Set the size of the frame
            setSize(960, 720);
            
            //Load the help from HTML file
            JEditorPane helpContents = new JEditorPane(getClass().getResource("help/index.html"));
            
            //Help cannot be edited
            helpContents.setEditable(false);
            
            //Add the hyperlink behavior in the help
            helpContents.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent he) {
                    try {
                        //When click the hyperlink...
                        if (he.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                            //Go to url
                            helpContents.setPage(he.getURL());
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Help.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            //Add to the main panel
            JScrollPane mainPanel = new JScrollPane(helpContents);
            
            //Disable the horizonal scroll bar
            mainPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            //Add to the frame
            this.add(mainPanel);
        } catch (Exception e) {
            // Show the error if the help can not be loaded
            setSize(200, 60);
            this.add(new JLabel("Cannot find \"Help\""));
        }
    }
}
