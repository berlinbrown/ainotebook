package org.berlin.pino.dev.analy;

public interface MessageLogger {

    public void setVerbose(boolean v);
    public boolean isVerbose();
    
    public void warn(final Object msg);
    
    public void info(final Object msg);
    
    public void error(final Object msg);
            
} // End of the Interface //
