import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class KNearestNeighbor {
	private List<TrainingDataItem> items;
	private int k;

	public KNearestNeighbor(List<TrainingDataItem> trainingData, int k) {
		items = trainingData;
		this.k = k;
	}

	public String classify(TrainingDataItem item) {

		// sort items based on distance to given item
		Collections.sort(items, new Comparator<TrainingDataItem>() {

			@Override
			public int compare(TrainingDataItem o1, TrainingDataItem o2) {
				return (int) (o1.distance(item) - o2.distance(item));
			}
		});

		List<TrainingDataItem> kNearest = items.subList(0, k);

		// get target class with maximum occurrences

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (TrainingDataItem i : kNearest) {
			String key = i.getTargetClass();
			map.put(key, map.getOrDefault(key, 0) + 1);
		}

		String best = null;
		int occ = 0;
		for (String key : map.keySet()) {
			if (occ < map.get(key)) {
				best = key;
				occ = map.get(key);
			}
		}

		return best;
	}

	// returns error of classifier
	public double testAgainstTestItems(List<TrainingDataItem> testData) {
		int fails = 0;
		for (TrainingDataItem i : testData) {
			if (!classify(i).equals(i.getTargetClass()))
				fails++;
		}
		return fails / (double) testData.size();
	}

	public void printConfusionMatrix(List<TrainingDataItem> items) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (TrainingDataItem i : items) {
			String key = classify(i) + " -> " + i.getTargetClass();
			map.put(key, map.getOrDefault(key, 0) + 1);
		}

		for (String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key));
		}
	}

	// read data from path
	public static ArrayList<TrainingDataItem> readData(String path) {
		ArrayList<TrainingDataItem> items = new ArrayList<TrainingDataItem>();

		File file = new File(path);
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String s = null;

			do {
				s = reader.readLine();
				if (s != null) {
					TrainingDataItem i = new TrainingDataItem(s);
					items.add(i);
				}
			} while (s != null && s != "");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	public static void main(String[] args) {

		// reading data from file
		ArrayList<TrainingDataItem> items = readData("car.data");

		do {
			System.out.println("Enter k");
			Scanner reader = new Scanner(System.in);
			int k = reader.nextInt();
			System.out.println("K = " + k);

			int runs = 100;
			double summedError = 0;
			for (int i = 0; i < runs; i++) {

				// shuffle list
				Collections.shuffle(items);
				int split = (int) (items.size() * 2 / 3f);

				// return first 2/3 to the training data
				List<TrainingDataItem> trainingData = items.subList(0, split);

				// last 1/3 to test data
				List<TrainingDataItem> testData = items.subList(split,
						items.size() - 1);

				// build classifier
				KNearestNeighbor knn = new KNearestNeighbor(trainingData, k);

				// test against test data
				summedError += knn.testAgainstTestItems(testData);

				// only at last run
				if (i == runs - 1)
					knn.printConfusionMatrix(testData);
			}

			System.out.println("Summed Mean Error: " + summedError / runs * 100
					+ "%");
		} while (true);
	}
}
