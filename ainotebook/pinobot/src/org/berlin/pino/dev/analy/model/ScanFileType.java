/**
 * Copyright (c) 2006-2010 Berlin Brown and botnode.com/Berlin Research All
 * Rights Reserved
 * 
 * http://www.opensource.org/licenses/bsd-license.php
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. * Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. * Neither the name of the Botnode.com (Berlin Brown)
 * nor the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Date: 2/15/2010
 * 
 * Home Page: http://botnode.com/
 * 
 * Description: File Unit Testing.
 * 
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */
package org.berlin.pino.dev.analy.model;

import java.io.File;

public abstract class ScanFileType {

    private final File file;
    private final long createTime;
    private BasicFileInfo fileInfo = new BasicFileInfo();
    
    private String packageName = "";
    
    public ScanFileType(final File file) {
        this.file = file;
        this.createTime = System.currentTimeMillis();
    }

    public String toString() {
        return (file == null) ? "invalid_file" : file.getName();
    }
    
    public String toReport() {
        return (file == null) ? "invalid_file" : "ScanFile : file=" 
            + file.getAbsolutePath() + " package=" + this.packageName;
    }
            
    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }
    
    public boolean exists() {
        return this.file.exists();
    }

    /**
     * @return the fileInfo
     */
    public BasicFileInfo getFileInfo() {
        return fileInfo;
    }

    /**
     * @param fileInfo the fileInfo to set
     */    
    public void setFileInfo(BasicFileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }
    
    /////////////////////////////////////////////////////////////////
    
    @Override
    public int hashCode() {

        final File file = this.file;        
        final String name = (file == null) ? "file" : file.getAbsolutePath();
        final int prime = 31;
        int result = 1;

        result = prime * result + name.hashCode();        
        result = prime * result + (int) createTime;
        return result;

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ScanFileType)) {
            return false;
        }
        final File file = ((ScanFileType) obj).getFile();
        final String name = (file == null) ? "invalid_file" : file.getAbsolutePath();
        return name.equals(obj);
    }

    /**
     * @return the packageName
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * @param packageName the packageName to set
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

} // End of the Class //
