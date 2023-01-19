package tema1Recursividad;

import java.util.Iterator;
import java.util.List;

import us.lsi.common.List2;

public class PruebaClaseIteradores {
	
	/* 
	 Descripción: Programa una funcion que devuelva de una lista de cadenas el número de cadenas
	 cuya longitud coincida con un entero N metido por parámetro.
	*/
	
	// RECURSIVO
	public static Integer recursivo(List<String> ls, Integer n) {
		return recursivo(ls.stream().iterator(), n, 0);
	}
	
	private static Integer recursivo(Iterator<String> it, Integer n, Integer ac) {
		Integer res = ac;
		if(it.hasNext()) {
			if(it.next().length() == n) {
				res++;
			}
			res = recursivo(it, n, res);
		}
		return res;
	}
	
		
	// ITERATIVO
	public static Integer iterativo(List<String> ls, Integer n) {
		Integer res = 0;
		Iterator<String> it = ls.stream().iterator();
		while(it.hasNext()) {
			if(it.next().length() == n) {
				res++;
			}
		}
		return res;
	}	
	
	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		List<String> lista = List2.of("Hola", "Adios", "Jaime", "Bienvenido");
		System.out.println(String.format("- RECURSIVA: Hay %d palabras de la lista de longitud %d", recursivo(lista, 5), 5));
		System.out.println(String.format("- ITERATIVA: Hay %d palabras de la lista de longitud %d", iterativo(lista, 5), 5));
	}

}
