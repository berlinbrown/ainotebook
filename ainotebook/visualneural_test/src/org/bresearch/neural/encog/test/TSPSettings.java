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

public class TSPSettings {

    public static final int    CITIES = 50;
    public static final int    POPULATION_SIZE = 1200;
    public static final double MUTATION_PERCENT = 0.1;
    public static final double PERCENT_TO_MATE = 0.24;
    public static final double MATING_POPULATION_PERCENT = 0.5;
    public static final int    CUT_LENGTH = CITIES/5;
    public static final int    MAP_SIZE = 256;
    public static final int    MAX_SAME_SOLUTION = 25;
    
    private int    cities = CITIES;
    private int    populationSize = POPULATION_SIZE;
    private double mutationPercent = MUTATION_PERCENT;
    private double percentToMate = PERCENT_TO_MATE;
    private double matingPopulationPercent = MATING_POPULATION_PERCENT;
    private int    cutLength = CUT_LENGTH;
    private int    mapSize = MAP_SIZE;
    private int    maxSameSolution = MAX_SAME_SOLUTION;
    
    /**
     * @return the cities
     */
    public int getCities() {
        return cities;
    }
    /**
     * @param cities the cities to set
     */
    public void setCities(int cities) {
        this.cities = cities;
    }
    /**
     * @return the populationSize
     */
    public int getPopulationSize() {
        return populationSize;
    }
    /**
     * @param populationSize the populationSize to set
     */
    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }
    /**
     * @return the mutationPercent
     */
    public double getMutationPercent() {
        return mutationPercent;
    }
    /**
     * @param mutationPercent the mutationPercent to set
     */
    public void setMutationPercent(double mutationPercent) {
        this.mutationPercent = mutationPercent;
    }
    /**
     * @return the percentToMate
     */
    public double getPercentToMate() {
        return percentToMate;
    }
    /**
     * @param percentToMate the percentToMate to set
     */
    public void setPercentToMate(double percentToMate) {
        this.percentToMate = percentToMate;
    }
    /**
     * @return the matingPopulationPercent
     */
    public double getMatingPopulationPercent() {
        return matingPopulationPercent;
    }
    /**
     * @param matingPopulationPercent the matingPopulationPercent to set
     */
    public void setMatingPopulationPercent(double matingPopulationPercent) {
        this.matingPopulationPercent = matingPopulationPercent;
    }
    /**
     * @return the cutLength
     */
    public int getCutLength() {
        return cutLength;
    }
    /**
     * @param cutLength the cutLength to set
     */
    public void setCutLength(int cutLength) {
        this.cutLength = cutLength;
    }
    /**
     * @return the mapSize
     */
    public int getMapSize() {
        return mapSize;
    }
    /**
     * @param mapSize the mapSize to set
     */
    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }
    /**
     * @return the maxSameSolution
     */
    public int getMaxSameSolution() {
        return maxSameSolution;
    }
    /**
     * @param maxSameSolution the maxSameSolution to set
     */
    public void setMaxSameSolution(int maxSameSolution) {
        this.maxSameSolution = maxSameSolution;
    }
        
} // End of the Class //
