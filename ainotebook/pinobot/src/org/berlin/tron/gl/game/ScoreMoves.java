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

/**
 * Based on a given position and board,
 * score this move. 
 * 
 * Calculate two scores, one for empty squares
 * and the other for enemy positions.
 * 
 * @author BerlinBrown
 *
 */
public class ScoreMoves {

    private final ITronBoard board;
    private final int x; 
    private final int y;
    private final byte type;
    
    public ScoreMoves(final ITronBoard board, final int x, final int y, final byte type) {       
        this.board = board;
        this.x = x;
        this.y = y;        
        this.type = type;
    }
    
    /**
     * Score a total based on the current position and empty spots.
     * 
     * @return
     */
    public double scoreEmptyMovesFull() {
        
        double score = 0;
        final DirectionModel adjacent = new DirectionModel(this.x, this.y);
        final List<Move> points = adjacent.getPoints();
        final List<Move> copyPoints = new ArrayList<Move>();
        for (Move copy : copyPoints) {
            copyPoints.add(copy);
        }
        // validate the move //
        for (Move move : points) {
            final DirectionModel moreAdjacent = new DirectionModel(move.getX(), move.getY());
            final List<Move> morePoints = moreAdjacent.getPoints();
            for (Move innerMove : morePoints) {
                if (!points.contains(innerMove)) {
                    copyPoints.add(innerMove);
                }
            }
        } // End of the for //
                        
        // Iterate through all of the moves //
        // validate the move //
        for (Move move : copyPoints) {
            final boolean valid = this.board.basicValidateMove(this.type, move.getX(), move.getY(), this.x, this.y);
            if (valid) {
                score += 1.0;
                move.setScore(1.0);
            } else {
                move.setScore(-1.0);
            } // End of the if //
        } // End of the for //
        return score;
    }
    
    /**
     * Score a total based on the current position and empty spots.
     * 
     * @return
     */
    public double scoreEmptyMoves() {
        
        double score = 0;
        final DirectionModel adjacent = new DirectionModel(this.x, this.y);
        final List<Move> points = adjacent.getPoints();
        
        // validate the move //
        for (Move move : points) {
            final boolean valid = this.board.basicValidateMove(this.type, move.getX(), move.getY(), this.x, this.y);
            if (valid) {
                score += 1.0;
                move.setScore(1.0);
            } else {
                move.setScore(-1.0);
            } // End of the if //
        } // End of the for //
        
        return score;
    }
    
    /**
     * Score negative values for enemy positions.
     * @return
     */
    public double scoreEnemyMoves() {
        
        double score = 0;
        final DirectionModel adjacent = new DirectionModel(this.x, this.y);
        final List<Move> points = adjacent.getPoints();
        
        // validate the move //
        for (Move move : points) {
            final boolean valid = this.board.basicValidateBounds(this.type, move.getX(), move.getY());
            if (!valid) {
                continue;
            }
            final byte curType = this.board.getBoardVal(move.getX(), move.getY());
            final boolean aPlayer = (curType == ITronBoard.PLAYER1) || (curType == ITronBoard.PLAYER2); 
            if (aPlayer && (curType != this.type)) {
                move.setScore(-1.0);
                score += -1.0;
            }            
        } // End of the for //
        
        return score;
    }
           
} // End of the Class //
