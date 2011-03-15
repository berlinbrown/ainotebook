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
 */
public interface IMove {
         
    public static final String NORTH = "North";
    public static final String SOUTH = "South";
    public static final String EAST  = "East";
    public static final String WEST  = "West";
        
    public static final double NEG_THOUGHT_MOVE_PREV_SCORE = -3.0;
    public static final double NEG_THOUGHT_MOVE_SCORE      = -2.0;    
    public static final double NEG_THOUGHT_MOVE_WALL_SCORE = -1.0;
    
    /**
     * If we are getting close to players, then this is a bad move.
     */
    public static final double NEG_THOUGHT_MOVE_PLAYER     = -10.0;
    
    public static final double NEG_THOUGHT_ONLY_ONE_MOVE   = -20.0;
    
    public static final double NEG_NO_MOVES                = -100.0;
    
    public static final double POS_VALID_MOVE              =  15.0;
    
    public static final double NEG_LOOK_ERR                =  -60.0;
    
    public static final double NEG_LOOK_NO_FIND_ERR        =  -50.0;
    
    public static final double POS_FIND                    =   70.0;
    
    public static final double NEG_OTHER_PLAYER           =  -30.0;
    
    /**
     * @return the x
     */
    public int getX();

    /**
     * @return the y
     */
    public int getY();
    
    /**
     * Method incx.
     * @return Move
     */
    public Move incx();
    
    /**
     * Method incy.
     * @return Move
     */
    public Move incy();
    
    /**
     * Method decx.
     * @return Move
     */
    public Move decx();
    
    /**
     * Method decy.
     * @return Move
     */
    public Move decy();
    
    /**
     * Method getMoveTimeMs.
     * @return long
     */
    public long getMoveTimeMs();
    
    /**
     * Method getDirection.
     * @return String
     */
    public String getDirection();
    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction);

    /**
     * Method getScore.
     * @return double
     */
    public double getScore();

    /**
     * @param score the score to set
     */
    public void setScore(double score);
    
    public double getEnemyDistance();

    /**
     * @param enemyDistance the enemyDistance to set
     */
    public void setEnemyDistance(double enemyDistance);
    
} // End of the Interface //
