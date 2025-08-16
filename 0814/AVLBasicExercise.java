// 檔名：AVLBasicExercise.java
// 功能：實作簡化版 AVL 樹，包含插入、搜尋、高度計算與檢查 AVL 有效性

class AVLNode {
    int key;              // 節點的值
    int height;           // 節點高度
    AVLNode left, right;  // 左右子樹

    AVLNode(int key) {
        this.key = key;
        this.height = 1;  // 新節點高度預設為 1
    }
}

public class AVLBasicExercise {
    private AVLNode root;

    // 取得節點高度，空節點高度為 0
    private int height(AVLNode node) {
        if (node == null) return 0;
        return node.height;
    }

    // 計算平衡因子 = 左子樹高度 - 右子樹高度
    private int getBalance(AVLNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    // 更新節點高度
    private void updateHeight(AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    // --- 四種旋轉操作 ---
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 執行旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        updateHeight(y);
        updateHeight(x);

        return x; // 新的子樹根
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 執行旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        updateHeight(x);
        updateHeight(y);

        return y; // 新的子樹根
    }

    // 插入節點並保持平衡
    private AVLNode insertNode(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);

        // 標準 BST 插入
        if (key < node.key) {
            node.left = insertNode(node.left, key);
        } else if (key > node.key) {
            node.right = insertNode(node.right, key);
        } else {
            return node; // 不允許重複鍵
        }

        // 更新高度
        updateHeight(node);

        // 計算平衡因子
        int balance = getBalance(node);

        // --- 4 種不平衡情況 ---
        // LL case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }
        // RR case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }
        // LR case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // 不需旋轉
    }

    public void insert(int key) {
        root = insertNode(root, key);
    }

    // 搜尋節點
    public boolean search(int key) {
        return searchNode(root, key);
    }

    private boolean searchNode(AVLNode node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        if (key < node.key) return searchNode(node.left, key);
        return searchNode(node.right, key);
    }

    // 計算樹的高度
    public int getHeight() {
        return height(root);
    }

    // 檢查是否為 AVL 樹
    public boolean isAVL() {
        return checkAVL(root);
    }

    private boolean checkAVL(AVLNode node) {
        if (node == null) return true;
        int balance = getBalance(node);
        if (balance < -1 || balance > 1) return false;
        return checkAVL(node.left) && checkAVL(node.right);
    }

    // --- 測試 ---
    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();

        // 插入節點
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        // 搜尋
        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 15: " + tree.search(15));

        // 高度
        System.out.println("樹的高度: " + tree.getHeight());

        // 檢查 AVL
        System.out.println("是否為 AVL 樹: " + tree.isAVL());
    }
}
