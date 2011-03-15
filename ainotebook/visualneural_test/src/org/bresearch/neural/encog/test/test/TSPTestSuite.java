/**
 * Berlin Brown
 * Dec 15, 2006
 */
package org.bresearch.neural.encog.test.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 */
public class TSPTestSuite {

    public static Test suite() {

        TestSuite suite = new TestSuite();
        suite.addTestSuite(TSPHelperTest.class);        
        return suite;
    }

    public static void main(String args[]) {
        
        TestRunner.run(suite());
        return;
    }
} // End of the Class //
