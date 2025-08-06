
public class SelectionSortImplementation {

    // 選擇排序法
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int compareCount = 0;  // 比較次數
        int swapCount = 0;     // 交換次數

        System.out.println("== 選擇排序開始 ==");

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // 尋找最小值的索引
            for (int j = i + 1; j < n; j++) {
                compareCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 若找到更小的才進行交換
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
            }

            // 顯示每一輪排序結果
            System.out.print("第 " + (i + 1) + " 輪結果：");
            printArray(arr);
        }

        System.out.println("== 選擇排序完成 ==");
        System.out.println("總比較次數：" + compareCount);
        System.out.println("總交換次數：" + swapCount);
    }

    // 氣泡排序法（比較用）
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int compareCount = 0;
        int swapCount = 0;

        System.out.println("\n== 氣泡排序開始 ==");

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                compareCount++;
                if (arr[j] > arr[j + 1]) {
                    // 交換
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                    swapped = true;
                }
            }

            System.out.print("第 " + (i + 1) + " 輪結果：");
            printArray(arr);

            // 若一輪下來都沒交換，表示已排序好
            if (!swapped) {
                break;
            }
        }

        System.out.println("== 氣泡排序完成 ==");
        System.out.println("總比較次數：" + compareCount);
        System.out.println("總交換次數：" + swapCount);
    }

    // 輔助方法：印出陣列
    public static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // 主程式
    public static void main(String[] args) {
        int[] data1 = {64, 25, 12, 22, 11};
        int[] data2 = {64, 25, 12, 22, 11};

        System.out.println("原始陣列：");
        printArray(data1);

        // 執行選擇排序
        selectionSort(data1);

        // 執行氣泡排序（比較用）
        bubbleSort(data2);
    }
}
