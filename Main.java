import java.util.Dictionary;
import java.math.*;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    static Dictionary<String, Integer> list_of_types = new Hashtable<String, Integer>();
    private static Scanner get_str = new Scanner(System.in);
    public static String ToBinary(int input_num) {
        if (input_num > 0){
            String res = "";
            int num, index = 0;
            num = input_num;

            while(input_num > 0) {
                input_num >>= 1;
                index ++;
            }

            while(num > 0) {
                index -= 1;
                if (num >= 1 << index) {
                    res += "1";
                    num -= 1 << index;
                }else {
                    res = res + "0";
                }
            }
            String rep = RepeatStr(index, "0");
            return String.format("%s%s", res, rep);
        }else {
            int n = Math.abs(input_num);
            String res = String.valueOf(n % 2);
            while (n >= 2){
                n = n / 2;
                res += String.valueOf(n % 2);
            }
            return new StringBuilder(res).reverse().toString();

        }

    }
    public static String FractionToBin(float frac) {
        String b = "";
        int n = 53;
        while (n > 0) {
            frac = 2 * frac;
            b = b + (int)Math.floor(frac);
            frac = frac - (int)Math.floor(frac);
            n--;
        }
        return b;
    }
    public static String RepeatStr(int i, String str) {
        String result = "";
        for( int u = 0; u < i; u++ ) {
            result = result + str;
        }
        return result;
    }
    public static String FloatOrDouble(String number, String type_) {
        int pp=0, mlen=0, b, k;
        String res="";
        if (Objects.equals(type_, "float")){
            pp = 127;
            mlen = 24;
        } else if (Objects.equals(type_, "double")){
            pp = 1023;
            mlen = 53;}
        String[] insert_num = (number.split("\\."));
        String a = ToBinary(Integer.parseInt((insert_num[0])));
        if (Integer.parseInt((insert_num[0])) < 0){
            res += "1 ";
        }
        else res += "0 ";
        res += ToBinary(a.length() + pp - 1) + " ";
        b = 0;
        k = 10;
        for (int i = 0; i < insert_num[1].length(); i++) {
            b += Integer.parseInt(String.valueOf(i)) / k;
            k *= 10;
        }
        a += String.valueOf(FractionToBin(b));
        res += "1 " + a.substring(2, mlen) + " ";

        return res;
    }
    public static String SuperDuperFunc(BigInteger n) {
        int bits = n.bitLength();
        int shift = 1 << bits;
        int mask = shift - 1;
        int number = ((Math.abs(n.intValue()) ^ mask) + 1) & mask;
        return ToBinary(shift | number);
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
        list_of_types.put("float", 1);
        list_of_types.put("double", 1);

        String first_int = input("! Input ur number: \n! ");
        String type_ = input("! Choose type (byte/short/int/long/float/double): \n! ");
        get_str.close();

        if(first_int.contains("-")){
            System.out.println(RetCoolFormat(SuperDuperFunc(new BigInteger(first_int)), "1", type_));
        }else if (first_int.contains(".") || first_int.contains(",") || Objects.equals(type_, "float") || Objects.equals(type_, "double")) {
            first_int = first_int.replace(',', '.');
            System.out.println(FloatOrDouble(String.valueOf(Float.parseFloat(first_int)), type_));
        }else{
            System.out.println((RetCoolFormat(ToBinary(Integer.parseInt(first_int)),"0", type_)));
        }
    }
    static String input(String str_){
        System.out.print(str_);
        return get_str.next();
    }
}
