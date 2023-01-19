package tema4Grafos;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class Ejemplo2 {
	
	public static void main(String[] args) {
		Graph<String,DefaultEdge> g = new SimpleDirectedWeightedGraph<>(String::new, DefaultEdge::new);
		g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addEdge("B", "A");
        g.setEdgeWeight("B", "A", 2.);
        g.addEdge("C", "B");
        g.setEdgeWeight("C", "B", 3.);
        var alg1 = new DijkstraShortestPath<>(g);
        var alg2 = new FloydWarshallShortestPaths<>(g);
        System.out.println("De C a A: "+alg1.getPathWeight("C", "A"));
        System.out.println("De C a A: "+alg2.getPathWeight("C", "A"));
        System.out.println("De A a C: "+alg1.getPathWeight("A", "C"));
        System.out.println("De A a C: "+alg2.getPathWeight("A", "C"));
        // Si no hay camino, getPathWeight (que está en ambos, Dijkstra y FloydWarshall, errata en diapositivas) 
        // devuelve Infinity. El primero tiene también la opción del método estático:
        var ls1 = DijkstraShortestPath.findPathBetween(g, "C", "A").getVertexList();
        var ls2 = alg1.getPath("C", "A").getVertexList();
        System.out.println(ls1);
        System.out.println(ls2);
	}

}
