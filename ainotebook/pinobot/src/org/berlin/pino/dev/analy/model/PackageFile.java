package org.berlin.pino.dev.analy.model;

import java.io.File;

public class PackageFile {

    private final String srcPath;
    private final File file;
    
    public PackageFile(final String srcPath, final File file) {
        
        this.srcPath = srcPath;
        this.file = file;
    }
    
    public String toSourceFilePath() {
        
        final File srcFile = new File(this.srcPath);
        final String srcFullPath = srcFile.getAbsolutePath() + "\\";
        final String filePath = this.file.getAbsolutePath();  
        if (filePath.startsWith(srcFullPath)) {
            return filePath.substring(srcFullPath.length());
        }
        return srcFullPath;
    }
    
    public String toPackage() {
        
        final String relFilePath = this.toSourceFilePath();
        final String fileName = this.file.getName();
        if (fileName.equalsIgnoreCase(relFilePath)) {
            return "";
        }
        
        // Continue with a file that has a package
        final int lenName = fileName.length();
        final int lenPath = relFilePath.length();        
        final String path = relFilePath.substring(0, lenPath - lenName-1);
        final String packageName = path.replaceAll("\\\\", ".");        
        return packageName;
    }
    
} // End of the Class //
