public class Tester {

	public static void main(String[] args) {
		/*
		 * The test class used to test the public methods in Statistics.java
		 * All test arrays are stored in double[][] tests
		 */
		double[][] tests = {
				{},
				{ 0 },
				{ 1, 2, 3, 4 },
				{ -1, -2, -3, -4 },
				{ -1, 1, 1, -1 },
				{ -1, -2, -3, -4, -4 },
				{ 0, 0, 0, 0 },
				{ -12341.2341234, 346346.4567, -658485.7383, 455.3567634, 4.0, -346346.4567, 346346.4567 },
				{3, 4, 2, 3, 2, 5, 8, 9, 7, 6},
				{3, 4, 5, 6, 3, 7, 5, 7, 8, 1, 2},
				{3, 4, 5, 6, 3, 7, 5, 6, 8, 1, 2, 9},
				{3, 4, 5, 6, 3, 6, 5, 7, 8, 1, 2, 9, 3},
				{ -1, 1, 1, -1, 3, 4, 5},
				{3, 4, 5, 6, 3, 6, 5, 7, 8, 1, 2, 9, 3, -3, -3, -3},
				{3, 4, 5, 6, 3, 6, 5, 7, 8, 1, 2, -2.76},
				{ -1, 1, 21, -1, 3, 4, 5}};				

		for (int i = 0; i < tests.length; i++) {
//			Test Number
			System.out.println("Test " + (i + 1));
			
//			toString of the input for reference
			System.out.println("toString()");
			System.out.println(Statistics.toString(tests[i]));

//			Mean Test
			System.out.println("mean()");
			System.out.println(Statistics.mean(tests[i]));

//			Median Test
			System.out.println("median()");
			System.out.println(Statistics.median(tests[i]));

//			Mode Test
			System.out.println("mode()");
			System.out.println(Statistics.mode(tests[i]));

//			Range Test
			System.out.println("range()");
			System.out.println(Statistics.range(tests[i]));

//			Upper Quartile Test
			System.out.println("upperQuartile()");
			System.out.println(Statistics.upperQuartile(tests[i]));

//			Lower Quartile Test
			System.out.println("lowerQuartile()");
			System.out.println(Statistics.lowerQuartile(tests[i]));

//			Interquartile Range Test
			System.out.println("interQuartileRange()");
			System.out.println(Statistics.interQuartileRange(tests[i]));

//			Outliers Test
			System.out.println("outliers()");
			System.out.println(Statistics.outliers(tests[i]));

//			Standard Deviation Test
			System.out.println("standardDeviation()");
			System.out.println(Statistics.standardDeviation(tests[i]));

//			Z Score Tests
			System.out.println("zScore(): " + 0);
			System.out.println(Statistics.zScore(tests[i], 0));
			for (int j = 0; j < tests[i].length; j++) {
				System.out.println("zScore(): " + tests[i][j]);
				System.out.println(Statistics.zScore(tests[i], tests[i][j]));
			}
			System.out.println();
		}
	}
}
