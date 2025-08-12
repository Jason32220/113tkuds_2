import java.util.*;

public class MultiLevelCacheSystem {
    static class CacheItem implements Comparable<CacheItem> {
        int key;
        String value;
        int frequency;
        long lastAccessTime;
        int level; // 1=L1,2=L2,3=L3
        double score; // 評分，越高越優先保留

        public CacheItem(int key, String value, int level) {
            this.key = key;
            this.value = value;
            this.level = level;
            this.frequency = 1;
            this.lastAccessTime = System.nanoTime();
            updateScore();
        }

        void access() {
            frequency++;
            lastAccessTime = System.nanoTime();
            updateScore();
        }

        void updateScore() {
            int cost = getCostByLevel(level);
            // 評分策略示例：頻率與成本比，加上時間加權 (時間越新，score略加成)
            score = ((double)frequency / cost) + 1.0 / (System.nanoTime() - lastAccessTime + 1);
        }

        @Override
        public int compareTo(CacheItem o) {
            // score 大排前面 (反轉)
            return Double.compare(o.score, this.score);
        }

        static int getCostByLevel(int level) {
            switch(level) {
                case 1: return 1;
                case 2: return 3;
                case 3: return 10;
                default: return 10;
            }
        }
    }

    private PriorityQueue<CacheItem> l1;
    private PriorityQueue<CacheItem> l2;
    private PriorityQueue<CacheItem> l3;

    private Map<Integer, CacheItem> cacheMap; // key → CacheItem 映射

    private final int L1_CAP = 2;
    private final int L2_CAP = 5;
    private final int L3_CAP = 10;

    public MultiLevelCacheSystem() {
        l1 = new PriorityQueue<>();
        l2 = new PriorityQueue<>();
        l3 = new PriorityQueue<>();
        cacheMap = new HashMap<>();
    }

    // 取得值，同時更新存取資訊並判斷是否層級調整
    public String get(int key) {
        CacheItem item = cacheMap.get(key);
        if (item == null) return null;

        item.access();
        adjustLevel(item);
        rebuildHeaps();
        return item.value;
    }

    // 新增或更新值
    public void put(int key, String value) {
        CacheItem item = cacheMap.get(key);
        if (item != null) {
            item.value = value;
            item.access();
        } else {
            // 新增預設放L3
            item = new CacheItem(key, value, 3);
            cacheMap.put(key, item);
            l3.offer(item);
        }

        adjustLevel(item);
        rebuildHeaps();
        evictIfNeeded();
    }

    // 調整 item 層級：依評分決定升級或降級
    private void adjustLevel(CacheItem item) {
        item.updateScore();

        // 判斷是否需要升級
        if (item.level > 1) {
            int higherLevel = item.level - 1;
            int higherCap = getCapByLevel(higherLevel);
            PriorityQueue<CacheItem> higherHeap = getHeapByLevel(higherLevel);
            if (higherHeap.size() < higherCap) {
                // 移動到更高層
                getHeapByLevel(item.level).remove(item);
                item.level = higherLevel;
                higherHeap.offer(item);
            }
        }

        // 也可加入降級條件 (示意，視策略微調)
    }

    // 超過容量時，從最低層開始淘汰最低分項目
    private void evictIfNeeded() {
        evictFromLevelIfNeeded(1, L1_CAP, l1);
        evictFromLevelIfNeeded(2, L2_CAP, l2);
        evictFromLevelIfNeeded(3, L3_CAP, l3);
    }

    private void evictFromLevelIfNeeded(int level, int capacity, PriorityQueue<CacheItem> heap) {
        while (heap.size() > capacity) {
            CacheItem toEvict = heap.poll();
            if (toEvict != null) {
                cacheMap.remove(toEvict.key);
                // 可考慮往下一層移動 (此版本直接刪除)
            }
        }
    }

    private int getCapByLevel(int level) {
        switch(level) {
            case 1: return L1_CAP;
            case 2: return L2_CAP;
            case 3: return L3_CAP;
            default: return L3_CAP;
        }
    }

    private PriorityQueue<CacheItem> getHeapByLevel(int level) {
        switch(level) {
            case 1: return l1;
            case 2: return l2;
            case 3: return l3;
            default: return l3;
        }
    }

    // 將 heap 重建 (因元素score可能變化)
    private void rebuildHeaps() {
        rebuildHeap(l1);
        rebuildHeap(l2);
        rebuildHeap(l3);
    }

    private void rebuildHeap(PriorityQueue<CacheItem> heap) {
        List<CacheItem> list = new ArrayList<>(heap);
        heap.clear();
        heap.addAll(list);
    }

    // 測試用列印目前各層狀態
    public void printCacheState() {
        System.out.println("L1: " + keysInHeap(l1));
        System.out.println("L2: " + keysInHeap(l2));
        System.out.println("L3: " + keysInHeap(l3));
    }

    private List<Integer> keysInHeap(PriorityQueue<CacheItem> heap) {
        List<Integer> keys = new ArrayList<>();
        for (CacheItem item : heap) {
            keys.add(item.key);
        }
        return keys;
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printCacheState();
        // 期望: L1:[2,3], L2:[1], L3:[]

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printCacheState();
        // 頻繁存取1應該升到L1，調整結果

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printCacheState();
        // 查看層級分佈狀況
    }
}
