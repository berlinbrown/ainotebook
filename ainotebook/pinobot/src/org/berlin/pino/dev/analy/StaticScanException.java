package org.berlin.pino.dev.analy;

public class StaticScanException extends RuntimeException {

    public StaticScanException(Throwable t) {
        super(t);
    }
    
    public StaticScanException(final String msg) {
        super(msg);
    }
    
} // End of the Class //
