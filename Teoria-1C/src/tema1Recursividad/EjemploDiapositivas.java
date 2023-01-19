package tema1Recursividad;

import java.util.ArrayList;
import java.util.List;

import us.lsi.math.Math2;

public class EjemploDiapositivas {
	
	// DIAPOSITIVA 40: Obtener una lista con los primos menores que n
	public static List<Integer> obtenPrimosMenores(Integer n) {
		List<Integer> res = new ArrayList<>();
		return obtenPrimosMenores(res, n-1);   // n-1 porque dice menores
	}
	
	private static List<Integer> obtenPrimosMenores(List<Integer> ls, Integer n) {
		List<Integer> res = ls;
		if(n >= 2) {
			if(Math2.esPrimo(n)) {
				ls.add(n);
			}
			res = obtenPrimosMenores(ls, n-1);
		}
		return res;
	}
	
	
	// DIAPOSITIVA 40: Obtener la suma de los primos mayores o iguales que m y menores que n
	public static Integer sumaPrimosEntre(Integer m, Integer n) {
		return sumaPrimosEntre(0, m, n);
	}
	
	private static Integer sumaPrimosEntre(Integer ac, Integer m, Integer n) {
		Integer res = ac;
		Integer num = m>=2? m:2;
		if(num<n) {
			if(Math2.esPrimo(num)) {
				ac += num;
			}
			res = sumaPrimosEntre(ac, num+1, n);
		}		
		return res;
	}

	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		System.out.println("\n- Lista con los primos menores que 20: " + obtenPrimosMenores(20));
		System.out.println("\n- Suma de primos entre 1 y 10: " + sumaPrimosEntre(1, 17));   // 41 tiene q salir

	}

}
