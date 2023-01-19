package tema1Recursividad;

import java.util.List;

import us.lsi.common.List2;

public class Ejemplo13 {
	
	// Descripcion: banderas holandesas
	
	// Record: crea tupla
	public static record Tupla(Integer a, Integer b) {
		public static Tupla of(Integer a, Integer b) {
			return new Tupla(a, b);
		}
	}

	// RECURSIVO
	public static Tupla banderaHolandesa(List<Integer> ls, Integer p) {
		Integer a = 0;
		Integer b = 0;
		Integer c = ls.size();
		return banderaHolandesa(ls, p, a, b, c);
	}
	
	private static Tupla banderaHolandesa(List<Integer> ls, Integer p, Integer a, Integer b, Integer c) {
		Tupla res = null;
		if(b.equals(c)) {          
			res = Tupla.of(a, b);
		} else if(ls.get(b).equals(p)) {              // para separar la bandera b de la a
			res = banderaHolandesa(ls, p, a, b+1, c);
		} else if(ls.get(b)<p) {
			List2.intercambia(ls, a, b);
			res = banderaHolandesa(ls, p, a+1, b+1, c);
		} else {
			List2.intercambia(ls, b, c-1);
			res = banderaHolandesa(ls, p, a, b, c-1);
		}
		return res;
	}

	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		List<Integer> ls = List2.of(7, 1, 20, 5, -4, 9, 3, 0, 5);
		Integer p = 5;
		System.out.println(ls);
		System.out.println("- Devuelvo las banderas a y b de la bandera holandesa: " + banderaHolandesa(ls, p));
		System.out.println(ls);
	}

}
