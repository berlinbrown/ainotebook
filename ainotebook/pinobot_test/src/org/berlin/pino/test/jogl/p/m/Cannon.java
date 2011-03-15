/**
 *
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
 */
package org.berlin.pino.test.jogl.p.m;

/**
 * A cannon has a length and barrel size.  Calculations are done on the cannon based
 * on the configuration of the device. 
 * 
 * A testable cannon class.
 * @author BerlinBrown
 *
 */
public class Cannon {

    private final double len;
    
    /**
     * The barrel size.
     */
    private final double size; 
   
    /**
     * Angle from the horizontal x-axis to the y-axis.
     * Angle in degrees.
     */
    private final double alphaAngle;
    
    private double scale = 1.0;
    
    public Cannon(final double l, final double sz, final double alpha) {
        
        this.len = l;
        this.size = sz;
        this.alphaAngle = alpha;
        if (len < 0) {
            throw new IllegalArgumentException("Invalid Cannon Length");
        }
        
        if (size < 0) {
            throw new IllegalArgumentException("Invalid Cannon Size");
        }
        
    }
    
    public String toString() {
        return "{l:" + this.len + " sz:" + this.size + " alpha: " + this.alphaAngle + "}";
    }
    
    public String toStringFull() {
        return "{l:" + this.len + " sz:" + this.size + " alpha: " + this.alphaAngle + "} - scalel:" + (this.scale * this.len);
    }    
    
    public double calcTiltX() {
        return (this.len * this.scale) * java.lang.Math.cos((this.alphaAngle) * Math.PI_180); 
    }
    
    public double calcTiltY() {
        return (this.len * this.scale) * java.lang.Math.sin((this.alphaAngle) * Math.PI_180); 
    }
    
    /////////////////////////////////////////////////////////////////
    
    /**
     * @return the scale
     */
    public double getScale() {
        return scale;
    }

    /**
     * @param scale the scale to set
     */
    public void setScale(double scale) {
        this.scale = scale;
    }
          
} // End of the Class //
 