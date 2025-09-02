import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 解決三數之和問題的類別。
 * 核心思想：先排序，然後使用雙指針法來尋找不重複的三元組。
 */
public class Solution {
    
    /**
     * 尋找所有不重複的三元組，使其和為 0。
     * @param nums 輸入的整數陣列
     * @return 包含所有不重複三元組的列表
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 用於存放結果的列表
        List<List<Integer>> result = new ArrayList<>();
        
        // 如果陣列長度小於 3，不可能找到三元組，直接返回空列表
        if (nums == null || nums.length < 3) {
            return result;
        }

        // 步驟 1: 對陣列進行排序，這對於雙指針法和去重至關重要
        Arrays.sort(nums);

        // 步驟 2: 遍歷陣列，以 nums[i] 為第一個數字
        for (int i = 0; i < nums.length - 2; i++) {
            // 如果 nums[i] 大於 0，因為陣列已排序，後續的數字也都大於 0，不可能總和為 0，所以可以提前結束
            if (nums[i] > 0) {
                break;
            }
            
            // 去重：如果當前數字與前一個數字相同，跳過以避免重複三元組
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 設置雙指針，left 從 i+1 開始，right 從末尾開始
            int left = i + 1;
            int right = nums.length - 1;

            // 步驟 3: 雙指針法尋找配對
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    // 找到了總和為 0 的三元組
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // 為了避免重複，移動指針直到遇到不同的數字
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // 移動指針到下一個位置
                    left++;
                    right--;
                } else if (sum < 0) {
                    // 總和太小，需要增加總和，移動 left 指針
                    left++;
                } else { // sum > 0
                    // 總和太大，需要減少總和，移動 right 指針
                    right--;
                }
            }
        }

        return result;
    }
}
