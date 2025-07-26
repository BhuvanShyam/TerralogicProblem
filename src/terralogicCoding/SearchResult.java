package terralogicCoding;

import java.util.List;

class SearchResult {
    boolean found;
    int cost;
    List<String> path;

    public SearchResult(boolean found, int cost, List<String> path) {
        this.found = found;
        this.cost = cost;
        this.path = path;
    }

    @Override
    public String toString() {
        if (!found) return "Target not found";
        return "Found target with cost: " + cost + "\nPath: " + path;
    }
}
