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

import java.util.Stack;


/**
 * Default google challenge AI bot.  Search for a valid mode.
 * This bot can also be used for checking valid moves and scoring them.
 * 
 * @author BerlinBrown
 *
 */
public class AIBotFavorOrder extends GLBot {
       
    public AIBotFavorOrder(final ITronBoard basicBoard) {
        
        super(basicBoard);                      
        this.setVerbose(false);
    }       
    
    /////////////////////////////////////////////////////////////////
                  
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
        
        final Stack<Move> stack = (Stack<Move>) this.getMoves().getMoves();
        final boolean nb = validateMove(stack, north);
        final boolean sb = validateMove(stack, south);
        final boolean eb = validateMove(stack, east);
        final boolean wb = validateMove(stack, west);

        if (wb) {
            this.makeMove(west);
            return;
        }
        if (nb) {
            this.makeMove(north);
            return;
        }
        
        if (eb) {
            this.makeMove(east);
            return;
        }                        
        if (sb) {
            this.makeMove(south);
            return;
        }
        
        this.makeMove(this.dumbRandomMove());
        return;        
    }
       
        
} // End of the Class //
