import java.util.stream.LongStream;

public class FunctionalProgramming5Parallezing {

    public static void main(String[] args) {
        // You can use BigInteger for huge values

        long time = System.currentTimeMillis();
        System.out.println(LongStream.range(0,1_000_000_000).sum());
        System.out.println(System.currentTimeMillis()-time);

        long time2 = System.currentTimeMillis();
        System.out.println(LongStream.range(0,1_000_000_000).parallel().sum());
        System.out.println(System.currentTimeMillis()-time2);
        //499999999500000000
        //2093
        //499999999500000000
        //179
    }
}
