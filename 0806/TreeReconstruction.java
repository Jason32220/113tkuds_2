public class TreeReconstruction {

    // 節點定義
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int v) {
            value = v;
            left = null;
            right = null;
        }
    }

    // === 1. 前序 + 中序 ===
    public static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        return buildPreIn(preorder, inorder, 0, 0, inorder.length - 1);
    }

    public static TreeNode buildPreIn(int[] preorder, int[] inorder, int preIndex, int inStart, int inEnd) {
        if (inStart > inEnd) return null;

        int rootValue = preorder[preIndex];
        TreeNode root = new TreeNode(rootValue);

        int rootPos = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                rootPos = i;
                break;
            }
        }

        int leftCount = rootPos - inStart;

        root.left = buildPreIn(preorder, inorder, preIndex + 1, inStart, rootPos - 1);
        root.right = buildPreIn(preorder, inorder, preIndex + 1 + leftCount, rootPos + 1, inEnd);

        return root;
    }

    // === 2. 後序 + 中序 ===
    public static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
        return buildPostIn(postorder, inorder, postorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode buildPostIn(int[] postorder, int[] inorder, int postIndex, int inStart, int inEnd) {
        if (inStart > inEnd) return null;

        int rootValue = postorder[postIndex];
        TreeNode root = new TreeNode(rootValue);

        int rootPos = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                rootPos = i;
                break;
            }
        }

        int rightCount = inEnd - rootPos;

        root.right = buildPostIn(postorder, inorder, postIndex - 1, rootPos + 1, inEnd);
        root.left = buildPostIn(postorder, inorder, postIndex - rightCount - 1, inStart, rootPos - 1);

        return root;
    }

    // === 3. 層序重建完全二元樹 ===
    public static TreeNode buildFromLevelOrder(int[] arr) {
        if (arr.length == 0) return null;

        TreeNode[] nodes = new TreeNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new TreeNode(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            int leftIdx = 2 * i + 1;
            int rightIdx = 2 * i + 2;
            if (leftIdx < arr.length) nodes[i].left = nodes[leftIdx];
            if (rightIdx < arr.length) nodes[i].right = nodes[rightIdx];
        }

        return nodes[0];
    }

    // === 驗證用：中序列印 ===
    public static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.value + " ");
        printInorder(node.right);
    }

    // === 驗證用：層序列印 ===
    public static void printLevelOrder(TreeNode root) {
        if (root == null) return;

        TreeNode[] queue = new TreeNode[100];
        int front = 0, rear = 0;
        queue[rear++] = root;

        while (front < rear) {
            TreeNode node = queue[front++];
            System.out.print(node.value + " ");
            if (node.left != null) queue[rear++] = node.left;
            if (node.right != null) queue[rear++] = node.right;
        }
    }

    // === 主程式測試 ===
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder =  {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] levelorder = {1, 2, 3, 4, 5, 6, 7};

        System.out.println("【前序 + 中序 建樹】");
        TreeNode t1 = buildFromPreIn(preorder, inorder);
        printInorder(t1);
        System.out.println();

        System.out.println("【後序 + 中序 建樹】");
        TreeNode t2 = buildFromPostIn(postorder, inorder);
        printInorder(t2);
        System.out.println();

        System.out.println("【層序重建完全二元樹】");
        TreeNode t3 = buildFromLevelOrder(levelorder);
        printLevelOrder(t3);
        System.out.println();
    }
}
