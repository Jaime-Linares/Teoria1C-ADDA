package RepasoArboles;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.BinaryTree.BinaryType;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;
import us.lsi.tiposrecursivos.Tree.TreeType;

public class Ejemplo1 {
	
	// Enunciado: Implementa un algoritmo para comprobar si dos arboles binarios son iguales 
	
	// Version entre la nueva y la antigua
	public static <E> Boolean igualesB1(BinaryTree<E> t1, BinaryTree<E> t2) {
		return switch(t1) {
		case BEmpty<E> t -> t2.type().equals(BinaryType.Empty);
		case BLeaf<E> t -> {
			Boolean r = t2.type().equals(BinaryType.Leaf);
			if(r) {
				BLeaf<E> e = (BLeaf<E>) t2;
				r = t.label().equals(e.label());
			}
			yield r;
		}
		case BTree<E> t -> {
			Boolean r = t2.type().equals(BinaryType.Binary);
			if(r) {
				BTree<E> e = (BTree<E>) t2;
				r = t.label().equals(e.label()) && igualesB1(t.left(), e.left()) &&
						igualesB1(t.right(), e.right());
			}
			yield r;
		}
		};		
	}
	
	// Version mas nueva
	public static <E> Boolean igualesB2(BinaryTree<E> t1, BinaryTree<E> t2) {
		return switch(t1) {
		case BEmpty<E> a && t2 instanceof BEmpty<E> b -> true;
		case BLeaf<E> a && t2 instanceof BLeaf<E> b -> a.label().equals(b.label());
		case BTree<E> a && t2 instanceof BTree<E> b -> a.label().equals(b.label()) &&
			igualesB2(a.left(), b.left()) && igualesB2(a.right(), b.right());
		default -> false;
		};		
	}
	
	
	// Enunciado: Implementa un algoritmo para comprobar si dos arboles n-arios son iguales 
	
	// Version entre la nueva y la antigua
	public static <E> Boolean igualesT1(Tree<E> t1, Tree<E> t2) {
		return switch(t1) {
		case TEmpty<E> t -> t2.type().equals(TreeType.Empty);
		case TLeaf<E> t -> {
			Boolean r = t2.type().equals(TreeType.Leaf);
			if(r) {
				TLeaf<E> e = (TLeaf<E>) t2;
				r = t.label().equals(e.label());
			}
			yield r;
		}
		case TNary<E> t -> {
			Boolean r = t2.type().equals(TreeType.Nary);
			if(r) {
				TNary<E> e = (TNary<E>) t2;
				r = t.numElements()==e.numElements();
				for(int i=0; r && i<t.numElements(); i++) {
					r = igualesT1(t.element(i), e.element(i));
				}
			}
			yield r;
		}
		};
	}
	
	// Version mas nueva
	public static <E> Boolean igualesT2(Tree<E> t1, Tree<E> t2) {
		return switch(t1) {
		case TEmpty<E> a && t2 instanceof TEmpty<E> b -> true;
		case TLeaf<E> a && t2 instanceof TLeaf<E> b -> a.label().equals(b.label());
		case TNary<E> a && t2 instanceof TNary<E> b -> {
			Boolean r = a.label().equals(b.label()) && a.numElements()==b.numElements();
			for(int i=0; r && i<a.numElements(); i++) {
				r = igualesT2(a.element(i), b.element(i));
			}
			yield r;
		}
		default -> false;
		};
	}	
	
	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
	}

}
