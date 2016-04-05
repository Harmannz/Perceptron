package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import DAO.Image;

public class Perceptron {

	private int numOfFeatures;
	private List<Image> images;
	private double[] weights;
	private static final int MAX_ITERATION = 1000;
	private static final double LEARNING_RATE = 0.025;
	private Random random;// = new Random(1);

	public Perceptron(int numOFFeatures, List<Image> images, Random random){
		this.random = random;
		this.numOfFeatures = numOFFeatures;
		this.images = new ArrayList<Image>(images);
		this.weights = new double[numOfFeatures]; //we add one feature weight at end for the dummy feature value
		initialiseWeights();
		System.out.println("Weights before: " +  Arrays.toString(weights));
	}

	private void initialiseWeights(){
		for(int i = 0; i < weights.length; i++){
			//enter seed for weights random number
			weights[i] = random.nextDouble()*0.5; //initialise the weights. 0<= x <0.5
		}
	}

	public void buildPerceptron(){
		int iteration = 0;
		int correctGuesses = 0;
		while(iteration < MAX_ITERATION){
			//present example to classifyInstance
			boolean allCorrectGuess = true;
			for (Image image : images){
				boolean result = classifyInstance(image.getFeatureValues());

				if (!image.getClassLabel() && result){
					//-ve example and wrong
					//i.e. weights are too high
					subtractFeatureVectors(image.getFeatureValues());
					allCorrectGuess = false;
				}
				else if(image.getClassLabel() && !result){
					//+ve example and wrong
					//i.e. weights are too low
					addFeatureVectors(image.getFeatureValues());
					allCorrectGuess = false;
				}
			}
			if(allCorrectGuess){
				//perceptron is correct on all training examples so we can exit loop
				break;
			}
			iteration++;
		}

		System.out.println("Algorithm finished");
		System.out.printf("Number of feature values: %d, weights: %d\n", numOfFeatures, weights.length);
		System.out.println("Iterations : " + iteration);
		System.out.println("Weights after: " +  Arrays.toString(weights));
		for (Image image : images){
			boolean result = classifyInstance(image.getFeatureValues());

			if (result && image.getClassLabel() || !result && !image.getClassLabel()){
				correctGuesses++;
			}
		}
		System.out.printf("Correctly guessed : %d/%d", correctGuesses, images.size() );
	}

	private void subtractFeatureVectors(int[] featureVectors){
		for(int i = 0; i < numOfFeatures;i++){
			weights[i] -= LEARNING_RATE * featureVectors[i];
		}
	}

	private void addFeatureVectors(int[] featureVectors){
		for(int i = 0; i < numOfFeatures;i++){
			weights[i] += LEARNING_RATE * featureVectors[i];
		}
	}
	private boolean classifyInstance(int[] featureValues){
		//perform sum of wi * fi
		double sum = -1; //-1 comes from threshold. XXX: GET CHECKED

		for(int i = 0; i < numOfFeatures; i++){
			sum += weights[i] * featureValues[i];
		}
		 if (sum > 0){
			 return true;
		 }else{
			 return false;
		 }
	}


}
