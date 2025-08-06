
import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // 多路樹節點
    static class TreeNode {

        String value;
        List<TreeNode> children;

        TreeNode(String val) {
            value = val;
            children = new ArrayList<>();
        }

        void addChild(TreeNode child) {
            children.add(child);
        }
    }

    // 深度優先走訪 (DFS)
    public static void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        for (TreeNode child : node.children) {
            dfs(child);
        }
    }

    // 廣度優先走訪 (BFS)
    public static void bfs(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.println(current.value);
            for (TreeNode child : current.children) {
                queue.offer(child);
            }
        }
    }

    // 計算樹高度 (遞迴)
    public static int treeHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int maxChildHeight = 0;
        for (TreeNode child : node.children) {
            int h = treeHeight(child);
            if (h > maxChildHeight) {
                maxChildHeight = h;
            }
        }
        return maxChildHeight + 1;
    }

    // 印出每個節點的度數（子節點數）
    public static void printDegree(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println("節點 " + node.value + " 度數: " + node.children.size());
        for (TreeNode child : node.children) {
            printDegree(child);
        }
    }

    // 簡單決策樹（猜數字遊戲）模擬
    // 節點儲存問題或答案
    static class DecisionNode {

        String questionOrAnswer;
        DecisionNode yesBranch;
        DecisionNode noBranch;

        DecisionNode(String text) {
            questionOrAnswer = text;
        }
    }

    public static void playDecisionTree(DecisionNode node, Scanner sc) {
        if (node.yesBranch == null && node.noBranch == null) {
            // 葉節點，答案
            System.out.println("答案是: " + node.questionOrAnswer);
            return;
        }

        System.out.println(node.questionOrAnswer + " (yes/no)");
        String ans = sc.nextLine().trim().toLowerCase();

        if (ans.equals("yes")) {
            playDecisionTree(node.yesBranch, sc);
        } else if (ans.equals("no")) {
            playDecisionTree(node.noBranch, sc);
        } else {
            System.out.println("請輸入 yes 或 no");
            playDecisionTree(node, sc);
        }
    }

    // 測試
    public static void main(String[] args) {
        // 建立一棵多路樹
        TreeNode root = new TreeNode("動物");
        TreeNode cat = new TreeNode("貓");
        TreeNode dog = new TreeNode("狗");
        TreeNode bird = new TreeNode("鳥");
        root.addChild(cat);
        root.addChild(dog);
        root.addChild(bird);
        cat.addChild(new TreeNode("波斯貓"));
        cat.addChild(new TreeNode("英國短毛貓"));
        dog.addChild(new TreeNode("拉布拉多"));
        dog.addChild(new TreeNode("哈士奇"));

        System.out.println("多路樹 DFS 走訪：");
        dfs(root);

        System.out.println("\n多路樹 BFS 走訪：");
        bfs(root);

        System.out.println("\n樹的高度：" + treeHeight(root));

        System.out.println("\n各節點度數：");
        printDegree(root);

        // 決策樹範例
        System.out.println("\n--- 猜數字決策樹遊戲 ---");
        DecisionNode rootDecision = new DecisionNode("數字大於 50 嗎?");
        rootDecision.yesBranch = new DecisionNode("數字大於 75 嗎?");
        rootDecision.noBranch = new DecisionNode("數字大於 25 嗎?");

        rootDecision.yesBranch.yesBranch = new DecisionNode("數字是 80");
        rootDecision.yesBranch.noBranch = new DecisionNode("數字是 60");

        rootDecision.noBranch.yesBranch = new DecisionNode("數字是 40");
        rootDecision.noBranch.noBranch = new DecisionNode("數字是 10");

        Scanner sc = new Scanner(System.in);
        playDecisionTree(rootDecision, sc);
        sc.close();
    }
}
