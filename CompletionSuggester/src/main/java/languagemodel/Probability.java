package languagemodel;

import java.util.List;

public class Probability {

	public double probability(Ngram ngram, String text) {
		String t = text.toLowerCase();
		t.replaceAll("[{:;.,}-]", " ");
		String[] splitted = t.split("\\s+");
		List<Integer> numerator = ngram.getNumeratorCount(text);
		List<Integer> denominator = ngram.getDenominatorCount(text);
		double probabilityNum = 1.0;
		double probabilityDen = 1.0;
		
		numerator = ngram.getNumeratorCount(text);

		System.out.println("numerator=" + numerator);

		denominator = ngram.getDenominatorCount(text);

		System.out.println("denominator="+ denominator);
		
		for (int i= 0 ;i<numerator.size();i++){
			 probabilityNum *= numerator.get(i);
		}
		
		for (int i= 0 ;i<denominator.size();i++){
			 probabilityDen *= denominator.get(i);
		}
		
		double probability = probabilityNum/probabilityDen;
		return probability;

	}

}
