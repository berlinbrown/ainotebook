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
 * Date: 12/15/2009 
 *   
 * Home Page: http://botnode.com/
 * 
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 * 
 * Simple Java OpenGL
 */
package org.berlin.tron.gl.game;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;

import org.berlin.tron.gl.GLGridBoard;

public class GL3DGridBoard extends GLGridBoard {
    
    public static final float axisHeight = 0.1f;

    private float rtri  = 0.0f;
    
    /**
     * Main
     * 
     * @param args
     */
    public static GLCanvas buildCanvas() {
        
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new GL3DGridBoard());
        canvas.setSize(GL_WIDTH, GL_HEIGHT);
        return canvas;
    }
    
    @Override
    public void init(GLAutoDrawable drawable) {

        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));
        GL gl = drawable.getGL();

        System.err.println("INIT GL IS: " + gl.getClass().getName());
        System.err.println("Chosen GLCapabilities: " + drawable.getChosenGLCapabilities());
        
        this.setBoard(new GL3DRenderBoard(DEFAULT_N, DEFAULT_N, 4.4f, 4.4f));
        this.getBoard().calcGLSize();
        this.getBoard().makeBoard();
                        
        gl.setSwapInterval(1);
        
        gl.glShadeModel(GL.GL_SMOOTH);                            // Enables Smooth Color Shading
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);                  // This Will Clear The Background Color 
        gl.glClearDepth(1.0);                                     // Enables Clearing Of The Depth Buffer
        gl.glEnable(GL.GL_DEPTH_TEST);                            // Enables Depth Testing
        gl.glDepthFunc(GL.GL_LEQUAL);                             // The Type Of Depth Test To Do
        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);  // Really Nice Perspective Calculations
        
    }
    
    @Override
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
        this.getGlu().gluPerspective(45.0f, h, 0.1f, 100.0f); // Calculate The Aspect Ratio Of The Window
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    public void renderBottomGrid(final GL gl) {
        
        gl.glBegin(GL.GL_QUADS);  
        
        gl.glColor3f(0.4f, 0.4f, 0.4f);
                
        gl.glVertex3f( 0.03f,  -0.1f,  0.03f);   // Top Right Of The Quad (Bottom)
        gl.glVertex3f(-0.03f,  -0.1f,  0.03f);   // Top Left Of The Quad (Bottom)
        gl.glVertex3f(-0.03f,  -0.1f, -0.03f);   // Bottom Left Of The Quad (Bottom)        
        gl.glVertex3f( 0.03f,  -0.1f, -0.03f);   // Bottom Right Of The Quad (Bottom)
        
    }
    
    /**
     * Example quad:
     * 
     * <pre>
     *   gl.glVertex3f(1.0f,  1.0f, -1.0f);   // Top Right Of The Quad (Right)
     *   gl.glVertex3f(1.0f,  1.0f,  1.0f);    // Top Left Of The Quad (Right)
     *   gl.glVertex3f(1.0f, -1.0f,  1.0f);   // Bottom Left Of The Quad (Right)
     *   gl.glVertex3f(1.0f, -1.0f, -1.0f);  // Bottom Right Of The Quad (Right)
     *  </pre>
     */
    @Override    
    public void renderXYAxis(final GL gl, final float x1, final float x2, final float y1, final float y2) {
        
        // Render the XY Axis
        final float edgeOver = 0.07f;
        gl.glColor3f(0.9f, 0.6f, 0.6f);
        
        // axisHeight
        
        gl.glBegin(GL.GL_QUADS);  
        gl.glVertex3f( 0.0f, axisHeight, y1 - edgeOver);
        gl.glVertex3f( 0.0f, axisHeight, y2 + edgeOver);
        gl.glVertex3f( 0.0f, -0.02f,     y2 + edgeOver);
        gl.glVertex3f( 0.0f, -0.02f,     y1 - edgeOver);
        gl.glEnd();
                
        gl.glColor3f(0.9f, 0.6f, 0.6f);        
        gl.glBegin(GL.GL_QUADS);               
        gl.glVertex3f( x2 + edgeOver,   axisHeight, 0.0f);
        gl.glVertex3f( x1 - edgeOver,   axisHeight, 0.0f);
        gl.glVertex3f( x1 - edgeOver,  -0.02f,      0.0f);
        gl.glVertex3f( x2 + edgeOver,  -0.02f,      0.0f);
        gl.glEnd();
        
        // End with white color
        gl.glColor3f(1.0f, 1.0f, 1.0f);
               
    }
    
    @Override
    public void display(GLAutoDrawable drawable) {

        final GL gl = drawable.getGL();
                
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT); // Clear and depth buffer bit
        gl.glLoadIdentity(); // Reset The View
        
        // Move the entire scene outwards/away in the negative Z direction
        // Move the scene down        
        gl.glTranslatef( 0.3f, -0.45f, -3.0f);  // Scene position
        gl.glRotatef(rtri + 160.0f, 0.0f, 1.0f, 0.0f);
        
        gl.glPushMatrix();
        ///////////////////////////////////////////////////
        // Define the width, height parameters:
        
        /////////////////////////////////////////
        // For Render Along Y
        /////////////////////////////////////////
        final float NforX      =  DEFAULT_N;
        final float lineYStart = -2.2f;
        final float lineYEnd   =  2.2f;
        final float xmin       = -2.2f;
        final float xmax       =  2.2f;
    
        /////////////////////////////////////////
        // For Render Along Y
        /////////////////////////////////////////
        final float N          =  DEFAULT_N;
        final float lineXStart = -2.2f;
        final float lineXEnd   =  2.2f;
        final float ymin       = -2.2f;
        final float ymax       =  2.2f;
        
        // Render the XY Axis lines
        if (!this.isDisableAxis()) {
            this.renderXYAxis(gl, lineXStart, lineXEnd, lineYStart, lineYEnd);
        }
        
        this.getBoard().renderBoard(gl);
                      
        gl.glPopMatrix();
                
        bounds();        
        gl.glFlush();
    }
    
    public void bounds() {
        if (rtri > 360.0f) {
            rtri = 0.0f;
        }
        if (rtri < 0.0f) {
            rtri = 360.0f;
        }       
    }
} // End of the class //
