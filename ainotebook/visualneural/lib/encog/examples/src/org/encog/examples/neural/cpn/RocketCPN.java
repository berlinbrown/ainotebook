package org.encog.examples.neural.cpn;

import org.encog.neural.activation.ActivationCompetitive;
import org.encog.neural.activation.ActivationLinear;
import org.encog.neural.data.NeuralData;
import org.encog.neural.data.NeuralDataSet;
import org.encog.neural.data.basic.BasicNeuralData;
import org.encog.neural.data.basic.BasicNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.layers.Layer;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.cpn.TrainInstar;
import org.encog.neural.networks.training.cpn.TrainOutstar;
import org.encog.neural.pattern.CPNPattern;

public class RocketCPN {
	
	public static final int WIDTH = 11;
	public static final int HEIGHT = 11;

	public static final String[][] PATTERN1 =  { { 
		"           ",
        "           ",
        "     O     ",
        "     O     ",
        "    OOO    ",
        "    OOO    ",
        "    OOO    ",
        "   OOOOO   ",
        "   OOOOO   ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "        O  ",
        "       O   ",
        "     OOO   ",
        "    OOO    ",
        "   OOO     ",
        " OOOOO     ",
        "OOOOO      ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "           ",
        "  OO       ",
        "  OOOOO    ",
        "  OOOOOOO  ",
        "  OOOOO    ",
        "  OO       ",
        "           ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "OOOOO      ",
        " OOOOO     ",
        "   OOO     ",
        "    OOO    ",
        "     OOO   ",
        "       O   ",
        "        O  ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "   OOOOO   ",
        "   OOOOO   ",
        "    OOO    ",
        "    OOO    ",
        "    OOO    ",
        "     O     ",
        "     O     ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "      OOOOO",
        "     OOOOO ",
        "     OOO   ",
        "    OOO    ",
        "   OOO     ",
        "   O       ",
        "  O        ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "           ",
        "       OO  ",
        "    OOOOO  ",
        "  OOOOOOO  ",
        "    OOOOO  ",
        "       OO  ",
        "           ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "  O        ",
        "   O       ",
        "   OOO     ",
        "    OOO    ",
        "     OOO   ",
        "     OOOOO ",
        "      OOOOO",
        "           ",
        "           "  } };

	String[][] PATTERN2 = { { 
		"           ",
        "           ",
        "     O     ",
        "     O     ",
        "     O     ",
        "    OOO    ",
        "    OOO    ",
        "    OOO    ",
        "   OOOOO   ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "     O     ",
        "     O     ",
        "    O O    ",
        "    O O    ",
        "    O O    ",
        "   O   O   ",
        "   O   O   ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "           ",
        "     O     ",
        "    OOO    ",
        "    OOO    ",
        "    OOO    ",
        "   OOOOO   ",
        "           ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "           ",
        "           ",
        "     O     ",
        "     O     ",
        "     O     ",
        "    OOO    ",
        "           ",
        "           ",
        "           "  },

      { "           ",
        "  O        ",
        "     O     ",
        "     O     ",
        "    OOO    ",
        "    OO     ",
        "    OOO   O",
        "    OOOO   ",
        "   OOOOO   ",
        "           ",
        "       O   "  },

      { "           ",
        "           ",
        "     O     ",
        "     O     ",
        "    OOO    ",
        "    OOO    ",
        "    OOO    ",
        "   OOOOO   ",
        "   OOOOO   ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "       O   ",
        "      O    ",
        "    OOO    ",
        "    OOO    ",
        "   OOO     ",
        "  OOOOO    ",
        " OOOOO     ",
        "           ",
        "           "  },

      { "           ",
        "           ",
        "        O  ",
        "       O   ",
        "     OOO   ",
        "    OOO    ",
        "   OOO     ",
        " OOOOO     ",
        "OOOOO      ",
        "           ",
        "           "  } };
	public static final double HI = 1;
	public static final double LO = 0;
	
	
	private double[][] input1;
	private double[][] input2;
	private double[][] ideal1;
	
	private int inputNeurons;
	private int instarNeurons;
	private int outstarNeurons;

	public void prepareInput()
	{
		int n,i,j;
		
		this.inputNeurons = WIDTH * HEIGHT;
		this.instarNeurons = PATTERN1.length;
		this.outstarNeurons = 2;
		
		this.input1 = new double[PATTERN1.length][this.inputNeurons];
		this.input2 = new double[PATTERN2.length][this.inputNeurons];
		this.ideal1 = new double[PATTERN1.length][this.instarNeurons];

		  for (n=0; n<PATTERN1.length; n++) {
		    for (i=0; i<HEIGHT; i++) {
		      for (j=0; j<WIDTH; j++) {
		    	input1[n][i*WIDTH+j] = (PATTERN1[n][i].charAt(j) == 'O') ? HI : LO;
		    	input2[n][i*WIDTH+j] = (PATTERN2[n][i].charAt(j) == 'O') ? HI : LO;
		      }
		    }
		  }
		  normalizeInput();
		  for (n=0; n<PATTERN1.length; n++) {
		    this.ideal1[n][0] = Math.sin(n * 0.25 * Math.PI);
		    this.ideal1[n][1] = Math.cos(n * 0.25 * Math.PI);
		  }

	}
	
	public double sqr(double x)
	{
		return x*x;
	}
	
	
	void normalizeInput()
	{
	  int  n,i;
	  double length1, length2;

	  for (n=0; n<PATTERN1.length; n++) {
	    length1 = 0;
	    length2 = 0;
	    for (i=0; i<this.inputNeurons; i++) {
	      length1  += sqr(this.input1[n][i]);
	      length2 += sqr(this.input2[n][i]);
	    }
	    length1  = Math.sqrt(length1);
	    length2 = Math.sqrt(length2);
	    
	    for (i=0; i<this.inputNeurons; i++) {
	      input1[n][i] /= length1;
	      input2[n][i] /= length2;
	    }
	  }
	}

	public BasicNetwork createNetwork()
	{		
        CPNPattern pattern = new CPNPattern();
        pattern.setInputNeurons( this.inputNeurons );
        pattern.setInstarCount( this.instarNeurons );
        pattern.setOutstarCount( this.outstarNeurons );

        BasicNetwork network = pattern.generate();
        network.reset();

        return network;
	}
	
	public void trainInstar(BasicNetwork network,NeuralDataSet training)
	{
		int epoch = 1;

		Train train = new TrainInstar(network,training,0.1);
		for(int i=0;i<20;i++) {
			train.iteration();
			System.out
					.println("Training instar, Epoch #" + epoch + ", Error: " + train.getError() );
			epoch++;
		} 
	}
	
	public void trainOutstar(BasicNetwork network,NeuralDataSet training)
	{
		int epoch = 1;

		Train train = new TrainOutstar(network,training,0.1);
		for(int i=0;i<20;i++) {
			train.iteration();
			System.out
					.println("Training outstar, Epoch #" + epoch + ", error=" + train.getError() );
			epoch++;
		} 
	}
	
	public NeuralDataSet generateTraining(double[][] input,double[][] ideal)
	{
		NeuralDataSet result = new BasicNeuralDataSet(input,ideal);
		return result;
	}
	
	public double determineAngle(NeuralData angle)
	{
		double result;

		  result = ( Math.atan2(angle.getData(0), angle.getData(1)) / Math.PI) * 180;
		  if (result < 0)
		    result += 360;

		  return result;
	}
	
	public void test(BasicNetwork network,String[][] pattern,double[][] input)
	{
		for(int i=0;i<pattern.length;i++)
		{
			NeuralData inputData = new BasicNeuralData(input[i]);
			NeuralData outputData = network.compute(inputData);
			double angle = determineAngle(outputData);
			
			// display image
			for(int j=0;j<HEIGHT;j++)
			{
				if( j==HEIGHT-1 )
					System.out.println("["+pattern[i][j]+"] -> " + ((int)angle) + " deg");
				else
					System.out.println("["+pattern[i][j]+"]");
			}
			
			System.out.println();
		}
	}
	
	public void run()
	{
		prepareInput();
		normalizeInput();
		BasicNetwork network = createNetwork();
		NeuralDataSet training = generateTraining(this.input1,this.ideal1);
		trainInstar(network,training);
		trainOutstar(network,training);
		test(network,PATTERN1,this.input1);
	}


	
	public static void main(String[] args)
	{
		RocketCPN cpn = new RocketCPN();
		cpn.run();
	}
	
}
