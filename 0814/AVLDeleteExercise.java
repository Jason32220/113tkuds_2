// 檔名：AVLDeleteExercise.java
// 功能：實作 AVL 樹的刪除操作 (葉子、單子節點、雙子節點)

class AVLNode {
    int key;              // 節點值
    int height;           // 節點高度
    AVLNode left, right;  // 左右子節點

    AVLNode(int key) {
        this.key = key;
        this.height = 1; // 新節點高度預設為 1
    }
}

public class AVLDeleteExercise {
    private AVLNode root;

    // 取得節點高度
    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    // 更新節點高度
    private void updateHeight(AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    // 計算平衡因子
    private int getBalance(AVLNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    // 右旋
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // 左旋
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // 插入節點 (方便測試刪除)
    private AVLNode insert(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // 不允許重複

        updateHeight(node);

        return rebalance(node, key);
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    // 重新平衡 (插入或刪除後都會用到)
    private AVLNode rebalance(AVLNode node, int key) {
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

    // 找右子樹的最小值 (後繼)
    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // 刪除節點
    private AVLNode delete(AVLNode node, int key) {
        if (node == null) return null;

        // 標準 BST 刪除
        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            // 找到要刪的節點
            if (node.left == null || node.right == null) {
                // 只有一個子節點 或 無子節點
                node = (node.left != null) ? node.left : node.right;
            } else {
                // 兩個子節點 → 找後繼 (右子樹最小值)
                AVLNode temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        }

        if (node == null) return null; // 如果已刪光，直接回傳

        // 更新高度
        updateHeight(node);

        // 重新平衡
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    // 中序遍歷 (確認樹結構)
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // --- 測試 ---
    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();

        // 插入一些數字
        int[] keys = {50, 30, 70, 20, 40, 60, 80};
        for (int k : keys) tree.insert(k);

        System.out.print("初始樹 (中序): ");
        tree.inorder();

        // 1. 刪除葉子節點
        tree.delete(20);
        System.out.print("刪除葉子節點 20: ");
        tree.inorder();

        // 2. 刪除只有一個子節點的節點
        tree.delete(30);
        System.out.print("刪除只有一個子節點的 30: ");
        tree.inorder();

        // 3. 刪除有兩個子節點的節點
        tree.delete(50);
        System.out.print("刪除有兩個子節點的 50: ");
        tree.inorder();
    }
}
