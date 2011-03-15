/**
 * Copyright (c) 2006-2010 Berlin Brown and botnode.com/Berlin Research  All Rights Reserved
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
 * Date: 2/15/2010 
 *   
 * Home Page: http://botnode.com/
 * 
 * Description: Seesaw (Teeter Totter) Swing Framework
 * 
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */

/**
 * SwingWorker.java:
 * 
 * This is the 3rd version of SwingWorker (also known as SwingWorker 3), an
 * abstract class that you subclass to perform GUI-related work in a dedicated
 * thread.
 */
package org.berlin.seesaw.swing;

import javax.swing.SwingUtilities;

/**
 * This is the 3rd version of SwingWorker (also known as SwingWorker 3), an
 * abstract class that you subclass to perform GUI-related work in a dedicated
 * thread. For instructions on using this class, see:
 * 
 * http://java.sun.com/docs/books/tutorial/uiswing/misc/threads.html
 * 
 * Note that the API changed slightly in the 3rd version: You must now invoke
 * start() on the SwingWorker after creating it.
 * 
 * Example Usage:
 * <pre>
 *       final ActionListener connectListener = new ActionListener() {
 *            public void actionPerformed(final ActionEvent event) {
 *               final SwingWorker worker = BrowserFrame.this.browserHandler.buildConnectWorker();
 *               worker.start();
 *           }}; // End of the Method //
 *       this.actionButton.addActionListener(connectListener); 
 *       this.urlField.addActionListener(connectListener);
 * </pre>
 */
public abstract class SwingWorker {

/* 
  Example Usage:
  ---------------
    public class HandleConnectWorker extends SwingWorker {
        public Object construct() {
            ...
            BrowserHandler.this.handleConnect();
            return "" + Thread.currentThread();
        }
        public void start() {
            this.tstart = System.currentTimeMillis();
            System.out.println("Executing...");
            super.start();
        }        
        public void finished() {
            ...
            System.out.println("Operation complete [!]");
            this.execTime();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    BrowserHandler.this.statusArea.setText("Operation complete in " + tdiff + " ms");
                }
            });
            super.finished();
        }
    }
    ...
    ...
    final ActionListener connectListener = new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
            
                final SwingWorker worker = new HandleConnectWorker();
                worker.start();
            }};
    this.actionButton.addActionListener(connectListener); 
    this.urlField.addActionListener(connectListener);

 */
    
    private Object value;    // see getValue(), setValue()
    private Thread thread;
    
    /**
     * Allow us to interact with the parent object that created this worker.
     */
    private Object parentMaster;

    /**
     * Start a thread that will call the <code>construct</code> method and then
     * exit.
     */
    public SwingWorker() {
                
        final Runnable doFinished = new Runnable() {
            public void run() {
                finished();
            }
        };

        Runnable doConstruct = new Runnable() {
            public void run() {
                try {
                    setValue(construct());
                } finally {
                    threadVar.clear();
                }
                SwingUtilities.invokeLater(doFinished);
            }
        };

        Thread t = new Thread(doConstruct);
        threadVar = new ThreadVar(t);
    }

    /**
     * Compute the value to be returned by the <code>get</code> method.
     */
    public abstract Object construct();

    /**
     * Start the worker thread.
     */
    public void start() {
        Thread t = threadVar.get();
        if (t != null) {
            t.start();
        }
    }

    /**
     * Class to maintain reference to current worker thread under separate
     * synchronization control.
     */
    private static class ThreadVar {

        private Thread thread;

        ThreadVar(Thread t) {
            thread = t;
        }

        synchronized Thread get() {
            return thread;
        }

        synchronized void clear() {
            thread = null;
        }

    }

    private ThreadVar threadVar;

    /**
     * Get the value produced by the worker thread, or null if it hasn't been
     * constructed yet.
     */
    protected synchronized Object getValue() {
        return value;
    }

    /**
     * Set the value produced by worker thread
     */
    private synchronized void setValue(Object x) {
        value = x;
    }

    /**
     * Called on the event dispatching thread (not on the worker thread) after
     * the <code>construct</code> method has returned.
     */
    public void finished() {

    }

    /**
     * A new method that interrupts the worker thread. Call this method to force
     * the worker to stop what it's doing.
     */
    public void interrupt() {
        Thread t = threadVar.get();
        if (t != null) {
            t.interrupt();
        }
        threadVar.clear();
    }

    /**
     * Return the value created by the <code>construct</code> method. Returns
     * null if either the constructing thread or the current thread was
     * interrupted before a value was produced.
     * 
     * @return the value created by the <code>construct</code> method
     */
    public Object get() {
        while (true) {
            Thread t = threadVar.get();
            if (t == null) {
                return getValue();
            }
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // propagate
                return null;
            }
        }
    }

    /**
     * @return the parentMaster
     */
    public synchronized Object getParentMaster() {
        return parentMaster;
    }

    /**
     * @param parentMaster the parentMaster to set
     */
    public synchronized void setParentMaster(final Object parentMaster) {
        this.parentMaster = parentMaster;
    }

} // End of the Classs //
