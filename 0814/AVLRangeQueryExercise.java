// 檔名：AVLRangeQueryExercise.java
// 功能：AVL 樹的範圍查詢 (Range Query)

import java.util.*;

class RangeNode {
    int key;
    int height;
    RangeNode left, right;

    RangeNode(int key) {
        this.key = key;
        this.height = 1;
    }
}

public class AVLRangeQueryExercise {
    private RangeNode root;

    // ====== 基本 AVL 操作 ======
    private int height(RangeNode node) {
        return (node == null) ? 0 : node.height;
    }

    private void updateHeight(RangeNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int getBalance(RangeNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private RangeNode rightRotate(RangeNode y) {
        RangeNode x = y.left;
        RangeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private RangeNode leftRotate(RangeNode x) {
        RangeNode y = x.right;
        RangeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private RangeNode insert(RangeNode node, int key) {
        if (node == null) return new RangeNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // 不允許重複

        updateHeight(node);

        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    // ====== 範圍查詢 ======
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private void rangeQueryHelper(RangeNode node, int min, int max, List<Integer> result) {
        if (node == null) return;

        // 左子樹可能有答案
        if (node.key > min) {
            rangeQueryHelper(node.left, min, max, result);
        }

        // 範圍內 → 加入結果
        if (node.key >= min && node.key <= max) {
            result.add(node.key);
        }

        // 右子樹可能有答案
        if (node.key < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }

    // 中序遍歷 (測試用)
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(RangeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // ====== 測試 ======
    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();

        // 插入測試資料
        int[] keys = {20, 10, 30, 5, 15, 25, 35};
        for (int k : keys) tree.insert(k);

        System.out.print("中序遍歷: ");
        tree.inorder(); // 應該是排序後: 5 10 15 20 25 30 35

        // 測試範圍查詢
        System.out.println("範圍查詢 [10, 30]: " + tree.rangeQuery(10, 30));
        System.out.println("範圍查詢 [15, 25]: " + tree.rangeQuery(15, 25));
        System.out.println("範圍查詢 [5, 40]: " + tree.rangeQuery(5, 40));
    }
}
