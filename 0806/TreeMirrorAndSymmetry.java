public class TreeMirrorAndSymmetry {

    // 節點類別
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 判斷是否為對稱樹（左右子樹為鏡像）
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) &&
                isMirror(t1.left, t2.right) &&
                isMirror(t1.right, t2.left);
    }

    // 2. 將一棵樹轉為鏡像樹
    public static TreeNode mirror(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = mirror(root.right);
        root.right = mirror(temp);
        return root;
    }

    // 3. 比較兩棵樹是否互為鏡像
    public static boolean isMirrorTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a.val == b.val) &&
                isMirrorTree(a.left, b.right) &&
                isMirrorTree(a.right, b.left);
    }

    // 4. 檢查一棵樹是否為另一棵樹的子樹
    public static boolean isSubtree(TreeNode main, TreeNode sub) {
        if (main == null) return false;
        if (isSameTree(main, sub)) return true;
        return isSubtree(main.left, sub) || isSubtree(main.right, sub);
    }

    private static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a.val == b.val) &&
                isSameTree(a.left, b.left) &&
                isSameTree(a.right, b.right);
    }

    // 中序列印（驗證用）
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // 主程式測試
    public static void main(String[] args) {
        // 建立原始樹
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹？" + (isSymmetric(root) ? "是" : "否"));

        System.out.println("\n鏡像前（中序）：");
        printInOrder(root);

        mirror(root);
        System.out.println("\n鏡像後（中序）：");
        printInOrder(root);

        // 測試互為鏡像
        TreeNode treeA = new TreeNode(1);
        treeA.left = new TreeNode(2);
        treeA.right = new TreeNode(3);

        TreeNode treeB = new TreeNode(1);
        treeB.left = new TreeNode(3);
        treeB.right = new TreeNode(2);

        System.out.println("\n\n兩棵樹是否互為鏡像？" + (isMirrorTree(treeA, treeB) ? "是" : "否"));

        // 測試是否為子樹
        TreeNode mainTree = new TreeNode(1);
        mainTree.left = new TreeNode(2);
        mainTree.right = new TreeNode(3);
        mainTree.left.left = new TreeNode(4);

        TreeNode subTree = new TreeNode(2);
        subTree.left = new TreeNode(4);

        System.out.println("是否為子樹？" + (isSubtree(mainTree, subTree) ? "是" : "否"));
    }
}
