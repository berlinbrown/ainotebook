package org.berlin.pino.test.functional.tron;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.berlin.tron.gl.game.GLBot;
import org.berlin.tron.gl.game.Move;

public class BotMovesTest extends TestCase {

    public void test1() {
        
        final GLBot bot1 = new GLBot();
        final GLBot bot2 = new GLBot();
        bot1.makeMove(new Move(0, 0));
        bot1.makeMove(new Move(0, 1));
        
    }
    
    public static void main(String [] args) {
        
        TestRunner runner = new TestRunner();
        runner.run(BotMovesTest.class);
        
    }
    
} // End of Test //
