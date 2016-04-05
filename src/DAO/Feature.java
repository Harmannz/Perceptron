package DAO;

import java.util.Arrays;
import java.util.Random;

public class Feature {
	private Random random;// = new Random(1);//new Random(0);
	public static final int SIZE = 4;

	private int[] row;
	private int[] col;
	boolean[] signs;

	public Feature(Random random){
		this.row = new int[SIZE];
		this.col = new int[SIZE];
		this.signs = new boolean[SIZE];
		this.random = random;
		createFeatureVals();
	}

	/**
	 * Create random feature values for the rows and columns and signs.
	 * there will be 4 values for each field
	 */
	private void createFeatureVals(){
		for(int i = 0; i < SIZE; i++){
			//create random pixels for row and col
			//create random boolean for signs
			row[i] = random.nextInt(10);
			col[i] = random.nextInt(10);
			signs[i] = random.nextBoolean();
		}
	}

	/**
	 * This function is perfect example of bad programming technique
	 * Im sorry, but you gotta do what you gotta do.
	 * FIXME: should not be passing an 2D array to function like that.
	 * @param image
	 * @return
	 */
	public int getFeatureValue(boolean[][] image){
		int sum = 0;
		for(int i = 0; i < SIZE; i++){
			if(image[row[i]][col[i]] == signs[i]){
				sum++;
			}
		}

		return sum >= 3 ? 1:0;
	}

	@Override
	public String toString() {
		return "Feature [row=" + Arrays.toString(row) + ", col=" + Arrays.toString(col) + ", signs="
				+ Arrays.toString(signs) + "]";
	}



}
