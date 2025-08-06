public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
            left = right = null;
        }
    }

    // -------------------
    // 1. BST 轉雙向鏈結串列（中序線索化）
    // 返回雙向鏈結串列的頭節點
    static TreeNode prev = null; // 用來連結節點
    static TreeNode head = null;

    public static TreeNode bstToDoublyList(TreeNode root) {
        prev = null;
        head = null;
        inorderConvert(root);
        return head;
    }

    private static void inorderConvert(TreeNode node) {
        if (node == null) return;

        inorderConvert(node.left);

        if (prev == null) {
            head = node; // 最左端節點為頭
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;

        inorderConvert(node.right);
    }

    // -------------------
    // 2. 排序陣列轉平衡 BST
    public static TreeNode sortedArrayToBST(int[] arr) {
        return sortedToBST(arr, 0, arr.length - 1);
    }

    private static TreeNode sortedToBST(int[] arr, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = sortedToBST(arr, start, mid - 1);
        node.right = sortedToBST(arr, mid + 1, end);
        return node;
    }

    // -------------------
    // 3. 檢查BST是否平衡 並回傳高度
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1;

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // -------------------
    // 3-2. 計算節點平衡因子（左子樹高度 - 右子樹高度）
    public static int balanceFactor(TreeNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // -------------------
    // 4. BST 節點值改為所有大於等於該節點值的總和
    //（Reverse Inorder Traversal）
    static int sumSoFar = 0;

    public static void convertBST(TreeNode root) {
        sumSoFar = 0;
        reverseInorder(root);
    }

    private static void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        sumSoFar += node.val;
        node.val = sumSoFar;
        reverseInorder(node.left);
    }

    // -------------------
    // 輔助：中序列印
    public static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }

    // 輔助：印雙向鏈結串列（從頭開始往右走）
    public static void printDoublyList(TreeNode head) {
        System.out.print("雙向鏈結串列：");
        TreeNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
        System.out.println();
    }

    // 測試程式
    public static void main(String[] args) {
        // 建立 BST
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        System.out.println("原始 BST 中序走訪:");
        printInorder(root);
        System.out.println();

        // 1. 轉雙向鏈結串列
        TreeNode head = bstToDoublyList(root);
        printDoublyList(head);

        // 2. 排序陣列轉平衡 BST
        int[] sortedArr = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedRoot = sortedArrayToBST(sortedArr);
        System.out.println("平衡 BST 中序走訪:");
        printInorder(balancedRoot);
        System.out.println();

        // 3. 檢查是否平衡
        System.out.println("BST 是否平衡? " + isBalanced(balancedRoot));
        System.out.println("根節點平衡因子: " + balanceFactor(balancedRoot));

        // 4. 節點值轉換成累加樹
        convertBST(root);
        System.out.println("累加樹中序走訪:");
        printInorder(root);
        System.out.println();
    }
}
