import java.util.*;

public class StockMaximizer {

    // 找出所有上升區間的利潤，放進最大堆，選取前 K 大
    public static int maxProfit(int[] prices, int K) {
        if (prices == null || prices.length < 2 || K == 0) return 0;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int n = prices.length;

        // 找所有上升區間利潤
        int i = 0;
        while (i < n - 1) {
            // 找局部低點
            while (i < n - 1 && prices[i] >= prices[i + 1]) i++;
            int buy = prices[i];

            // 找局部高點
            while (i < n - 1 && prices[i] <= prices[i + 1]) i++;
            int sell = prices[i];

            int profit = sell - buy;
            if (profit > 0) {
                maxHeap.offer(profit);
            }
        }

        // K 大於等於區間數，全部收益相加
        if (K >= maxHeap.size()) {
            int total = 0;
            while (!maxHeap.isEmpty()) {
                total += maxHeap.poll();
            }
            return total;
        }

        // 取最大 K 筆利潤相加
        int maxProfit = 0;
        for (int j = 0; j < K; j++) {
            maxProfit += maxHeap.poll();
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {2, 4, 1};
        int K1 = 2;
        System.out.println(maxProfit(prices1, K1)); // 2

        int[] prices2 = {3, 2, 6, 5, 0, 3};
        int K2 = 2;
        System.out.println(maxProfit(prices2, K2)); // 7

        int[] prices3 = {1, 2, 3, 4, 5};
        int K3 = 2;
        System.out.println(maxProfit(prices3, K3)); // 4
    }
}
