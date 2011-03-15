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


public class FunctionalScoreAllMoves implements GameWidget {
        
    private boolean verbose = false;

    private final ITronBoard board;
    private final int x; 
    private final int y;
    private final byte type;
    private final int depth;
        
    private final DirectionModel allDirections;
    
    private int maxOperations = 200;
    
    public FunctionalScoreAllMoves(final int depth, final ITronBoard board, final int x, final int y, final byte type) {
        
        this.board = board;
        this.x = x;
        this.y = y;        
        this.type = type;
        this.depth = depth;
        
        allDirections = new DirectionModel(x, y);
    }
    
    public Map<Double, Move> scoreAll() {
        
        final LinkedHashMap<Double, Move> map = new LinkedHashMap<Double, Move>();
        map.put(new Double(this.scoreNorth()),  allDirections.getNorth());
        map.put(new Double(this.scoreSouth()), allDirections.getSouth());
        map.put(new Double(this.scoreEast()),  allDirections.getEast());
        map.put(new Double(this.scoreWest()),  allDirections.getWest());
        return BotUtilityMap.sortMapByKey(map);        
    }
    
    public double scoreNorth() {
        
        final FunctionalScoreMoves functionalScore = new FunctionalScoreMoves(this.depth, board, 
                allDirections.getNorth().getX(), allDirections.getNorth().getY(), type);        
        functionalScore.setVerbose(this.getVerbose());
        functionalScore.setMaxOperations(maxOperations);
        return functionalScore.scoreMoves();
        
    }
    
    public double scoreSouth() {
        
        final FunctionalScoreMoves functionalScore = new FunctionalScoreMoves(this.depth, board, 
                allDirections.getSouth().getX(), allDirections.getSouth().getY(), type);        
        functionalScore.setVerbose(this.getVerbose());
        functionalScore.setMaxOperations(maxOperations);
        return functionalScore.scoreMoves();
        
    }

    public double scoreEast() {
        
        final FunctionalScoreMoves functionalScore = new FunctionalScoreMoves(this.depth, board, 
                allDirections.getEast().getX(), allDirections.getEast().getY(), type);        
        functionalScore.setVerbose(this.getVerbose());
        functionalScore.setMaxOperations(maxOperations);
        return functionalScore.scoreMoves();
        
    }

    public double scoreWest() {
        
        final FunctionalScoreMoves functionalScore = new FunctionalScoreMoves(this.depth, board, 
                allDirections.getWest().getX(), allDirections.getWest().getY(), type);        
        functionalScore.setVerbose(this.getVerbose());
        functionalScore.setMaxOperations(maxOperations);
        return functionalScore.scoreMoves();
        
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
    
} // End of the Class //
