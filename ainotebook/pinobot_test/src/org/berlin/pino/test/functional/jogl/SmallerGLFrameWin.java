/**
 * Copyright (c) 2006-2007 Berlin Brown and botnode.com/Berlin Research  All Rights Reserved
 *
 * http://www.opensource.org/licenses/bsd-license.php

 * All rights reserved.

 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:

 * * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * * Neither the name of the Botnode.com (Berlin Brown) nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written permission.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Date: 2/15/2009 
 *   
 * Home Page: http://botnode.com/
 * 
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */
package org.berlin.pino.test.functional.jogl;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCanvas;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.berlin.pino.test.jogl.box.GLBox;

import com.sun.opengl.util.Animator;

/**
 * 
 * @author BerlinBrown
 *
 */
public class SmallerGLFrameWin extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 443658669585028L;

    public void buildFrame() {
        
        final JPanel panel = new JPanel();
        final JTextField text  = new JTextField("http://www.botnode.com");
        final JTextField text2 = new JTextField("http://www.google.com");
                
        final BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);                
        // this.getContentPane().setLayout(layout);                      
        panel.setLayout(layout);

        final GLCanvas canvas = (new GLBox.Builder()).buildCanvas();
        final Animator animator = new Animator(canvas);
        
        // Add components //        
        panel.add(text);                               
        panel.add(canvas);
        panel.add(text2);
        panel.setPreferredSize(new Dimension(600, 400));
        
        // Add the panel to the main frame
        this.add(panel);
        this.setLocation(400, 200);        
        this.setResizable(false);                
        
        /////////////////////         
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });        
        animator.start();
        this.pack();
    }
    
    public static void main(String [] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final SmallerGLFrameWin demo = new SmallerGLFrameWin();
                demo.buildFrame();                
                demo.setVisible(true);
                
            }
        });
        
    }
   
} // End of the class //
