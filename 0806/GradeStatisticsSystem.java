public class GradeStatisticsSystem {
    public static void main(String[] args) {
        // 測試資料：學生成績
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        // 變數宣告
        int total = 0;
        int max = scores[0];
        int min = scores[0];
        int aboveAverageCount = 0;

        // 計算總分、最高分、最低分
        for (int score : scores) {
            total += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }

        double average = (double) total / scores.length;

        // 計算高於平均的學生人數
        for (int score : scores) {
            if (score > average) {
                aboveAverageCount++;
            }
        }

        // 統計各等第（A、B、C、D、F）
        int gradeA = 0, gradeB = 0, gradeC = 0, gradeD = 0, gradeF = 0;

        for (int score : scores) {
            if (score >= 90) {
                gradeA++;
            } else if (score >= 80) {
                gradeB++;
            } else if (score >= 70) {
                gradeC++;
            } else if (score >= 60) {
                gradeD++;
            } else {
                gradeF++;
            }
        }

        // 輸出報表
        System.out.println("=== 成績報表 ===");
        System.out.print("成績列表：");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();

        System.out.printf("平均成績：%.2f\n", average);
        System.out.println("最高分數：" + max);
        System.out.println("最低分數：" + min);
        System.out.println("高於平均的人數：" + aboveAverageCount);

        System.out.println("\n各等第人數：");
        System.out.println("A (90~100)：" + gradeA);
        System.out.println("B (80~89)：" + gradeB);
        System.out.println("C (70~79)：" + gradeC);
        System.out.println("D (60~69)：" + gradeD);
        System.out.println("F ( < 60 )：" + gradeF);
    }
}
