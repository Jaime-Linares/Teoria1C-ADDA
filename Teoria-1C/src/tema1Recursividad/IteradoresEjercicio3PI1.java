package tema1Recursividad;

import java.util.Iterator;

import us.lsi.common.Files2;

public class IteradoresEjercicio3PI1 {
	
	// Vamos a hacer lo de los iteradores del ejercicio3 de la PI1
	// Hay tres formas (en los apuntes), la que si se puede usar 100% es la de FILTRO -> MÉTODO
	
	/*
	 Descripción: implementar un método que dado un fichero de enteros, itere sobre él devolviendo
	 la suma de los impares
	 */
	
	// RECURSIVO FINAL
	public static Integer solucionRecFinal(String file) {
		// Iterator<String> it2 = Stream2.file(file).iterator();     --> puede dar problemas
		Iterator<String> it = Files2.streamFromFile(file).iterator();
		return recFinal(it, 0);
	}
	
	private static Integer recFinal(Iterator<String> it, Integer ac) {
		Integer r = ac;
		if(it.hasNext()) {
			String s = it.next();
			Integer n = Integer.valueOf(s);
			r += n%2==1? n: 0;
			r = recFinal(it, r);
		}
		return r;
	}
	
	// ITERATIVO
	public static Integer solucionItera(String file) {
		Iterator<String> it = Files2.streamFromFile(file).iterator();
		Integer ba = 0;
		while(it.hasNext()) {
			Integer n = Integer.valueOf(it.next());
			if(n%2==1) {
				ba += n;
			}
		}
		return ba;
	}

	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		String file1 = "ficheros/Ejemplo3PI1";
		System.out.println("Recursivo Final: " + solucionRecFinal(file1));
		System.out.println("Iterativo: " + solucionItera(file1));
	}
	
}
