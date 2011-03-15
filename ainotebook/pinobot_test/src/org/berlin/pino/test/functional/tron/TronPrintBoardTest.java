package org.berlin.pino.test.functional.tron;

import org.berlin.tron.gl.game.ITronBoard;
import org.berlin.tron.gl.game.TronBoard;

public class TronPrintBoardTest {

    public static void main(String [] args) {
        System.out.println("Running print board");
        final ITronBoard board = new TronBoard(6);
        board.initRandom();
        board.makeRandomBoard();
        board.printBoard();
        System.out.println();
    }
    
}
