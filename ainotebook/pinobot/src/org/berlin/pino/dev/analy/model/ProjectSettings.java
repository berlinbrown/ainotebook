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
package org.berlin.pino.dev.analy.model;

public class ProjectSettings {

    private final String projectDir;
    private final String sourceDir;
    
    private final String testProjectDir;
    private final String testSourceDir;
    
    private boolean createTestProjectDir = true;
    private boolean createTestSrcDir = true;
    
    public ProjectSettings(final String proj, final String src, final String testProj, final String testSrc) {
        
        this.projectDir = proj;
        this.sourceDir = src;
        
        this.testProjectDir = testProj;
        this.testSourceDir = testSrc;
        
    }
    
    public String toString() {
        return "#ProjectSettings{ proj=" + this.projectDir + " test=" + this.testProjectDir +  "}";
    }

    /**
     * @return the projectDir
     */
    public String getProjectDir() {
        return projectDir;
    }

    /**
     * @return the sourceDir
     */
    public String getSourceDir() {
        return sourceDir;
    }

    /**
     * @return the testProjectDir
     */
    public String getTestProjectDir() {
        return testProjectDir;
    }

    /**
     * @return the testSourceDir
     */
    public String getTestSourceDir() {
        return testSourceDir;
    }

    /**
     * @return the createTestProjectDir
     */
    public boolean isCreateTestProjectDir() {
        return createTestProjectDir;
    }

    /**
     * @param createTestProjectDir the createTestProjectDir to set
     */
    public void setCreateTestProjectDir(boolean createTestProjectDir) {
        this.createTestProjectDir = createTestProjectDir;
    }

    /**
     * @return the createTestSrcDir
     */
    public boolean isCreateTestSrcDir() {
        return createTestSrcDir;
    }

    /**
     * @param createTestSrcDir the createTestSrcDir to set
     */
    public void setCreateTestSrcDir(boolean createTestSrcDir) {
        this.createTestSrcDir = createTestSrcDir;
    }
    
} // End of the Class //
