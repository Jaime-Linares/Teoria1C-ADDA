package tema3Arboles;

import java.util.Comparator;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ejemplo4 {
	
	// 1) Menor label para un arbol binario dado un comparador
	public static <E> E menorLabelBinary(BinaryTree<E> tree, Comparator<E> cmp) {
		return switch(tree) {
		case BEmpty<E> t -> null;   // Discutir: MAX_VALUE para tipos no numericos
		case BLeaf<E> t -> t.label();
		case BTree<E> t -> {
			E a = t.label();
			E b = menorLabelBinary(t.left(), cmp);
			E c = menorLabelBinary(t.right(), cmp);
			b = b==null? a: b;
			c = c==null? a: c;
			E res = c;
			if(cmp.compare(a, b)<=0 && cmp.compare(a, c)<=0) {
				res = a;
			} else if(cmp.compare(b, a)<=0 && cmp.compare(b, c)<=0) {
				res = b;
			}	
			yield res;
		}
		};
	}
	
	
	// 2) Menor label para un arbol n-ario dado un comparador
	public static<E> E menorLabelNary(Tree<E> tree, Comparator<E> cmp) {
		return switch (tree) {
		case TEmpty<E> t -> null;
		case TLeaf<E> t -> t.label();
		case TNary<E> t -> {
			E a = t.label();
			E b = t.elements().stream().map(x -> menorLabelNary(x, cmp))
				.min(cmp).orElse(null);
			yield cmp.compare(a, b) < 0? a: b;
		}
		};
	}
	
	
	// 3) Mayor label para un arbol binario dado un comparador
	public static <E> E mayorLabelBinary(BinaryTree<E> tree, Comparator<E> cmp) {
		return switch(tree) {
		case BEmpty<E> t -> null;
		case BLeaf<E> t -> t.label();
		case BTree<E> t -> {
			E a = t.label();
			E b = mayorLabelBinary(t.left(), cmp);
			E c = mayorLabelBinary(t.right(), cmp);
			b = b==null? a:b;
			c = c==null? a:c;
			E res = c;
			if(cmp.compare(a, b)>=0 && cmp.compare(a, c)>=0) {
				res = a;
			} else if (cmp.compare(b, a)>=0 && cmp.compare(b, c)>=0) {
				res = b;
			}
			yield res;
		}		
		};
	}
	
	
	// 4) Mayor label para un arbol n-ario dado un comparador
	public static <E> E mayorLabelNary(Tree<E> tree, Comparator<E> cmp) {
		return switch(tree) {
		case TEmpty<E> t -> null;
		case TLeaf<E> t -> t.label();
		case TNary<E> t -> {
			E a = t.label();
			E b = t.elements().stream().map(x -> mayorLabelNary(x, cmp))
					.max(cmp).orElse(null);
			yield cmp.compare(a, b) > 0? a:b;
		}		
		};
	}
	
	
	// TEST 
	public static void main(String[] args) {
		System.out.println("* TEST *");
		BinaryTree<String> bt = BinaryTree.parse("Hola(HastaLuego(Jaime,Linares),Adios)");
		Tree<String> nt = Tree.parse("Hola(HastaLuego(Jaime,Linares),Adios,Ey)");
		System.out.println("Arbol binario que vamos a utilizar: " + bt);
		System.out.println("Arbol n-ario que vamos a utilizar: " + nt);
		//
		Comparator<String> cmp = Comparator.comparing(String::length);
		System.out.println("- Menor label arbol binario: " + menorLabelBinary(bt, cmp));
		System.out.println("- Mayor label arbol binario: " + mayorLabelBinary(bt, cmp));
		System.out.println("- Menor label arbol n-ario: " + menorLabelNary(nt, cmp));
		System.out.println("- Mayor label arbol n-ario: " + mayorLabelNary(nt, cmp));
	}
	
}
