package org.berlin.pino.test.test.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.berlin.pino.dev.analy.ScanDirectory;
import org.berlin.pino.dev.analy.model.ProjectSettings;
import org.berlin.pino.dev.analy.util.ScanDirWalk;
import org.berlin.pino.dev.analy.util.ScanFile;
import org.berlin.pino.dev.analy.util.StaticScanString;

public class ScanDirTest extends TestCase {

    private StringBuffer allTestData = new StringBuffer();
    private ByteArrayOutputStream activeOutputStream;
    
    protected void setUp() {
        this.activeOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.activeOutputStream));
    }
    
    protected void tearDown() {
        System.err.println("==>" + this.getName());
        System.err.println(new String(this.activeOutputStream.toByteArray()));
    }
    
    public void test1() {
        
        final ScanDirWalk scan = new ScanDirWalk();
        scan.setVerbose(false);        
        scan.printFiles(scan.walkDir(".", ScanDirWalk.IGNORE_SVN, ScanDirWalk.JAVA));        
    }
    
    public void test2() {
        
        ProjectSettings settings = new ProjectSettings(".", ".", ".", ".");
        ScanDirectory scan = new ScanDirectory(settings);
        scan.scanSource();        
        System.out.println(scan.numSourceFiles());
    }
    
    public void test3() {
        final ScanFile file = new ScanFile();
        System.out.println(file.countNumberLines(new File("/tmp/B.java")));
    }
    
    public void test4() {
        
        final String pattern = "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)";                               
        final String sourcecode = "// x \n // y \n // \n\n public static final void main() { } /* Data */ ";
        System.out.println(sourcecode.replaceAll(pattern, ""));
        
    }
    
    public void test5() {
        
        final String sourcecode = "// x \n // y \n // \n \n \n\n public static final void main() { } /* Data */ \n\n ";
        final ScanFile file = new ScanFile();
        System.out.println("ct(5): " + file.countNumberLines(new StaticScanString(sourcecode).toStream()));
        
    }
    
    public void test6() {
        
        final String sourcecode = "// x \n // y \n // \n \n \n\n private String abc = \"xxx\"; \n public static final void main() { } /* Data */ \n\n ";
        final ScanFile file = new ScanFile();
        System.out.println("ct(6): " + file.countSourceLines(new StaticScanString(sourcecode).toStream()));
        
    }
    
    public void test7() {
        
        final String sourcecode = "// x \n // y \n // \n \n \n\n public static final void main() { } /* Data */ \n\n ";
        final ScanFile file = new ScanFile();
        System.out.println("ct(7): " + file.countSourceLinesRmComments(sourcecode));
        
    }
    
    public static final void main(String [] args) throws Exception {

        TestRunner.run(ScanDirTest.class);        
        
    }
} // End of the Test Class //
