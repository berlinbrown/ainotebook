package org.bresearch.websec.test.functional;

import java.io.File;

import org.bresearch.websec.utils.FileUtil;
import org.bresearch.websec.utils.botlist.report.IReport;
import org.bresearch.websec.utils.botlist.report.ReportDocument;
import org.bresearch.websec.utils.botlist.report.ReportModule;
import org.bresearch.websec.utils.botlist.report.ReportTermsDocument;
import org.bresearch.websec.utils.botlist.report.ReportTermsModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class StatsFile {

    public static void main(String [] args) throws Exception {
        System.out.println("Loading file");
        
        FileUtil util = new FileUtil();
        String data = util.readLinesRaw(new File("./misc/internet/troll1.txt"));        
        final Injector injector = Guice.createInjector(new ReportModule(data, true));             
        final IReport report = injector.getInstance(ReportDocument.class);
        
        final Injector injectorTerms = Guice.createInjector(new ReportTermsModule(data));
        final ReportTermsDocument reportTerms = injectorTerms.getInstance(ReportTermsDocument.class);        
        reportTerms.setNumTopWords(2000);        
        
        System.out.println();
        System.out.println(report.toReport());    
        System.out.println(reportTerms.toReport());
        System.out.println("Done");
    }
    
} // End of the Class //
