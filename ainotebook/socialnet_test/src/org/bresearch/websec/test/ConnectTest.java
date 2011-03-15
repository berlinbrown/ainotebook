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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.bresearch.websec.net.ConnectSettingsBean;
import org.bresearch.websec.net.HttpConnect;
import org.bresearch.websec.net.HttpConnectFileLoad;
import org.bresearch.websec.net.IHttpConnect;
import org.bresearch.websec.net.URLConnectAdapter;
import org.bresearch.websec.net.conf.URLLoader;

public class ConnectTest extends TestCase {

    public ConnectTest() {
        super();
    }
           
    private class MockHttpURLConnection extends HttpURLConnection {
        
        public MockHttpURLConnection(final URL u) {
            super(u);
        }
        
        @Override
        public InputStream getInputStream() {
            
            return new ByteArrayInputStream((new String("<html><body></body></html>")).getBytes());
            
        }        
        @Override
        public void disconnect() {          
        }

        @Override
        public boolean usingProxy() {
            return false;
        }

        @Override
        public void connect() throws IOException {                       
        }
        
        @Override
        public int getResponseCode() {
            return 200;
        }
        
    } // End of the Class //
    
    private URLConnectAdapter buildURLAdapter(final String url) {
        
        try {
            return (new URLConnectAdapter(new URL(url)) {
                
                public URLConnection openConnection() {
                    return new MockHttpURLConnection(this.getUrl());
                }
                                                
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    public void testConnect() {
        
        final String u = "http://www.botnode.com";
        final ConnectSettingsBean settings = new ConnectSettingsBean(u);
        final IHttpConnect connection = new HttpConnect(settings, null);
        connection.buildConnectProperties();
        connection.connect(buildURLAdapter(u));
        final String res = connection.getLastResult().getHtmlData();        
        assertEquals("<html><body></body></html>", res.trim());
        assertNotNull(connection.getLastResult());
        
    }
    
    public void testConnectLoadFile() throws Exception {
        
        final URL [] arr = new URL [] {
                new URL("http://www.botnode.com")
        };
        final String [] headers = new String [] {
                "server",
                "set-cookie"
        };
        
        final List<URL> list = Arrays.asList(arr);
        final List<String> targetHeaders = Arrays.asList(headers);
        
        final URLLoader loader = new URLLoader("scan_url_list.dat");
        loader.setUrlList(list);
        final HttpConnectFileLoad connectFile = new HttpConnectFileLoad(loader, targetHeaders);
        //connectFile.connect();                   
    }
    
    public static void main(String args[]) {
        
        TestSuite suite = new TestSuite();
        suite.addTestSuite(ConnectTest.class);
        
        TestRunner.run(suite);
        return;
    }
    
} // End of the Class //
