import java.util.*;

public class MeetingRoomScheduler {

    // 第一部分：計算最少會議室數量
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
                minHeap.poll();
            }
            minHeap.offer(interval[1]);
        }

        return minHeap.size();
    }

    // 找最後一個不衝突會議(結束時間 <= startTime)
    private static int binarySearchLastNonConflict(int[][] intervals, int index) {
        int low = 0, high = index - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (intervals[mid][1] <= intervals[index][0]) {
                if (mid == index - 1 || intervals[mid + 1][1] > intervals[index][0]) {
                    return mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // 第二部分：最大總會議時間(限定N個會議室，選擇非衝突會議最大時長)
    public static int maxTotalMeetingTime(int[][] intervals, int N) {
        if (intervals.length == 0 || N == 0) return 0;

        // 先依結束時間排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int n = intervals.length;
        int[] dp = new int[n];
        int[] count = new int[n]; // 紀錄已用會議室數量

        // dp[i] = 第 i 個會議為結束會議，最大總時長
        // count[i] = 使用會議室數量

        for (int i = 0; i < n; i++) {
            int length = intervals[i][1] - intervals[i][0];
            int last = binarySearchLastNonConflict(intervals, i);

            int includeLength = length;
            int includeCount = 1;
            if (last != -1) {
                includeLength += dp[last];
                includeCount += count[last];
            }

            // 選擇包含 or 不包含 i
            if (i > 0) {
                if (includeCount <= N && includeLength > dp[i - 1]) {
                    dp[i] = includeLength;
                    count[i] = includeCount;
                } else {
                    dp[i] = dp[i - 1];
                    count[i] = count[i - 1];
                }
            } else {
                if (includeCount <= N) {
                    dp[i] = includeLength;
                    count[i] = includeCount;
                } else {
                    dp[i] = 0;
                    count[i] = 0;
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[][] meetings1 = {{0,30},{5,10},{15,20}};
        System.out.println("最少會議室: " + minMeetingRooms(meetings1)); // 2

        int[][] meetings2 = {{9,10},{4,9},{4,17}};
        System.out.println("最少會議室: " + minMeetingRooms(meetings2)); // 2

        int[][] meetings3 = {{1,5},{8,9},{8,9}};
        System.out.println("最少會議室: " + minMeetingRooms(meetings3)); // 2

        int[][] meetings4 = {{1,4},{2,3},{4,6}};
        int N = 1;
        System.out.println("最多會議室為1時最大總會議時間: " + maxTotalMeetingTime(meetings4, N)); // 5
    }
}
