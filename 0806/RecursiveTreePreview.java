import java.util.*;

public class RecursiveTreePreview {

    // 1. 遞迴計算資料夾的總檔案數（模擬檔案系統）
    static class FileNode {
        String name;
        boolean isFile;
        List<FileNode> children;

        FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            if (!isFile) {
                children = new ArrayList<>();
            }
        }
    }

    public static int countFiles(FileNode node) {
        if (node == null) return 0;
        if (node.isFile) return 1;

        int count = 0;
        for (FileNode child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    // 2. 遞迴列印多層選單結構
    static class MenuItem {
        String name;
        List<MenuItem> subMenu;

        MenuItem(String name) {
            this.name = name;
            this.subMenu = new ArrayList<>();
        }
    }

    public static void printMenu(MenuItem menu, int level) {
        if (menu == null) return;

        String indent = "  ".repeat(level);
        System.out.println(indent + "- " + menu.name);
        for (MenuItem sub : menu.subMenu) {
            printMenu(sub, level + 1);
        }
    }

    // 3. 遞迴處理巢狀陣列的展平（List<Object>可能是Integer或List）
    public static List<Integer> flattenNestedList(List<?> nestedList) {
        List<Integer> flatList = new ArrayList<>();
        for (Object obj : nestedList) {
            if (obj instanceof Integer) {
                flatList.add((Integer) obj);
            } else if (obj instanceof List<?>) {
                flatList.addAll(flattenNestedList((List<?>) obj));
            }
        }
        return flatList;
    }

    // 4. 遞迴計算巢狀清單的最大深度
    public static int maxDepth(List<?> nestedList) {
        int max = 1; // 單層清單深度至少1
        for (Object obj : nestedList) {
            if (obj instanceof List<?>) {
                int depth = 1 + maxDepth((List<?>) obj);
                if (depth > max) max = depth;
            }
        }
        return max;
    }

    // 主程式測試
    public static void main(String[] args) {

        System.out.println("=== 1. 模擬檔案系統計算檔案數 ===");
        FileNode root = new FileNode("root", false);
        FileNode folder1 = new FileNode("folder1", false);
        FileNode folder2 = new FileNode("folder2", false);
        FileNode file1 = new FileNode("file1.txt", true);
        FileNode file2 = new FileNode("file2.txt", true);
        FileNode file3 = new FileNode("file3.txt", true);

        folder1.children.add(file1);
        folder1.children.add(file2);
        folder2.children.add(file3);
        root.children.add(folder1);
        root.children.add(folder2);

        System.out.println("總檔案數: " + countFiles(root));

        System.out.println("\n=== 2. 多層選單列印 ===");
        MenuItem menu = new MenuItem("首頁");
        MenuItem sub1 = new MenuItem("產品");
        MenuItem sub2 = new MenuItem("關於我們");
        MenuItem sub11 = new MenuItem("手機");
        MenuItem sub12 = new MenuItem("筆電");

        sub1.subMenu.add(sub11);
        sub1.subMenu.add(sub12);
        menu.subMenu.add(sub1);
        menu.subMenu.add(sub2);

        printMenu(menu, 0);

        System.out.println("\n=== 3. 巢狀陣列展平 ===");
        List<Object> nestedList = Arrays.asList(1, Arrays.asList(2, 3), 4, Arrays.asList(Arrays.asList(5, 6), 7));
        List<Integer> flat = flattenNestedList(nestedList);
        System.out.println(flat);

        System.out.println("\n=== 4. 巢狀清單最大深度 ===");
        System.out.println("最大深度: " + maxDepth(nestedList));
    }
}
