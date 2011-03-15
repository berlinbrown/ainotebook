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
package org.berlin.pino.test.jogl.p;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Gravity implements GLEventListener, MouseListener,
        MouseMotionListener {

    private GLU glu = new GLU();
    private Mass mass = new Mass();

    /**
     * Main
     * 
     * @param args
     */
    public GLCanvas buildCanvas() {
        
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new Gravity());
        canvas.setSize(900, 200);
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
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void renderGridVerticalAlongX(final GL gl) {
        
        final float xmin  = -3.20f;
        final float xmax  =  3.20f;
        final float N     =  30.0f;
        final float delta = (Math.abs(xmin) + Math.abs(xmax)) / N;
        
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        for (float x = xmin; x < xmax; x += delta) {
            
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex3f( x,  3.2f, 0.0f);
            gl.glVertex3f( x, -3.2f, 0.0f);
            gl.glEnd();            
            
        } // End of the For //
        
    }
    
    public void renderGridHorizontalAlongY(final GL gl) {
        
        final float ymin  = -3.20f;
        final float ymax  =  3.20f;
        final float N     =  30.0f;
        final float delta = (Math.abs(ymin) + Math.abs(ymax)) / N;
        
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        for (float y = ymin; y < ymax; y += delta) {
            
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex3f( -3.2f,  y, 0.0f);
            gl.glVertex3f(  3.2f,  y, 0.0f);
            gl.glEnd();            
            
        } // End of the For //
       
    }
    
    public void display(GLAutoDrawable drawable) {

        final GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -6.0f);
                
        renderGridVerticalAlongX(gl);
        renderGridHorizontalAlongY(gl); 
        
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f( 0.0f,  3.2f, 0.0f);
        gl.glVertex3f( 0.0f, -3.2f, 0.0f);
        gl.glEnd();
        
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(-3.2f,  0.0f, 0.0f);
        gl.glVertex3f( 3.2f,  0.0f, 0.0f);
        gl.glEnd();                
                        
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        
        // Render //
        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);        
        gl.glVertex3f(-mass.getGlsize()+mass.getX(),  mass.getGlsize()+mass.getY(), 0.0f);
        gl.glVertex3f( mass.getGlsize()+mass.getX(),  mass.getGlsize()+mass.getY(), 0.0f);
        gl.glVertex3f( mass.getGlsize()+mass.getX(), -mass.getGlsize()+mass.getY(), 0.0f);
        gl.glVertex3f(-mass.getGlsize()+mass.getX(), -mass.getGlsize()+mass.getY(), 0.0f);
        gl.glEnd();
        gl.glPopMatrix();        
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
} // End of the Class //
