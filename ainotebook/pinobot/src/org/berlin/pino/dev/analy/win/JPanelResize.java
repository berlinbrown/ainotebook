package org.berlin.pino.dev.analy.win;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

public class JPanelResize extends JPanel implements ComponentListener {
    
    private Dimension size;

    public JPanelResize(final LayoutManager layout) {
        super(layout);
        this.addComponentListener(this);        
    }
    
    public void paintComponent(Graphics g) {
        System.out.println("paintComponent");
    }

    public void componentResized(ComponentEvent e) {
        // Perform calculation here
        System.out.println("componentResized");
    }

    public void componentHidden(ComponentEvent e) { }

    public void componentMoved(ComponentEvent e) { }

    public void componentShown(ComponentEvent e) { }

    
} // End of the Class //
