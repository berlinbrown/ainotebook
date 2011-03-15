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

public class AIBotScoreModel {

    private double scoreNorth = 0;
    private double scoreSouth = 0;
    private double scoreEast  = 0;
    private double scoreWest  = 0;
    
    public String toString() {
        return "Scores - n=" + this.scoreNorth + " s=" + this.scoreSouth + " e=" + this.scoreEast + " w=" + this.scoreWest;
    }
    
    /**
     * @return the scoreNorth
     */
    public double getScoreNorth() {
        return scoreNorth;
    }
    /**
     * @param scoreNorth the scoreNorth to set
     */
    public void setScoreNorth(double scoreNorth) {
        this.scoreNorth = scoreNorth;
    }
    /**
     * @return the scoreSouth
     */
    public double getScoreSouth() {
        return scoreSouth;
    }
    /**
     * @param scoreSouth the scoreSouth to set
     */
    public void setScoreSouth(double scoreSouth) {
        this.scoreSouth = scoreSouth;
    }
    /**
     * @return the scoreEast
     */
    public double getScoreEast() {
        return scoreEast;
    }
    /**
     * @param scoreEast the scoreEast to set
     */
    public void setScoreEast(double scoreEast) {
        this.scoreEast = scoreEast;
    }
    /**
     * @return the scoreWest
     */
    public double getScoreWest() {
        return scoreWest;
    }
    /**
     * @param scoreWest the scoreWest to set
     */
    public void setScoreWest(double scoreWest) {
        this.scoreWest = scoreWest;
    }
    
}
