public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        // 確保 nums1 永遠是較短的陣列，以優化二元搜尋的範圍
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0;
        int right = m;
        int halfLen = (m + n + 1) / 2;

        while (left <= right) {
            // 在 nums1 中尋找分割點 i
            int i = (left + right) / 2;
            // 根據 i 計算 nums2 中的分割點 j
            int j = halfLen - i;

            // 處理邊界情況，用極值代表不存在的元素
            // L1: nums1[i-1], L2: nums2[j-1]
            // R1: nums1[i], R2: nums2[j]
            int L1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int R1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int L2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int R2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 如果找到完美的分割點 (i, j)
            if (L1 <= R2 && L2 <= R1) {
                // 如果總元素數是奇數，中位數是左半部分的最大值
                if ((m + n) % 2 == 1) {
                    return Math.max(L1, L2);
                } else {
                    // 如果總元素數是偶數，中位數是左半部分最大值和右半部分最小值的平均
                    return (double) (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
                }
            } else if (L1 > R2) {
                // 如果 nums1 的左半部分太大，需要將 i 往左移
                right = i - 1;
            } else { // L2 > R1
                // 如果 nums2 的左半部分太大，需要將 i 往右移
                left = i + 1;
            }
        }
        
        // 理論上不會執行到這行，因為題目保證有解
        return 0.0;
    }
}