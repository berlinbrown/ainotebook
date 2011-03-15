package org.berlin.pino.test.test;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.berlin.pino.win.BasicWindow;
import org.berlin.seesaw.app.ITeeterWindow;
import org.berlin.seesaw.swing.ITeeterButton;
import org.berlin.seesaw.swing.ITeeterPanel;
import org.berlin.seesaw.swing.TeeterButton;
import org.berlin.seesaw.swing.TeeterPanel;

public class BuildFrameTest extends TestCase {

    public void test1() {
        
        final ITeeterButton buttonEnter = new TeeterButton(null, null);
        final ITeeterButton buttonClear = new TeeterButton(null, null);
        final ITeeterButton buttonExit  = new TeeterButton(null, null);        
        final ITeeterPanel panel = new TeeterPanel();
        panel.addButton(buttonEnter);
        panel.addButton(buttonClear);
        panel.addButton(buttonExit);
        
        final ITeeterWindow window = (new BasicWindow.Builder()).withButtons(panel, buttonEnter, buttonClear, buttonExit).build();
    }
    
    public static void main(String [] args) {
        
        TestRunner.run(BuildFrameTest.class);
    }
    
} // End of the Class //
