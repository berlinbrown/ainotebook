package org.berlin.pino.test.functional.teeter;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;

import org.berlin.pino.win.PinoWindowBuilder;
import org.berlin.pino.win.base.AbstractWindowBuilder;
import org.berlin.pino.win.base.BasicWindow;
import org.berlin.pino.win.base.IBasicWindow;

public class TeeterWindowTest {

    
    public static void main(String [] args) {
        
        final IBasicWindow window = new BasicWindow(); 
        final AbstractWindowBuilder windowBuilder = new PinoWindowBuilder(window);
        windowBuilder.build();
        
        System.out.println(window);
        
        ///////////////
        final JFrame frame = new JFrame("App - " + new Date());              
        frame.add(window.getComponent());
        frame.setLocation(400, 400);
        frame.setBackground(Color.white);
        frame.pack();
        
        ///////////////////////////////
        frame.addWindowListener(new WindowAdapter() {            
            public void windowClosing(WindowEvent e) {
                System.out.println("Exiting...");
                System.exit(0);
            }            
        });

        frame.setVisible(true);        
    }
    
} // End of the Class //
