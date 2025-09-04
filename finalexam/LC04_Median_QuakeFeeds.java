import java.text.DecimalFormat;
import java.util.Scanner;

public class LC04_Median_QuakeFeeds {

    /**
     * 計算兩個已排序陣列的統一中位數。
     * * @param nums1 第一個已排序陣列
     * @param nums2 第二個已排序陣列
     * @return 兩個陣列聯合集的中位數
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 確保 nums1 永遠是較短的那個陣列，以優化二分搜尋
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int n1 = nums1.length;
        int n2 = nums2.length;
        int low = 0, high = n1;
        
        // 左半部分需要的總元素數
        int totalLeft = (n1 + n2 + 1) / 2;

        while (low <= high) {
            // 在 nums1 中進行二分，找到一個可能的分割點 i
            int i = low + (high - low) / 2;
            // 根據 i 計算 nums2 的分割點 j
            int j = totalLeft - i;

            // 定義四個邊界值，處理索引越界的邊界情況
            int maxLeft1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRight1 = (i == n1) ? Integer.MAX_VALUE : nums1[i];
            
            int maxLeft2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRight2 = (j == n2) ? Integer.MAX_VALUE : nums2[j];

            // 檢查分割是否正確：左半邊最大值 <= 右半邊最小值
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // 找到正確的分割點，開始計算中位數
                if ((n1 + n2) % 2 == 1) { // 總長度為奇數
                    return Math.max(maxLeft1, maxLeft2);
                } else { // 總長度為偶數
                    return (double) (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }
            } else if (maxLeft1 > minRight2) {
                // nums1 的左半邊太大，需要將 nums1 的分割點向左移
                high = i - 1;
            } else { // maxLeft2 > minRight1
                // nums2 的左半邊太大，需要將 nums1 的分割點向右移
                low = i + 1;
            }
        }
        
        return 0.0; // 程式不應執行到此處，如果執行表示輸入有問題
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] nums1 = new int[n];
        for (int i = 0; i < n; i++) {
            nums1[i] = scanner.nextInt();
        }

        int[] nums2 = new int[m];
        for (int i = 0; i < m; i++) {
            nums2[i] = scanner.nextInt();
        }

        double median = findMedianSortedArrays(nums1, nums2);
        
        // 格式化輸出，保留一位小數
        DecimalFormat df = new DecimalFormat("0.0");
        System.out.println(df.format(median));

        scanner.close();
    }
}
