package tema1Recursividad;

public class ExJunio2122 {
	
	// RNF
	public static String fRNF(Integer a, Integer b, Integer c) {
		String res;
		if(a<3 || b<3 || c<3) {
			res = Integer.toString(a+b+c) + "y";
		} else if(a<5 || (b<a && b<c) || c<5) {
			res = Integer.toString(a*b*c) + "z";
		} else if(a%2==0 && b%2==0 && c%2==0) {
			res = fRNF(a-1, b/3, c-3) + "+";
		} else {
			res = fRNF(a-4, b/3, c-4) + "#";
		}		
		return res;
	}
	
	
	//RF
	public static String fRF(Integer a, Integer b, Integer c) {
		return fRF("", a, b, c);
	}
	
	private static String fRF(String ac, Integer a, Integer b, Integer c) {
		String res;
		if(a<3 || b<3 || c<3) {
			res = Integer.toString(a+b+c) + "y" + ac;
		} else if(a<5 || (b<a && b<c) || c<5) {
			res = Integer.toString(a*b*c) + "z";
		} else if(a%2==0 && b%2==0 && c%2==0) {
			res = fRF("+" + ac, a-1, b/3, c-3);
		} else {
			res = fRF("#" + ac, a-4, b/3, c-4);
		}		
		return res;
	}


	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		System.out.println("Recursiva No Final: " + fRNF(30, 80, 90));
		System.out.println("Recursiva Final: " + fRNF(30, 80, 90));

	}

}
