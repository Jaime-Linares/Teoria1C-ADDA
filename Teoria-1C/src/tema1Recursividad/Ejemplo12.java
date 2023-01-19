package tema1Recursividad;

import java.util.List;

import us.lsi.common.List2;

public class Ejemplo12 {

	// Descripción: Nos da un número y una lista con números, hay que decir en que posicion esta o -1 sino esta en la lista
	// Vamos a resolverlo por el método de biseccion
	
	// RECURSIVO              
	public static Integer posicionEnLista(List<Integer> ls, Integer n) {
		Integer k = ls.size()/2;
		Integer i = 0;
		Integer j = ls.size();
		return posicionEnLista(ls, n, k, i, j);
	}
	
	private static Integer posicionEnLista(List<Integer> ls, Integer n, Integer k, Integer i, Integer j) {
		Integer res = null;
		if(i==j) {
			res = -1;
		} else if(ls.get(k).equals(n)) {
			res = k; 
		} else if(ls.get(k) > n) {
			res = posicionEnLista(ls, n, (k+i)/2, i, k);
		} else {
			res = posicionEnLista(ls, n, (k+1+j)/2, k+1, j);
		}
		return res;
	}
	
	// ITERATIVO
	public static Integer posicionEnListaIterativo(List<Integer> ls, Integer n) {
		Integer res = null;
		Integer k = ls.size()/2;
		Integer i = 0;
		Integer j = ls.size();
		
		while(!(ls.get(k).equals(n) || i==j)) {
			if(ls.get(k) > n) {
				k = (k+i)/2;
				j = k;
			} else {
				k = (k+1+j)/2;
				i = k+1;
			}
		}
		res = ls.get(k).equals(n)?k:-1;
		return res;
	}

	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		List<Integer> ls = List2.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		System.out.println("- En que posicion esta el numero 5?(R): " + posicionEnLista(ls, 5));
		System.out.println("- En que posicion esta el numero 5?(I): " + posicionEnListaIterativo(ls, 5));
	}

}
