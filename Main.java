import java.util.Scanner;


public class Main {
    public static String toBin(int tmp) {
        String res = "";
        int num, k = 0;
        num = tmp;

        while(tmp > 0) {
            tmp >>= 1;
            k ++;
        }

        while(num > 0) {
            k -= 1;
            if (num >= 1 << k) {
                res = res + "1";
                num -= 1 << k;
            }else {
                res = res + "0";
            }
        }
        String rep = RepeatStr(k, "0");
        return String.format("%s%s", res.toString(), rep);
    }
    public static String RepeatStr(int i, String str) {
        String result = "";
        for( int u = 0; u < i; u++ ) {
            result = result + str;
        }
        return result;
    }
    public static String toBaseOst(float frac) {
        String b = "";
        int n = 16;
        while (n > 0) {
            frac = 2 * frac;
            b = b + (int)Math.floor(frac);
            frac = frac - (int)Math.floor(frac);
            n--;
        }
        return b.toString();
    }
    public static void main(String[] args) {
        Scanner get_str = new Scanner(System.in);
        String first_int = get_str.nextLine();
        get_str.close();
        if((first_int.contains(".") || first_int.contains(",") && first_int.contains("-"))){
            first_int = first_int.substring(1);
            first_int = first_int.replace(',', '.');
            String[] insert_num = (first_int.split("\\."));
            String final_bin = toBin(Integer.parseInt((insert_num[0])));
            String final_ost = toBaseOst(Float.parseFloat(String.format("0.%s", insert_num[1])));
            int coef_bin = final_bin.length();

            while (coef_bin % 8 != 0){
                coef_bin++;
            }
            String rep_bin = RepeatStr(coef_bin - final_bin.length(), "0");
            final_bin = rep_bin + final_bin;
            String final_bin2 = final_bin.replace("0", "c").replace("1", "0").replace("c", "1");
            final_bin2 = final_bin.substring(0, final_bin.length()-1) + '1';
            int coef_ost = final_bin.length();

            while (coef_ost % 8 != 0){
                coef_ost++;
            }
            String rep_ost = RepeatStr(coef_ost - final_ost.length(), "0");
            final_ost = rep_ost + final_ost;
            String final_ost2 = final_ost.replace("0", "c").replace("1", "0").replace("c", "1");
            final_ost2 = final_ost.substring(0, final_ost.length()-1) + '1';

            System.out.println(final_bin2 + '.' + final_ost2);
        }else if (first_int.contains("-")) {
            first_int = first_int.substring(1);
            String res = toBin(Integer.parseInt(first_int));
            int coef = res.length();
            while (coef % 8 != 0){
                coef++;
            }
            String rep = RepeatStr(coef - res.length(), "0");
            res = rep + res;
            String final_bin = res.replace("0", "c").replace("1", "0").replace("c", "1");
            final_bin = final_bin.substring(0, res.length()-1) + '1';
            System.out.println(final_bin);
        }else if((first_int.contains(".") || first_int.contains(","))){
            first_int = first_int.replace(',', '.');
            String[] insert_num = (first_int.split("\\."));
            String final_bin = toBin(Integer.parseInt((insert_num[0])));
            String final_ost = toBaseOst(Float.parseFloat(String.format("0.%s", insert_num[1])));
            System.out.println(final_bin + '.' + final_ost);

        }else System.out.println(toBin(Integer.parseInt(first_int)));
        }
    }
