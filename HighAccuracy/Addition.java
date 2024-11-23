import java.util.Arrays;
import java.util.Scanner;

public class Addition {
    /**
     * 移除指定字符串形式的非负整数前导0。
     *
     * @param num 存储指定字符串形式的非负整数
     * @return 移除num前导0的结果
     */
    public String removeLeadingZeros(String num) {
        int n = num.length(); // 指定非负整数num的位数
        for (int i = 0; i < n; i++) { // 从num的第1个数字依次遍历到第n个数字
            if (num.charAt(i) != '0') { // 如果当前数字非0
                return num.substring(i); // 返回从当前数字开始的剩余元素
            }
        }
        return "0"; // 以上没有返回，说明num为全0，返回字符串0
    }

    /**
     * 返回指定两个字符串形式的非负整数的和。
     *
     * @param a 指定字符串形式的非负整数
     * @param b 指定字符串形式的非负整数
     * @return 数组形式的a与b的和
     */
    public int[] addition(String a, String b) {
        String A = removeLeadingZeros(a); // 移除指定非负整数a的前导0
        String B = removeLeadingZeros(b); // 移除指定非负整数b的前导0
        int len1 = A.length(); // 指定非负整数a的位数
        int len2 = B.length(); // 指定非负整数b的位数
        int n = Math.max(len1, len2); // 对位加法的次数由a、b中较长的位数决定
        int i;
        int j;
        int[] one = new int[n + 1]; // 将非负整数a按右对齐存入数组，不足补0
        // 按右对齐方式，将a的每位数字存入one数组
        for (i = n, j = len1 - 1; j >= 0; i--, j--) {
            one[i] = A.charAt(j) - '0';
        }
        int[] two = new int[n + 1]; // 将非负整数b按右对齐存入数组，不足补0
        // 按右对齐方式，将b的每位数字存入two数组
        for (i = n, j = len2 - 1; j >= 0; i--, j--) {
            two[i] = B.charAt(j) - '0';
        }
        int[] ans = new int[n + 1]; // 存储a+b对位加法的结果，可能会多出1位
        int carry = 0; // 每次对位加法产生的进位，初始为0
        for (i = n; i >= 1; i--) { // 从整数低位到高位，对位相加，共计算n次
            ans[i] = one[i] + two[i] + carry; // 当前数位进行加法计算
            carry = ans[i] / 10; // 计算进位
            ans[i] %= 10; // 当前数位仅保留计算结果的个位数
        }
        if (carry == 1) { // 如果最终产生了一个更高位的进位
            ans[0] = 1; // 存储该进位作为结果的最高位
            return ans; // 返回整个ans做为计算结果
        }
        // 计算结果没有产生更高位的进位，返回ans[1]~ans[n]做为计算结果
        return Arrays.copyOfRange(ans, 1, n + 1);
    }

    public static void main(String[] args) {
        Addition test = new Addition();
        Scanner input = new Scanner(System.in);
        String a = input.next();
        String b = input.next();
        int[] ans = test.addition(a, b);
        for (int e : ans) {
            System.out.print(e);
        }
    }
}
