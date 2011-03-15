package org.berlin.pino.test.functional.teeter;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.berlin.pino.win.CommandButtonPanel;
import org.berlin.seesaw.swing.BaseWorker;
import org.berlin.seesaw.swing.ITeeterButton;
import org.berlin.seesaw.swing.ITeeterEventWorker;
import org.berlin.seesaw.swing.ITeeterPanel;
import org.berlin.seesaw.swing.TeeterButton;
import org.berlin.seesaw.swing.layout.DefaultTeeterLayout;
import org.berlin.seesaw.swing.layout.ITeeterLayout;

public class TeeterPanelTest {

    public static ITeeterButton createEnterButton() {
        
        final ITeeterEventWorker eventWorker = new BaseWorker() {
            public void execute() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Tested - Enter!");
            }
        };        
        final ITeeterButton button = new TeeterButton(new JButton("Enter"), eventWorker);
        button.addEventHandler();
        return button;
    }
    
    public static ITeeterButton createClearButton() {
        
        final ITeeterEventWorker eventWorker = new BaseWorker() {
            public void execute() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Tested - Clear!");
            }
        };        
        final ITeeterButton button = new TeeterButton(new JButton("Clear"), eventWorker);
        button.addEventHandler();
        return button;
    }
    
    public static ITeeterButton createExitButton() {
        
        final ITeeterEventWorker eventWorker = new BaseWorker() {
            public void execute() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Tested - Exit!");
            }
        };        
        final ITeeterButton button = new TeeterButton(new JButton("Exit"), eventWorker);
        button.addEventHandler();
        return button;
    }
        
    public static ITeeterPanel buildMyPanel() {
        
        final ITeeterLayout layout = new DefaultTeeterLayout();
        layout.defaultSettings();
        
        final JPanel swingPanel = new JPanel(layout.getLayout());
        final ITeeterPanel panel = new CommandButtonPanel(swingPanel, layout, createEnterButton(), createClearButton(), createExitButton());
        panel.constructView();
        return panel;
    }
    
    public static void main(String [] args) {
        
        ///////////////
        final JFrame frame = new JFrame("Hello World!");               
        frame.add(buildMyPanel().getComponent());
        //frame.setSize(300, 300);
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
