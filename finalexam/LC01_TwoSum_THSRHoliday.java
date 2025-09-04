import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LC01_TwoSum_THSRHoliday {

    /**
     * 主函式，負責處理輸入、呼叫解題邏輯並輸出結果。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取 n 和 target
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        
        // 讀取 n 個座位數
        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = scanner.nextInt();
        }
        
        // 建立 HashMap 來儲存 "需要的座位數" 及其索引
        // Map<需要的數, 索引>
        Map<Integer, Integer> complementMap = new HashMap<>();
        
        // 預設為無解
        int index1 = -1;
        int index2 = -1;
        
        // 遍歷座位數陣列
        for (int i = 0; i < n; i++) {
            int currentSeat = seats[i];
            
            // 計算當前座位數所需要的互補數
            int complement = target - currentSeat;
            
            // 檢查 HashMap 中是否已存在這個互補數
            if (complementMap.containsKey(complement)) {
                // 如果找到，則取出其索引
                index1 = complementMap.get(complement);
                // 當前索引為另一個解
                index2 = i;
                // 找到解，跳出迴圈
                break;
            }
            
            // 如果沒找到，將當前座位數和其索引放入 HashMap
            // 讓後面的元素可以找到它
            complementMap.put(currentSeat, i);
        }
        
        // 輸出結果
        System.out.println(index1 + " " + index2);
        
        scanner.close();
    }
}
