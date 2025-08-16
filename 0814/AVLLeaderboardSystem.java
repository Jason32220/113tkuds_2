import java.util.*;

class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

class AVLNode {
    Player player;
    AVLNode left, right;
    int height;
    int size; // 子樹節點數，用於排名

    AVLNode(Player player) {
        this.player = player;
        this.height = 1;
        this.size = 1;
    }
}

public class AVLLeaderboardSystem {
    private AVLNode root;

    // ====== AVL 工具函數 ======
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int size(AVLNode node) {
        return node == null ? 0 : node.size;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private void update(AVLNode node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = 1 + size(node.left) + size(node.right);
        }
    }

    // ====== AVL 旋轉 ======
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        update(y);
        update(x);

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        update(x);
        update(y);

        return y;
    }

    // ====== 插入 / 更新分數 ======
    private AVLNode insert(AVLNode node, Player player) {
        if (node == null) return new AVLNode(player);

        // AVL 用 score 排名，分數高排前
        if (player.score > node.player.score) {
            node.left = insert(node.left, player);
        } else if (player.score < node.player.score) {
            node.right = insert(node.right, player);
        } else {
            // 分數相同時按名字排序，避免覆蓋
            if (player.name.compareTo(node.player.name) < 0)
                node.left = insert(node.left, player);
            else
                node.right = insert(node.right, player);
        }

        update(node);

        int balance = getBalance(node);

        // 左左
        if (balance > 1 && player.score > node.left.player.score)
            return rightRotate(node);

        // 右右
        if (balance < -1 && player.score < node.right.player.score)
            return leftRotate(node);

        // 左右
        if (balance > 1 && player.score < node.left.player.score) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 右左
        if (balance < -1 && player.score > node.right.player.score) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void addOrUpdatePlayer(String name, int score) {
        // 若玩家已存在，刪除舊分數
        removePlayer(name);
        root = insert(root, new Player(name, score));
    }

    // ====== 刪除節點（更新分數時用） ======
    private AVLNode remove(AVLNode node, String name) {
        if (node == null) return null;

        if (name.equals(node.player.name)) {
            // 有一個子節點或無子節點
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // 有兩個子節點，找前驅
            AVLNode pred = node.left;
            while (pred.right != null) pred = pred.right;
            node.player = pred.player;
            node.left = remove(node.left, pred.player.name);
        } else {
            node.left = remove(node.left, name);
            node.right = remove(node.right, name);
        }

        update(node);
        int balance = getBalance(node);

        // 左左
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // 左右
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 右右
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // 右左
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void removePlayer(String name) {
        root = remove(root, name);
    }

    // ====== 查詢排名 ======
    public int getRank(String name) {
        return 1 + getRank(root, name);
    }

    private int getRank(AVLNode node, String name) {
        if (node == null) return 0;

        if (name.equals(node.player.name)) {
            return size(node.left);
        }

        int left = getRank(node.left, name);
        if (left != 0 || (node.left != null && name.equals(node.left.player.name))) {
            return left;
        }

        return size(node.left) + 1 + getRank(node.right, name);
    }

    // ====== 查詢前 K 名玩家 ======
    public List<Player> topK(int k) {
        List<Player> result = new ArrayList<>();
        collectTopK(root, result, k);
        return result;
    }

    private void collectTopK(AVLNode node, List<Player> list, int k) {
        if (node == null || list.size() >= k) return;

        collectTopK(node.left, list, k);
        if (list.size() < k) list.add(node.player);
        collectTopK(node.right, list, k);
    }

    // ====== 測試範例 ======
    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();

        leaderboard.addOrUpdatePlayer("Alice", 90);
        leaderboard.addOrUpdatePlayer("Bob", 100);
        leaderboard.addOrUpdatePlayer("Charlie", 80);

        System.out.println("前 3 名：");
        for (Player p : leaderboard.topK(3)) {
            System.out.println(p.name + " - " + p.score);
        }

        System.out.println("\nBob 排名: " + leaderboard.getRank("Bob"));
        System.out.println("Alice 排名: " + leaderboard.getRank("Alice"));

        System.out.println("\n更新 Alice 分數 110");
        leaderboard.addOrUpdatePlayer("Alice", 110);

        System.out.println("前 3 名：");
        for (Player p : leaderboard.topK(3)) {
            System.out.println(p.name + " - " + p.score);
        }

        System.out.println("\nAlice 排名: " + leaderboard.getRank("Alice"));
    }
}
