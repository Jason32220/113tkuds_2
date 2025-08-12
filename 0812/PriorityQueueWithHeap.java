import java.util.ArrayList;

public class PriorityQueueWithHeap {

    private static class Task {
        String name;
        int priority;
        long timestamp; // 用來確保相同優先級時先加入的先執行

        Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return name + "(priority=" + priority + ")";
        }
    }

    private ArrayList<Task> heap;
    private long timeCounter; // 用於記錄加入順序

    public PriorityQueueWithHeap() {
        heap = new ArrayList<>();
        timeCounter = 0;
    }

    public void addTask(String name, int priority) {
        Task newTask = new Task(name, priority, timeCounter++);
        heap.add(newTask);
        heapifyUp(heap.size() - 1);
    }

    public Task executeNext() {
        if (heap.isEmpty()) {
            System.out.println("No tasks to execute.");
            return null;
        }
        Task root = heap.get(0);
        Task last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        System.out.println("Executing: " + root);
        return root;
    }

    public Task peek() {
        if (heap.isEmpty()) {
            System.out.println("No tasks in queue.");
            return null;
        }
        return heap.get(0);
    }

    public void changePriority(String name, int newPriority) {
        boolean found = false;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).name.equals(name)) {
                heap.get(i).priority = newPriority;
                // 重新調整 heap
                heapifyUp(i);
                heapifyDown(i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Task not found: " + name);
        }
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parent)) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && compare(heap.get(left), heap.get(largest)) > 0) {
                largest = left;
            }
            if (right < size && compare(heap.get(right), heap.get(largest)) > 0) {
                largest = right;
            }
            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    private int compare(Task a, Task b) {
        if (a.priority != b.priority) {
            return Integer.compare(a.priority, b.priority);
        }
        return Long.compare(b.timestamp, a.timestamp) * -1; // 較小時間戳記先
    }

    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("Next task: " + pq.peek());
        pq.executeNext();
        pq.executeNext();
        pq.executeNext();
    }
}
