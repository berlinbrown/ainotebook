

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
 * Code From: http://apcs.mathorama.com/index.php?n=Main.Perspective
 * 
 */
public class LorenzCube {

	private LorenzPoint3D v[]; // vertices
	private Color color;

	private List<LorenzPoint3D> points = new ArrayList<LorenzPoint3D>();
	private List<LorenzPoint3D> lorenz = new ArrayList<LorenzPoint3D>();

	private double deltat = 0.004;
	private double sigma = 10.0;
	private double rho = 28.0;
	private double beta = 8.0 / 3.0;

	private int iterations = 20000;

	public LorenzCube(final LorenzPoint3D topFrontLeftCorner, double size, Color c) {
		this.color = c;
		v = new LorenzPoint3D[8];
		v[0] = topFrontLeftCorner;
		v[1] = new LorenzPoint3D(topFrontLeftCorner.getX() + size, topFrontLeftCorner.getY(), topFrontLeftCorner.getZ());
		v[2] = new LorenzPoint3D(topFrontLeftCorner.getX() + size, topFrontLeftCorner.getY() + size, topFrontLeftCorner.getZ());
		v[3] = new LorenzPoint3D(topFrontLeftCorner.getX(), topFrontLeftCorner.getY() + size, topFrontLeftCorner.getZ());
		v[4] = new LorenzPoint3D(topFrontLeftCorner.getX(), topFrontLeftCorner.getY(), topFrontLeftCorner.getZ() + size);
		v[5] = new LorenzPoint3D(topFrontLeftCorner.getX() + size, topFrontLeftCorner.getY(), topFrontLeftCorner.getZ() + size);
		v[6] = new LorenzPoint3D(topFrontLeftCorner.getX() + size, topFrontLeftCorner.getY() + size, topFrontLeftCorner.getZ() + size);
		v[7] = new LorenzPoint3D(topFrontLeftCorner.getX(), topFrontLeftCorner.getY() + size, topFrontLeftCorner.getZ() + size);

		// Set random points
		final Random r = new Random();
		final int offx = 260;
		final int offy = 60;
		for (int i = 0; i < 10; i++) {
			points.add(new LorenzPoint3D(r.nextInt(200) + offx, r.nextInt(200) + offy, 200));
		} // End of the for //

		final int offx2 = 280;
		final int offy2 = 180;
		final int offz2 = 30;
		final double scale = 20.0;

		// Based on the equations from:
		// http://en.wikipedia.org/wiki/Lorenz_attractor
		double x = 0.01;
		double y = 0.01;
		double z = 0.01;

		double xnext = 0;
		double ynext = 0;
		double znext = 0;
		double t = 0;
		for (int i = 0; i < iterations; i++) {
			final double dxdt = sigma * (y - x);
			final double dydt = (x * (rho - z)) - y;
			final double dzdt = (x * y) - (beta * z);
			lorenz.add(new LorenzPoint3D((scale * x) + offx2, (y * scale) + offy2, (scale * z) + offz2));
			// for the differential equation
			xnext = x + (deltat * dxdt);
			ynext = y + (deltat * dydt);
			znext = z + (deltat * dzdt);
			x = xnext;
			y = ynext;
			z = znext;
			t += deltat;
		} // End of the for //
	}

	/**
	 * 
	 * @param g
	 *            the Graphics object to draw on
	 * @param v
	 *            the viewpoint
	 */
	public void draw(final Graphics g, final LorenzPoint3D vp) {
		final Color oldColor = g.getColor();
		g.setColor(color);

		// Connect points a to b, etc. These are indexes not points in space
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
		for (final LorenzPoint3D p : this.points) {
			g.fillRect(p.viewX(vp), p.viewY(vp), 1, 1);
		}

		for (final LorenzPoint3D p : this.lorenz) {
			g.fillRect(p.viewX(vp), p.viewY(vp), 1, 1);
		}

		g.setColor(oldColor);
	}

	public void connect(Graphics g, LorenzPoint3D vp, int a, int b) {
		g.drawLine((v[a]).viewX(vp), v[a].viewY(vp), v[b].viewX(vp),
				v[b].viewY(vp));
	}

} // End of the class //
