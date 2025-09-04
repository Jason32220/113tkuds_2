import java.util.Scanner;

public class LC26_RemoveDuplicates_Scores {

    /**
     * 在已排序陣列中原地移除重複元素，使每個元素只出現一次。
     *
     * @param nums 輸入的已排序整數陣列
     * @return 新陣列的長度
     */
    public static int removeDuplicates(int[] nums) {
        // 處理邊界情況：如果陣列為空，則沒有元素
        if (nums.length == 0) {
            return 0;
        }

        // 設置一個寫指針，指向下一個不重複元素應該被寫入的位置
        // 它也是最終新陣列的長度
        int writePointer = 1;

        // 從第二個元素開始遍歷整個陣列
        for (int i = 1; i < nums.length; i++) {
            // 如果當前元素與前一個不重複元素不同，則將其寫入
            if (nums[i] != nums[i - 1]) {
                nums[writePointer] = nums[i];
                writePointer++;
            }
        }
        return writePointer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        // 處理換行符
        scanner.nextLine();

        // 處理空輸入
        if (n == 0) {
            System.out.println("0");
            System.out.println("");
            scanner.close();
            return;
        }
        
        String line = scanner.nextLine();
        String[] values = line.split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }

        int newLength = removeDuplicates(arr);

        System.out.println(newLength);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newLength; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb.toString().trim());

        scanner.close();
    }
}
