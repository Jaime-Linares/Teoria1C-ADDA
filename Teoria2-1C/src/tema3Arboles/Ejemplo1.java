package tema3Arboles;

import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import us.lsi.common.IntPair;
import us.lsi.common.Set2;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ejemplo1 {
	
	// Ejercicio de la diapositiva 15
	/*
	 Diseñe un algoritmo que dado un árbol n-ario de caracteres devuelva un conjunto de cadenas de 
	 caracteres que contenga todas las cadenas palíndromas que se formen desde la raíz a una hoja no vacía.
	*/
	// Una viene en EjemplosParteComun, en apuntes dice donde exactamente
	
	public static Set<String> palindromas(Tree<Character> tree) {
		return palindromas(tree, "", Set2.of());
	}
	
	private static Set<String> palindromas(Tree<Character> tree, String camino, Set<String> ac) {
		return switch (tree) {
		case TEmpty<Character> t -> Set2.of();
		case TLeaf<Character> t -> {
			Character label = t.label();
			camino = camino + label;
			if(esPalindromo(camino)) {
				ac.add(camino);
			}
			yield ac;
		}
		case TNary<Character> t -> {
			Character label = t.label();
			camino = camino + label;
			for(Tree<Character> tr: t.elements()) {
			 	palindromas(tr, camino, ac); 
			 }
			//t.elements().forEach(tr -> palindromas(tr, camino, ac));
			yield ac;
		}
		};
	}

	// Funcion esPalindromo1 copiada del repositorio
	private static boolean esPalindromo(String st) {
		UnaryOperator<IntPair> next = p -> IntPair.of(p.first()+1, p.second()-1);
		Stream<IntPair> s = Stream.iterate(IntPair.of(0,st.length()), p -> (p.second()-p.first())>1, next);
		return s.allMatch(p->st.charAt(p.first()) == st.charAt(p.second()-1));
	}
	
	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		Tree<Character> tree = Tree.parse("A(B(B(A,X,Y,Z),D,U(X,Z),W),U(U(A,B,U),Z,E))", x -> x.charAt(0));
		System.out.println("Arbol que hemos utilizado: " + tree);
		System.out.println("- Palindromos: " + palindromas(tree));
	}

}
