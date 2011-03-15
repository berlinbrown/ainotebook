package org.berlin.pino.test.functional.teeter;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.berlin.seesaw.swing.BaseWorker;
import org.berlin.seesaw.swing.ITeeterButton;
import org.berlin.seesaw.swing.ITeeterEventWorker;
import org.berlin.seesaw.swing.TeeterButton;

public class TeeterButtonTest {

    public static JComponent buildMyComponent() {
        
        final ITeeterEventWorker eventWorker = new BaseWorker() {
            public void execute() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Tested!");
            }
        };        
        final ITeeterButton button = new TeeterButton(new JButton("Push Me!"), eventWorker);
        button.addEventHandler();
        return button.getComponent();
    }
    
    public static void main(String [] args) {
        
        ///////////////
        final JFrame frame = new JFrame("Hello World!");               
        frame.add(buildMyComponent());
        frame.setSize(300, 300);
        frame.setLocation(400, 400);
        frame.setBackground(Color.white);                
        
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
