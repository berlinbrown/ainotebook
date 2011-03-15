package org.bresearch.websec.test.functional;

import java.io.File;

import opennlp.tools.lang.english.PosTagger;
import opennlp.tools.postag.POSDictionary;

import org.bresearch.websec.utils.FileUtil;

public class NLPTest {

    /*
    (defn build-model
      "Create the OpenNLP POS Tagger"
      [model-path dict-file]
      ;;;;;;;;;;;;;;;;;;;;;;;
      (let [dict (POSDictionary. dict-file true)]
        (PosTagger. model-path dict)))
        */
    
    public static void test1() throws Exception {
     
        System.out.println("Running");
        
        final String modelPath = "../socialnet/models/tag.bin.gz";
        final String dictFile = "../socialnet/models/tagdict";        
        
        final POSDictionary dict = new POSDictionary(dictFile, true);
        final PosTagger tagger = new PosTagger(modelPath, dict);
        System.out.println(tagger.tag("The dog is going to the store for some chicken"));
        System.out.println("Done");
    }
        
    public static void test2() throws Exception {
        
        final String modelPath = "../socialnet/models/tag.bin.gz";
        final String dictFile = "../socialnet/models/tagdict";
        
        FileUtil util = new FileUtil();
        String data = util.readLinesRaw(new File("./misc/data6.txt"));
        final POSDictionary dict = new POSDictionary(dictFile, true);
        final PosTagger tagger = new PosTagger(modelPath, dict);
        System.out.println(tagger.tag(data));
        System.out.println("Done");
    }
    
    public static void main(String [] args) throws Exception {
     
        test2();
    }
     
} // End of the Class //
