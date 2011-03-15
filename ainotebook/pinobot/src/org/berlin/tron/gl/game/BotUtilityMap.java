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

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Utility for working with map.
 * 
 * @author Maxim Veksler
 *
 */
public class BotUtilityMap {
    
    /**
     * Sort a map by it's keys in ascending order. 
     *  
     * @return new instance of {@link LinkedHashMap} contained sorted entries of supplied map.
     * @author Maxim Veksler
     */
    public static <K, V> LinkedHashMap<K, V> sortMapByKey(final Map<K, V> map) {
            return sortMapByKey(map, SortingOrder.DESCENDING);
    }
    
    /**
     * Sort a map by it's values in ascending order.
     *  
     * @return new instance of {@link LinkedHashMap} contained sorted entries of supplied map.
     * @author Maxim Veksler
     */
    public static <K, V> LinkedHashMap<K, V> sortMapByValue(final Map<K, V> map) {
            return sortMapByValue(map, SortingOrder.DESCENDING);
    }
    
    /**
     * Sort a map by it's keys.
     *  
     * @param sortingOrder {@link SortingOrder} enum specifying requested sorting order. 
     * @return new instance of {@link LinkedHashMap} contained sorted entries of supplied map.
     * @author Maxim Veksler
     */
    public static <K, V> LinkedHashMap<K, V> sortMapByKey(final Map<K, V> map, final SortingOrder sortingOrder) {
            Comparator<Map.Entry<K, V>> comparator = new Comparator<Entry<K,V>>() {
                    public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                            return comparableCompare(o1.getKey(), o2.getKey(), sortingOrder);
                    }
            };
    
            return sortMap(map, comparator);
    }
    
    /**
     * Sort a map by it's values.
     *  
     * @param sortingOrder {@link SortingOrder} enum specifying requested sorting order. 
     * @return new instance of {@link LinkedHashMap} contained sorted entries of supplied map.
     * @author Maxim Veksler
     */
    public static <K, V> LinkedHashMap<K, V> sortMapByValue(final Map<K, V> map, final SortingOrder sortingOrder) {
            Comparator<Map.Entry<K, V>> comparator = new Comparator<Entry<K,V>>() {
                    public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                            return comparableCompare(o1.getValue(), o2.getValue(), sortingOrder);
                    }
            };
    
            return sortMap(map, comparator);
    }
    
    @SuppressWarnings("unchecked")
    private static <T> int comparableCompare(T o1, T o2, SortingOrder sortingOrder) {
            int compare = ((Comparable<T>)o1).compareTo(o2);
    
            switch (sortingOrder) {
            case ASCENDING:
                    return compare;
            case DESCENDING:
                    return (-1) * compare;
            }
    
            return 0;
    }
    
    /**
     * Sort a map by supplied comparator logic.
     *  
     * @return new instance of {@link LinkedHashMap} contained sorted entries of supplied map.
     * @author Maxim Veksler
     */
    public static <K, V> LinkedHashMap<K, V> sortMap(final Map<K, V> map, final Comparator<Map.Entry<K, V>> comparator) {
            // Convert the map into a list of key,value pairs.
            List<Map.Entry<K, V>> mapEntries = new LinkedList<Map.Entry<K, V>>(map.entrySet());
    
            // Sort the converted list according to supplied comparator.
            Collections.sort(mapEntries, comparator);
    
            // Build a new ordered map, containing the same entries as the old map.  
            LinkedHashMap<K, V> result = new LinkedHashMap<K, V>(map.size() + (map.size() / 20));
            for(Map.Entry<K, V> entry : mapEntries) {
                    // We iterate on the mapEntries list which is sorted by the comparator putting new entries into 
                    // the targeted result which is a sorted map. 
                    result.put(entry.getKey(), entry.getValue());
            }
    
            return result;
    }
    
    /**
     * Sorting order enum, specifying request result sort behavior.
     * @author Maxim Veksler
     *
     */
    public static enum SortingOrder {
            /**
             * Resulting sort will be from smaller to biggest.
             */
            ASCENDING,
            /**
             * Resulting sort will be from biggest to smallest.
             */
            DESCENDING
    }

} // End of the Class //