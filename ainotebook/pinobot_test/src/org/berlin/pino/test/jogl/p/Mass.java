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
package org.berlin.pino.test.jogl.p;

public class Mass {
            
    private float x = 0;
    private float y = 0;
    private float glsize = 0.04f;
    
    /**
     * Magnitude of velocity, m / s
     */
    private float vm = 1.0f;
    
    /**
     * Angle y-axis to the cannon.
     */
    private float alphaCannon = 25.0f;
    
    private float lenCannon = 10.0f;
    
    /**
     * Base elevation of the cannon
     */
    private float yb = 10.0f;    
    private float time = 0.0f;    
    private float tinc = 0.01f;
    
    /**
     * Acceleration due to gravity, m / (s ^ 2)
     */
    private float gravity = 9.81f; 
    
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
    

    public void simulate() {
     
        float b;
        float cannonx, cannony;
        float cannonEndx;
        float cannonEndy;
        float cosx, cosy;
        
        // Projection of barrel 
        b = (float) (this.lenCannon * Math.cos((90.0f - this.alphaCannon) * 3.14f / 180.0f));
        cannonx = b * (float) Math.cos(0.0f);
        cannony = y * (float) Math.cos(this.alphaCannon * 3.14f / 180.0f);               

        cosx = cannonx / this.lenCannon;
        cosy = cannony / this.lenCannon;
     
        // x/y coords of the end of the cannon barrel
        cannonEndx = (float) (this.lenCannon * Math.cos((90.0f - this.alphaCannon) * 3.14 / 180.0f));
        cannonEndy = (float) (this.lenCannon * Math.cos((90.0f - this.alphaCannon) * 3.14 / 180.0f));
        
        this.x = this.vm * cosx * this.time + cannonEndx;        
        this.y = (float) ((this.yb + this.lenCannon * Math.cos(this.alphaCannon * 3.14 / 180.0f))
                    + (this.vm * cosy * this.time)
                    - (0.5 * this.gravity * this.time * this.time));
                
        // Scale X and Y
        this.x *= 0.11f;
        this.y *= 0.11f;
        
        // Print report //
        System.out.println("Cannon End X: " + cannonEndx + " Cannon End Y: " + cannonEndy); 
        System.out.println("X: " + this.x + " Y: " + this.y);
        
        this.time += this.tinc;        
    }
    
} // End of the Class //
