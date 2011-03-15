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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.bresearch.websec.utils.botlist.text.WordProcessor;


public class WordFrequencyTest extends TestCase {
    
    public void test1() {
     
        final String data = "   Hello my name is a person.   Hello how are you doing.  hello, this is great.  What do you think?   ";
        
        final String trim = data.trim().toLowerCase();        
        assertEquals("hello my name is a person.   hello how are you doing.  hello, this is great.  what do you think?", trim);
        
        final String data2 = " !!!   Hello my name is a person.   Hello how are you doing.  hello, this is great.  What do you think?   ";
        final String trim2 = data2.trim().toLowerCase();
        // Use the not char to replace everything but he alpha numeric chars.
        final Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        final Matcher m = pattern.matcher(trim2);
        final String res = m.replaceAll(" ");        
        final String res2 = res.trim();
        assertEquals("hello my name is a person    hello how are you doing   hello  this is great   what do you think", res2);
    }   
    
    public void test2() {
        String res2 = (new WordProcessor()).filterOnlyAlphaNumeric("   Hello my name is a person.   Hello how are you doing.  hello, this is great.  What do you think?   ");
        assertEquals("hello my name is a person    hello how are you doing   hello  this is great   what do you think", res2);
    }
    
    public static void main(String args[]) {

        TestSuite suite = new TestSuite();
        suite.addTestSuite(WordFrequencyTest.class);
        
        TestRunner.run(suite);
        return;
    }
    
}
