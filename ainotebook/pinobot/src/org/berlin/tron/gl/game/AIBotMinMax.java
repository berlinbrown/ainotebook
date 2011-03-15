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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;


/**
 * Default google challenge AI bot.  Search for a valid mode.
 * This bot can also be used for checking valid moves and scoring them.
 * 
 * @author BerlinBrown
 *
 */
public class AIBotMinMax extends GLBot {
    
    private byte type = ITronBoard.PLAYER1;
    private int numberInvalidMoves = 0;    
    private int numberInvalidEnemy = 0;
    
    private Map<Double, Move> lastScoreMap = null; 
    
    public AIBotMinMax(final ITronBoard basicBoard) {
        
        super(basicBoard);                      
        this.setVerbose(false);
    }       
    
    /////////////////////////////////////////////////////////////////
    
    
    /**
     * As opposed to using North in the invalid case,
     * use a completely random move.
     * 
     * @return
     */
    @Override
    public Move dumbRandomMove() {
        
        final BotMath math = new BotMath();
        if (this.lastScoreMap != null) {            
            
            // Check if there are values in the score map
            final double sum = math.sum(this.lastScoreMap);
            if (sum > 1.0) {
                final Move checkMove = dumbLastScoreMove(this.lastScoreMap);
                if (checkMove != null) {
                    return checkMove;
                }
            }
        } // End of if //
        
        return super.dumbRandomMove();
    }
    
    /**
     * Return the top score of the last move on a dumb, failed case.
     * 
     * @param scoreMap
     * @return
     */
    public Move dumbLastScoreMove(final Map<Double, Move> scoreMap) {
        try {
            Map.Entry<Double, Move> lastEntry = null;
            lastEntry = scoreMap.entrySet().iterator().next();
            return lastEntry.getValue();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return null;
        
    }
    
    public boolean makeLogicMoveAIValidateScores(final Map<Double, Move> scoreMap) {
        
        if (scoreMap == null) {
            return false;
        }            
        // Get the first entry        
        // Select the first valid case
        Map.Entry<Double, Move> lastEntry = null; 
        for (final Iterator<Map.Entry<Double, Move>> it = scoreMap.entrySet().iterator(); it.hasNext(); ) {
            lastEntry = it.next();            
            final boolean res = makeLogicMoveAIValidateScore(lastEntry);
            if (res) {
                return true;
            } // End of if //
        } // End of the for //
        
        this.numberInvalidMoves++;        
        return false;
    }
    
    public boolean makeLogicMoveAIValidateScore(final Map.Entry<Double, Move> highestMoveEntry) {
        
        if (highestMoveEntry == null) {            
            return false;
        }
        final Move highestMove = highestMoveEntry.getValue();
        if (highestMoveEntry.getKey().doubleValue() <= 1.0) {
            this.addMessages("-1000-AI: using default move, invalid score");                 
            return false;            
        } // End of the if - else //
        
        // We have our high value move, validate it.
        final Stack<Move> stackMoves = (Stack<Move>) this.getMoves().getMoves();
        if (!this.validateMoveWithEnemyDist(stackMoves, highestMove, 1.2)) {
            this.addMessages("-2000-AI: using default move, invalid move - attempt=" + highestMove);            
            return false;
        }
                
        this.addMessages("+5000-AI: valid move");
        this.makeMove(highestMove);
        return true;
    }
    
    /**
     * With only one move the bot can't do anything.
     * Make this particular move.
     */
    public boolean makeOnlyOneMoveLeft(final List<Move> validMovesList) {        
        if (validMovesList == null) {
            // Ignore
            return true;
        }
        if (validMovesList.size() == 1) {
            this.makeMove(validMovesList.get(0));
            return false;
        }
        
        return true;
    }
        
    
    public boolean makeDistanceEnemy(final List<Move> forValidMovesList) {
                
        final double halfDist      = Math.abs(this.calcBoardDistance()) * 0.18;
        final double minDist       = Math.abs(this.calcEnemyDistance());
        final boolean hasShortDist = (minDist < halfDist);
                
        if (!hasShortDist) {         
            return true;
        }
        if ((forValidMovesList != null) && (forValidMovesList.size() > 0)) { 
            
            if (       (forValidMovesList.size() <= 1) 
                    || (forValidMovesList.size() >= 3)) {
                // Exit //
                return true;
            }
            
            double max = Double.MIN_VALUE;
            Move forEnemyMove  = null;                                                                               
            for (Move move : forValidMovesList) {                
                if ((move.getEnemyDistance() > 0) && (move.getEnemyDistance() > max)) {
                    forEnemyMove = move;
                    max = move.getEnemyDistance();                                        
                }                
            } // End of for //                        
            if (forEnemyMove != null) {                
                this.makeMove(forEnemyMove);
                return false;
            } 
        }
        return true;
    }
        
    /**
     * Based on the number of valid moves, select the first one.
     * 
     * @param validMovesList
     * @return
     */
    @Override
    public Move checkValidMovesFirst(final List<Move> validMovesList) {
                        
        final CalcFill calcFill = new CalcFill(this.getBoard(), this.getLastMove());              
        final double e = calcFill.spaceEast();
        final double w = calcFill.spaceWest();               
        final double diff = Math.abs(w - e);
        if (w > e && (diff < 0.25)) {                        
            for (Move move : validMovesList) {
                if (move.getDirection().startsWith("W")) {
                    return move;
                }
            }            
        } else {            
            for (Move move : validMovesList) {
                if (move.getDirection().startsWith("E")) {
                    return move;
                }
            }
        }
        return validMovesList.get(0);
    }
    
    /**
     * Test the default move, use to fall back on.
     * 
     * @return
     */
    @Override
    public void makeLogicMove() {
                        
        this.setVerbose(false);
        this.getBoard().setVerbose(false);
        this.getBoard().printBoard();
        
        this.printThoughts();
        this.printMessages();
                        
        // If dead, don't make a move //
        if (this.isDead()) {
            return;
        } 
        
        if (this.getVerbose()) {
            System.err.println("Making logic move for aibotscorer - " + this.getMoves().size());
        }
                
        if (this.calcEnemyDistance() < 2.1) {
            this.numberInvalidMoves++;
        }
                        
        final Move lastMove = this.getLastMoveNull();
        if (lastMove == null) {
            this.numberInvalidMoves++;
            this.addMessages("-3000-AI: using default move, last move null");
            // Revert back to the default move
            super.makeLogicMove();
            return;
        }
        
        final List<Move> forValidMovesList = this.checkValidMovesRaw();
        if (!this.makeOnlyOneMoveLeft(forValidMovesList)) {
            this.addMessages("-9999-AI: no more moves left, making default");
            return;
        }
       
        final double halfDist      = Math.abs(this.calcBoardDistance()) * 0.75;
        final double minDist       = Math.abs(this.calcEnemyDistance());
        final boolean hasLongDist = (minDist > halfDist);
        if (hasLongDist) {
            super.makeLogicMove();
            return;
        }
        
        /////////////////////////////////////////
        // Continue with logic moves
        /////////////////////////////////////////
        
        if (!makeDistanceEnemy(forValidMovesList)) {            
            return;
        }
        
        final FunctionalScoreAllMoves functionalScore = 
        new FunctionalScoreAllMoves(100, this.getBoard(), lastMove.getX(), lastMove.getY(), this.getType());        
        functionalScore.setVerbose(this.getVerbose());
        
        final Map<Double, Move> scoreMap = functionalScore.scoreAll();
        this.lastScoreMap = scoreMap;
        if (this.getVerbose()) {
            System.err.println("Logic Move Score " + scoreMap);
            this.addMessages("+6000-AI: last score set = " + scoreMap);
        }
        
        if (this.getVerbose()) {
            System.err.println(" ---> invalid moves: " + this + " -- ((" + this.numberInvalidMoves + ")) m=" + this.getMoves().size() + " **" + this.numberInvalidMoves + "** " + scoreMap);
        }
        
        final boolean validScoreCheck = this.makeLogicMoveAIValidateScores(scoreMap);              
        if (!validScoreCheck) {
            this.numberInvalidMoves++;            
            // If on the valid case, the move has already been made            
            super.makeLogicMove();
            return;
        } // End of if //
               
    } // End of the Method //

    /**
     * @return the type
     */
    public byte getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(byte type) {
        this.type = type;
    }
       
        
} // End of the Class //
