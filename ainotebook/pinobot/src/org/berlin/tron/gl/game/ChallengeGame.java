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

/**
 */
public class ChallengeGame implements IChallengeGame, GameWidget {
    
    private IBotMoves challengeMove      = new BotMoves();
    private IBotMoves movesCurrentPlayer = new BotMoves();
    private IBotMoves movesOtherPlayer   = new BotMoves();
    
    private ITronBoard tronBoard;    
    private IBot player1ai;
    private IBot player2ai;    
    
    private boolean readyForLogic = false;
    private boolean verbose = false;
    
    /**
     * Method init.
     * @param width int
     * @param height int
     * @see org.berlin.tron.gl.game.IChallengeGame#init(int, int)
     */
    public void init(final int width, final int height) {
        
        this.tronBoard = new TronBoard(width, height);
        
        // Player 1 and 2 are the default bots //
        // Also create an AI bot
        player1ai = AIBotBuilder.buildBotMinMax(this.tronBoard);
        player2ai = new GLBot(this.tronBoard);
        
        player1ai.setOtherBot(player2ai);
        player2ai.setOtherBot(player1ai);             
    }
    
    /**
     * Method botReadyForLogic.
     * @param botCheck IBot
     * @return boolean
     */
    public boolean botReadyForLogic(final IBot botCheck) {
        if (botCheck == null) {
            return false;
        }
        
        return ((botCheck.getMoves().size() >= 1)
                    && (this.challengeMove.size() >= 1));
    }
    
    /**
     * Method makeLogicMove.
     * @return String
     * @see org.berlin.tron.gl.game.IChallengeGame#makeLogicMove()
     */
    public String makeLogicMove() {
         
        this.readyForLogic = this.botReadyForLogic(this.player1ai);
                                
        if (!this.readyForLogic) {
            return "North";
        }
        
        try {
            
            final String smartMove = safeSmartBotMakeLogicMove();
            if (smartMove != null) {
                return smartMove;
            }
            
            // Default to the player1 default AI.
            if (this.player1ai != null) {
                this.player1ai.makeLogicMove();
                final Move lastMove = this.player1ai.getLastMove();
                if (lastMove == null) {
                    return "North";
                } else {
                    return lastMove.getDirection();
                } // End of the if - else //                
            } // End of the if - check for player 1 //
            
        } catch(Exception e) {
            
        }
        return "North";
    }
    
    /**
     * Smart bot can return null on invalid input.
     * 
     * @return String
     */
    public String smartBotMakeLogicMove() {
        
        this.readyForLogic = this.botReadyForLogic(this.player1ai);
        if (!this.readyForLogic) {
            return null;
        }
        
        if (this.player1ai == null) {
            return null;
        }
        
        this.player1ai.makeLogicMove();
        final Move lastMove = this.player1ai.getLastMove();
        if (lastMove == null) {
            return null;
        }        
        return lastMove.getDirection();
    }
    
    /**
     * Method safeSmartBotMakeLogicMove.
     * @return String
     */
    public String safeSmartBotMakeLogicMove() {
        try {
            return this.smartBotMakeLogicMove();
        } catch(Exception e) {
            
        }
        return null; 
    }
    
    
    /**
     * Method findWalls.
     * @param x int
     * @param y int
     * @see org.berlin.tron.gl.game.IChallengeGame#findWalls(int, int)
     */
    public void findWalls(final int x, final int y) {
        
        if (this.challengeMove.size() > 0) {
            // Walls already found //
            return;
        }        
        if (tronBoard != null) {
            tronBoard.setBoardVal(ITronBoard.WALL, x, y);
        } // End of the If //        
    }
    
    /**
     * Method checkInit.
     * @param width int
     * @param height int
     * @see org.berlin.tron.gl.game.IChallengeGame#checkInit(int, int)
     */
    public void checkInit(final int width, final int height) {
        if (this.challengeMove.size() == 0) {
            this.init(width, height);
        } // End of the if /
    }
    
    /**
     * Method checkInitPlayerPos.
     * @param initMove Move
     * @param otherPlayerMove Move
     * @see org.berlin.tron.gl.game.IChallengeGame#checkInitPlayerPos(Move, Move)
     */
    public void checkInitPlayerPos(final Move initMove, final Move otherPlayerMove) {
        if (this.player1ai.getMoves().size() == 0) {
            this.player1ai.makeMove(initMove);                   
        } // End of the if //
        
        if (this.player2ai.getMoves().size() == 0) {
            this.player2ai.makeMove(otherPlayerMove);
        }
    }
    
    /**
     * Method addChallengeMove.
     * @param challenge Move
     * @see org.berlin.tron.gl.game.IChallengeGame#addChallengeMove(Move)
     */
    public void addChallengeMove(final Move challenge) {
        this.challengeMove.makeMove(challenge);
    }       
    
    /**
     * Add a move for the current player.
     * @param currentPlayer Move
     * @see org.berlin.tron.gl.game.IChallengeGame#addCurrentMove(Move)
     */
    public void addCurrentMove(final Move currentPlayer) {
        this.tronBoard.setBoardVal(ITronBoard.PLAYER1, currentPlayer.getX(), currentPlayer.getY());
        this.movesCurrentPlayer.makeMove(currentPlayer);
    }
    
    /**
     * Add a move for the other player
     * @param otherPlayer Move
     * @see org.berlin.tron.gl.game.IChallengeGame#addOtherMove(Move)
     */
    public void addOtherMove(final Move otherPlayer) {
        synchronized(this.player2ai) {
            this.tronBoard.setBoardVal(ITronBoard.PLAYER2, otherPlayer.getX(), otherPlayer.getY());
            this.movesOtherPlayer.makeMove(otherPlayer);
            this.player2ai.makeMove(otherPlayer);
        } // End of Sync Block
    }
    
    /**
     * @return the movesCurrentPlayer
     * @see org.berlin.tron.gl.game.IChallengeGame#getMovesCurrentPlayer()
     */
    public IBotMoves getMovesCurrentPlayer() {
        return movesCurrentPlayer;
    }
    /**
     * @param movesCurrentPlayer the movesCurrentPlayer to set
     * @see org.berlin.tron.gl.game.IChallengeGame#setMovesCurrentPlayer(IBotMoves)
     */
    public void setMovesCurrentPlayer(IBotMoves movesCurrentPlayer) {
        this.movesCurrentPlayer = movesCurrentPlayer;
    }
    /**
     * @return the movesOtherPlayer
     * @see org.berlin.tron.gl.game.IChallengeGame#getMovesOtherPlayer()
     */
    public IBotMoves getMovesOtherPlayer() {
        return movesOtherPlayer;
    }
    /**
     * @param movesOtherPlayer the movesOtherPlayer to set
     * @see org.berlin.tron.gl.game.IChallengeGame#setMovesOtherPlayer(IBotMoves)
     */
    public void setMovesOtherPlayer(IBotMoves movesOtherPlayer) {
        this.movesOtherPlayer = movesOtherPlayer;
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
