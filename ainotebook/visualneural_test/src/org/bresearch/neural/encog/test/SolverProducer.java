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


public class SolverProducer {

    public TestCity createCity(final TSPSettings settings) {
        
        final TestCity city;
        int xPos = (int) (Math.random() * settings.getMapSize());
        int yPos = (int) (Math.random() * settings.getMapSize());
        city = new TestCity(xPos, yPos);
        return city;
        
    }
    
    public TestCity [] buildCities(final TSPSettings settings) {
        
        final TestCity [] cities = new TestCity[settings.getCities()];
        for (int i = 0; i < cities.length; i++) {
            cities[i] = this.createCity(settings);
        } // End of the For //
        
        return cities;
    }
    
    public ISolver createTSP() {
                
        final TSPSettings settings = new TSPSettings();
        final TestCity [] cities = this.buildCities(settings);       
        final TestTSPGeneticAlgorithm genetic = new TestTSPGeneticAlgorithm(
                cities,
                settings.getPopulationSize(),
                settings.getMutationPercent(),
                settings.getPercentToMate(),
                settings.getMatingPopulationPercent(),
                settings.getCutLength());
        
        final ISolver tsp = new SolveTravelingSalesman(genetic, cities, settings);
        // Make sure to execute the solve method //
        return tsp;
        
    }
         
} // End of the Class //
