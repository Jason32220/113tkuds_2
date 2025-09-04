import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class LC20_ValidParentheses_AlertFormat {

    /**
     * 檢查括號字串是否有效。
     *
     * @param s 包含 ()[]{} 的字串
     * @return 如果括號成對且順序正確，返回 true；否則返回 false
     */
    public static boolean isValid(String s) {
        // 如果字串為空，根據題目規則，它被視為有效
        if (s == null || s.length() == 0) {
            return true;
        }

        // 建立一個對應表，用於快速查詢閉括號所對應的開括號
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        // 使用堆疊來儲存遇到的開括號
        Stack<Character> stack = new Stack<>();

        // 遍歷輸入字串中的每個字元
        for (char ch : s.toCharArray()) {
            // 如果是開括號，將其壓入堆疊
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else { // 如果是閉括號
                // 如果堆疊為空或棧頂元素與當前閉括號不匹配，則無效
                if (stack.isEmpty() || stack.pop() != map.get(ch)) {
                    return false;
                }
            }
        }

        // 遍歷結束後，如果堆疊為空，表示所有括號都已成功匹配
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        
        boolean result = isValid(s);
        System.out.println(result);
        
        scanner.close();
    }
}
