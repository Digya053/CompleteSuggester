package languagemodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import utilities.Math;

import languagemodel.Ngram;

public class MarkovChain {

	Probability probability = new Probability();

	public List<String> nextWord(Ngram ngram, String text) {
		String key = null;
		List<String> suggestions = new ArrayList<String>();
		text = text.replaceAll("[{}.,:;]", "");
		String[] splitText = text.split(" ");

		for (Entry<String, Integer> entry : ngram.getMap().entrySet()) {
			key = entry.getKey();
			System.out.println(key);
			key = key.replaceAll("[{}.,:;]", "");
			String[] splitted = key.split(" ");

			if(splitted.length<3){
				continue;
			}

			for (int i = 0; i <splitText.length-2; i++) {
				if ((splitText[i+1].equals(splitted[i])) && (splitText[i+2].equals(splitted[i+1]))){ 

					suggestions.add(splitted[i+2]);

				}
			}

		}
		System.out.println("The suggestions are" + suggestions);
		double p = 0.0;
		List<Double> probabilities = new ArrayList<Double>();
		Map<String, Double> map= new HashMap<String, Double>();

		for(int i=0; i<suggestions.size();i++){
			String sentence = text + " " + suggestions.get(i);
			p = probability.probability(ngram, sentence);
			System.out.println(sentence +":"+p);
			probabilities.add(p);
			map.put(suggestions.get(i), p);
		}

		double m = Math.max(probabilities);
		for (String k : map.keySet()){
			if(map.get(k)==m){
				System.out.println("The next word is most likely to be "+k);
			}
		}
			return suggestions;
		}
}
