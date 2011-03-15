package org.berlin.pino.dev.analy;

import java.io.File;
import java.io.IOException;

import org.berlin.pino.dev.analy.model.PackageFile;
import org.berlin.pino.dev.analy.model.ProjectFileType;
import org.berlin.pino.dev.analy.model.ProjectSettings;
import org.berlin.pino.dev.analy.model.ProjectSrcStore;
import org.berlin.pino.dev.analy.model.ProjectTestSource;
import org.berlin.pino.dev.analy.model.ScanFileType;
import org.berlin.pino.dev.analy.model.TestFileType;
import org.berlin.pino.dev.analy.util.ScanFile;

public class ParseInfoFile {
    
    private final ProjectSrcStore srcStore;
    private final File file;
    private ProjectSettings settings;
    
    public ParseInfoFile(final ProjectSrcStore srcStore, final File file) {
        
        this.srcStore = srcStore;
        this.file = file;
        
    }
    
    public void parseFile() {
        
        final ScanFileType type = new ProjectFileType(file.getAbsoluteFile());
        srcStore.addFile(type);
                        
        final ScanFile scanfile = new ScanFile();
        final int numlines = scanfile.countNumberLines(this.file);
        type.getFileInfo().setNumLines(numlines);
        
        // Load the source data //               
        this.parseFileSourceData(type);  
        this.parsePackage(type);
        this.parseTestSource(type);
    }    
    
    public void parsePackage(final ScanFileType fileType) {
        
        final PackageFile parsePackage = new PackageFile(this.getSettings().getSourceDir(), fileType.getFile());
        fileType.setPackageName(parsePackage.toPackage());
        
    }
    
    public void parseTestSource(final ScanFileType fileType) {
        final ProjectTestSource testSrc = new ProjectTestSource(this.file.getName(), 
                fileType.getPackageName(), this.settings.getTestSourceDir());
        final TestFileType testSrcType = (TestFileType) testSrc.buildTestFile();        
        if (testSrcType != null) {
            final ProjectFileType projFile = (ProjectFileType) fileType;
            projFile.setTestFile(testSrcType);
            testSrcType.setProjectFile(projFile);
        } // End of the if //
    }
    
    public void parseFileSourceData(final ScanFileType fileType) {
        
        final ScanFile fileUtil = new ScanFile();
        final File file = fileType.getFile();
        
        try {
            
            final String fileData = fileUtil.readLines(file);
            final int numlinesSrc = fileUtil.countSourceLinesRmComments(fileData, file.getAbsolutePath());
            fileType.getFileInfo().setNumLinesSrc(numlinesSrc);
        } catch (IOException e) {                      
            e.printStackTrace();
            throw new StaticScanException("parseFileSourceData - error");
        } // End of try catch //
        
    }

    /**
     * @return the settings
     */
    public ProjectSettings getSettings() {
        return settings;
    }

    /**
     * @param settings the settings to set
     */
    public void setSettings(ProjectSettings settings) {
        this.settings = settings;
    }
    
} // End of the Class //
