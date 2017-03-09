package guava.graphs;

import com.google.common.graph.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jiangjiajie on 2017/2/14.
 */
public class BasicGraphDemo<N,E> {
    public static void main(String[] args) {
        MutableGraph<Integer> graph = GraphBuilder.directed().build();
        graph.addNode(1);
        graph.putEdge(2,3); // also adds nodes 2 and 3 if not already present
        Set<Integer> successorsOfTwo = graph.successors(2); // return {3}
        graph.putEdge(2, 3); // no effect; Graph does not support parallel edges

        MutableValueGraph<Integer, Double> weightedGraph = ValueGraphBuilder.directed().build();
        weightedGraph.addNode(1);
        weightedGraph.putEdgeValue(2, 3, 1.5); // also adds nodes 2 and 3 if not already present
        weightedGraph.putEdgeValue(3, 5, 1.5); // edge values (like Map values) need not be unique
        weightedGraph.putEdgeValue(2, 3, 2.0); // updates the value for (2,3) to 2.0

        MutableNetwork<Integer, String> network = NetworkBuilder.directed().build();
        network.addNode(1);
        network.addEdge(2,3,"2->3"); // also adds nodes 2 and 3 if not already present
        Set<Integer> successorOfTwo = network.successors(2); // returns {3}
        Set<String> outEdgeOfTwo = network.outEdges(2); // returns {"2->3"}
        network.addEdge(2,3,"2->3 too"); // throws; Network disallows parallel edges by default
        network.addEdge(2,3,"2->3"); // no effect; this edge is already present and connecting these nodes in this order
        Set<String> inEdgeOfFour = network.inEdges(4); // throws; node not in  graph
    }

    // Return all nodes reachable by traversing 2 edges starting from "node"
    // (ignoring edge direction and edge weights, if any, and not including "node")
    public Set<N> getTwoHopNeighbors(Graph<N> graph, N node) {
        Set<N> twoHopNeighbors = new HashSet<>();
        for (N neighbor : graph.adjacentNodes(node)) {
            twoHopNeighbors.addAll(graph.adjacentNodes(neighbor));
        }
        twoHopNeighbors.remove(node);
        return twoHopNeighbors;
    }

    // Update the shortest-path weighted distances of the successors to "node"
    // in a directed Network (inner loop of Dijkstra's algorithm)
    // given a known distance for {@code node} stored in a {@code Map<N, Double>},
    // and a {@code Function<E, Double>} for retrieving a weight for an edge.
//    void updateDistancesFrom(Network<N, E> network, N node) {
//        double nodeDistance = distances.get(node);
//        for (E outEdge : network.outEdges(node)) {
//            N target = network.target(outEdge);
//            double targetDistance = nodeDistance + edgeWeights.apply(outEdge);
//            if (targetDistance < distances.getOrDefault(target, Double.MAX_VALUE)) {
//                distances.put(target, targetDistance);
//            }
//        }
//    }
}
