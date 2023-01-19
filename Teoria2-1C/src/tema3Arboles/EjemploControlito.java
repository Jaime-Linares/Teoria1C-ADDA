package tema3Arboles;

import java.util.function.Function;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;

public class EjemploControlito {
	
	/* 
	  Enunciado: Dado un arbol binario (suponemos no ordenado) de enteros y una entero,
	  comprobar si ese entero se encuentra o no en el árbol. 
	*/
	public static Boolean containsLabel(BinaryTree<Integer> tree, Integer n) {
		return switch (tree) {
		case BEmpty<Integer> t -> false;
		case BLeaf<Integer> t -> t.label().equals(n);
		case BTree<Integer> t -> t.label().equals(n) || containsLabel(t.left(), n) || containsLabel(t.right(), n);
		};
	}
		
	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		Function<String, Integer> map = s -> Integer.valueOf(s);
		BinaryTree<Integer> t = BinaryTree.parse("-1(-2(3,4(5,6)),7(_,8(9,10)))", map);
		System.out.println("Árbol Binario que vamos a testear: " + t);
		System.out.println("- Contiene el arbol la etiqueta n=10?: " + containsLabel(t, 10));
	}

}
