package org.example.algomonster.graphs_weighted;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Implement Dijkstra's shorted path in a directed weighted graph.
 *
 */
public class DijkstrasShortestPathAlgorithm {
    public static int findShortestPath(List<List<Entry<Integer, Integer>>> graph, int source, int target) {
        PriorityQueue<Entry<Integer, Integer>> q = new PriorityQueue<>(graph.size(),
                (a, b) -> Integer.compare(a.getValue(), b.getValue()));
        Map<Integer, Integer> distanceMap = new HashMap<>();
        for (int i = 0; i < graph.size(); i++) {
            if (i == source) {
                q.add(Map.entry(i, 0));
            } else {
                q.add(Map.entry(i, Integer.MAX_VALUE));
            }
            distanceMap.put(i, Integer.MAX_VALUE);
        }
        while(!q.isEmpty()) {
            Entry<Integer, Integer> currentNode = q.poll();
            int currentNodeId = currentNode.getKey();
            int currentNodeDistance = currentNode.getValue();

            //  MAKE NOTE: IF YOU COMPARE THE DIJKSTRA'S ALGORITHM FROM WIKIPEDIA. THIS LOGIC IS DIFFERENT. IN WHICH
            //  WE DO NOT UPDATE THE EXISTING QUEUE NODES WITH SHORTEST PATHS, INSTEAD WE QUEUE A DUPLICATE ENTRY OF THE
            //  NODE IF WE FIND THE SHORTEST PATH WHILE CALCULATING EDGES. SO, IF WE SEE A NODE WHOSE DISTANCE IS
            //  GREATER THAN ALREADY RECORDED DISTANCE IN DISTANCEMAP WE SKIP THAT NODE.
            if (distanceMap.get(currentNodeId) < currentNodeDistance) continue; // skip processing edges.
            distanceMap.put(currentNodeId, currentNodeDistance);
            List<Entry<Integer, Integer>> edges = graph.get(currentNode.getKey());
            for (Entry<Integer, Integer> edge : edges) {
                // IF THE DISTANCE FROM SOURCE TO THIS EDGE IS LESS THAN PREVIOUSLY RECORDED DISTANCE, THEN
                // WE QUEUE THE EDGE WITH THE NEW SHORTEST DISTANCE.
                if ( currentNodeDistance + edge.getValue() < distanceMap.get(edge.getKey())) {
                    q.add(Map.entry(edge.getKey(), currentNodeDistance + edge.getValue()));
                }
            }
        }
        return distanceMap.get(target) == Integer.MAX_VALUE ? -1 : distanceMap.get(target);
    }
}
