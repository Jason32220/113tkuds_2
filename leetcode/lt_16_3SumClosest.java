// 題目：最接近的三數之和

// 核心解題思路：
// 使用「排序 + 雙指針」的方法。
// 首先對陣列進行排序，然後固定一個數字，再使用兩個指針從兩端向中間移動，
// 不斷調整總和，直到找到最接近目標值的總和。

import java.util.Arrays;

class Solution {
    
    /**
     * 尋找陣列中三個數字的和最接近目標值的總和。
     * @param nums 輸入的整數陣列
     * @param target 目標值
     * @return 最接近目標值的總和
     */
    public int threeSumClosest(int[] nums, int target) {
        // 步驟 1: 對陣列進行排序，這對於雙指針法至關重要
        Arrays.sort(nums);
        
        // 初始化最接近的總和，使用陣列中前三個元素的總和作為初始值
        int closestSum = nums[0] + nums[1] + nums[2];

        // 步驟 2: 遍歷陣列，以 nums[i] 為第一個數字
        for (int i = 0; i < nums.length - 2; i++) {
            // 設置雙指針，left 從 i+1 開始，right 從末尾開始
            int left = i + 1;
            int right = nums.length - 1;

            // 步驟 3: 雙指針法尋找配對
            while (left < right) {
                // 計算當前三數的總和
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // 比較當前總和與目標值的距離，如果更接近，則更新 closestSum
                // Math.abs(currentSum - target) < Math.abs(closestSum - target)
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                // 根據總和與目標值的關係，移動指針
                if (currentSum < target) {
                    // 總和太小，增加 left 指針以增加總和
                    left++;
                } else if (currentSum > target) {
                    // 總和太大，減少 right 指針以減少總和
                    right--;
                } else {
                    // 如果總和剛好等於目標值，則找到了最佳解，直接返回
                    return currentSum;
                }
            }
        }

        // 遍歷結束後，返回最接近的總和
        return closestSum;
    }
}
