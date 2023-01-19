package RepasoArboles;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ejemplo2 {
		
	// Enunciado: Decir si dos arboles binarios son simetricos
	public static <E> Boolean simetricosB(BinaryTree<E> t1, BinaryTree<E> t2) {
		return switch(t1) {
		case BEmpty<E> a && t2 instanceof BEmpty<E> b -> true;
		case BLeaf<E> a && t2 instanceof BLeaf<E> b -> a.label().equals(b.label());
		case BTree<E> a && t2 instanceof BTree<E> b -> a.label().equals(b.label()) &&
			simetricosB(a.left(), b.right()) && simetricosB(a.right(), b.left());
		default -> false;
		};
	}
	
		
	// Enunciado: Decir si dos arboles n-arios son simetricos
	public static <E> Boolean simetricosT(Tree<E> t1, Tree<E> t2) {
		return switch(t1) {
		case TEmpty<E> a && t2 instanceof TEmpty<E> b -> true; 
		case TLeaf<E> a && t2 instanceof TLeaf<E> b -> a.label().equals(b.label());
		case TNary<E> a && t2 instanceof TNary<E> b -> {
			Boolean r = a.label().equals(b.label()) && a.numElements()==b.numElements();
			for(int i=0; r && i<=a.numElements()/2; i++) {    // <= por si numElements es impar
				int j = a.numElements()-i-1;
				r = simetricosT(a.element(i), b.element(j));
			}
			yield r;
		}
		default -> false;	
		};
	}
	
	
	/*
	 Enunciado: Dados dos arboles binarios, realizar un metodo que devuelva verdadero si ambos 
	 			arboles son simetricos y la suma de sus nodos hijos (descendientes directos) es
	 			mayor que la de su nodo padre
	 */
	public static Boolean simetricosYSuma(BinaryTree<Integer> t1, BinaryTree<Integer> t2) {
		return switch(t1) {
		case BEmpty<Integer> a && t2 instanceof BEmpty<Integer> b -> true;
		case BLeaf<Integer> a && t2 instanceof BLeaf<Integer> b -> a.label().equals(b.label());
		case BTree<Integer> a && t2 instanceof BTree<Integer> b -> a.label().equals(b.label()) &&
			simetricosYSuma(a.left(), b.right()) && simetricosYSuma(a.right(), b.left()) &&
			sumaMayorHijos(a.label(), a.left(), a.right()) && sumaMayorHijos(b.label(), b.left(), b.right());
		};
	}
		
	private static Boolean sumaMayorHijos(Integer e, BinaryTree<Integer> left, BinaryTree<Integer> right) {
		Boolean res = true;
		if(!left.isEmpty() && !right.isEmpty()) {
			if(e >= (left.optionalLabel().get()+right.optionalLabel().get())) {
				res = false;
			}
		}
		return res;
	}


	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
	}

}
