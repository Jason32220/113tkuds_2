class Solution {
    /**
     * 在排序陣列中尋找目標的起始與結束位置。
     *
     * @param nums 排序陣列
     * @param target 要尋找的目標值
     * @return 目標值的起始與結束索引，如果不存在則返回 [-1, -1]
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // 尋找左側邊界（起始索引）
        result[0] = findBoundary(nums, target, true);
        
        // 如果左側邊界找到了，才繼續尋找右側邊界
        if (result[0] != -1) {
            result[1] = findBoundary(nums, target, false);
        }
        
        return result;
    }
    
    /**
     * 使用二分搜尋來尋找目標的左側或右側邊界。
     *
     * @param nums 排序陣列
     * @param target 目標值
     * @param findLeft 指示是否尋找左側邊界（true）或右側邊界（false）
     * @return 邊界的索引，如果找不到則返回 -1
     */
    private int findBoundary(int[] nums, int target, boolean findLeft) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                index = mid; // 找到一個可能的答案
                if (findLeft) {
                    // 繼續向左搜尋，尋找更小的索引
                    right = mid - 1;
                } else {
                    // 繼續向右搜尋，尋找更大的索引
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return index;
    }
}
