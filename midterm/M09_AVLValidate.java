import java.util.*;

public class M09_AVLValidate {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    /*
     * 從層序輸入建立二元樹（-1 表 null）
     *
     * Time Complexity: O(n)
     * 說明：
     * - 每個節點最多處理一次 → O(n)
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
     * 檢查 BST
     *
     * Time Complexity: O(n)
     * 說明：
     * - 每個節點訪問一次 → O(n)
     */
    private static boolean isBST(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    /*
     * 檢查 AVL，回傳高度；若非法返回 -1
     *
     * Time Complexity: O(n)
     * 說明：
     * - 後序遍歷每個節點一次
     */
    private static int checkAVL(TreeNode root) {
        if (root == null) return 0;
        int lh = checkAVL(root.left);
        int rh = checkAVL(root.right);
        if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) return -1;
        return 1 + Math.max(lh, rh);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        TreeNode root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (checkAVL(root) == -1) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }
}

/*
 * Overall Time Complexity: O(n)
 * 說明：
 * - 建樹 O(n)
 * - 檢查 BST O(n)
 * - 檢查 AVL O(n)
 * - 總體為 O(n)
 */
