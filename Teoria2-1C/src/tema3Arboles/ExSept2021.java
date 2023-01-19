package tema3Arboles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.common.List2;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class ExSept2021 {
	
	/* 
	 ENUNCIADO: Implemente en Java la función Map<Integer, List<Integer>> agrupaMultiplos(Tree<Integer> t, List<Integer> l) 
	 con un algoritmo recursivo que, dado un árbol n-ario de enteros y una lista de enteros, devuelva un mapa en el que a 
	 cada entero de la lista le asocie el valor de los nodos que tienen un valor múltiplo de ese entero (si un entero 
	 de la lista no tiene múltiplos, no es necesario que aparezca).
	 Ejemplo:
	 	Entrada:
			t = 4(1(3,2),2(1,5(3,8,9)))
			l = [2, 3, 4, 6]
		Salida:
			{2=[4,2,2,8], 3=[3,3,9], 4=[4,8]}
	 */
	
	public static Map<Integer, List<Integer>> agregaMultiplos(Tree<Integer> tree, List<Integer> ls) {
		Map<Integer, List<Integer>> ac = new HashMap<>();
		return aux(tree, ls, ac);
	}
	
	private static Map<Integer, List<Integer>> aux(Tree<Integer> tree, List<Integer> ls, Map<Integer, List<Integer>> ac) {
		return switch(tree) {
		case TEmpty<Integer> t -> ac;
		case TLeaf<Integer> t -> {
			for(Integer num: ls) {
				if(t.label()%num==0) {
					if(ac.containsKey(num)) {
						ac.get(num).add(t.label());
					} else {
						ac.put(num, List2.of(t.label()));
					}
				}
			}
			yield ac;
		}
		case TNary<Integer> t -> {
			for(Integer num: ls) {
				if(t.label()%num==0) {
					if(ac.containsKey(num)) {
						ac.get(num).add(t.label());
					} else {
						ac.put(num, List2.of(t.label()));
					}
				}
			}
			for(Tree<Integer> hijo: t.elements()) {
				aux(hijo, ls, ac);
			}
			yield ac;
		}
		};
	}


	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		Tree<Integer> tree = Tree.parse("4(1(3,2),2(1,5(3,8,9)))", x -> Integer.valueOf(x));
		List<Integer> ls = List2.of(2,3,4,6);
		System.out.println("- Solucion ejemplo: " + agregaMultiplos(tree, ls));
	}

}
