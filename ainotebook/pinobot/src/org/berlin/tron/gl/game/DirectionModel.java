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

public class DirectionModel {

    private final Move north;
    private final Move south;
    private final Move east;
    private final Move west;
    
    private final Move north_east;
    private final Move south_east;
    private final Move north_west;
    private final Move south_west;
    
    public DirectionModel(final int x, final int y) {
        
        final Move curMove = new Move(x, y);
        this.north = curMove.decy();
        this.south = curMove.incy();
        this.east  = curMove.incx();
        this.west  = curMove.decx();       
        final Move tmp_ne = curMove.incx();                
        final Move tmp_se = curMove.incx();
        final Move tmp_nw = curMove.decx();
        final Move tmp_sw = curMove.decx();        
        this.north_east = tmp_ne.decy();
        this.south_east = tmp_se.incy();
        this.north_west = tmp_nw.decy();
        this.south_west = tmp_sw.incy();        
    }
    
    public String toString() {
        return "Directions-Around : n=" + this.north + " s=" + this.south + " e=" + this.east + " w=" + this.west;
    }
    
    public List<Move> getPoints() {
        
        final List<Move> points = new ArrayList<Move>();
        points.add(north);
        points.add(south);
        points.add(east);
        points.add(west);        
        points.add(north_east);
        points.add(south_east);
        points.add(north_west);
        points.add(south_west);
        return points;
    }
    
    /**
     * @return the north
     */
    public Move getNorth() {
        return north;
    }

    /**
     * @return the south
     */
    public Move getSouth() {
        return south;
    }

    /**
     * @return the east
     */
    public Move getEast() {
        return east;
    }

    /**
     * @return the west
     */
    public Move getWest() {
        return west;
    }

    /**
     * @return the north_east
     */
    public Move getNorth_east() {
        return north_east;
    }

    /**
     * @return the south_east
     */
    public Move getSouth_east() {
        return south_east;
    }

    /**
     * @return the north_west
     */
    public Move getNorth_west() {
        return north_west;
    }

    /**
     * @return the south_west
     */
    public Move getSouth_west() {
        return south_west;
    }
    
} // End of the Class //
