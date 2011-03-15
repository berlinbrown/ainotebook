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
 * Simple Java OpenGL - Render a 2D Opengl Tron board.
 */
package org.berlin.tron.gl.game;

import javax.media.opengl.GL;

import org.berlin.tron.gl.Mass;

/**
 * Interact with the GL libraries to render the board.
 * 
 * @author Berlin Brown
 */
public class GLRenderBoard {

    /**
     * Allow the board to be updated.
     */
    private ITronBoard board;
    
    private final float sizeX;
    private final float sizeY;
    
    private final float xwidth;
    private final float yheight;
    
    private float glSize     = 1.0f;
    private float halfGlSize = 0.5f;
    private float padding    = 0.011f;
            
    /**
     * Constructor for GLRenderBoard.
     * @param N_X float
     * @param N_Y float
     * @param xwidth float
     * @param yheight float
     */
    public GLRenderBoard(                      
            final float N_X, final float N_Y, 
            final float xwidth, final float yheight) {
                
        this.sizeX   = N_X;
        this.sizeY   = N_Y;
        this.xwidth  = xwidth;
        this.yheight = yheight;
                
    }
    
    /**
     * Method renderMass.
     * @param gl GL
     * @param mass Mass
     */
    public void renderMass(final GL gl, final Mass mass) {
        
        // Render The Mass //
        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(  mass.getR(), mass.getG(), mass.getB());
        
        final float withSmallerGLSize = mass.getGlsize() - this.padding;
        
        gl.glVertex3f(-withSmallerGLSize + mass.getX(),  withSmallerGLSize + mass.getY(), 0.0f);
        gl.glVertex3f( withSmallerGLSize + mass.getX(),  withSmallerGLSize + mass.getY(), 0.0f);
        gl.glVertex3f( withSmallerGLSize + mass.getX(), -withSmallerGLSize + mass.getY(), 0.0f);
        gl.glVertex3f(-withSmallerGLSize + mass.getX(), -withSmallerGLSize + mass.getY(), 0.0f);
        
        gl.glEnd();
        gl.glPopMatrix();
    }    
    
    /**
     * Method makeBoard.
     */
    public void makeBoard() {
        
        final ITronBoard board = new TronBoard((int) sizeX, (int) sizeY);
        board.clearBoard();
        GLWalls.appWallSet(board);
        this.board = board;
        System.out.println("Tron Board At Create!");
        System.out.println(this.board);
        this.buildGame();
    }
           
    /**
     * Method buildGame.
     */
    public void buildGame() {
        
        final GLGameBuilder builder = new GLGameBuilder(this);
        final GLGame game = builder.build();
        game.setInitBotPos();
        game.launchTask();
    }
    
    /**
     * There are N x N number of squares,
     * each square consists of a glsize.
     */
    public void calcGLSize() {
        
        final float sqrSize = (this.xwidth / this.sizeX);
        this.glSize = sqrSize / 2.0f;
        this.halfGlSize = this.xwidth / 2.0f;                                        
        System.out.println("+ Render Board size=" + this.sizeX +  " glsize=" + this.glSize + " half=" + this.halfGlSize);
    }    
    
    /**
     * Method createGLMass.
     * @param massType byte
     * @param x float
     * @param y float
     * @param glSize float
     * @return Mass
     */
    public Mass createGLMass(final byte massType, float x, float y, float glSize) {
        
        final Mass mass = new Mass();
        mass.setX(x);
        mass.setY(y);
        mass.setGlsize(glSize - this.padding);
        
        // !IMPORTANT! - change bot color here.
        if (massType == ITronBoard.PLAYER1) {
            
            mass.setType(ITronBoard.PLAYER1);
            mass.setColor(0.62f, 0.62f, 0.62f);
            
        } else if (massType == ITronBoard.PLAYER2) {
            
            mass.setType(ITronBoard.PLAYER2);
            mass.setColor(0.71f, 0.41f, 0.75f);
            
        } else if (massType == ITronBoard.WALL) {
            
            mass.setType(ITronBoard.WALL);
            mass.setColor(0.46f, 0.56f, 0.56f);
            
        } // End of the if //
        return mass;
    }
    
    /**
     * Method renderBoard.
     * @param gl GL
     */
    public void renderBoard(final GL gl) {

        final int sizex = this.board.getNumCols();
        final int sizey = this.board.getNumRows();
        
        final byte [] board = this.board.getBoard();
        synchronized(this.board.getBoard()) {
            for (int j = 0; j < sizey; j++) {            
                for (int i = 0; i < sizex; i++) {

                    final byte val = board[(j * sizey) + i];

                    final float entitySize = this.glSize * 2.0f;
                    final float x = (((i * entitySize) - this.halfGlSize) - this.glSize) + entitySize;
                    final float y = ((j * entitySize) - this.halfGlSize) + this.glSize;

                    final Mass mass = this.createGLMass(val, x, y, this.glSize);
                    if (mass.getType() != ITronBoard.EMPTY) {                    
                        this.renderMass(gl, mass);
                    } 
                }  // End of inner for//

            } // End of the For //     
        } // End of sync block
    }

    /**
     * @return the board
     */
    public ITronBoard getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(ITronBoard board) {
        this.board = board;
    }

    /**
     * @return the glSize
     */
    public float getGlSize() {
        return glSize;
    }

    /**
     * @return the padding
     */
    public float getPadding() {
        return padding;
    }
        
} // End of the Class //
