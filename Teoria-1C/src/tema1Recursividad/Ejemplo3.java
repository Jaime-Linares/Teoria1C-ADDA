package tema1Recursividad;

import java.util.stream.IntStream;

public class Ejemplo3 {

	// Descripción: suma los multiplos de NUM comprendidos entre A y B
	
	// FUNCIONAL
	public static Integer ejemploFuncional(Integer a, Integer b, Integer num) {
		return IntStream.range(a, b)
				.filter(e -> e%num == 0)
				.sum();
	}
	
	// ITERATIVO
	public static Integer ejemploIterativo(Integer a, Integer b, Integer num) {
		Integer e = a;
		Integer ac = 0;
		while(e < b) {
			if(e%num == 0) {
				ac += e;
			}
			e += 1;
		}
		return ac;
	}
		
	// RECURSIVO
	public static Integer ejemploRecursivo(Integer a, Integer b, Integer num) {
		Integer e = a;
		Integer ac = 0;
		return ejemploRecursivo(e, ac, a, b, num);
	}
	
	public static Integer ejemploRecursivo1(Integer a, Integer b, Integer num) {
		Integer e = a;
		Integer ac = 0;
		return ejemploRecursivo1(e, ac, a, b, num);
	}
	
	private static Integer ejemploRecursivo(Integer e, Integer ac, Integer a, Integer b, Integer num) {
		Integer res = ac;
		if(e < b) {                  // Otra forma (solo poner esto detras del if y yasta
			if(e%num == 0) {         // res = ejemploRecursivo(e+1, res += e%num==0?e:0, a, b, num);
				res += e;
			}
			res = ejemploRecursivo(e+1, res, a, b, num);
		} 
		return res;	
	}
	
	private static Integer ejemploRecursivo1(Integer e, Integer ac, Integer a, Integer b, Integer num) {
		Integer res = ac;
		if(e < b) {                 
			res = ejemploRecursivo(e+1, res += e%num==0?e:0, a, b, num);
		} 
		return res;	
	}
	

	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		System.out.println("- Funcional: " + ejemploFuncional(1, 11, 2));
		System.out.println("- Iterativo: " + ejemploIterativo(1, 11, 2));
		System.out.println("- Recursivo: " + ejemploRecursivo(1, 11, 2));
		System.out.println("- Recursivo1: " + ejemploRecursivo1(1, 11, 2));
	}

}
