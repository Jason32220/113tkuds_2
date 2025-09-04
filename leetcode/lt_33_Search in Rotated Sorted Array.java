class Solution {
    /**
     * 在旋轉排序陣列中搜尋目標值。
     *
     * @param nums 旋轉過的排序陣列
     * @param target 要尋找的目標值
     * @return 目標值的索引，如果不存在則返回 -1
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // 判斷左半邊是否有序
            if (nums[left] <= nums[mid]) {
                // 如果左半邊有序，且目標值在左半邊範圍內
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    // 目標值在右半邊，縮小左邊界
                    left = mid + 1;
                }
            } else {
                // 右半邊有序
                // 如果右半邊有序，且目標值在右半邊範圍內
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    // 目標值在左半邊，縮小右邊界
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
