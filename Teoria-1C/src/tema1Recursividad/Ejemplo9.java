package tema1Recursividad;

import java.util.Map;

import us.lsi.common.Map2;

public class Ejemplo9 {

	// Descripción: mirar en los apuntes
	
	// RECURSIVO SIN MEMORIA
	public static Long recursivoSinMemoria(Integer a, Integer b) {
		Long r = null;
		if(a<=2 || b<=2) {
			r = a+b + 0L;
		} else {
			r = a*b + recursivoSinMemoria(a-2, b-3) + recursivoSinMemoria(a/2, b/3);
		}
		return r;
	}
	
	
	// RECURSIVO CON MEMORIA
	public static Long recursivoConMemoria(Integer a, Integer b) {
		return recursivoConMemoria(a, b, Map2.empty());
	}
	
	private static record Tupla(Integer a, Integer b) {       // lo hacemos aqui pq es muy simple
		public static Tupla of(Integer a, Integer b) {
			return new Tupla(a, b);
		}
	}
	
	private static Long recursivoConMemoria(Integer a, Integer b, Map<Tupla, Long> mp) {
		Long r = mp.get(Tupla.of(a, b));
		if(r == null) {
			if(a<=2 || b<=2) {
				r = a+b + 0L;
			} else {
				r = a*b + recursivoConMemoria(a-2, b-3, mp) + recursivoConMemoria(a/2, b/3, mp);
			}
			mp.put(Tupla.of(a, b), r);
		}
		return r;
	}
	
	// ITERATIVO
	public static Long iterativo(Integer a, Integer b) {
		Long r = null;
		Map<Tupla, Long> mp = Map2.empty();
		for(int i=0; i<=a; i++) {
			for(int j=0; j<=b; j++) {
				if(i<=2 || j<=2) {
					r = i+j + 0L;
				} else {
					r = i*j + mp.get(Tupla.of(i-2, j-3)) + mp.get(Tupla.of(i/2, j/3)); 
				}
				mp.put(Tupla.of(i, j), r);
			}
		}
		return mp.get(Tupla.of(a, b));
	}

	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		System.out.println("- Recursivo sin memoria: " + recursivoSinMemoria(20, 20));
		System.out.println("- Recursivo con memoria: " + recursivoConMemoria(20, 20));
		System.out.println("- Iterativo: " + iterativo(20, 20));
	}

}
