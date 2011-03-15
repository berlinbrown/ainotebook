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
 * 
 * Simple Java OpenGL
 */
package org.berlin.tron.gl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import org.berlin.tron.gl.game.GLRenderBoard;

public class GLGridBoard implements GLEventListener, MouseListener, MouseMotionListener {

    public static final int GL_WIDTH  = GLGridApp.SCREEN_WIDTH;
    public static final int GL_HEIGHT = (int) (GLGridApp.SCREEN_HEIGHT * 0.9);
    
    /**
     * Number of spots on the board.
     * !IMPORTANT! - key value for determining the GL board size.
     */
    public static final float DEFAULT_N = 30.0f;
    
    private GLU glu = new GLU();
    private GLRenderBoard board;
    
    private boolean disableGrid = true;
    private boolean disableAxis = true;
    
    /**
     * Main
     * 
     * @param args
     */
    public static GLCanvas buildCanvas() {
        
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new GLGridBoard());
        canvas.setSize(GL_WIDTH, GL_HEIGHT);
        return canvas;
    }

    public void init(GLAutoDrawable drawable) {

        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));
        GL gl = drawable.getGL();

        System.err.println("INIT GL IS: " + gl.getClass().getName());
        System.err.println("Chosen GLCapabilities: " + drawable.getChosenGLCapabilities());

        gl.setSwapInterval(1);
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glShadeModel(GL.GL_FLAT);
        
        this.board = new GLRenderBoard(DEFAULT_N, DEFAULT_N, 4.4f, 4.4f);
        this.board.calcGLSize();
        this.board.makeBoard();
        
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
            int height) {

        GL gl = drawable.getGL();
        System.err.println("GL_VENDOR: " + gl.glGetString(GL.GL_VENDOR));
        System.err.println("GL_RENDERER: " + gl.glGetString(GL.GL_RENDERER));
        System.err.println("GL_VERSION: " + gl.glGetString(GL.GL_VERSION));

        if (height <= 0) {
            height = 1;
        }

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 30.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void renderGridVerticalAlongX(final GL gl, float N, float lineYStart, float lineYEnd, float xmin, float xmax) {
                        
        final float delta = (Math.abs(xmin) + Math.abs(xmax)) / N;
        
        gl.glColor3f(0.8f, 0.7f, 0.7f);
        for (float x = xmin; x < xmax; x += delta) {
            
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex3f( x,  lineYStart, 0.0f);
            gl.glVertex3f( x,  lineYEnd, 0.0f);            
            gl.glEnd();            
            
        } // End of the For //        
    }
    public void renderGridVerticalAlongX_End(final GL gl, float lineYStart, float lineYEnd, float x) {
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f( x,  lineYStart, 0.0f);
        gl.glVertex3f( x,  lineYEnd, 0.0f);            
        gl.glEnd();    
    }
    
    public void renderGridHorizontalAlongY(final GL gl, float N, float lineXStart, float lineXEnd, float ymin, float ymax) {
                        
        final float delta = (Math.abs(ymin) + Math.abs(ymax)) / N;        
        gl.glColor3f(0.5f, 0.5f, 0.5f);
        for (float y = ymin; y < ymax; y += delta) {
            
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex3f(  lineXStart,  y, 0.0f);
            gl.glVertex3f(  lineXEnd,    y, 0.0f);
            gl.glEnd();            
            
        } // End of the For //       
    }
    
    public void renderGridHorizontalAlongY_End(final GL gl, float lineXStart, float lineXEnd, float y) {
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(  lineXStart,  y, 0.0f);
        gl.glVertex3f(  lineXEnd,    y, 0.0f);
        gl.glEnd();   
    }
    
    public void renderXYAxis(final GL gl, final float x1, final float x2, final float y1, final float y2) {
        
        // Render the XY Axis
        final float edgeOver = 0.07f;
        gl.glColor3f(0.9f, 0.6f, 0.6f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f( 0.0f, y1 - edgeOver, 0.0f);
        gl.glVertex3f( 0.0f, y2 + edgeOver, 0.0f);
        gl.glEnd();
        
        gl.glColor3f(0.9f, 0.6f, 0.6f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f( x1 - edgeOver,  0.0f, 0.0f);
        gl.glVertex3f( x2 + edgeOver,  0.0f, 0.0f);
        gl.glEnd();                                        
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        
    }
    
    /////////////////////////////////////////////////////////////////
    
    public void display(GLAutoDrawable drawable) {

        final GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -6.0f);                
        
        /////////////////////////////////////////
        // For Render Along Y
        /////////////////////////////////////////
        final float NforX      =  DEFAULT_N;
        final float lineYStart = -2.2f;
        final float lineYEnd   =  2.2f;
        final float xmin       = -2.2f;
        final float xmax       =  2.2f;
        if (!this.disableGrid) {
            renderGridVerticalAlongX(gl, NforX, lineYStart, lineYEnd, xmin, xmax);
            renderGridVerticalAlongX_End(gl, lineYStart, lineYEnd, xmax);
        }
        
        /////////////////////////////////////////
        // For Render Along Y
        /////////////////////////////////////////
        final float N          =  DEFAULT_N;
        final float lineXStart = -2.2f;
        final float lineXEnd   =  2.2f;
        final float ymin       = -2.2f;
        final float ymax       =  2.2f;
        
        if (!this.disableGrid) {
            renderGridHorizontalAlongY(gl, N, lineXStart, lineXEnd, ymin, ymax);
            renderGridHorizontalAlongY_End(gl, lineXStart, lineXEnd, ymax);
        }
        
        // Render the XY Axis lines
        if (!this.disableAxis) {
            this.renderXYAxis(gl, lineXStart, lineXEnd, lineYStart, lineYEnd);
        }
        
        this.board.renderBoard(gl);        
        
        // Flush the Buffer //
        gl.glFlush();
    }

    /////////////////////////////////////////////////////////////////
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }

    // Methods required for the implementation of MouseListener
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    // Methods required for the implementation of MouseMotionListener
    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    /**
     * @return the board
     */
    public GLRenderBoard getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(GLRenderBoard board) {
        this.board = board;
    }

    /**
     * @return the glu
     */
    public GLU getGlu() {
        return glu;
    }

    /**
     * @param glu the glu to set
     */
    public void setGlu(GLU glu) {
        this.glu = glu;
    }

    /**
     * @return the disableGrid
     */
    public boolean isDisableGrid() {
        return disableGrid;
    }

    /**
     * @param disableGrid the disableGrid to set
     */
    public void setDisableGrid(boolean disableGrid) {
        this.disableGrid = disableGrid;
    }

    /**
     * @return the disableAxis
     */
    public boolean isDisableAxis() {
        return disableAxis;
    }

    /**
     * @param disableAxis the disableAxis to set
     */
    public void setDisableAxis(boolean disableAxis) {
        this.disableAxis = disableAxis;
    }
} // End of the Class //
