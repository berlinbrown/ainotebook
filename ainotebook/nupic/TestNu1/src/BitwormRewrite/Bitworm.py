#!/usr/bin/env python
"""
 Artificial Intelligence Notebook:
 Modified by Berlin Brown from the Numenta example
 See http://www.numenta.com/
 Tested with python 2.5.4
"""

from nupic.analysis import netexplorer
import random
import os

#####################################################################
# Bit Worm Data
#####################################################################
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
        input = []
        for _ in range(0, pos): input.append(self.getBit(0))
        if wormType == 'solid':
            self.categories.append(0)
            for _ in range (pos, pos+length): input.append(self.getBit(1))    
        elif wormType == 'textured':
            self.categories.append(1)
            bit = 1
            for _ in range (pos, pos+length):
                input.append(self.getBit(bit))
                bit = 1 - bit
                        
        for _ in range (pos+length, inputSize): input.append(self.getBit(0))
        self.inputs.append(input)
    
    def appendBlank(self):
        """ Append a blank vector."""
        size = self['inputSize']
        blank = []
        for _ in range(0,size): blank.append(0)
        self.inputs.append(blank)
        self.categories.append(0)
      
    def createData(self):
        size = self['inputSize']
        random.seed(self['randomSeed'])
        for _ in range(0, self['numSequencesPerBitwormType']):
            increment = 1
            for wormType in ['solid','textured']:
                length = random.randint(self['minLength'], self['maxLength'])
                pos = random.randint(0, size-length-1)
                increment = -1*increment
                for _ in range(0, self['sequenceLength']):
                    self.createBitworm(wormType, pos, length, size)
                    if pos+length >= size:
                        increment = -1*increment
                    if pos + increment < 0: increment = -1*increment
                    pos += increment
                              
                self.appendBlank()
            
        self.writeFiles()

    def writeFiles(self):
        """ Write the generated data into files."""
        # Ensure vector data and category data have the same length
        if len(self.inputs) != len(self.categories):
            raise "WARNING: Data and category vectors don't match"
        
        # write out data vectors
        try:
            os.mkdir('data')
        except:
            print "Error creating 'data' directory"
            
        dataFileName = 'data/' + self['prefix']+'data.txt'            
        dataFile = open(dataFileName, 'w')
        for input in self.inputs:
            for x in input: print >>dataFile, x,
            print >> dataFile
    
        # write out category file
        categoryFileName = 'data/' + self['prefix'] + 'categories.txt'
        catFile = open(categoryFileName, 'w')
        print "Bitworm Data Write Files, length of categories list=", len(self.categories)
        for c in self.categories: print >> catFile, c
        catFile.close()
        dataFile.close()        
        print len(self.inputs), "data vectors written to ", self['prefix']+'data.txt'
        print "INFO: category and data file closed file: dataFile=", dataFileName 

    def getBit(self, originalBit):
        """ Adds noise to originalBit (additive or bitFlip) and returns it."""
        bit = originalBit
        if random.uniform(0,1) < self['bitFlipProbability']: bit = 1 - bit
        bit += random.uniform(-self['additiveNoise'], self['additiveNoise'])        
        if bit==0 or bit==1: return int(bit)
        else: return bit

def createBitworm(filenameDataPrefix = 'my_',
                  additiveNoise = 0.0,                                                                                
                  numIterationsPerBitwormType = 10, 
                  sequenceLengthPerSession = 20,
                  inputSizeWormVec = 16,
                  minLengthWorm = 9, 
                  maxLengthWorm = 12):                  
    ''' Helper method to instantiate a bitworm data object.  After
    the object is created, invoke create data to write the test/training data to file.  '''
    
    print "Creating bitworm, numIterationsPerBitwormType=", numIterationsPerBitwormType, " seqLenPerSession=", sequenceLengthPerSession, " minWormSize=", minLengthWorm    
    
    bitwormData = BitwormData()
    bitwormData['prefix'] = filenameDataPrefix
    bitwormData['minLength'] = minLengthWorm
    bitwormData['maxLength'] = maxLengthWorm
    bitwormData['sequenceLength'] = sequenceLengthPerSession
    bitwormData['inputSize'] = inputSizeWormVec
    bitwormData['numSequencesPerBitwormType'] = numIterationsPerBitwormType
    bitwormData['additiveNoise'] = additiveNoise
    bitwormData['bitFlipProbability'] = 0.0
    return bitwormData

###################
# End of File
###################        