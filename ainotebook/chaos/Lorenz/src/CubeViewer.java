/**
 * Code From: 
 * http://apcs.mathorama.com/index.php?n=Main.Perspective
 * 
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Demonstrates an 3D Object like a cube and makes a "First-person" point-of
 * view using the arrow keys to move up and down, left and right
 * 
 * @author Chris Thiel, OFMCap size of applet should be 800 x 600
 */

@SuppressWarnings("serial")
public class CubeViewer extends Applet implements KeyListener {
    public static double amount = 10.0; // amount of change each arrow will
                                        // change
    Point3D view;
    Cube cube;

    public void init() {
        view = new Point3D(400, 300, 0);
        cube = new Cube(new Point3D(300, 200, 100), 200.0, Color.BLUE);
        this.addKeyListener(this);
    }

    public void paint(Graphics g) {
        String vx = "x = " + view.getX();
        String vy = "y = " + view.getY();
        String vz = "z = " + view.getZ();
        g.drawString(vx, 20, 20);
        g.drawString(vy, 20, 35);
        g.drawString(vz, 20, 50);
        g.drawString("Click Screen for focus.  Use Arrows to view left and right, a to go forward, z to go back", 20,
                580);
        cube.draw(g, view);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 38) { // up
            view.setY(view.getY() + amount);
        } else if (keyCode == 40) { // down
            view.setY(view.getY() - amount);
        } else if (keyCode == 37) { // left
            view.setX(view.getX() - amount);
        } else if (keyCode == 39) { // right
            view.setX(view.getX() + amount);
        } else if (keyCode == 65) { // forward
            view.setZ(view.getZ() + amount);
        } else if (keyCode == 90) { // back
            view.setZ(view.getZ() - amount);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}