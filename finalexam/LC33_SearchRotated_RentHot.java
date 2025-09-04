import java.util.Scanner;

public class LC33_SearchRotated_RentHot {

    /**
     * 在旋轉的升序陣列中尋找目標值。
     *
     * @param nums 旋轉的升序陣列
     * @param target 欲尋找的目標值
     * @return 目標值的索引，若不存在則回傳 -1
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 如果找到目標，直接回傳索引
            if (nums[mid] == target) {
                return mid;
            }

            // 判斷左半邊是否有序
            if (nums[left] <= nums[mid]) {
                // 如果左半邊有序，且 target 在此範圍內
                if (target >= nums[left] && target < nums[mid]) {
                    // 縮小搜尋範圍至左半邊
                    right = mid - 1;
                } else {
                    // 否則，在右半邊繼續搜尋
                    left = mid + 1;
                }
            } else { // 右半邊有序
                // 如果右半邊有序，且 target 在此範圍內
                if (target > nums[mid] && target <= nums[right]) {
                    // 縮小搜尋範圍至右半邊
                    left = mid + 1;
                } else {
                    // 否則，在左半邊繼續搜尋
                    right = mid - 1;
                }
            }
        }

        // 遍歷結束，未找到目標
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int index = search(nums, target);
        System.out.println(index);

        scanner.close();
    }
}
