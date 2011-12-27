/**
 * Copyright (c) 2006-2010 Berlin Brown and botnode.com  All Rights Reserved
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
 * Graphics3d.java
 * Dec 25, 2011
 * bbrown
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */
package org.berlin.graphics3d.basic;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author bbrown
 *
 */
public class Graphics3d {

    private final Basic3Dto2DJava graphics;
   
    private double sceneWidth = 600.0;
    private double sceneHeight = 400.0;
    
    private double angleFieldOfView = 45.0;
    
    private double aspectRatio = 0;
    
    private double nNearPlane = 20.0;   
    private double fFarPlane = 100.0;

    /** Test target point in space, slightly above 0,0,0 */
    private Point3DSpace baseTargetPoint = new Point3DSpace(0,0,0); 
    
    /**
     * Constructor.
     * 
     * @param graphics
     */
    public Graphics3d(final Basic3Dto2DJava graphics) {
        this.graphics = graphics;
    }
    
    public void render(final Graphics g) {
        g.setColor(Color.red);        
    }
    
    public class Point3DSpace {
        private double x = 0;
        private double y = 0;
        private double z = 0;
        public Point3DSpace(final double x, final double y, final double z) {
            super();
            this.x = x;
            this.y = y;
            this.z = z;
        }
               
        public Point3DSpace() {
            super();
        }
    }
    
    public class Camera {
        private Point3DSpace cameraPosition = new Point3DSpace();
        private double angleOrientation = 30.0;
        private Point3DSpace eyeLookAt = new Point3DSpace();
    }
    
    /**
     * Simple 3 x 3 matrix multiply with result 3x3.
     * @param a
     * @param b
     * @return
     */
    public double [][] matrixMultiply3by3(final double [][] a, final double b [][]) {
       final int numRows = 3;
       final int numCols = 3;
              
       for (int i = 0; i < numCols; i++) {                      
       } // End of the for //
       return null;
    }
    
    /** Multiply 1 row X 3 col matrix = 1x1 */
    public double matrixMultiply1by3(final double [] a, final double vec []) {
        double sum = 0;
        sum += (a[0] * vec[0]);
        sum += (a[1] * vec[1]);
        sum += (a[2] * vec[2]);
        return sum;
    }
    
    public double [] vector(final double a, final double b, final double c) {
        return new double [] { a, b, c };
    }
    public double [] mat1by3(final double a, final double b, final double c) {
        return new double [] { a, b, c };
    }
    
    public static void main(final String [] args) {
        System.out.println("Test 3d graphics logic");   
        final Graphics3d t = new Graphics3d(null); 
        System.out.println("T1: " + t.matrixMultiply1by3(t.mat1by3(1, 2, 3), t.vector(1, 2, 3)));
        System.out.println("Done");
    }
    
} // End of the class //

