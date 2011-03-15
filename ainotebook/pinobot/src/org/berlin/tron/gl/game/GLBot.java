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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Default google challenge AI bot.  Search for a valid mode.
 * This bot can also be used for checking valid moves and scoring them.
 * 
 * @author BerlinBrown
 *
 */
public class GLBot implements IBot {

    public static final Random sysRand = new Random(System.currentTimeMillis());
        
    private final ITronBoard board;
    private IBotMoves moves = new BotMoves();    
    private Random random = new Random(System.currentTimeMillis()); 
    
    private IBot otherBot;
    
    private boolean unableToMakeMove = false;
    private boolean dead = false;

    private String name = "(bot:" + sysRand.nextInt() + ")";   
    private String causeDeath = "";
    
    private List<String> messages = new ArrayList<String>();
    private List<MoveThought> thoughts = new ArrayList<MoveThought>();
    
    private double score = 0.0;
    /**
     * See perMove score methods.
     */
    private double moveScore = 0.0;
    private double moveScoreChecksForAvg = 0.0;
    
    /**
     * Enable or disable debugging messages.
     */
    private boolean verbose = false;
    
    /**
     * Main constructor for Bot.
     * 
     * @param basicBoard
     */
    public GLBot(final ITronBoard basicBoard) {
        this.board = basicBoard;
    }
    
    /**
     * Method toString.
     * @return String
     */
    public String toString() {
        return "#{Bot-" + this.getName() + " score=" + this.getScore() + " dead?=" + this.isDead() + " move?=" + (!this.isUnableToMakeMove()) + " cause-death=" + this.getCauseDeath() + "}";
    }
    
    /**
     * @return the moves
     * @see org.berlin.tron.gl.game.IBot#getMoves()
     */
    public IBotMoves getMoves() {        
        return moves;
    }
    
    /**
     * @param moves the moves to set
     * @see org.berlin.tron.gl.game.IBot#setMoves(IBotMoves)
     */
    public void setMoves(IBotMoves moves) {
        this.moves = moves;
    }    

    /**
     * Method makeMove.
     * @param move Move
     * @see org.berlin.tron.gl.game.IBot#makeMove(Move)
     */
    public void makeMove(final Move move) {
        this.moves.makeMove(move);
    }    
    
    /**
     * Method printMoves.
     * @see org.berlin.tron.gl.game.IBot#printMoves()
     */
    public void printMoves() {
        this.moves.printMoves();
    }

    /**
     * @return the board
     * @see org.berlin.tron.gl.game.IBot#getBoard()
     */
    public ITronBoard getBoard() {
        return board;
    }
    
    /**
     * Method addThoughts.
     * @param moveThought MoveThought
     * @see org.berlin.tron.gl.game.IBot#addThoughts(MoveThought)
     */
    public void addThoughts(final MoveThought moveThought) {
        this.thoughts.add(moveThought);
    }
    /**
     * Method addMessages.
     * @param msg String
     * @see org.berlin.tron.gl.game.IBot#addMessages(String)
     */
    public void addMessages(final String msg) {
        this.messages.add(msg);
    }
    
    /**
     * Method validateOtherMove.
     * @param theOtherBot IBot
     * @param move Move
     * @return boolean
     */
    public boolean validateOtherMove(final IBot theOtherBot, final Move move) {
                        
        // For an invalid other bot, that is OK, return true.
        if (theOtherBot == null) {
            return true;
        }
        
        final List<Move> enemyStack = theOtherBot.getMoves().getMoves();
        if (enemyStack.contains(move)) {
                                 
            this.moveScoreChecksForAvg += 1.0;
            this.score += IMove.NEG_OTHER_PLAYER;
            this.addMessages("!!! WARNING: Other Player at this location");
            return false;
        } 
        return true;
        
    }
    
    /**
     * Method validateMove.
     * @param stack Stack<Move>
     * @param move Move
     * @return boolean
     */
    public boolean validateMove(final Stack<Move> stack, final Move move) {
        
        if (stack.size() == 0) {
            return false;
        }        
        final MoveThought thought = new MoveThought(move.getX(), move.getY(), move);
        this.thoughts.add(thought);
                       
        if (move.getX() < 0) {
            this.moveScoreChecksForAvg += 1.0;
            this.score += IMove.NEG_THOUGHT_MOVE_SCORE;
            thought.setThoughtOnMove("- BadMove, less than board size X - " + this.score);
            return false;
        }        
        if (move.getY() < 0) {
            this.moveScoreChecksForAvg += 1.0;
            this.score += IMove.NEG_THOUGHT_MOVE_SCORE;
            thought.setThoughtOnMove("- BadMove, less than board size Y - " + this.score);
            return false;
        }
        if (move.getX() >= this.board.getNumCols()) {
            this.moveScoreChecksForAvg += 1.0;
            this.score += IMove.NEG_THOUGHT_MOVE_SCORE;
            thought.setThoughtOnMove("- BadMove, greater than board size X - " + this.score);
            return false;
        }        
        if (move.getY() >= this.board.getNumRows()) {
            this.moveScoreChecksForAvg += 1.0;
            this.score += IMove.NEG_THOUGHT_MOVE_SCORE;
            thought.setThoughtOnMove("- BadMove, greater than board size Y - " + this.score);
            return false;
        }
        
        if (!validateOtherMove(this.getOtherBot(), move)) {
            thought.setThoughtOnMove("8699 - BadMove, enemy player has moved there");
            return false;
        }
        
        if (stack.contains(move)) {
            this.moveScoreChecksForAvg += 1.0;
            this.score += IMove.NEG_THOUGHT_MOVE_PREV_SCORE;
            thought.setThoughtOnMove("- BadMove, I already moved there - " + this.score);
            return false;
        }
        
        // Set the move distance from the enemy
        move.setEnemyDistance(this.calcEnemyDistanceFromMove(move));
        
        return this.checkRawMap(board.getBoard(), move, thought);               
    }
    
    /**
     * Additional check with enemy distance.
     * 
     * @param stack
     * @param move
     * @param minDist
     * @return
     */
    public boolean validateMoveWithEnemyDist(final Stack<Move> stack, final Move move, final double minDist) {

        final boolean prevValidateMove = validateMove(stack, move);
        
        // Ignore if no other bot //
        if (this.getOtherBot() == null) {
            return prevValidateMove;
        }        
        // If distance too small then invalidate.
        final CalcEnemyDistance calc = new CalcEnemyDistance(this.getOtherBot().getMoves().getMoves(), this.getLastMove()); 
        if (prevValidateMove && (calc.calcMin() < minDist)) {
            return false;
        }
        return prevValidateMove;
    }
    
    /**
     * Return min distance.
     */
    public double calcEnemyDistance() {
        try {
            final CalcEnemyDistance calc = new CalcEnemyDistance(this.getOtherBot().getMoves().getMoves(), this.getLastMove());
            return calc.calcMin();
        } catch(Exception e) {
            
        }
        return -1;
    }
    
    /**
     * Find the distance between two points on the map.
     * 
     * @return
     */
    public double calcBoardDistance() {
        
        final int w = this.getBoard().getNumCols();
        final int h = this.getBoard().getNumRows();
        return Math.sqrt((w * w) + (h * h));
    }
    
    /**
     * Return max distance.
     */
    public double calcEnemyDistanceFromMove(final Move move) {
        try {                                   
            final CalcEnemyDistance calc = new CalcEnemyDistance(this.getOtherBot().getMoves().getMoves(), move);
            return calc.calcMin();
        } catch(Exception e) {
            
        }
        return -1;
    }
    
    /**
     * Method checkRawMap.
     * @param board byte[]
     * @param newMove Move
     * @param moveThought MoveThought
     * @return boolean
     * 
     */
    public boolean checkRawMap(final byte [] board, final Move newMove, final MoveThought moveThought) {
        
        if (newMove == null) {
            return false;
        }
        
        final int x = newMove.getX();
        final int y = newMove.getY();
        final byte type = this.getBoard().getBoardVal(x, y);
        
        if (type == ITronBoard.WALL) {
            this.moveScoreChecksForAvg += 1.0;
            this.score += IMove.NEG_THOUGHT_MOVE_WALL_SCORE;
            moveThought.setThoughtOnMove("- BadMove, might hit a wall - " + this.score);
            return false;
        } 
        if ((type == ITronBoard.PLAYER1) || (type == ITronBoard.PLAYER2)) {
            this.moveScoreChecksForAvg += 1.0;
            this.score += IMove.NEG_THOUGHT_MOVE_PLAYER;
            moveThought.setThoughtOnMove("- 7001 - BadMove, might hit myself or another player - " + this.score);
            return false;
        }
        return true;
    }
        
    
    /**
     * Method getLastMove.
     * @return Move
     * @see org.berlin.tron.gl.game.IBot#getLastMove()
     */
    public Move getLastMove() {
        
        final Stack<Move> stack = (Stack<Move>) this.moves.getMoves();        
        final Move lastMove = (stack.size() == 0) ? null : stack.peek();
        if (lastMove == null) {
            // Ideally the last move shouldn't be null.
            return new Move(-1, -1);
        }
        return lastMove;
    }
    
    /**
     * Get Last Move, but allow for null.
     * 
     * @return Move
     * @see org.berlin.tron.gl.game.IBot#getLastMoveNull()
     */
    public Move getLastMoveNull() {
        final Stack<Move> stack = (Stack<Move>) this.moves.getMoves();        
        final Move lastMove = (stack.size() == 0) ? null : stack.peek();
        if (lastMove == null) {
            // Ideally the last move shouldn't be null.
            return null;
        } // End of the if  //
        return lastMove;
    }
    
    /**
     * As opposed to using North in the invalid case,
     * use a completely random move.
     * 
     * @return
     */
    public Move dumbRandomMove() {
                        
        final Stack<Move> stack = (Stack<Move>) this.moves.getMoves();        
        final Move lastMove = (stack.size() == 0) ? null : stack.peek();
        if (lastMove == null) {
            // Ideally the last move shouldn't be null.
            return new Move(0, 0);
        } // End of the if  //
        
        final List<Move> validMovesList = new ArrayList<Move>();
        validMovesList.add(lastMove.decy());
        validMovesList.add(lastMove.incy());
        validMovesList.add(lastMove.incx());      
        validMovesList.add(lastMove.decx());
        final int sel = random.nextInt(validMovesList.size());
        return validMovesList.get(sel);
    }
    
    /**
     * Check for empty squares.
     * 
     * @return List<Move>
     */
    public List<Move> checkValidMovesRaw() {
        
        final Stack<Move> stack = (Stack<Move>) this.moves.getMoves();        
        final Move lastMove = (stack.size() == 0) ? null : stack.peek();
        if (lastMove == null) {
            // Ideally the last move shouldn't be null.
            return null;
        } // End of the if  //
        
        final List<Move> validMovesList = new ArrayList<Move>();
        final Move north = lastMove.decy();
        final Move south = lastMove.incy();
        final Move east  = lastMove.incx();      
        final Move west  = lastMove.decx();        
        final boolean nb = validateMove(stack, north);
        final boolean sb = validateMove(stack, south);
        final boolean eb = validateMove(stack, east);
        final boolean wb = validateMove(stack, west);
        
        int validMovesLeft = 0;
        if (nb) {
            this.score += IMove.POS_VALID_MOVE;
            this.moveScoreChecksForAvg += 1.0;
            validMovesList.add(north);
            validMovesLeft++;
        }
        if (sb) {
            this.score += IMove.POS_VALID_MOVE;
            this.moveScoreChecksForAvg += 1.0;
            validMovesList.add(south);
            validMovesLeft++;
        }
        if (eb) {
            this.score += IMove.POS_VALID_MOVE;
            this.moveScoreChecksForAvg += 1.0;
            validMovesList.add(east);
            validMovesLeft++;
        }
        if (wb) {
            this.score += IMove.POS_VALID_MOVE;            
            this.moveScoreChecksForAvg += 1.0;
            validMovesList.add(west);
            validMovesLeft++;
        }
                
        // The move score is the score over the average number of checks. 
        this.moveScoreChecksForAvg = (this.moveScoreChecksForAvg <= 0) ? 1.0 : this.moveScoreChecksForAvg;
        this.moveScore = this.score / this.moveScoreChecksForAvg;        
        this.messages.add("Message: (movesleft=" + validMovesLeft + ") Direction Check - " 
                + nb + " " + sb + " " + eb + " " + wb + " // " + this.score + " //chks=" + this.moveScore);        
        return validMovesList;
    }
    
    /**
     * CHeck the valid moves.
     * @return Move
     * @see org.berlin.tron.gl.game.IBot#checkValidMoves()
     */
    public Move checkValidMoves() {
        
        final List<Move> validMovesList = this.checkValidMovesRaw();
        Move rawMove = new Move(0, 0);
        
        if (validMovesList == null) {
            this.messages.add("+ Message: valid move = " + rawMove);
            return rawMove;
        }
        // Add to the queue //
        if (validMovesList.size() == 0) {
            
            this.score += IMove.NEG_NO_MOVES;
            this.moveScoreChecksForAvg += 1.0;
            // Invalid state 
            this.messages.add("+ Message: ERR [return my move] size is zero, looking for a north move");
            // Move north
            // !IMPORTANT! - may throw nullpointer, not check last move
            rawMove = this.dumbRandomMove();
            
        } else if (validMovesList.size() == 1) {
            
            this.score += IMove.NEG_THOUGHT_ONLY_ONE_MOVE;
            this.moveScoreChecksForAvg += 1.0;
            this.messages.add("+ Message: [return my move] size is one, first - " + this.moveScore);
            rawMove = validMovesList.get(0);
            
        } else {
            
            this.messages.add("+ 8833 Message: [return my move] " + this.moveScore);            
            rawMove = checkValidMovesFirst(validMovesList);
            
        } // End of if - else //     
                
        this.messages.add("+ Message: valid move = " + rawMove);
        return rawMove;
    }
    
    public Move checkValidMovesRandom(final List<Move> validMovesList) {
        final int sel = random.nextInt(validMovesList.size());
        final Move rawMove = validMovesList.get(sel);
        return rawMove;
    }    
    
    /**
     * Based on the number of valid moves, select the first one.
     * 
     * @param validMovesList
     * @return
     */
    public Move checkValidMovesFirst(final List<Move> validMovesList) {
        return validMovesList.get(0);
    }
    
    /**
     * Method printMessages.
     */
    public void printMessages() {   
       
        if (!this.getVerbose()) {
            return;
        }
        
        for (String msg : this.messages) {           
            System.err.println(msg);            
        }
    }
    
    /**
     * Method printThoughts.
     */
    public void printThoughts() {
        if (!this.getVerbose()) {
            return;
        }
        for (MoveThought thought : this.thoughts) {
            System.err.println(thought);
        }
    }
    
    /**
     * Method printAIMap.
     */
    public void printAIMap() {
        this.board.printBoard();
    }
    
    /**
     * Make a logic move basd on the alternatives.
     * @see org.berlin.tron.gl.game.IBot#makeLogicMove()
     */
    public void makeLogicMove() {
     
        this.printAIMap();
        this.printThoughts();
        this.printMessages();
        
        // If dead, don't make a move //
        if (this.isDead()) {
            return;
        }        
        final Move newMove = this.checkValidMoves();
        if (newMove != null) {
            this.makeMove(newMove);
        } else {                       
            this.unableToMakeMove = true;            
            //  Just move north //
            this.makeMove(this.dumbRandomMove());
        } // End of the if //        
    }

    /**
     * Method getOtherBotPos.
     * @return Move
     * @see org.berlin.tron.gl.game.IBot#getOtherBotPos()
     */
    public Move getOtherBotPos() {
        
        if (this.getOtherBot() != null) {
            final IBotMoves otherMovesSet = this.getOtherBot().getMoves();
            final Stack<Move> stackMoves = (Stack<Move>) otherMovesSet.getMoves();
            return stackMoves.peek();
        }
        
        return null;
    }

    /**
     * @return the otherBot
     * @see org.berlin.tron.gl.game.IBot#getOtherBot()
     */
    public IBot getOtherBot() {
        return otherBot;
    }

    /**
     * @param otherBot the otherBot to set
     * @see org.berlin.tron.gl.game.IBot#setOtherBot(IBot)
     */
    public void setOtherBot(final IBot otherBot) {
        this.otherBot = otherBot;
    }

    /**
     * @return the unableToMakeMove
     * @see org.berlin.tron.gl.game.IBot#isUnableToMakeMove()
     */
    public boolean isUnableToMakeMove() {
        return unableToMakeMove;
    }

    /**
     * @return the dead
     * @see org.berlin.tron.gl.game.IBot#isDead()
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * @param dead the dead to set
     * @see org.berlin.tron.gl.game.IBot#setDead(boolean)
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * @return the name
     * @see org.berlin.tron.gl.game.IBot#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     * @see org.berlin.tron.gl.game.IBot#setName(String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the causeDeath
     * @see org.berlin.tron.gl.game.IBot#getCauseDeath()
     */
    public String getCauseDeath() {
        return causeDeath;
    }

    /**
     * @param causeDeath the causeDeath to set
     * @see org.berlin.tron.gl.game.IBot#setCauseDeath(String)
     */
    public void setCauseDeath(String causeDeath) {
        this.causeDeath = causeDeath;
    }

    /**
     * @return the verbose
     */
    public boolean getVerbose() {
        return verbose;
    }

    /**
     * @param verbose the verbose to set
     */
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    /**
     * @return the score
     * @see org.berlin.tron.gl.game.IBot#getScore()
     */
    public double getScore() {
        return score;
    }

    /**
     * @param score the score to set
     * @see org.berlin.tron.gl.game.IBot#setScore(double)
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Method incScore.
     * @param score double
     * @see org.berlin.tron.gl.game.IBot#incScore(double)
     */
    public void incScore(double score) {
        this.score += score;
    }

    
    /**
     * @return the moveScore
     * @see org.berlin.tron.gl.game.IBot#getPerMoveScore()
     */
    public double getPerMoveScore() {
        return moveScore;
    }

    /**
     * @param moveScore the moveScore to set
     * @see org.berlin.tron.gl.game.IBot#setPerMoveScore(double)
     */
    public void setPerMoveScore(double moveScore) {
        this.moveScore = moveScore;
    }

    /**
     * @param unableToMakeMove the unableToMakeMove to set
     * @see org.berlin.tron.gl.game.IBot#setUnableToMakeMove(boolean)
     */
    public void setUnableToMakeMove(boolean unableToMakeMove) {
        this.unableToMakeMove = unableToMakeMove;
    }

    /**
     * @return the messages
     * @see org.berlin.tron.gl.game.IBot#getMessages()
     */
    public List<String> getMessages() {
        return messages;
    }
    
} // End of the Class //
