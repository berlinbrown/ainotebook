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
package org.berlin.tron.gl;

public class Mass {
            
    private float x = 0;
    private float y = 0;
    private float glsize = 0.04f;
    
    private float r = 1.0f;
    private float g = 0.0f;
    private float b = 0.0f;
    
    private byte type;
    
    public void setColor(final float r1, final float g1, final float b1) {
        this.r = r1;
        this.g = g1;
        this.b = b1;
    }
    
    /////////////////////////////////////////////////////////////////
    
    /**
     * @return the x
     */
    public float getX() {                   
        return x;
    }
    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }
    /**
     * @return the y
     */
    public float getY() {
        return y;
    }
    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }
    /**
     * @return the glsize
     */
    public float getGlsize() {
        return glsize;
    }
    /**
     * @param glsize the glsize to set
     */
    public void setGlsize(float glsize) {
        this.glsize = glsize;
    }    
    
    public void incX(float x) {
        this.x += x;
    }
    
    public void incY(float y) {
        this.y += y;
    }

    /**
     * @return the r
     */
    public float getR() {
        return r;
    }

    /**
     * @return the g
     */
    public float getG() {
        return g;
    }

    /**
     * @return the b
     */
    public float getB() {
        return b;
    }

    /**
     * @return the type
     */
    public byte getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(byte type) {
        this.type = type;
    }       
    
} // End of the Class //
