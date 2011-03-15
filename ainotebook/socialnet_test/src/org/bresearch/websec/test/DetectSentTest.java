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

public class DetectSentTest extends TestCase {

    public static final String SENTS [] = {
        "I walked down to the station with them, and then wandered through the streets of the little town, finally returning to the hotel, where I lay upon the sofa and tried to interest myself in a yellow-backed novel.  But what",
        "The puny plot of the story was so thin, however, when compared to the deep mystery through which we were groping, and I found my attention wander so continually from the action to the fact, that I at last flung it across the room and gave myself up entirely to a consideration of the events of the day.  But what",
        "  It was late before Sherlock Holmes returned.  But what",
        "   He came back alone, for Lestrade was staying in lodgings in the town.  But what",
        "What do you mean at 5 p.pm. imagine that it means?  But what",
        "You have compromised yourself seriously.   But what",
        "Then I do not see the point.  But what",
        "'What steps will you take?' I asked.  But what",
        "We sat in silence for some minutes, Holmes more depressed and shaken than I had ever seen him.  But what", 
        "Isa Whitney, brother of the late Elias Whitney, D.D., Principal of the Theological College of St. George's, was much addicted to opium.  But what",          
    };
    
    public static final String SENT = "I, walked down to the station with them, and then wandered through the streets of the little town, finally returning to the hotel, where I lay upon the sofa and tried to interest myself in a yellow-backed novel.  But what?  The puny plot of the story was so thin, however, when compared to the deep mystery through which we were groping, and I found my attention wander so continually from the action to the fact, that I at last flung it across the room and gave myself up entirely to a consideration of the events of the day.  But what?  It was late before Sherlock Holmes returned.  But what?  He came back alone, for Lestrade was staying in lodgings in the town.  But what?  What do you mean at 5 p.pm. imagine that it means?  But what?  You have compromised yourself seriously.   But what?  Then I do not see the point!  But what?  'What steps will you take?' I asked.  But what?  We sat in silence for some minutes, Holmes more depressed and shaken than I had ever seen him.  But what?  Isa Whitney, brother of the late Elias Whitney, D.D., Principal of the Theological College of St. George's, was much addicted to opium.  But what?";
    
    public static final String SENT2 = "  I, walked with them, where I lay upon the sofa and tried?   I like chicken?    ";
    
    public void test1() throws Exception {
                
        // Pattern:
        // Any character other than ?, one or more times
        final String patternSent = "([A-Z][^\\.?!]+[\\.\\?!])";
        final Pattern pattern = Pattern.compile(patternSent, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(SENT);
        
        while(matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group());                                  
            System.out.println("----");
        } // End of the While //
    }
    
    public static void main(String args[]) {
        
        TestSuite suite = new TestSuite();
        suite.addTestSuite(DetectSentTest.class);
        
        TestRunner.run(suite);
        return;
    }
    
} // End of the Class //
