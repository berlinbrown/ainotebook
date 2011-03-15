package org.berlin.pino.dev.analy;

import java.io.File;
import java.util.List;

import org.berlin.pino.dev.analy.model.ProjectSettings;
import org.berlin.pino.dev.analy.model.ProjectSrcStore;
import org.berlin.pino.dev.analy.model.ScanFileType;
import org.berlin.pino.dev.analy.model.report.DirectoryReport;
import org.berlin.pino.dev.analy.util.ScanDirWalk;
import org.berlin.pino.dev.analy.util.StaticScanString;

public class ScanDirectory implements IScanDirectory, MessageLogger {

    private final ProjectSettings settings;
    private boolean verbose = true;
    private ProjectSrcStore srcStore = new ProjectSrcStore();
    
    public ScanDirectory(final ProjectSettings settings) {
        this.settings = settings;
    }
            
    /**
     * The project directory must exist and be writable.
     */
    public void validateDirectory() {
               
        final StaticScanString help = new StaticScanString(); 
        if (this.settings == null) {
            throw new StaticScanException("Invalid Settings, is null");
        }
        
        if (help.isEmpty(this.settings.getProjectDir())) {
            throw new StaticScanException("Invalid project directory setting");
        }
        
        if (help.isEmpty(this.settings.getTestProjectDir())) {
            throw new StaticScanException("Invalid project TEST directory setting");
        }
        /////////////////////////////////////////////////////////////
        
        // Check for the project directory
        final File fileProj = new File(this.settings.getProjectDir());
        if (!fileProj.exists()) {
            throw new StaticScanException("Invalid project directory, does not exist");
        }                
        if (!fileProj.canWrite()) {
            throw new StaticScanException("Invalid project directory, cannot write to directory");
        }             
        
    }
    
    public void createProjectDirs() {
        
        this.validateDirectory();
        if (this.settings.isCreateTestProjectDir()) {
            
            final File fileTest = new File(this.settings.getTestProjectDir());
            if (!fileTest.canWrite()) {
                throw new StaticScanException("Invalid project directory, cannot write to test project directory");
            } else {                
                // Create the test directory //
                final boolean res = fileTest.mkdirs();                
                this.info("INFO : ScanDirectory.createProjectDirs - creating testing dir: " + fileTest.getAbsolutePath());
                if (res) {
                    this.info("INFO : ScanDirectory.createProjectDirs - result=" + res);
                }                
            } // End of if - else //
            
            ////////////////// Check the test source //
            final File fileTestSrc = new File(this.settings.getTestProjectDir());
            if (!fileTestSrc.canWrite()) {
                throw new StaticScanException("Invalid project directory, cannot write to test SRCs project directory");
            } else {
                final boolean res = fileTestSrc.mkdirs();
                this.info("INFO : ScanDirectory.createProjectDirs - creating testing SRC dir: " + fileTestSrc.getAbsolutePath());
                if (res) {
                    this.info("INFO : ScanDirectory.createProjectDirs - result=" + res);
                }
            } // End of if - else //
            
                        
        } // End of the if  //
    }
    
    public DirectoryReport scan() {

        this.validateDirectory();                        
        return null;
    }
    
    public void scanSource() {
                
        final ScanDirWalk scan = new ScanDirWalk();
        scan.setVerbose(this.isVerbose());        
        final List<File> allFilesSrc = scan.walkDir(this.settings.getSourceDir(), 
                ScanDirWalk.IGNORE_SVN, ScanDirWalk.JAVA);
        
        for (File file : allFilesSrc) {
            
            final ParseInfoFile infoFile = new ParseInfoFile(this.srcStore, file);
            infoFile.setSettings(settings);
            infoFile.parseFile();
            
        } // End of the for //        
    }
       
    public int numSourceFiles() {
        return this.srcStore.size();
    }
    
    /////////////////////////////////////////////////////////////////
    // Utility methods //
    /////////////////////////////////////////////////////////////////
    
    public void error(Object msg) {
        if (msg == null) { return; }
        
    }

    public void info(Object msg) {
        if (msg == null) { return; }        
        
    }   

    public void warn(Object msg) {        
        if (msg == null) { return; }
        
    }
    
    public boolean isVerbose() {
        return this.verbose;
    }

    public void setVerbose(boolean v) {
        this.verbose = v;
        
    }

    /**
     * @return the srcStore
     */
    public ProjectSrcStore getSrcStore() {
        return srcStore;
    }

    /**
     * @return the settings
     */
    public ProjectSettings getSettings() {
        return settings;
    }

} // End of the Class //
