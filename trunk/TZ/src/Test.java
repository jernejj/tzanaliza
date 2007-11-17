import java.util.*;

public class Test {
	public static void main(String[] args) {
		String izraz = "";
		
		String[] elements = {"x1", "x2", "x3", "x4", "x5"};
		int[] indices;
		CombinationGenerator x = new CombinationGenerator (elements.length, 1);
		StringBuffer combination;
		System.out.println("vseh kom je: "+x.getTotal());
		while (x.hasMore ()) {
		  combination = new StringBuffer ();
		  indices = x.getNext ();
		  for (int i = 0; i < indices.length; i++) {
		    combination.append (elements[indices[i]]);
		  }
		  System.out.println (combination.toString ());
		}
	
	
	}
	
	public static String trimWhiteSpace(String niz) {
		String[] tmp1 = niz.split(" ");
		String tmp2 = "";
		
		
		for(int i=0; i < tmp1.length; i++ )
			tmp2 = tmp2 + tmp1[i];
			
		return tmp2;		
	}
	
	
	public static String[] get() {
		return null;
	}
}
