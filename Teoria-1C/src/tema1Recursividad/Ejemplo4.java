package tema1Recursividad;

import java.util.stream.Stream;

import us.lsi.math.Math2;

public class Ejemplo4 {

	// Descripción: suma de los N primeros primos
	
	// FUNCIONAL
	public static Long ejemploFuncional(Integer n) {
		return Stream.iterate(2, e -> true, e -> Math2.siguientePrimo(e))   // potencialmente infinita gracias al e->true, realmente si no se pone no pasa nada
				.limit(n)
				.mapToLong(e -> e+0L)
				.sum();
	}
	
	// ITERATIVO
	public static Long ejemploIterativo(Integer n) {
		Integer e = 2;
		Long ac = 0L;
		Integer cont = 0;
		while(cont < n) {
			ac += e;
			cont++;
			e = Math2.siguientePrimo(e);
		}
		return ac;
	}
	
	// RECURSIVO
	public static Long ejemploRecursivo(Integer n) {
		Integer e = 2;
		Long ac = 0L;
		Integer cont = 0;
		return ejemploRecursivo(e, ac, cont, n);
	}
	
	private static Long ejemploRecursivo(Integer e, Long ac, Integer cont, Integer n) {
		Long res = ac;
		if(cont < n) {
			res = ejemploRecursivo(Math2.siguientePrimo(e), res+=e, cont+1, n);   // nose podra poner x++ pq no lo guarda bn, mirar en los apuntes
		}
		return res;
	}
	
	// RECURSIVO NO FINAL (es igual que el recursivo final pero no ponemos la base del acumulador como parametro)
	public static Long ejemploRecursivoNoFinal(Integer n) {
		Integer e = 2;
		Integer cont = 0;
		return ejemploRecursivoNoFinal(e, cont, n);
	}

	private static Long ejemploRecursivoNoFinal(Integer e, Integer cont, Integer n) {
		Long res = 0L;
		if(cont < n) {
			res = e + ejemploRecursivoNoFinal(Math2.siguientePrimo(e), cont+1, n);   // no se puede poner cont++ pq da error
		}
		return res;
	}

	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		System.out.println("- Funcional: " + ejemploFuncional(200));
		System.out.println("- Iterativo: " + ejemploIterativo(200));
		System.out.println("- Recursivo: " + ejemploRecursivo(200));
		System.out.println("- RecursivoNoFinal: " + ejemploRecursivoNoFinal(200));
	}

}
