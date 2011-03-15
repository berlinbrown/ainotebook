package org.bresearch.websec.test.functional;

import java.io.File;
import java.util.List;

import org.bresearch.websec.parse.IParseDocument;
import org.bresearch.websec.parse.NLPDocumentStore;
import org.bresearch.websec.parse.ParseDocument;
import org.bresearch.websec.parse.ParseDocumentModule;
import org.bresearch.websec.parse.model.Sentence;
import org.bresearch.websec.utils.FileUtil;
import org.bresearch.websec.utils.botlist.report.IReport;
import org.bresearch.websec.utils.botlist.report.ReportDocument;
import org.bresearch.websec.utils.botlist.report.ReportModule;
import org.bresearch.websec.utils.botlist.report.ReportTermsDocument;
import org.bresearch.websec.utils.botlist.report.ReportTermsModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class NLPToReport {
   
    public static List<Sentence> getSentences() throws Exception {        
        
        FileUtil util = new FileUtil();
        //final String data = util.readLinesRaw(new File("./misc/oz_small.txt"));        
        //final String data = util.readLinesRaw(new File("./misc/speech/bush2009.txt"));
        final String data = util.readLinesRaw(new File("./misc/sm3.txt"));
        
        final String modelPath = "../socialnet/models/tag.bin.gz";
        final String dictFile = "../socialnet/models/tagdict";
        
        final Injector injector = Guice.createInjector(new ParseDocumentModule(data, modelPath, dictFile));
        final IParseDocument reportTerms = injector.getInstance(ParseDocument.class);
        return reportTerms.parse();
        
    }
    
    public static String buildDocument(final List<Sentence> sentences) {
        
        final NLPDocumentStore store = new NLPDocumentStore(sentences);
        final String doc = store.buildDocument();
        store.setMinCommonSentReport(1);
        store.printStore();
        return doc;
    }
        
    public static void report(String doc) {
        String data = doc;        
        final Injector injector = Guice.createInjector(new ReportModule(data, true));             
        final IReport report = injector.getInstance(ReportDocument.class);
        
        final Injector injectorTerms = Guice.createInjector(new ReportTermsModule(data));
        final ReportTermsDocument reportTerms = injectorTerms.getInstance(ReportTermsDocument.class);        
        reportTerms.setNumTopWords(2000);        
        
        System.out.println();
        System.out.println(report.toReport());        
        //System.out.println(reportTerms.toReport());
        System.out.println("Done");

    }
    
    public static void main(String [] args) throws Exception {
        
        System.out.println("Loading file : " + args.length);
        final String doc = buildDocument(getSentences());                
        report(doc);
    }
    
} // End of the Class //
