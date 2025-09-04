class Solution {
    /**
     * 尋找並原地修改為下一個排列。
     *
     * @param nums 待處理的整數陣列
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 1. 從右向左尋找第一個非遞增的元素
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 2. 如果找到這樣的元素，尋找右邊比它大的最小元素並交換
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // 3. 反轉從 i+1 到結尾的子陣列，使其變為升序
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
