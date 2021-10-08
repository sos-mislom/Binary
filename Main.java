import java.util.Dictionary;
import java.math.*;
import java.util.Hashtable;
import java.util.Scanner;


public class Main {
    static Dictionary<String, Integer> list_of_types = new Hashtable<String, Integer>();

    public static String ToBynary(int tmp) {
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
                res += "1";
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
    public static String SuperDuperFunc(BigInteger n) {
        int bits = n.bitLength();
        int shift = 1 << bits;
        int mask = shift - 1;
        int number = ((Math.abs(n.intValue()) ^ mask) + 1) & mask;
        return ToBynary(shift | number);
    }
    public static String RetCoolFormat(String res, String str_, String type_) {
        int coef = res.length();
        while (coef % 8 != 0){
            coef++;
        }
        res = RepeatStr((coef - res.length()), str_) + res;
        String finalRES = "";
        coef = 0;
        for (int i=0; i < res.length(); i++) {
            if (i % 8 == 0){
                coef ++;
            }
        }
        if (list_of_types.get(type_) > coef){
            String rep = RepeatStr((list_of_types.get(type_) - coef), RepeatStr(8, str_));
            res = rep + res;
            for (int i=0; i < res.length(); i++) {
                if (i % 8 == 0){
                    finalRES += res.substring(i, i+8)+" ";
                }
            }
        }else{
            for (int i=0; i < res.length(); i++) {
                if (i % 8 == 0){
                    finalRES += res.substring(i, i+8)+" ";
                }
            }
        }
        return finalRES;
    }
    public static void main(String[] args) {
        list_of_types.put("byte", 1);
        list_of_types.put("short", 2);
        list_of_types.put("int", 4);
        list_of_types.put("long", 8);
        Scanner get_str = new Scanner(System.in);
        String first_int = get_str.nextLine();
        String type_ = get_str.nextLine();
        get_str.close();
        if(first_int.contains(".") || first_int.contains(",")){
            System.out.println("in development");
        }else if (first_int.contains("-")) {
            System.out.println(RetCoolFormat(SuperDuperFunc(new BigInteger(first_int)), "1", type_));
        }else{
            String res = ToBynary(Integer.parseInt(first_int));
            System.out.println((RetCoolFormat(res,"0", type_)));
        }
    }
}
