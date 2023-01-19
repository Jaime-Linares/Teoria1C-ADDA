package tema3Arboles;

import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;

public class ExDic1819 {
	
	/*
	 ENUNCIADO: Implemente un método llamado sumaEtiquetas que dados dos árboles binarios
	 cuyas etiquetas sean números, devuelva la suma total de los nodos de las ramas (de
	 raíz a hoja) de ambos árboles si se cumple que las mismas ramas de cada árbol
	 suman igual, y 0 en caso contrario.
	 */
	public static Integer sumaEtiquetas(BinaryTree<Integer> t1, BinaryTree<Integer> t2) {
		List<Tupla> ac = new ArrayList<>();
		List<Tupla> ls = aux(t1, t2, Tupla.of(0, true), Tupla.of(0, true), ac);
		Integer res;
		Boolean condicionNoSuma = ls.stream().anyMatch(x -> x.equals(Tupla.of(0, false)));
		if(condicionNoSuma) {
			res = 0;
		} else {
			res = ls.stream().map(x -> x.a()).mapToInt(x -> x).sum();
		}
		return res;
	}
	
	private static List<Tupla> aux(BinaryTree<Integer> t1, BinaryTree<Integer> t2, Tupla ac1, Tupla ac2, List<Tupla> ac) {
		return switch(t1) {
		case BEmpty<Integer> a && t2 instanceof BEmpty<Integer> b -> ac;
		case BLeaf<Integer> a && t2 instanceof BLeaf<Integer> b -> {
			Tupla arbol1 = Tupla.of(ac1.a()+a.label(), true);
			Tupla arbol2 = Tupla.of(ac2.a()+b.label(), true);
			Tupla res;
			if(arbol1.a().equals(arbol2.a())) {
				res = Tupla.of(arbol1.a()+arbol2.a(), true);
			} else {
				res = Tupla.of(0, false);
			}
			ac.add(res);
			yield ac;
		}
		case BTree<Integer> a && t2 instanceof BTree<Integer> b -> {
			aux(a.left(), b.left(), Tupla.of(ac1.a()+a.label(), true), Tupla.of(ac2.a()+b.label(), true), ac);
			aux(a.right(), b.right(), Tupla.of(ac1.a()+a.label(), true), Tupla.of(ac2.a()+b.label(), true), ac);
			yield ac;
		}
		case default -> {
			ac.add(Tupla.of(0, false));
			yield ac;
		}
		};
	}
	
	private static record Tupla(Integer a, Boolean b) {
		public static Tupla of(Integer a, Boolean b) {
			return new Tupla(a, b);
		}
	}
	
	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		BinaryTree<Integer> a1 = BinaryTree.parse("5(1(3,4),2)", x -> Integer.valueOf(x));
		BinaryTree<Integer> a2 = BinaryTree.parse("2(3(4,5),5)", x -> Integer.valueOf(x));
		BinaryTree<Integer> a3 = BinaryTree.parse("5(4(1,3),2)", x -> Integer.valueOf(x));
		System.out.println("- Funcion sumaEtiquetas entre a1 y a2: " + sumaEtiquetas(a1, a2));
		System.out.println("- Funcion sumaEtiquetas entre a1 y a3: " + sumaEtiquetas(a1, a3));
		System.out.println("- Funcion sumaEtiquetas entre a2 y a3: " + sumaEtiquetas(a2, a3));
	}

}
