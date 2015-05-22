/*
 * Contains multiple methods for calculating the following:
 * Mean, Median, Mode, Range, Quartiles, InterQuartileRange, Outliers, Standard Deviation, and Z Score
 * Written by Kevin Zhan
 * Last Updated: 11/17/14
 */

import java.util.ArrayList;

public class Statistics {
	/*
	 * Finds the mean of an array of doubles and returns it as a double value
	 * in = inputed array of double values
	 * If in is empty, the method returns NaN (Not a Number)
	 */
	public static double mean(double[] in) {
		if (in.length > 0) {
			double total = 0;
			for (double val : in)
				total += val;
			return total / in.length;
		}
		return Double.NaN;
	}

	/*
	 * Finds the median of an array of doubles and returns it as a double value
	 * in = inputed array of double values
	 * If in is empty, the method returns NaN (Not a Number)
	 */
	public static double median(double[] in) {
		if (in.length > 0) {
			if (in.length == 1)
				return in[0];
			double[] out = mergeSort(copy(in));
			if (out.length % 2 == 0)
				return ((out[out.length / 2] + out[out.length / 2 - 1]) / 2);
			else
				return out[out.length / 2];
		}
		return Double.NaN;
	}

	/*
	 * Finds the modes of an array of doubles and returns them as an ArrayList
	 * in = inputed array of double values
	 * If there are no modes, the method returns an empty ArrayList
	 */
	public static ArrayList<Double> mode(double[] in) {
		if (in.length > 2) {
			double[] sorted = mergeSort(copy(in));
			ArrayList<Double> vals = new ArrayList<Double>();
			ArrayList<Integer> repeats = new ArrayList<Integer>();
			ArrayList<Double> modes = new ArrayList<Double>();
			int valCount = 1;
			int max = 0;
			for (int i = 0; i < sorted.length; i++) {
				if (i < sorted.length - 1 && sorted[i] == sorted[i + 1]) {
					valCount++;
				} else {
					vals.add(sorted[i]);
					repeats.add(valCount);
					valCount = 1;
				}
				if (valCount > max)
					max = valCount;
			}
			int halfCount = vals.size() / 2;
			if (vals.size() % 2 != 0)
				halfCount++;
			for (int i = 0; i < vals.size(); i++) {
				if (repeats.get(i) == max) {
					modes.add(vals.get(i));
					halfCount--;
				}
			}
			if (halfCount > 0)
				return modes;
		}
		return new ArrayList<Double>();
	}

	/*
	 * Finds the range of an array of doubles and returns it as a double
	 * in = inputed array of double values
	 * If in is empty, the method returns NaN (Not a Number) 
	 */
	public static double range(double[] in) {
		if (in.length > 0)
			return (getMax(in) - getMin(in));
		return Double.NaN;
	}

	/*
	 * Finds the upper quartile of an array of doubles and returns it as a double
	 * in = inputed array of double values
	 * If in has less than 2 values, the method returns NaN (Not a Number) 
	 */
	public static double upperQuartile(double[] in) {
		if (in.length > 1) {
			double[] copy = mergeSort(copy(in));
			int middle;
			if (copy.length % 2 == 0)
				middle = copy.length / 2;
			else
				middle = copy.length / 2 + 1;
			double[] upper = copy(copy, middle, copy.length - 1);
			return median(upper);
		}
		return Double.NaN;
	}

	/*
	 * Finds the lower quartile of an array of doubles and returns it as a double
	 * in = inputed array of double values
	 * If in has less than 2 values, the method returns NaN (Not a Number) 
	 */
	public static double lowerQuartile(double[] in) {
		if (in.length > 1) {
			double[] copy = mergeSort(copy(in));
			int middle = copy.length / 2 - 1;
			double[] lower = copy(copy, 0, middle);
			return median(lower);
		}
		return Double.NaN;
	}

	/*
	 * Finds the interquartile range of an array of doubles and returns it as a double
	 * in = inputed array of double values
	 * If in has less than 2 values, the method returns NaN (Not a Number) 
	 */
	public static double interQuartileRange(double[] in) {
		if (in.length > 1)
			return upperQuartile(in) - lowerQuartile(in);
		return Double.NaN;
	}

	/*
	 * Finds the outliers of an array of doubles and returns them as an ArrayList
	 * in = inputed array of double values
	 * If there are no outliers, the method returns an empty ArrayList
	 */
	public static ArrayList<Double> outliers(double[] in) {
		if (in.length > 1) {
			ArrayList<Double> out = new ArrayList<Double>();
			double low = lowerQuartile(in);
			double high = upperQuartile(in);
			double inRange = high - low;
			for (double val : in)
				if (val < low - 1.5 * inRange || val > high + 1.5 * inRange)
					out.add(val);
			return out;
		}
		return new ArrayList<Double>();
	}

	/*
	 * Finds the standard deviation of an array of doubles and returns it as a double
	 * in = inputed array of double values
	 * If in is empty, the method returns NaN (Not a Number) 
	 */
	public static double standardDeviation(double[] in) {
		if (in.length > 0) {
			double[] copy = copy(in);
			double mean = mean(copy);
			double total = 0;
			for (int i = 0; i < copy.length; i++)
				copy[i] = (copy[i] - mean) * (copy[i] - mean);
			for (double val : copy)
				total += val;
			total /= (copy.length - 1);
			return Math.sqrt(total);
		}
		return Double.NaN;
	}

	/*
	 * Finds the Z score of an array of doubles and returns it as a double
	 * in = inputed array of double values
	 * data = sample data value in double form
	 * If data is not a value from in or in is empty, the method returns NaN (Not a Number)
	 */
	public static double zScore(double[] in, double data) {
		boolean isData = false;
		for (double val : in)
			if (val == data) {
				isData = true;
				break;
			}
		if (in.length > 0 && isData) {
			return (data - mean(in)) / standardDeviation(in);
		}
		return Double.NaN;
	}

	/*
	 * Makes a copy of an array of doubles
	 * in = array of double values
	 */
	private static double[] copy(double[] in) {
		double[] out = new double[in.length];
		for (int i = 0; i < in.length; i++)
			out[i] = in[i];
		return out;
	}

	/*
	 * Makes a copy of section of an array of doubles
	 * in = array of double values
	 * start = start of the section to be copied
	 * end = end of the section to be copied
	 */
	private static double[] copy(double[] in, int start, int end) {
		double[] out = new double[++end - start];
		int index = 0;
		for (int i = start; i < end; i++)
			out[index++] = in[i];
		return out;
	}

	/*
	 * Sorts an array of doubles from smallest to largest value in order using a mergesort algorithm
	 * in = array of double values
	 */
	private static double[] mergeSort(double[] in) {
		boolean isSorted = true;
		if (in.length > 1)
			for (int i = 0; i < in.length - 1; i++)
				if (in[i] > in[i + 1]) {
					isSorted = false;
					break;
				}
		if (isSorted)
			return in;
		else {
			int mid = in.length / 2;
			double[] left = new double[mid];
			double[] right = new double[in.length - mid];
			for (int i = 0; i < mid; i++)
				left[i] = in[i];
			for (int i = mid; i < in.length; i++)
				right[i - mid] = in[i];
			left = mergeSort(left);
			right = mergeSort(right);
			int total = left.length + right.length;
			double[] merged = new double[total];
			for (int leftCount = 0, rightCount = 0, mergedCount = 0; mergedCount < total; mergedCount++) {
				if (leftCount < left.length
						&& (rightCount == right.length || left[leftCount] <= right[rightCount]))
					merged[mergedCount] = left[leftCount++];
				else
					merged[mergedCount] = right[rightCount++];
			}
			return merged;
		}
	}

	/*
	 * Returns an array of doubles in a String format
	 * in = array of double values
	 * ex: if in = {1, 2, 3, 4, 5} then the method returns "[1, 2, 3, 4, 5]"
	 */
	public static String toString(double[] in) {
		if (in.length > 0) {
			String out = "[";
			for (int i = 0; i < in.length - 1; i++)
				out += in[i] + ", ";
			out += in[in.length - 1] + "]";
			return out;
		} else
			return "[]";
	}

	/*
	 * Finds the largest value of an array of doubles and returns it as a double
	 * in = inputed array of double values
	 * If in is empty, the method returns NaN (Not a Number) 
	 */
	private static double getMax(double[] in) {
		if (in.length < 1)
			return Double.NaN;
		double max = in[0];
		for (double val : in)
			if (val > max)
				max = val;
		return max;
	}

	/*
	 * Finds the smallest value of an array of doubles and returns it as a double
	 * in = inputed array of double values
	 * If in is empty, the method returns NaN (Not a Number) 
	 */
	private static double getMin(double[] in) {
		if (in.length < 1)
			return Double.NaN;
		double min = in[0];
		for (double val : in)
			if (val < min)
				min = val;
		return min;
	}
}
