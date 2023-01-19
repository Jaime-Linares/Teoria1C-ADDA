package tema3Arboles;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;

public class ExFeb2122 {
	
	/*
	 ENUNCIADO: Implemente un algoritmo recursivo para comprobar si un árbol binario de enteros es equiponderado.
	 	- El árbol binario vacío está equiponderado por definición.
        - Un árbol binario está equiponderado si se cumple que para todo nodo el peso del subárbol izquierdo
		es igual al peso del subárbol derecho.
        - Llamamos peso de un árbol a la suma de todos sus elementos.
        - El valor de un árbol vacío es cero
	 */
	public static Boolean esEquiponderado(BinaryTree<Integer> tree) {
		Tupla res = aux(tree);
		return res.b();
	}
	
	private static Tupla aux(BinaryTree<Integer> tree) {
		return switch(tree) {
		case BEmpty<Integer> t -> Tupla.of(0, true);
		case BLeaf<Integer> t -> Tupla.of(t.label(), true);
		case BTree<Integer> t -> {
			Integer valor = t.label();
			Tupla izq = aux(t.left());
			Tupla der = aux(t.right());
			yield Tupla.of(
					valor+izq.a()+der.a(), 
					izq.a().equals(der.a()) && izq.b() && der.b());
		}
		};
	}

	private static record Tupla(Integer a, Boolean b) {
		public static Tupla of(Integer a, Boolean b) {
			return new Tupla(a, b);
		}
	}

	public static void main(String[] args) {
		System.out.println("* TEST *");
		BinaryTree<Integer> bt1 = BinaryTree.parse("8(4(2,2),0(4,4))", x -> Integer.valueOf(x));
		BinaryTree<Integer> bt2 = BinaryTree.parse("0(8(4,4),4(6,6(_,2(-1,-1))))", x -> Integer.valueOf(x));
		System.out.println("Primer arbol: " + bt1);
		System.out.println("  Es equiponderado?: " + esEquiponderado(bt1));
		System.out.println("Segundo arbol: " + bt2);
		System.out.println("  Es equiponderado?: " + esEquiponderado(bt2));
	}

}
