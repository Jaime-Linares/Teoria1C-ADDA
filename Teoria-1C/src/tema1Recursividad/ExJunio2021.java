package tema1Recursividad;

import java.util.stream.Stream;

import us.lsi.math.Math2;

public class ExJunio2021 {
	
	// FUNCIONAL
	public static Integer func_funcional(Integer a, Integer b) {
		return Stream.iterate(a, var-> var < b, var -> Math2.siguientePrimo(var))
				.filter(var -> (var+1)%a == 0)
				.findFirst()
				.orElse(null);
	}


	// ITERATIVA
	public static Integer func_iter(Integer a, Integer b) {
		Integer res = null;
		Integer var = a;
		while(var < b && res == null) {
			if((var+1)%a==0) {
				res = var;
			}
			var = Math2.siguientePrimo(var);
		}
		return res;
	}

	
	// RECURSIVA FINAL
	public static Integer func_rec(Integer a, Integer b) {
		return func_rec(a, a, b);
	}
	
	private static Integer func_rec(Integer var, Integer a, Integer b) {
		if(var >= b) {
			return null;
		} else if((var+1)%a == 0) {
			return var;
		} else {
			return func_rec(Math2.siguientePrimo(var), a, b);
		}
	}


	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		System.out.println("- Funcional: " + func_funcional(2, 21));
		System.out.println("- Iterativa: " + func_iter(2, 21));
		System.out.println("- Recursiva Final: " + func_rec(2, 21));
	}

	
}
