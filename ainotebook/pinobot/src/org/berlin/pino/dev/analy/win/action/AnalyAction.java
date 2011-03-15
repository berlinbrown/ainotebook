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
package org.berlin.pino.dev.analy.win.action;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.berlin.pino.dev.analy.ScanDirectory;
import org.berlin.pino.dev.analy.model.ProjectFileType;
import org.berlin.pino.dev.analy.model.ProjectSettings;
import org.berlin.pino.dev.analy.model.ProjectSrcStore;
import org.berlin.pino.dev.analy.model.ScanFileType;
import org.berlin.pino.dev.analy.model.TestFileType;
import org.berlin.pino.dev.analy.win.base.BasicWindow;
import org.berlin.seesaw.app.AbstractAction;
import org.berlin.seesaw.app.ITeeterWindow;

public class AnalyAction extends AbstractAction {
    
    final String proj        = "/projects/tools/home/src/ainotebook/ainotebook/ainotebook/pinobot";
    final String projsrc     = "/projects/tools/home/src/ainotebook/ainotebook/ainotebook/pinobot/src";
    final String testProj    = "/projects/tools/home/src/ainotebook/ainotebook/ainotebook/pinobot_test2";
    final String testProjsrc = "/projects/tools/home/src/ainotebook/ainotebook/ainotebook/pinobot_test2/src";
    
    private ProjectSettings settings = new ProjectSettings(proj, projsrc, testProj, testProjsrc);
    private ScanDirectory scanProject = new ScanDirectory(settings);
    
    private BasicWindow window;
    
    public AnalyAction(ITeeterWindow window) {
        super(window);
        this.window = (BasicWindow) window;
    }

    public String reportTestInfo(final ScanFileType fileType) {
        
        final StringBuilder buf = new StringBuilder(100);
        final ProjectFileType projFile = (ProjectFileType) fileType;
        if (projFile.getTestFile() != null) {
            final TestFileType testFile = (TestFileType) projFile.getTestFile();
            buf.append("\n    Test File : " + testFile.getFile().getAbsolutePath());
            buf.append("\n    Test File Exists? : " + testFile.getFile().exists());
            buf.append("\n");
        }
        return buf.toString();
    }
    
    public void onEnterSetInfoScanText() {
        
        final StringBuilder buf = new StringBuilder(100);
        final ProjectSrcStore src = this.scanProject.getSrcStore();
        
        buf.append("<<Project Source Report>> - " + new Date());       
        buf.append("\n-------------------\n");
        buf.append("    Source Dir      : [" + settings.getSourceDir()).append('\n');
        buf.append("    Test Source Dir : [" + settings.getTestSourceDir()).append('\n');
        buf.append("    Source Files    : [" + src.size()).append('\n');
        buf.append("\n-------------------\n");
                
        for (Iterator<Map.Entry<ScanFileType, ScanFileType>> it = src.getSourceStore().entrySet().iterator(); it.hasNext(); ) {
            
            final Map.Entry<ScanFileType, ScanFileType> entry = it.next();            
            final ScanFileType fileType = entry.getValue();
            buf.append("File Name: " + fileType.getFile().getName()).append('\n');
            buf.append("    Absolute Path : " + fileType.getFile().getAbsolutePath()).append('\n');
            buf.append("    Package Name : " + fileType.getPackageName()).append('\n');
            buf.append("    Number of Lines : " + fileType.getFileInfo().getNumLines()).append('\n');            
            buf.append("    Number of Lines Source: " + fileType.getFileInfo().getNumLinesSrc()).append('\n');
            buf.append(this.reportTestInfo(fileType));
            buf.append("------------------- << End File Info >>\n");            
            
        } // End of the for //
                
        this.window.getChatTextArea().setText(buf.toString());
    }
    
    public synchronized void handleOnButtonEnter() {
        
        System.out.println(this.window.getInputTextArea().getText());        
        this.window.getChatTextArea().setText((this.window.getChatTextArea().getText() + this.window.getInputTextArea().getText()) + "\n");
        this.window.getInputTextArea().setText("");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        
        System.out.println("scanning project, please stand by");
        scanProject.scanSource();
        this.onEnterSetInfoScanText();
        System.out.println("scanning project, done");
        
    }
    
    public synchronized void handleOnButtonClear() {
                       
        this.window.getChatTextArea().setText("");               
    }
        
} // End of the Class //
