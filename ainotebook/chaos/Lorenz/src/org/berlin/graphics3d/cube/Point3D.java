package org.berlin.graphics3d.cube;
/**
 * Code From: 
 * http://apcs.mathorama.com/index.php?n=Main.Perspective
 */

public class Point3D {
    
    public static final double screenDistance = 150.0;
    private double x;
    private double y;
    private double z;

    public Point3D(double x0, double y0, double z0) {
        x = x0;
        y = y0;
        z = z0;
    }

    public Point3D(int x0, int y0, int z0) {
        x = x0;
        y = y0;
        z = z0;
    }

    // See http://www.mathorama.com/geom/lessons/stereo2.html
    // or http://en.wikipedia.org/wiki/3D_projection#Perspective_projection
    public int viewX(Point3D viewpoint) {       
        double depth = this.z - viewpoint.getZ(); // D+E
        double width = this.x - viewpoint.getX(); // w analogous to h        
        // coord where eye is at (0,0)
        int ox = (int) Math.round(width * screenDistance / depth); 
        return (int) viewpoint.getX() + ox;
    }

    public int viewY(Point3D viewpoint) {
        double depth = this.z - viewpoint.getZ();  // D+E
        double height = this.y - viewpoint.getY(); // h
        int oy = (int) Math.round(height * screenDistance / depth);
        return (int) viewpoint.getY() + oy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double value) {
        this.x = value;
    }

    public void setY(double value) {
        this.y = value;
    }

    public void setZ(double value) {
        this.z = value;
    }
}
