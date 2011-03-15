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
 * Calculate the enemy distance.
 * 
 * @author BerlinBrown
 *
 */
public class CalcEnemyDistance {

    private final List<Move> moves;
    private final Move currentMove;
    
    public CalcEnemyDistance(final List<Move> movesCheck, final Move currentMove) {
        this.moves = movesCheck;
        this.currentMove = currentMove;
    }
    
    public double calcMin() {
        final BotMath math = new BotMath();
        return math.min(this.calcDistanceAllMoves());
    }
    
    public double calcMax() {
        final BotMath math = new BotMath();
        return math.max(this.calcDistanceAllMoves());
    }
    
    public List<Double> calcDistanceAllMoves() {
        
        if (this.moves == null) {
            return new ArrayList<Double>();
        }
        
        final List<Double> newList = new ArrayList<Double>();
        for (Move move : this.moves) {
            newList.add(new Double(this.calcDistance(move)));
        }
        return newList;
    }
    
    /**
     * Calc distance for one point.
     * 
     * @param otherPoint
     * @return
     */
    public double calcDistance(final Move otherPoint) {
        
        final double x = this.currentMove.getX();
        final double y = this.currentMove.getY();
        
        final double x2 = otherPoint.getX();
        final double y2 = otherPoint.getY();
        
        final double distx = Math.abs(x2 - x);
        final double disty = Math.abs(y2 - y);
        
        return Math.sqrt((distx * distx) + (disty * disty));
    }
    
} // End of the class //
