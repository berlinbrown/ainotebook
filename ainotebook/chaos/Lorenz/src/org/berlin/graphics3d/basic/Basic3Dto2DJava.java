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
 * Basic3Dto2DJava.java
 * Dec 25, 2011
 * bbrown
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */
package org.berlin.graphics3d.basic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author bbrown
 *
 */
public class Basic3Dto2DJava {

    private final int w = 790;
    private final int h = 820;

    private JFrame frame;
    private int counter = 0;
    private UpdatableCanvas canvas;

    private int maxGridRows = 32;
    private int maxGridCols = 32;
    private int sizeCell = 20;

    private final int offX = 30;
    private final int offY = 30;

    private final int perCellOffset = 2;

    private int renderGridWidth = 0;
    private int renderGridHeight = 0;

    private int timeInMsForRenderUpdate = 80;

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
            timer.schedule(new UpdatableCanvasTask(), 0, timeInMsForRenderUpdate);
        }

        /**
         * Timer task, refresh canvas.
         */
        private class UpdatableCanvasTask extends java.util.TimerTask {
            private static final long serialVersionUID = 1L;

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
         * Render the cell line grid.
         */
        public void renderCellLineGrid(final Graphics g) {

            final Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(1));

            final int szrows = (maxGridRows * (sizeCell + perCellOffset));
            final int szcols = (maxGridCols * (sizeCell + perCellOffset));
            g.setColor(Color.GREEN);
            for (int i = 0; i < maxGridRows + 1; i++) {
                g.drawLine(offX + (i * (sizeCell + perCellOffset)), offY + 0, offX + (i * (sizeCell + perCellOffset)), offY
                        + szrows);
            } // End of the For //
            for (int j = 0; j < maxGridCols + 1; j++) {
                g.drawLine(offX + 0, offY + (j * (sizeCell + perCellOffset)), offX + szcols, offY
                        + (j * (sizeCell + perCellOffset)));
            }
            final int xEndLow = offX + szcols;
            final int yEndLow = offY + szrows;

            g.setColor(Color.MAGENTA);
            g.drawString("X", offX, offY);
            g.drawString("Z" + xEndLow, xEndLow, offY);
            g.drawString("A" + yEndLow, offX, yEndLow);
        }

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
                renderCellLineGrid(offScreenGraphicsDrawed);
            }
            g.drawImage(offScreenImageDrawed, 0, 0, null);
            g.setColor(Color.GREEN);
            g.drawString("UpdatableCanvas:" + canvasCounter, 6, 14);            
                                   
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

        final int szrows = (maxGridRows * (sizeCell + perCellOffset));
        final int szcols = (maxGridCols * (sizeCell + perCellOffset));

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
        this.startServerWait();
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
            waitOnApprTimer.scheduleAtFixedRate(waitOnApprTimerTask, 0, 2 * 1000);
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
        final Basic3Dto2DJava o = new Basic3Dto2DJava();
        o.invokeLater();
        System.out.println("Done");
    }

     
} // End of the class //
