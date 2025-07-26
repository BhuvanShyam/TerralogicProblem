package terralogicCoding;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    String data;
    int weight;
    int depth;
    List<TreeNode> children;

    public TreeNode(String data, int weight, int depth) {
        this.data = data;
        this.weight = weight;
        this.depth = depth;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}
