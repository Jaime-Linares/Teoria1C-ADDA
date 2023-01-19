package tema3Arboles;

import java.util.function.Function;
import java.util.function.Predicate;

import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;

public class Ejemplo2 {
	
	// 1) Contar todas las etiquetas (labels) que cumplen el predicado
	public static Integer countIfPredicate(BinaryTree<Integer> tree, Predicate<Integer> pred) {
		return switch (tree) {
		case BEmpty<Integer> t -> 0;
		case BLeaf<Integer> t -> pred.test(t.label())? 1:0;
		case BTree<Integer> t -> {
			Integer r = pred.test(t.label())? 1:0;
			r += countIfPredicate(t.left(), pred) + countIfPredicate(t.right(), pred);
			yield r;
		}
		};
	}	
	
	
	// 2) Transformar a un nuevo arbol, solo las labels (etiquetas) que cumplen el predicado
	public static BinaryTree<Long> changeIfPredicate(BinaryTree<Integer> tree, 
			Predicate<Integer> pred, Function<Integer, Long> func) {
		return switch (tree) {
		case BEmpty<Integer> t -> BinaryTree.empty();
		case BLeaf<Integer> t -> BinaryTree.leaf(
				pred.test(t.label())? func.apply(t.label()): t.label()+0L);
		case BTree<Integer> t -> {
			Long raiz = pred.test(t.label())? func.apply(t.label()):t.label()+0L;
			BinaryTree<Long> izq = changeIfPredicate(t.left(), pred, func);
			BinaryTree<Long> der = changeIfPredicate(t.right(), pred, func);
			yield BinaryTree.binary(raiz, izq, der);
		}
		};
	}
	
	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		Function<String, Integer> map = s -> Integer.valueOf(s);
		BinaryTree<Integer> t = BinaryTree.parse("-1(-2(3,4(5,6)),7(_,8(9,10)))", map);
		System.out.println("Arbol con el que vamos a trabajar: " + t);
		//
		Predicate <Integer> p = n -> n%3==0;
		System.out.println("- Resultado método countIfPredicate: " + countIfPredicate(t, p));
		//
		Function<Integer, Long> f = n -> Math2.fibonacci3(n);
		System.out.println("- Resultado método changeIfPredicate: " + changeIfPredicate(t, p, f));
	}

}
