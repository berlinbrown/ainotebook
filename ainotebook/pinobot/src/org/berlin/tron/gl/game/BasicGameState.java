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

public class BasicGameState extends UpdateStateTask implements GameWidget {

    private GLGame game;
    private ITronBoard basicBoard;
    private final IBot bot1;
    private final IBot bot2;
    private boolean verbose = false;
    
    public BasicGameState(final GLGame game, final GLRenderBoard board, final IBot bot1, final IBot bot2) {
        super(board);
        this.bot1 = bot1;
        this.bot2 = bot2;
        this.game = game;
    }

    public void updateState() {

        synchronized (this.basicBoard) {
                        
            // Continue to normal game state update //
            basicBoard.marshalMoves(ITronBoard.PLAYER1, this.bot1);
            basicBoard.marshalMoves(ITronBoard.PLAYER2, this.bot2);
            this.game.stepGame();
            this.getGlRenderBoard().setBoard(basicBoard);
        } // End of the block //
    }

    @Override
    public void run() {

        if (this.getGlRenderBoard() != null) {

            // If null, create a new basic board //
            if (this.basicBoard == null) {
                this.basicBoard = this.getGlRenderBoard().getBoard();
            } else {
                this.updateState();
            } // End of the if - else //

        } // End of Sync Block //
    }

    public boolean getVerbose() {
        return verbose;
    }

    public void setVerbose(boolean b) {
        this.verbose = b;
    }

} // End of the Class //
