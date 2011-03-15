/**
 *
 * Copyright (c) 2006-2007 Berlin Brown and botnode.com/Berlin Research  All Rights Reserved
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
 * Date: 12/15/2009 
 *   
 * Home Page: http://botnode.com/
 * 
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */
package org.bresearch.websec.test;

import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.bresearch.websec.test.nontest.ConstDoc;
import org.bresearch.websec.utils.botlist.BotlistStringUtils;
import org.bresearch.websec.utils.botlist.text.WordProcessor;

public class BotlistStringUtilsTest extends TestCase {

    
    public void test1() throws Exception {
      
        final String data2 = (new WordProcessor()).filterOnlyAlphaNumeric(" !!!   Hello my name is a person.   Hello how are you doing.  hello, this is great.  What do you think?   ");
               
        final BotlistStringUtils utils = new BotlistStringUtils();
        final List<String> a = utils.buildWordList(data2);
        assertEquals(27, a.size());                                
        assertEquals("[hello=3, you=2, is=2, this=1, do=1, doing=1, name=1, what=1, great=1, are=1, my=1, a=1, how=1, person=1, think=1]",                      
            "" + utils.mapReduce(a, -1));      
    }
    
    public void test2() throws Exception {
        
        final String data2 = (new WordProcessor()).filterOnlyAlphaNumeric(ConstDoc.CONST);       
        final BotlistStringUtils utils = new BotlistStringUtils();
        final List<String> a = utils.buildWordList(data2);
        assertEquals(1717, a.size());

        assertEquals("[the=133, of=88, shall=77", ("" + utils.mapReduce(a, -1)).substring(0, 25));
    }
    
    public void test3() throws Exception {
        
        final String data2 = (new WordProcessor()).filterOnlyAlphaNumeric(" !!!   Hello my name is a person.   Hello how are you doing.  hello, this is great.  What do you think?   ");
               
        final BotlistStringUtils utils = new BotlistStringUtils();
        final List<String> a = utils.buildWordList(data2);        
                        
        assertEquals("[hello=3, doing=1, name=1, great=1, person=1, think=1]", "" + utils.mapReduceWithStopWords(a, -1));
    }
    
    public void test4() throws Exception {
        
        final String data2 = (new WordProcessor()).filterOnlyAlphaNumeric(ConstDoc.CONST);       
        final BotlistStringUtils utils = new BotlistStringUtils();
        final List<String> a = utils.buildWordList(data2);
        System.out.println(utils.mapReduceWithStopWords(a, -1));
    }
    
    public static void main(String args[]) {
        
        TestSuite suite = new TestSuite();
        suite.addTestSuite(BotlistStringUtilsTest.class);        
        TestRunner.run(suite);
        return;
    }
    
} // End of the Class //
