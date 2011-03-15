#!/usr/bin/env python
#
# Modified by Berlin Brown from Numenta example

# In the example below
# The default configuration includes 20 rows, each row represents a 16 bit vector
# Input size represents the input vector, the max size for a bitworm?

from nupic.network.helpers import  \
    AddSensor, AddClassifierNode,  \
    AddZeta1Level, TrainBasicNetwork, RunBasicNetwork

from nupic.network import CreateRuntimeNetwork, Network
from nupic.analysis import InferenceAnalysis, responses

from nupic.analysis import netexplorer
import random
import sys

#####################################################################
# Global Parameters 
#####################################################################

maxDistance        = 0.0
topNeighbors       = 3
transitionMemory   = 4

numSequencesPerBitwormType = 10  # No. of sequences for each bitworm type
sequenceLength             = 20  # No. of patterns in each temporal sequence

trainingFile       = "training_data.txt"        # Location of training data
trainingCategories = "training_categories.txt"  # Location of training categories
trainingResults    = "training_results.txt"     # File containing inference results for each training pattern

testFile           = "test_data.txt"            # Location of test data
testCategories     = "test_categories.txt"      # Location of test categories
testResults        = "test_results.txt"         # File containing inference

class BitwormData(netexplorer.DataInterface):
      
    def __init__(self):
        """ Initialize parameters to default values."""
        netexplorer.DataInterface.__init__(self)        
        self.addParam('inputSize', default=16) 
        self.addParam('numSequencesPerBitwormType', default=10)
        self.addParam('sequenceLength', default=20)
        self.addParam('minLength', default=5)
        self.addParam('maxLength', default=8)
        self.addParam('randomSeed', default=41)
        self.addParam('additiveNoise', default=0.0)
        self.addParam('bitFlipProbability', default=0.0)        
        self.inputs = []
        self.categories = []  # will be 0 for solid, 1 for textured

    def createBitworm(self, wormType, pos, length, inputSize):
        """ Create a single bitworm of the given type, position, and length.
        Stores the data in self.inputs and the category in self.categories."""
        
        print "CREATING BIT WORM: wormType==", wormType, " POS==", pos, " LENGTH==", length, " INPUTSIZE=", inputSize
        
        input = []
        for _ in range(0, pos):
            input.append(self.getBit(0))
        
        if wormType == 'solid':
            self.categories.append(0)
            for _ in range (pos, pos+length):
                input.append(self.getBit(1))                    
        elif wormType == 'textured':
            self.categories.append(1)
            bit = 1
            for _ in range (pos, pos+length):
                input.append(self.getBit(bit))
                bit = 1 - bit
                        
        for _ in range (pos+length, inputSize):
            input.append(self.getBit(0))
                    
        self.inputs.append(input)        

    def createData(self):
        """ Generate the data using the current set of parameters
        and write them out to files. For each sequence, this code
        generates a bitworm using a random length and position (within the current
        parameter constraints), and then slides it left and right."""
        
        size = self['inputSize']
        random.seed(self['randomSeed'])          
        for myI in range(0, self['numSequencesPerBitwormType']):
            increment = 1
            print "Index for seqs per bitworm type : ", myI
            
            for wormType in ['solid','textured']:                
                length = random.randint(self['minLength'], self['maxLength'])                
                pos = random.randint(0, size - length - 1)
                increment = -1 * increment
                
                for _ in range(0, self['sequenceLength']):
                    self.createBitworm(wormType, pos, length, size)
                    if (pos + length) >= size:
                        increment = -1 * increment
                    if (pos + increment) < 0:
                        increment = -1 * increment
                    pos += increment
                 
                # End loop, for worm type
                self.appendBlank()
        
        # Write data to files
        self.writeFiles()
                
    def getBit(self, originalBit):
        """ Adds noise to originalBit (additive or bitFlip) and returns it."""
        bit = originalBit
        if random.uniform(0,1) < self['bitFlipProbability']: bit = 1 - bit
        bit += random.uniform(-self['additiveNoise'], self['additiveNoise'])        
        if bit==0 or bit==1: return int(bit)
        else: return bit
        
    def appendBlank(self):
        """ Append a blank vector."""
        size = self['inputSize']
        blank = []
        for _ in range(0,size): blank.append(0)
        self.inputs.append(blank)
        self.categories.append(0)
        
    def writeFiles(self):
        """ Write the generated data into files."""
        # Ensure vector data and category data have the same length
        if len(self.inputs) != len(self.categories):
            raise "Data and category vectors don't match" 
               
        # write out data vectors    
        dataFile = open(self['prefix'] + 'data.txt', 'w')
        for input in self.inputs:
            for x in input: print >>dataFile, x,
            print >> dataFile
            
        # write out category file
        catFile = open(self['prefix']+'categories.txt', 'w')
        for c in self.categories:
            print >> catFile, c
        catFile.close()
        dataFile.close()        
        print len(self.inputs), "data vectors written to ", (self['prefix'] + 'data.txt')

def generateBitwormData(additiveNoiseTraining = 0.0, 
                        bitFlipProbabilityTraining = 0.0,
                        additiveNoiseTesting = 0.0, 
                        bitFlipProbabilityTesting = 0.0,
                        numSequencesPerBitwormType = 10, 
                        sequenceLength = 20,
                        inputSize = 16,
                        trainingMinLength = 9, 
                        trainingMaxLength = 12,
                        testMinLength = 5, 
                        testMaxLength = 8):
    
    # Generate training data with worms of lengths between 5 and 8
    trainingData = BitwormData()
    trainingData['prefix'] = 'training_'
    trainingData['minLength'] = trainingMinLength
    trainingData['maxLength'] = trainingMaxLength
    trainingData['sequenceLength'] = sequenceLength
    trainingData['inputSize'] = inputSize
    trainingData['numSequencesPerBitwormType'] = numSequencesPerBitwormType
    trainingData['additiveNoise'] = additiveNoiseTraining
    trainingData['bitFlipProbability'] = bitFlipProbabilityTraining
    trainingData.createData()
    
    print "Training Data"
    print trainingData
    
    # Generate test data containing different worms, with lengths between 9 and 12
    testData = BitwormData()
    testData['prefix'] = 'test_'
    testData['minLength'] = testMinLength
    testData['maxLength'] = testMaxLength
    testData['numSequencesPerBitwormType'] = numSequencesPerBitwormType
    testData['randomSeed'] = trainingData['randomSeed'] + 1
    testData['additiveNoise'] = additiveNoiseTesting
    testData['bitFlipProbability'] = bitFlipProbabilityTesting
    testData.createData()
            
    print "Test Data"
    print testData
    
         
#####################################################################
# Main 
#####################################################################
if __name__ == '__main__':        
    generateBitwormData(additiveNoiseTraining           = 0.0,
                            bitFlipProbabilityTraining  = 0.0,
                            additiveNoiseTesting        = 0.0,
                            bitFlipProbabilityTesting   = 0.0,
                            numSequencesPerBitwormType  = numSequencesPerBitwormType,
                            sequenceLength              = sequenceLength,
                            inputSize                   = 16,
                            trainingMinLength           = 9,
                            trainingMaxLength           = 12,
                            testMinLength               = 5,
                            testMaxLength               = 8)
  
    bitNet = Network()    
    print "Bit Net : ", bitNet    
    AddSensor(bitNet, featureVectorLength = 16)
    AddZeta1Level(bitNet, numNodes = 1)
    AddClassifierNode(bitNet, numCategories = 2)
    print "Bit Net (after adding sensors): ", bitNet
    
    bitNet['level1'].setParameter('topNeighbors', topNeighbors)
    bitNet['level1'].setParameter('maxDistance', maxDistance)
    bitNet['level1'].setParameter('transitionMemory', transitionMemory)
    bitNet['topNode'].setParameter('spatialPoolerAlgorithm','dot')
    print "Bit Net (after level1): ", bitNet
    
    # Train the network
    # TrainBasicNetwork: This function trains a basic network with the given data and category files and returns the trained network
    bitNet = TrainBasicNetwork(bitNet,
                               dataFiles = [trainingFile],
                               categoryFiles = [trainingCategories])
    print "Bit Net (TrainBasicNetwork-1): ", bitNet
    
    # RunBasicNetwork: Runs the network using the given data files. The output of the classifier
    # node for each input pattern is stored in resultsFile.
    accuracy = RunBasicNetwork(bitNet,
                dataFiles     = [trainingFile],
                categoryFiles = [trainingCategories],
                resultsFile   = trainingResults)
    print "Bit Net (RunBasicNetwork-2): ", bitNet
    print "Training set accuracy with HTM[a] = ", accuracy * 100.0
            
    # RunBasicNetwork: Runs the network using the given data files. The output of the classifier
    # node for each input pattern is stored in resultsFile.
    # Run inference on test set to check generalization
    accuracy2 = RunBasicNetwork(bitNet,
                dataFiles     = [testFile],
                categoryFiles = [testCategories],
                resultsFile   = testResults)
    print "Bit Net (RunBasicNetwork-3): ", bitNet
    print "Test set accuracy with HTM[b] = ", accuracy2 * 100.0
    
###################
# End of File
###################      