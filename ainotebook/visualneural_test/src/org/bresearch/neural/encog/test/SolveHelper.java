/**
 *
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
 * Date: 1/15/2009 
 *   
 * Home Page: http://botnode.com/
 * 
 * Neural Network Development
 *  
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */
package org.bresearch.neural.encog.test;

import org.encog.examples.nonlinear.tsp.genetic.TSPGeneticAlgorithm;

/**
 * TSP Collaborator.
 * 
 * @author BerlinBrown
 *
 */
public class SolveHelper {    

    private StringBuffer builder = new StringBuffer(100);    
    private int sameSolutionCount = 0;
    private int iteration = 1;
    private double lastSolution = Double.MAX_VALUE;
        
    private final TestTSPGeneticAlgorithm genetic;
    
    public SolveHelper(final TestTSPGeneticAlgorithm genetic) {
        this.genetic = genetic;
    }
    
    /////////////////////////////////////////////////////////////////
    
    public String toString() {
        return genetic + " it: " + this.getIteration() + " - lastSolution: " + this.lastSolution;
    }
    
    public void printIteration(final double theSolution) {
        this.getBuilder().setLength(0);            
        this.getBuilder().append("Iteration: ");
        this.getBuilder().append(this.getIteration());
        this.getBuilder().append(", Best Path Length = ");
        this.getBuilder().append(theSolution);
        System.out.println(this.getBuilder());
    }
    
    public void solve() {
        
        genetic.iteration();
        double thisSolution = genetic.getChromosome(0).getCost();
        this.printIteration(thisSolution);
        this.incIteration();
        
        // Update the same solution count //
        if (Math.abs(this.getLastSolution() - thisSolution) < 1.0) {                
            this.incSameSolutionCount();
        } else {
            this.setSameSolutionCount(0);                
        }
        
        // Update the last solution //
        this.setLastSolution(thisSolution);  
    }
    
    /**
     * @return the builder
     */
    public StringBuffer getBuilder() {
        return builder;
    }
    /**
     * @param builder the builder to set
     */
    public void setBuilder(StringBuffer builder) {
        this.builder = builder;
    }
    /**
     * @return the sameSolutionCount
     */
    public int getSameSolutionCount() {
        return sameSolutionCount;
    }
    /**
     * @param sameSolutionCount the sameSolutionCount to set
     */
    public void setSameSolutionCount(int sameSolutionCount) {
        this.sameSolutionCount = sameSolutionCount;
    }
    
    public void incSameSolutionCount() {
        this.sameSolutionCount++;        
    }
    
    /**
     * @return the iteration
     */
    public int getIteration() {
        return iteration;
    }
    /**
     * @param iteration the iteration to set
     */
    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public void incIteration() {
        this.iteration++;
    }

    
    /**
     * @return the lastSolution
     */
    public double getLastSolution() {
        return lastSolution;
    }
    /**
     * @param lastSolution the lastSolution to set
     */
    public void setLastSolution(double lastSolution) {
        this.lastSolution = lastSolution;
    }
        
    
} // End of the Class //
