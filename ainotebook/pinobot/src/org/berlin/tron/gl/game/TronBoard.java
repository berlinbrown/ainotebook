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

import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Board can only consist of:
 * 
 * <pre>
 * 0    - Empty
 * 255  - Player 1
 * 128  - Player 2
 * 1    - Wall
 * </pre>
 * 
 * @author BerlinBrown
 *
 */
public class TronBoard implements ITronBoard {
    
    private List<Move> points = new Stack<Move>();
    
    private final int sizex;
    private final int sizey;
    private final byte board [];
    
    private boolean verbose = false;
    
    private Random random = new Random(); 
    
    /**
     * Constructor for TronBoard.
     * @param sizex int
     * @param sizey int
     */
    public TronBoard(final int sizex, final int sizey) {
        this.sizex = sizex;
        this.sizey = sizey;
        this.board = new byte [sizex * sizey];
    }
    
    /**
     * Method toString.
     * @return String
     */
    public String toString() {
        return "TronBoard: size=" + this.sizex + " obj=" + super.toString();
    }
    
    /**
     * Method initRandom.
     * @see org.berlin.tron.gl.game.ITronBoard#initRandom()
     */
    public void initRandom() {
        try {
            //this.random = SecureRandom.getInstance("SHA1PRNG");
            this.random = new Random(System.currentTimeMillis());
        } catch(Exception e) {                        
            this.random = new Random();
        }
    }
    
    /**
     * Save point is also used with setboard val, to map a move/point
     * to a board coordinate.
     * 
     * @param type byte
     * @param x int
     * @param y int
     * @see org.berlin.tron.gl.game.ITronBoard#savePoint(byte, int, int)
     */
    public void savePoint(final byte type, final int x, final int y) {
        if (type != EMPTY) {
            final Move point = new Point(x, y);            
            if (!this.points.contains(point)) {
                this.points.add(point);
            }
        } // End of if //
    }
    
    /**
     * Ensure that a bot cannot end up on a wall or other player.
     * He must be dead and not render on top of another object.
     * @param onMoveType byte
     * @param bot IBot
     * @param x int
     * @param y int
     * @return boolean
     */
    public boolean validateBoardMove(final byte onMoveType, final IBot bot, final int x, final int y) {
        
        if (bot == null) {
            return false;
        }
        if ((x < 0) || (y < 0) || (x >= this.getNumCols()) || (y >= this.getNumRows())) {
            bot.setCauseDeath("Hit Bounds at : x=" + x + "y=" + y);
            bot.setDead(true);
            return false;
        }
        final byte existType = this.getBoardVal(x, y);
                
        this.printBoard();        
        if (existType != ITronBoard.EMPTY) {
            bot.setCauseDeath("Ran into something - " + existType + " (" + x + "," + y+ ")");
            bot.setDead(true);
            return false;
        }
        
        return true;
    }
          
    /**
     * Method marshalMoves.
     * @param type byte
     * @param bot IBot
     * @see org.berlin.tron.gl.game.ITronBoard#marshalMoves(byte, IBot)
     */
    public void marshalMoves(final byte type, final IBot bot) {
        
        if (bot == null) {
            return;
        }
        
        final Move lastBotMove = bot.getLastMove();
        Move invalidMove = null;
        final int lastx = lastBotMove.getX();
        final int lasty = lastBotMove.getY();        
        final boolean validMove = validateBoardMove(type, bot, lastx, lasty);        
        if (!validMove) {
            invalidMove = lastBotMove;
        }
        // Continue to setting moves
        final IBotMoves moves = bot.getMoves();        
        bot.printMoves();
        for (Move curmove : moves.getMoves()) {            
            final int x = curmove.getX();
            final int y = curmove.getY();            
            if ((invalidMove != null) && (invalidMove.equals(curmove))) {
                // Don't draw
            } else {                
                this.setBoardVal(type, x, y);
            }
        } // End of the For //
        this.printPoints();
    }
    
    /**
     * Method setRandomObject.
     * @param x int
     * @param y int
     * @see org.berlin.tron.gl.game.ITronBoard#setRandomObject(int, int)
     */
    public void setRandomObject(final int x, final int y) {
        
        synchronized(this.board) {
            final int randVal = this.random.nextInt(12);        
            if (randVal == 1) {
                this.setBoardVal(PLAYER1, x, y);                
            } else if (randVal == 2) {                
                this.setBoardVal(PLAYER2, x, y);
            } else if (randVal == 3) {
                this.setBoardVal(WALL, x, y);
            } else {
                this.setBoardVal(EMPTY, x, y);                
            } // End of the if - else //
        }
    }
    
    /**
     * Method makeRandomBoard.
     * @see org.berlin.tron.gl.game.ITronBoard#makeRandomBoard()
     */
    public void makeRandomBoard() {        
        for (int i = 0; i < sizey; i++) {            
            for (int j = 0; j < sizex; j++) {                
                this.setRandomObject(i, j);
            }
        } // End of the For //
    }
    
    /**
     * Method clearBoard.
     * @see org.berlin.tron.gl.game.ITronBoard#clearBoard()
     */
    public void clearBoard() {
        
        synchronized(this.board) {
            for (int i = 0; i < sizey; i++) {            
                for (int j = 0; j < sizex; j++) {
                    this.setBoardVal(EMPTY, j, i);                    
                }
            } // End of the For //
        }
    }

    /**
     * Method printBoard.
     * @see org.berlin.tron.gl.game.ITronBoard#printBoard()
     */
    public void printBoard() {
        
        if (!this.getVerbose()) {
            return;
        }
        
        for (int j = 0; j < sizey; j++) {            
            for (int i = 0; i < sizex; i++) {
                
                final byte p = this.board[(j * sizey) + i];
                if (p == PLAYER1) {
                    System.err.print("[# x=" + i + " y=" + j + "],");
                } else if (p == PLAYER2) {
                    System.err.print("[+ x=" + i + " y=" + j + "],");                    
                } else if (p == WALL) {   
                    System.err.print("[@ x=" + i + " y=" + j + "],");
                } else {
                    System.err.print("[. x=" + i + " y=" + j + "],");                    
                } // End of the if - else //                
            } // End of the for //
            System.err.println();
        } // End of the For //
    }
    
    /**
     * @return the board
     * @see org.berlin.tron.gl.game.ITronBoard#getBoard()
     */
    public byte [] getBoard() {
        synchronized(this.board) {
            return board;
        }
    }

    /**
     * Method setBoardVal.
     * @param type byte
     * @param x int
     * @param y int
     * @see org.berlin.tron.gl.game.ITronBoard#setBoardVal(byte, int, int)
     */
    public void setBoardVal(final byte type, final int x, final int y) {
        synchronized(this.board) {
            this.savePoint(type, x, y);
            this.board[(y * sizey) + x] = type;
        }
    }
    
    /**
     * Validate if this move for this particular player is valid or not.
     * ignore if you are on your own square. 
     * 
     * @param curType
     * @param x
     * @param y
     * @param curX
     * @param curY
     * @return
     */
    public boolean basicValidateMove(final byte curType, final int x, final int y, final int curX, final int curY) {
        
        // If you are a wall, Walls can be placed anywhere
        if (curType == WALL) {
            return true;
        }            
        
        // If out of bounds, invalid
        if ((x < 0) || (y < 0)
            || (x >= this.getNumCols()) || (y >= this.getNumRows())) {            
            return false;
            
        } // End of the if
        
        // If board is empty at this spot, it is valid
        final byte type = getBoardVal(x, y);
        final boolean playerPos = ((type == PLAYER1) || (type == PLAYER2));
        if (type == EMPTY) {
            return true;
        }        
        if (playerPos && (x == curX) && (y == curY) && (type == curType)) {
            return true;
        } // End of the if //
        
        return false;
    }
    
    public boolean basicValidateBounds(final byte curType, final int x, final int y) {
        // If you are a wall, Walls can be placed anywhere
        if (curType == WALL) {
            return true;
        } 
     
        // If out of bounds, invalid
        if ((x < 0) || (y < 0)
            || (x >= this.getNumCols()) || (y >= this.getNumRows())) {            
            return false;
            
        } // End of the if
        return true;
    }
    
    /**
     * Validate if this move for this particular player is valid or not.
     * ignore if you are on your own square. 
     * 
     * @param curType
     * @param x
     * @param y
     * @param curX
     * @param curY
     * @return
     */
    public boolean basicValidateMove(final byte curType, final int x, final int y) {
        // If you are a wall, Walls can be placed anywhere
        if (curType == WALL) {
            return true;
        }            
        
        // If out of bounds, invalid
        if ((x < 0) || (y < 0)
            || (x >= this.getNumCols()) || (y >= this.getNumRows())) {            
            return false;
            
        } // End of the if
        
        // If board is empty at this spot, it is valid
        final byte type = getBoardVal(x, y);
        if (type == EMPTY) {
            return true;
        }
        final boolean playerPos = ((type == PLAYER1) || (type == PLAYER2));
        if (playerPos && (type != curType)) {
            return false;
        }
        return false;
    }
    
    /**
     * Method getBoardVal.
     * @param x int
     * @param y int
     * @return byte
     * @see org.berlin.tron.gl.game.ITronBoard#getBoardVal(int, int)
     */
    public byte getBoardVal(final int x, final int y) {
        synchronized(this.board) {
            return this.board[(y * sizey) + x];
        }
    }
            
    /**
     * Method getNumRows.
     * @return int
     * @see org.berlin.tron.gl.game.ITronBoard#getNumRows()
     */
    public int getNumRows() {
        return sizey;
    }
    
    /**
     * Method getNumCols.
     * @return int
     * @see org.berlin.tron.gl.game.ITronBoard#getNumCols()
     */
    public int getNumCols() {
        return sizex;
    }

    /**
     * Method printPoints.
     */
    public void printPoints() {
        if (!this.getVerbose()) {
            return;
        }
        System.err.println("<Points on Board> size=" + this.points.size());
        for (Move curmove : this.points) {
            System.err.println(curmove);
        }
    }
    
    public void printScores() {
        
        if (!this.getVerbose()) {
            return;
        }        
        System.err.println("<Scores> size=");
        for (Move curmove : this.points) {
            System.err.format("[%10.4f %s]\n", curmove.getScore(), curmove);
        }        
    }

    /**
     * @return the verbose
     * @see org.berlin.tron.gl.game.GameWidget#getVerbose()
     */
    public boolean getVerbose() {
        return verbose;
    }

    /**
     * @param verbose the verbose to set
     * @see org.berlin.tron.gl.game.GameWidget#setVerbose(boolean)
     */
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
    
} // End of the Class //
