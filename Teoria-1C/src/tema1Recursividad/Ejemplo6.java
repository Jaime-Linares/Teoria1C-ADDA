package tema1Recursividad;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.common.String2;

public class Ejemplo6 {

	// Codigo para crear el Tipo Paridad y dar sentido a este
	public static enum Paridad {PAR, IMPAR};
	
	private static Paridad paridad(Integer e) {
		return e%2==0? Paridad.PAR:Paridad.IMPAR;
	}
	
	
	// FUNCIONES PARA ESQUEMAS ITERATIVOS Y RECURSIVOS
	// Funcional
	public static Map<Paridad, List<Integer>> funcional(List<Integer> ls) {
		return ls.stream()
				.collect(Collectors.groupingBy(e -> paridad(e)));
	}
	
	// Iterativo
	public static Map<Paridad, List<Integer>> iterativo(List<Integer> ls) {
		Map<Paridad, List<Integer>> res = Map2.empty();
		Integer i = 0;
		while(i < ls.size()) {
			Paridad p = paridad(ls.get(i));
			if(res.containsKey(p)) {
				res.get(p).add(ls.get(i));
			} else {
				res.put(p, List2.of(ls.get(i)));
			}
			i++;
		}
		return res;
	}
	
	// Recursivo
	public static Map<Paridad, List<Integer>> recursivo(List<Integer> ls) {
		Integer e = 0;
		Map<Paridad, List<Integer>> mp = Map2.empty();
		return recursivo(e, mp, ls);
	}

	private static Map<Paridad, List<Integer>> recursivo(Integer i, Map<Paridad, List<Integer>> ac, List<Integer> ls) {
		if(i<ls.size()) {
			Paridad p = paridad(ls.get(i));
			if(ac.containsKey(p)) {
				ac.get(p).add(ls.get(i));
			} else {
				ac.put(p, List2.of(ls.get(i)));
			}
			ac = recursivo(i+1, ac, ls);
		}
		return ac;
	}
	
	
	// TEST
		public static void main(String[] args) {
			System.out.println("* TEST *");
			//
			Random rnd = new Random();
			List<Integer> ls = List2.empty();
			for(int i=0; i<100; i++) {
				ls.add(rnd.nextInt());
			}
			//
			System.out.println("\n- FUNCIONAL:");
			Map<Paridad, List<Integer>> m = funcional(ls);
			String2.toConsole("PARES: %s", m.get(Paridad.PAR));
			String2.toConsole("IMPARES: %s", m.get(Paridad.IMPAR));
			//
			System.out.println("\n- ITERATIVO:");
			Map<Paridad, List<Integer>> m1 = iterativo(ls);
			String2.toConsole("PARES: %s", m1.get(Paridad.PAR));
			String2.toConsole("IMPARES: %s", m1.get(Paridad.IMPAR));
			//
			System.out.println("\n- RECURSIVO");
			Map<Paridad, List<Integer>> m2 = recursivo(ls);
			String2.toConsole("PARES: %s", m2.get(Paridad.PAR));
			String2.toConsole("IMPARES: %s", m2.get(Paridad.IMPAR));
		}
		
}
