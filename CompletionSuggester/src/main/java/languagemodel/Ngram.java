package languagemodel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ngram {

	private Map<String, Integer> ngram;

	public Ngram() {
		ngram = new HashMap<String, Integer>();

	}
	 public Map<String, Integer> getMap(){
		 return ngram;
	 }

	public void loadFromFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		String absolutePath = classLoader.getResource(fileName).getPath();
		try {
			BufferedReader br = new BufferedReader(new FileReader(absolutePath));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.toLowerCase().trim();
				if (line.isEmpty()) {
					continue;
				}

				String currentNum = null;
				String currentDen = null;
				line = line.replaceAll("[|,.)(;:]", "");
				String[] splitted = line.split("\\s+");

				for (int j = 0; j < splitted.length - 2; j++) {

					currentNum = splitted[j] + " " + splitted[j + 1] + " " + splitted[j + 2];

					try {
						int countNum = ngram.get(currentNum);
						countNum++;
						ngram.put(currentNum, countNum);
					} catch (Exception e) {
						ngram.put(currentNum, 1);
					}
				}

				for (int i = 0; i < splitted.length - 1; i++) {

					currentDen = splitted[i] + " " + splitted[i + 1];
					try {
						int countDen = ngram.get(currentDen);
						countDen++;
						ngram.put(currentDen, countDen);
					} catch (Exception e) {
						ngram.put(currentDen, 1);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void display() {
		System.out.println(this.ngram);
	}

	public List<Integer> getNumeratorCount(String text) {
		String t = text.toLowerCase();
		String[] splitted = t.split("\\s+");
		int countNum = 0;
		List<Integer> numeratorCounts = new ArrayList<Integer>();
		String currentNum = null;

		for (int i = 0; i < splitted.length - 2; i++) {
			currentNum = splitted[i] + " " + splitted[i + 1] + " " + splitted[i + 2];
			
			try {
				countNum = ngram.get(currentNum);
				numeratorCounts.add(countNum);
			} catch (Exception e) {
				System.out.println("The text" + text + "is not available in our data");
			}
		}

		
		return numeratorCounts;
	}

	public List<Integer> getDenominatorCount(String text) {
		String t = text.toLowerCase();
		String[] splitted = t.split("\\s+");
		int countDen = 0;
		String currentDen = null;
		List<Integer> denominatorCounts= new ArrayList<Integer>();
		for (int i = 0; i < splitted.length - 2; i++) {
			currentDen = splitted[i] + " " + splitted[i + 1];
			try {
				countDen = ngram.get(currentDen);
				denominatorCounts.add(countDen);
			} catch (Exception e) {
				System.out.println("The text" + text + "is not available in our data");
			}
		}
		
		return denominatorCounts;
	}
}
