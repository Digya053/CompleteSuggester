package main;

import languagemodel.Ngram;

public class MainClass {

	public static void main(String[] args){
		System.out.println("Hello World");
		Ngram ngram = new Ngram();
		ngram.loadFromFile("data/text");
		ngram.display();
	}
}
