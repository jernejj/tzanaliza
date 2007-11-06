import java.util.*;

public class Test {
	public static void main(String[] args) {
		String izraz = "";
	
		System.out.print((true || false) && (false || false));
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
