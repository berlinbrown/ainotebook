/**
 * Copyright (c) 2006-2010 Berlin Brown and botnode.com/Berlin Research  All Rights Reserved
 *
 * http://www.opensource.org/licenses/bsd-license.php

 * All rights reserved.

 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:

 * * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * * Neither the name of the Botnode.com (Berlin Brown) nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written permission.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Date: 1/23/2010 
 * Description: Social Networking Site Document Analysis
 * Home Page: http://botnode.com/
 * 
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */
package org.bresearch.websec.test;

import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.bresearch.websec.test.nontest.ConstDoc;
import org.bresearch.websec.utils.botlist.BotlistStringUtils;
import org.bresearch.websec.utils.botlist.stats.DocumentWordStats;
import org.bresearch.websec.utils.botlist.text.WordProcessor;

public class CommonsMathTest extends TestCase {

    
    public void test1() throws Exception {
        
        /* min, max, mean, geometric mean, n, sum, sum of squares, 
         * standard deviation, variance, percentiles, skewness, kurtosis, median */
        
        // Get a DescriptiveStatistics instance using factory method
        DescriptiveStatistics stats = new DescriptiveStatistics();

        final double [] inputArray = { 4, 3, 3, 2 };
        
        // Add the data from the array
        for (int i = 0; i < inputArray.length; i++) {
                stats.addValue(inputArray[i]);                
        }

        // Compute some statistics
        double mean = stats.getMean();
        double std = stats.getStandardDeviation();
        long n = stats.getN();        
        assertEquals("3.0", "" + mean);
        assertEquals("0.816496580927726", "" + std); 
    }
    
    public void test2() {
        final String data2 = (new WordProcessor()).filterOnlyAlphaNumeric(" !!!   Hello my name is a person.   Hello how are you doing.  hello, this is great.  What do you think?   ");
        
        final BotlistStringUtils utils = new BotlistStringUtils();
        final List<String> a = utils.buildWordList(data2);
        assertEquals(27, a.size());
        
        DescriptiveStatistics stats = new DescriptiveStatistics();
        
        for (int i = 0; i < utils.mapReduceCount(a, -1).length; i++) {
            stats.addValue(utils.mapReduceCount(a, -1)[i]);                
        }
               
        // Compute some statistics
        double mean = stats.getMean();
        double std = stats.getStandardDeviation();
        assertEquals("1.2666666666666666", "" + mean);
        assertEquals("0.5936168397046634", "" + std);
        
        long n = stats.getN();
        assertEquals("15", "" + n);
        
    }
    
    public void test3() {
        final String data2 = (new WordProcessor()).filterOnlyAlphaNumeric(" !!!   Hello my name is a person.   Hello how are you doing.  hello, this is great.  What do you think?   ");
        
        final BotlistStringUtils utils = new BotlistStringUtils();
        final List<String> a = utils.buildWordList(data2);
        
        DescriptiveStatistics stats = new DescriptiveStatistics();       
        for (int i = 0; i < utils.mapReduceWordSize(a, -1).length; i++) {
            stats.addValue(utils.mapReduceWordSize(a, -1)[i]);                
        }
               
        // Compute some statistics        
        assertEquals("3.6", "" + stats.getMean());
        assertEquals("54.0", "" + stats.getSum()); 
    }
    
    public void test4() {
        final DocumentWordStats docStats = new DocumentWordStats(ConstDoc.CONST);
        final DescriptiveStatistics stats = docStats.mapReduceStats();
        
        /*
        System.out.println("" + stats.getMean());
        System.out.println("" + stats.getN());
        System.out.println("" + stats.getGeometricMean());        
        System.out.println("" + stats.getMax());
        */
    }
    
    public void test5() {
        final DocumentWordStats docStats = new DocumentWordStats(ConstDoc.CONST_SM);
        final DescriptiveStatistics stats = docStats.mapReduceStats();
                
        System.out.println("" + stats.getSum());
        System.out.println("" + stats.getMean());
        System.out.println("" + stats.getN());
        System.out.println("" + stats.getGeometricMean());        
        System.out.println("" + stats.getMax());
        
    }
    
    public static void main(String args[]) {
        
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CommonsMathTest.class);
        
        TestRunner.run(suite);
        return;
    }
    
} // End of the Class //
