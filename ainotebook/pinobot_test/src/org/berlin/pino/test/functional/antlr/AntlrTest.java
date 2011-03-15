package org.berlin.pino.test.functional.antlr;

import java.io.ByteArrayInputStream;

import org.berlin.pino.dev.analy.metric.antlr.ClassPathFactory;
import org.berlin.pino.dev.analy.metric.antlr.JavaSrcRepository;

public class AntlrTest {
    
    public static void main(String [] args) throws Exception {
        
        System.out.println("Running");
        final ClassPathFactory factory = new ClassPathFactory();
        final JavaSrcRepository src = new JavaSrcRepository(null,
                factory.createFromPath(".")
                );
        
        ByteArrayInputStream stream = new ByteArrayInputStream("\n public class A { \n private String x; \n public String getX() {} \n public void setX(String n) { } }".getBytes());
        src.parse(stream, "FakeMap");
        
        System.out.println(src.getClasses());
        System.out.println(src.getClasses().get("A").getInterfaces());
        System.out.println(src.getClasses().get("A").getFields());
        System.out.println(src.getClasses().get("A").getSetters());
        
        System.out.println("Done");
    }
    
} // End of the Class //

