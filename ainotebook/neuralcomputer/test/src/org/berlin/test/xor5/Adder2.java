package org.berlin.test.xor5;

import org.encog.engine.network.flat.FlatNetwork;
import org.encog.engine.network.train.prop.TrainFlatNetworkResilient;
import org.encog.neural.data.NeuralDataPair;
import org.encog.neural.data.NeuralDataSet;
import org.encog.neural.data.basic.BasicNeuralDataSet;
import org.encog.util.logging.Logging;

public class Adder2 {
	
	public static final int MAX = 1000;
	
	public static double INPUT [][] = { 
			{ 0.0, 0.0, 0.0 },
			{ 0.0, 1.0, 0.0 }, 
			{ 1.0, 0.0, 0.0 }, 
			{ 1.0, 1.0, 0.0 },
			{ 0.0, 0.0, 1.0 }, 
			{ 0.0, 1.0, 1.0 }, 
			{ 1.0, 0.0, 1.0 },
			{ 1.0, 1.0, 1.0 } };

	public static double IDEAL [][] = { 
			{ 0.0, 0.0 }, 
			{ 0.0, 1.0 },
			{ 0.0, 1.0 }, 
			{ 1.0, 0.0 }, 
			{ 0.0, 1.0 }, 
			{ 1.0, 0.0 },
			{ 1.0, 0.0 }, 
			{ 1.0, 1.0 }, 
	};

	public static void main(final String args[]) {

		Logging.stopConsoleLogging();

		// Args: (final int input, final int hidden1, final int hidden2, final int output, final boolean tanh)
		FlatNetwork network = new FlatNetwork(3, 6, 0, 2, false);
		network.randomize();

		NeuralDataSet trainingSet = new BasicNeuralDataSet(INPUT, IDEAL);
		TrainFlatNetworkResilient train = new TrainFlatNetworkResilient(network, trainingSet);

		int epoch = 1;
		do {
			train.iteration();
			System.out.println("Epoch #" + epoch + " Error:" + train.getError());
			epoch++;			
		} while ((epoch < MAX) && (train.getError() > 0.001));

		double[] output = new double[2];
		// Test the neural network
		System.out.println("Neural Network Results:");
		for (final NeuralDataPair pair : trainingSet) {
			double [] input = pair.getInput().getData();
			network.compute(input, output);
			System.out.println(input[0] + "," + input[1] + "," + input[2] + ":" + output[0] + "," + output[1]);
		} // End of the for 
	}
	
} // End of the class 
