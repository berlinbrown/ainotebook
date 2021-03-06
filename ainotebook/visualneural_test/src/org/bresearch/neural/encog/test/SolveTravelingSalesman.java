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

public class SolveTravelingSalesman implements ISolver {
    
    private final TSPSettings settings;
    private final TestTSPGeneticAlgorithm genetic;
    private final TestCity cities [];

    public SolveTravelingSalesman(final TestTSPGeneticAlgorithm genetic, final TestCity [] cities, final TSPSettings settings) {
        this.genetic = genetic;
        this.cities = cities;
        this.settings = settings;
    }
    
    /**
     * Display the cities in the final path.
     */
    @Override
    public void displaySolution() {
        
        final Integer path [] = genetic.getChromosome(0).getGenes();
        for (int i = 0; i < path.length; i++) {
            if (i != 0) {
                System.out.print(" -to-> ");
            }
            System.out.print(":path(gene):" + path[i]);                                  
        } // End of the For //
        System.out.println();
        
    }

    @Override
    public void solve() {
        
        System.out.println("Initial solution found...");
        this.displaySolution();
        
        final SolveHelper solveHelper = new SolveHelper(genetic);
        while (solveHelper.getSameSolutionCount() < this.settings.getMaxSameSolution()) {            
            solveHelper.solve();
        } // End of the While //
        
        System.out.println("Good solution found:");
        this.displaySolution();
    }
    
} // End of the Class //
