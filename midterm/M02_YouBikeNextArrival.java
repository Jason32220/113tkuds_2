import java.util.*;

public class M02_YouBikeNextArrival {

    // 將 HH:mm 轉換為自 00:00 起的總分鐘數
    private static int toMinutes(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    // 將分鐘數轉回 HH:mm（補零）
    private static String toHHmm(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    /*
     * 使用二分搜尋找「第一個大於 query」的時間
     *
     * Time Complexity: O(log n)
     * 說明：二分搜尋每次縮小一半區間，最壞情況 log2(n) 次比較。
     */
    private static int findNextTime(int[] times, int query) {
        int left = 0, right = times.length - 1;
        int ans = -1; // 預設找不到

        while (left <= right) {
            int mid = (left + right) / 2;
            if (times[mid] > query) {
                ans = mid;
                right = mid - 1; // 繼續往左找更早的
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine(); // 吃掉換行

        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = toMinutes(sc.nextLine().trim());
        }

        int query = toMinutes(sc.nextLine().trim());

        // 查詢最近且晚於 query 的時間
        int idx = findNextTime(times, query);

        if (idx == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHmm(times[idx]));
        }
    }
}
