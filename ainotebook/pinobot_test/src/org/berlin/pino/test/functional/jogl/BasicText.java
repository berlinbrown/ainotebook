/**
 *
 * Copyright (c) 2006-2007 Berlin Brown and botnode.com/Berlin Research  All Rights Reserved
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
 * Date: 12/15/2009 
 *   
 * Home Page: http://botnode.com/
 * 
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */
package org.berlin.pino.test.functional.jogl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BasicText {
    
    public static JPanel buildPanel() {
        
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints constraints = new GridBagConstraints();                
                
        panel.setPreferredSize(new Dimension(600, 200));

        final Insets insets = new Insets(2, 2, 2, 2);
        constraints.insets  = insets;
        constraints.gridy   = 0;
        constraints.gridx   = 0;
        constraints.fill    = GridBagConstraints.NONE;
        constraints.anchor  = GridBagConstraints.NORTHWEST;        
        constraints.weightx = 1;
        constraints.weighty = 1;

        panel.add(new JCheckBox("abc", true), constraints);                
        constraints.gridy++;
        panel.add(new JCheckBox("abcxxxxxxx", true), constraints);        
        constraints.gridy++;
        panel.add(new JCheckBox("abc", true), constraints); 
        constraints.gridy++;
        
        // Add the textarea -> scroll pane -> to the panel -> to the jframe                                      
        return panel;
    }
    
    public static void main(String[] args) {
               
        
        JFrame frame = new JFrame("Hello World!");
        frame.setLayout(new GridBagLayout());
        final GridBagConstraints gc = new GridBagConstraints();        
        gc.anchor  = GridBagConstraints.NORTHWEST;
        gc.fill    = GridBagConstraints.NONE;
        gc.weightx = 1;
        gc.weighty = 1;
        
        frame.add(buildPanel(), gc);
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setLocation(200, 100);
        frame.setBackground(Color.white);               

        frame.pack();
        frame.setVisible(true);
    }
    
} // End of the class //
