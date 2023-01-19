
package tema1Recursividad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Ejemplo5 {

	// Descripción: Sumamos los enteros que estan en conjuntos dentro de una lista
	
	// FUNCIONAL
	public static Integer ejemploFuncional(List<Set<Integer>> ls) {
		return ls.stream()
				.flatMap(e -> e.stream())
				.mapToInt(Integer::intValue)
				.sum();
	}
	// s= ()
	// A = (0, (b,e)-> b+=e)
	
	// ITERATIVO
	public static Integer ejemploIterativo(List<Set<Integer>> ls) {
		Integer ac = 0;
		Iterator<Set<Integer>> it = ls.iterator();  
		while(it.hasNext()) {
			Set<Integer> conj = it.next();
			Iterator<Integer> it2 = conj.iterator();
			while(it2.hasNext()) {
				ac += it2.next();
			}
		}
		return ac;
	}
	
	public static Integer ejemploIterativo1(List<Set<Integer>> ls) {
		Integer ac = 0;
		for(Set<Integer> ss: ls) {
			for(Integer e: ss) {
				ac += e;
			}
		}
		return ac;
	}
		
	// RECURSIVO
	public static Integer ejemploRecursivo(List<Set<Integer>> ls) {
		Integer ac = 0;
		Iterator<Set<Integer>> e = ls.iterator();
		return ejemploRecursivo(e, ac);
	}
	
	private static Integer ejemploRecursivo(Iterator<Set<Integer>> e, Integer ac) {
		Integer res = ac;
		if(e.hasNext()) {
			Integer sumaInterna = recursivoInterno(e.next());
			res = ejemploRecursivo(e, ac+sumaInterna);
		}
		return res;
	}
	
	private static Integer recursivoInterno(Set<Integer> ss) {
		Integer ac = 0;
		Iterator<Integer> e = ss.iterator();
		return recursivoInterno(e, ac);
	}
	
	private static Integer recursivoInterno(Iterator<Integer> e, Integer ac) {
		Integer res = ac;
		if(e.hasNext()) {
			res = recursivoInterno(e, ac += e.next());			
		}
		return res;
	}
	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		//
		// Vamos a crear la lista de conjuntos (la suma tendrá que dar 43)
		Set<Integer> s1 = Set.of(9, 12, 1);
		Set<Integer> s2 = Set.of(10, 11);
		List<Set<Integer>> ls = new ArrayList<>();
		ls.add(s1);
		ls.add(s2);
		//
		System.out.println("- Funcional: " + ejemploFuncional(ls));
		System.out.println("- Iterativo: " + ejemploIterativo(ls));
		System.out.println("- Iterativo1: " + ejemploIterativo1(ls));
		System.out.println("- Recursivo: " + ejemploRecursivo(ls));
	}

}
