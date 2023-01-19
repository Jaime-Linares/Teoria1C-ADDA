package tema4Grafos;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;

import us.lsi.graphs.views.SubGraphView;

public class Ejemplo1 {
	
	/* 
	 ENUNCIADO: Dado un grafo, obtener el arbol de expansion minimo de sus componentes conexas
	 y el del grafo en general.	
	*/
	public static void fEjemplo1(Graph<String,String> gf) {
		// Obtenemos las componentes conexas
		var alg1 = new ConnectivityInspector<>(gf);
		List<Set<String>> compConex = alg1.connectedSets();
		// De cada componente conexa aplicamos el algoritmo para obtener el arbol de expansion
		for(Set<String> vertices: compConex) {
			Graph<String,String> sgf = SubGraphView.of(gf, vertices);
			var alg2 = new KruskalMinimumSpanningTree<>(sgf);
			SpanningTree<String> sol = alg2.getSpanningTree(); 
			System.out.println("Arbol de expansion de la componente conexa: " + sol);
		}
		// Ahora, tras obtener la de las comp conexas, obtenemos la del grafo en general
		var alg3 = new KruskalMinimumSpanningTree<>(gf);
		SpanningTree<String> sol2 = alg3.getSpanningTree();
		System.out.println("Arbol de expansion del arbol en general: " + sol2);	
	}
	
	
}
