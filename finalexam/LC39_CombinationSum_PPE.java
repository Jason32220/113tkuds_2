import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LC39_CombinationSum_PPE {

    /**
     * 解決組合總和問題 (Combination Sum I)。
     *
     * @param candidates 候選數字的陣列，數字可以重複使用。
     * @param target 目標總和。
     * @return 包含所有不重複組合的列表。
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 為提高效率，先對候選陣列進行排序。這有助於剪枝（優化）。
        Arrays.sort(candidates);
        // 呼叫回溯演算法輔助函式，從索引 0 開始。
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    /**
     * 組合總和的回溯演算法輔助函式。
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
            // 將當前組合的副本加入結果列表。
            result.add(new ArrayList<>(current));
            return;
        }

        // 基本情況 2: 如果 remaining 小於 0，表示當前路徑無效，需要回溯。
        if (remaining < 0) {
            return;
        }

        // 遞歸步驟：遍歷所有可能的選擇。
        for (int i = start; i < candidates.length; i++) {
            // 優化（剪枝）：如果當前候選數字已經大於剩餘值，由於陣列已排序，後續的數字只會更大，
            // 因此可以提前結束當前層次的循環。
            if (candidates[i] > remaining) {
                break;
            }
            // 做出選擇：將當前候選數字加入組合。
            current.add(candidates[i]);
            // 遞歸：呼叫下一層。因為每個數字可以重複使用，所以下一層遞歸仍從當前索引 i 開始。
            backtrack(result, current, candidates, remaining - candidates[i], i);
            // 回溯：移除最後加入的候選數字，以探索其他可能性。
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = scanner.nextInt();
        }

        List<List<Integer>> result = combinationSum(candidates, target);
        
        for (List<Integer> combo : result) {
            for (int i = 0; i < combo.size(); i++) {
                System.out.print(combo.get(i) + (i == combo.size() - 1 ? "" : " "));
            }
            System.out.println();
        }
        
        scanner.close();
    }
}
