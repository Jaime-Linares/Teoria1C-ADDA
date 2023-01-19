package tema1Recursividad;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.List2;

public class Ejemplo11 {

	// Descripción: Escribir un metodo que dado una lista obtenga su inversa
	
	// RECURSIVO FINAL
	public static List<Integer> invierteLista(List<Integer> ls) {
		return invierteLista(ls, List2.empty(), 0);		
	}
	
	private static List<Integer> invierteLista(List<Integer> ls, List<Integer> ac, Integer i) {
		if(i < ls.size()) {
			ac.add(0, ls.get(i));
			ac = invierteLista(ls, ac, i+1);
		}
		return ac;
	}

	// ITERATIVO
	public static List<Integer> invierteListaIterativo(List<Integer> ls) {
		List<Integer> res = new ArrayList<>();
		Integer i = 0;
		while(i<ls.size()) {
			res.add(0, ls.get(i));
			i++;
		}
		return res;
	}
	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		List<Integer> ls = List2.of(1, 2, 3, 4, 5);
		System.out.println("Lista: " + ls);
		System.out.println("Lista invertida (recursivo): " + invierteLista(ls));
		System.out.println("Lista invertida (iterativo): " + invierteListaIterativo(ls));
	}

}
