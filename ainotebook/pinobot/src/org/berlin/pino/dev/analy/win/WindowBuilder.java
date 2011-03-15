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
package org.berlin.pino.dev.analy.win;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.berlin.pino.dev.analy.win.action.AnalyAction;
import org.berlin.pino.dev.analy.win.base.AbstractWindowBuilder;
import org.berlin.pino.dev.analy.win.base.BasicWindow;
import org.berlin.pino.dev.analy.win.base.IBasicWindow;
import org.berlin.seesaw.swing.BaseWorker;
import org.berlin.seesaw.swing.ITeeterButton;
import org.berlin.seesaw.swing.ITeeterEventWorker;
import org.berlin.seesaw.swing.ITeeterPanel;
import org.berlin.seesaw.swing.ITeeterTextArea;
import org.berlin.seesaw.swing.TeeterButton;
import org.berlin.seesaw.swing.layout.DefaultTeeterLayout;
import org.berlin.seesaw.swing.layout.ITeeterLayout;

public class WindowBuilder extends AbstractWindowBuilder {
     
    /**
     * Constructor for PinoWindowBuilder.
     * @param basicWindow IBasicWindow
     */
    public WindowBuilder(final IBasicWindow basicWindow) {
        super(basicWindow);
    }
    
    /**
     * Method createEnterButton.
     * @return ITeeterButton
     */
    public ITeeterButton createEnterButton() {
        
        final ITeeterEventWorker eventWorker = new BaseWorker() {
            public void execute() {
                
                final ITeeterButton button = (ITeeterButton) this.getMasterParent();                
                final AnalyAction action = (AnalyAction) button.getWindow().getActionHandler();
                action.handleOnButtonEnter();               
            }
        };        
        final ITeeterButton button = new TeeterButton(new JButton("Run Project Info"), eventWorker, this.getBasicWindow());
        button.addEventHandler();
        return button;
    }
    
    /**
     * Method createClearButton.
     * @return ITeeterButton
     */
    public ITeeterButton createClearButton() {
        
        final ITeeterEventWorker eventWorker = new BaseWorker() {
            public void execute() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("^-------------");
                System.out.println("Tested - Clear! [2]");
                System.out.println(this.getLastEvent());
                System.out.println(this.getMasterParent());
                System.out.println("v-------------");
            }
        };        
        final ITeeterButton button = new TeeterButton(new JButton("Clear"), eventWorker, this.getBasicWindow());
        button.addEventHandler();
        return button;
    }
    
    /**
     * Method createExitButton.
     * @return ITeeterButton
     */
    public ITeeterButton createExitButton() {
        
        final ITeeterEventWorker eventWorker = new BaseWorker() {
            public void execute() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Tested - Exit!");
                System.out.println(this.getLastEvent());
            }
        };        
        final ITeeterButton button = new TeeterButton(new JButton("Exit"), eventWorker, this.getBasicWindow());
        button.addEventHandler();
        return button;
    }
    
    /////////////////////////////////////////////////////////////////
    
    /**
     * Method withOutputTextArea.
     * @return PinoWindowBuilder
     */
    public WindowBuilder withOutputTextArea() {
        
        final ITeeterTextArea textArea = new OutputTextArea(new JTextArea(""));
        textArea.defaultSettings();
        ((BasicWindow) this.getBasicWindow()).setChatTextArea(textArea); 
        textArea.setText("System Loaded - Hello - " + (new Date()));
        
        return this;
    }
    
    /**
     * Method withInputCommandArea.
     * @return PinoWindowBuilder
     */
    public WindowBuilder withInputCommandArea() {
        final ITeeterTextArea textArea = new CommandInputArea(new JTextArea(""));
        textArea.defaultSettings();
        ((BasicWindow) this.getBasicWindow()).setInputTextArea(textArea);        
        return this;
    }
        
    
    /**
     * Method withButtonPanel.
     * @return PinoWindowBuilder
     */
    public WindowBuilder withButtonPanel() {
        
        final ITeeterButton enterButton = this.createEnterButton();
        final ITeeterButton clearButton = this.createClearButton();
        final ITeeterButton exitButton = this.createExitButton();
        
        final ITeeterLayout layout = new DefaultTeeterLayout();
        layout.defaultSettings();
        
        final JPanel swingPanel = new JPanel(layout.getLayout());
        final ITeeterPanel panel = new CommandButtonPanel(swingPanel, layout, enterButton, clearButton, exitButton);
        panel.constructView();
        
        // Set the components on the window //
        ((BasicWindow) this.getBasicWindow()).setButtonEnter(enterButton);
        ((BasicWindow) this.getBasicWindow()).setButtonClear(clearButton);
        ((BasicWindow) this.getBasicWindow()).setButtonExit(exitButton);
        ((BasicWindow) this.getBasicWindow()).setButtonPanel(panel);
        
        return this;
    }
    
    public WindowBuilder withTestSelectPanel() {
        
        final ITeeterLayout layout = new DefaultTeeterLayout();
        final JPanel swingPanel = new JPanel(layout.getLayout());
        final JScrollPane scrollPane = new JScrollPane(swingPanel);
        final ITeeterPanel panel = new TestSelectionPanel(swingPanel, scrollPane, layout);
        panel.constructView();
        ((BasicWindow) this.getBasicWindow()).setTestSelectPanel(panel);
        return this;
    }
    
    
    /**
     * Method withMainPanel.
     * @return PinoWindowBuilder
     */
    public WindowBuilder withMainPanel() {
        
        this.withTestSelectPanel();
        this.withOutputTextArea();
        this.withInputCommandArea();
        this.withButtonPanel();
        
        /////////////////////////////////////////
        // Create a new panel 
        // with the default layout
        /////////////////////////////////////////
        final ITeeterLayout layout = new DefaultTeeterLayout();
        layout.defaultSettings();
       
        final JPanel swingPanel = new JPanelResize(layout.getLayout());
        final ITeeterPanel panel = new AnalyPanel((IBasicWindow) this.getBasicWindow(), swingPanel, layout);
        panel.constructView();
        
        ((BasicWindow) this.getBasicWindow()).setWindowPanel(panel);
        return this;
    }
    
    /**
     * Method build.
     * @return IBasicWindow
     * @see org.berlin.seesaw.app.ITeeterWindowBuilder#build()
     */
    @Override
    public IBasicWindow build() {
        
        this.withMainPanel();
        return (IBasicWindow) this.getBasicWindow();
        
    }        

} // End of the Class //
