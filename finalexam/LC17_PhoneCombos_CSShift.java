import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LC17_PhoneCombos_CSShift {

    // 建立數字到字母的映射表
    private static final Map<Character, String> keypad = new HashMap<>();
    static {
        keypad.put('2', "abc");
        keypad.put('3', "def");
        keypad.put('4', "ghi");
        keypad.put('5', "jkl");
        keypad.put('6', "mno");
        keypad.put('7', "pqrs");
        keypad.put('8', "tuv");
        keypad.put('9', "wxyz");
    }

    /**
     * 主函式，調用回溯方法並返回所有組合。
     *
     * @param digits 輸入的數字字串
     * @return 所有可能的字母組合列表
     */
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        // 如果輸入為空字串，直接返回空列表
        if (digits.isEmpty()) {
            return result;
        }
        // 啟動回溯過程
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    /**
     * 回溯演算法核心函式。
     *
     * @param result 儲存最終結果的列表
     * @param currentCombo 當前正在構建的組合
     * @param digits 輸入的數字字串
     * @param index 當前處理的數字索引
     */
    private static void backtrack(List<String> result, StringBuilder currentCombo, String digits, int index) {
        // 遞歸終止條件：當組合長度等於數字字串長度時，表示完成一個組合
        if (index == digits.length()) {
            result.add(currentCombo.toString());
            return;
        }

        // 獲取當前數字及其對應的字母
        char digit = digits.charAt(index);
        String letters = keypad.get(digit);

        // 遍歷所有可能的字母
        for (char letter : letters.toCharArray()) {
            // 做出選擇：將字母追加到當前組合
            currentCombo.append(letter);
            
            // 遞歸到下一個數字
            backtrack(result, currentCombo, digits, index + 1);
            
            // 回溯：撤銷選擇，回到上一狀態，以便嘗試其他字母
            currentCombo.deleteCharAt(currentCombo.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String digits = scanner.nextLine();
        List<String> result = letterCombinations(digits);
        
        if (result.isEmpty()) {
            System.out.println("0");
        } else {
            for (String combo : result) {
                System.out.println(combo);
            }
        }
        
        scanner.close();
    }
}
