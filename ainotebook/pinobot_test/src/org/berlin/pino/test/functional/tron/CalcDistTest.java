package org.berlin.pino.test.functional.tron;

import java.util.ArrayList;
import java.util.List;

import org.berlin.tron.gl.game.CalcEnemyDistance;
import org.berlin.tron.gl.game.GLWalls;
import org.berlin.tron.gl.game.ITronBoard;
import org.berlin.tron.gl.game.Move;
import org.berlin.tron.gl.game.TronBoard;

public class CalcDistTest {

    public static void test1() {
        
        final ITronBoard board = new TronBoard(42, 42);
        board.setVerbose(true);
        GLWalls.appWallSet(board);
        
        List<Move> enemy = new ArrayList<Move>();
        enemy.add(new Move(3, 4));
        enemy.add(new Move(9, 9));
        enemy.add(new Move(3, 3));
        
        final CalcEnemyDistance calc = new CalcEnemyDistance(enemy, new Move(0, 0)); 
        System.out.println(calc.calcDistance(new Move(3, 2)));
        
        final CalcEnemyDistance calc2 = new CalcEnemyDistance(enemy, new Move(0, 0)); 
        System.out.println(calc2.calcDistanceAllMoves().get(0));
        
        final CalcEnemyDistance calc3 = new CalcEnemyDistance(enemy, new Move(0, 0)); 
        System.out.println(calc3.calcMin());
    }
    
    public static void main(String [] args) {
        System.out.println();
        test1();
    }
    
}
