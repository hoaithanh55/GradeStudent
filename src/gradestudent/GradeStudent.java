package gradestudent;

import java.util.Scanner;

public class GradeStudent {

    // Tạo biến cục bộ
    static int weightMid;
    static int weightFinal;
    static int weightHomework;

    static double weightMidTermScore;
    static double weightFinalScore;
    static double weightHomeworkScore;

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        double pointMid;
        double pointFinal;
        double pointHomeword;
        begin();
        do {
            pointMid = midTerm();
            pointFinal = finalTerm();
            pointHomeword = homework();
        } while (weightMid + weightFinal + weightHomework != 100);                  // Tổng 3 weight phải bằng 100
        report(pointMid, pointFinal, pointHomeword);
    }

//    Hiển thị thông điệp chào mừng
    public static void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade .");
        System.out.println();
    }

//    Nhập và tính toán điểm thi giữa kỳ
    public static double midTerm() {
        System.out.println("Midterm:");
        System.out.print("Weight (0-100) ? ");
        weightMid = sc.nextInt();
        System.out.print("Score earned ? ");
        int scoreEarned = sc.nextInt();
        System.out.print("Were scores shifted (1 = yes, 2 = no) ? ");
        int scoreShifted = sc.nextInt();
        int point;
        int totalPoint;

        if (scoreShifted == 1) {
            System.out.print("Shift amount ?");
            int shiftAmount = sc.nextInt();
            point = scoreEarned + shiftAmount;
            totalPoint = Math.min(point, 100);                                         // điểm tối đa của total point là 100.
        } else {
            totalPoint = scoreEarned;
        }

        double weightScore = ((double) totalPoint / 100) * weightMid;                       // Điểm số tính trên thang điểm Weight
        weightMidTermScore = (double) Math.round(weightScore * 10) / 10;                    // Làm tròn lên 1 số thập phân
        System.out.println("Total points = " + totalPoint + " / 100");
        System.out.println("Weighted score = " + weightMidTermScore + " / " + weightMid);
        System.out.println();
        return weightMidTermScore;          // trả về giá trị cho method midTerm
    }

//    Nhập và tính toán điểm thi cuối kỳ
    public static double finalTerm() {
        int point;
        int totalPoint;
        System.out.println("Final : ");
        System.out.print("Weight (0-100) ? ");
        weightFinal = sc.nextInt();
        System.out.print("Score earned ? ");
        int scoreEarned = sc.nextInt();
        System.out.print("Were scores shifted (1 = yes, 2 = no) ? ");
        int scoreShift = sc.nextInt();

        if (scoreShift == 1) {
            System.out.print("Shift amount ? ");
            int shiftAmount = sc.nextInt();
            point = scoreEarned + shiftAmount;
            totalPoint = Math.min(point, 100);                                          // điểm tối đa của total point là 100.
        } else {
            totalPoint = scoreEarned;
        }

        double weightScore = ((double) totalPoint / 100) * weightFinal;                     // Điểm số tính trên thang điểm Weight
        weightFinalScore = (double) Math.round(weightScore * 10) / 10;
        System.out.println("Total points = " + totalPoint + " / 100");
        System.out.println("Weighted score = " + weightFinalScore + " / " + weightFinal);
        System.out.println();
        return weightFinalScore;                // trả về giá trị cho method finalTerm
    }

//    Nhập và tính toán điểm bài tập về nhà
    public static double homework() {
        int firstScore = 0;
        int totalScoreMax = 30;                                                             // Max of section point is 30
        int totalPoint;
        int totalScore;
        System.out.println("Homework : ");
        System.out.print("Weight (0 - 100) ? ");
        weightHomework = sc.nextInt();
        System.out.print("Number of assignments ? ");
        int totalAssignment = sc.nextInt();
        sc.nextLine();
        String[] arrScoreMax = new String[totalAssignment];
        for (int i = 1; i <= totalAssignment; i++) {
            System.out.print("Assignments " + i + " score and max ? ");                     // Điểm thực và điểm tối đa
            String scoreMax = sc.nextLine();
            //Tách chuỗi dựa trên khoảng trắng
            arrScoreMax = scoreMax.split(" ");
            int score = Integer.parseInt(arrScoreMax[0]);
            int max = Integer.parseInt(arrScoreMax[1]);
            firstScore += score;
            totalScoreMax += max;

        }

//        Điểm tối đa của phần Assignment là 150
        totalScore = Math.min(firstScore, 150);

        System.out.print("how many sections did you attend ? ");                        // số điểm chuyên cần với mỗi buổi điểm danh
        int student = sc.nextInt();
        int point = student * 5;

//        Điểm tối đa của phần Attend là 30
        int sectionPoint = Math.min(point, 30);
        System.out.println("Section points = " + sectionPoint + " / 30");
        totalPoint = totalScore + sectionPoint;
        System.out.println("Total points = " + totalPoint + " / " + totalScoreMax);
        double weightScore = ((double) totalPoint / totalScoreMax) * weightHomework;
        weightHomeworkScore = Math.round(weightScore * 10) / 10.0;
        System.out.println("Weight score = " + weightHomeworkScore + " / " + weightHomework);
        System.out.println();
        if (weightMid + weightFinal + weightHomework != 100) {
            System.out.println();
            System.out.println("Your weight input wrong (total Weight = 100), please try again ! ");
            System.out.println();
        }
        return weightHomeworkScore;                 // trả về giá trị cho method  HomeWork
    }

//    Tính toán về hiển thị kết quả GPA, thông báo nhận xét tương ứng
    public static void report(double a, double b, double c) {
        double gpa;
        String message;
        double grade = a + b + c;
        if (grade == 100) {
            gpa = 4.0;
            message = "excellent";
        } else if (grade >= 85 && grade < 100) {
            gpa = 3.0;
            message = "verry good";
        } else if (grade >= 75 && grade < 85) {
            gpa = 2.0;
            message = "good";
        } else if (grade >= 60 && grade < 75) {
            gpa = 1.0;
            message = "average";
        } else {
            gpa = 0.0;
            message = "weak";
        }
        System.out.println("Overall percentage = " + grade);
        System.out.println("Your grade will be at least : " + gpa);
        System.out.println("Your Ranked academic : " + message + " !");
    }
}
