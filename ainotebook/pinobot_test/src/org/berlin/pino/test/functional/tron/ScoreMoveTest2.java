package org.berlin.pino.test.functional.tron;

import org.berlin.tron.gl.game.FunctionalScoreAllMoves;
import org.berlin.tron.gl.game.FunctionalScoreMoves;
import org.berlin.tron.gl.game.GLWalls;
import org.berlin.tron.gl.game.ITronBoard;
import org.berlin.tron.gl.game.TronBoard;

public class ScoreMoveTest2 {
    
    public static void test3() {
        
        final ITronBoard board = new TronBoard(42, 42);
        board.setVerbose(true);
        GLWalls.appWallSet(board);
        board.printBoard();
        
        int startX = 6;
        int startY = 16;
        
        for (int i = 0; i < 10; i++) {                    
            System.out.println("Starting new operation >> " + (startX+i) + " // " + startY);
            final FunctionalScoreMoves functionalScore = new FunctionalScoreMoves(100, board, startX + i, startY, ITronBoard.PLAYER1);
            functionalScore.setVerbose(true);
            final double x = functionalScore.scoreMoves();
            System.out.println("Operations --> " + functionalScore.getTotalOperations());
            System.out.println("Score --> " + x);
            
            ///
            
            final FunctionalScoreAllMoves functionalScoreALL = new FunctionalScoreAllMoves(100, board, startX + i, startY, ITronBoard.PLAYER1);
            functionalScoreALL.setVerbose(true);                        
            System.out.println("ScoreALL-- --> " + functionalScoreALL.scoreAll());
            
        } // End of the for //                
    }
    
    public static void main(String [] args) {
        System.out.println("Running");
        test3();
    }
    
}
