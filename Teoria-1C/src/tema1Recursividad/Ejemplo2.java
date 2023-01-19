package tema1Recursividad;

import java.util.stream.IntStream;

public class Ejemplo2 {

	// Descripción: suma de los cuadrados desde A hasta B, sin incluir B
	
	// FUNCIONAL
	public static Double ejemploFuncional(Integer a, Integer b) {
		return IntStream.range(a, b)
				.mapToDouble(e -> e*e)
				.sum();
	}
	
	// ITERATIVO
	public static Double ejemploIterativo(Integer a, Integer b) {
		Integer e = a;
		Double ac = 0.;
		while(e < b) {
			ac += e*e;
			e++;
		}
		return ac;
	}
	
	// RECURSIVO
	public static Double ejemploRecursivo(Integer a, Integer b) {
		Integer e = a;
		Double ac = 0.;
		return ejemploRecursivo(e, ac, a, b);
	}
	
	private static Double ejemploRecursivo(Integer e, Double ac, Integer a, Integer b) {
		Double res = ac;
		if(e < b) {
			res = ejemploRecursivo(e+1, ac+e*e, a, b);
		}
		return res;
	}

	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		System.out.println("- Funcional: " + ejemploFuncional(1, 3));
		System.out.println("- Iterativo: " + ejemploIterativo(1, 3));
		System.out.println("- Recursivo: " + ejemploRecursivo(1, 3));
	}

}
