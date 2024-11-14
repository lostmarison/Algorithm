import java.util.Arrays;
import java.util.Scanner;

public class sumFactorial {
    /**
     * sumOfFactorial计算1!+2!+3!+…+n!
     *
     * @param n 非负整数，代表n的值（n不大于50）
     * @return 整型数组，代表1!+2!+3!+…+n!
     */
    public int[] sumOfFactorial(int n) {
        int[] a = new int[100]; // 计算阶乘
        int[] b = new int[101]; // 计算阶乘和
        int validIndex = 99; // 记录有效数字的下标
        int k;
        int j;
        int i;
        for (k = 1; k <= n; ++k) { // 累加次数
            // 计算阶乘
            validIndex = 99;
            a[99] = 1; // // 初始化a[99]=1,因为0!=0
            for (i = 1; i <= k; ++i) { // // 从1开始计算到i的阶乘
                int t1 = 0; // t1用于存储乘法运算中的进位
                for (j = 99; j >= validIndex; --j) { // 每乘都应该从个位开始计算
                    a[j] = a[j] * i + t1; // 计算对应位数的乘积
                    t1 = a[j] / 10; // 更新进位t1
                    a[j] %= 10; // 更新当前位的值（只保留个位数）
                }
                // 如果乘法运算后有进位，则需要将进位加到结果的更高位上
                while (t1 > 0) { // 可能会多次进位
                    a[--validIndex] = t1 % 10; // 将进位的个位数添加到更高位
                    t1 /= 10; // 更新进位（去掉已经添加的个位数）
                }
            }
            // 计算阶乘和（大整数加法）
            int t2 = 0; // t2用于存储加法运算中的进位
            for (j = 99; j >= validIndex; --j) {
                b[j] = b[j] + a[j] + t2; // // 计算对应位数的和
                t2 = b[j] / 10; // 更新进位t1
                b[j] %= 10; // 更新当前位的值（只保留个位数）
            }
            if (t2 > 0) { // 加法最多进一位
                b[--validIndex] = t2; // 将进位的个位数添加到更高位
            }

        }
        return Arrays.copyOfRange(b, validIndex, 100);
    }

    public static void main(String[] args) {
        sumFactorial test = new sumFactorial();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] ans = test.sumOfFactorial(n);
        for (int e : ans) {
            System.out.print(e);
        }
        input.close();
    }
}
