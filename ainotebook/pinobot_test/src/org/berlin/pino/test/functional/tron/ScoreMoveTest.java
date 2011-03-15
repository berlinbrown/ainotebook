package org.berlin.pino.test.functional.tron;

import org.berlin.tron.gl.game.DirectionModel;
import org.berlin.tron.gl.game.FunctionalScoreAllMoves;
import org.berlin.tron.gl.game.FunctionalScoreMoves;
import org.berlin.tron.gl.game.GLWalls;
import org.berlin.tron.gl.game.ITronBoard;
import org.berlin.tron.gl.game.ScoreMoves;
import org.berlin.tron.gl.game.TronBoard;

public class ScoreMoveTest {

    public static void test1() {
        final ITronBoard board = new TronBoard(42, 42);
        board.setVerbose(true);
        GLWalls.appWallSet(board);        
        board.printBoard();
    }
    
    public static void test2() {
        
        final ITronBoard board = new TronBoard(42, 42);
        board.setVerbose(true);
        GLWalls.appWallSet(board);
        
        int startX = 6;
        int startY = 29;
        
        final DirectionModel around = new DirectionModel(startX, startY);
        System.out.println(around);
        
        ScoreMoves score = new ScoreMoves(board, startX, startY, ITronBoard.PLAYER1);
        System.out.println(score.scoreEmptyMoves());
        //
        
        for (int j = 0; j < 30; j++) {
            for (int i = 0; i < 30; i++) {
                startX = i;
                startY = j;
                score = new ScoreMoves(board, startX, startY, ITronBoard.PLAYER1);
                System.out.println(score.scoreEmptyMovesFull() + " x=" + startX + " y=" + startY);
            }
        }
        
        /*
        for (int j = 0; j < 30; j++) {
            for (int i = 0; i < 30; i++) {
                startX = i;
                startY = j;
                score = new ScoreMoves(board, startX, startY, ITronBoard.PLAYER1);
                System.out.println(score.scoreEnemyMoves() + " x=" + startX + " y=" + startY);
            }
        }
        */
    }
    
    public static void test3() {
        
        final ITronBoard board = new TronBoard(42, 42);
        board.setVerbose(true);
        GLWalls.appWallSet(board);
        board.printBoard();
        
        int startX = 6;
        int startY = 16;
        
        for (int i = 0; i < 10; i++) {                    
            System.out.println("Starting new operation >> " + startX+i + " // " + startY);
            final FunctionalScoreMoves functionalScore = new FunctionalScoreMoves(100, board, startX + i, startY, ITronBoard.PLAYER1);
            functionalScore.setVerbose(true);
            final double x = functionalScore.scoreMoves();
            System.out.println("Operations --> " + functionalScore.getTotalOperations());
            System.out.println("Score --> " + x);
        } // End of the for //                
    }
    
    public static void test4() {
        
        final ITronBoard board = new TronBoard(42, 42);
        board.setVerbose(true);
        GLWalls.appWallSet(board);        
        
        int startX = 6;
        int startY = 16;
        
        for (int i = 0; i < 4; i++) {                    
            System.out.println("Starting new operation >> " + (startX+i) + " // " + startY);
            final FunctionalScoreMoves functionalScore = new FunctionalScoreMoves(100, board, startX + i, startY, ITronBoard.PLAYER1);
            functionalScore.setVerbose(true);
            functionalScore.setMaxOperations(200);
            final double x = functionalScore.scoreMoves();
            System.out.println("Operations --> " + functionalScore.getTotalOperations());
            System.out.println("Score --> " + x);
            System.out.println();
            System.out.println();
        } // End of the for //                
    }
            
    public static void test5() {
        
        final ITronBoard board = new TronBoard(42, 42);
        board.setVerbose(true);
        GLWalls.appWallSet(board);        
        
        int startX = 41;
        int startY = 41;
        
        for (int i = 0; i < 4; i++) {
            final FunctionalScoreAllMoves functionalScore = new FunctionalScoreAllMoves(100, board, 
                    startX + i, startY, ITronBoard.PLAYER1);
            functionalScore.setVerbose(false);
            System.out.println(functionalScore.scoreAll());            
            System.out.println("-->");
        }
        
    }
    
    public static void main(String [] args) {
        System.out.println("Running");
        test5();
    }
    
}
