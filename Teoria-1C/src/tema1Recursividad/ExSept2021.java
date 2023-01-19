package tema1Recursividad;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExSept2021 {
	
	// FUNCIONAL
	public static String enunciado(Integer limite) {
		return Stream.iterate(1, x -> x <= limite, x -> x+3)
				.filter(x -> x%2==0)
				.map(x -> x.toString())
				.collect(Collectors.joining("\n"));
	}
	
	
	// ITERATIVO
	public static String fIterativa(Integer limite) {
		String ac = "";
		Integer a = 1;
		while(a<=limite) {
			if(a%2==0) {
				ac += a+"\n";
			}
			a += 3;
		}
		return ac;
	}
	
	
	// RECURSIVO FINAL
	public static String fRecursivaFinal(Integer limite) {
		return aux("", 1, limite);
	}
	
	private static String aux(String ac, Integer a, Integer limite) {
		String res = ac;
		if(a<=limite) {
			if(a%2==0) {
				res = ac + a + "\n";
			}
			aux(res, a+3, limite);
		}
		return res;
	}


	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		System.out.println("Funcional: " + enunciado(14));
		System.out.println("Iterativa: " + fIterativa(14));
		System.out.println("Recursiva Final: " + fRecursivaFinal(14));
	}

}
