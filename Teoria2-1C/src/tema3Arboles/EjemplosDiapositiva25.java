package tema3Arboles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.common.List2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class EjemplosDiapositiva25 {
	
	// Ejemplo2 y Ejemplo3 estan como ejemplos en la PI2
	
	/* 
	 EJEMPLO 1: Diseñe un algoritmo que dado un árbol binario de enteros devuelva cierto en
	 caso de que para cada nodo que tenga 2 hijos no vacíos se cumpla que su etiqueta es igual 
	 a la suma de las etiquetas de las raíces de sus 2 hijos.
	 */ 
	public static Boolean fEjemplo1(BinaryTree<Integer> tree) {
		return aux1(tree).b();
	}	
	
	private static Tupla1 aux1(BinaryTree<Integer> tree) {
		return switch(tree) {
		case BEmpty<Integer> t -> Tupla1.of(0, true);
		case BLeaf<Integer> t -> Tupla1.of(t.label(), true);
		case BTree<Integer> t -> {
			Tupla1 izq = aux1(t.left());
			Tupla1 der = aux1(t.right());
			Tupla1 res = Tupla1.of(t.label(), true && izq.b() && der.b());
			if(!t.left().isEmpty() && !t.right().isEmpty()) {
				Integer sum = izq.a()+der.a();
				res = Tupla1.of(t.label(), sum.equals(t.label()) && izq.b() && der.b());
			}
			yield res;
		}
		};
	}

	private static record Tupla1(Integer a, Boolean b) {
		public static Tupla1 of(Integer a, Boolean b) {
			return new Tupla1(a, b); 
		}
	}
	
	
	/*
	 EJEMPLO 4: Diseñe un algoritmo que dado un árbol n-ario de tipo genérico E devuelva un
	 Map<Integer,List<E>> que incluya una entrada en el map por cada nivel del árbol. Dicha 
	 entrada debe tener como clave el nivel y como información asociada una lista con las 
	 etiquetas de los nodos que se encuentran en dicho nivel y que tienen un número par de hijos.
	 */
	public static <E> Map<Integer,List<E>> fEjemplo4(Tree<E> tree) {
		Integer nivel = 0;
		Map<Integer,List<E>> ac = new HashMap<>();
		return aux4(tree, nivel, ac);
	}
		
	private static <E> Map<Integer, List<E>> aux4(Tree<E> tree, Integer nivel, Map<Integer, List<E>> ac) {
		return switch(tree) {
		case TEmpty<E> t -> {
			if(!ac.containsKey(nivel)) {
				ac.put(nivel, List2.of());
			}
			yield ac;
		}
		case TLeaf<E> t -> {
			if(!ac.containsKey(nivel)) {
				ac.put(nivel, List2.of());
			}
			yield ac;
		}
		case TNary<E> t -> {
			if(!ac.containsKey(nivel)) {
				ac.put(nivel, List2.of());
			}
			List<Tree<E>> hijos = t.elements();
			if(hijos.size()%2==0) {
				ac.get(nivel).add(t.label());
			}
			for(int i=0; i<hijos.size(); i++) {
				aux4(hijos.get(i), nivel+1, ac);
			}
			yield ac;
		}
		};
	}
	

	/*
	 EJEMPLO 5: Diseñe un algoritmo recursivo que dado un árbol n-ario de tipo genérico devuelva 
	 un Map<Integer, List<Tree<E>>>. Dicho map en cada entrada tiene como clave el número de hijos 
	 de un árbol y como valor una lista con todos los árboles que tienen ese número de hijos.
	 */
	public static <E> Map<Integer,List<Tree<E>>> fEjemplo5(Tree<E> tree) {
		Map<Integer,List<Tree<E>>> ac = new HashMap<>();
		return aux5(tree, ac);
	}
		
	private static <E> Map<Integer, List<Tree<E>>> aux5(Tree<E> tree, Map<Integer, List<Tree<E>>> ac) {
		return switch(tree) {
		case TEmpty<E> t -> {
			Integer hijos = 0;
			if(ac.containsKey(hijos)) {
				ac.get(hijos).add(t);
			} else {
				ac.put(hijos, List2.of(t));
			}
			yield ac;
		}
		case TLeaf<E> t -> {
			Integer hijos = 0;
			if(ac.containsKey(hijos)) {
				ac.get(hijos).add(t);
			} else {
				ac.put(hijos, List2.of(t));
			}
			yield ac;
		}
		case TNary<E> t -> {
			Integer hijos = t.numElements();
			if(ac.containsKey(hijos)) {
				ac.get(hijos).add(t);
			} else {
				ac.put(hijos, List2.of(t));
			}
			for(Tree<E> hijo: t.elements()) {
				aux5(hijo, ac);
			}
			yield ac;
		}
		};
	}

	
	// TEST
	public static void main(String[] args) {
		System.out.println("* TEST *");
		//
		// test ejemplo1
		System.out.println("\n---EJEMPLO 1---");
		BinaryTree<Integer> bt1 = BinaryTree.parse("10(9(15(14,2),_),1(0,1))", x -> Integer.valueOf(x));
		System.out.println("Arbol que vamos a utilizar para el ejemplo 1: " + bt1);
		System.out.println("Resultado del ejemplo 1: " + fEjemplo1(bt1));
		//
		// test ejemplo4
		System.out.println("\n---EJEMPLO 4---");
		Tree<String> nt4 = Tree.parse(
				"Hola(QueTal,Bienvenido(Yo,Me,Llamo,Jaime(Y,Tu)),Jose(Pero(Soy(Mentira,En,Verdad),Un,Poco,Tonto)),Ok)");
		Map<Integer,List<String>> sol4 = fEjemplo4(nt4);
		System.out.println("Arbol que vamos a utilizar para el ejemplo 4: " + nt4);
		System.out.println("Resultado del ejemplo 4:");
		for(Map.Entry<Integer,List<String>> mp: sol4.entrySet()) {
			System.out.println("  " + mp);
		}
		//
		// test ejemplo5
		System.out.println("\n---EJEMPLO 5---");
		Tree<Character> nt5 = Tree.parse("A(B(E,F,G),C(H,I),D(J(M,_),K(N),L),O)", x -> x.charAt(0));
		Map<Integer, List<Tree<Character>>> sol5 = fEjemplo5(nt5);
		System.out.println("Arbol que vamos a utilizar para el ejemplo 5: " + nt5);
		System.out.println("Resultado del ejemplo 5:");
		for(Map.Entry<Integer,List<Tree<Character>>> mp: sol5.entrySet()) {
			System.out.println("  " + mp);
		}
	}

}
