import java.util.*;

public class LevelOrderTraversalVariations {

    // 樹的節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
            left = null;
            right = null;
        }
    }

    // 1. 每層節點放入不同 List 中
    public static void printEachLevel(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            System.out.print("第 " + level + " 層：");

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                System.out.print(node.val + " ");
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            System.out.println();
            level++;
        }
    }

    // 2. 之字形層序（奇數層右→左）
    public static void printZigzag(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;

        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            int[] temp = new int[size];

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                int index = leftToRight ? i : size - 1 - i;
                temp[index] = node.val;

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            System.out.print("第 " + level + " 層（之字）：");
            for (int val : temp) System.out.print(val + " ");
            System.out.println();

            leftToRight = !leftToRight;
            level++;
        }
    }

    // 3. 每層的最後一個節點
    public static void printLastOfEachLevel(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode last = null;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                last = node;

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            System.out.println("第 " + level + " 層最後一個節點：" + last.val);
            level++;
        }
    }

    // 4. 垂直層序走訪（用座標來看）
    static class NodeWithCol {
        TreeNode node;
        int col;

        NodeWithCol(TreeNode n, int c) {
            node = n;
            col = c;
        }
    }

    public static void printVerticalOrder(TreeNode root) {
        if (root == null) return;

        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        Queue<NodeWithCol> q = new LinkedList<>();
        q.offer(new NodeWithCol(root, 0));

        while (!q.isEmpty()) {
            NodeWithCol pair = q.poll();
            int col = pair.col;
            TreeNode node = pair.node;

            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(node.val);

            if (node.left != null) q.offer(new NodeWithCol(node.left, col - 1));
            if (node.right != null) q.offer(new NodeWithCol(node.right, col + 1));
        }

        System.out.println("垂直層序（從左到右列印）：");
        for (int key : map.keySet()) {
            System.out.print("垂直位置 " + key + "：");
            for (int v : map.get(key)) System.out.print(v + " ");
            System.out.println();
        }
    }

    // 建立一棵測試樹
    public static TreeNode buildSampleTree() {
        //       1
        //     /   \
        //    2     3
        //   / \   / \
        //  4   5 6   7
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = buildSampleTree();

        System.out.println("【1】每層一行：");
        printEachLevel(root);

        System.out.println("\n【2】之字形層序：");
        printZigzag(root);

        System.out.println("\n【3】每層最後一個節點：");
        printLastOfEachLevel(root);

        System.out.println("\n【4】垂直層序走訪：");
        printVerticalOrder(root);
    }
}
