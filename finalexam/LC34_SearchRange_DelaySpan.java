import java.util.Scanner;

public class LC34_SearchRange_DelaySpan {

    /**
     * 在已排序陣列中尋找目標值首次出現的索引。
     *
     * @param nums 待搜尋的已排序陣列
     * @param target 欲尋找的目標值
     * @return 首次出現的索引，若不存在則回傳 -1
     */
    private static int findFirst(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            if (nums[mid] == target) {
                index = mid;
            }
        }
        return index;
    }

    /**
     * 在已排序陣列中尋找目標值最後一次出現的索引。
     *
     * @param nums 待搜尋的已排序陣列
     * @param target 欲尋找的目標值
     * @return 最後一次出現的索引，若不存在則回傳 -1
     */
    private static int findLast(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            if (nums[mid] == target) {
                index = mid;
            }
        }
        return index;
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int[] result = searchRange(nums, target);
        System.out.println(result[0] + " " + result[1]);
        
        scanner.close();
    }
}
