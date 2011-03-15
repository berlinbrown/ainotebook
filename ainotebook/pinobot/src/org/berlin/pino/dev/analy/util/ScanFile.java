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
package org.berlin.pino.dev.analy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.berlin.pino.dev.analy.StaticScanException;

/**
 */
public class ScanFile {

    public static final String EMPTY = "";

    //public static final String RMV_COMMENT_PATTERN = "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)"; 
    public static final String RMV_COMMENT_PATTERN = "_____________XXXXX_______________";
    
    /**
     * @author Berlin
     * @version $Revision: 1.0 $
     */
    private static final class SortableFileByPath implements Comparable {

        private String path;

        private final File file;

        /**
         * Constructor for SortableFileByPath.
         * 
         * @param file
         *            File
         */
        private SortableFileByPath(final File file) {
            this.file = file;
            if (this.file != null) {
                this.path = this.file.getAbsolutePath();
            } else {
                this.path = "";
            }
        }

        /**
         * Implementation Routine compareTo.
         * 
         * @param o
         *            Object
         * @return int
         */
        public int compareTo(Object o) {

            if (o instanceof File) {
                final File chkFile = (File) o;
                final String chkPath = chkFile.getAbsolutePath();
                return this.path.compareTo(chkPath);
            } else {
                return -1;
            }
        }

        /**
         * @return the file
         */
        public synchronized final File getFile() {
            return file;
        }
    } // End of class //

    // ///////////////////////////////////////////////////////////////

    /**
     * Implementation Routine getFileExt.
     * 
     * @param filename
     *            String
     * @return String
     */
    public String getFileExt(final String filename) {
        if (filename == null) {
            return EMPTY;
        }
        int i = filename.lastIndexOf(".");
        if (i == -1) {
            return EMPTY;
        }
        return filename.substring(i + 1);
    }

    /**
     * Implementation Routine getFileExt.
     * 
     * @param file
     *            File
     * @return String
     */
    public String getFileExt(final File file) {

        if (file == null) {
            return EMPTY;
        }
        return getFileExt(file.getName());
    }

    // ///////////////////////////////////////////////////////////////

    /**
     * Count number of lines.
     * 
     * @param file
     *            File
     * @return int
     */
    public int countSourceLines(final InputStream stream) {

        String line;
        BufferedReader lnreader = null;
        int lineNumber = 0;
        try {
            final InputStreamReader sreader = new InputStreamReader(stream);
            lnreader = new BufferedReader(sreader);            
            String data = EMPTY;            
            do {
                data = lnreader.readLine();
                final StaticScanString str = new StaticScanString(data);
                if (!str.isEmpty(str.toString().trim())) {                    
                    lineNumber++;
                }                
            } while (data != null);                     
            return lineNumber;

        } catch (IOException ioe) {
            throw new StaticScanException("Error when counting line numbers");
        } // End of the try - catch //
    }
    
    public int countSourceLinesRmComments(final String sourceData) {
        
        try {
            final String withOutComments = sourceData.replaceAll(RMV_COMMENT_PATTERN, "");
        } catch(Exception e) {
            System.out.println(sourceData);
        }
        return countSourceLines(new StaticScanString(sourceData).toStream());
    }
    
    public int countSourceLinesRmComments(final String sourceData, final String filename) {
        
        try {
            final String withOutComments = sourceData.replaceAll(RMV_COMMENT_PATTERN, "");
        } catch(Exception e) {
            System.out.println(sourceData);
        }
        return countSourceLines(new StaticScanString(sourceData).toStream());
    }


    
    /**
     * Count number of lines.
     * 
     * @param file
     *            File
     * @return int
     */
    public int countNumberLines(final File file) {

        String line;
        LineNumberReader lnreader = null;
        int lineNumber = 0;
        try {

            final FileReader freader = new FileReader(file);
            lnreader = new LineNumberReader(freader);
            while ((line = lnreader.readLine()) != null) {
                lineNumber = lnreader.getLineNumber();
            } // End of the While //
            return lineNumber;

        } catch (IOException ioe) {
            throw new StaticScanException("Error when counting line numbers");
        } finally {
            if (lnreader != null) {
                try {
                    lnreader.close();
                } catch (IOException e) {
                    // Igore
                }
            }
        } // End of the try - catch //

    }

    /**
     * Method countNumberLines.
     * 
     * @param stream
     *            InputStream
     * @return int
     */
    public int countNumberLines(final InputStream stream) {

        String line;
        LineNumberReader lnreader = null;
        int lineNumber = 0;
        try {
            final InputStreamReader sreader = new InputStreamReader(stream);
            lnreader = new LineNumberReader(sreader);
            while ((line = lnreader.readLine()) != null) {
                lineNumber = lnreader.getLineNumber();
            } // End of the While //
            return lineNumber;
        } catch (IOException ioe) {
            throw new StaticScanException("Error when counting line numbers");
        } finally {
            if (lnreader != null) {
                try {
                    lnreader.close();
                } catch (IOException e) {
                    // Ignore //
                }
            }
        } // End of the try - catch //
    }

    /**
     * Read the lines into a data string.
     * 
     * @param inFile
     *            File
     * @return String
     * @throws IOException
     */
    public String readLines(final File inFile) throws IOException {

        final StringBuffer resultBuffer = new StringBuffer(128);
        final FileInputStream stream    = new FileInputStream(inFile);
        final BufferedReader reader     = new BufferedReader(new InputStreamReader(stream));
        try {
            String data = EMPTY;            
            do {
                data = reader.readLine();
                if (data != null) {
                    resultBuffer.append(data).append('\n');
                }                
                
            } while (data != null);

        } finally {

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    ;
                }
            } // End of the If //

        } // End of Finally //
        return resultBuffer.toString();
    }

} // End of the Class //
