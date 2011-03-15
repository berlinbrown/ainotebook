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


/**
 * Default google challenge AI bot.  Search for a valid mode.
 * This bot can also be used for checking valid moves and scoring them.
 * 
 * @author BerlinBrown
 *
 */
public class BotSimpleDirection extends GLBot {
    
    private final int dirCode;
    
    public static final int DIR_NORTH = 0;
    public static final int DIR_SOUTH = 1;
    public static final int DIR_EAST  = 2;
    public static final int DIR_WEST  = 3;
        
    public BotSimpleDirection(final ITronBoard basicBoard, final int dirCode) {        
        super(basicBoard);
        this.dirCode = dirCode;        
        this.setVerbose(false);
    }
    
    /**
     * Test the default move, use to fall back on.
     * 
     * @return
     */
    public Move botSimpleMakeLogicMove() {
        
        // If dead, don't make a move //
        if (this.isDead()) {
            return null;
        }        
        final List<Move> listMoves = this.checkValidMovesRaw();
        if (listMoves == null) {
            this.incScore(IMove.NEG_LOOK_ERR);
            return null;
        } // End of the if //
        
        if (this.getVerbose()) {
            System.err.println("Simple Direction Bot: move-size=" + listMoves.size() + " " + listMoves);
        }
        
        // Check if the move is the direction
        // we are interested in.
        for (Move move : listMoves) {
            
            // In this case, the text north/south are reversed.
            switch(dirCode) {
            case DIR_NORTH:
                return checkMoveDirection(IMove.SOUTH, move);
                
            case DIR_SOUTH:
                return checkMoveDirection(IMove.NORTH, move);                

            case DIR_EAST:
                return checkMoveDirection(IMove.EAST, move);                
                
            case DIR_WEST:
                return checkMoveDirection(IMove.WEST, move);                
            }
            
        } // End of the For //
        
        return null;
    }
    
    @Override
    public void makeLogicMove() {
                              
        this.printMessages();
        this.printThoughts();
        
        if (this.getVerbose()) {
            System.err.println(">>> Simple Direction Bot: (" + this.dirCode + ")");
            System.err.println("Simple Direction Bot: Number of Moves Made - " + this.getMoves().size() + " score=" + this.getScore());
        }
        final Move newMove = this.botSimpleMakeLogicMove();
        if (this.getVerbose()) {
            System.err.println("Simple Direction Bot: " + newMove);
        }
        if (newMove != null) {            
            this.setPerMoveScore(IMove.POS_FIND);
            this.incScore(IMove.POS_FIND);
            this.makeMove(newMove);
        } else {            
            this.setPerMoveScore(IMove.NEG_LOOK_NO_FIND_ERR);
            this.incScore(IMove.NEG_LOOK_NO_FIND_ERR);            
            this.setUnableToMakeMove(true);
        } // End of the if //                    
    }
    
    public Move checkMoveDirection(final String checkFor, final Move moveCheck) {
        
        if (checkFor.equalsIgnoreCase(moveCheck.getDirection())) {            
            this.setPerMoveScore(IMove.POS_FIND);
            this.incScore(IMove.POS_FIND);
            return moveCheck;
        } else {
            this.setPerMoveScore(IMove.NEG_LOOK_NO_FIND_ERR);
            this.incScore(IMove.NEG_LOOK_NO_FIND_ERR);
            return null;
        } // End of the if - else //
        
    }
           
} // End of the Class //
