package org.berlin.test.xor5;

import org.encog.neural.data.NeuralData;
import org.encog.neural.data.NeuralDataPair;
import org.encog.neural.data.NeuralDataSet;
import org.encog.neural.data.basic.BasicNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.CalculateScore;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.TrainingSetScore;
import org.encog.neural.networks.training.anneal.NeuralSimulatedAnnealing;
import org.encog.util.logging.Logging;

public class Adder {
	
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

		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(3));
		network.addLayer(new BasicLayer(6));
		network.addLayer(new BasicLayer(2));
		network.getStructure().finalizeStructure();
		network.reset();

		NeuralDataSet trainingSet = new BasicNeuralDataSet(INPUT, IDEAL);

		// train the neural network
		CalculateScore score = new TrainingSetScore(trainingSet);
		final Train train = new NeuralSimulatedAnnealing(network, score, 10, 2, MAX);

		int epoch = 1;
		do {
			train.iteration();
			System.out.println("Epoch #" + epoch + " Error:" + train.getError());
			epoch++;
		} while ((epoch < MAX) && (train.getError() > 0.001));

		// network = train.getNetwork();

		// test the neural network
		System.out.println("Neural Network Results:");
		for (NeuralDataPair pair : trainingSet) {
			final NeuralData output = network.compute(pair.getInput());
			System.out.println(pair.getInput().getData(0) + ","
					+ pair.getInput().getData(1) + ", actual="
					+ output.getData(0) + ","
					+ output.getData(1) + ",ideal="
					+ pair.getIdeal().getData(0) + ","
					+ pair.getIdeal().getData(1));
		}
	}
}
