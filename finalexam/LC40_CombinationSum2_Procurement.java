import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LC40_CombinationSum2_Procurement {

    /**
     * 解決組合總和問題 (Combination Sum II)。
     *
     * @param candidates 候選數字的陣列，可能包含重複數字。
     * @param target 目標總和。
     * @return 包含所有不重複組合的列表。
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 必須先排序，這是處理重複和剪枝的基礎。
        Arrays.sort(candidates);
        // 呼叫回溯演算法輔助函式，從索引 0 開始。
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    /**
     * 組合總和 II 的回溯演算法輔助函式。
     *
     * @param result 用於儲存所有有效組合的最終列表。
     * @param current 當前正在建構的組合。
     * @param candidates 候選數字的陣列。
     * @param remaining 距離目標總和還差多少。
     * @param start 當前遞歸層次可以開始選擇的索引。
     */
    private static void backtrack(List<List<Integer>> result, List<Integer> current, int[] candidates, int remaining, int start) {
        // 基本情況 1: 如果 remaining 為 0，表示找到了一個有效的組合。
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 基本情況 2: 如果 remaining 小於 0，表示當前路徑無效。
        if (remaining < 0) {
            return;
        }

        // 遞歸步驟：遍歷所有可能的選擇。
        for (int i = start; i < candidates.length; i++) {
            // 優化（剪枝）：如果當前候選數字已經大於剩餘值，由於陣列已排序，後續的數字只會更大，
            // 因此可以提前結束。
            if (candidates[i] > remaining) {
                break;
            }
            // 關鍵的去重邏輯：如果當前數字與前一個數字相同，且它們不是同一層次的第一個選擇，
            // 我們跳過它，以避免產生重複組合。
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            // 做出選擇：將當前候選數字加入組合。
            current.add(candidates[i]);
            // 遞歸：呼叫下一層。每個數字只能使用一次，所以下一層遞歸從 i + 1 開始。
            backtrack(result, current, candidates, remaining - candidates[i], i + 1);
            // 回溯：移除最後加入的候選數字，探索其他可能性。
            current.remove(current.size() - 1);
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("請輸入 n 和 target:");
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        int[] candidates = new int[n];
        System.out.println("請輸入 " + n + " 個數字:");
        for (int i = 0; i < n; i++) {
            candidates[i] = scanner.nextInt();
        }

        List<List<Integer>> result = combinationSum2(candidates, target);
        
        System.out.println("結果：");
        for (List<Integer> combo : result) {
            for (int i = 0; i < combo.size(); i++) {
                System.out.print(combo.get(i) + (i == combo.size() - 1 ? "" : " "));
            }
            System.out.println();
        }
        
        scanner.close();
    }
}
