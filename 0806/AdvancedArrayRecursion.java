
import java.util.*;

public class AdvancedArrayRecursion {

    // 1. 遞迴實作快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = arr[(left + right) / 2];
        int index = partition(arr, left, right, pivot);

        quickSort(arr, left, index - 1);
        quickSort(arr, index, right);
    }

    // 輔助方法：將小於 pivot 的值放左邊，大於的放右邊
    public static int partition(int[] arr, int left, int right, int pivot) {
        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    // 2. 遞迴合併兩個已排序陣列
    public static int[] mergeRecursive(int[] a, int[] b, int i, int j, List<Integer> result) {
        if (i >= a.length && j >= b.length) {
            return result.stream().mapToInt(Integer::intValue).toArray();
        }

        if (i >= a.length) {
            result.add(b[j]);
            return mergeRecursive(a, b, i, j + 1, result);
        }

        if (j >= b.length) {
            result.add(a[i]);
            return mergeRecursive(a, b, i + 1, j, result);
        }

        if (a[i] < b[j]) {
            result.add(a[i]);
            return mergeRecursive(a, b, i + 1, j, result);
        } else {
            result.add(b[j]);
            return mergeRecursive(a, b, i, j + 1, result);
        }
    }

    // 3. 遞迴尋找第 k 小元素（簡化用 QuickSelect）
    public static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        int pivot = arr[(left + right) / 2];
        int index = partition(arr, left, right, pivot);

        int count = index - left;  // 幾個元素小於 pivot

        if (k < count) {
            return quickSelect(arr, left, index - 1, k);
        } else if (k > count) {
            return quickSelect(arr, index, right, k - count);
        } else {
            return arr[index - 1];
        }
    }

    // 4. 遞迴檢查是否有子序列總和 = target
    public static boolean subsetSum(int[] arr, int index, int target) {
        if (target == 0) {
            return true;
        }
        if (index >= arr.length || target < 0) {
            return false;
        }

        // 選 or 不選當前數字
        return subsetSum(arr, index + 1, target - arr[index])
                || subsetSum(arr, index + 1, target);
    }

    // 輔助：列印陣列
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 主程式：功能測試
    public static void main(String[] args) {
        System.out.println("== 遞迴陣列操作進階 ==");

        // 測試 Quick Sort
        int[] arr1 = {5, 2, 9, 1, 6, 3};
        System.out.print("原始陣列：");
        printArray(arr1);
        quickSort(arr1, 0, arr1.length - 1);
        System.out.print("快速排序結果：");
        printArray(arr1);

        // 測試合併兩個排序陣列
        int[] a = {1, 4, 7};
        int[] b = {2, 3, 6};
        int[] merged = mergeRecursive(a, b, 0, 0, new ArrayList<>());
        System.out.print("遞迴合併結果：");
        printArray(merged);

        // 測試 QuickSelect 找第 k 小
        int[] arr2 = {7, 10, 4, 3, 20, 15};
        int k = 3;
        int kthSmallest = quickSelect(arr2.clone(), 0, arr2.length - 1, k - 1);
        System.out.println("第 " + k + " 小的元素是：" + kthSmallest);

        // 測試 Subset Sum
        int[] arr3 = {3, 34, 4, 12, 5, 2};
        int target = 9;
        boolean exists = subsetSum(arr3, 0, target);
        System.out.println("是否存在子序列總和 = " + target + "？" + (exists ? "是" : "否"));
    }
}
