package org.berlin.pino.dev.analy.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StaticScanString {
    
    public static final String EMPTY = "";
    
    private final String internStr;
    
    public StaticScanString() {
        this.internStr = EMPTY;
    }
    
    public StaticScanString(final String str) {
        this.internStr = str == null ? EMPTY : str;
    }
    
    public String toString() {
        return this.internStr;
    }
    
    public String trim() {        
        return this.internStr.trim(); 
    }
    
    /**
     * Check for empty string.
     * 
     * @param s String
     * @return boolean
     */
    public boolean isEmpty(final String s) {        
        return (s == null) ? true : (s.length() == 0);        
    }
    
    public boolean isEmpty() {        
        return this.isEmpty(this.internStr);        
    }
    
    public InputStream toStream() {
        return new ByteArrayInputStream(this.internStr.getBytes());        
    }
    
    public InputStream toStream(final String str) {
        if (str == null) {
            return new ByteArrayInputStream(EMPTY.getBytes());
        }
        return new ByteArrayInputStream(str.getBytes());
    }
    
} // End of the class //
