import java.util.Scanner;

public class LC27_RemoveElement_Recycle {

    /**
     * 在陣列中原地移除所有等於 val 的元素。
     *
     * @param nums 輸入的整數陣列
     * @param val 要移除的值
     * @return 新陣列的長度
     */
    public static int removeElement(int[] nums, int val) {
        // 設置一個寫指針，指向下一個不等於 val 的元素應該被寫入的位置
        int writePointer = 0;

        // 從頭到尾遍歷整個陣列
        for (int i = 0; i < nums.length; i++) {
            // 如果當前元素不等於 val，則將其寫入
            if (nums[i] != val) {
                nums[writePointer] = nums[i];
                writePointer++;
            }
        }
        return writePointer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int val = scanner.nextInt();
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int newLength = removeElement(arr, val);

        System.out.println(newLength);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newLength; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb.toString().trim());

        scanner.close();
    }
}
