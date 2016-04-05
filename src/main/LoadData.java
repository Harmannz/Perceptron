package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import DAO.Feature;
import DAO.Image;

public class LoadData {
	private static final int NUM_OF_FEATURES = 51;
	private int rows = 10;
	private int cols = 10;
	private Feature[] features = new Feature[NUM_OF_FEATURES];
	private List<Image> images = new ArrayList<Image>();
	private Random random = new Random(1);


	public void createFeatures(){
		for(int i = 0; i < NUM_OF_FEATURES - 1; i++){
			//create random features and add to list
			features[i] = new Feature(random);
			System.out.println(features[i]);
		}
	}
	/**
	 * Load all the images into array of image objects
	 */
	public void load() {
		createFeatures();
		boolean[][] newimage = null;
		try {
			java.util.regex.Pattern bit = java.util.regex.Pattern.compile("[01]");
			Scanner f = new Scanner(new File(FileDialog.open()));
			while(f.hasNext()){
				if (!f.next().equals("P1"))
					System.out.println("Not a P1 PBM file");
				String category = f.next().substring(1);
				boolean classLabel;
				if (!category.equalsIgnoreCase("yes")){
					classLabel = false;
				}else{
					classLabel = true;
				}
				rows = f.nextInt();
				cols = f.nextInt();

				newimage = new boolean[rows][cols];
				for (int r = 0; r < rows; r++) {
					for (int c = 0; c < cols; c++) {
						newimage[r][c] = (f.findWithinHorizon(bit, 0).equals("1"));
					}
				}

				int[] imageFeatureValues = new int[NUM_OF_FEATURES]; //we add extra feature value for the dummy feature
				/**
				 * for feature in the list, we get the feature value of the image and
				 * save that in the image
				 */
				for(int i = 0; i < NUM_OF_FEATURES - 1; i++){
					imageFeatureValues[i] = features[i].getFeatureValue(newimage);
				}
				imageFeatureValues[imageFeatureValues.length - 1] = 1; //add dummy feature value at end

				images.add(new Image(classLabel, imageFeatureValues));
			}
			f.close();
		} catch (IOException e) {
			System.out.println("Load from file failed");
		}

		for(Image image : images){
			System.out.println(image);
		}
	}


	public static void main(String[] args){
		LoadData ld = new LoadData();
		ld.load();
		Perceptron p = new Perceptron(NUM_OF_FEATURES, ld.images, ld.random);
		p.buildPerceptron();
	}
}
