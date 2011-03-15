package org.berlin.pino.dev.analy.model;

import java.io.File;

public class ProjectTestSource {

    private final String srcPackage; 
    private final String testSrcProjPath;
    private final String srcFileName;    
    
    public ProjectTestSource(final String srcFileName, final String srcPackage, final String testSrcProjPath) {
        this.srcPackage = srcPackage; 
        this.testSrcProjPath = testSrcProjPath;
        this.srcFileName = srcFileName;        
    }
    
    public String toTestSrcPath() {        
        final String path = this.srcPackage.replaceAll("\\.", "\\\\");
        return path;
    }
    
    public ScanFileType buildTestFile() {
        
        final File testProjPathFile = new File(this.testSrcProjPath);
        final File newTestFile = new File(testProjPathFile.getAbsolutePath() + "\\" + this.toTestSrcPath() + "\\test\\Test" + this.srcFileName);
        final ScanFileType testType = new TestFileType(newTestFile);
        testType.setPackageName(this.srcPackage + ".test");
        return testType;
    }
     
} // End of the Class //
