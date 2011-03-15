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
 * Based on an input starting bot, build another bot with
 * similar attributes.
 * 
 * @author BerlinBrown
 *
 */
public class AIBotBuilder {
    
    public static final int MAX_SCORE_CHECK_DEPTH = 20;
    
    private final IBot startingBot;
    
    private boolean dead = false;
    private boolean canMakeMove = true;
    
    private IBot otherBot;
    private Move otherBotPos;
    
    private String name;   
    private ITronBoard board;
    
    public AIBotBuilder(final IBot startingBot) {
        this.startingBot = startingBot;
    }
    
    public AIBotBuilder withOtherBot() { 
        this.otherBot = this.startingBot.getOtherBot();
        this.otherBotPos = this.startingBot.getOtherBotPos();
        return this;
    }
    
    public AIBotBuilder withBotAttrs() {
        this.dead = this.startingBot.isDead();
        this.canMakeMove = this.startingBot.isUnableToMakeMove();
        this.name = this.startingBot.getName() + "-clone";
        return this;
    }
    
    /**
     * Transfer the builder attributes into the new object.
     * @return
     */
    public IBot build() {
        if (startingBot == null) {
            return null;
        }
        final IBot newBot = new GLBot(this.startingBot.getBoard());
        newBot.setDead(this.dead);
        newBot.setOtherBot(this.otherBot);        
        newBot.setName(this.name);      
        return newBot;
    }
    
    public static IBot buildBotScoreMoves(final ITronBoard basicBoard) {
       
        // Create four bots for use with the bot scorer
        return new AIBotScoreMoves(basicBoard, MAX_SCORE_CHECK_DEPTH);
                    
                            
    }
    
    public static IBot buildBotOrder(final ITronBoard basicBoard) {
        return new AIBotFavorOrder(basicBoard);
    }
    
    public static IBot buildBotMinMax(final ITronBoard basicBoard) {
        return new AIBotMinMax(basicBoard);
    }
    
} // End of the Class//
