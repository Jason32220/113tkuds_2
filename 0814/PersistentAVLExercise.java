import java.util.*;

class PAVLNode {
    final int key;
    final PAVLNode left, right;
    final int height;

    PAVLNode(int key, PAVLNode left, PAVLNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(height(left), height(right));
    }

    private static int height(PAVLNode node) {
        return node == null ? 0 : node.height;
    }

    int balance() {
        return height(left) - height(right);
    }
}

public class PersistentAVLExercise {
    private List<PAVLNode> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null); // 版本 0 為空樹
    }

    // ====== 插入產生新版本 ======
    public void insert(int key) {
        PAVLNode newRoot = insert(versions.get(versions.size() - 1), key);
        versions.add(newRoot);
    }

    private PAVLNode insert(PAVLNode node, int key) {
        if (node == null) return new PAVLNode(key, null, null);

        PAVLNode newNode;
        if (key < node.key) newNode = new PAVLNode(node.key, insert(node.left, key), node.right);
        else if (key > node.key) newNode = new PAVLNode(node.key, node.left, insert(node.right, key));
        else return node;

        return balance(newNode);
    }

    // ====== 平衡操作 ======
    private PAVLNode balance(PAVLNode node) {
        int b = node.balance();

        if (b > 1) {
            if (node.left.balance() >= 0) return rotateRight(node);
            else return rotateLeftRight(node);
        }
        if (b < -1) {
            if (node.right.balance() <= 0) return rotateLeft(node);
            else return rotateRightLeft(node);
        }
        return node;
    }

    private PAVLNode rotateRight(PAVLNode y) {
        PAVLNode x = y.left;
        PAVLNode T2 = x.right;
        return new PAVLNode(x.key, x.left, new PAVLNode(y.key, T2, y.right));
    }

    private PAVLNode rotateLeft(PAVLNode x) {
        PAVLNode y = x.right;
        PAVLNode T2 = y.left;
        return new PAVLNode(y.key, new PAVLNode(x.key, x.left, T2), y.right);
    }

    private PAVLNode rotateLeftRight(PAVLNode node) {
        PAVLNode newLeft = rotateLeft(node.left);
        return rotateRight(new PAVLNode(node.key, newLeft, node.right));
    }

    private PAVLNode rotateRightLeft(PAVLNode node) {
        PAVLNode newRight = rotateRight(node.right);
        return rotateLeft(new PAVLNode(node.key, node.left, newRight));
    }

    // ====== 取得版本 ======
    public PAVLNode getVersion(int version) {
        if (version < 0 || version >= versions.size()) return null;
        return versions.get(version);
    }

    // ====== 中序遍歷 ======
    public void inorder(PAVLNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.key + " ");
        inorder(node.right);
    }

    // ====== 教科書風格樹形列印 ======
    public void printTree(PAVLNode root) {
        int maxLevel = maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private int maxLevel(PAVLNode node) {
        if (node == null) return 0;
        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private void printNodeInternal(List<PAVLNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printWhitespaces(firstSpaces);

        List<PAVLNode> newNodes = new ArrayList<>();
        for (PAVLNode node : nodes) {
            if (node != null) {
                System.out.print(node.key);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                System.out.print(" ");
                newNodes.add(null);
                newNodes.add(null);
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(edgeLines * 2 + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null) System.out.print("/");
                else printWhitespaces(1);

                printWhitespaces(i * 2 - 1);

                if (nodes.get(j).right != null) System.out.print("\\");
                else printWhitespaces(1);

                printWhitespaces(edgeLines * 2 - i);
            }
            System.out.println();
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private boolean isAllElementsNull(List<PAVLNode> list) {
        for (PAVLNode node : list) {
            if (node != null) return false;
        }
        return true;
    }

    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) System.out.print(" ");
    }

    // ====== 測試範例 ======
    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(25);
        tree.insert(5);

        for (int v = 0; v < tree.versions.size(); v++) {
            System.out.println("\n版本 " + v + ":");
            tree.printTree(tree.getVersion(v));
        }
    }
}
