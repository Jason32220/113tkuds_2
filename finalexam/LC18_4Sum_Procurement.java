import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LC18_4Sum_Procurement {

    /**
     * 找出陣列中所有不重複的四元組，其總和等於給定的目標值。
     *
     * @param nums 包含整數的陣列
     * @param target 目標總和
     * @return 所有符合條件的四元組列表
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        // 結果列表
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        // 如果陣列長度小於 4，無法組成四元組
        if (n < 4) {
            return result;
        }

        // 排序是雙指針方法的關鍵第一步
        Arrays.sort(nums);

        // 外層迴圈，以每個元素作為四元組的第一個數字
        for (int i = 0; i < n - 3; i++) {
            // 避免重複的四元組，如果當前元素與前一個相同則跳過
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 第二層迴圈，以每個元素作為四元組的第二個數字
            for (int j = i + 1; j < n - 2; j++) {
                // 避免重複
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 設置雙指針，一個在當前元素的下一個位置，一個在陣列末端
                int left = j + 1;
                int right = n - 1;
                long sum;

                while (left < right) {
                    // 使用 long 避免整數溢位
                    sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        // 找到一個符合條件的四元組，將其加入結果
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 移動指針以尋找下一個可能的解
                        left++;
                        right--;

                        // 避免重複，同步略過重複的元素
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        // 總和太小，向右移動 left 指針
                        left++;
                    } else { // sum > target
                        // 總和太大，向左移動 right 指針
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取 n 和 target
        int n = scanner.nextInt();
        long target = scanner.nextLong();

        // 讀取 n 個整數
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // 調用方法並輸出結果
        List<List<Integer>> result = fourSum(nums, (int) target);
        for (List<Integer> quadruplet : result) {
            System.out.println(quadruplet.get(0) + " " + quadruplet.get(1) + " " + quadruplet.get(2) + " " + quadruplet.get(3));
        }

        scanner.close();
    }
}
