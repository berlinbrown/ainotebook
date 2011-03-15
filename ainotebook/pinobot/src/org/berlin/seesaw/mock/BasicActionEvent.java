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
package org.berlin.seesaw.mock;

import java.awt.AWTEvent;

public class BasicActionEvent extends AWTEvent {
    
    /**
     * The string that gives more details
     * of what caused the event.     
     */
    private String actionCommand;
    private long when;   
    private int modifiers;

    /**
     * Constructs an <code>ActionEvent</code> object.
     */
    public BasicActionEvent(Object source, int id, String command) {
        this(source, id, command, 0);
    }

    /**
     * Constructs an <code>ActionEvent</code> object with modifier keys.
     */
    public BasicActionEvent(Object source, int id, String command, int modifiers) {
        this(source, id, command, 0, modifiers);
    }

    /**
     * Constructs an <code>ActionEvent</code> object with the specified
     * modifier keys and timestamp. 
     */
    public BasicActionEvent(Object source, int id, String command, long when, int modifiers) {
        
        super(source, id);
        
        this.actionCommand = command;
        this.when = when;
        this.modifiers = modifiers;
    }
        
    /**
     * Returns the command string associated with this action.
     */
    public String getActionCommand() {
        return actionCommand;
    }

    /**
     * Returns the timestamp of when this event occurred.  
     */
    public long getWhen() {
        return when;
    }

    /**
     * Returns the modifier keys held down during this action event. 
     */
    public int getModifiers() {
        return modifiers;
    }

    /**
     * Returns a parameter string identifying this action event.
     */
    public String paramString() {
                       
        return "{paramString}";
    }
    
} // End of the Class //
