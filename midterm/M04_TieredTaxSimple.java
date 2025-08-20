import java.util.*;

public class M04_TieredTaxSimple {

    /*
     * 計算單一收入的稅額
     *
     * Time Complexity: O(1)
     * 說明：只有固定 4 個級距判斷，每次計算最多 4 次 if/else → 常數時間。
     */
    private static int computeTax(int income) {
        int tax = 0;

        if (income <= 120000) {
            tax = (int)(income * 0.05);
        } else if (income <= 500000) {
            tax = (int)(120000 * 0.05 + (income - 120000) * 0.12);
        } else if (income <= 1000000) {
            tax = (int)(120000 * 0.05 + (500000 - 120000) * 0.12
                      + (income - 500000) * 0.20);
        } else {
            tax = (int)(120000 * 0.05 + (500000 - 120000) * 0.12
                      + (1000000 - 500000) * 0.20
                      + (income - 1000000) * 0.30);
        }
        return tax;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int totalTax = 0;
        int[] results = new int[n];

        for (int i = 0; i < n; i++) {
            int income = sc.nextInt();
            int tax = computeTax(income);
            results[i] = tax;
            totalTax += tax;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("Tax: " + results[i]);
        }
        System.out.println("Average: " + (totalTax / n));
    }
}

/*
 * Overall Time Complexity: O(n)
 * 說明：每筆收入計算稅額 O(1)，共 n 筆 → O(n)。
 * 輸出也需 O(n)，因此總時間為 O(n)。
 */
