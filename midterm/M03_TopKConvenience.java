import java.util.*;

public class M03_TopKConvenience {

    // 商品類別：包含名稱與銷量
    static class Item {
        String name;
        int qty;

        Item(String name, int qty) {
            this.name = name;
            this.qty = qty;
        }
    }

    /*
     * 使用 Min-Heap 維護 Top-K 銷售量
     *
     * Time Complexity: O(n log K)
     * 說明：
     * 1. 每筆資料最多做一次 heap 的插入/移除，耗時 O(log K)。
     * 2. 共 n 筆 → O(n log K)。
     * 3. K 遠小於 n 時，效率遠優於 O(n log n) 排序。
     */
    private static List<Item> topK(List<Item> items, int K) {
        // Min-Heap，依 qty (小的優先)，若 qty 相同依 name 比較
        PriorityQueue<Item> heap = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) {
                    return Integer.compare(a.qty, b.qty); // 小的在前
                } else {
                    return a.name.compareTo(b.name); // 同 qty 依字典序
                }
            }
        });

        for (Item it : items) {
            heap.offer(it); // 放入 heap
            if (heap.size() > K) {
                heap.poll(); // 保持大小為 K
            }
        }

        // 將結果取出並依需求排序（大到小）
        List<Item> result = new ArrayList<>(heap);
        result.sort(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (b.qty != a.qty) {
                    return Integer.compare(b.qty, a.qty); // 大的在前
                } else {
                    return a.name.compareTo(b.name); // tie-break: 字典序
                }
            }
        });
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items.add(new Item(name, qty));
        }

        List<Item> ans = topK(items, K);

        for (Item it : ans) {
            System.out.println(it.name + " " + it.qty);
        }
    }
}
