package tema1Recursividad;

import java.util.List;

import us.lsi.common.List2;

public class Ejemplo10 {

	// Descripción: Implementar un método que indique si una lista de numeros esta ordenada
	
	// RECURSIVA FINAL
	public static Boolean estaOrdenada(List<Integer> ls) {
		return estaOrdenada(ls, true, 0);
	}
	
	private static Boolean estaOrdenada(List<Integer> ls, Boolean b, Integer i) {
		Boolean r = null;
		if(b==false || i==ls.size()-1) {
			r = b;
		} else {
			r = estaOrdenada(ls, ls.get(i)<=ls.get(i+1), i+1);
		}
		return r;
	}

	// ITERATIVA
	public static Boolean estaOrdenadaItera(List<Integer> ls) {
		Boolean r = true;
		Integer i = 0;
		while(r==true && i<ls.size()-1) {
			r = ls.get(i)<=ls.get(i+1)? true: false; 
			i++;
		}
		return r;
	}
	
	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		List<Integer> ls = List2.of(0,1,2,3,4,5,6,7,8,9);
		System.out.println("- Esta ordenada?: " + estaOrdenada(ls));
		System.out.println("- Esta ordenada (Iterativo)?: " + estaOrdenadaItera(ls));
	}

}
