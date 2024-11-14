import java.util.Arrays;
import java.util.Scanner;

public class Subtraction {
    /**
     * 计算两个用字符串表示的非负大整数的差
     * 注：a>b
     *
     * @param a String类型的对象，代表被减数
     * @param b String类型的对象，代表减数
     * @return int类型的数组，代表的差
     */
    public int[] subtraction(String a, String b) {
        int len1 = a.length(); // 被减数的长度
        int len2 = b.length(); // 减数的长度
        int[] one = new int[len1]; // 存放a的每一位数字
        int[] two = new int[len1]; // 按右对齐存放b的每位数字，不够的补0
        int i;
        int j;
        for (i = 0; i < len1; i++) { // 将a的每位数字存入one数组
            one[i] = a.charAt(i) - '0';
        }
        for (i = len2 - 1, j = len1 - 1; i >= 0; i--, j--) { // 按右对齐将b存放入two数组
            two[j] = b.charAt(i) - '0';
        }
        int[] ans = new int[len1]; // 存放计算结果
        for (i = len1 - 1; i >= 0; --i) { // 从低位到高位，依次逐位做减法
            if (one[i] < two[i]) { // 如果当前被减数位小于减数位
                --one[i - 1]; // 被减数的高一位减1
                one[i] += 10; // 从高位借10
            }
            ans[i] = one[i] - two[i]; // 当前数位做减法
        }
        int validIndex = 0; // 记录有效数字的索引
        while (validIndex < len1 - 1 && ans[validIndex] == 0) {
            ++validIndex;
        }
        return Arrays.copyOfRange(ans, validIndex, len1);
    }

    public static void main(String[] args) {
        Subtraction test = new Subtraction();
        Scanner input = new Scanner(System.in);
        String a = input.next();
        String b = input.next();
        int[] res = test.subtraction(a, b);
        for (int e : res) {
            System.out.print(e);
        }
        input.close();
    }
}
