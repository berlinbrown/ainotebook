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

import java.util.Timer;

public class GLGame {

    private final IBot bot1;
    private final IBot bot2;
    private final GLRenderBoard board;
    
    public GLGame(final GLRenderBoard board, final IBot bot1, final IBot bot2) {
        this.board  = board;
        this.bot1   = bot1;
        this.bot2   = bot2;
    }
 
    public void setInitBotPos() {
        
        final ITronBoard basicBoard = this.board.getBoard();        
        int sizex = basicBoard.getNumCols();
        int sizey = basicBoard.getNumRows();
        
        // Init position
        this.bot1.makeMove(new Move(2, 2));
        this.bot2.makeMove(new Move(sizex-2, sizey-2));
    }
    
    public void stepGame() {
        
        bot1.makeLogicMove();
        bot2.makeLogicMove();
        
    }
    
    public UpdateStateTask launchTask() {
        
        final UpdateStateTask changeStateTask = new BasicGameState(this, this.board, bot1, bot2);
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(changeStateTask, 
                changeStateTask.getTaskDelayMs(), 
                changeStateTask.getTaskPeriodMs());
        return changeStateTask;
    }
    
} // End of the Class //
