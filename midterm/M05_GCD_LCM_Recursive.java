import java.util.*;

public class M05_GCD_LCM_Recursive {

    /*
     * 遞迴版 Euclidean Algorithm
     *
     * Time Complexity: O(log(min(a,b)))
     * 說明：
     * - 每次呼叫 gcd(x,y) 時，y 會至少減少一半量級。
     * - 因此遞迴深度最多 O(log(min(a,b)))。
     */
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    /*
     * LCM 計算公式：lcm(a, b) = (a / gcd(a, b)) * b
     * 避免溢位：先做 a / gcd，再乘 b。
     *
     * Time Complexity: O(1)
     */
    private static long lcm(long a, long b, long g) {
        return (a / g) * b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = lcm(a, b, g);

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }
}

/*
 * Overall Time Complexity: O(log(min(a, b)))
 * 說明：
 * - gcd 遞迴深度為 O(log(min(a, b)))。
 * - lcm 計算為 O(1)。
 * - 總體由 gcd 主導 → O(log(min(a, b)))。
 */
