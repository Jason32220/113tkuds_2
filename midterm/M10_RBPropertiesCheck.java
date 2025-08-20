import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {
        int val;
        char color; // 'R' or 'B'
        Node(int v, char c) { val = v; color = c; }
    }

    private static Node[] buildTree(int n, int[] vals, char[] colors) {
        Node[] tree = new Node[n];
        for (int i = 0; i < n; i++) {
            if (vals[i] == -1) tree[i] = null; // NIL 节点視為空
            else tree[i] = new Node(vals[i], colors[i]);
        }
        return tree;
    }

    /*
     * 檢查紅紅相鄰
     *
     * Time Complexity: O(n)
     * 說明：
     * - 每個非空節點檢查左右子 → 每個節點最多訪問一次。
     */
    private static String checkRedRed(Node[] tree) {
        for (int i = 0; i < tree.length; i++) {
            Node cur = tree[i];
            if (cur == null) continue;
            if (cur.color == 'R') {
                int left = 2*i +1, right = 2*i+2;
                if (left < tree.length && tree[left]!=null && tree[left].color=='R')
                    return "RedRedViolation at index " + i;
                if (right < tree.length && tree[right]!=null && tree[right].color=='R')
                    return "RedRedViolation at index " + i;
            }
        }
        return null;
    }

    /*
     * 檢查黑高度一致 (從根開始)
     *
     * Time Complexity: O(n)
     * 說明：
     * - 從每個節點遞迴計算黑高度，NIL 視為黑。
     * - 遞迴每個節點一次 → O(n)
     */
    private static int checkBlackHeight(Node[] tree, int idx) {
        if (idx >= tree.length || tree[idx]==null) return 1; // NIL 黑高度 =1
        int leftBH = checkBlackHeight(tree, 2*idx+1);
        int rightBH = checkBlackHeight(tree, 2*idx+2);
        if (leftBH == -1 || rightBH == -1 || leftBH != rightBH) return -1;
        return leftBH + (tree[idx].color=='B'?1:0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] vals = new int[n];
        char[] colors = new char[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
            String c = sc.next();
            colors[i] = c.charAt(0);
        }

        if (n==0) { // 空樹視為合法
            System.out.println("RB Valid");
            return;
        }

        Node[] tree = buildTree(n, vals, colors);

        // 檢查根是否黑
        if (tree[0]==null || tree[0].color!='B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 檢查紅紅
        String rr = checkRedRed(tree);
        if (rr != null) {
            System.out.println(rr);
            return;
        }

        // 檢查黑高度
        int bh = checkBlackHeight(tree, 0);
        if (bh == -1) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }
}

/*
 * Overall Time Complexity: O(n)
 * 說明：
 * - buildTree O(n)
 * - checkRedRed O(n)
 * - checkBlackHeight O(n)
 * - 每個節點最多訪問一次 → O(n)
 */
