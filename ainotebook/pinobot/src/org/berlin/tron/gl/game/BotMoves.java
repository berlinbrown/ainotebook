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
import java.util.Stack;

/**
 * Collection of moves.
 * 
 * @author BerlinBrown
 *
 */
public class BotMoves implements IBotMoves, GameWidget {

    public Stack<Move> moves = new Stack<Move>();
    
    private boolean verbose = false;
    
    /**
     * @return the moves
     * @see org.berlin.tron.gl.game.IBotMoves#getMoves()
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * @param moves the moves to set
     * @see org.berlin.tron.gl.game.IBotMoves#setMoves(List<Move>)
     */
    public void setMoves(List<Move> moves) {
        this.moves = (Stack<Move>) moves;
    }
    
    /**
     * Method makeMove.
     * @param move Move
     * @see org.berlin.tron.gl.game.IBotMoves#makeMove(Move)
     */
    public void makeMove(final Move move) {
        synchronized(move) {
            this.moves.push(move);
        }
    }
    
    /**
     * Method size.
     * @return int
     * @see org.berlin.tron.gl.game.IBotMoves#size()
     */
    public int size() {
        return this.moves.size();
    }
    
    /**
     * Method printMove.
     * @param move Move
     * @see org.berlin.tron.gl.game.IBotMoves#printMove(Move)
     */
    public void printMove(final Move move) {
        if (this.getVerbose()) {
            System.err.println(move);
        }
    }
    
    /**
     * Method printMoves.
     * @see org.berlin.tron.gl.game.IBotMoves#printMoves()
     */
    public void printMoves() {
        for (Move curmove : this.moves) {
            this.printMove(curmove);
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
