class Solution {
    /**
     * 在已排序的陣列中搜尋目標值的索引。
     * 如果目標值不存在，返回它應該被插入的索引。
     *
     * @param nums 排序陣列
     * @param target 目標值
     * @return 目標值的索引或插入位置
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 迴圈結束時，left 指向目標值應該被插入的位置
        return left;
    }
}
