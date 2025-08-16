import java.util.*;

class AVLNode {
    int key;
    AVLNode left, right;

    AVLNode(int key) {
        this.key = key;
    }
}

public class AVLRotationExercise {
    AVLNode root;

    // ====== 四種旋轉 ======
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        return y;
    }

    // 插入 (純 BST)
    AVLNode insertRaw(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);
        if (key < node.key) node.left = insertRaw(node.left, key);
        else if (key > node.key) node.right = insertRaw(node.right, key);
        return node;
    }

    void insertRaw(int key) {
        root = insertRaw(root, key);
    }

    // ====== 教科書風格樹形列印 ======
    void printTree() {
        int maxLevel = maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    int maxLevel(AVLNode node) {
        if (node == null) return 0;
        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    void printNodeInternal(List<AVLNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printWhitespaces(firstSpaces);

        List<AVLNode> newNodes = new ArrayList<>();
        for (AVLNode node : nodes) {
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

    boolean isAllElementsNull(List<AVLNode> list) {
        for (AVLNode node : list) {
            if (node != null) return false;
        }
        return true;
    }

    void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) System.out.print(" ");
    }

    // ====== 測試旋轉 ======
    public static void main(String[] args) {
        // LL Case
        AVLRotationExercise llTree = new AVLRotationExercise();
        llTree.insertRaw(30);
        llTree.insertRaw(20);
        llTree.insertRaw(10);

        System.out.println("插入 30, 20, 10");
        System.out.println("旋轉前 (LL case):");
        llTree.printTree();

        llTree.root = llTree.rightRotate(llTree.root);
        System.out.println("\n右旋後:");
        llTree.printTree();

        // RR Case
        AVLRotationExercise rrTree = new AVLRotationExercise();
        rrTree.insertRaw(10);
        rrTree.insertRaw(20);
        rrTree.insertRaw(30);

        System.out.println("\n插入 10, 20, 30");
        System.out.println("旋轉前 (RR case):");
        rrTree.printTree();

        rrTree.root = rrTree.leftRotate(rrTree.root);
        System.out.println("\n左旋後:");
        rrTree.printTree();

        // LR Case
        AVLRotationExercise lrTree = new AVLRotationExercise();
        lrTree.insertRaw(30);
        lrTree.insertRaw(10);
        lrTree.insertRaw(20);

        System.out.println("\n插入 30, 10, 20");
        System.out.println("旋轉前 (LR case):");
        lrTree.printTree();

        lrTree.root.left = lrTree.leftRotate(lrTree.root.left);
        lrTree.root = lrTree.rightRotate(lrTree.root);
        System.out.println("\n左右旋後:");
        lrTree.printTree();

        // RL Case
        AVLRotationExercise rlTree = new AVLRotationExercise();
        rlTree.insertRaw(10);
        rlTree.insertRaw(30);
        rlTree.insertRaw(20);

        System.out.println("\n插入 10, 30, 20");
        System.out.println("旋轉前 (RL case):");
        rlTree.printTree();

        rlTree.root.right = rlTree.rightRotate(rlTree.root.right);
        rlTree.root = rlTree.leftRotate(rlTree.root);
        System.out.println("\n右左旋後:");
        rlTree.printTree();
    }
}
