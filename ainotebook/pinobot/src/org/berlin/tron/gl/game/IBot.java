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
 * Basic bot.
 * 
 * @author Berlin Brown
 */
public interface IBot {
            
    public static final String VERS = "0.1";
    
    /**
     * @return the moves
     */
    public IBotMoves getMoves();        
        
    /**
     * @param moves the moves to set
     */
    public void setMoves(IBotMoves moves);

    /**
     * Method makeMove.
     * @param move Move
     */
    public void makeMove(final Move move);
    
    /**
     * Method printMoves.
     */
    public void printMoves();
        
    /**
     * Method getBoard.
     * @return ITronBoard
     */
    public ITronBoard getBoard();
    
    /**
     * Method makeLogicMove.
     */
    public void makeLogicMove();
    
    /**
     * Method getLastMove.
     * @return Move
     */
    public Move getLastMove();
    /**
     * Method getLastMoveNull.
     * @return Move
     */
    public Move getLastMoveNull();
    
    /**
     * Method checkValidMoves.
     * @return Move
     */
    public Move checkValidMoves();

    /**
     * Method getOtherBotPos.
     * @return Move
     */
    public Move getOtherBotPos();
    
    /**
     * Method setOtherBot.
     * @param otherBot IBot
     */
    public void setOtherBot(IBot otherBot);
    
    /**
     * Method getOtherBot.
     * @return IBot
     */
    public IBot getOtherBot();
         
    /**
     * Method isUnableToMakeMove.
     * @return boolean
     */
    public boolean isUnableToMakeMove();
    
    /**
     * Method setUnableToMakeMove.
     * @param unableToMakeMove boolean
     */
    public void setUnableToMakeMove(boolean unableToMakeMove);
        
    /**
     * Method isDead.
     * @return boolean
     */
    public boolean isDead();

    /**
     * @param dead the dead to set
     */
    public void setDead(boolean dead);
    
    /**
     * Method getName.
     * @return String
     */
    public String getName();        

    /**
     * @param name the name to set
     */
    public void setName(String name);
    
    /**
     * Method getCauseDeath.
     * @return String
     */
    public String getCauseDeath();
    /**
     * Method setCauseDeath.
     * @param causeDeath String
     */
    public void setCauseDeath(String causeDeath);
    
    /**
     * Method getScore.
     * @return double
     */
    public double getScore();    

    /**
     * @param score the score to set
     */
    public void setScore(double score);
    
    /**
     * Method incScore.
     * @param score double
     */
    public void incScore(double score);
    
    /**
     * Method getPerMoveScore.
     * @return double
     */
    public double getPerMoveScore();

    /**
     * @param moveScore the moveScore to set
     */
    public void setPerMoveScore(double moveScore);
    
    /**
     * Method addThoughts.
     * @param moveThought MoveThought
     */
    public void addThoughts(final MoveThought moveThought);
     
    /**
     * Method addMessages.
     * @param msg String
     */
    public void addMessages(final String msg);
    
    /**
     * Method getMessages.
     * @return List<String>
     */
    public List<String> getMessages();
        
    public double calcEnemyDistance();
    
} // End of the Class //
