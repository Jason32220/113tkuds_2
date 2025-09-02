public class Solution {
    public int maxArea(int[] height) {
        // 左指針從陣列開頭開始
        int left = 0;
        // 右指針從陣列末尾開始
        int right = height.length - 1;
        // 用於記錄找到的最大容量
        int maxArea = 0;

        // 當左指針小於右指針時，迴圈繼續
        while (left < right) {
            // 計算當前兩條線之間的寬度
            int width = right - left;
            
            // 計算當前兩條線中的較短高度
            int currentHeight = Math.min(height[left], height[right]);
            
            // 計算當前容量，並與最大容量比較，取較大的值
            maxArea = Math.max(maxArea, width * currentHeight);

            // 移動指針：總是移動較短的那條線的指針
            if (height[left] < height[right]) {
                left++; // 左邊較短，向右移動左指針
            } else {
                right--; // 右邊較短或兩邊相等，向左移動右指針
            }
        }
        
        return maxArea;
    }
}