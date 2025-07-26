package terralogicCoding;

import java.util.*;

public class BestCostSearch {

    // Inner class for Priority Queue state
    static class NodeState {
        TreeNode node;
        int cost;
        List<String> path;

        NodeState(TreeNode node, int cost, List<String> path) {
            this.node = node;
            this.cost = cost;
            this.path = path;
        }
    }

    public static SearchResult search(TreeNode root, String target) {
        if (root == null) return new SearchResult(false, -1, Collections.emptyList());

        // Min-Heap based on total cost so far
        PriorityQueue<NodeState> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.add(new NodeState(root, root.weight + root.depth, new ArrayList<>(Arrays.asList(root.data))));

        while (!pq.isEmpty()) {
            NodeState current = pq.poll();

            // Check if target found
            if (current.node.data.equals(target)) {
                return new SearchResult(true, current.cost, current.path);
            }

            // Add children with cumulative cost and path
            for (TreeNode child : current.node.children) {
                List<String> newPath = new ArrayList<>(current.path);
                newPath.add(child.data);
                int newCost = current.cost + child.weight + child.depth;
                pq.add(new NodeState(child, newCost, newPath));
            }
        }

        return new SearchResult(false, -1, Collections.emptyList());
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("A", 3, 0);

        TreeNode b = new TreeNode("B", 2, 1);
        TreeNode c = new TreeNode("C", 1, 1);
        TreeNode d = new TreeNode("D", 4, 1);
        root.addChild(b);
        root.addChild(c);
        root.addChild(d);

        // Generate deeper levels for B, C, D
        generateTreeLevel(b, 3);
        generateTreeLevel(c, 3);
        generateTreeLevel(d, 3);


        // Example search
        System.out.println("Enter the String to caluaclate");
        Scanner sc = new Scanner(System.in);
        String target = sc.next();
        target = target.toUpperCase();
        SearchResult result = search(root, target);
        System.out.println(result);
    }

    // Recursive level generator (3 children per node up to maxDepth)
    private static void generateTreeLevel(TreeNode parent, int maxDepth) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(parent);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.depth >= maxDepth) continue;

            for (int i = 0; i < 3; i++) {
                char suffix = (char) ('A' + i);
                String childName = node.data + "X" + suffix;
                TreeNode child = new TreeNode(childName, (int)(Math.random() * 10), node.depth + 1);
                node.addChild(child);
                q.add(child);
            }
        }
    }

}
