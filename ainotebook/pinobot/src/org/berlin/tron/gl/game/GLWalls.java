/**
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
 * 
 * Simple Java OpenGL
 */
package org.berlin.tron.gl.game;

import java.util.Random;

public class GLWalls {

    private static final Random random = new Random();
    
    public static void appWallSet(final ITronBoard board) {        
        wallSetRandomMiddle(board);
        wallSetBounds(board);        
        //scenario2(board);
    }
    
    public static void wallSet1(final ITronBoard board) {
        
        board.setBoardVal(ITronBoard.WALL, 4, 3);
        board.setBoardVal(ITronBoard.WALL, 4, 3);
        board.setBoardVal(ITronBoard.WALL, 4, 4);
        board.setBoardVal(ITronBoard.WALL, 4, 5);
        board.setBoardVal(ITronBoard.WALL, 4, 6);
        board.setBoardVal(ITronBoard.WALL, 4, 7);
        board.setBoardVal(ITronBoard.WALL, 4, 8);
        
        board.setBoardVal(ITronBoard.WALL, 5, 6);
        board.setBoardVal(ITronBoard.WALL, 6, 6);
        board.setBoardVal(ITronBoard.WALL, 7, 6);
        board.setBoardVal(ITronBoard.WALL, 8, 6);        
        
    }
    
    public static void wallSet2(final ITronBoard board) {
        board.setBoardVal(ITronBoard.WALL, 4, 2);
        board.setBoardVal(ITronBoard.WALL, 4, 3);
        board.setBoardVal(ITronBoard.WALL, 4, 4);
    }
    
    public static void wallSetRandomMed(final ITronBoard board) {
     
        final int w = board.getNumCols();
        final int h = board.getNumRows();        
        
        for (int j = 3; j < (w-2); j++) {
            for (int i = 3; i < (h-2); i++) {                
                final int chk = random.nextInt(10);
                if (chk == 1) {
                    board.setBoardVal(ITronBoard.WALL, i, j);
                }                
            }
        } // End of the for //        
    }
    
    public static void wallSetRandomHigh(final ITronBoard board) {
     

        final int w = board.getNumCols();
        final int h = board.getNumRows();        
        
        for (int j = 3; j < (w-2); j++) {
            for (int i = 3; i < (h-2); i++) {                
                final int chk = random.nextInt(7);
                if (chk == 1) {
                    board.setBoardVal(ITronBoard.WALL, i, j);
                }                
            }
        } // End of the for //  
    }
    
    public static void wallSetRandomMiddle(final ITronBoard board) {       

        final int w = board.getNumCols();
        final int h = board.getNumRows();        
        
        for (int j = 6; j < (w-8); j++) {
            for (int i = 6; i < (h-8); i++) {                
                final int chk = random.nextInt(8);
                if (chk == 1) {
                    board.setBoardVal(ITronBoard.WALL, i, j);
                }                
            }
        } // End of the for //  
    }
    
    public static void wallSetBounds(final ITronBoard board) {
        final int w = board.getNumCols();
        final int h = board.getNumRows();
        
        for (int j = 4; j < (w-5); j++) {                                            
            board.setBoardVal(ITronBoard.WALL, 5, j);                                           
        } // End of the for //        
        for (int j = 6; j < (w-8); j++) {                                            
            board.setBoardVal(ITronBoard.WALL, w-5, j);                                           
        } // End of the for //
        
        for (int i = 6; i < (w-12); i++) {
            board.setBoardVal(ITronBoard.WALL, i, h-12);
        }
    }
    
    public static void randomEnemy(final ITronBoard board) {
        
        final int w = board.getNumCols();
        final int h = board.getNumRows();        
        
        for (int j = 3; j < (w-5); j++) {
            for (int i = 3; i < (h-5); i++) {                
                final int chk = random.nextInt(8);
                if (chk == 1) {
                    board.setBoardVal(ITronBoard.PLAYER2, i, j);
                }                
            }
        } // End of the for //
    }
    
    public static void scenario1(final ITronBoard board) {
      
        final int w = board.getNumCols();
        final int h = board.getNumRows();
        
        for (int j = 0; j < w; j++) {
            for (int i = 6; i < 12; i++) {
                board.setBoardVal(ITronBoard.WALL, j, i);
            }
        } // End of the for //
        
    }
    
    public static void scenario2(final ITronBoard board) {
        
        final int w = board.getNumCols();
        final int h = board.getNumRows();
        
        for (int j = 0; j < w; j++) {
            for (int i = 3; i < 12; i++) {
                board.setBoardVal(ITronBoard.WALL, j, i);
            }
        } // End of the for //
        
    }
    
} // End of the method //
