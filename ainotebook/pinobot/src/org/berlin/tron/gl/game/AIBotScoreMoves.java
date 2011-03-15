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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;


/**
 * Default google challenge AI bot.  Search for a valid mode.
 * This bot can also be used for checking valid moves and scoring them.
 * 
 * @author BerlinBrown
 *
 */
public class AIBotScoreMoves extends GLBot {
    
    public static final double LOW_SCORE = -999999.0;
    
    private IBot north;
    private IBot south;
    private IBot east;
    private IBot west;
    
    private int moveCheckDepth = 10;
       
    private Random random = new Random(System.currentTimeMillis());
        
    public AIBotScoreMoves(final ITronBoard basicBoard, final int moveCheckDepth,
            final IBot north, final IBot south, final IBot east, final IBot west) {
        
        super(basicBoard);
        
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;        
        this.moveCheckDepth = moveCheckDepth;        
        this.setVerbose(false);
    }       
    
    public AIBotScoreMoves(final ITronBoard basicBoard, final int moveCheckDepth) {
        
        this(basicBoard, 
                AIBotBuilder.MAX_SCORE_CHECK_DEPTH,
                new BotSimpleDirection(basicBoard, BotSimpleDirection.DIR_NORTH),                    
                new BotSimpleDirection(basicBoard, BotSimpleDirection.DIR_SOUTH),
                new BotSimpleDirection(basicBoard, BotSimpleDirection.DIR_EAST),
                new BotSimpleDirection(basicBoard, BotSimpleDirection.DIR_WEST));
        
    }
    
    /////////////////////////////////////////////////////////////////
    
    public void initCloneAttrs() {               
       
    }
    
    /**
     * Make the first bot move, initially set to the parent bot move. 
     * 
     * @param bot
     */
    public void initDirBotMove(final IBot bot) {
        if (bot.getMoves().size() == 0) {
            this.cloneBotAttrs(bot);
            bot.makeMove(this.getLastMove());            
        } // End of the if //
    }
    
    /**
     * Reset the bots with all new values.
     */
    public void resetNewDirBots() {
        
        this.north = new BotSimpleDirection(this.getBoard(), BotSimpleDirection.DIR_NORTH);
        this.south = new BotSimpleDirection(this.getBoard(), BotSimpleDirection.DIR_SOUTH);
        this.east  = new BotSimpleDirection(this.getBoard(), BotSimpleDirection.DIR_EAST);
        this.west  = new BotSimpleDirection(this.getBoard(), BotSimpleDirection.DIR_WEST);
        
        // At this point, we can do a clone
        this.initDirBotMove(north);
        this.initDirBotMove(south);
        this.initDirBotMove(east);
        this.initDirBotMove(west);
    }

    public AIBotScoreModel scoreAllDirections() {
        
        final double scoreNorth = scoreDirection(this.north);
        final double scoreSouth = scoreDirection(this.south);
        final double scoreEast  = scoreDirection(this.east);
        final double scoreWest  = scoreDirection(this.west);
        
        if (this.getVerbose()) {
            System.err.println("Score All Directions : n=" + scoreNorth + " s=" + scoreSouth + " e=" + scoreEast + " w=" + scoreWest);                        
        }
        
        final AIBotScoreModel scores = new AIBotScoreModel();
        scores.setScoreNorth(scoreNorth);
        scores.setScoreSouth(scoreSouth);
        scores.setScoreEast(scoreEast);
        scores.setScoreWest(scoreWest);
        return scores;
    }
    
    @Override
    public void makeMove(final Move move) {  
        
        // On init before move
        // check if there are moves in the queue
        // If there are moves in the queue
        // then we can perform our reset direction bots.
        if (this.getMoves().size() >= 4) {
            this.resetNewDirBots();
        }
        
        // It is OK to now make the default move
        // basically add a New Point to the move queue
        super.makeMove(move);        
        initDirBotMove(this.north);
        initDirBotMove(this.south);
        initDirBotMove(this.east);
        initDirBotMove(this.west);        
    }
    
    /**
     * Test the default move, use to fall back on.
     * 
     * @return
     */
    @Override
    public void makeLogicMove() {

        this.printThoughts();
        this.printMessages();
        
        // If dead, don't make a move //
        if (this.isDead()) {
            return;
        } 
        
        if (this.getVerbose()) {
            System.err.println("Making logic move for aibotscorer - " + this.getMoves().size());
        }
        
        final AIBotScoreModel scores = scoreAllDirections();
        if (this.getVerbose()) {
            System.err.println(scores);
        }
        if (this.getMoves().size() <= 3) {
            super.makeLogicMove();
            return;
        }
        
        if (this.getMoves().size() == 26) {
            this.addMessages("-5000-AI: using default move, on 30 clause");
            super.makeLogicMove();
            return;
        }
         
        final int randomMoveFlag = random.nextInt(16);
        if (randomMoveFlag == 1) {
            this.addMessages("-4000-AI: using default move, random case");
            super.makeLogicMove();
            return;
        }
        
        final Move lastMove = this.getLastMoveNull();
        if (lastMove == null) {
            this.addMessages("-3000-AI: using default move, last move null");
            // Revert back to the default move
            super.makeLogicMove();
            return;
        }
                        
        final Move north = lastMove.decy();
        final Move south = lastMove.incy();
        final Move east  = lastMove.incx();
        final Move west  = lastMove.decx();
        
        final LinkedHashMap<Double, Move> map = new LinkedHashMap<Double, Move>();
        map.put(new Double(scores.getScoreNorth()), north);
        map.put(new Double(scores.getScoreSouth()), south);
        map.put(new Double(scores.getScoreEast()), east);
        map.put(new Double(scores.getScoreWest()), west);
        final LinkedHashMap<Double, Move> scoreMap = BotUtilityMap.sortMapByKey(map);
        
        if (this.getVerbose()) {
            System.err.println(scoreMap);
        }
        
        final boolean validScoreCheck = this.makeLogicMoveAIValidateScores(scoreMap);
        if (!validScoreCheck) {
            // If on the valid case, the move has already been made            
            super.makeLogicMove();
            return;
        } // End of if //
    }
    
    public boolean makeLogicMoveAIValidateScores(final LinkedHashMap<Double, Move> scoreMap) {
        
        if (scoreMap == null) {
            return false;
        }
            
        // Get the first entry        
        // Select the first valid case
        for (int i = 0; i < 2; i++) {
            final boolean res = makeLogicMoveAIValidateScore(scoreMap.entrySet().iterator().next());
            if (res) {
                return true;
            }
        }
        return false;
    }
    
    public boolean makeLogicMoveAIValidateScore(final Map.Entry<Double, Move> highestMoveEntry) {
        if (highestMoveEntry == null) {
            return false;
        }
        final Move highestMove = highestMoveEntry.getValue();
        if (highestMoveEntry.getKey().doubleValue() < 0) {
            this.addMessages("-1000-AI: using default move, invalid score");            
            return false;            
        } // End of the if - else //
        
        // We have our high value move, validate it.
        final Stack<Move> stackMoves = (Stack<Move>) this.getMoves().getMoves();
        if (!this.validateMove(stackMoves, highestMove)) {
            this.addMessages("-2000-AI: using default move, invalid move - attempt=" + highestMove);            
            return false;
        }
        this.addMessages("+5000-AI: valid move");
        this.makeMove(highestMove);
        return true;
    }
    
    public double scoreDirection(final IBot botDirection) {
        
        if (botDirection.getMoves().size() == 0) {
            return LOW_SCORE;
        }        
        for (int numMoves = 0; numMoves < this.moveCheckDepth; numMoves++) {
            final boolean notAbleToMove = botDirection.isDead() || botDirection.isUnableToMakeMove();
            if (!notAbleToMove) {
                botDirection.makeLogicMove();
            } 
        } // End of the For //
        return botDirection.getScore();
    }
    
    public void cloneBotAttrs(final IBot newBot) {
        newBot.setDead(this.isDead());
        newBot.setOtherBot(this.getOtherBot());
        newBot.setName(this.getName() + "--" + newBot.getName() + "-clone");       
    }
          
        
} // End of the Class //
