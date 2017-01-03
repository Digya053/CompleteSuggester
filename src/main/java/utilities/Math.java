package utilities;

import java.util.List;

public class Math {
	
	public static double max(List<Double> num){
		double maxprob = num.get(0);
		for (int i=0; i<num.size();i++){
			if(num.get(i)> maxprob){
				maxprob = num.get(i);
			}
		}
		return maxprob;
	}

}
