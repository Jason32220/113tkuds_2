import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
            left = right = null;
        }
    }

    // 1. 找出所有根到葉的路徑
    public static void printAllPaths(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        System.out.println("所有根到葉節點的路徑：");
        dfsPaths(root, path);
    }

    private static void dfsPaths(TreeNode node, List<Integer> path) {
        if (node == null) return;

        path.add(node.val);

        if (node.left == null && node.right == null) {
            System.out.println(path);
        } else {
            dfsPaths(node.left, path);
            dfsPaths(node.right, path);
        }

        path.remove(path.size() - 1); // 回朔
    }

    // 2. 是否有根到葉的路徑總和等於 target
    public static boolean hasPathSum(TreeNode root, int target) {
        if (root == null) return false;

        if (root.left == null && root.right == null) {
            return root.val == target;
        }

        return hasPathSum(root.left, target - root.val) ||
               hasPathSum(root.right, target - root.val);
    }

    // 3. 找出最大總和的根到葉路徑
    static int maxSum = Integer.MIN_VALUE;
    static List<Integer> maxPath = new ArrayList<>();

    public static void maxRootToLeafPath(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        maxSum = Integer.MIN_VALUE;
        maxPath = new ArrayList<>();
        dfsMaxSum(root, 0, path);

        System.out.println("最大總和根到葉路徑為：" + maxPath);
        System.out.println("總和為：" + maxSum);
    }

    private static void dfsMaxSum(TreeNode node, int sum, List<Integer> path) {
        if (node == null) return;

        path.add(node.val);
        sum += node.val;

        if (node.left == null && node.right == null) {
            if (sum > maxSum) {
                maxSum = sum;
                maxPath = new ArrayList<>(path);
            }
        } else {
            dfsMaxSum(node.left, sum, path);
            dfsMaxSum(node.right, sum, path);
        }

        path.remove(path.size() - 1); // 回朔
    }

    // 4. 計算任意兩節點最大路徑總和（樹的直徑/最大路徑和）
    static int globalMax = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        globalMax = Integer.MIN_VALUE;
        maxGain(root);
        return globalMax;
    }

    private static int maxGain(TreeNode node) {
        if (node == null) return 0;

        int left = Math.max(0, maxGain(node.left));
        int right = Math.max(0, maxGain(node.right));

        int currentPathSum = node.val + left + right;
        globalMax = Math.max(globalMax, currentPathSum);

        return node.val + Math.max(left, right); // 回傳往上的最大貢獻
    }

    // 測試樹建立
    public static TreeNode buildSampleTree() {
        //       5
        //     /   \
        //    4     8
        //   /     / \
        //  11    13  4
        // /  \         \
        //7    2         1
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = buildSampleTree();

        // 1. 所有路徑
        printAllPaths(root);

        // 2. 是否存在總和為 22 的路徑
        int target = 22;
        boolean exists = hasPathSum(root, target);
        System.out.println("\n是否存在總和為 " + target + " 的根到葉路徑？" + (exists ? "是" : "否"));

        // 3. 最大總和路徑
        maxRootToLeafPath(root);

        // 4. 任意兩節點最大總和（直徑）
        int maxPath = maxPathSum(root);
        System.out.println("整棵樹中任意兩節點最大總和為：" + maxPath);
    }
}
