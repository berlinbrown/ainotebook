package org.berlin.pino.test.functional.tron;

import org.berlin.tron.gl.game.ChallengeGame;
import org.berlin.tron.gl.game.ChallengeMoveModel;
import org.berlin.tron.gl.game.IChallengeGame;
import org.berlin.tron.gl.game.Move;

public class BotTest {

    private static final IChallengeGame game = new ChallengeGame();
    
    public static void makeMove(int x, int y) {
        
        game.checkInit(50, 50);                          
        
        game.checkInitPlayerPos(new Move(x, y));
        final int oppx = 20;
        final int oppy = 20;

        final Move makeMoveModel = new ChallengeMoveModel(x, y);            
        game.addChallengeMove(makeMoveModel);
        game.addCurrentMove(new Move(x, y));
        game.addOtherMove(new Move(oppx, oppy));
        final String lastMove = game.makeLogicMove();
        
        System.out.println(lastMove);
    }
    
    public static void main(String [] args) {
        
        makeMove(0, 1);
        makeMove(0, 2);
        makeMove(0, 3);
        makeMove(0, 4);
        makeMove(0, 5);
        
    }
    
}
