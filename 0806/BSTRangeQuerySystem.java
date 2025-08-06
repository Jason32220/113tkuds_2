import java.util.*;

public class BSTRangeQuerySystem {

    // 二元搜尋樹節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 插入節點
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);
        return root;
    }

    // 1. 範圍查詢：回傳在 [min, max] 範圍內的所有節點
    public static void rangeQuery(TreeNode root, int min, int max, List<Integer> result) {
        if (root == null) return;

        if (root.val > min) {
            rangeQuery(root.left, min, max, result);
        }
        if (root.val >= min && root.val <= max) {
            result.add(root.val);
        }
        if (root.val < max) {
            rangeQuery(root.right, min, max, result);
        }
    }

    // 2. 範圍計數
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;

        if (root.val < min) {
            return rangeCount(root.right, min, max);
        } else if (root.val > max) {
            return rangeCount(root.left, min, max);
        } else {
            return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
        }
    }

    // 3. 範圍總和
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;

        if (root.val < min) {
            return rangeSum(root.right, min, max);
        } else if (root.val > max) {
            return rangeSum(root.left, min, max);
        } else {
            return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
        }
    }

    // 4. 找出最接近目標值的節點
    public static int closestValue(TreeNode root, int target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            if (target < root.val) {
                root = root.left;
            } else if (target > root.val) {
                root = root.right;
            } else {
                return root.val; // 完全相等，直接回傳
            }
        }
        return closest;
    }

    // 主程式測試
    public static void main(String[] args) {
        int[] values = {10, 5, 1, 7, 15, 12, 20};
        TreeNode root = null;

        for (int val : values) {
            root = insert(root, val);
        }

        int min = 6, max = 16;

        System.out.println("=== 範圍查詢 [6,16] ===");
        List<Integer> rangeResult = new ArrayList<>();
        rangeQuery(root, min, max, rangeResult);
        System.out.println("節點值: " + rangeResult);

        System.out.println("\n=== 範圍計數 [6,16] ===");
        System.out.println("數量: " + rangeCount(root, min, max));

        System.out.println("\n=== 範圍總和 [6,16] ===");
        System.out.println("總和: " + rangeSum(root, min, max));

        int target = 13;
        System.out.println("\n=== 最接近查詢，目標值: " + target + " ===");
        System.out.println("最接近節點值: " + closestValue(root, target));
    }
}
