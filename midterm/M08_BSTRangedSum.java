import java.util.*;

public class M08_BSTRangedSum {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    /*
     * 從層序輸入建立 BST（-1 表 null）
     *
     * Time Complexity: O(n)
     * 說明：
     * - 每個元素最多處理一次 (建立節點或忽略)。
     * - 使用 queue 層序連接 → O(n)。
     */
    private static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode cur = q.poll();

            if (i < arr.length && arr[i] != -1) {
                cur.left = new TreeNode(arr[i]);
                q.add(cur.left);
            }
            i++;

            if (i < arr.length && arr[i] != -1) {
                cur.right = new TreeNode(arr[i]);
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    /*
     * 區間總和 (DFS with pruning)
     *
     * Time Complexity: O(h + k)
     * 說明：
     * - h = 樹高, k = 區間內節點數。
     * - 若 val < L → 只走右子樹；若 val > R → 只走左子樹。
     * - 每個節點至多被訪問一次，最壞退化 O(n)，平均情況更快。
     */
    private static int rangeSum(TreeNode root, int L, int R) {
        if (root == null) return 0;

        if (root.val < L) {
            return rangeSum(root.right, L, R);
        } else if (root.val > R) {
            return rangeSum(root.left, L, R);
        } else {
            return root.val + rangeSum(root.left, L, R) + rangeSum(root.right, L, R);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();

        TreeNode root = buildTree(arr);
        int sum = rangeSum(root, L, R);

        System.out.println("Sum: " + sum);
    }
}

/*
 * Overall Time Complexity: O(h + k)
 * 說明：
 * - h = 樹高，k = 區間內節點數。
 * - 利用 BST 特性剪枝，不會遍歷整棵樹。
 * - 最壞情況退化為 O(n)，平均情況更快。
 */
