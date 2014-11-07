/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spreading_of_fire;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author IQ
 */
public class Help extends JFrame {

    public Help() {
        super("Help");
        setResizable(false);
        try {
            setSize(800, 600);
            JEditorPane helpContents = new JEditorPane(getClass().getResource("help/index.html"));
            helpContents.setEditable(false);
            helpContents.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent he) {
                    try {
                        if (he.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                            helpContents.setPage(he.getURL());
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Help.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            JScrollPane mainPanel = new JScrollPane(helpContents);
            mainPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            this.add(mainPanel);
        } catch (Exception e) {
            setSize(200, 60);
            this.add(new JLabel("Cannot find \"Help\""));
        }
    }
}
