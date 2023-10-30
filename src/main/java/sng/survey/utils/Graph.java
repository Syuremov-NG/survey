package sng.survey.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Component
public class Graph {
    private final Map<Long, HashMap<Long, Long>> adjacencyList = new HashMap<>();

    public boolean addEdge(Long parentId, Long value, Long childId) {

        adjacencyList.putIfAbsent(parentId, new HashMap<>());

        if (childId == null) {
            return true;
        }

        adjacencyList.get(parentId).put(value, childId);
        List<Long> oldOptions = new ArrayList<>(adjacencyList.get(parentId).keySet());

        return !oldOptions.contains(value) || !adjacencyList.containsKey(childId);
    }

    public List<Long> getCycle(Long startVertex) {
        List<Long> cycle = findCycle(startVertex, new HashMap<>(), new ArrayList<>());
        if (cycle.isEmpty()) {
            return cycle;
        }
        cycle.add(cycle.get(0));

        return cycle;
    }

    public List<Long> findCycle(Long startVertex, Map<Long, Boolean> visited, List<Long> path) {
        visited.put(startVertex, true);
        path.add(startVertex);

        if (!adjacencyList.containsKey(startVertex)) {
            path.remove(path.size() - 1);
            return new ArrayList<>();
        }

        for (Map.Entry<Long, Long> entry : (adjacencyList.get(startVertex)).entrySet()) {
            Long neighbor = entry.getValue();
            if (!visited.containsKey(neighbor)) {
                List<Long> cycle = findCycle(neighbor, visited, path);
                if (!cycle.isEmpty()) {
                    return cycle;
                }
            } else if (path.contains(neighbor)) {
                int start = path.indexOf(neighbor);
                return path.subList(start, path.size());
            }
        }

        return new ArrayList<>();
    }

    static class InvalidGraphType extends Exception {
        public InvalidGraphType(String message) {
            super(message);
        }
    }
}