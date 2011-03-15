/**
 * Berlin Brown
 * Dec 15, 2006
 */
package org.bresearch.websec.basic;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 */
public class ExampleTestSuite {

    public static Test suite() {

        TestSuite suite = new TestSuite();
        suite.addTestSuite(ExampleTest.class);
        System.out.println("Number of Test Cases: " + suite.countTestCases());
        return suite;
    }

    public static void main(String args[]) {

        TestRunner.run(suite());
        return;
    }
} // End of the Class //
