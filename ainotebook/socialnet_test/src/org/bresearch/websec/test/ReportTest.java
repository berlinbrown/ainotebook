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

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.bresearch.websec.test.nontest.ConstDoc;
import org.bresearch.websec.test.nontest.ConstJavaDoc;
import org.bresearch.websec.utils.botlist.BotlistDocument;
import org.bresearch.websec.utils.botlist.report.IReport;
import org.bresearch.websec.utils.botlist.report.ReportDocument;
import org.bresearch.websec.utils.botlist.report.ReportModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ReportTest extends TestCase {
    
    public void test1() {
        
        BotlistDocument doc = new BotlistDocument(ConstDoc.CONST);
        
        IReport report = new ReportDocument(doc, true);
        System.out.println();
        System.out.println(report.toReport());
    }

    public void test2() {
                
        BotlistDocument doc = new BotlistDocument("My name is bob jones bob bob bob");        
        ReportDocument report = new ReportDocument(doc, true);
        
        System.out.println();
        System.out.println("My name is bob jones bob bob bob");
        System.out.println(report.toReport());
    }

    public void test3() {
        
        BotlistDocument doc = new BotlistDocument(ConstJavaDoc.JAVA);        
        IReport report = new ReportDocument(doc, true);
        System.out.println();
        System.out.println(report.toReport());
    }
    
    public void test4() {
        
        final Injector injector = Guice.createInjector(new ReportModule(ConstJavaDoc.JAVA, true));             
        IReport report = injector.getInstance(ReportDocument.class);
        System.out.println();
        System.out.println(report.toReport());
    }
    
    
    public static void main(String args[]) {
        
        TestSuite suite = new TestSuite();
        suite.addTestSuite(ReportTest.class);        
        TestRunner.run(suite);
        return;
    }
    
} // End of the Class //
