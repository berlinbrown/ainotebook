package org.berlin.pino.dev.analy;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;

import org.berlin.pino.dev.analy.win.WindowBuilder;
import org.berlin.pino.dev.analy.win.base.AbstractWindowBuilder;
import org.berlin.pino.dev.analy.win.base.BasicWindow;
import org.berlin.pino.dev.analy.win.base.IBasicWindow;


public class AnalysisApp {
    
    public static void main(String [] args) {
        
        final IBasicWindow window = new BasicWindow(); 
        final AbstractWindowBuilder windowBuilder = new WindowBuilder(window);
        windowBuilder.build();
        
        System.out.println(window);
        
        ///////////////
        final JFrame frame = new JFrame("Java Static Analysis - " + new Date());              
        frame.add(window.getComponent());
        frame.setLocation(300, 300);        
        frame.pack();
        frame.setResizable(true);
        
        ///////////////////////////////
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frame.setVisible(true);        
    }
} // End of the Class //
