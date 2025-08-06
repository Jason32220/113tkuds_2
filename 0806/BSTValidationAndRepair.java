import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
            left = right = null;
        }
    }

    // 1. 驗證是否為有效 BST
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBSTHelper(TreeNode node, long min, long max) {
        if (node == null) return true;

        if (node.val <= min || node.val >= max) return false;

        return isValidBSTHelper(node.left, min, node.val) &&
               isValidBSTHelper(node.right, node.val, max);
    }

    // 2. 找出錯置的兩個節點（中序遍歷錯誤對）
    static TreeNode first = null, second = null, prev = new TreeNode(Integer.MIN_VALUE);

    public static void findSwappedNodes(TreeNode root) {
        first = second = null;
        prev = new TreeNode(Integer.MIN_VALUE);
        inorderFind(root);
    }

    private static void inorderFind(TreeNode node) {
        if (node == null) return;

        inorderFind(node.left);

        if (prev != null && node.val < prev.val) {
            if (first == null) first = prev;
            second = node;
        }

        prev = node;
        inorderFind(node.right);
    }

    // 3. 修復錯誤的 BST（只支援兩個節點互換）
    public static void recoverBST(TreeNode root) {
        findSwappedNodes(root);
        if (first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }

    // 4. 最少刪除多少個節點，讓中序結果升序（Longest Increasing Subsequence）
    public static int minRemovalsToBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);
        int lisLength = lengthOfLIS(inorder);
        return inorder.size() - lisLength;
    }

    private static void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }

    // 最長遞增子序列（LIS）演算法
    private static int lengthOfLIS(List<Integer> nums) {
        List<Integer> dp = new ArrayList<>();
        for (int num : nums) {
            int idx = Collections.binarySearch(dp, num);
            if (idx < 0) idx = -(idx + 1);
            if (idx == dp.size()) dp.add(num);
            else dp.set(idx, num);
        }
        return dp.size();
    }

    // 中序列印
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // 測試資料
    public static TreeNode buildIncorrectBST() {
        // 正常應為：1 - 2 - 3 - 4 - 5 - 6 - 7
        // 故意交換 3 和 5
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5); // 應該是 3
        root.right.left = new TreeNode(3); // 應該是 5
        root.right.right = new TreeNode(7);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = buildIncorrectBST();

        System.out.print("中序遍歷（錯誤 BST）：");
        printInorder(root);
        System.out.println();

        System.out.println("✅ 是有效 BST？ " + isValidBST(root));

        // 找出錯誤節點
        findSwappedNodes(root);
        if (first != null && second != null)
            System.out.println("❗ 錯誤節點是：" + first.val + " 和 " + second.val);

        // 修復
        recoverBST(root);
        System.out.print("✅ 修復後中序：");
        printInorder(root);
        System.out.println("\n✅ 是有效 BST？ " + isValidBST(root));

        // 測試最少刪除節點
        TreeNode badTree = new TreeNode(10);
        badTree.left = new TreeNode(5);
        badTree.right = new TreeNode(15);
        badTree.left.right = new TreeNode(12); // 錯誤插入
        System.out.print("\n中序（亂序）：");
        printInorder(badTree);
        int remove = minRemovalsToBST(badTree);
        System.out.println("\n最少需要移除 " + remove + " 個節點來變成合法 BST。");
    }
}
