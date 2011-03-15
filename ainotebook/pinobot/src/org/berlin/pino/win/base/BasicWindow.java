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
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */
package org.berlin.pino.win.base;

import javax.swing.JComponent;

import org.berlin.pino.win.action.PinoAction;
import org.berlin.seesaw.app.ITeeterActionHandler;
import org.berlin.seesaw.swing.ITeeterButton;
import org.berlin.seesaw.swing.ITeeterPanel;
import org.berlin.seesaw.swing.ITeeterTextArea;
import org.berlin.seesaw.swing.gl.ITeeterGLCanvas;
import org.berlin.seesaw.swing.layout.ITeeterLayout;

/**
 * 
 * @author NNN 
 *
 */
public class BasicWindow implements IBasicWindow {

    private ITeeterLayout   layout;
    
    private ITeeterPanel    windowPanel;
    private ITeeterGLCanvas glCanvas;  
    private ITeeterTextArea chatTextArea; 
    private ITeeterTextArea inputTextArea;
    
    private ITeeterPanel    buttonPanel;
    private ITeeterButton   buttonEnter;    
    private ITeeterButton   buttonClear;    
    private ITeeterButton   buttonExit;
   
    private PinoAction actionHandler = new PinoAction(this);
    
    ///////////////////////////////////////////////////////
    
    public String toString() {
        return String.format("#{Basic Window: %s - %s}", super.toString(), this.windowPanel);
    }
    
    /**
     * @param windowPanel the windowPanel to set
     * @see org.berlin.pino.win.IBasicWindow#setWindowPanel(ITeeterPanel)
     */
    public void setWindowPanel(final ITeeterPanel windowPanel) {
        this.windowPanel = windowPanel;
    }
    /**
     * @param glCanvas the glCanvas to set
     * @see org.berlin.pino.win.IBasicWindow#setGLCanvas(ITeeterGLCanvas)
     */
    public void setGLCanvas(final ITeeterGLCanvas glCanvas) {
        this.glCanvas = glCanvas;
    }
    /**
     * @param chatTextArea the chatTextArea to set
     * @see org.berlin.pino.win.IBasicWindow#setChatTextArea(ITeeterTextArea)
     */
    public void setChatTextArea(final ITeeterTextArea chatTextArea) {
        this.chatTextArea = chatTextArea;
    }
    /**
     * @param inputTextArea the inputTextArea to set
     * @see org.berlin.pino.win.IBasicWindow#setInputTextArea(ITeeterTextArea)
     */
    public void setInputTextArea(final ITeeterTextArea inputTextArea) {
        this.inputTextArea = inputTextArea;
    }
    /**
     * @param buttonPanel the buttonPanel to set
     * @see org.berlin.pino.win.IBasicWindow#setButtonPanel(ITeeterPanel)
     */
    public void setButtonPanel(final ITeeterPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }
    /**
     * @param buttonEnter the buttonEnter to set
     * @see org.berlin.pino.win.IBasicWindow#setButtonEnter(ITeeterButton)
     */
    public void setButtonEnter(final ITeeterButton buttonEnter) {
        this.buttonEnter = buttonEnter;
    }
    /**
     * @param buttonClear the buttonClear to set
     * @see org.berlin.pino.win.IBasicWindow#setButtonClear(ITeeterButton)
     */
    public void setButtonClear(final ITeeterButton buttonClear) {
        this.buttonClear = buttonClear;
    }
    /**
     * @param buttonExit the buttonExit to set
     * @see org.berlin.pino.win.IBasicWindow#setButtonExit(ITeeterButton)
     */
    public void setButtonExit(final ITeeterButton buttonExit) {
        this.buttonExit = buttonExit;
    }
    public JComponent getComponent() {
        return this.windowPanel.getComponent();
    }

    /**
     * @param layout the layout to set
     */
    public void setLayout(ITeeterLayout layout) {
        this.layout = layout;
    }

    /**
     * @return the chatTextArea
     */
    public ITeeterTextArea getChatTextArea() {
        return chatTextArea;
    }

    /**
     * @return the inputTextArea
     */
    public ITeeterTextArea getInputTextArea() {
        return inputTextArea;
    }

    /**
     * @return the buttonPanel
     */
    public ITeeterPanel getButtonPanel() {
        return buttonPanel;
    }

    /**
     * @return the layout
     */
    public ITeeterLayout getLayout() {
        return layout;
    }

    public String getText() {
        return "";
    }

    public void setText(String text) {    
        
    }

    /**
     * @return the actionHandler
     */
    public PinoAction getActionHandler() {
        return actionHandler;
    }

    /**
     * @param actionHandler the actionHandler to set
     */
    public void setActionHandler(PinoAction actionHandler) {
        this.actionHandler = actionHandler;
    }

    public void setActionHandler(ITeeterActionHandler actionHandler) {
        // TODO Auto-generated method stub
        
    }        
    
} // End of the Class //
