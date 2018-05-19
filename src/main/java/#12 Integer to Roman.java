/** 12. Integer to Roman */
class Q12_Integer_to_Roman {

    public static void main(String[] args) {
        System.out.println(new Q12_Integer_to_Roman().intToRoman(1994));
    }

    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder(64);

        int temp = num;
        while (temp > 0) {
            temp = add(builder, 1000, temp, "M");
            temp = add(builder, 900, temp, "CM");
            temp = add(builder, 500, temp, "D");
            temp = add(builder, 400, temp, "CD");
            temp = add(builder, 100, temp, "C");
            temp = add(builder, 90, temp, "XC");
            temp = add(builder, 50, temp, "L");
            temp = add(builder, 40, temp, "XL");
            temp = add(builder, 10, temp, "X");
            temp = add(builder, 9, temp, "IX");
            temp = add(builder, 5, temp, "V");
            temp = add(builder, 4, temp, "IV");
            temp = add(builder, 1, temp, "I");
        }
        return builder.toString();
    }

    static private int add(StringBuilder builder, int size, int num, String append) {
        int temp = num;
        while (temp / size > 0) {
            builder.append(append);
            temp -= size;
        }
        return temp;
    }
}
