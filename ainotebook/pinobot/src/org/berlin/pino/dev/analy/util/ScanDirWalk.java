/********************************************************************
 *
 * Copyright (c) 2006-2007 Berlin Brown and botnode.com  All Rights Reserved
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
 * Date: 1/5/2009
 *       7/15/2009 - Added Clojure 1.0, other performance fixes and cleanups.
 *       
 * Main Description: Light Log Viewer is a tool for making it easier to search log files.  
 * Light Log Viewer adds some text highlighting, quick key navigation to text files, simple graphs 
 * and charts for monitoring logs, file database to quickly navigate to files of interest, 
 * and HTML to PDF convert tool.  
 * Light Log was developed with a combination of Clojure 1.0, Java and Scala with use of libs, SWT 3.4, JFreeChart, iText. 
 * 
 * Quickstart : the best way to run the Light Log viewer is to click on the win32 batch script light_logs.bat
 * (you may need to edit the Linux script for Unix/Linux environments).
 * Edit the win32 script to add more heap memory or other parameters.
 * 
 * The clojure source is contained in : HOME/src/octane
 * The java source is contained in :  HOME/src/java/src
 * 
 * To build the java source, see : HOME/src/java/build.xml and build_pdf_gui.xml
 * 
 * Metrics: (as of 7/15/2009) Light Log Viewer consists of 6500 lines of Clojure code, and contains wrapper code
 *  around the Java source.  There are 2000+ lines of Java code in the Java library for Light Log Viewer.
 *  
 * Additional Development Notes: The SWT gui and other libraries are launched from a dynamic classloader.  Clojure is also
 *   started from the same code, and reflection is used to dynamically initiate Clojure. See the 'start' package.  The binary
 *   code is contained in the octane_start.jar library.
 *   
 * Home Page: http://code.google.com/p/lighttexteditor/
 * 
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 *********************************************************************/

package org.berlin.pino.dev.analy.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.berlin.pino.dev.analy.MessageLogger;


/**
 * @author Berlin
 * @version $Revision: 1.0 $
 */
public class ScanDirWalk implements MessageLogger {
    
    
    private int maxDepthWalk = 32;
    private boolean verbose = false;
    
    public static final String IGNORE_SVN = "[^\\.svn]";
    public static final String JAVA       = ".+\\.java$";
    public static final String CLASS      = ".+\\.class$";
    
    
    /////////////////////////////////////////////////////////////////
    
    /**
     * @author Berlin
     * @version $Revision: 1.0 $
     */
    private static final class MatchFilenameFilter implements FilenameFilter {
        
        private final String targetRegexName; 
        private boolean forDir = false;
        
        /**
         * Constructor for MergeSubDirFilter.
         * @param regex String
         */
        public MatchFilenameFilter(final String regex) { 
            this.targetRegexName = regex; 
        }
                
        /**
         * Implementation Routine accept.
         * @param dir File
         * @param name String
         * @return boolean
         * @see java.io.FilenameFilter#accept(File, String)
         */
        public boolean accept(final File dir, final String name) {
            
            if ((new StaticScanString(this.targetRegexName).isEmpty())) {
                return true;
            }            
            final Pattern targetPattern = Pattern.compile(targetRegexName);
            final Matcher matchTarget = targetPattern.matcher(name);
            final boolean targFind = matchTarget.find();            
            return targFind;
            
        }

        /**
         * @return the forDir
         */
        public boolean isForDir() {
            return forDir;
        }

        /**
         * @param forDir the forDir to set
         * @return MatchFilenameFilter
         */
        public MatchFilenameFilter setForDir(boolean forDir) {
            this.forDir = forDir;
            return this;
        }        
        
    } // End of the class //
    
   
    /////////////////////////////////////////////////////////////////
    
    /**
     * Implementation Routine getMergeFiles.
     * 
     * @param parentDir String
     * @param dirRegex String
     * @param fileFilterRegex String
     * @return File[]
     */
    public Pair<String, File []> listFiles(final String parentDir, final String dirRegex, final String fileFilterRegex) {        
        
        final StaticScanString str = new StaticScanString();
        
        // Always attempt to return a zero length array on error.
        if (str.isEmpty(parentDir)) {
            return new Pair<String, File []>(parentDir, new File[0]);
        }        
        final List<File> listFiles = new ArrayList<File>();
        
        // Given the parent directory.
        final File parentDirFile = new File(parentDir);        
        if (parentDirFile.exists()) {
            return this.listFilesCheckDir(parentDirFile, listFiles, parentDir, dirRegex, fileFilterRegex);
        } // End of the if - parent dir //
        
        // Return empty array
        return new Pair<String, File []>(parentDir, new File[0]);
    }
    
    /**
     * Method listFilesCheckDir.
     * 
     * @param parentDirFile File
     * @param listFiles List<File>
     * @param parentDir String
     * @param dirRegex String
     * @param fileFilterRegex String
     * @return Pair<String,File[]>
     */
    public Pair<String, File []> listFilesCheckDir(final File parentDirFile, final List<File> listFiles, final String parentDir, final String dirRegex, final String fileFilterRegex) {
                        
        final MatchFilenameFilter filter = new MatchFilenameFilter(dirRegex);
        filter.setForDir(true);
        
        // Add the current directory //
        this.listFilesAtDir(listFiles, new File(parentDir), fileFilterRegex, true);        
        final File [] parentSubDirs = parentDirFile.listFiles(filter);            
        for (int i = 0; i < parentSubDirs.length; i++) {
                        
            if (parentSubDirs[i].isDirectory()) {                
                this.listFilesAtDir(listFiles, parentSubDirs[i], fileFilterRegex, false);                    
            } // End of the if // is directory //
            
        } // End of the for // - outer  //
        
        // After if exists check, determine if we return merged files //
        final File [] res = (File []) listFiles.toArray(new File [listFiles.size()]);
        if (res != null && (res.length > 0)) {
            return new Pair<String, File []>(parentDir, res);
        }
        
        return new Pair<String, File []>(parentDir, new File[0]);
    }
    
    /**
     * Method listFilesAtDir.
     * @param listFiles List<File>
     * @param parentSubDir File
     * @param fileFilterRegex String
     * @param isOnlyFile boolean
     */
    public void listFilesAtDir(final List<File> listFiles, final File parentSubDir, final String fileFilterRegex, final boolean isOnlyFile) {
        
        // If directory found, then attempt to find the files of interest
        final FilenameFilter filenameFilter = new MatchFilenameFilter(fileFilterRegex);
        final File [] targetFiles = parentSubDir.listFiles(filenameFilter);
        for (int z = 0; z < targetFiles.length; z++) {
            if (targetFiles[z].isFile()) {
                listFiles.add(targetFiles[z]);
            }             
        } // End of For on Files //
        
        if (isOnlyFile) {
            // Do not continue
            return;
        }
        
        // We also need to include the directories in the list //        
        final File [] targetDirs = parentSubDir.listFiles();
        for (int z = 0; z < targetDirs.length; z++) {
            if (targetDirs[z].isDirectory()) {
                listFiles.add(targetDirs[z]);
            }             
        } // End of For on Files //               
        
    }
    
    /**
     * Method walkDir.
     * @param depth int
     * @param fileResultSet List<File>
     * @param parentDir String
     * @param dirRegex String
     * @param fileFilterRegex String
     */
    public void walkDir(final int depth, final List<File> fileResultSet, final String parentDir, 
            final String dirRegex, final String fileFilterRegex) {
        
        if (depth > this.getMaxDepthWalk()) {
            return;
        }
        
        final Pair<String, File []> tupleRes = this.listFiles(parentDir, dirRegex, fileFilterRegex);
        if (tupleRes == null) {
            return;
        }
                        
        final File [] filesForDir = tupleRes.getSecond();
        for (int i = 0; i < filesForDir.length; i++) {
            
            if (filesForDir[i].isDirectory()) {
                final MatchFilenameFilter matchForDir = new MatchFilenameFilter(dirRegex);
                if (matchForDir.accept(filesForDir[i], filesForDir[i].getName())) {                    
                    walkDir(depth + 1, fileResultSet, filesForDir[i].getAbsolutePath(), dirRegex, fileFilterRegex);
                } // End of the if //
            } else {                
                fileResultSet.add(filesForDir[i]);
            } // End of the if - else //
            
        } // End of the for //
        
    }

    /**
     * Method walkDir.
     * @param parentDir String
     * @param dirRegex String
     * @param fileFilterRegex String
     * @return List<File>
     */
    public List<File> walkDir(final String parentDir, final String dirRegex, final String fileFilterRegex) {
        
        final List<File> files = new ArrayList<File>();
        this.walkDir(0, files, parentDir, dirRegex, fileFilterRegex);        
        return files;
    }
    
    /**
     * Method printFiles.
     * @param files List<File>
     */
    public void printFiles(List<File> files) {
        for (File file : files) {
            this.info(file.getAbsoluteFile());
        }
    }
    
    /**
     * @return the maxDepthWalk
     */
    public int getMaxDepthWalk() {
        return maxDepthWalk;
    }

    /**
     * @param maxDepthWalk the maxDepthWalk to set
     */
    public void setMaxDepthWalk(int maxDepthWalk) {
        this.maxDepthWalk = maxDepthWalk;
    }

    /**
     * Method error.
     * @param msg Object
     * @see org.berlin.pino.dev.analy.MessageLogger#error(Object)
     */
    public void error(Object msg) {
        // TODO Auto-generated method stub
        
    }

    /**
     * Method info.
     * @param msg Object
     * @see org.berlin.pino.dev.analy.MessageLogger#info(Object)
     */
    public void info(Object msg) {
        if (msg == null) {
            return;
        }
        System.out.println(msg);
    }

    /**
     * Method isVerbose.
     * @return boolean
     * @see org.berlin.pino.dev.analy.MessageLogger#isVerbose()
     */
    public boolean isVerbose() {
        return this.verbose;
    }

    /**
     * Method setVerbose.
     * @param v boolean
     * @see org.berlin.pino.dev.analy.MessageLogger#setVerbose(boolean)
     */
    public void setVerbose(boolean v) {
        this.verbose = v;
        
    }

    /**
     * Method trace.
     * @param msg Object
     */
    public void trace(Object msg) {
        if (!this.isVerbose()) {
            return;
        }
        System.out.println(msg);
    }
    
    /**
     * Method warn.
     * @param msg Object
     * @see org.berlin.pino.dev.analy.MessageLogger#warn(Object)
     */
    public void warn(Object msg) {
        if (!this.isVerbose()) {
            return;
        }
    }
    
} // End of the Class //
