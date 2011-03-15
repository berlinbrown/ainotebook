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

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.DefaultCaret;

/**
 * A <code>JTextArea</code> is a multi-line area that displays plain text. 
 * It is intended to be a lightweight component that provides source 
 * compatibility with the <code>java.awt.TextArea</code>.
 * 
 * @author  Berlin Brown
 * @version 1.1 02/02/2010
 * 
 * @see JTextPane
 * @see JEditorPane
 */
public class TeeterTextArea implements ITeeterTextArea {

    private final JTextArea textArea;
    
    /**
     * Constructor for TeeterTextArea.
     * @param textArea JTextArea
     */
    public TeeterTextArea(final JTextArea textArea) {
        this.textArea = textArea;
    }
    
    /**
     * Method getComponent.
     * @return JComponent
     * @see org.berlin.seesaw.swing.ITeeterTextArea#getComponent()
     */
    public JComponent getComponent() {
        return this.textArea;
    }

    /**
     * Returns the text contained in this <code>TextComponent</code>.
     * If the underlying document is <code>null</code>,
     * will give a <code>NullPointerException</code>.
     * 
     * @return String
     * @see org.berlin.seesaw.swing.ITeeterTextArea#getText()
     */
    public String getText() {
        synchronized(this.textArea) {
            return this.textArea.getText();
        }
    }
    
    /**
     * Sets the text of this <code>TextComponent</code>
     * to the specified text.  
     * 
     * @param text String
     * @see org.berlin.seesaw.swing.ITeeterTextArea#setText(String)
     */
    public void setText(String text) {
        synchronized(this.textArea) {
            this.textArea.setText(text);
        }
    }
    
    /**
     * Method setColumnsAndRows.
     * 
     * @param cols int
     * @param rows int
     * @see org.berlin.seesaw.swing.ITeeterTextArea#setColumnsAndRows(int, int)
     */
    public void setColumnsAndRows(final int cols, final int rows) {
        this.textArea.setColumns(cols);
        this.textArea.setRows(rows);
    }
    
    /**
     * Method setLineWrap.
     * @param lineWrap boolean
     * @see org.berlin.seesaw.swing.ITeeterTextArea#setLineWrap(boolean)
     */
    public void setLineWrap(final boolean lineWrap) {
        this.textArea.setLineWrap(lineWrap);
    }
    
    /**
     * Method setCaretPosition.
     * @param pos int
     * @see org.berlin.seesaw.swing.ITeeterTextArea#setCaretPosition(int)
     */
    public void setCaretPosition(final int pos) {
        this.textArea.setCaretPosition(pos);
    }
    
    /**
     * Method setEditable.
     * @param b boolean
     * @see org.berlin.seesaw.swing.ITeeterTextArea#setEditable(boolean)
     */
    public void setEditable(final boolean b) {
        this.textArea.setEditable(b);
    }
    
    /**
     * Method defaultSettings.
     * @see org.berlin.seesaw.swing.ITeeterTextArea#defaultSettings()
     */
    public void defaultSettings() {    
        
        this.setColumnsAndRows(70, 30);
        this.setLineWrap(false);
        this.setCaretPosition(0);
        this.setEditable(true);
        
    }
    
} // End of the Class //
