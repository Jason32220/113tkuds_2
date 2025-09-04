import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
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
            currentCombination.add(candidates[i]);
            backtrack(result, currentCombination, candidates, remaining - candidates[i], i); // 允許重複使用同一個數字
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
