package org.berlin.graphics3d.cube;

/**
 * Code From: 
 * http://apcs.mathorama.com/index.php?n=Main.Perspective 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Demonstrates an 3D Object like a cube and makes a "First-person" point-of
 * view using the arrow keys to move up and down, left and right
 * 
 * @author Chris Thiel, OFMCap size of applet should be 800 x 600
 */
public class CubeViewer implements KeyListener {

	private final int w = 790;
	private final int h = 820;

	private JFrame frame;
	private int counter = 0;
	private UpdatableCanvas canvas;

	private int maxGridRows = 32;
	private int maxGridCols = 32;
	
	private final int offX = 30;
	private final int offY = 30;

	private int renderGridWidth = 0;
	private int renderGridHeight = 0;

	private int timeInMsForRenderUpdate = 80;

	public static double amount = 10.0; // amount of change each arrow will
	private Point3D view = new Point3D(710, 60, 0);
	private Cube cube = new Cube(new Point3D(300, 200, 100), 210.0, Color.BLUE);

	/**
	 * Canvas.
	 */
	private class UpdatableCanvas extends JPanel {

		private static final long serialVersionUID = 1L;

		private Image offScreenImage = null;
		private Graphics offScreenGraphics = null;
		private Image offScreenImageDrawed = null;
		private Graphics offScreenGraphicsDrawed = null;

		private final Timer timer = new Timer();

		private int canvasCounter = 0;

		public UpdatableCanvas() {
			timer.schedule(new UpdatableCanvasTask(), 0,
					timeInMsForRenderUpdate);
		}

		/**
		 * Timer task, refresh canvas.
		 */
		private class UpdatableCanvasTask extends java.util.TimerTask {
			@Override
			public void run() {
				if (!EventQueue.isDispatchThread()) {
					EventQueue.invokeLater(this);
				} else {
					UpdatableCanvas.this.repaint();
				}
			}
		} // End of the Class //

		/**
         * Use double buffering.
         * 
         * @see java.awt.Component#update(java.awt.Graphics)
         */
        @Override
        public void update(final Graphics g) {
            final Dimension d = canvas.getSize();
            if (offScreenImage == null) {
                offScreenImage = canvas.createImage(d.width, d.height);
                offScreenGraphics = offScreenImage.getGraphics();
            }
            canvas.paint(offScreenGraphics);
            g.drawImage(offScreenImage, 0, 0, null);
        }
        
        /**
         * Draw this generation.
         * 
         * @see java.awt.Component#paint(java.awt.Graphics)
         */
        public void paint(final Graphics g) {
            // Draw grid on background image, which is faster
            if (offScreenImageDrawed == null) {
                final Dimension d = getSize();
                offScreenImageDrawed = createImage(d.width, d.height);
                offScreenGraphicsDrawed = offScreenImageDrawed.getGraphics();
                offScreenGraphicsDrawed.setColor(Color.black);                
                offScreenGraphicsDrawed.fillRect(0, 0, d.width, d.height);                
            }
            g.drawImage(offScreenImageDrawed, 0, 0, null);
            g.setColor(Color.GREEN);
            g.drawString("UpdatableCanvas:" + canvasCounter, 6, 5);            
            
            final String vx = "x = " + view.getX();
            final String vy = "y = " + view.getY();
            final String vz = "z = " + view.getZ();
            g.drawString(vx, 20, 20);
            g.drawString(vy, 20, 35);
            g.drawString(vz, 20, 50);
            g.drawString("Click Screen for focus.  Use Arrows to view left and right, a to go forward, z to go back", 20, 580);
            cube.draw(g, view);                       
            canvasCounter++;
        }


	} // End of the class //

	/**
	 * Launch the 2D frame window.
	 */
	public void invokeLater() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initializeApplication();
			}
		});
	}

	public int getMaxGridRows() {
		return maxGridRows;
	}

	public int getMaxGridCols() {
		return maxGridCols;
	}

	public int getOffX() {
		return offX;
	}

	public int getOffY() {
		return offY;
	}

	public int getRenderGridWidth() {
		return renderGridWidth;
	}

	public int getRenderGridHeight() {
		return renderGridHeight;
	}

	/**
	 * Create application. Do not extend the JFrame class.
	 */
	public void initializeApplication() {

		this.frame = new JFrame("Simple Physics Cannon Test - " + new Date());
		frame.setLocation(200, 200);
		frame.setPreferredSize(new Dimension(w, h));
		frame.setSize(new Dimension(w, h));
		frame.pack();
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		/*
		 * Add default panel.
		 */
		final JPanel panel = new JPanel();
		canvas = new UpdatableCanvas();
		panel.setVisible(true);
		panel.setPreferredSize(new Dimension(w, h));
		panel.setFocusable(true);
		panel.setBackground(Color.black);

		/*
		 * Add default canvas.
		 */
		canvas.setPreferredSize(new Dimension(w, h));
		canvas.setSize(new Dimension(w, h));
		canvas.setBackground(Color.black);
		panel.add(canvas);

		// Panel setup, toggle visibility on frame, set visible
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		panel.addKeyListener(this);
		this.startServerWait();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == 38) { // up			
			System.out.println("Processing up..");
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	protected void startServerWait() {
		try {
			final TimerTask waitOnApprTimerTask = new TimerTask() {
				public void run() {
					System.out.println("Still waiting ... " + counter);
					counter++;
				}
			};
			final Timer waitOnApprTimer = new Timer(true);
			waitOnApprTimer.scheduleAtFixedRate(waitOnApprTimerTask, 0,
					2 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Main entry point for application.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		System.out.println("Running");
		final CubeViewer o = new CubeViewer();
		o.invokeLater();
		System.out.println("Done");
	}

}