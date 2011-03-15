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
package org.berlin.tron.gl;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.media.opengl.GLCanvas;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.berlin.tron.gl.game.GL3DGridBoard;

import com.sun.opengl.util.Animator;

/**
 * JFrame for the java opengl window.
 * 
 * @author BerlinBrown
 *
 */
public class GLGridApp extends JFrame {
    
    /**
     * Serial Version Id.
     */
    private static final long serialVersionUID = 443658669585028L;
    
    public static final int TEXT_COLS     = 80;
    public static final int SCREEN_WIDTH  = 800;
    public static final int SCREEN_HEIGHT = (int) (SCREEN_WIDTH * 0.90);
    
    /**
     * Method buildFrame.
     */
    public void buildFrame() {
        
        final JTextArea text = new JTextArea("AppLoaded - " + new Date());
        text.setRows(3);
        text.setColumns(TEXT_COLS);
        
        final BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);       
        this.getContentPane().setLayout(layout);
        this.add(text);
                
        final GLCanvas canvas = GL3DGridBoard.buildCanvas();
        final Animator animator = new Animator(canvas);
        
        this.add(canvas);
        this.setLocation(10, 10);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setResizable(false);
        this.pack();
        
        /////////////////////         
        this.addWindowListener(new WindowAdapter() {
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
        
    }
    
    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String [] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final GLGridApp demo = new GLGridApp();
                demo.buildFrame();
                demo.setVisible(true);
            }
        });
        
    }
   
} // End of the class //
