package tema3Arboles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class ExFeb1920 {
	
	/*
	 ENUNCIADO: Dado un árbol n-ario de enteros, implemente un algoritmo en Java que devuelva una
	 lista con las etiquetas de los elementos que forman parte del camino más largo.
	 Notas: 
	 	(1) Si existen varios caminos con la misma longitud, la lista contendrá los elementos
	 		de uno cualquiera de ellos.
	  	(2) Los árboles Empty no se considerarán como parte del camino.
	 */
	public static List<Integer> caminoMasLargo(Tree<Integer> tree) {
		List<Integer> ac = new ArrayList<>();
		return aux(tree, ac);
	}
	
	private static List<Integer> aux(Tree<Integer> tree, List<Integer> ac) {
		return switch(tree) {
		case TEmpty<Integer> t -> ac;
		case TLeaf<Integer> t -> {
			ac.add(t.label());
			yield ac;
		}
		case TNary<Integer> t -> {
			ac.add(t.label());
			Tree<Integer> hijoMasLargo = t.elements().stream()
					.max(Comparator.comparing(Tree::height))
					.get();
			aux(hijoMasLargo, ac);
			yield ac;
		}
		};
	}


	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		Tree<Integer> tree = Tree.parse("1(2(5,6),3,4(7,8(10),9(11(12,13(14,15)))))", x -> Integer.valueOf(x));
		System.out.println("Arbol que hemos utilizado: " + tree);
		System.out.println("- Camino mas largo: " + caminoMasLargo(tree));
	}

}
