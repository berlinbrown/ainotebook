#!/usr/bin/env python
"""
 Modified by Berlin Brown from the Numenta example
 See http://www.numenta.com/
 Tested with python 2.5.4
"""

import os

from Bitworm import createBitworm
from TrainNetwork import trainOnNetwork
from nupic.network.helpers import RunBasicNetwork

def createTrainingData():
    ''' Helper method to instantiate a bitworm data object.  After
    the object is created, invoke create data to write the test/training data to file.  '''
                                                                                
    numIterationsPerBitwormType = 10 
    sequenceLengthPerSession = 20                        
    minLengthWorm = 9
    maxLengthWorm = 12
    trainingData = createBitworm("train_", 0.0, 
                                 numIterationsPerBitwormType, sequenceLengthPerSession, 16, 
                                 minLengthWorm, maxLengthWorm)
    trainingData.createData() 
    
def createTestingData():
    ''' Helper method to instantiate a bitworm data object.  After
    the object is created, invoke create data to write the test/training data to file.  '''
                                                                                                                                                                  
    numIterationsPerBitwormType = 10 
    sequenceLengthPerSession = 20                        
    minLengthWorm = 9
    maxLengthWorm = 12
    testData = createBitworm("test_", 0.0, 
                                 numIterationsPerBitwormType, sequenceLengthPerSession, 16, 
                                 minLengthWorm, maxLengthWorm)
    testData.createData()           

def createTrainingAndTestData():
    createTrainingData()
    createTestingData()

def testNetwork(bitNetwork):
    
    pathCwd = "%s/data/" % os.getcwd()
    print "At Data directory : ", pathCwd
    
    accuracy = RunBasicNetwork(bitNetwork,
                dataFiles     = [pathCwd + 'test_data.txt'],
                categoryFiles = [pathCwd + 'test_categories.txt'],
                resultsFile   =  pathCwd + 'test_results.txt')
    print "Test set accuracy with HTM = ", (accuracy * 100.0)

####################################################################
# Run Application
#####################################################################
def runApp():
    print "Running Nupic HTM Test Program"
    print "Creating Test and Training Data Files"
    createTrainingAndTestData()
    bitNetwork = trainOnNetwork()    
    testNetwork(bitNetwork)
                
#####################################################################
# Main 
#####################################################################
if __name__ == '__main__':
    runApp()
    print "Done!"

###################
# End of File
###################