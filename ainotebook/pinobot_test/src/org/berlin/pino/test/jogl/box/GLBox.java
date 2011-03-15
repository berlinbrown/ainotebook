/**
 *
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
 * Date: 12/15/2009 
 *   
 * Home Page: http://botnode.com/
 * 
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */
package org.berlin.pino.test.jogl.box;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class GLBox implements GLEventListener, MouseListener, MouseMotionListener {

    private final GLU glu = new GLU();
    private float rtri  = 0.0f;
    private float rquad = 0.0f;
    

    /**
     * Build the GL Canvas for rendering.
     * 
     * @param args
     */
    public static class Builder {
        
        public GLCanvas buildCanvas() {

            GLCanvas canvas = new GLCanvas();
            canvas.addGLEventListener(new GLBox());
            canvas.setSize(100, 60);
            return canvas;
        }
        
    } // End of the Class //
    
    /**
     * Render the objects to GL.
     */
    public GL renderGLScene(final GLAutoDrawable drawable) {
        
        final float shiftXPos = 1.2f;
        final GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT); // Clear and depth buffer bit
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(-4.0f + shiftXPos, 0.0f, -7.0f);
        gl.glRotatef(rtri, 0.0f, 1.0f, 1.0f); 
        
        gl.glBegin(GL.GL_QUADS); // Draw A Quad        
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);   // Top Right Of The Quad (Top)
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);  // Top Left Of The Quad (Top)
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);   // Bottom Left Of The Quad (Top)
        gl.glVertex3f(1.0f, 1.0f, 1.0f);    // Bottom Right Of The Quad (Top)
        
        gl.glColor3f(1.0f, 0.5f, 0.0f); 
        gl.glVertex3f(1.0f, -1.0f, 1.0f);   // Top Right Of The Quad (Bottom)
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);  // Top Left Of The Quad (Bottom)
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Quad (Bottom)        
        gl.glVertex3f(1.0f, -1.0f, -1.0f);  // Bottom Right Of The Quad (Bottom)
        
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);    // Top Right Of The Quad (Front)
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);   // Top Left Of The Quad (Front)
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);  // Bottom Left Of The Quad (Front)
        gl.glVertex3f(1.0f, -1.0f, 1.0f);   // Bottom Right Of The Quad (Front)
        
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);  // Top Right Of The Quad (Back)
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Top Left Of The Quad (Back)
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);  // Bottom Left Of The Quad (Back)
        gl.glVertex3f(1.0f, 1.0f, -1.0f);   // Bottom Right Of The Quad (Back)
        
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);   // Top Right Of The Quad (Left)
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);  // Top Left Of The Quad (Left)
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Quad (Left)
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);  // Bottom Right Of The Quad (Left)
        gl.glColor3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);   // Top Right Of The Quad (Right)
        gl.glVertex3f(1.0f, 1.0f, 1.0f);    // Top Left Of The Quad (Right)
        gl.glVertex3f(1.0f, -1.0f, 1.0f);   // Bottom Left Of The Quad (Right)
        gl.glVertex3f(1.0f, -1.0f, -1.0f);  // Bottom Right Of The Quad (Right)
        gl.glEnd(); // Done Drawing The Quad


        gl.glLoadIdentity();                  // Reset The Current Modelview Matrix
        gl.glTranslatef(2.0f + shiftXPos, 0.0f, -7.0f); 
        gl.glRotatef(rquad, 1.0f, 1.0f, 1.0f); // Rotate The Quad On The X axis
                                               // ( NEW )
        gl.glBegin(GL.GL_QUADS); // Draw A Quad        
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);   // Top Right Of The Quad (Top)
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);  // Top Left Of The Quad (Top)
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);   // Bottom Left Of The Quad (Top)
        gl.glVertex3f(1.0f, 1.0f, 1.0f);    // Bottom Right Of The Quad (Top)
        
        gl.glColor3f(1.0f, 0.5f, 0.0f); 
        gl.glVertex3f(1.0f, -1.0f, 1.0f);   // Top Right Of The Quad (Bottom)
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);  // Top Left Of The Quad (Bottom)
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Quad (Bottom)        
        gl.glVertex3f(1.0f, -1.0f, -1.0f);  // Bottom Right Of The Quad (Bottom)
        
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);    // Top Right Of The Quad (Front)
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);   // Top Left Of The Quad (Front)
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);  // Bottom Left Of The Quad (Front)
        gl.glVertex3f(1.0f, -1.0f, 1.0f);   // Bottom Right Of The Quad (Front)
        
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);  // Top Right Of The Quad (Back)
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Top Left Of The Quad (Back)
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);  // Bottom Left Of The Quad (Back)
        gl.glVertex3f(1.0f, 1.0f, -1.0f);   // Bottom Right Of The Quad (Back)
        
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);   // Top Right Of The Quad (Left)
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);  // Top Left Of The Quad (Left)
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Quad (Left)
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);  // Bottom Right Of The Quad (Left)
        gl.glColor3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);   // Top Right Of The Quad (Right)
        gl.glVertex3f(1.0f, 1.0f, 1.0f);    // Top Left Of The Quad (Right)
        gl.glVertex3f(1.0f, -1.0f, 1.0f);   // Bottom Left Of The Quad (Right)
        gl.glVertex3f(1.0f, -1.0f, -1.0f);  // Bottom Right Of The Quad (Right)
        gl.glEnd(); // Done Drawing The Quad

        rtri += 1.6f;
        rquad -= 1.65f;               
        this.bounds();
        return gl;

    }
    
    public void bounds() {
        if (rtri > 360.0f) {
            rtri = 0.0f;
        }
        if (rtri < 0.0f) {
            rtri = 360.0f;
        }
        
        if (rquad > 360.0f) {
            rquad = 0.0f;
        }        
        if (rquad < 0.0f) {
            rquad = 360.0f;
        }
    }

    public void display(final GLAutoDrawable drawable) {
                
        final GL gl = this.renderGLScene(drawable);               
        gl.glFlush();
    }
    
    public void displayChanged(final GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        
    }
    

    public void init(final GLAutoDrawable drawable) {
        
        final GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        System.err.println("Chosen GLCapabilities: " + drawable.getChosenGLCapabilities());

        gl.setSwapInterval(1);
        
        gl.glShadeModel(GL.GL_SMOOTH);                            // Enables Smooth Color Shading
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);               // This Will Clear The Background Color 
        gl.glClearDepth(1.0);                                  // Enables Clearing Of The Depth Buffer
        gl.glEnable(GL.GL_DEPTH_TEST);                            // Enables Depth Testing
        gl.glDepthFunc(GL.GL_LEQUAL);                             // The Type Of Depth Test To Do
        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);  // Really Nice Perspective Calculations
    }

    public void reshape(final GLAutoDrawable drawable, int x, int y, int width, int height) {

        final GL gl = drawable.getGL();
        System.err.println("GL_VENDOR: "   + gl.glGetString(GL.GL_VENDOR));
        System.err.println("GL_RENDERER: " + gl.glGetString(GL.GL_RENDERER));
        System.err.println("GL_VERSION: "  + gl.glGetString(GL.GL_VERSION));

        if (height <= 0) {
            height = 1;
        }
        final float h = (float) width / (float) height;
        
        gl.glViewport(0, 0, width, height);  // Reset The Current Viewport And Perspective Transformation
        gl.glMatrixMode(GL.GL_PROJECTION);   // Select The Projection Matrix
        gl.glLoadIdentity();                 // Reset The Projection Matrix
        glu.gluPerspective(45.0f, h, 0.1f, 100.0f); // Calculate The Aspect Ratio Of The Window
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    /////////////////////////////////////////////////////////////////
    //
    // Continue to click events
    //
    /////////////////////////////////////////////////////////////////
    
    public void mouseClicked(MouseEvent e) {               
    }

    public void mouseEntered(MouseEvent e) {        
    }

    public void mouseExited(MouseEvent e) {            
    }

    public void mousePressed(MouseEvent e) {
       
    }

    public void mouseReleased(MouseEvent e) {
        
    }
    
    public void mouseDragged(MouseEvent arg0) {
  
    }

    public void mouseMoved(MouseEvent arg0) {
        
    }

    // End of Events //
    
} // End of the Class //
