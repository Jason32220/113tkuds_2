/**
 * 解決從陣列中移除特定值的所有出現次數。
 * 核心思路：使用雙指針原地移除指定元素。
 */
class Solution {
    /**
     * 從陣列中移除所有與 val 相等的元素。
     *
     * @param nums 一個整數陣列
     * @param val 要移除的特定值
     * @return 移除後，陣列中不等於 val 的元素數量
     */
    public int removeElement(int[] nums, int val) {
        // 慢指針 i 用於追蹤下一個非 val 元素應該放置的位置
        int i = 0;

        // 快指針 j 用於遍歷整個陣列
        for (int j = 0; j < nums.length; j++) {
            // 如果當前元素 (nums[j]) 不等於要移除的值 (val)，
            // 則將其移動到慢指針 i 的位置。
            if (nums[j] != val) {
                nums[i] = nums[j];
                // 移動慢指針，為下一個非 val 元素做準備
                i++;
            }
        }
        
        // i 的最終值就是不等於 val 的元素總數
        return i;
    }
}
