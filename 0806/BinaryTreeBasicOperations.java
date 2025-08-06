import java.util.*;

public class BinaryTreeBasicOperations {

    // 二元樹節點類別
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 計算總和與平均值
    static int totalSum = 0;
    static int totalCount = 0;

    public static void sumAndCount(TreeNode root) {
        if (root == null) return;
        totalSum += root.val;
        totalCount++;
        sumAndCount(root.left);
        sumAndCount(root.right);
    }

    // 2. 找最大值與最小值
    public static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }

    public static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

    // 3. 計算最大寬度（每層最多節點數）
    public static int maxWidth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // 當前層節點數
            maxWidth = Math.max(maxWidth, levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return maxWidth;
    }

    // 4. 判斷是否為完全二元樹
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false; // 一旦遇到空節點，後面都應該是空

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                end = true;
            } else {
                if (end) return false; // 如果前面出現null，後面又不是null，則不是完全樹
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }

    // 主程式測試
    public static void main(String[] args) {
        /*
                範例樹：
                      10
                     /  \
                    5    15
                   / \   /
                  3   7 12
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);

        // 總和與平均
        sumAndCount(root);
        System.out.println("節點總和: " + totalSum);
        System.out.println("節點平均值: " + ((double) totalSum / totalCount));

        // 最大與最小
        System.out.println("最大值: " + findMax(root));
        System.out.println("最小值: " + findMin(root));

        // 樹的寬度
        System.out.println("最大寬度（節點最多的層）: " + maxWidth(root));

        // 是否為完全二元樹
        System.out.println("是否為完全二元樹: " + (isCompleteTree(root) ? "是" : "否"));
    }
}
