package languagemodel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Ngram {

	private Map<String, Integer> ngram;

	public Ngram(){
		ngram = new HashMap<String, Integer>();

	}

	public void loadFromFile(String fileName){
		ClassLoader classLoader = getClass().getClassLoader();
		String absolutePath = classLoader.getResource(fileName).getPath();
		try{
			BufferedReader br = new BufferedReader(new FileReader(absolutePath));
			String line;
			while( (line=br.readLine()) !=null){
				line= line.toLowerCase().trim();
				if(line.isEmpty()){
					continue;
				}

				line = line.replaceAll("[|,.)(;:]", "");
				String[] splitted =line.split("\\s+");
				for (int i = 0 ; i<splitted.length-1 ; i++){
					String current = splitted[i] + " " + splitted[i+1]; //"he has"
					try{
						int count = ngram.get(current);
						count++;
						ngram.put(current, count);
					}
					catch(Exception e){
						ngram.put(current,1);
					}
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();

		}
	}
	
	public void display(){
		System.out.println(this.ngram);
	}
}
