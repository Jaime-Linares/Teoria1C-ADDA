package tema1Recursividad;

import java.util.stream.Stream;

public class Ejemplo7 {
	
	/* 
	  Descripción: Sea la siguiente definicion f, dados a, b que pertenecen a los reales,
	  f(a,b) = {a+b si a<2;  b/2+f(a-2,b) e.o.c} 
	  Encuentre una funcion recursiva no final, una recursiva final, una iterativa y una funcional
	 */
    
	// RECURSIVO NO FINAL     (es literalmente pasar la defincion de la funcion a java)
	public static Integer recursivoNoFinal(Integer a, Integer b) {   
		Integer ac = null;
		if(a<2) {
			ac = a+b;   // no se pone el res d nuevo pq realmente primero va sumando y cuando llega al final solo tiene q sumar eso
		} else {
			ac = b/2 + recursivoNoFinal(a-2, b);
		}
		return ac;
	}
		
	// RECURSIVO FINAL
	public static Integer recursivoFinal(Integer a, Integer b) {
		Integer ac = 0;   // (la diferencia con la no final esq basicamente tenemos q hacer una primera llamada que inicializa la base del acumulador)
		return recursivoFinal(ac, a, b);
	}
	
	private static Integer recursivoFinal(Integer ac, Integer a, Integer b) {
		Integer res = null;
		if(a<2) {
			res = ac + a+b;    // se pone pq este se va actualizando 
		} else {
			res = recursivoFinal(ac + b/2, a-2, b);
		}
		return res;
	}
	
	//ITERATIVO
	public static Integer iterativo(Integer a, Integer b) {
		Integer ac = 0;
		while(a>=2) {
			ac += b/2;
			a -= 2;
		}
		ac += a+b;		
		return ac;
	}
	
	// FUNCIONAL
	public static Integer funcional(Integer a, Integer b) {
		// queremos sacar como la secuencia de los valores q han ido teniendo las varibales (en este caso a, ac)
		TuplaE7 res = Stream.iterate(TuplaE7.seed(a, b), TuplaE7::next)
				.filter(e -> e.a()<2)
				.findFirst()
				.get();  // aqui tenemos hasta el bucle
		return res.ac() + (res.a() + res.b());   // hay q sumarle el a+b pq es lo q aparece despues del bucle
		// en vez de res.b() podemos poner b (pq la b no cambia)
	}
			
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		// El ejemplo tendrá que dar 510
		System.out.println("- Recursivo No Final: " + recursivoNoFinal(200, 10));
		System.out.println("- Recursivo Final: " + recursivoFinal(200, 10));		
		System.out.println("- Iterativo: " + iterativo(200, 10));
		System.out.println("- Funcional: " + funcional(200, 10));
	}

}
