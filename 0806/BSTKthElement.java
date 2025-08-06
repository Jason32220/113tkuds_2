public class BSTKthElement {

    // 定義 BST 節點，附帶左子樹節點數 size
    static class TreeNode {
        int val;
        int size; // 左子樹節點數（含自己）
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    // 插入節點，並更新 size
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        root.size = 1 + getSize(root.left) + getSize(root.right);
        return root;
    }

    // 刪除節點（簡化版，僅調整大小，實際情況可做補強）
    public static TreeNode delete(TreeNode root, int val) {
        if (root == null) return null;

        if (val < root.val) {
            root.left = delete(root.left, val);
        } else if (val > root.val) {
            root.right = delete(root.right, val);
        } else {
            // 只有一邊或都無子樹
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // 兩邊都有，取右邊最小值替代
            TreeNode successor = findMin(root.right);
            root.val = successor.val;
            root.right = delete(root.right, successor.val);
        }

        root.size = 1 + getSize(root.left) + getSize(root.right);
        return root;
    }

    public static TreeNode findMin(TreeNode root) {
        while (root.left != null) root = root.left;
        return root;
    }

    public static int getSize(TreeNode node) {
        return node == null ? 0 : node.size;
    }

    // 1. 找出第 k 小的元素
    public static int findKthSmallest(TreeNode root, int k) {
        if (root == null) throw new IllegalArgumentException("樹為空");

        int leftSize = getSize(root.left);

        if (k <= leftSize) return findKthSmallest(root.left, k);
        else if (k == leftSize + 1) return root.val;
        else return findKthSmallest(root.right, k - leftSize - 1);
    }

    // 2. 找出第 k 大的元素
    public static int findKthLargest(TreeNode root, int k) {
        int total = getSize(root);
        return findKthSmallest(root, total - k + 1);
    }

    // 3. 找出第 k 小到第 j 小之間所有元素
    public static void findKtoJ(TreeNode root, int k, int j, int[] counter, java.util.List<Integer> result) {
        if (root == null) return;

        findKtoJ(root.left, k, j, counter, result);
        counter[0]++;
        if (counter[0] >= k && counter[0] <= j) result.add(root.val);
        if (counter[0] > j) return;
        findKtoJ(root.right, k, j, counter, result);
    }

    // 主程式測試
    public static void main(String[] args) {
        int[] values = {20, 8, 22, 4, 12, 10, 14};
        TreeNode root = null;

        for (int val : values) {
            root = insert(root, val);
        }

        System.out.println("第 3 小元素是：" + findKthSmallest(root, 3));
        System.out.println("第 2 大元素是：" + findKthLargest(root, 2));

        java.util.List<Integer> rangeResult = new java.util.ArrayList<>();
        findKtoJ(root, 2, 5, new int[]{0}, rangeResult);
        System.out.println("第 2 小到第 5 小的元素：" + rangeResult);

        System.out.println("\n[插入 7]");
        root = insert(root, 7);
        System.out.println("第 3 小元素是：" + findKthSmallest(root, 3));

        System.out.println("\n[刪除 10]");
        root = delete(root, 10);
        System.out.println("第 3 小元素是：" + findKthSmallest(root, 3));
    }
}
