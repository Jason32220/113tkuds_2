import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 核心解題思路：
// 使用「排序 + 雙指針」的方法。
// 我們可以將四數之和問題，降維成兩個「兩數之和」問題的結合。
// 具體做法是，先固定兩個數字，然後用雙指針法在剩餘的陣列中尋找另外兩個數字。

class Solution {
    
    /**
     * 尋找所有不重複的四元組，使其和等於目標值。
     * @param nums 輸入的整數陣列
     * @param target 目標值
     * @return 包含所有不重複四元組的列表
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        // 如果陣列長度小於 4，不可能找到四元組
        if (n < 4) {
            return result;
        }
        
        // 步驟 1: 對陣列進行排序，這對於雙指針法和去重至關重要
        Arrays.sort(nums);
        
        // 步驟 2: 使用兩層迴圈固定前兩個數字 (i 和 j)
        for (int i = 0; i < n - 3; i++) {
            // 去重：如果當前數字與前一個數字相同，跳過以避免重複四元組
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            for (int j = i + 1; j < n - 2; j++) {
                // 去重：如果當前數字與前一個數字相同，跳過
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 步驟 3: 設置雙指針，left 從 j+1 開始，right 從末尾開始
                int left = j + 1;
                int right = n - 1;
                
                while (left < right) {
                    // 計算當前四數的總和。這裡使用 long 以防止加法時的整數溢位
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if (sum == target) {
                        // 找到了總和等於目標值的四元組
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
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
                    } else if (sum < target) {
                        // 總和太小，需要增加總和，移動 left 指針
                        left++;
                    } else { // sum > target
                        // 總和太大，需要減少總和，移動 right 指針
                        right--;
                    }
                }
            }
        }
        
        return result;
    }
}
