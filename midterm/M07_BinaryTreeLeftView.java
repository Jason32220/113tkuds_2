import java.util.*;

public class M07_BinaryTreeLeftView {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    /*
     * 從層序輸入建立二元樹
     *
     * Time Complexity: O(n)
     * 說明：
     * - 每個元素最多處理一次 (建立節點或忽略 -1)。
     * - 用 queue 依序連接左右子節點 → O(n)。
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
     * BFS 左視圖輸出
     *
     * Time Complexity: O(n)
     * 說明：
     * - 每個節點進出佇列各一次 → O(n)。
     * - 每層第一個節點記錄到結果 → O(1)。
     */
    private static List<Integer> leftView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (i == 0) res.add(cur.val); // 每層第一個

                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        TreeNode root = buildTree(arr);
        List<Integer> view = leftView(root);

        System.out.print("LeftView:");
        for (int v : view) {
            System.out.print(" " + v);
        }
        System.out.println();
    }
}

/*
 * Overall Time Complexity: O(n)
 * 說明：
 * - 建樹 O(n)
 * - BFS O(n)
 * - 總體為 O(n)
 */
