package org.bresearch.websec.test.parse;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.bresearch.websec.parse.IParseDocument;
import org.bresearch.websec.parse.ParseDocument;
import org.bresearch.websec.parse.ParseDocumentModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ParseDocTest extends TestCase {

    public static final String DOC = "I, walked down to the station with them, and then wandered through the streets of the little town, finally returning to the hotel, where I lay upon the sofa and tried to interest myself in a yellow-backed novel.  But what?  The puny plot of the story was so thin, however, when compared to the deep mystery through which we were groping, and I found my attention wander so continually from the action to the fact, that I at last flung it across the room and gave myself up entirely to a consideration of the events of the day.  But what?  It was late before Sherlock Holmes returned.  But what?  He came back alone, for Lestrade was staying in lodgings in the town.  But what?  What do you mean at 5 p.pm. imagine that it means?  But what?  You have compromised yourself seriously.   But what?  Then I do not see the point!  But what?  'What steps will you take?' I asked.  But what?  We sat in silence for some minutes, Holmes more depressed and shaken than I had ever seen him.  But what?  Isa Whitney, brother of the late Elias Whitney, D.D., Principal of the Theological College of St. George's, was much addicted to opium.  But what?";
    
    public void test1() {                
        
        final String modelPath = "../socialnet/models/tag.bin.gz";
        final String dictFile = "../socialnet/models/tagdict";
        
        final Injector injector = Guice.createInjector(new ParseDocumentModule(DOC, modelPath, dictFile));
        final IParseDocument reportTerms = injector.getInstance(ParseDocument.class);
        reportTerms.parse();
    }
    
    public static void main(String [] args) {
        
        TestSuite suite = new TestSuite();
        suite.addTestSuite(ParseDocTest.class);
        TestRunner.run(suite);
    }
    
} // End of the Class //

