package tema1Recursividad;

import java.util.Map;

import us.lsi.common.Map2;

public class Ejemplo8 {

	// Descripción: la recursion multiple aplicada a fibonacci
	
	// RECURSIVA SIN MEMORIA   ("equivale" a la no final)
	public static Long recursivaSinMemoria(Integer n) {
		Long r = null;
		if(n<=1) {
			r = n + 0L;
		} else {
			r = recursivaSinMemoria(n-1) + recursivaSinMemoria(n-2);
		}
		return r;
	}
	
	// RECURSIVA CON MEMORIA  (por si hay calculos repetido se guarden, de ahi a que hagamos un Map)("equivale" a la final)
	public static Long recursivaConMemoria(Integer n) {
		return recursivaConMemoria(Map2.empty(), n);
	}
		// como valor el tipo que devuelve la funcion original, la clave del mismo tipo q recibe la funcion en este caso Integer
	private static Long recursivaConMemoria(Map<Integer, Long> m, Integer n) {
		Long r = m.get(n);   // m lo devolvera si ya se ha hecho
		if(r == null) {   // sino lo crea pa poder usarlos luego y hemos copiado el codigo del sin memoria
			if(n<=1) {
				r = n+0L;
			} else {
				r = recursivaConMemoria(m, n-1) + recursivaConMemoria(m, n-2);
			}
			m.put(n, r);
		}
		return r;
	}

	// ITERATIVA
	// con el iterativo vamos a calcular basicamente todas las posibles soluciones
	public static Long iterativo(Integer n) {
		Long r = null;
		Map<Integer, Long> mp = Map2.empty();
		for(int i=0; i<=n; i++) {
			if(i<=1) {
				r = i+0L;
			} else {
				r = mp.get(i-1) + mp.get(i-2);
			}
			mp.put(i, r);
		}
		return mp.get(n);
	}
		
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		System.out.println("- Recursivo sin memoria: " + recursivaSinMemoria(10));
		System.out.println("- Recursivo con memoria: " + recursivaConMemoria(10));
		System.out.println("- Iterativo: " + iterativo(10));
	}

}
