public class PrimitiveOperations {

    public static void main(String[] args) {

        // 0) Арифметические операции над двумя int
        int a = 10;
        int b = 3;

        int sum = a + b;
        int diff = a - b;
        int mult = a * b;
        int div = a / b;

        System.out.println("int operations:");
        System.out.println(sum);
        System.out.println(diff);
        System.out.println(mult);
        System.out.println(div);


        // 1) Арифметические операции int + double в одном выражении
        double result = a + b * 2.5 - 4 / 2.0;
        System.out.println("\nint + double:");
        System.out.println(result);


        // 2) Логические операции
        boolean greater = a > b;
        boolean lessOrEqual = a <= b;
        boolean complex = (a > 5) && (b < 5);

        System.out.println("\nLogical operations:");
        System.out.println(greater);
        System.out.println(lessOrEqual);
        System.out.println(complex);


        // 3) Диапазоны типов с плавающей точкой
        System.out.println("\nFloat range:");
        System.out.println("Min: " + Float.MIN_VALUE);
        System.out.println("Max: " + Float.MAX_VALUE);

        System.out.println("\nDouble range:");
        System.out.println("Min: " + Double.MIN_VALUE);
        System.out.println("Max: " + Double.MAX_VALUE);


        // 4) Переполнение int
        int maxInt = Integer.MAX_VALUE;
        System.out.println("\nBefore overflow: " + maxInt);
        maxInt++;
        System.out.println("After overflow: " + maxInt);

    }
}
