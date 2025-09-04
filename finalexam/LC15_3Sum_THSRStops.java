import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LC15_3Sum_THSRStops {

    /**
     * 找出陣列中所有不重複的三元組，其總和為 0。
     *
     * @param nums 包含整數的陣列
     * @return 所有符合條件的三元組列表
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        // 結果列表
        List<List<Integer>> result = new ArrayList<>();
        // 排序是雙指針方法的關鍵第一步
        Arrays.sort(nums);

        // 遍歷陣列，以每個元素作為三元組的第一個數字
        for (int i = 0; i < nums.length - 2; i++) {
            // 優化: 如果第一個數字大於 0，後面的數字也都會大於 0，總和不可能為 0
            if (nums[i] > 0) {
                break;
            }
            // 避免重複的三元組，如果當前元素與前一個相同則跳過
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 設置雙指針，一個在當前元素的下一個位置，一個在陣列末端
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // 找到一個符合條件的三元組，將其加入結果
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
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
                } else if (sum < 0) {
                    // 總和太小，需要增加總和，向右移動 left 指針
                    left++;
                } else { // sum > 0
                    // 總和太大，需要減少總和，向左移動 right 指針
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        
        List<List<Integer>> result = threeSum(nums);
        for (List<Integer> triplet : result) {
            System.out.println(triplet.get(0) + " " + triplet.get(1) + " " + triplet.get(2));
        }
        
        scanner.close();
    }
}
