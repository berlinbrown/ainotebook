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
 * Lorenz attractor in Java:
 * With 3D view.
 * 
 * http://en.wikipedia.org/wiki/Lorenz_attractor 
 * 
 * Dec 25, 2011
 * bbrown
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */


/**
 * Code From: 
 * http://apcs.mathorama.com/index.php?n=Main.Perspective 
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Demonstrates an 3D Object like a cube and makes a "First-person" point-of
 * view using the arrow keys to move up and down, left and right
 * 
 * @author Chris Thiel, OFMCap size of applet should be 800 x 600
 */
public class LorenzViewer extends Applet implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	private final int w = 790;
	private final int h = 820;
	
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
	private LorenzPoint3D view = new LorenzPoint3D(710, 60, 0);
	private LorenzCube cube = new LorenzCube(new LorenzPoint3D(300, 200, 100), 210.0, Color.BLUE);
	
    public void init() {
    	this.invokeLater();
    }
	
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
		
		this.setPreferredSize(new Dimension(w, h));
		this.setSize(new Dimension(w, h));						

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
		this.add(panel);		
		this.setVisible(true);
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
		final LorenzViewer o = new LorenzViewer();
		o.invokeLater();
		System.out.println("Done");
	}

}