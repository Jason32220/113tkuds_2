import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序是處理重複組合的關鍵
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentCombination, int[] candidates, int remaining, int start) {
        if (remaining < 0) {
            return;
        } else if (remaining == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 處理重複的關鍵：如果當前元素與前一個元素相同，且前一個元素未被使用，則跳過
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            currentCombination.add(candidates[i]);
            backtrack(result, currentCombination, candidates, remaining - candidates[i], i + 1); // 每個數字只能使用一次，所以是 i + 1
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
