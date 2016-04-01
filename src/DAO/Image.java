package DAO;

import java.util.Arrays;

public class Image {
	private boolean classLabel;
	private int[] featureValues;

	public Image(boolean classLabel, int[] featureValues) {
		this.classLabel = classLabel;
		this.featureValues = featureValues;
	}

	public boolean getClassLabel() {
		return classLabel;
	}
	
	public void setClassLabel(boolean classLabel) {
		this.classLabel = classLabel;
	}
	
	public int[] getFeatureValues() {
		return featureValues;
	}
	
	public void setFeatureValues(int[] featureValues) {
		this.featureValues = featureValues;
	}

	@Override
	public String toString() {
		return "Image [classLabel=" + classLabel + ", featureValues=" + Arrays.toString(featureValues) + "]";
	}

	
}
