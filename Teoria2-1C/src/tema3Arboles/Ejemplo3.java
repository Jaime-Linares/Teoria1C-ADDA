package tema3Arboles;

import java.util.function.Function;
import java.util.function.Predicate;

import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ejemplo3 {
	
	// 1) Mapear
	public static <E,R> Tree<R> transform(Tree<E> tree, Function<E,R> f) {
		return switch (tree) {
		case TEmpty<E> t -> Tree.empty();
		case TLeaf<E> t -> Tree.leaf(f.apply(t.label()));
		case TNary<E> t -> Tree.nary(f.apply(t.label()),
				t.elements().stream().map(e -> transform(e, f)).toList());
		};
	}
	
	
	// 2) Sumar todas las labels que cumplen el predicado, varias opciones
	public static Integer sumIfPredicate(Tree<Integer> tree, Predicate<Integer> pred) {
		return switch (tree) {
		case TEmpty<Integer> t -> 0;
		case TLeaf<Integer> t -> pred.test(t.label())? t.label():0;
		case TNary<Integer> t -> {
			Integer r = pred.test(t.label())? t.label(): 0;
			r += t.elements().stream().mapToInt(x -> sumIfPredicate(x, pred)).sum();
			yield r;
		}
		};		
	}
	
	public static Integer sumIfPredicate2(Tree<Integer> tree, Predicate<Integer> pred) {
		return switch (tree) {
		case TEmpty<Integer> t -> 0;
		case TLeaf<Integer> t && !pred.test(t.label()) -> 0;
		case TLeaf<Integer> t && pred.test(t.label()) -> t.label();
		case TNary<Integer> t -> {
			Integer r = pred.test(t.label())? t.label(): 0;
			r += t.elements().stream().mapToInt(x -> sumIfPredicate(x, pred)).sum();
			yield r;
		}
		};		
	}
	
	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		Tree<String> t1 = Tree.parse("2(3,4(5,6,7),8,9(10,11,12(13,14,15(16,17(18,19,20)))))");
		System.out.println("Arbol con el que vamos a trabajar: " + t1);
		//
		Tree<Integer> t2 = transform(t1, Integer::valueOf);
		System.out.println("- Resultado de la funcion transform: " + t2);
		//
		Predicate<Integer> p = n -> n%5==0;
		System.out.println("- Resultado de la funcion sumIfPredicate: " + sumIfPredicate(t2, p));
		System.out.println("- Resultado de la funcion sumIfPredicate2: " + sumIfPredicate(t2, p));
	}

}
