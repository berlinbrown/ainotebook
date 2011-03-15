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
package org.bresearch.websec.test.nontest;

public class ConstJavaDoc {
    
    public static final String JAVA = new StringBuffer()
    .append("One of the exciting trends to recently emerge from the Java community is the concept of the JVM language. These technologies")
    .append("are all that you would expect them to be. They are implementations of languages that run on the Java Virtual Machine.")
    .append("Some are newly created and some are based on existing, more mature languages. JRuby, Jython are two JVM languages based on CRuby and CPython. Groovy, Scala, Clojure are three completely new JVM languages that were created to add new language features that weren't supported by the core Java language. Some must or can be compiled. Some run without compilation. You can easily compile Scala code to Java bytecode. Clojure also allows this feature (ahead of time compilation). Clojure and JRuby code can also run without having be explicitly compiled. You can interact with the language. In most cases and with most JVM languages, you have full access to existing libraries that were written in pure Java. And normally you can access existing JVM language code from Java (known as Java interoperability). In most cases, it is easier to access Java calls from the JVM language than it is to call the language code from Java. It really depends on the language. In the snippet below, there is a call to the System static method, 'nanoTime'.")
    .append("Simply invoke the system like you would from pure Java.")
    .append("For the more popular JVM languages, like JRuby and Jython, there isn't much of a difference between running code")
    .append("in their respective C implementations. JRuby is especially well known for being very portable.")
    .append("With JRuby release 1.3.1, JRuby is compatible with CRuby 1.8.6. Jython 2.5.0 was released last")
    .append("month and brings the level of compatibility to CPython versions 2.5. Django and other popular")
    .append("CPython based frameworks are able to work with Jython. You may be wondering, if the Java Virtual")
    .append("Machine language is compatible with the C language, native implementation, is there a loss in performance")
    .append("when running on the Java Virtual Machine? Is there a major loss in performance? That is this purpose")
    .append("of this document, how much time does it take for a particular piece of code to run in JVM language?")
    .append("How long does it take to run similar routines using pure Java code? I want to make it clear, you will")
    .append("not find a large scientific benchmark run under clean room like conditions. I want to present a simple")
    .append("set of routines and how long it took to run. How long did the Clojure code run? How long did the Scala")
    .append("code run? Basically, I want to present the code and how long each test ran, but I don't want to")
    .append("claim that anyone language or piece of code is faster or slower based on these tests. You could say")
    .append("that most of the pure Java code ran faster. Most of the time, that is what happened after running")
    .append("these tests. But there is too much confounding in my tests. Like Zed Shaw said, 'If you want to'")    
    .append("these an official comparison. There is a lot of confounding. But, here is the code, here is how long it")
    .append("took to run? It be relevant in more common tests like a Project Euler problem. Project Euler is a")
    .append("website that contains math problems intended to be solved with computer programs. In Project Euler")
    .append("problem number one, I write a program in Clojure and then in Java. They both run on the JVM and")
    .append("the same value is returned. What was the execution time for each program? Simple tests, simple results.")
    .append("When working with JVM languages and possible performance bottlenecks, you want to consider execution time,")
    .append("but you also want to look at the garbage collector and heap memory usage. Garbage collection is an expensive operation.")
    .append("It won't take a minute to run a garbage collect, but it will take cpu cycles away from your application.")
    .append("JVM code runs in a protected environment, the garbage collector provides automatic memory management")
    .append("and normally protects you from improper memory use. And the garbage collector attempts to free up")
    .append("memory that is no longer needed. You can't normally control when the JVM runs garbage collection")
    .append("and certainly don't want force it. But if you monitor your application, you can identify memory leaks or")
    .append("other problems that might cause performance bottlenecks. It will normally be evident where there is a problem.")
    .append("If you see too many garbage collects within a very short period of time and your total available memory is maxed out,")
    .append("you might eventually encounter an out of memory error. In a long running server environment,")
    .append("most of your performance issues might be alleviated if you look at proper heap memory use. Is your code forcing")
    .append("too many garbage collections within a short period of time? Are you creating too many large objects and")
    .append("holding on to them for too long? In performance tuning your application, there are many things to consider.")
    .append("It may not just be improving a particular algorithm. Consider heap memory and object allocation as well.")
    .append("For most of the tests, there are performance stats, memory stats and other garbage collection statistics.")
    .append("Note: With this document, I tried not to position one language as better or worse than the other.")
    .append("Each technology that I mention has advantages and disadvantages for writing software. Each tool")
    .append("may give the developer productivity gains and some developers may never get used to changing to a new syntax,")
    .append("never truly realizing some of the intended benefits that the language has to offer. You will have to")
    .append("evaluate these languages (or not) on your own and make. I merely try to point out some of the similarities")
    .append("and some of the differences.")
    .append("Lisp certainly suffers from that old adage, 'easy to learn, may take a lifetime to master'.")
    .append("It is easy to write Lisp code, it may take time to write readable, solid, idiomatic Lisp code.")
    .append("I am sure I will get many comments on how to write more idiomatic Clojure code even for these")
    .append("rudimentary examples. Luckily, Clojure has many of the functions that you will encounter with most other Lisp dialects.")
    .append("In Java or C++, you may be accustomed to the 'for loop' syntax. Here is the Clojure macro for building a list of elements.")
    .append("Why even investigate a JVM language? What is the point? A programming language is like any other tool or")
    .append("library used to create and interact with other software or hardware components? If you work in a web or J2EE environment,")
    .append("you might write SQL code, define CSS scripts, write Javascript code, write HTML/XHTML. It isn't uncommon for web application developers")
    .append("to write Java, sql, css, javascript or HTML code. Sometimes all within the same day, sometimes during different phases of a project. That doesn't take into account the number libraries that you must normally learn, understand and work with. So, NOT learning a new JVM programming language just on the premise that it is something different, isn't a valid reason for not using it. People have asked me, would you use Clojure, Java, or Scala? I have used all three for small GUI projects. I have used Scala for the the backend API where I don't need to make small changes. I used Clojure because of the dynamic nature of the language. I can make many, quick incremental changes without having any major impact on the Scala backend API. Take the Java api for example. Most of the core library is set in stone. The java.lang.String class hasn't changed much in over a decade. I see Scala being used for those type of rigid API requirements. This doesn't mean that Clojure couldn't be used for this purpose, it just means that is how I have used Scala and it just seemed to fit because of how easy it is to call Scala from Java (Java interoperability), also because of the nature of Scala's types. Here is just one example on how I used Clojure. The code snippet below contains valid Clojure code used to develop a small GUI application. If you just look at it without understanding the syntax, the code below almost looks like a general purposed configuration file. Here I can easily modify the layout of my GUI window, buttons without ever really getting any complex language details. I am just looking at the data required to change my layout.")
    .append("The quick sort example is not a good test to really push the garbage collector. Here is another test,")
    .append("one with Clojure and one with Java. I instantiate a large number of large objects and do the same for small objects.")
    .append("The garbage collector is better at handling smaller objects and consequently not as good at")
    .append("handling large objects (using the default GC rules). So, if you are looking at performance issues,")
    .append("you might look at how often large objects are being created and how long you are holding onto those objects.")
    .append("It is better to create many small objects and retain them for a short time than creating a few number")
    .append("large objects and retaining them for a long time. For example, I guess it is better to write short, static,")    
    .append("will go directly to the old generation area; they will take longer to initialize (when setting fields to their default values such as null and zero);")    
    .append("large object within a memory heap exceeds a maximum contiguous free space within the Java heap.")
    .append("With all the discussion centered around the java web-application frameworks including Struts, SpringMVC and WebWork,")
    .append("how does one interface these with Jython and why would you want to do so. Normally, you will do this the same")
    .append("way that you would in a typical standalone console application. You must find a way to invoke the Jython interpreter")
    .append("and then execute your Jython code. The same is done in a Servlet environment. This example demonstrates how to put")
    .append("together a web-application that uses the Struts MVC (model, view, controller) framework and also uses Hibernate for")
    .append("persisting our objects to the database. The JSP files make up all the of the View code and Jython is used for all")
    .append("the back-end work. The goal of the 'BotList Link Aggregator Application' is to create a web-app that stores a set")
    .append("of links associated with keywords and description and also presents an interface to delete, view, edit, and")
    .append("list the links for the user.")
    .append("The Stuts Action class contains the majority of the business logic for your web-application. In this example, the")
    .append("Jython classes are subclasses of the Action class. The one Java Action class acts as a controller; depending on")
    .append("the request from the user, this Action class invokes one of the Jython Action classes accordingly. Normally, an Action")
    .append("will just overwrite the execute method as shown in the Jython code below.")
    .append("We discussed earlier how Jython is basically used for the backend coding, that includes communicating with Hibernate.")
    .append("Here are the code snippets associated with each of those operations. Most of the code is fairly intuitive;")
    .append("at the heart of the create operation, you must get the Hibernate SessionFactory and initiate a transaction.")
    .append("Once that is done, create an instance of the Hibernate POJO bean and populate the bean with the data from the Struts ActionForm.")
    .append("Once that is taken care of, use the session and transaction object to save the data. The Edit operation probably contains")
    .append("the most code and is seperated into two Jython classes.")
    .toString();
} // End of Class //
