package org.berlin.tron.gl.game;

import java.util.TimerTask;

public class UpdateStateTask extends TimerTask {

    private final GLRenderBoard glRenderBoard;
    
    private int taskDelayMs  = 150;
    private int taskPeriodMs = 150;
    
    private long lastRunStart = 0;
    private long lastRunEnd = 0;
    
    public UpdateStateTask(final GLRenderBoard board) {
        this.glRenderBoard = board;
        this.lastRunStart = System.currentTimeMillis();        
    }
    
    @Override
    public void run() {
        
        if (this.glRenderBoard != null) {
            
            final ITronBoard basicBoard = new TronBoard(glRenderBoard.getBoard().getNumCols(), glRenderBoard.getBoard().getNumRows()); 
            basicBoard.makeRandomBoard();           
            glRenderBoard.setBoard(basicBoard);
            
            this.lastRunEnd = System.currentTimeMillis();
            final long diff = this.lastRunEnd - this.lastRunStart;
            System.err.println("Task Updated - " + diff);
            this.lastRunStart = this.lastRunEnd;
            
        } // End of the If //
    }

    /**
     * @return the taskDelayMs
     */
    public int getTaskDelayMs() {
        return taskDelayMs;
    }

    /**
     * @param taskDelayMs the taskDelayMs to set
     */
    public void setTaskDelayMs(int taskDelayMs) {
        this.taskDelayMs = taskDelayMs;
    }

    /**
     * @return the taskPeriodMs
     */
    public int getTaskPeriodMs() {
        return taskPeriodMs;
    }

    /**
     * @param taskPeriodMs the taskPeriodMs to set
     */
    public void setTaskPeriodMs(int taskPeriodMs) {
        this.taskPeriodMs = taskPeriodMs;
    }

    /**
     * @return the glRenderBoard
     */
    public GLRenderBoard getGlRenderBoard() {
        return glRenderBoard;
    }

} // End of the Class //
