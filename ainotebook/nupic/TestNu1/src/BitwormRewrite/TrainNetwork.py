#!/usr/bin/env python

"""
 Modified by Berlin Brown from the Numenta example
 See http://www.numenta.com/
 Tested with python 2.5.4
"""

import os
from nupic.network.helpers import  \
    AddSensor, AddClassifierNode,  \
    AddZeta1Level, TrainBasicNetwork, RunBasicNetwork

from nupic.network import Network

#####################################################################
# Train
#####################################################################

def trainOnNetwork():    
    # Create the bitworm network.
    bitNet = Network()
    AddSensor(bitNet, featureVectorLength = 16)
    AddZeta1Level(bitNet, numNodes = 1)
    AddClassifierNode(bitNet, numCategories = 2)
    
    maxDistance = 0.0
    topNeighbors = 3
    transitionMemory = 4
    
    # Set some of the parameters we are interested in tuning
    bitNet['level1'].setParameter('topNeighbors', topNeighbors)
    bitNet['level1'].setParameter('maxDistance', maxDistance)
    bitNet['level1'].setParameter('transitionMemory', transitionMemory)
    bitNet['topNode'].setParameter('spatialPoolerAlgorithm','dot')    
    
    # Train the network
    pathCwd = "%s/data/" % os.getcwd()
    print "At Data directory : ", pathCwd
    
    bitNet = TrainBasicNetwork(bitNet,
                dataFiles     = [pathCwd + 'train_data.txt'],
                categoryFiles = [pathCwd + 'train_categories.txt'])
    print "Bit Net [1]: ", bitNet
    
    # Ensure the network learned the training set
    accuracy = RunBasicNetwork(bitNet,
                dataFiles     = [pathCwd + 'train_data.txt'],
                categoryFiles = [pathCwd + 'train_categories.txt'],
                resultsFile   =  pathCwd + 'train_results.txt')    
    print "Training set accuracy with HTM = ", (accuracy * 100.0)
    return bitNet

###################
# End of File
###################   