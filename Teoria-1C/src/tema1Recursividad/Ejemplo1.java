package tema1Recursividad;

import java.util.List;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public class Ejemplo1 {
	
	// FUNCIONAL
	public static List<Integer> añadeParesHastaDiezFuncional() {
		return IntStream.range(0, 10)
				.boxed()
				.filter(e -> e%2 == 0)
				.toList();
	}
	
	// ITERATIVO
	public static List<Integer> añadeParesHastaDiezIterativo() {
		List<Integer> ls = List2.empty();
		Integer e = 0;
		while(e < 10) {
			if(e%2 == 0) {
				ls.add(e);
			}
			e += 1;
		}
		return ls;
	}
	
	// RECURSIVO
	public static List<Integer> añadeParesHastaDiezRecursivo() {
		return añadeParesHastaDiezRecursivo(List2.empty(), 0);
	}
	
	private static List<Integer> añadeParesHastaDiezRecursivo(List<Integer> ls, Integer e) {
		List<Integer> res = ls;
		if(e < 10) {
			if(e%2 == 0) {
				ls.add(e);
			}
			res = añadeParesHastaDiezRecursivo(ls, e+1);
		}
		return res;
	}
	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		System.out.println("- Funcional: "+ añadeParesHastaDiezFuncional());
		System.out.println("- Iterativo: "+ añadeParesHastaDiezIterativo());
		System.out.println("- Recursivo: "+ añadeParesHastaDiezRecursivo());
	}

}
