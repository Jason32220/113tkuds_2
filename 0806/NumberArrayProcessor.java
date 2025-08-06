public class NumberArrayProcessor {

    // 功能一：移除陣列中的重複元素
    public static int[] removeDuplicates(int[] arr) {
        // 用來記錄不重複的數字
        int[] temp = new int[arr.length];
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            boolean isDuplicate = false;

            // 檢查 arr[i] 是否在 temp 裡面已經出現過
            for (int j = 0; j < count; j++) {
                if (arr[i] == temp[j]) {
                    isDuplicate = true;
                    break;
                }
            }

            // 如果沒有重複就加入 temp
            if (!isDuplicate) {
                temp[count] = arr[i];
                count++;
            }
        }

        // 建立新的陣列來回傳
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    // 功能二：合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        // 把兩個陣列合併成一個有序的陣列
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                result[k] = a[i];
                i++;
            } else {
                result[k] = b[j];
                j++;
            }
            k++;
        }

        // 將剩下的元素加入
        while (i < a.length) {
            result[k] = a[i];
            i++;
            k++;
        }

        while (j < b.length) {
            result[k] = b[j];
            j++;
            k++;
        }

        return result;
    }

    // 功能三：找出出現次數最多的元素
    public static int findMostFrequent(int[] arr) {
        int maxCount = 0;
        int mostFrequent = arr[0];

        for (int i = 0; i < arr.length; i++) {
            int count = 0;

            // 統計 arr[i] 出現幾次
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                mostFrequent = arr[i];
            }
        }

        return mostFrequent;
    }

    // 功能四：將陣列分成兩個總和接近的子陣列
    public static void splitArray(int[] arr) {
        int total = 0;

        // 計算總和
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }

        int half = total / 2;
        int sum1 = 0;
        System.out.print("子陣列 1：");

        // 簡單貪婪法：先裝滿第一組接近一半
        for (int i = 0; i < arr.length; i++) {
            if (sum1 + arr[i] <= half) {
                System.out.print(arr[i] + " ");
                sum1 += arr[i];
                arr[i] = -1; // 標記為已用過
            }
        }

        System.out.println("\n子陣列 2：");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                System.out.print(arr[i] + " ");
            }
        }

        System.out.println();
    }

    // 顯示陣列內容
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 主程式：測試功能
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 2, 4, 5, 3};
        int[] array2 = {2, 4, 6, 8};
        int[] array3 = {1, 2, 2, 2, 3, 4, 1};

        System.out.println("== 移除重複元素 ==");
        int[] noDup = removeDuplicates(array1);
        printArray(noDup);

        System.out.println("\n== 合併已排序陣列 ==");
        int[] merged = mergeSortedArrays(array1, array2); // 假設 array1, array2 是已排序
        printArray(merged);

        System.out.println("\n== 找出出現最多的元素 ==");
        int most = findMostFrequent(array3);
        System.out.println("出現最多的數字是：" + most);

        System.out.println("\n== 分割為兩組總和接近的子陣列 ==");
        int[] array4 = {5, 3, 2, 7, 4};
        splitArray(array4);
    }
}
