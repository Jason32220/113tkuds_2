import java.util.Scanner;

public class LC11_MaxArea_FuelHoliday {

    /**
     * 找出能容納最大面積的兩個垂線。
     *
     * @param heights 一個整數陣列，代表每個位置的垂線高度
     * @return 容器能容納的最大面積
     */
    public static long maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        long maxArea = 0;

        // 當左右指針相遇時停止
        while (left < right) {
            // 計算當前容器的寬度和高度，高度取決於較短的邊
            int width = right - left;
            int height = Math.min(heights[left], heights[right]);
            
            // 計算當前面積，並與最大面積比較
            long currentArea = (long) width * height;
            if (currentArea > maxArea) {
                maxArea = currentArea;
            }

            // 移動高度較小的指針，因為只有這樣才有可能找到更大的面積
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取 n
        int n = scanner.nextInt();
        
        // 讀取 n 個高度
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextInt();
        }
        
        // 調用方法並輸出結果
        long result = maxArea(heights);
        System.out.println(result);
        
        scanner.close();
    }
}
