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
package org.berlin.seesaw.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseWorker implements ITeeterEventWorker {    
    
    /**
     * Action method executed by the listener event.
     */
    public abstract void execute();
    
    private ActionEvent lastEvent;
    private ITeeterWidget masterParent;
    
    public SwingWorker buildWorker(final ITeeterWidget masterParent, final ActionEvent event) {
        
        // At this point, we know the event and the parent object
        // before the event method is called.
        this.masterParent = masterParent;
        this.lastEvent = event;
        return new Worker();
        
    }
    
    public ActionListener buildListener(final ITeeterWidget masterParent) {
        
        return new ActionListener() {    
            public void actionPerformed(final ActionEvent event) {
                final SwingWorker worker = BaseWorker.this.buildWorker(masterParent, event);
                worker.start();
            }
        }; // End of Listener //
        
    }
    
    /**
     * BaseWorker must be created first then
     * the Worker class.
     * 
     * In the swing component action listener method,
     * the contruct method is invoked.
     * 
     * Flow:
     * 
     * ------------------
     * (1) new BaseWorker -> (2) [ActionListener/actionPerformed] obj.buildWorker() ... (3) [Event] obj.execute() called   
     * ------------------
     * 
     */
    public class Worker extends SwingWorker {
        
        public SwingWorker build() {
            return this;
        }

        @Override
        public Object construct() {
            BaseWorker.this.execute();
            return Thread.currentThread() + "-" + this.toString();
        }
    } // End of the Worker Class //

    /**
     * @return the lastEvent
     */
    public ActionEvent getLastEvent() {
        return lastEvent;
    }

    /**
     * @return the masterParent
     */
    public ITeeterWidget getMasterParent() {
        return masterParent;
    }
    
} // End of the Class
