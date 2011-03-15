package org.bresearch.websec.test.functional;

import java.io.File;

import org.bresearch.websec.parse.IParseDocument;
import org.bresearch.websec.parse.ParseDocument;
import org.bresearch.websec.parse.ParseDocumentModule;
import org.bresearch.websec.utils.FileUtil;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class NLPFile {

    public static void main(String [] args) throws Exception {
        System.out.println("Loading file");
        
        FileUtil util = new FileUtil();
        String data = util.readLinesRaw(new File("./misc/oz.txt"));        
        
        final String modelPath = "../socialnet/models/tag.bin.gz";
        final String dictFile = "../socialnet/models/tagdict";
        
        final Injector injector = Guice.createInjector(new ParseDocumentModule(data, modelPath, dictFile));
        final IParseDocument reportTerms = injector.getInstance(ParseDocument.class);
        reportTerms.parse();
        
    }
    
} // End of the Class //
