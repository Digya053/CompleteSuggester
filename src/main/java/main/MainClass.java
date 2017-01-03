package main;

import languagemodel.MarkovChain;
import languagemodel.Ngram;
import languagemodel.Probability;

public class MainClass {

	public static void main(String[] args) {
		Probability probability = new Probability();
		Ngram ngram = new Ngram();
		MarkovChain mc = new MarkovChain();
		ngram.loadFromFile("data/text");
		ngram.display();
		System.out.println("The probability is "+ probability.probability(ngram, "It is the"));
		mc.nextWord(ngram,"of seasons where");
	}
}
