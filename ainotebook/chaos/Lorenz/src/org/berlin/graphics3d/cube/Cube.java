package org.berlin.graphics3d.cube;
/**
 * Code From: 
 * http://apcs.mathorama.com/index.php?n=Main.Perspective
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Code From: 
 * http://apcs.mathorama.com/index.php?n=Main.Perspective
 * 
 */
public class Cube {
    
    private Point3D v[]; // vertices
    private Color color;
    
    private List<Point3D> points = new ArrayList<Point3D>();

    public Cube(final Point3D topFrontLeftCorner, double size, Color c) {
        this.color = c;
        v = new Point3D[8];
        v[0] = topFrontLeftCorner;
        v[1] = new Point3D(topFrontLeftCorner.getX() + size, topFrontLeftCorner.getY(), topFrontLeftCorner.getZ());
        v[2] = new Point3D(topFrontLeftCorner.getX() + size, topFrontLeftCorner.getY() + size, topFrontLeftCorner.getZ());
        v[3] = new Point3D(topFrontLeftCorner.getX(), topFrontLeftCorner.getY() + size, topFrontLeftCorner.getZ());
        v[4] = new Point3D(topFrontLeftCorner.getX(), topFrontLeftCorner.getY(), topFrontLeftCorner.getZ() + size);
        v[5] = new Point3D(topFrontLeftCorner.getX() + size, topFrontLeftCorner.getY(), topFrontLeftCorner.getZ() + size);
        v[6] = new Point3D(topFrontLeftCorner.getX() + size, topFrontLeftCorner.getY() + size, topFrontLeftCorner.getZ()+ size);
        v[7] = new Point3D(topFrontLeftCorner.getX(), topFrontLeftCorner.getY() + size, topFrontLeftCorner.getZ() + size);
        
        // Set random points
        final Random r = new Random();
        final int offx = 260;
        final int offy = 60;
        for (int i = 0; i < 200; i++) {        
        	points.add(new Point3D(r.nextInt(200) + offx, r.nextInt(200) + offy, 200));
        }
    }

    /**
     * 
     * @param g
     *            the Graphics object to draw on
     * @param v
     *            the viewpoint
     */
    public void draw(final Graphics g, final Point3D vp) {
        final Color oldColor = g.getColor();
        g.setColor(color);
        
        // Connect points a to b, etc.  These are indexes not points in space
        connect(g, vp, 0, 1);
        connect(g, vp, 1, 2);
        connect(g, vp, 2, 3);
        connect(g, vp, 3, 0);
        connect(g, vp, 4, 5);
        connect(g, vp, 5, 6);
        connect(g, vp, 6, 7);
        connect(g, vp, 7, 4);
        connect(g, vp, 0, 4);
        connect(g, vp, 0, 4);
        connect(g, vp, 1, 5);
        connect(g, vp, 2, 6);
        connect(g, vp, 3, 7);
        
        g.setColor(Color.red);
        for (final Point3D p : this.points) {
        	g.fillRect(p.viewX(vp), p.viewY(vp), 1, 1);
        }        
        g.setColor(oldColor);
    }

    public void connect(Graphics g, Point3D vp, int a, int b) {
        g.drawLine((v[a]).viewX(vp), v[a].viewY(vp), v[b].viewX(vp), v[b].viewY(vp));
    }
    
} // End of the class //
